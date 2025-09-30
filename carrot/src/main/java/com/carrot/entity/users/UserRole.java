package com.carrot.entity.users;

import lombok.Getter;

@Getter
public enum UserRole {
	admin(0, "admin"),
	user(1, "user"),
	unused(2, "unused");
	
	private final int code;	// DB 저장용
	private final String role;	// 조회용 문자열
	
	UserRole(int code, String role){
		this.code = code;
		this.role = role;
	}

	public int getCode() {
		return code;	// DB 저장할 값 반환
	}
	
	public static UserRole of(int code) {
		for(UserRole role : UserRole.values()) {
			if(role.getCode() == code) return role;
		}
		throw new IllegalArgumentException("Unknown code: " + code);
	}
}
