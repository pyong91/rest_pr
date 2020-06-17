package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

// Repository 생성 시 JPA에서 제공하는 Repository를 하나 상속 받아야 함
// - 1번째 Generic은 Entity type
// - 2번째 Generic은 P.K type
public interface BookRepository extends JpaRepository<Book, Integer>{
	// 이름 일치 검색 메소드
	List<Book> findAllByName(String name);
	Page<Book> findAllByName(String name, Pageable pageable);
	
	// 이름 일치 검색 메소드(+대소문자 무시)
	List<Book> findAllByNameIgnoreCase(String name);
	Page<Book> findAllByNameIgnoreCase(String name, Pageable pageable);
	
	// 이름 유사 검색 메소드
	List<Book> findAllByNameLike(String name);
	Page<Book> findAllByNameLike(String name, Pageable pageable);
	List<Book> findAllByNameContaining(String name);
	Page<Book> findAllByNameContaining(String name, Pageable pageable);
	
	// 이름 유사 검색 메소드(+대소문자 무시)
	List<Book> findAllByNameLikeIgnoreCase(String name);
	Page<Book> findAllByNameLikeIgnoreCase(String name, Pageable pageable);
	List<Book> findAllByNameContainingIgnoreCase(String name);
	Page<Book> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	// + 정렬(OrderBy+항목+순서)
	List<Book> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
	Page<Book> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
	
}
