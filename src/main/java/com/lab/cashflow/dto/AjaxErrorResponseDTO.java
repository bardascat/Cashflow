package com.lab.cashflow.dto;

public class AjaxErrorResponseDTO {

	private String message;
	
	public AjaxErrorResponseDTO(String message){
		
		this.message=message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AjaxErrorResponseDTO [message=" + message + "]";
	}
	
	
	
}
