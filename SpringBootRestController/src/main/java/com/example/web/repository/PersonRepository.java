package com.example.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.web.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>, CrudRepository<Person, Integer> {
	
	@Query(value = "select * from Person p where p.name=:name", nativeQuery = true)
	public List<Person> getPersonByName(String name);

	
	@Query(value = "select * from Person p where p.name=:name", nativeQuery = true)
	public Person getPersonByName2(String name);
	
//	@Query(value = "delete from Person p where p.id=:id", nativeQuery = true)
//	public void delete(Integer id);
	
	
}