package com.kardex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private ResponseEntity<Map<String, Object>> responseEntityObject;
	private ResponseEntity<Map<String, String>> responseEntityString;
	private Map<String, String> stringAndStringMap = new HashMap<>();
	private Map<String, Object> stringAndObjecMap = new HashMap<>();
	private Map<String, Object> allMap = new HashMap<>();

	/**
	 * Este método expone el endpoint que obtiene todos los kardexDetails existentes
	 * en bases de datos.
	 * 
	 * @return AllKardexMain retorna todos los KardexDetails main info mapeados.
	 **/
	@GetMapping("/kardexDetails")
	public ResponseEntity<Map<String, Object>> getAllKardexDetails() {
		
		try
		{
			log.debug("Ingresando al método getAllKardexDetails() de la clase (controller) CKardexDetails");
			log.debug("Obteniendo todos los KadexDetails de base de datos");
			List<MKardexDetails> AllKardexDetails = iKardexDetailsRepository.findAll();
			stringAndObjecMap.clear();
			stringAndStringMap.clear();
			allMap.clear();
			stringAndStringMap.put("HttpStatus", "200");
			stringAndStringMap.put("Message", "All KardexDetails have been found");
			stringAndObjecMap.put("kardexDetailsList", AllKardexDetails);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.OK).body(allMap);
			log.debug("Retornando datos KardexDetails obtenidos");
			log.debug("Mapa retornado: " + stringAndObjecMap);
		}
		catch (Exception e)
		{
			stringAndObjecMap.clear();
			stringAndStringMap.clear();
			allMap.clear();
			stringAndStringMap.put("HttpStatus", "500");
			stringAndStringMap.put("Message", "KardexDetails not found");
			stringAndObjecMap.put("kardexDetailsList", null);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(allMap);
		}
		log.debug("saliendo del método getAllKardexDetails() de la clase (controller) CKardexDetails");
		return responseEntityObject;
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
	public ResponseEntity<Map<String, String>> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail) {
		log.debug("Ingresando al método newKardexDetails() de la clase (controller) CKardexDetails");
		try {
			log.debug("Guardando en base de datos, los datos recibidos");
			log.debug("Datos recibidos por parámetros: " + KardexDetail);
			iKardexDetailsRepository.save(KardexDetail);
			log.debug("Datos guardados correctamente");
			log.debug("Armando respuesta del servidor");
			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "KardexDetails created successfully");
			stringAndStringMap.put("HttpStatus", "200");
			responseEntityString = ResponseEntity.status(HttpStatus.OK).body(stringAndStringMap);
			log.debug("Armando correctamente");
			log.debug("Mapa de respuesta: " + stringAndStringMap);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado. Falló la persistencia de los datos");
			log.error("Armando respuesta del servidor");
			stringAndStringMap.clear();
			stringAndStringMap.put("Message", "The Kardex couldn't be created");
			stringAndStringMap.put("HttpStatus", "500");
			log.error("Armado correctamente");
			log.error("Respuesta a retornar: "+stringAndStringMap);
			responseEntityString = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stringAndStringMap);
			log.error("saliendo del método newKardexDetails() de la clase (controller) CKardexDetails");
		}
		log.debug("saliendo del método newKardexDetails() de la clase (controller) CKardexDetails");	
		return responseEntityString;
	}

	/**
	 * Este método expone el endpoint que obtiene los kardexDetails por id.
	 * 
	 * @param KardexMain el objeto contiene los kardex details info mapeados.
	 * @return responseEntity responde OK (200) o Server Error(500).
	 **/
	@GetMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String,Object>> getByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
		log.debug("Ingresando al método getByIdKardexDetails() de la clase (controller) CKardexDetails");
		try {
			log.debug("Obteniendo información del KerdexDetail con id '" + KardexDetailsId + "'");
			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
			MKardexDetails kardexDetails = kardex.get();
			log.debug("Obteniendo objeto de respuesta de la base de datos");
			log.debug("Respuesta obtenida: " + kardexDetails);
			log.debug("Armando respuesta del servidor");
			stringAndStringMap.clear();
			stringAndObjecMap.clear();
			allMap.clear();
			stringAndStringMap.put("Message", "KardexDetails has been found");
			stringAndStringMap.put("HttpStatus", "200");
			stringAndObjecMap.put("Kardex", kardexDetails);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.OK).body(allMap);
			log.debug("Respuesta final: " + allMap);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado");
			log.error("Armando respuesta del servidor");
			log.error("Respuesta final del servidor: "+ allMap);
			stringAndStringMap.clear();
			stringAndObjecMap.clear();
			allMap.clear();
			stringAndStringMap.put("Message", "KardexDetails not found");
			stringAndStringMap.put("HttpStatus", "500");
			stringAndObjecMap.put("Kardex", null);
			allMap.putAll(stringAndStringMap);
			allMap.putAll(stringAndObjecMap);
			responseEntityObject = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(allMap);
			log.error("retornando respuesta del servidor");
			log.error("saliendo del método getByIdKardexDetails() de la clase (controller) CKardexDetails");
		}
		log.error("saliendo del método getByIdKardexDetails() de la clase (controller) CKardexDetails");
		log.error("Retornando un responseEntity");
		log.error("saliendo del método getByIdKardexDetails() de la clase (controller) CKardexDetails");
		return responseEntityObject;
	}

	/**
	 * Este método expone el endpoint para actualizar los kardexDetails por id.
	 * 
	 * @param KardexMainId   id que representa el kardexDetails a ser actualizado.
	 * @param kardexMainBody es el body con la información de un kardex.
	 * @return responseEntity response un OK (200) o un Server Error (500).
	 **/
	@PutMapping("/kardexDetails/{id}")
	public ResponseEntity<Map<String, String>> updateKardexDetails(@PathVariable(value = "id") Long KardexDetailsId,
			@Valid @RequestBody MKardexDetails kardexDetailsBody) {
		log.debug("Ingresando al método updateKardexDetails() de la clase (controller) CKardexDetails");
		try {
			log.debug("Obteniendo KardexDetails representado con el id'" + KardexDetailsId + "'");
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
			
			log.debug("Armando respuesta del servicio");
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "200");
			stringAndStringMap.put("Message", "KardexDetails updated successfully");
			stringAndStringMap.put("KardexId", KardexDetailsId.toString());
			responseEntityString = ResponseEntity.status(HttpStatus.OK).body(stringAndStringMap);			
			log.debug("Respuesta del servidor: "+stringAndStringMap);
		} catch (Exception e) {
			log.error("Ha ocurrido un error inesperado");
			log.error("Armando respuesta del servidor");
			stringAndStringMap.clear();
			stringAndStringMap.put("HttpStatus", "500");
			stringAndStringMap.put("Message", "KardexDetails could not be updated");
			stringAndStringMap.put("KardexId", KardexDetailsId.toString());
			log.error("Respuesta del servidor:" + stringAndStringMap);	
			log.error("Retornando respuesta");
			log.error("Saliendo del método updateKardexDetails() de la clase (controller) CKardexDetails");
			responseEntityString = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stringAndStringMap);
		}
		log.debug("Armando respuesta del servidor");
		log.debug("Respuesta: " + stringAndStringMap);
		log.debug("Saliendo del método updateKardexDetails() de la clase (controller) CKardexDetails");
		return responseEntityString;
	}

//	/**
//<<<<<<< HEAD
//	 * Este método contiene el endpoint que elimina un KardexDetail por Id.
//	 * 
//	 * @param KardexDetailsId id que representa el kardexDetails a ser eliminado.
//	 * @return responseEntity responde OK (200) o Server Error (500).
//	 **/
//	@DeleteMapping("/kardexDetails/{id}")
//	public ResponseEntity<?> deleteByIdKardexDetails(@PathVariable(value = "id") Long KardexDetailsId) {
//		log.debug("Ingresando al método deleteByIdKardexDetails() de la clase (Controller) CKardexDetails");
//		try {
//			log.debug("Eliminando KardexDetails con id '" + KardexDetailsId + "'");
//			iKardexDetailsRepository.deleteById(KardexDetailsId);
//			log.debug("KardexDetails con id '" + KardexDetailsId + "' eliminado exitosamente");
//			log.debug("Armando respuesta de servidor");
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails deleted successfully");
//			MapResponse.addGenericMap("KardexId deleted", KardexDetailsId.toString());
//			MapResponse.addGenericMap("HttpStatus", "200");
//			log.debug("Respuesta final del servidor: "
//					+ ResponseEntity.status(HttpStatus.OK).body(MapResponse.getGenericMap()));
//		} catch (Exception e) {
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails couldn't be deleted");
//			MapResponse.addGenericMap("KardexId requested", KardexDetailsId.toString());
//			MapResponse.addGenericMap("HttpStatus", "500");
//			ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(MapResponse.getGenericMap());
//			return responseEntity;
//
//		}
//		log.debug("Retornando respuesta");
//		log.debug("Saliendo del método deleteByIdKardexDetails() de la clase (controller) CKardexDetails");
//		ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(MapResponse.getGenericMap());
//		return responseEntity;
//	}

}
