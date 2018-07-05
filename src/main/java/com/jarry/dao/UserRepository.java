package com.jarry.dao;

import com.jarry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jarry on 2018/6/14.
 */

public interface UserRepository extends JpaRepository<User, Integer>{

    public User findUserByAge(Integer age);
}
