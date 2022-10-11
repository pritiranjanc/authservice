package com.verlab.alexandria.core.authentication.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verlab.alexandria.controller.AuthenticationController;
import com.verlab.alexandria.core.authentication.entity.Role;
import com.verlab.alexandria.core.authentication.entity.User;
import com.verlab.alexandria.core.authentication.port.repository.IUserRepository;
import com.verlab.alexandria.core.authentication.port.service.IUserService;

@Service
public class UserService implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    private final IUserRepository userRepo;

    @Autowired
    public UserService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUser(String username) {
        LOG.info("In UserService");
        return this.userRepo.getUser(username);
    }

	@Override
	public List<Role> getRoles(Integer userId) {
		 return this.userRepo.getRoles(userId);
	}
    
    
}
