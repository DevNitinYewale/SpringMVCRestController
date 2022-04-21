package com.example.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entity.Book;
import com.example.web.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepo;
	
	public List<Book> getAll(){
		
		return bookRepo.findAll();
	}
	
	public void saveBook(Book book) {
		bookRepo.save(book);
	}
	
	public Book getBookId(Integer id) {
		return bookRepo.findById(id).get();
	}
	
	public void deleteBook(Integer id) {
		bookRepo.deleteById(id);
	}
	
//	public Book getName(String name) {
//		return bookRepo.getByName(name);
//	}
}
