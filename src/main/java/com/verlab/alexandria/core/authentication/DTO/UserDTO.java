package com.verlab.alexandria.core.authentication.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
@RequiredArgsConstructor
public class UserDTO {
	
    @NotNull
    private int id;

    @Email
    private String email;
}
