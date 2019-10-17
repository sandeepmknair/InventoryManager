package com.inventorymanagement.repository;

import com.inventorymanagement.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}