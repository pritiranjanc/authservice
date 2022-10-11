package com.verlab.alexandria.core.authentication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.verlab.alexandria.core.authentication.entity.User;
import com.verlab.alexandria.core.authentication.port.repository.IUserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private IUserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User user;

	@BeforeEach
	public void setup(){
		user = new User(1,"Ram","");
	}

	@Test
	public void testGetUser() {
		Mockito.when(userRepository.getUser(Mockito.anyString())).thenReturn(user);
		User user = userService.getUser("Ram");
		assertEquals(user.getUsername(), "Ram");
	}
}
