package com.example.web.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.web.entity.Book;
import com.example.web.exception.CustomException;
import com.example.web.repository.BookRepository;
import com.example.web.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	BookRepository bookRepo;

	@GetMapping("byName/{name}")
	public ResponseEntity<Book> getBook(@PathVariable String name) throws Exception  {
		Book b2 = bookRepo.getByName(name);
	
		if (b2 == null) 
				throw new Exception("Book Details Not Found:");
		return new ResponseEntity<Book>(b2, HttpStatus.OK);
		
		}
	

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookId(@PathVariable Integer id) throws Exception{

			Book b1 = bookService.getBookId(id);
			if(b1==null)  
				throw new NoSuchElementException("ID Not Found");
			
			return new ResponseEntity<Book>(b1,HttpStatus.OK);	
		}

	@GetMapping("/")
	public ResponseEntity<List<Book>> getAllBooks() throws Exception{
		List<Book> l1=bookService.getAll();
		if(l1.isEmpty())
			throw new Exception("Books not available");
		return new ResponseEntity<List<Book>>(l1,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id){
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			//return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			throw new EmptyResultDataAccessException(id);
		}
	}
	
	
	
	/*
	@GetMapping("/get")
	public List<Book> getAllBook() {

		return bookService.getAll();
	}

	@PostMapping("/save")
	public String bookSaved(@RequestBody Book book2) {
		bookService.saveBook(book2);
		return "book saved";
	}

	@GetMapping("/get/{id}")
	public Book getBook(@PathVariable Integer id) throws Exception {
		Book book = bookService.getBookId(id);

		if (book == null) {
			System.out.println("inside If Block...");
			throw new Exception("Invalid id: ");
		} else
			return book;
	}

	@GetMapping("/getBy/{name}")
	public Book getBook(@PathVariable String name) {
		Book b2 = bookRepo.getByName(name);

		if (b2 == null) {
			System.out.println("in error...");
			throw new CustomException("Book name not found");
		} else
			return b2;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
		return "book deleted";
	}

	@GetMapping("/entity/{id}")
	public ResponseEntity<Book> entity(@PathVariable Integer id) {
		Book b1 = bookService.getBookId(id);
		try {
			return new ResponseEntity<Book>(b1, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Book>(b1, HttpStatus.NOT_FOUND);
		}
	}
*/
	/*
	 * @GetMapping("/responseEntity/{name}") public ResponseEntity<Book>
	 * getBook2Entity(@PathVariable String name){ Book b2=bookRepo.getByName(name);
	 * try { return new ResponseEntity<Book> (b2, HttpStatus.OK); } catch (Exception
	 * e) { // TODO: handle exception return new
	 * ResponseEntity<Book>(HttpStatus.NOT_FOUND); } }
	 */
	/*
	 * @GetMapping("/getBy/{name}") public Book getBook(@PathVariable String name)
	 * throws Exception { Book b2=bookRepo.getByName(name);
	 * 
	 * if(b2==null) { System.out.println("in error..."); throw new
	 * Exception("Invalid name"); } else return b2; }
	 */

}
