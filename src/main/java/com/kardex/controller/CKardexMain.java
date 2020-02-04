package com.kardex.controller;

import java.util.HashMap;
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

@RestController
public class CKardexMain
{
	@Autowired
	IKardexMainRepository iKardexMainRepository;
	private ResponseEntity<Map<String, Object>> responseEntityObject;
	private ResponseEntity<Map<String, String>> responseEntityString;
	private Map<String, String> stringAndStringMap = new HashMap<>();
	private Map<String, Object> stringAndObjecMap = new HashMap<>();
	private Map<String, Object> allMap = new HashMap<>();

	/**
	 * Este método expone el endpoint que obtiene todos los Kardex existentes en la base de datos.
	 * 
	 * @return AllKardexMain retorna todos los Kardex Main mappeados en base de datos.
	 **/
	@GetMapping("/kardex")
	public ResponseEntity<Map<String, Object>> getAllKardexMain()
	{
		try {
			List<MKardexMain> AllKardexMain = iKardexMainRepository.findAll();
			stringAndObjecMap.clear();
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "200");
			stringAndStringMap.put("Message", "All Kardex Info have been found");
			stringAndObjecMap.put("kardexMainList", AllKardexMain);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.OK).body(allMap);
		} catch (Exception e) {
			stringAndObjecMap.clear();
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "500");
			stringAndStringMap.put("Message", "Kardex Info not found");
			stringAndObjecMap.put("kardexMainList", null);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(allMap);
		}
		return responseEntityObject;
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
			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "Kardex created successfully");
			stringAndStringMap.put("HttpStatus", "200");
			responseEntityString = ResponseEntity.status(HttpStatus.OK).body(stringAndStringMap);
		}
		catch (Exception e)
		{
			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "The Kardex couldn't be created");
			stringAndStringMap.put("HttpStatus", "500");
			responseEntityString = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stringAndStringMap);
		}
		return responseEntityString;
	}

	/**
	 * Este método expone el endpoint que obtiene el id del Kardex a ser consultado.
	 * 
	 * @param KardexMain
	 *            object contain kardex main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@GetMapping("/kardex/{id}")
	public ResponseEntity<Map<String, Object>> getByIdKardexMain(@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			Optional<MKardexMain> kardexMain = iKardexMainRepository.findById(KardexMainId);
			MKardexMain kardexMainResult = kardexMain.get();
			stringAndStringMap.clear();
			stringAndObjecMap.clear();
			stringAndStringMap.put("Message", "Kerdex Found");
			stringAndStringMap.put("HttpStatus", "200");
			stringAndObjecMap.put("kardexInfo", kardexMainResult);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.OK).body(allMap);
		}
		catch (Exception e)
		{
			stringAndStringMap.clear();
			stringAndObjecMap.clear();
			stringAndStringMap.put("Message", "Kerdex not Found");
			stringAndStringMap.put("HttpStatus", "500");
			stringAndObjecMap.put("kardexInfo", null);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(allMap);
		}
		return responseEntityObject;
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

			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "Kardex updated successfully");
			stringAndStringMap.put("HttpStatus", "200");
			stringAndStringMap.put("kardexIdUpdated", KardexMainId.toString());
			responseEntityString = ResponseEntity.status(HttpStatus.OK).body(stringAndStringMap);
		}
		catch (Exception e)
		{
			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "Kardex not found");
			stringAndStringMap.put("kardexIdUpdated", KardexMainId.toString());
			stringAndStringMap.put("HttpStatus", "500");
			responseEntityString = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stringAndStringMap);
		}
		return responseEntityString;
	}

	/**
	 * Este método expone el endpoint para la eliminación del método por id.
	 * 
	 * @param KardexMainId id que contiene el kardex a ser eliminado.
	 * @return responseEntity que response ok (200) o error de servidor (500).
	 **/
	@DeleteMapping("/kardex/{id}")
	public ResponseEntity<?> deleteByIdKardexMain(@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			iKardexMainRepository.deleteById(KardexMainId);
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "200");
			stringAndStringMap.put("Message", "Kardex deleted successfully");
			stringAndStringMap.put("KardexId", KardexMainId.toString());
			responseEntityString = ResponseEntity.status(HttpStatus.OK).body(stringAndStringMap);
		}
		catch (Exception e)
		{
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "500");
			stringAndStringMap.put("Message", "Kardex couldn't be deleted");
			stringAndStringMap.put("KardexId", KardexMainId.toString());
			responseEntityString = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stringAndStringMap);
		}
		return responseEntityString;
	}

}
