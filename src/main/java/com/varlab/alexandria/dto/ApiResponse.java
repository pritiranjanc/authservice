package com.varlab.alexandria.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel
public class ApiResponse<T> {

	private Integer status;
	private String message;
	private T data;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm")
	private LocalDateTime timestamp;
	private List<ApiValidationError> validatonErrors;

	public ApiResponse() {
		this.status = HttpStatus.OK.value();
		this.message = HttpStatus.OK.getReasonPhrase();
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiResponse(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiResponse(HttpStatus status, String message , T data) {
		this.status = status.value();
		this.message = message;
		this.data = data ;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(HttpStatus status, List<ApiValidationError> errors) {
		this.status = status.value();
		this.message = status.getReasonPhrase();
		this.validatonErrors = errors;
		this.timestamp = LocalDateTime.now();
	}


}
