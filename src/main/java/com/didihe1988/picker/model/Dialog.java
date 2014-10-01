package com.didihe1988.picker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dialog")
public class Dialog {
	@Id
	@GeneratedValue
	@Column(name = "dialog_id")
	private long id;

	@Column(name = "dialog_count")
	private int count;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Dialog() {
		this.count=1;
	}
	
	/*
	public Dialog(long id, int count) {
		super();
		this.id = id;
		this.count = count;
	}*/

	@Override
	public String toString() {
		return "Dialog [id=" + id + ", count=" + count + "]";
	}

}
