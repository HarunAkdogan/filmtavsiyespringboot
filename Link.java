package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int movieid;
	String imdbid;
	int tmdbid;
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	
	
	public String getImdbid() {
		return imdbid;
	}
	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}
	public int getTmdbid() {
		return tmdbid;
	}
	public void setTmdbid(int tmdbid) {
		this.tmdbid = tmdbid;
	}
	
	
}
