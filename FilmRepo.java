package com.example.demo;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepo extends CrudRepository<Film, Integer>{
	
	List<Film> findByimdbid(String imdbid);
	
	@Query(value="select * from film where title like %?1%", nativeQuery = true)
	List<Film> findBytitle(String title);
	
	@Query(value=" SELECT film.*, count(*) as counter FROM film JOIN izlenim  ON film.imdbid = izlenim.imdbid WHERE izlenimtarihi <= CURDATE() 	 AND izlenimtarihi >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY film.imdbid ORDER BY count(film.imdbid) DESC LIMIT 20", nativeQuery=true)
	//@Query("select new com.example.demo.Film(v.filmid, count(v)) from Film v group by v.filmid")
	List<Film> trendGetir();
	
	@Query(value="(select * from film order by rand() limit 10) order by imdbrating desc", nativeQuery=true)
	List<Film> rastfilm();

}