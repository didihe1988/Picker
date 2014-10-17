package com.didihe1988.picker.model.json;

import java.util.Date;
import java.util.List;

import com.didihe1988.picker.model.dp.AnswerDp;

public class AnswerJson extends BaseJson {
	private String brief;

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public AnswerJson() {

	}

	public AnswerJson(String title, String picture, String link, String time,
			String brief) {
		super(title, picture, link, time);
		this.brief = brief;
	}

	public AnswerJson getAnswerJsonFromAnswer(AnswerDp answerDp) {
		if (answerDp != null) {
			String imageUrl = "";
			List<String> imageUrls = answerDp.getImageUrls();
			if ((imageUrls != null) && (imageUrls.size() != 0)) {
				imageUrl = imageUrls.get(0);
			}
			String link = "/picker/question/" + answerDp.getQuestionId();
			return new AnswerJson(answerDp.getQuestionName(), imageUrl, link,
					answerDp.getStrDate(), answerDp.getBrief());
		} else {
			return new AnswerJson();
		}
	}
}
