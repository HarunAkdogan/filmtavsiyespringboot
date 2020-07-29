package com.example.demo;




import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TavsiyeRepo extends CrudRepository<Tavsiyefilm, Integer>{
	
	
	List<Tavsiyefilm> findByimdbid(String movieid);
	@Query(value="select * from tavsiyefilm order by tavmiktari desc", nativeQuery=true)
	List<Tavsiyefilm> findBykulid(int kulid);
	@Query(value="select * from tavsiyefilm where kulid=?1 and imdbid=?2", nativeQuery=true)
	List<Tavsiyefilm> tavBul(int kulid, String imdbid);

}
