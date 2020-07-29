package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Tavsiyefilm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int tavfilmid;
	int kulid;
	int tavmiktari;
	int movieid;
	String imdbid;

	@OneToOne(optional = false)
	@JoinColumn(name = "movieid", insertable = false, updatable = false)
	private Movie film;

	public int getTavfilmid() {
		return tavfilmid;
	}

	public void setTavfilmid(int tavfilmid) {
		this.tavfilmid = tavfilmid;
	}

	public int getKulid() {
		return kulid;
	}

	public void setKulid(int kulid) {
		this.kulid = kulid;
	}

	public int getTavmiktari() {
		return tavmiktari;
	}

	public void setTavmiktari(int tavmiktari) {
		this.tavmiktari = tavmiktari;
	}

	public String getImdbid() {
		return imdbid;
	}

	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public Movie getFilm() {
		return film;
	}

	public void setFilm(Movie film) {
		this.film = film;
	}

}
