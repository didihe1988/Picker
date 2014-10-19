package com.didihe1988.picker.model.form;

public class AnswerForm {
	private String raw;

	private int questionId;

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public AnswerForm() {
		
	}

	public AnswerForm(String raw, int questionId) {
		super();
		this.raw = raw;
		this.questionId = questionId;
	}

	public boolean checkFieldValidation() {
		if ((this.questionId > 0) && (this.raw != null)
				&& (!this.raw.equals(""))) {
			return true;
		}
		return false;
	}

}
