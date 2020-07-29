package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Izlenim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int izlenimid;
	int kulid;
	String imdbid;
	int kulrating;
	String izlenimtarihi;

	public int getIzlenimid() {
		return izlenimid;
	}

	public void setIzlenimid(int izlenimid) {
		this.izlenimid = izlenimid;
	}

	public int getKulid() {
		return kulid;
	}

	public void setKulid(int kulid) {
		this.kulid = kulid;
	}

	public String getImdbid() {
		return imdbid;
	}

	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

	public int getKulrating() {
		return kulrating;
	}

	public void setKulrating(int kulrating) {
		this.kulrating = kulrating;
	}

	public String getIzlenimtarihi() {
		return izlenimtarihi;
	}

	public void setIzlenimtarihi(String izlenimtarihi) {
		this.izlenimtarihi = izlenimtarihi;
	}

}
