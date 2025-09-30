package com.carrot.entity.users;

import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, Integer>{

	// 엔티티 -> DB 저장
	@Override
	public Integer convertToDatabaseColumn(UserRole attribute) {
		return attribute.getCode(); // enum의 code 값 (0,1)을 DB 컬럼에 저장
	}

	// DB -> 엔티티 조회
	@Override
	public UserRole convertToEntityAttribute(Integer dbData) {
		return UserRole.of(dbData); // DB 정수를 enum으로 복원
	}

}
