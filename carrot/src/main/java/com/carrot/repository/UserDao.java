package com.carrot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrot.entity.users.User;
import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	//로그인
	Optional<User> findById(String id);
	Optional<User> findByIdAndPw(String id, String pw);
}
