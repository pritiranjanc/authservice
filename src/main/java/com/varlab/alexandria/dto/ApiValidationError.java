package com.varlab.alexandria.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel
public class ApiValidationError  {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError (String field, Object rejectedValue, String message) {
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
	
}
