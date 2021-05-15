package com.ctseducare.oauth2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctseducare.oauth2.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByLogin(String login);

}
