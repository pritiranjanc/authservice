package com.verlab.alexandria.core.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {
	
	private Integer id;
	
	private String name;

}
