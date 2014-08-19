package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circle")
public class Circle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CIRCLE_MEMBER_NUM = "memberNum";

	@Id
	@GeneratedValue
	@Column(name = "circle_id")
	private int id;

	@Column(name = "circle_name")
	private String name;

	@Column(name = "circle_establishtime")
	private Date establishTime;

	@Column(name = "circle_establisherid")
	private int establisherId;

	@Column(name = "circle_describe")
	private String describe;

	@Column(name = "circle_membernum")
	private int memberNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}

	public int getEstablisherId() {
		return establisherId;
	}

	public void setEstablisherId(int establisherId) {
		this.establisherId = establisherId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "Circle [id=" + id + ", name=" + name + ", establishTime="
				+ establishTime + ", establisherId=" + establisherId
				+ ", describe=" + describe + ", memberNum=" + memberNum + "]";
	}

	public Circle(int id, String name, Date establishTime, int establisherId,
			String describe) {
		super();
		this.id = id;
		this.name = name;
		this.establishTime = establishTime;
		this.establisherId = establisherId;
		this.describe = describe;
	}

	public Circle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Circle(String name, Date establishTime, int establisherId,
			String describe) {
		super();
		this.name = name;
		this.establishTime = establishTime;
		this.establisherId = establisherId;
		this.describe = describe;
	}

	public Circle(String name, int establisherId, String describe) {
		this(name, new Date(), establisherId, describe);
	}
}
