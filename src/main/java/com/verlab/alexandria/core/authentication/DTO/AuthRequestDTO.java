package com.verlab.alexandria.core.authentication.DTO;

import javax.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class AuthRequestDTO {

	@NotNull
    private String username;

	@NotNull
    private String password;
}
