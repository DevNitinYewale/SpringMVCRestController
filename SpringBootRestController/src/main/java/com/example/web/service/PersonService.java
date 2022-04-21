package com.example.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entity.Person;
import com.example.web.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;
	
	
	//DISPLAY ALL
	public List<Person> getAllPerson(){
		return personRepo.findAll();
	}
	
	//DESPLAY BY ID
	public Person getPersonById(Integer id) {
		return personRepo.findById(id).get();
	}
	
	//SAVE PERSON
	public void savePerson(Person person ) {
		personRepo.save(person);
	}
	
	//DELETE BY ID
	public void deletePersonId(Integer id) {
		personRepo.deleteById(id);
	}
	
	//DISPLAY PERSON BY NAME
	public List<Person> getPersonName(String name){
		return personRepo.getPersonByName(name);
	}
	
}