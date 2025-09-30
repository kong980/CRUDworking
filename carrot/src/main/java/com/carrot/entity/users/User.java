package com.carrot.entity.users;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// JPA가 기본키를 생성하도록 한다.
	private Integer idx;	//회원 고유 번호
	
	@Column(nullable = false, unique = true) // 중복 금지
	private String id; // 회원 아이디
	
	
	@Column(nullable = false)
	private String pw;
	
	@Column(nullable = false)
	@Convert(converter = UserRoleConverter.class)
	@ColumnDefault("1") // 기본으로 user 권한 부여 (DB 기본값)
	private UserRole role = UserRole.user; // 회원 권한(admin / user /unused:탈퇴
	
	@Column(nullable = false)
	private String mobileNumber; // 휴대폰 번호
	
	@Column(nullable = false)
	@ColumnDefault("1") // activated = true , DB 기본값
	private boolean activated = true; // 활성화 상태
	
	@Column(nullable = false)
	@ColumnDefault("0") // DB 기본값
	private double ratingScore = 0; //평가 점수
	
	@Column(nullable = false)
	@CreatedDate
	private Timestamp createdAt; // 생성 일자(회원가입 날짜)
	
	@Column(insertable = false)
	@LastModifiedDate
	private Timestamp updateDate; // 수정 일자


	
}

