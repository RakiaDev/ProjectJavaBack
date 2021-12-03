package com.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Repository.UserRepository;
import com.model.User;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		if(userRepository.count()>0) {
			return;
		}

		User user1 = new User("T1",UUID.randomUUID().toString(),"Test1", "o1", 24, "BR");

		User user2 = new User("T2",UUID.randomUUID().toString(),"Test2", "o1", 24, "Mexic");

		User user3 = new User("T3",UUID.randomUUID().toString(),"Test3", "o1", 24, "USA");

		User user4 = new User("T4",UUID.randomUUID().toString(),"Test4", "o1", 24, "Canada");
		
		User user5 = new User("T5",UUID.randomUUID().toString(),"Test5", "o1", 24, "BR");

		User user6 = new User("T6",UUID.randomUUID().toString(),"Test6", "o1", 24, "Mexic");

		User user7 = new User("T7",UUID.randomUUID().toString(),"Test7", "o1", 24, "USA");

		User user8 = new User("T8",UUID.randomUUID().toString(),"Test8", "o1", 24, "Canada");
		
		User user9 = new User("T9",UUID.randomUUID().toString(),"Test9", "o1", 24, "USA");

		User user10 = new User("T10",UUID.randomUUID().toString(),"Test10", "o1", 24, "Canada");
		
		
		List<User>userList=Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		
		userList=userList.stream().map(user->{user.setPassword(passwordEncoder.encode(user.getPassword()));
		return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(userList);

	}

}
