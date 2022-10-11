package com.verlab.alexandria.core.authentication.port.repository;

import java.util.List;

import com.verlab.alexandria.core.authentication.entity.Role;
import com.verlab.alexandria.core.authentication.entity.User;

public interface IUserRepository {
	
    User getUser(String username);
    
    List<Role> getRoles(Integer userId);
    
}