package com.verlab.alexandria.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varlab.alexandria.dto.ApiResponse;
import com.verlab.alexandria.core.authentication.DTO.AuthRequestDTO;
import com.verlab.alexandria.security.services.JwtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Authentication Services")
public class AuthenticationController {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	  
	  @Autowired
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  @Autowired 
	  private JwtService jwtService;
	  
	  @Autowired 
	  public AuthenticationController(AuthenticationManager authenticationManager) { 
		  this.authenticationManager = authenticationManager; 
	  }
	  
	  @PostMapping("/login") 
	  @ApiOperation(value = "Api to generate authenticate token") 
	  public ResponseEntity<ApiResponse<String>> authenticate(@Valid @RequestBody AuthRequestDTO request) { 
		  ResponseEntity<ApiResponse<String>> responseE = null;
		  ApiResponse<String> response = null;
		  LOGGER.info(" .... Authenticate Controller ..");
		  try {
			  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
			  String token = jwtService.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
			  response = new ApiResponse<>(HttpStatus.OK,"Token Generated Successfully",token);
		  } catch (AuthenticationException e) {
			  response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"Invalid username/password supplied",null);
		  }
		  responseE = new ResponseEntity<>(response,HttpStatus.OK);
		  return responseE;
	  }
	 
    
}
