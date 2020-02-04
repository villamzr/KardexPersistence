package com.kardex.controller;

import java.util.List;
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
import com.kardex.domain.ResponseAllKardexMain;
import com.kardex.domain.ResponseDeleteByIdKardexMain;
import com.kardex.domain.ResponseGetKardexMainById;
import com.kardex.domain.ResponsePutKardexMain;
import com.kardex.domain.ResponseSaveKardexMain;
import com.kardex.repository.IKardexMainRepository;

@RestController
public class CKardexMain
{
	@Autowired
	IKardexMainRepository iKardexMainRepository;
	private ResponseEntity<ResponseAllKardexMain> responseAllKardexMain;
	private ResponseEntity<ResponseSaveKardexMain> responseSaveKardexMain;
	private ResponseEntity<ResponseGetKardexMainById> responseGetKardexMainById;
	private ResponseEntity<ResponsePutKardexMain> responsePutKardexMain;
	private ResponseEntity<ResponseDeleteByIdKardexMain> responseDeleteByIdKardexMain;

	/**
	 * Este método expone el endpoint que obtiene todos los Kardex existentes en la base de datos.
	 * 
	 * @return AllKardexMain retorna todos los Kardex Main mappeados en base de datos.
	 **/
	@GetMapping("/kardex")
	public ResponseEntity<ResponseAllKardexMain> getAllKardexMain()
	{
		try
		{
			List<MKardexMain> AllKardexMain = iKardexMainRepository.findAll();
			ResponseAllKardexMain response = new ResponseAllKardexMain(200, "Kardex List found", AllKardexMain);
			responseAllKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponseAllKardexMain response = new ResponseAllKardexMain(500, "Kardex List not found", null);
			responseAllKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseAllKardexMain;
	}

	/**
	 * Este método expone el endpoint que crea nuevos kardex en base de datos.
	 * 
	 * @param KardexMain
	 *            Objeto que contiene la información mapeada necesaria para crear un nuevo Kardex.
	 * @return responseEntity response OK (200) o Server Error (500).
	 **/
	@PostMapping("/kardex")
	public ResponseEntity<ResponseSaveKardexMain> newKardexMain(@Valid @RequestBody MKardexMain KardexMain)
	{
		try
		{
			iKardexMainRepository.save(KardexMain);
			ResponseSaveKardexMain response = new ResponseSaveKardexMain(200, "Kardex created successfully");
			responseSaveKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponseSaveKardexMain response = new ResponseSaveKardexMain(500, "Kardex could not be created");
			responseSaveKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseSaveKardexMain;
	}

	/**
	 * Este método expone el endpoint que obtiene el id del Kardex a ser consultado.
	 * 
	 * @param KardexMain
	 *            object contain kardex main info mapped.
	 * @return responseEntity response OK or Server Error.
	 **/
	@GetMapping("/kardex/{id}")
	public ResponseEntity<ResponseGetKardexMainById> getByIdKardexMain(@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			Optional<MKardexMain> kardexMain = iKardexMainRepository.findById(KardexMainId);
			MKardexMain kardexMainResult = kardexMain.get();
			ResponseGetKardexMainById response = new ResponseGetKardexMainById(200, "Kardex found", kardexMainResult);
			responseGetKardexMainById = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponseGetKardexMainById response = new ResponseGetKardexMainById(500, "Kardex not found", null);
			responseGetKardexMainById = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseGetKardexMainById;
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
	public ResponseEntity<ResponsePutKardexMain> updateKardex(@PathVariable(value = "id") Long KardexMainId,
			@Valid @RequestBody MKardexMain kardexMainBody)
	{
		try
		{
			Optional<MKardexMain> kardex = iKardexMainRepository.findById(KardexMainId);
			MKardexMain getkardexMain = kardex.get();
			getkardexMain.setObject(kardexMainBody.getObject());
			getkardexMain.setSupplier(kardexMainBody.getSupplier());
			getkardexMain.setReference(kardexMainBody.getReference());
			getkardexMain.setUnit(kardexMainBody.getUnit());
			getkardexMain.setMin(kardexMainBody.getMin());
			getkardexMain.setLocation(kardexMainBody.getLocation());
			getkardexMain.setMax(kardexMainBody.getMax());
			iKardexMainRepository.save(getkardexMain);
			ResponsePutKardexMain response = new ResponsePutKardexMain(200, "KardexMain updated successfully",
					KardexMainId);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponsePutKardexMain response = new ResponsePutKardexMain(500, "KardexMain could not updated",
					KardexMainId);
			responsePutKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responsePutKardexMain;
	}

	/**
	 * Este método expone el endpoint para la eliminación del método por id.
	 *
	 * @param KardexMainId
	 *            id que contiene el kardex a ser eliminado.
	 * @return responseEntity que response ok (200) o error de servidor (500).
	 **/
	@DeleteMapping("/kardex/{id}")
	public ResponseEntity<ResponseDeleteByIdKardexMain> deleteByIdKardexMain(
			@PathVariable(value = "id") Long KardexMainId)
	{
		try
		{
			iKardexMainRepository.deleteById(KardexMainId);
			ResponseDeleteByIdKardexMain response = new ResponseDeleteByIdKardexMain(200,
					"Kardex deleted successfulluy", KardexMainId);
			responseDeleteByIdKardexMain = ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch (Exception e)
		{
			ResponseDeleteByIdKardexMain response = new ResponseDeleteByIdKardexMain(500, "Kardex could not be deleted",
					KardexMainId);
			responseDeleteByIdKardexMain = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return responseDeleteByIdKardexMain;
	}
}
