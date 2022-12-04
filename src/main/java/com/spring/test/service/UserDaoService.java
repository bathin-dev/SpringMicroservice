package com.spring.test.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.spring.test.entity.User;

@Component
public class UserDaoService {
	//JPA/Hibernate > Database
	//

	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	
	static {
		users.add(new User(++userCount,"ThinNB",LocalDate.now().minusYears(35)));
		users.add(new User(++userCount,"DienLV",LocalDate.now().minusYears(37)));
		users.add(new User(++userCount,"HungBV",LocalDate.now().minusYears(36)));
	}
	
	
	//GET /users
	public List<User> findAll(){
		return users;
	}

	//GET /user/{id}
	public User findOne(int id) {
		Predicate<? super User> predicate = item -> item.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User saveUser(User user) {
		user.setId(++userCount);
		this.users.add(user);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = item -> item.getId().equals(id);
//		User usr = users.stream().filter(predicate).findFirst().orElse(null);
		users.removeIf(predicate);
	}
	
	
	
	
	
	
	
}
