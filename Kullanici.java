package com.example.demo;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "kullanici")
public class Kullanici {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int kulid;
	private String username;
    private String password;
    
	@Transient
    private String passwordconfirm;
	private String dtarih;
	private String email;
	private String ad;
	private String soyad;
	
	@ManyToMany
    private Set<Role> roles;
	
	
	public int getKulid() {
		return kulid;
	}
	public void setKulid(int kulid) {
		this.kulid = kulid;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getPasswordconfirm() {
		return passwordconfirm;
	}
	public void setPasswordconfirm(String passwordconfirm) {
		this.passwordconfirm = passwordconfirm;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDtarih() {
		return dtarih;
	}
	public void setDtarih(String dtarih) {
		this.dtarih = dtarih;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
}
