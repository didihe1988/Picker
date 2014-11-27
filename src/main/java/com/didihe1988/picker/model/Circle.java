package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.didihe1988.picker.model.dp.SearchResult;
import com.didihe1988.picker.model.interfaces.Search;
import com.didihe1988.picker.model.json.CircleJson;

@Entity
@Table(name = "circle")
public class Circle implements Serializable, Search {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CIRCLE_MEMBER_NUM = "memberNum";

	@Id
	@GeneratedValue
	@Column(name = "circle_id")
	protected int id;

	@Column(name = "circle_name")
	protected String name;

	@Column(name = "circle_establishtime")
	protected Date establishTime;

	@Column(name = "circle_establisherid")
	protected int establisherId;

	@Column(name = "circle_describe")
	protected String describe;

	@Column(name = "circle_membernum")
	protected int memberNum;

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

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	@Override
	public String toString() {
		return "Circle [id=" + id + ", name=" + name + ", establishTime="
				+ establishTime + ", establisherId=" + establisherId
				+ ", describe=" + describe + ", memberNum=" + memberNum + "]";
	}

	public Circle(int id, String name, Date establishTime, int establisherId,
			String describe, int memberNum) {
		super();
		this.id = id;
		this.name = name;
		this.establishTime = establishTime;
		this.establisherId = establisherId;
		this.describe = describe;
		this.memberNum = memberNum;
	}

	public Circle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Circle(Circle circle) {
		this(circle.getId(), circle.getName(), circle.getEstablishTime(),
				circle.getEstablisherId(), circle.getDescribe(), circle
						.getMemberNum());
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

	public boolean checkFieldValidation() {
		if ((this.name != null) && (!this.name.equals(""))
				&& (this.describe != null) && (!this.describe.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SearchResult toSearchResult() {
		// TODO Auto-generated method stub
		return new SearchResult(this.id,SearchResult.RESULT_CIRCLE, this.name, this.describe);
	}

	public CircleJson toCircleJson() {
		return new CircleJson("/group/" + this.id, this.name, this.describe,
				this.memberNum, "/json/circle/" + this.id + "/join",
				this.establishTime);

	}

}
