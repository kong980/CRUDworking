package com.carrot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrot.entity.users.User;
import com.carrot.model.UserDto;
import com.carrot.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final SecurityFilterChain filterChain;
	
	@Autowired
	private UserService userService;

    UserController(SecurityFilterChain filterChain) {
        this.filterChain = filterChain;
    }
	
// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody UserDto userDto){
		userService.join(userDto);

		return ResponseEntity.ok().build();
	}
	
// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto){
		Optional<User> user = userService.login(userDto);
		
		if(!user.isPresent()) {	// 정보가 없을 경우
			return ResponseEntity.badRequest().body("다시 확인하세요");
		}
		
		return ResponseEntity.ok().build();
	}

}
