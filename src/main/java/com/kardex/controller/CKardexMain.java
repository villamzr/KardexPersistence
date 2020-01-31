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

import com.kardex.domain.MKardexMain;
import com.kardex.repository.IKardexMainRepository;
import com.kardex.utils.MapResponse;

@RestController
public class CKardexMain
{
	@Autowired
	IKardexMainRepository iKardexMainRepository;

	/**
	 * Este método expone el endpoint que obtiene todos los Kardex existentes en la base de datos.
	 * 
	 * @return AllKardexMain retorna todos los Kardex Main mappeados en base de datos.
	 **/
	@GetMapping("/kardex")
	public Map<String, List<MKardexMain>> getAllKardexMain()
	{
		List<MKardexMain> AllKardexMain = iKardexMainRepository.findAll();
		MapResponse.clearGenericMapList();
		MapResponse.addGenericMap("kardexMainList", AllKardexMain);
		Map<String, List<MKardexMain>> AllKardexMainResult = MapResponse.getGenericMapList();
		return AllKardexMainResult;
	}

	/**
	 * Este método expone el endpoint que crea nuevos kardex en base de datos.
	 * 
	 * @param KardexMain
	 *            Objeto que contiene la información mapeada necesaria para crear un nuevo Kardex.
	 * @return responseEntity response OK (200) o Server Error (500).
	 **/
	@PostMapping("/kardex")
	public ResponseEntity<Map<String, String>> newKardexMain(@Valid @RequestBody MKardexMain KardexMain)
	{
		try
		{
			iKardexMainRepository.save(KardexMain);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex created successfully");
			MapResponse.addGenericMap("HttpStatus", "200");
		}
		catch (Exception e)
		{
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
	 * Este método expone el endpoint que obtiene el id del Kardex a ser consultado.
	 * 
	 * @param KardexMain
	 *            object contain kardex main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@GetMapping("/kardex/{id}")
	public ResponseEntity<Map<String, Map<String, String>>> getByIdKardexMain(
			@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			Optional<MKardexMain> kardexMain = iKardexMainRepository.findById(KardexMainId);
			MKardexMain kardexMainResult = kardexMain.get();
			MapResponse.clearGenericMapped();
			MapResponse.setKardexMainMap(kardexMainResult);
			MapResponse.addGenericMap("kardexInfo", MapResponse.getKardexMainMap());
		}
		catch (Exception e)
		{
			MapResponse.clearGenericMapped();
			MapResponse.addGenericMap("Message", "Kerdex not Found");
			MapResponse.addGenericMap("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			MapResponse.addGenericMap("Info", MapResponse.getGenericMap());
			ResponseEntity<Map<String, Map<String, String>>> responseEntity = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MapResponse.getMapGenericMapped());
			return responseEntity;
		}
		ResponseEntity<Map<String, Map<String, String>>> responseEntity = ResponseEntity.ok()
				.body(MapResponse.getMapGenericMapped());
		return responseEntity;
	}

	/**
	 * Este método expone el endpoint que actualiza un kardex, según sea el id solicitado.
	 * 
	 * @param KardexMainId
	 *            Id que contiene el Kardex a ser actualizado.
	 * @param kardexMainBody
	 *            Contiene el body con la información del Kardex.
	 * @return responseEntity responde OK or Server Error.
	 **/
	@PutMapping("/kardex/{id}")
	public ResponseEntity<Map<String, String>> updateKardex(@PathVariable(value = "id") Long KardexMainId,
			@Valid @RequestBody MKardexMain kardexMainBody)
	{
		try
		{
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
			MapResponse.addGenericMap("kardexIdUpdated", KardexMainId.toString());
		}
		catch (Exception e)
		{
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "Kardex not found");
			MapResponse.addGenericMap("kardexIdUpdated", KardexMainId.toString());
			MapResponse.addGenericMap("HttpStatus", "500");
			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}
		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
		return responseEntity;
	}

	/**
	 * Este método expone el endpoint que elimina un Kardex, según sea el id solicitado.
	 * 
	 * @param KardexMainId
	 *            Id que contiene el Kardex a ser eliminado.
	 * @return responseEntity responde OK o Server Error.
	 **/
	@DeleteMapping("/kardex/{id}")
	public ResponseEntity<?> deleteByIdKardexMain(@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			iKardexMainRepository.deleteById(KardexMainId);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("Message", "Kardex deleted successfully");
			MapResponse.addGenericMap("KardexId", KardexMainId.toString());
		}
		catch (Exception e)
		{
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("HttpStatus", "500");
			MapResponse.addGenericMap("Message", "Kardex couldn't be deleted");
			MapResponse.addGenericMap("KardexId", KardexMainId.toString());
			ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MapResponse.getGenericMap());
			return responseEntity;
		}

		ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(MapResponse.getGenericMap());
		return responseEntity;
	}

}
