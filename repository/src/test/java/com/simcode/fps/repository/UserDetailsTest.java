package com.simcode.fps.repository;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.simcode.fps.repository.model.Role;
import com.simcode.fps.repository.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LoginRepository userRepository;
	
	@Test
	public void findByUserName() {
		User user = new User("admin", "admin", "admin@gmail.com");

		Role role = new Role("ROLE_ADMIN");
		role.setUser(user);
		
		user.setRoles(Arrays.asList(role));
		entityManager.persist(user);
		
		User byUsername = userRepository.findByUsername(user.getUsername()).get();
		byUsername.getRoles().forEach(rl-> {
			System.out.println(rl.getRoleName());
		});
		assertEquals(byUsername.getUsername(), user.getUsername());
	}
}
