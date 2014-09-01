package com.didihe1988.picker.model;

public class CommentDp extends Comment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerName;

	private String commentedName;

	private String userAvatarUrl;

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getCommentedName() {
		return commentedName;
	}

	public void setCommentedName(String commentedName) {
		this.commentedName = commentedName;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public CommentDp() {

	}

	public CommentDp(Comment comment, String producerName,
			String commentedName, String userAvatarUrl) {
		super(comment.getId(), comment.getCommentedId(), comment
				.getProducerId(), comment.getContent(), comment.getType(),
				comment.getFavoriteNum(), comment.getDate());
		this.producerName = producerName;
		this.commentedName = commentedName;
		this.userAvatarUrl = userAvatarUrl;
	}

}
