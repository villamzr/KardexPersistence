package com.kardex.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.kardex.domain.ResponseAllKardexDetails;
import com.kardex.domain.ResponseDeleteByIdKardexDetails;
import com.kardex.domain.ResponseGetByIdKardexDetails;
import com.kardex.domain.ResponseNewKardexDetails;
import com.kardex.domain.ResponsePutKardexMain;
import com.kardex.repository.IKardexDetailsRepository;

/**
 * Esta clase expone los endpoints de: a) Obtener todos los KardesDetails
 * contenidos en base de datos. b) Obtener un KardexDetails filtrado por id. c)
 * Actualizar un KardexDetails. d) Eliminar un KardexDetails.
 * 
 * @author Alejandro Villamizar
 * @version 1.0.0
 * @since 1.0.0
 **/
@RestController
public class CKardexDetails {
	private static final Logger log = LoggerFactory.getLogger(CKardexDetails.class);
	@Autowired
	IKardexDetailsRepository iKardexDetailsRepository;
	private ResponseEntity<ResponseAllKardexDetails> responseAllKardexDetails;
	private ResponseEntity<ResponseNewKardexDetails> responseNewKardexDetails;
	private ResponseEntity<ResponseGetByIdKardexDetails> responseGetByIdKardexDetails;
	private ResponseEntity<ResponsePutKardexMain> responsePutKardexMain;
	private ResponseEntity<ResponseDeleteByIdKardexDetails> responseDeleteByIdKardexDetails;

	/**
	 * Este método expone el endpoint que obtiene todos los kardexDetails existentes
	 * en bases de datos.
	 * 
	 * @return AllKardexMain retorna todos los KardexDetails main info mapeados.
	 **/
	@GetMapping("/kardexDetails")
	public ResponseEntity<ResponseAllKardexDetails> getAllKardexDetails() {		
		try
		{
			List<MKardexDetails> ListKardexDetails = iKardexDetailsRepository.findAll();
			ResponseAllKardexDetails response = new ResponseAllKardexDetails(200, "KardexDetails found", ListKardexDetails);
			responseAllKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponseAllKardexDetails response = new ResponseAllKardexDetails(500, "KardexDetails found", null);
			responseAllKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseAllKardexDetails;
	}

	/**
	 * Este método expone el endpoint que crea nuevos KardexDetails en bases de
	 * datos.
	 * 
	 * @param KardexMain
	 *            Objeto que contiene el body de un KardexDetail.
	 * @return responseEntity responde OK o Server Error.
	 **/
	@PostMapping("/kardexDetails")
	public ResponseEntity<ResponseNewKardexDetails> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail) {
		try {
			iKardexDetailsRepository.save(KardexDetail);
			ResponseNewKardexDetails response = new ResponseNewKardexDetails(200, "KardexDetails created successfully");
			responseNewKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			ResponseNewKardexDetails response = new ResponseNewKardexDetails(500, "KardexDetails could not be created");
			responseNewKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}	
		return responseNewKardexDetails;
	}

	/**
	 * Este método expone el endpoint que obtiene los kardexDetails por id.
	 * 
	 * @param KardexMain el objeto contiene los kardex details info mapeados.
	 * @return responseEntity responde OK (200) o Server Error(500).
	 **/
	@GetMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponseGetByIdKardexDetails> getByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		try {
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			ResponseGetByIdKardexDetails response = new ResponseGetByIdKardexDetails(200, "KardexDetails found", kardexDetails);
			responseGetByIdKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			ResponseGetByIdKardexDetails response = new ResponseGetByIdKardexDetails(500, "KardexDetails not found", null);
			responseGetByIdKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseGetByIdKardexDetails;
	}

	/**
	 * Este método expone el endpoint para actualizar los kardexDetails por id.
	 * 
	 * @param KardexMainId   id que representa el kardexDetails a ser actualizado.
	 * @param kardexMainBody es el body con la información de un kardex.
	 * @return responseEntity response un OK (200) o un Server Error (500).
	 **/
	@PutMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponsePutKardexMain> updateKardexDetails(@PathVariable(value = "id") Long KardexDetailsId,
			@Valid @RequestBody MKardexDetails kardexDetailsBody) {
		try {
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			kardexDetails.setIdKardexMain(kardexDetailsBody.getIdKardexMain());
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
			ResponsePutKardexMain response = new ResponsePutKardexMain(200, "KardexDetails updated", KardexDetailsId);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			ResponsePutKardexMain response = new ResponsePutKardexMain(500, "KardexDetails could not be updated", KardexDetailsId);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responsePutKardexMain;
	}

	/**
	 * Este método contiene el endpoint que elimina un KardexDetail por Id.
	 * 
	 * @param KardexDetailsId id que representa el kardexDetails a ser eliminado.
	 * @return responseEntity responde OK (200) o Server Error (500).
	 **/
	@DeleteMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponseDeleteByIdKardexDetails> deleteByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		try {
			iKardexDetailsRepository.deleteById(KardexDetailsId);
			ResponseDeleteByIdKardexDetails response = new ResponseDeleteByIdKardexDetails(200, "KardexDetails deleted successfully", KardexDetailsId);
			responseDeleteByIdKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			ResponseDeleteByIdKardexDetails response = new ResponseDeleteByIdKardexDetails(500, "KardexDetails could not be deleted", null);
			responseDeleteByIdKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseDeleteByIdKardexDetails;
	}

}
