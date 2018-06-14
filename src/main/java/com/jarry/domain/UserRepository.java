package com.jarry.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jarry on 2018/6/14.
 */

public interface UserRepository extends JpaRepository<User, Integer>{

    public User findUserByAge(Integer age);
}
