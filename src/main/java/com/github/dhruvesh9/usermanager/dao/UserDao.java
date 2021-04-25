package com.github.dhruvesh9.usermanager.dao;


import com.github.dhruvesh9.usermanager.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends MongoRepository<User, Object>{}
