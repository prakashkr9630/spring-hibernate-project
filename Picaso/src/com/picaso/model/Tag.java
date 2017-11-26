package com.picaso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int Imgid;
	private String tag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImgid() {
		return Imgid;
	}
	public void setImgid(int imgid) {
		Imgid = imgid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
