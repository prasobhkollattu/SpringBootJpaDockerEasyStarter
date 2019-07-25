package com.opensourzesupport.bootapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.opensourzesupport.bootapp.dao.UserRepository;
import com.opensourzesupport.bootapp.dao.entity.UserRecord;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringServiceApplicationTests {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void createUser() {
		// given
		UserRecord prasobh = new UserRecord();
		prasobh.setName("Prasobh Kollattu");
		prasobh.setEmail("prasobh.kollattu@outlook.com");
		entityManager.persist(prasobh);
		entityManager.flush();

		
		UserRecord sachin = new UserRecord();
		sachin.setName("Sachin Tendulkar");
		sachin.setEmail("prasobh.kollattu@outlook.com");
		entityManager.persist(sachin);
		entityManager.flush();
		
		
		// when
		UserRecord found = userRepository.findByName(prasobh.getName());
		// then
		assertThat(found.getName()).isEqualTo(prasobh.getName());
		List<UserRecord> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		
		assertThat(users.size()).isEqualTo(2);
	}

}
