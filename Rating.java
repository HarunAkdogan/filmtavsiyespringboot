package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Rating {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int ratingid;
	int userid;
	int movieid;
	int rating;
	int timestamp;
	
	public int getRatingid() {
		return ratingid;
	}
	public void setRatingid(int ratingid) {
		this.ratingid = ratingid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
