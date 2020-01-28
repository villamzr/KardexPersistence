package com.kardex.controller;

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

import com.kardex.domain.MKardexDetails;
import com.kardex.repository.IKardexDetailsRepository;
import com.kardex.utils.MapResponse;

@RestController
public class CKardexDetails {
	@Autowired
	IKardexDetailsRepository iKardexDetailsRepository;

	/**
	 * This method expose the end point that get all kardexDetails existing in
	 * database.
	 * 
	 * @return AllKardexMain return all KardexDetails main info mapped.
	 **/
	@GetMapping("/kardexDetails")
	public List<MKardexDetails> getAllKardexMain() {
		List<MKardexDetails> AllKardexDetails = iKardexDetailsRepository.findAll();
		return AllKardexDetails;
	}

	/**
	 * This method expose the end point that create new kardexDetails in database.
	 * 
	 * @param KardexMain object contain kardexDetails main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@PostMapping("/kardexDetails")
	public ResponseEntity<Map<String, String>> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail) {
		try {
			iKardexDetailsRepository.save(KardexDetail);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails created successfully");
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
	 * This method expose the end point that get kardexDetails by id.
	 * 
	 * @param KardexMain object contain kardex details info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@GetMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String, String>> getByIdKardexMain(@PathVariable(value = "id") Long KardexDetailsId) {
		try {
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails found");
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("Kardex", kardexDetails.getDescription());
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails not found");
			MapResponse.addGenericMap("KardexDetailsId requested", KardexDetailsId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * This method expose the end point that update kardexDetails by id.
	 * 
	 * @param KardexMainId   id contain kardexDetails to be updated.
	 * @param kardexMainBody containt the body with the kardex information.
	 * @return responseEntity response OK or Server Error.
	 **/
	@PutMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String, String>> updateKardex(@PathVariable(value = "id") Long KardexDetailsId,
			@Valid @RequestBody MKardexDetails kardexDetailsBody) {
		try {
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			kardexDetails.setDate(kardexDetailsBody.getDate());
			kardexDetails.setDescription(kardexDetailsBody.getDescription());
			kardexDetails.setUnitValue(kardexDetailsBody.getUnitValue());
			kardexDetails.setInputQuantity(kardexDetailsBody.getInputQuantity());
			kardexDetails.setInputValue(kardexDetailsBody.getInputValue());
			kardexDetails.setOutputQuantity(kardexDetailsBody.getOutputQuantity());
			kardexDetails.setOutputValue(kardexDetailsBody.getOutputValue());
			kardexDetails.setBalanceQuantity(kardexDetailsBody.getBalanceQuantity());
			kardexDetails.setBalanceValue(kardexDetailsBody.getBalanceValue());
			iKardexDetailsRepository.save(kardexDetails);

			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails updated successfully");
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("KardexId updated", KardexDetailsId.toString());
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails not found");
			MapResponse.addGenericMap("KardexId requested", KardexDetailsId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			e.printStackTrace();
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * This method expose the end point that delete kardex by id.
	 * 
	 * @param KardexDetailsId id contain kardexDetails to be deleted.
	 * @return responseEntity response OK or Server Error.
	 **/
	@DeleteMapping("/kardexDetails/{id}")
	public ResponseEntity<?> deleteByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		try {
			iKardexDetailsRepository.deleteById(KardexDetailsId);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails deleted successfully");
			MapResponse.addGenericMap("KardexId deleted", KardexDetailsId.toString());
			MapResponse.addGenericMap("HttpStatus", "200");
		} catch (Exception e) {
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails couldn't be deleted");
			MapResponse.addGenericMap("KardexId requested", KardexDetailsId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
			
		}
		ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(MapResponse.getGenericMap());
		return responseEntity;
	}

}
