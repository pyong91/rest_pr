package com.example.demo.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Integer>{
	
	// convertToDatabaseColumn 은 자바에서 DB로 갈 때 변환하는 메소드
	@Override
	public Integer convertToDatabaseColumn(Boolean attribute) {
		// 1은 true, 2는 false와 맵핀
		if(attribute == null) return 2;
		return attribute ? 1 : 2;
	}
	
	// 반대 메소드
	@Override
	public Boolean convertToEntityAttribute(Integer dbData) {
		if(dbData == null) return false;
		return dbData == 1;
	}
}
