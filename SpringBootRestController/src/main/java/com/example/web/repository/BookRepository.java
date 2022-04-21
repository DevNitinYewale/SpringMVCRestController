package com.example.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.web.entity.Book;


public interface BookRepository extends JpaRepository<Book, Integer>, CrudRepository<Book, Integer> {

//	@Query(value="select * from Book b where b.name=:name", nativeQuery = true )
//	public Book getByName(String name);
	
	@Query(value = "select * from Book b where b.name=:name", nativeQuery = true)
	public Book getByName(String name);
	
}
