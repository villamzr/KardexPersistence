package com.kardex.controller;

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
//	private static final Logger log = LoggerFactory.getLogger(CKardexDetails.class);
//	@Autowired
//	IKardexDetailsRepository iKardexDetailsRepository;
//
//	/**
//	 * Este método expone el endpoint que obtiene todos los kardexDetails existentes
//	 * en bases de datos.
//	 * 
//	 * @return AllKardexMain retorna todos los KardexDetails main info mapeados.
//	 **/
//	@GetMapping("/kardexDetails")
//	public List<MKardexDetails> getAllKardexMain() {
//		log.debug("Ingresando al método getAllKardexMain() de la clase (controller) CKardexDetails");
//		log.debug("Obteniendo todos los KadexDetails de base de datos");
//		List<MKardexDetails> AllKardexDetails = iKardexDetailsRepository.findAll();
//		log.debug("Retornando datos KardexDetails obtenidos");
//		log.debug("Objetos retornados: " + AllKardexDetails);
//		log.debug("saliendo del método getAllKardexMain() de la clase (controller) CKardexDetails");
//		return AllKardexDetails;
//	}
//
//	/**
//	 * Este método expone el endpoint que crea nuevos KardexDetails en bases de
//	 * datos.
//	 * 
//	 * @param KardexMain
//	 *            Objeto que contiene el body de un KardexDetail.
//	 * @return responseEntity responde OK o Server Error.
//	 **/
//	@PostMapping("/kardexDetails")
//	public ResponseEntity<Map<String, String>> newKardexDetails(@Valid @RequestBody MKardexDetails KardexDetail) {
//		log.debug("Ingresando al método newKardexDetails() de la clase (controller) CKardexDetails");
//		try {
//			log.debug("Guardando en base de datos, los datos recibidos");
//			log.debug("Datos recibidos por parámetros: " + KardexDetail);
//			iKardexDetailsRepository.save(KardexDetail);
//			log.debug("Datos guardados correctamente");
//			log.debug("Armando respuesta del servidor");
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails created successfully");
//			MapResponse.addGenericMap("HttpStatus", "200");
//			log.debug("Armando correctamente");
//			log.debug("Respuesta del servidor: " + ResponseEntity.ok().body(MapResponse.getGenericMap()));
//		} catch (Exception e) {
//			log.error("Ha ocurrido un error inesperado. Falló la persistencia de los datos");
//			log.error("Armando respuesta del servidor");
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "The Kardex couldn't be created");
//			MapResponse.addGenericMap("HttpStatus", "500");
//			log.error("Armado correctamente");
//			log.error("Respuesta a retornar: "
//					+ ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MapResponse.getGenericMap()));
//			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(MapResponse.getGenericMap());
//			log.error("saliendo del método newKardexDetails() de la clase (controller) CKardexDetails");
//			return responseEntity;
//		}
//		log.debug("saliendo del método newKardexDetails() de la clase (controller) CKardexDetails");
//		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
//		return responseEntity;
//	}
//
//	/**
//<<<<<<< HEAD
//	 * Este método expone el endpoint que obtiene los kardexDetails por id.
//	 * 
//	 * @param KardexMain el objeto contiene los kardex details info mapeados.
//	 * @return responseEntity responde OK (200) o Server Error(500).
//	 **/
//	@GetMapping("/kardexDetails/{id}")
//	public ResponseEntity<Map<String, String>> getByIdKardexMain(@PathVariable(value = "id") Long KardexDetailsId) {
//		log.debug("Ingresando al método getByIdKardexMain() de la clase (controller) CKardexDetails");
//		try {
//			log.debug("Obteniendo información del KerdexDetail con id '" + KardexDetailsId + "'");
//			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
//			MKardexDetails kardexDetails = kardex.get();
//			log.debug("Obteniendo objeto de respuesta de la base de datos");
//			log.debug("Respuesta obtenida: " + kardexDetails);
//			log.debug("Armando respuesta del servidor");
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails found");
//			MapResponse.addGenericMap("HttpStatus", "200");
//			MapResponse.addGenericMap("Kardex", kardexDetails.getDescription());
//			log.debug("Respuesta final: " + ResponseEntity.ok().body(MapResponse.getGenericMap()));
//		} catch (Exception e) {
//			log.error("Ha ocurrido un error inesperado");
//			log.error("Armando respuesta del servidor");
//			log.error("Respuesta final del servidor: "
//					+ ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MapResponse.getGenericMap()));
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails not found");
//			MapResponse.addGenericMap("KardexDetailsId requested", KardexDetailsId.toString());
//			MapResponse.addGenericMap("HttpStatus", "500");
//			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(MapResponse.getGenericMap());
//			log.error("retornando respuesta del servidor");
//			log.error("saliendo del método getByIdKardexMain() de la clase (controller) CKardexDetails");
//			return responseEntity;
//		}
//		log.error("saliendo del método getByIdKardexMain() de la clase (controller) CKardexDetails");
//		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
//		log.error("Retornando un responseEntity");
//		log.error("saliendo del método getByIdKardexMain() de la clase (controller) CKardexDetails");
//		return responseEntity;
//	}
//
//	/**
//<<<<<<< HEAD
//	 * Este método expone el endpoint para actualizar los kardexDetails por id.
//	 * 
//	 * @param KardexMainId   id que representa el kardexDetails a ser actualizado.
//	 * @param kardexMainBody es el body con la información de un kardex.
//	 * @return responseEntity response un OK (200) o un Server Error (500).
//	 **/
//	@PutMapping("/kardexDetails/{id}")
//	public ResponseEntity<Map<String, String>> updateKardex(@PathVariable(value = "id") Long KardexDetailsId,
//			@Valid @RequestBody MKardexDetails kardexDetailsBody) {
//		log.debug("Ingresando al método updateKardex() de la clase (controller) CKardexDetails");
//		try {
//			log.debug("Obteniendo KardexDetails representado con el id'" + KardexDetailsId + "'");
//			Optional<MKardexDetails> kardex = iKardexDetailsRepository.findById(KardexDetailsId);
//			log.debug("KardexDetails obtenido: " + kardex);
//			log.debug("KardexDetails obtenido: ");
//			MKardexDetails kardexDetails = kardex.get();
//			log.debug("Setteando los datos del KardexDetails obtenido");
//			log.debug("Setteando fecha");
//			kardexDetails.setDate(kardexDetailsBody.getDate());
//			log.debug("fecha setteada");
//			log.debug("Setteando Descripcion");
//			kardexDetails.setDescription(kardexDetailsBody.getDescription());
//			log.debug("Descripcion setteada");
//			log.debug("Setteando Valor Unitario");
//			kardexDetails.setUnitValue(kardexDetailsBody.getUnitValue());
//			log.debug("Valor Unitario seteado");
//			log.debug("Setteando Cantidad (Entrada)");
//			kardexDetails.setInputQuantity(kardexDetailsBody.getInputQuantity());
//			log.debug("Cantidad (Entrada) Setteado");
//			log.debug("Setteando (Entrada) Valor de entrada");
//			kardexDetails.setInputValue(kardexDetailsBody.getInputValue());
//			log.debug("(Entrada) Valor de entrada Setteado");
//			log.debug("Setteando Cantidad (Salida)");
//			kardexDetails.setOutputQuantity(kardexDetailsBody.getOutputQuantity());
//			log.debug("Cantidad (Salida) Setteado");
//			log.debug("Setteando (Salida) Valor de salida");
//			kardexDetails.setOutputValue(kardexDetailsBody.getOutputValue());
//			log.debug("Valor de salida setteado");
//			log.debug("Setteando Cantidad Balance");
//			kardexDetails.setBalanceQuantity(kardexDetailsBody.getBalanceQuantity());
//			log.debug("Cantidad Balance setteado");
//			log.debug("Setteando Valor de balanza");
//			kardexDetails.setBalanceValue(kardexDetailsBody.getBalanceValue());
//			log.debug("Valor de balanza Setteado");
//			log.debug("Guardando información anterior en base de datos para actualización de objeto");
//			iKardexDetailsRepository.save(kardexDetails);
//			log.debug("Guardado exitosamente");
//			log.debug("Armando respuesta del servidor");
//			MapResponse.clearGenericMap();
//			MapResponse.addGenericMap("Message", "KardexDetails updated successfully");
//			MapResponse.addGenericMap("HttpStatus", "200");
//			MapResponse.addGenericMap("KardexId updated", KardexDetailsId.toString());
//			log.debug("Respuesta del servidor: " + ResponseEntity.ok().body(MapResponse.getGenericMap()));
//		} catch (Exception e) {
//			log.error("Ha ocurrido un error inesperado");
//			log.error("Armando respuesta del servidor");
//			MapResponse.clearGenericMap();
//			log.error("Respuesta del servidor:"
//					+ ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MapResponse.getGenericMap()));
//			MapResponse.addGenericMap("Message", "KardexDetails not found");
//			MapResponse.addGenericMap("KardexId requested", KardexDetailsId.toString());
//			MapResponse.addGenericMap("HttpStatus", "500");
//			ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(MapResponse.getGenericMap());
//			e.printStackTrace();
//			log.error("Retornando respuesta");
//			log.error("Saliendo del método updateKardex() de la clase (controller) CKardexDetails");
//			return responseEntity;
//		}
//		log.debug("Armando respuesta del servidor");
//		log.debug("Respuesta: " + ResponseEntity.ok().body(MapResponse.getGenericMap()));
//		log.debug("Saliendo del método updateKardex() de la clase (controller) CKardexDetails");
//		ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok().body(MapResponse.getGenericMap());
//		return responseEntity;
//	}
//
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
