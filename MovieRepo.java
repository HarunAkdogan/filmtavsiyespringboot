package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie, Integer>{

	@Query(value="select * from movie where movieid=?1", nativeQuery=true)
	List<Movie>findBymovieid(int movieid);
	
}
