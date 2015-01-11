package com.didihe1988.picker.model.json;

import java.util.Date;

public class CircleJson {
	private String link;

	private String name;

	private String intro;

	private int number_of_people;

	private String join_link;

	private Date create_date;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getNumber_of_people() {
		return number_of_people;
	}

	public void setNumber_of_people(int number_of_people) {
		this.number_of_people = number_of_people;
	}

	public String getJoin_link() {
		return join_link;
	}

	public void setJoin_link(String join_link) {
		this.join_link = join_link;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public CircleJson() {

	}

	public CircleJson(String link, String name, String intro,
			int number_of_people, String join_link, Date create_date) {
		super();
		this.link = link;
		this.name = name;
		this.intro = intro;
		this.number_of_people = number_of_people;
		this.join_link = join_link;
		this.create_date = create_date;
	}

}
