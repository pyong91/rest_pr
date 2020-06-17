package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

// CORS 설정
//  - Cross-Origin Resource Sharing의 줄임말
//  - 다른 출처의 자원에 접근할 수 있도록 허용하는 설정

@CrossOrigin(origins = {"*"}, allowedHeaders = {"Authorization"})
@RestController
public class DemoController {
	
	@GetMapping("/")
	public String welcome() {
		return "welcome to rest service";
	}
	/*
	  REST에서의 핵심은 "자원"을 기준으로 주소를 정한다.(ROA: Resource Oriented Architecture)
	   - 추가적으로 POST(C)/GET(R)/PUT(U)/DELETE(D) 방식을 더해서 직관적인 처리를 수행
	  SOAP에서의 핵심은 "서비스"를 기준으로 주소를 정한다.(SOA: Service Oriented Architecture)
	  
	  Book으로 설계하면
	  [POST] /book/ : 등록
	  [GET] /book/ : 조회
	  [PUT] /book/ : 수정
	  [DELETE] /book/ : 삭제
	   - POST랑 PUT은 body를 가질 수 있다.
	*/
	
	@Autowired
	private BookRepository bookRepo;
	
	//@RequestParam : 파라미터 수신
	//@ModelAttribute : 파라미터를 객체로 수신
	//@RequestBody : 요청 바디를 수신
	// - 등록 후에는 등록된 결과를 반환(json형식 - jackson-databind 사용[스프링부트에 내장])
	@PostMapping("/book/") // '/'까지 적어야함 end-point
	public Book add(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@GetMapping("/book/")
	public List<Book> list() {
		return bookRepo.findAll();
		//return bookRepo.findAll(PageRequest.of(0, 10));
	}
	
	// 파라미터처럼 주소에 데이터를 보낼 일이 있으면 PathVariable을 사용
	// - PathVariable이 종류가 많을 때에는 왼쪽에 이름을 추가한다.
	// @DeleteMapping("/book/name/{name}")
	// @DeleteMapping("/book/no/{no}")
	@DeleteMapping("/book/{no}")
	public void delete(@PathVariable int no) {
		bookRepo.deleteById(no);
	}
	
	// 수정을 위해서는 수정할 항목과 PK가 필요
	//  - 등록과 마찬가지로 수정된 결과가 반환
	//  - PK가 같은 경우 수정이 이루어짐
	//  - 문제는 .. PK가 없으면 추가가 된다는 것, 그리고 모든 정보를 다 줘야 한다는 것..
	@PutMapping("/book/")
	public Book edit(@RequestBody Book book) throws Exception{
		// return bookRepo.save(book); // PK가 없으면 신규생성되버림
		
		// 선조회 + 후 수정
		//Optional<Book> op = bookRepo.findById(book.getNo());
		// method reference : Exception의 new를 불러라(객체 생성)
		//Book find = op.orElseThrow(Exception::new); 
		Book find = bookRepo.findById(book.getNo()).orElseThrow(Exception::new);
		// find에 수정된 정보를 추가하여 저장하면 수정이 이루어진다.
		find.setName(book.getName());
		find.setPublisher(book.getPublisher());
		find.setPrice(book.getPrice());
		return bookRepo.save(find);
	}
	
	// 단일 조회
	@GetMapping("/book/no/{no}")
	public Book get(@PathVariable int no) throws Exception{
		return bookRepo.findById(no).orElseThrow(Exception::new);
	}
	
	@GetMapping("/book/name/{name}")
	public List<Book> get(@PathVariable String name) { 
		//return bookRepo.findAllByName(name);	// 이름 일치 검색
		return bookRepo.findAllByNameContaining(name);
	}
	
	@GetMapping("/book/page/{page}/size/{size}/order/{order}")
	public Page<Book> list(
			@PathVariable int page, 
			@PathVariable int size,  
			@PathVariable String order) {
		// page번호가 0부터 시작 (보정 필요)
		PageRequest req = PageRequest.of(page - 1, size, Sort.by(order));
		return bookRepo.findAll(req);
	}
	
}
