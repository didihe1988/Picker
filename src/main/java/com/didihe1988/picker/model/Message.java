package com.didihe1988.picker.model;

//作为评论、私信的基类
public class Message {
	protected int id;
	protected int receiverId;
	protected int producerId;

	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Message(int id, int receiverId, int producerId) {
		super();
		this.id = id;
		this.receiverId = receiverId;
		this.producerId = producerId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getProducerId() {
		return producerId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}
	
}
