package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUsername(String searchItem);

	List<User> findByFirstName(String searchItem);

	List<User> findByLastName(String searchItem);

	List<User> findByAge(Integer age);

	List<User> findByCountry(String searchItem);

}
