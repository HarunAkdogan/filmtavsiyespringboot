package com.example.demo;

public interface UserService {
	
    void save(Kullanici user);

    Kullanici findByUsername(String username);
}