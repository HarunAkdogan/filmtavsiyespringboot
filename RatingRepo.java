package com.example.demo;




import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepo extends CrudRepository<Rating, Integer>{
	
	
	List<Rating> findBymovieid(int movieid);
	List<Rating> findByuserid(int userid);

}
