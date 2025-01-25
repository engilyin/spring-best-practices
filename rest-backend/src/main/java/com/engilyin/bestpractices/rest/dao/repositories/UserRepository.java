package com.engilyin.bestpractices.rest.dao.repositories;

import com.engilyin.bestpractices.rest.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
