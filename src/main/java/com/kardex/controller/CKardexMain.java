package com.kardex.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.domain.MKardexMain;
import com.kardex.repository.IKardexMainRepository;
import com.kardex.utils.MapResponse;

@RestController
public class CKardexMain {
	@Autowired
	IKardexMainRepository iKardexMainRepository;

	/**
	 * This method expose the end point that get all kardex existing in database.
	 * 
	 * @return AllKardexMain return all Kardex main info mapped.
	 **/
	@GetMapping("/kardex")
	public List<MKardexMain> getAllKardexMain() {
		List<MKardexMain> AllKardexMain = iKardexMainRepository.findAll();
		return AllKardexMain;
	}

	/**
	 * This method expose the end point that create new kardex in database.
	 * 
	 * @param KardexMain object contain kardex main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@PostMapping("/kardex")
	public ResponseEntity<Map<String, String>> newKardexMain(@Valid @RequestBody MKardexMain KardexMain) {
		try {
			iKardexMainRepository.save(KardexMain);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex created successfully");
			MapResponse.addGenericMap("HttpStatus", "200");
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "The Kardex couldn't be created");
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * This method expose the end point that get kardex by id.
	 * 
	 * @param KardexMain object contain kardex main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@GetMapping("/kardex/{id}")
	public ResponseEntity<Map<String, String>> getByIdKardexMain(@PathVariable(value = "id") Long KardexMainId) {
		try {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex found");
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("HttpStatus", new Date().toString());
			MapResponse.addGenericMap("Kardex", iKardexMainRepository.findById(KardexMainId).get().getObject());
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex not found");
			MapResponse.addGenericMap("KardexId requested", KardexMainId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * This method expose the end point that update kardex by id.
	 * 
	 * @param KardexMainId   id contain kardex to be updated.
	 * @param kardexMainBody containt the body with the kardex information.
	 * @return responseEntity response OK or Server Error.
	 **/
	@PutMapping("/kardex/{id}")
	public ResponseEntity<Map<String, String>> updateKardex(@PathVariable(value = "id") Long KardexMainId,
			@Valid @RequestBody MKardexMain kardexMainBody) {
		try {
			Optional<MKardexMain> kardex = iKardexMainRepository.findById(KardexMainId);
			MKardexMain kardexMain = kardex.get();
			kardexMain.setObject(kardexMainBody.getObject());
			kardexMain.setSupplier(kardexMainBody.getSupplier());
			kardexMain.setReference(kardexMainBody.getReference());
			kardexMain.setUnit(kardexMainBody.getUnit());
			kardexMain.setLocation(kardexMainBody.getLocation());
			kardexMain.setMin(kardexMainBody.getMin());
			kardexMain.setMax(kardexMainBody.getMax());
			iKardexMainRepository.save(kardexMain);

			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex updated successfully");
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("KardexId updated", KardexMainId.toString());
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex not found");
			MapResponse.addGenericMap("KardexId requested", KardexMainId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * This method expose the end point that delete kardex by id.
	 * 
	 * @param KardexMainId id contain kardex to be deleted.
	 * @return responseEntity response OK or Server Error.
	 **/
	@DeleteMapping("/kardex/{id}")
	public ResponseEntity<?> deleteByIdKardexMain(@PathVariable(value = "id") Long KardexMainId) {
		try {
			iKardexMainRepository.deleteById(KardexMainId);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex deleted successfully");
			MapResponse.addGenericMap("KardexId deleted", KardexMainId.toString());
			MapResponse.addGenericMap("HttpStatus", "200");
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex couldn't be deleted");
			MapResponse.addGenericMap("KardexId requested", KardexMainId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}

		ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(MapResponse.getGenericMap());
		return responseEntity;
	}

}
