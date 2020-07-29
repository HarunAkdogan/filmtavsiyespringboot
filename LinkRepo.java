package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<Link, Integer>{
	
	List<Link> findByimdbid(String imdbid);

}
