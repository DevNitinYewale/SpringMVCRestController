package com.example.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.entity.Person;
import com.example.web.exception.CustomException;
import com.example.web.repository.PersonRepository;
import com.example.web.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@Autowired
	PersonRepository perRepo;
	
	@PostMapping("/insert/")
	public ResponseEntity<Void> savePerson(@RequestBody Person person) throws Exception{
		
		try {
			Person id=perRepo.save(person);
			System.out.println(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/")
	public List<Person> displayall() throws Exception{
		//return personService.getAllPerson();
		List<Person> person=new ArrayList<>();
		person.addAll(personService.getAllPerson());
		if(person.isEmpty())
			throw new Exception("No Record Found");
		return person;
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<Person>> getPerson(@PathVariable("name") String name) throws Exception{
		List<Person> p=personService.getPersonName(name);
		//System.out.println(p);
		if(p.isEmpty())
			throw new Exception("Person not found..");
		return new ResponseEntity<List<Person>> (p,HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Person> getPersonByID(@PathVariable("id") Integer id){
		Person p1=personService.getPersonById(id);
		if(p1==null)
			throw new NoSuchElementException();
		else
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePersonById(@PathVariable("id") Integer id){
		try {
			personService.deletePersonId(id);
			
			ResponseEntity<Void> rs=new ResponseEntity<Void>(HttpStatus.OK);
			System.out.println(rs);
			return rs;
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			System.out.println(e);
			ResponseEntity<Void> rs2= new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			System.out.println(rs2);
			throw new EmptyResultDataAccessException(id);
		}
	}
	
}
/*	
	//DISPLAY ALL
	@GetMapping("/get")
	public List<Person> displayall(){
		//return personService.getAllPerson();
		List<Person> person=new ArrayList<>();
		person.addAll(personService.getAllPerson());
		if(person.isEmpty())
			throw new CustomException("empty list");
		return person;
	}
	
	//DISPLAY BY ID
	@GetMapping("/get/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Integer id){
		try {
			Person person=personService.getPersonById(id);
				return new ResponseEntity<Person> (person, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Person> (HttpStatus.NOT_FOUND);
		}		
	}
	
	//SAVE 
	@PostMapping("/insert")
	public String savePersonDetails(@RequestBody Person person) {
		personService.savePerson(person);
		return "person saved";
	}
	
	//DELETE BY ID
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable Integer id) {
		personService.deletePersonId(id);
		return "person deleted";
	}
	
	//SAVE
	@GetMapping("/hello")
	public String greeting(@RequestParam Integer id, 
						   @RequestParam String name,
						   @RequestParam Integer age) {
		Person p1=new Person(id, name, age);
		personService.savePerson(p1);
				
		return "person saved";
	}
	
	//DISPLAY BY NAME
	@GetMapping("/getByName")
	public List<Person> getName(@RequestParam String name){
		//return personService.getPersonName(name);
		//System.out.println(l1);
		
		List<Person> l1=new ArrayList<>();
		l1.addAll(personService.getPersonName(name));
		if(l1.isEmpty())
			throw new CustomException("person name not found");
		return l1;
	}
	
	@GetMappigetErrorResponseng("/getByName2")
	public Person getPersonName(@RequestParam String name) {
		Person p2= perRepo.getPersonByName2(name);
		if(p2==null)
			throw new CustomException("person name not found ");
		return p2;
	}
	
	//UPDATE PERSON
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Person person, @PathVariable Integer id){
		
		try {
			person.setId(id);
			personService.savePerson(person);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}*/