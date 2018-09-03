package com.simcode.fps.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simcode.fps.repository.model.User;

@Repository
public interface LoginRepository extends CrudRepository<User, Long>{

	Optional<User> findByUsername(String username);

	User findByEmail(String email);
}
