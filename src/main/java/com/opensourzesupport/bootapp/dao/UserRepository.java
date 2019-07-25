package com.opensourzesupport.bootapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opensourzesupport.bootapp.dao.entity.UserRecord;

@Repository
public interface UserRepository extends CrudRepository<UserRecord, Integer> {
	public UserRecord findByName(String name);
}
