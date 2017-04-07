package com.bemInternet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bemInternet.domain.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByStudentld(String studentld);
}
