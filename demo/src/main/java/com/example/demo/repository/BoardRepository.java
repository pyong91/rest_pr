package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	// JPQL을 이용한 데이터 조회 구문 작성
	// - 구문만 잘 짜면 메소드 이름은 더이상 중요하지 않다(아예 오해 없도록 다르게 짓는게 좋음)
	// - JPQL은 대소문자를 구분하며 Entity를 기준으로 구문을 작성한다.(테이블 기준X)
	// select * from board; (X)
	// select b from Board b; (O) Entity 기준
	// - 와일드 카드가 없음
	
	// - 조회 시 N+1 이슈가 발생(데이터가 N개면 조회구문이 N+1번 실행된다)
	//@Query("select b from Board b")
	// - 해결책으로 fetch를 명시하여 조인하면 구문이 조인으로 변경되어 실행된다.(fetch join)
	//@Query("select b from Board b join fetch b.replies") // inner join
	@Query("select b from Board b left join fetch b.replies") // left outer join
	List<Board> getList();
	
}
