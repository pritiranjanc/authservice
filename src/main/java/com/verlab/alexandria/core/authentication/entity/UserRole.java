package com.verlab.alexandria.core.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRole {

	private Integer id;
	private Integer userId;
	private Integer roleId;
	
}
