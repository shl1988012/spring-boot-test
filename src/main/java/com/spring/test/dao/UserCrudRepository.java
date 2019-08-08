package com.spring.test.dao;

import com.spring.test.model.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserCrudRepository extends CrudRepository<User, Long> {

    List<User> getUserByAge(int age);
}
