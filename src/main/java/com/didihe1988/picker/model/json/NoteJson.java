package com.didihe1988.picker.model.json;

import java.util.Date;
import java.util.List;

import com.didihe1988.picker.model.dp.FeedDp;

public class NoteJson extends BaseJson {
	private String brief;

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public NoteJson() {

	}

	public NoteJson(String title, String picture, String link, Date time,
			String brief) {
		super(title, picture, link, time);
		this.brief = brief;
	}

	public static NoteJson getNoteJsonFromNote(FeedDp feedDp) {
		if (feedDp != null) {
			String imageUrl = "";
			List<String> imageUrls = feedDp.getImageUrls();
			if ((imageUrls != null) && (imageUrls.size() != 0)) {
				imageUrl = imageUrls.get(0);
			}
			String link = "/picker/note/" + feedDp.getId();
			return new NoteJson(feedDp.getTitle(), imageUrl, link,
					feedDp.getDate(), feedDp.getBrief());
		} else {
			return new NoteJson();
		}
	}
}