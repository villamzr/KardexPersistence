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
public class CKardexDetails
{
	@Autowired
	IKardexDetailsRepository iKardexDetailsRepository;

	/**
	 * Este método expone el endpoint que obtiene todos los KardexDetails existentes en base de datos
	 * 
	 * @return AllKardexMain retorna una lista de todos los KardexDetails almacenados en base de datos.
	 **/
	@GetMapping("/kardexDetails")
	public List<MKardexDetails> getAllKardexMain()
	{
		List<MKardexDetails> AllKardexDetails = iKardexDetailsRepository.findAll();
		return AllKardexDetails;
	}

	/**
	 * Este método expone los endpoint para crear nuevos KardexDetails en base de datos.
	 * 
	 * @param KardexMain
	 *            Objeto que contiene el body de un KardexDetail.
	 * @return responseEntity responde OK o Server Error.
	 **/
	@PostMapping("/kardexDetails")
	public ResponseEntity<Map<String, String>> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail)
	{
		try
		{
			iKardexDetailsRepository.save(KardexDetail);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails created successfully");
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
	 * Este método expone el endpoint que obtiene un KardexDetail según sea el id.
	 * 
	 * @param KardexDetailsId
	 *            Objeto que contiene el id del KardexDetails a ser obtenido.
	 * @return responseEntity response OK o Server Error.
	 **/
	@GetMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String, String>> getByIdKardexMain(@PathVariable(value = "id") Long KardexDetailsId)
	{
		try
		{
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails found");
			MapResponse.addGenericMap("HttpStatus", "200");
			MapResponse.addGenericMap("Kardex", kardexDetails.getDescription());
		}
		catch (Exception e)
		{
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
	 * Este método expone el endpoint que actualiza un kardexDetails según el id.
	 * 
	 * @param KardexMainId
	 *            Id del kardexDetails a ser actualizado.
	 * @param kardexMainBody
	 *            Contiene el body con la nueva información del KardexDetails.
	 * @return responseEntity responde OK o Server Error.
	 **/
	@PutMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String, String>> updateKardex(@PathVariable(value = "id") Long KardexDetailsId,
			@Valid @RequestBody MKardexDetails kardexDetailsBody)
	{
		try
		{
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
		}
		catch (Exception e)
		{
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
	 * Este método expone el endpoint que elimina el KardexDetails, según sea el Id.
	 * 
	 * @param KardexDetailsId
	 *            Id que contiene el kardexDetails a ser eliminado.
	 * @return responseEntity responde OK o Server Error.
	 **/
	@DeleteMapping("/kardexDetails/{id}")
	public ResponseEntity<?> deleteByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId)
	{
		try
		{
			iKardexDetailsRepository.deleteById(KardexDetailsId);
			MapResponse.clearGenericMap();
			MapResponse.addGenericMap("Message", "KardexDetails deleted successfully");
			MapResponse.addGenericMap("KardexId deleted", KardexDetailsId.toString());
			MapResponse.addGenericMap("HttpStatus", "200");
		}
		catch (Exception e)
		{
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
