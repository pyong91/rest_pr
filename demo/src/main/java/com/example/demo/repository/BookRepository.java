package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

// Repository 생성 시 JPA에서 제공하는 Repository를 하나 상속 받아야 함
// - 1번째 Generic은 Entity type
// - 2번째 Generic은 P.K type
public interface BookRepository extends JpaRepository<Book, Integer>{
	
}
