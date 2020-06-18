package com.example.demo.entity;

// 프로젝션(Projection)
// - Entity의 정보를 선택적으로 가져오고 추출만 가능하도록 만드는 도구
// - Set은 불가하고 Get만 가능
// - 원하는 항목의 Getter메소드만 생성하면 됨

public interface MemberInfo {
	String getMember_id();
	String getMember_nickname();
}
