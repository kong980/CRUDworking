package com.carrot.model;

import java.sql.Timestamp;

import com.carrot.entity.users.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int idx;
	private String id;
	private String pw;
	private UserRole role;
	private String mobileNumber;
	private boolean activated;
	private double ratingScore;
	private Timestamp createdAt;
	private Timestamp updateDate;
}
