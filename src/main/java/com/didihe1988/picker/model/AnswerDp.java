package com.didihe1988.picker.model;

public class AnswerDp extends Answer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String questionName;

	private String replierName;

	private String replierAvartarUrl;

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getReplierName() {
		return replierName;
	}

	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}

	public String getReplierAvartarUrl() {
		return replierAvartarUrl;
	}

	public void setReplierAvartarUrl(String replierAvartarUrl) {
		this.replierAvartarUrl = replierAvartarUrl;
	}

	public AnswerDp() {

	}

	public AnswerDp(Answer answer, String questionName, String replierName,
			String replierAvartarUrl) {
		super(answer.getId(), answer.getQuestionId(), answer.getReplierId(),
				answer.getContent(), answer.getFavoriteNum(), answer
						.getCommentNum(), answer.getDate());
		this.questionName = questionName;
		this.replierName = replierName;
		this.replierAvartarUrl = replierAvartarUrl;
	}

}
