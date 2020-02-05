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

import com.kardex.domain.MKardexMain;
import com.kardex.domain.ResponseAllKardexMain;
import com.kardex.domain.ResponseDeleteByIdKardexMain;
import com.kardex.domain.ResponseGetKardexMainById;
import com.kardex.domain.ResponsePutKardexMain;
import com.kardex.domain.ResponseSaveKardexMain;
import com.kardex.repository.IKardexMainRepository;

@RestController
public class CKardexMain
{
	private static final Logger log = LoggerFactory.getLogger(CKardexMain.class);
	@Autowired
	IKardexMainRepository iKardexMainRepository;
	private ResponseEntity<ResponseAllKardexMain> responseAllKardexMain;
	private ResponseEntity<ResponseSaveKardexMain> responseSaveKardexMain;
	private ResponseEntity<ResponseGetKardexMainById> responseGetKardexMainById;
	private ResponseEntity<ResponsePutKardexMain> responsePutKardexMain;
	private ResponseEntity<ResponseDeleteByIdKardexMain> responseDeleteByIdKardexMain;

	/**
	 * Este método expone el endpoint que obtiene todos los KardexMain existentes en la base de datos.
	 * 
	 * @return responseAllKardexMain retorna un objeto de tipo responseAllKardexMain que contiene todos los KardexMain mappeados en base de datos.
	 **/
	@GetMapping("/kardex")
	public ResponseEntity<ResponseAllKardexMain> getAllKardexMain()
	{
		log.debug("Ingresando al método getAllKardexMain() de la clase controladora CKardexMain");
		try
		{
			log.debug("Obteniendo la lista de objetos MKardexMain almacenadas en base de datos");
			List<MKardexMain> AllKardexMain = iKardexMainRepository.findAll();
			log.debug("MKardexMain existentes en base de datos: "+AllKardexMain);
			ResponseAllKardexMain response = new ResponseAllKardexMain(200, "Kardex List found", AllKardexMain);
			log.debug("Objeto que response el servicio: "+response);
			responseAllKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo OBTENER respuesta de la base de datos");
			ResponseAllKardexMain response = new ResponseAllKardexMain(500, "Kardex List not found", null);
			log.error("Objeto que responde el servicio: /kardex (GET): "+response);
			responseAllKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método getAllKardexMain() de la clase controladora CKardexMain");
		return responseAllKardexMain;
	}

	/**
	 * Este método expone el endpoint que crea nuevos kardex en base de datos.
	 * 
	 * @param KardexMain Objeto que contiene el body del nuevo KardexMain.
	 * @return responseSaveKardexMain retorna si se pudo o no crear el nuevo KardexMain.
	 **/
	@PostMapping("/kardex")
	public ResponseEntity<ResponseSaveKardexMain> newKardexMain(@Valid @RequestBody MKardexMain KardexMain)
	{
		log.debug("Ingresando al método newKardexMain() de la clase controladora CKardexMain");
		try
		{
			log.debug("Actualizando el KardexMain con id #"+KardexMain);
			iKardexMainRepository.save(KardexMain);
			ResponseSaveKardexMain response = new ResponseSaveKardexMain(200, "Kardex created successfully");
			log.debug("Objeto que responde el servicio: "+response);
			responseSaveKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo CREAR el nuevo KardexMain en la base de datos");
			ResponseSaveKardexMain response = new ResponseSaveKardexMain(500, "Kardex could not be created");
			log.error("Objeto que response el servicio: "+response);
			responseSaveKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método newKardexMain() de la clase controladora CKardexMain");
		return responseSaveKardexMain;
	}

	/**
	 * Este método expone el endpoint que obtiene el id del KardexMain a ser consultado.
	 * 
	 * @param KardexMainId contiene el id del KardexMain a ser consultado en base de datos. 
	 * @return responseGetKardexMainById retorna si se pudo o no encontrar el KardexMain consultado.
	 **/
	@GetMapping("/kardex/{id}")
	public ResponseEntity<ResponseGetKardexMainById> getByIdKardexMain(@PathVariable(value = "id") Long KardexMainId)
	{
		log.debug("Ingresando al método getByIdKardexMain() de la clase controladora CKardexMain");
		try
		{
			log.debug("Obteniendo el KardexMain con id #"+KardexMainId);
			Optional<MKardexMain> kardexMain = iKardexMainRepository.findById(KardexMainId);
			MKardexMain kardexMainResult = kardexMain.get();
			log.debug("KardexMain obtenido"+kardexMainResult);
			ResponseGetKardexMainById response = new ResponseGetKardexMainById(200, "Kardex found", kardexMainResult);
			log.debug("Objeto que response el servicio: "+response);
			responseGetKardexMainById = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo OBTENER el KardexMin con id #"+KardexMainId);
			ResponseGetKardexMainById response = new ResponseGetKardexMainById(500, "Kardex not found", null);
			log.error("Objeto que response el servicio: "+response);
			responseGetKardexMainById = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método getByIdKardexMain() de la clase controladora CKardexMain");
		return responseGetKardexMainById;
	}

	/**
	 * Este método expone el endpoint que actualiza un KardexMain, según sea el id solicitado.
	 * 
	 * @param KardexMainId Id que contiene el KardexMain a ser actualizado.
	 * @param kardexMainBody Contiene el body con la nueva información del KardexMain.
	 * @return responsePutKardexMain retorna si se pudo o no actualizar el KardexMain.
	 **/
	@PutMapping("/kardex/{id}")
	public ResponseEntity<ResponsePutKardexMain> updateKardex(@PathVariable(value = "id") Long KardexMainId,
			@Valid @RequestBody MKardexMain kardexMainBody)
	{
		log.debug("Ingresando al método updateKardex() de la clase controladora CKardexMain");
		try
		{
			log.debug("Obteniendo el KardexMain con id #"+KardexMainId);
			Optional<MKardexMain> kardex = iKardexMainRepository.findById(KardexMainId);
			MKardexMain getkardexMain = kardex.get();
			log.debug("KardexMain obtenido "+getkardexMain);
			getkardexMain.setObject(kardexMainBody.getObject());
			getkardexMain.setSupplier(kardexMainBody.getSupplier());
			getkardexMain.setReference(kardexMainBody.getReference());
			getkardexMain.setUnit(kardexMainBody.getUnit());
			getkardexMain.setMin(kardexMainBody.getMin());
			getkardexMain.setLocation(kardexMainBody.getLocation());
			getkardexMain.setMax(kardexMainBody.getMax());
			log.debug("Actualizando el KardexMain con id #"+KardexMainId);
			iKardexMainRepository.save(getkardexMain);
			ResponsePutKardexMain response = new ResponsePutKardexMain(200, "KardexMain updated successfully", KardexMainId);
			log.debug("Objeto que response el servicio "+response);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo ACTUALIZAR el KardexMain con id #"+KardexMainId);
			ResponsePutKardexMain response = new ResponsePutKardexMain(500, "KardexMain could not be updated", KardexMainId);
			log.error("Objeto que response el servicio: "+response);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Saliendo del método updateKardex() de la clase controladora CKardexMain");
		return responsePutKardexMain;
	}

	/**
	 * Este método expone el endpoint que permite la eliminación del KardexMain por id.
	 *
	 * @param KardexMainId id que contiene el KardexMain a ser eliminado.
	 * @return responseDeleteByIdKardexMain que reretorna si el KardexMain pudo o no ser eliminado.
	 **/
	@DeleteMapping("/kardex/{id}")
	public ResponseEntity<ResponseDeleteByIdKardexMain> deleteByIdKardexMain(
			@PathVariable(value = "id") Long KardexMainId)
	{
		log.debug("Ingresando al método deleteByIdKardexMain() de la clase controladora CKardexMain");
		try
		{
			log.debug("Eliminando KardexMain con id #"+KardexMainId);
			iKardexMainRepository.deleteById(KardexMainId);
			ResponseDeleteByIdKardexMain response = new ResponseDeleteByIdKardexMain(200, "Kardex deleted successfully", KardexMainId);
			log.debug("Objeto que response el servicio: "+response);
			responseDeleteByIdKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			log.error("Ha ocurrido un error inesperado. No se pudo ELIMINAR el KardexMain con id #"+KardexMainId);
			ResponseDeleteByIdKardexMain response = new ResponseDeleteByIdKardexMain(500, "Kardex could not be deleted", KardexMainId);
			log.error("Objeto que response el servicio: "+response);
			responseDeleteByIdKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		log.debug("Ingresando al método deleteByIdKardexMain() de la clase controladora CKardexMain");
		return responseDeleteByIdKardexMain;
	}
}
