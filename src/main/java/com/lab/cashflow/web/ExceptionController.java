package com.lab.cashflow.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.cashflow.dto.AjaxErrorResponseDTO;
import com.lab.cashflow.exception.NoBudgetException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = Logger.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value={NoBudgetException.class})
	public ResponseEntity<AjaxErrorResponseDTO> handleOrdinaryException(Exception e){
		
		logger.info("Handling the exception: " + e.getMessage());
		
		AjaxErrorResponseDTO response = new AjaxErrorResponseDTO(e.getMessage());
		
		return new ResponseEntity<AjaxErrorResponseDTO>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
