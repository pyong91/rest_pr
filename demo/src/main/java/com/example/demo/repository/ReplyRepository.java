package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.board.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

}
