package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Kullanici, Integer>{
	
	Kullanici findByUsername(String username);
}
