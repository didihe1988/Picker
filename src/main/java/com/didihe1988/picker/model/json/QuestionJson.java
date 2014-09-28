package com.didihe1988.picker.model.json;

import java.util.Date;
import java.util.List;

import com.didihe1988.picker.model.dp.FeedDp;

public class QuestionJson extends BaseJson {

	private int comment_cnt;

	private int answers_cnt;

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public int getAnswers_cnt() {
		return answers_cnt;
	}

	public void setAnswers_cnt(int answers_cnt) {
		this.answers_cnt = answers_cnt;
	}

	public QuestionJson() {

	}

	public QuestionJson(String title, String picture, String link, Date time,
			int comment_cnt, int answers_cnt) {
		super(title, picture, link, time);
		this.comment_cnt = comment_cnt;
		this.answers_cnt = answers_cnt;
	}

	public static QuestionJson getQuestionJsonFromQuestion(FeedDp feedDp) {
		if (feedDp != null) {
			String imageUrl = "";
			List<String> imageUrls = feedDp.getImageUrls();
			if ((imageUrls != null) && (imageUrls.size() != 0)) {
				imageUrl = imageUrls.get(0);
			}
			String link = "/picker/question/" + feedDp.getId();
			return new QuestionJson(feedDp.getTitle(), imageUrl, link,
					feedDp.getDate(), feedDp.getCommentNum(),
					feedDp.getAnswerNum());
		} else {
			return new QuestionJson();
		}
	}

}
