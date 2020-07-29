package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IzlenimRepo extends CrudRepository<Izlenim, Integer> {

	List<Izlenim> findByimdbid(String imdbid);

	List<Izlenim> findBykulid(int kulid);

	@Transactional	
	@Modifying
	@Query(value="update izlenim set kulrating = ?2, izlenimtarihi=?3 where kulid=?1", nativeQuery=true)
	void setIzlenimBykulid(int kulid, int kulrating, String izlenimtarihi);
	
	@Query(value="select * from izlenim where kulid=?1 order by rand() limit 2", nativeQuery=true)
	List<Izlenim> rastgeleIki(int kulid);
	
	
	


	//@Transactional
	//@Modifying(clearAutomatically = true)
	//@Query("UPDATE Izlenim i SET i.kulrating = :kulrating, i.izlenimtarihi=:izlenimtarihi WHERE i.kulid = :kulid")
	//int setIzlenimBykulid(@Param("kulid") int kulid, @Param("kulrating")int kulrating, @Param("izlenimtarihi") String izlenimtarihi);

}
