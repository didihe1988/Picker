package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.didihe1988.picker.model.dp.SearchResult;
import com.didihe1988.picker.model.dp.SearchResult.Type;
import com.didihe1988.picker.model.form.FeedForm;
import com.didihe1988.picker.model.interfaces.Search;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.model.json.QuestionJson;
import com.didihe1988.picker.utils.MarkdownUtils;

@Entity
@Table(name = "feed")
public class Feed implements Serializable, Search {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int TYPE_ALL = 0;
	public static final int TYPE_QUESTION = 1;
	public static final int TYPE_NOTE = 2;

	@Id
	@GeneratedValue
	@Column(name = "feed_id")
	protected int id;

	@Column(name = "feed_bookid")
	protected int bookId;

	@Column(name = "feed_userid")
	protected int userId;

	@Column(name = "feed_title")
	protected String title;

	@Column(name = "feed_content")
	protected String content;

	@Column(name = "feed_brief")
	protected String brief;

	@Column(name = "feed_date")
	protected Date date;

	@Column(name = "feed_page")
	protected int page;

	@Column(name = "feed_type")
	protected int type;

	@Column(name = "feed_ispublic")
	protected boolean isPublic;

	@Column(name = "feed_favoritenum")
	protected int favoriteNum;

	@Column(name = "feed_answernum")
	protected int answerNum;

	@Column(name = "feed_commentnum")
	protected int commentNum;

	@Column(name = "feed_follownum")
	protected int followNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * @description 移除content的MarkDown tag
	 */
	public void setBriefByContent() {
		this.brief = MarkdownUtils.removeTags(this.content);
	}

	public Feed() {

	}

	public Feed(int id, int bookId, int userId, String title, String content,
			String brief, Date date, int page, int type, boolean isPublic,
			int favoriteNum, int answerNum, int commentNum, int followNum) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.brief = brief;
		this.date = date;
		this.page = page;
		this.type = type;
		this.isPublic = isPublic;
		this.favoriteNum = favoriteNum;
		this.answerNum = answerNum;
		this.commentNum = commentNum;
		this.followNum = followNum;
	}

	public Feed(int bookId, int userId, String title, String content,
			String brief, Date date, int page, int type, boolean isPublic) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.page = page;
		this.type = type;
		this.brief = brief;
		this.isPublic = isPublic;
	}

	public Feed(int bookId, int userId, String title, String content,
			String brief, int page, int type, boolean isPublic) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.page = page;
		this.type = type;
		this.brief = brief;
		this.isPublic = isPublic;
	}

	public Feed(int bookId, int userId, String title, String content,
			String brief, int page, int type) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.page = page;
		this.type = type;
		this.brief = brief;
		this.isPublic = true;
	}

	public Feed(FeedForm feedForm, int bookId, int userId) {
		this(bookId, userId, feedForm.getTitle(), feedForm.getRaw(),
				MarkdownUtils.removeTags(feedForm.getRaw()),
				feedForm.getPage(), getType(feedForm.getType()));
	}

	private static int getType(String formType) {
		int type = Feed.TYPE_NOTE;
		if (formType.equals("question")) {
			type = Feed.TYPE_QUESTION;
		}
		return type;
	}

	/*
	 * 拷贝构造函数
	 */
	public Feed(Feed feed) {
		this(feed.getId(), feed.getBookId(), feed.getUserId(), feed.getTitle(),
				feed.getContent(), feed.getBrief(), feed.getDate(), feed
						.getPage(), feed.getType(), feed.isPublic(), feed
						.getFavoriteNum(), feed.getAnswerNum(), feed
						.getCommentNum(), feed.getFollowNum());
	}

	@Override
	public String toString() {
		return "Feed [id=" + id + ", bookId=" + bookId + ", userId=" + userId
				+ ", title=" + title + ", content=" + content + ", brief="
				+ brief + ", date=" + date + ", page=" + page + ", type="
				+ type + ", isPublic=" + isPublic + ", favoriteNum="
				+ favoriteNum + ", answerNum=" + answerNum + ", commentNum="
				+ commentNum + ", followNum=" + followNum + "]";
	}

	/**
	 * 
	 * @description check: bookId userId title content page
	 */
	public boolean checkFieldValidation() {
		/*
		 * 算上了page==0
		 */
		if ((this.bookId != 0) && (this.title != null)
				&& (!this.title.equals("")) && (this.content != null)
				&& (!this.content.equals("")) && (this.page >= 0)) {
			return true;
		}
		return false;

	}

	@Override
	public SearchResult toSearchResult() {
		// TODO Auto-generated method stub

		return new SearchResult(this.bookId, getSearchResultType(), this.title,
				this.brief);
	}

	private Type getSearchResultType() {
		if (this.type == TYPE_QUESTION) {
			return Type.Question;
		}
		return Type.Note;
	}
	/*
	 * public QuestionJson toQuestionJson() {
	 * 
	 * return new QuestionJson(this.title, null, "/detail/" + this.id,
	 * this.date, this.commentNum, this.answerNum); }
	 * 
	 * public NoteJson toNoteJson() { return new NoteJson(this.title, null,
	 * "/detail/" + this.id, this.date, this.content); }
	 */

}
