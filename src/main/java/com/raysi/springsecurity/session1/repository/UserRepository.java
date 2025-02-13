package com.raysi.springsecurity.session1.repository;

import com.raysi.springsecurity.session1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String username);
}
