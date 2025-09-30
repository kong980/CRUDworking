package com.carrot.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.carrot.entity.users.User;
import com.carrot.entity.users.UserRole;
import com.carrot.model.UserDto;
import com.carrot.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	
	public UserService(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

// 회원가입
	public void join(UserDto userDto) {
		User user = new User();
		
		user.setId(userDto.getId());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setRatingScore(0); // 처음 가입 했을 때는 별점이 0점
		
		
		// 비밀번호
		String encodedPw = passwordEncoder.encode(userDto.getPw()); // 회원이 입력한 비밀번호를 암호화
		user.setPw(encodedPw);
		
		userDao.save(user);
		
	}
	
// 로그인
	public Optional<User> login(UserDto userDto) {
		String id = userDto.getId();
		String pw = userDto.getPw();
		
		Optional<User> user = userDao.findById(id);
		
		if(user.isPresent()) {
			String encodedPw = user.get().getPw();	// findById(id)로 찾은 user 정보에서의 비밀번호를 가져와서 암호화
			boolean isMatches = passwordEncoder.matches(pw, encodedPw);	// 사용자가 입력한 비밀번호와 암호화 된 비밀번호가 서로 일치하는지 확인
			
			if(isMatches) {
				user = userDao.findByIdAndPw(id, encodedPw);

				return user;
			}
		}
		return Optional.empty(); 	// nullPointException 고려해서 null값으로 리턴하지 않음
	}

}
