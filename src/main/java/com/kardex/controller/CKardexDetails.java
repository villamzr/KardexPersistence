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
 * Esta clase expone los endpoints de:
 * Obtener todos los KardexDetails - #@GetMapping("/kardexDetails").
 * Obtener KardexDetails por id - #@GetMapping("/kardexDetails/{id}").
 * Actualizar un KardexDetail por id - #@PutMapping("/kardexDetails/{id}").
 * Crear un nuevo KardexDetail - #@PostMapping("/kardexDetails").
 * Eliminar un KardexDetail por id - #@DeleteMapping("/kardexDetails/{id}").
 * 
 * @author Alejandro Villamizar
 * @version 1.0.2
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
	 * @return responseAllKardexDetails retorna la lista de todos los KardexDetails.
	 **/
	@GetMapping("/kardexDetails")
	public ResponseEntity<ResponseAllKardexDetails> getAllKardexDetails() {
		log.debug("Ingresando al método getAllKardexDetails() de la clase controladora CKardexDetails");
		try
		{
			log.debug("Consultando KardexDetails existentes en base de datos");
			List<MKardexDetails> ListKardexDetails = iKardexDetailsRepository.findAll();
			log.debug("KardexDetails existentes en base de datos: "+ListKardexDetails);
			ResponseAllKardexDetails response = new ResponseAllKardexDetails(200, "KardexDetails found", ListKardexDetails);
			log.debug("Objeto que responde el servicio: /kardexDetails (GET): "+response);
			responseAllKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo obtener respuesta de la base de datos");
			ResponseAllKardexDetails response = new ResponseAllKardexDetails(500, "KardexDetails found", null);
			log.error("Objeto que responde el servicio: "+response);
			log.error("Causa del error: ", e.getCause());
			responseAllKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método getAllKardexDetails() de la clase controladora CKardexDetails");
		return responseAllKardexDetails;
	}

	/**
	 * Este método expone el endpoint que crea nuevos KardexDetails en bases de
	 * datos.
	 * 
	 * @param KardexDetail Objeto que contiene el body de un nuevo KardexDetail.
	 * @return responseNewKardexDetails retorna si la creación fue exitosa o no.
	 **/
	@PostMapping("/kardexDetails")
	public ResponseEntity<ResponseNewKardexDetails> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail) {
		log.debug("Ingresando al método newKardexDetails() de la clase controladora CKardexDetails"); 
		try {
			log.debug("Persistiendo el nuevo KardexDetails en la base de datos");
			iKardexDetailsRepository.save(KardexDetail);
			log.debug("Body del nuevo KardexDetails:"+KardexDetail);
			ResponseNewKardexDetails response = new ResponseNewKardexDetails(200, "KardexDetails created successfully");
			log.debug("Objeto que responde el servicio: /kardexDetails (POST): "+response);
			responseNewKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado. No se pudo crear el nuevo KardexDetail en la base de datos");
			ResponseNewKardexDetails response = new ResponseNewKardexDetails(500, "KardexDetails could not be created");
			log.error("Objeto que responde el servicio: "+response);
			e.printStackTrace();
			responseNewKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}	
		log.debug("Saliendo del método newKardexDetails() de la clase controladora CKardexDetails");
		return responseNewKardexDetails;
	}

	/**
	 * Este método expone el endpoint que obtiene los kardexDetails por id.
	 * 
	 * @param KardexDetailsId es el id que se utiliza para buscar un KardexDetail en base de datos.
	 * @return responseGetByIdKardexDetails retorna si existe o no el KardexDetail en la base de datos.
	 **/
	@GetMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponseGetByIdKardexDetails> getByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		log.debug("Ingresando al método getByIdKardexDetails() de la clase controladora CKardexDetails"); 
		try {
			log.debug("Obteniendo el objeto KardexDetail de la base de datos con id #"+KardexDetailsId);
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			log.debug("Obteniendo el objeto KardexDetail obtenido"+kardexDetails);
			ResponseGetByIdKardexDetails response = new ResponseGetByIdKardexDetails(200, "KardexDetails found", kardexDetails);
			log.debug("Objero que response el servicio: "+response);
			responseGetByIdKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado. No se pudo obtener respuesta de la base de datos para el id #"+KardexDetailsId);
			ResponseGetByIdKardexDetails response = new ResponseGetByIdKardexDetails(500, "KardexDetails not found", null);
			log.error("Objeto que responde el servicio: "+response);
			log.error("Causa del error: ", e.getCause());
			responseGetByIdKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseGetByIdKardexDetails;
	}

	/**
	 * Este método expone el endpoint que permite actualizar los kardexDetails por id.
	 * 
	 * @param KardexDetailsId id que representa el kardexDetails a ser actualizado.
	 * @param kardexDetailsBody es el body con la nueva información de un kardex.
	 * @return responsePutKardexMain retorna si el KardexDetail pudo ser actualizado o no.
	 **/
	@PutMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponsePutKardexMain> updateKardexDetails(@PathVariable(value = "id") Long KardexDetailsId,
			@Valid @RequestBody MKardexDetails kardexDetailsBody) {
		log.debug("Ingresando al método updateKardexDetails() de la clase controladora CKardexDetails"); 
		try {
			log.debug("Actualizando el KardexDetail con el id #"+KardexDetailsId);
			log.debug("Información nueva: "+kardexDetailsBody);
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
			log.debug("Objeto que response el servicio: "+response);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado. El objeto KardexDetail con el id #"+KardexDetailsId+" no pudo ser actualizado");
			ResponsePutKardexMain response = new ResponsePutKardexMain(500, "KardexDetails could not be updated", KardexDetailsId);
			log.error("Objeto que responde el servicio: "+response);
			log.error("Causa del error: ", e.getCause());
			responsePutKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método updateKardexDetails() de la clase controladora CKardexDetails"); 
		return responsePutKardexMain;
	}

	/**
	 * Este método contiene el endpoint que permite eliminar un KardexDetail por Id.
	 * 
	 * @param KardexDetailsId id que representa el kardexDetails a ser eliminado.
	 * @return responseDeleteByIdKardexDetails retorna si se pudo o no eliminar el KardexDetail.
	 **/
	@DeleteMapping("/kardexDetails/{id}")
	public ResponseEntity<ResponseDeleteByIdKardexDetails> deleteByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		log.debug("Ingresando al método deleteByIdKardexDetails() de la clase controladora CKardexDetails"); 
		try {
			log.debug("Eliminando el objeto KardexDetail con id #"+KardexDetailsId);
			iKardexDetailsRepository.deleteById(KardexDetailsId);
			ResponseDeleteByIdKardexDetails response = new ResponseDeleteByIdKardexDetails(200, "KardexDetails deleted successfully", KardexDetailsId);
			log.debug("Objeto que response el servicio: +"+response);
			responseDeleteByIdKardexDetails = ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado. El objeto KardexDetail con el id #"+KardexDetailsId+" no pudo ser eliminado");
			ResponseDeleteByIdKardexDetails response = new ResponseDeleteByIdKardexDetails(500, "KardexDetails could not be deleted", null);
			log.error("Objeto que response el servicio: "+response);
			responseDeleteByIdKardexDetails = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método deleteByIdKardexDetails() de la clase controladora CKardexDetails");
		return responseDeleteByIdKardexDetails;
	}

}
