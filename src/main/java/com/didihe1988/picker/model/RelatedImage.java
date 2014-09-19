package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relatedimage")
public class RelatedImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int QUESTION_IMAGE = 0;
	public static final int NOTE_IMAGE = 1;
	public static final int ANSWER_IMAGE = 2;
	public static final int UNKOWN_IMAGE = 3;

	@Id
	@GeneratedValue
	@Column(name = "relatedimage_id")
	private int id;

	@Column(name = "relatedimage_relatedid")
	private int relatedId;

	@Column(name = "relatedimage_type")
	private int type;

	@Column(name = "relatedimage_imageurl")
	private String imageUrl;

	public RelatedImage() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(int relatedId) {
		this.relatedId = relatedId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/*
	public static int getTypeFromObject(Object object) {
		if (object instanceof Feed) {
			int type = ((Feed) object).getType();
			if (type == Feed.TYPE_NOTE) {
				return QUESTION_IMAGE;
			} else {
				return NOTE_IMAGE;
			}
		} else if (object instanceof Answer) {
			return ANSWER_IMAGE;
		} else {
			return UNKOWN_IMAGE;
		}
	}*/

	public RelatedImage(int id, int relatedId, int type, String imageUrl) {
		super();
		this.id = id;
		this.relatedId = relatedId;
		this.type = type;
		this.imageUrl = imageUrl;
	}

	public RelatedImage(int relatedId, int type, String imageUrl) {
		this.relatedId = relatedId;
		this.type = type;
		this.imageUrl = imageUrl;
	}
}
