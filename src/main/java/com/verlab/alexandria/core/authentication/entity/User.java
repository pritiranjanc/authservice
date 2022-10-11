package com.verlab.alexandria.core.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	
    private Integer id;
    
    private String username;
    
    private String password;
    
}
