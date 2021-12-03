package com.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Repository.UserRepository;
import com.model.User;
import com.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	public  List<User> userList=new ArrayList<>();
	/*private static Long COUNTER=1l;*/
	
	
	/***********************************Avec BD*************************************************************/
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<User> findAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
		//sans BD
		//return userList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
	}

	@Override
	public Optional<User> findById(Long id) {
		
		//return  userList.stream().filter(user->user.getId()== id).findFirst();

		/*Optional<User>userOpt= userList.stream().filter(user->user.getId()==id).findFirst();
		if(userOpt.isPresent()) {
			return userOpt;
		}
		return Optional.empty();*/
		
		return userRepository.findById(id);
	}

	@Override
	public void add(User user) {

		/*user.setId(COUNTER++);
		userList.add(user);*/
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}
	
	
	
	@Override
	public Optional<User> update(User user) {
		
		/*Optional<User>userOpt= userList.stream().filter(u->u.getId() == user.getId()).findFirst();
		if(userOpt.isPresent()) {
			User existingUser =userOpt.get();
			
			if(user.getFirstName()!=null) {
				existingUser.setFirstName(user.getFirstName());
			}
			
			if(user.getLastName()!=null) {
				existingUser.setLastName(user.getLastName());
			}
			
			if(user.getCountry()!=null) {
				existingUser.setCountry(user.getCountry());
			}
			
			if(user.getAge()!=null) {
				existingUser.setAge(user.getAge());
			}
			
			
			userList=userList.stream().filter(u->u.getId() != existingUser.getId()).collect(Collectors.toList());
			userList.add(existingUser);
			
			return Optional.of(existingUser);
		}

		
		return Optional.empty();*/
		
		
		/******************avec BD****************************/
		Optional<User>userOpt=userRepository.findById(user.getId());
		
		if(userOpt.isPresent()) {
 
			User existingUser =userOpt.get();
			
			if(user.getUsername()!=null) {
				existingUser.setUsername(user.getFirstName());
			}
			
			if(user.getPassword()!=null) {
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			
			if(user.getFirstName()!=null) {
				existingUser.setFirstName(user.getFirstName());
			}
			
			if(user.getLastName()!=null) {
				existingUser.setLastName(user.getLastName());
			}
			
			if(user.getCountry()!=null) {
				existingUser.setCountry(user.getCountry());
			}
			
			if(user.getAge()!=null) {
				existingUser.setAge(user.getAge());
			}
			
			//userRepository.save(userOpt.get());
			//return userOpt;
			
			userRepository.save(existingUser);
			return Optional.of(existingUser);

		}
		return Optional.empty();
			
			
	}
	
	
	

	@Override
	public Optional<User> delete(Long id) {

		/*Optional<User>userOpt= userList.stream().filter(user->user.getId()==id).findFirst();
		if(userOpt.isPresent()) {
			userList=userList.stream().filter(user->userOpt.get().getId() != user.getId()).collect(Collectors.toList());
			return userOpt;
		}
		return Optional.empty();*/
		
		Optional<User>userOpt=userRepository.findById(id);
		
		if(userOpt.isPresent()) {
			userRepository.delete(userOpt.get());
			return userOpt;
		}
		return Optional.empty();
			
			}

	@Override
	public List<User> findByCriteria(String criteria, String searchItem) {

		switch (criteria) {
		case "username":
			return this.userRepository.findByUsername(searchItem);
			
			
		case "firstName":
			return this.userRepository.findByFirstName(searchItem);
		case "lastName":
			return this.userRepository.findByLastName(searchItem);
		case "age":
			try {
				Integer age = Integer.valueOf(searchItem);
				return this.userRepository.findByAge(age);
			} catch (NumberFormatException e) {
				System.out.println("Could not convert age to number...");
			}
			return new ArrayList<>();
		case "country":
			return this.userRepository.findByCountry(searchItem);
		}
		return new ArrayList<>();
	}

	
	
	



}
