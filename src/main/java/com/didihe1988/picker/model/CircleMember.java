package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circlemember")
public class CircleMember implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "circlemember_id")
	private int id;

	@Column(name = "circlemember_circleid")
	private int circleId;

	@Column(name = "circlemember_memberid")
	private int memberId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public CircleMember(int id, int circleId, int memberId) {
		super();
		this.id = id;
		this.circleId = circleId;
		this.memberId = memberId;
	}

	public CircleMember(int circleId, int memberId) {
		super();
		this.circleId = circleId;
		this.memberId = memberId;
	}

	public CircleMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CircleMember [id=" + id + ", circleId=" + circleId
				+ ", memberId=" + memberId + "]";
	}

}
