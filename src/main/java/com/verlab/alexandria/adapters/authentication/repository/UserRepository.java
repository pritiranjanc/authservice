package com.verlab.alexandria.adapters.authentication.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.verlab.alexandria.adapters.BaseAdapter;
import com.verlab.alexandria.core.authentication.entity.Role;
import com.verlab.alexandria.core.authentication.entity.User;
import com.verlab.alexandria.core.authentication.entity.UserRole;
import com.verlab.alexandria.core.authentication.port.repository.IUserRepository;

@Repository
public class UserRepository extends BaseAdapter implements IUserRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    @Override
    public User getUser(String username) {
    	try {
    		User user = jdbc.queryForObject("SELECT * FROM public.\"users\" WHERE username=?",
    				BeanPropertyRowMapper.newInstance(User.class), username);
    		LOGGER.info("Retrived User Info {}",user.toString());
    		return user;
    	} catch (IncorrectResultSizeDataAccessException e) {
    		return null;
    	}
    }

	@Override
	public List<Role> getRoles(Integer userId) {
		try {
			List<Role> role = jdbc.queryForList("SELECT * FROM public.\"userRole\" WHERE userId=?",
					BeanPropertyRowMapper.newInstance(UserRole.class), userId);
			LOGGER.info("Retrived User Info {}",userId);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
    
}
