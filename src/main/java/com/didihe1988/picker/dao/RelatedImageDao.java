package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.RelatedImage;

public interface RelatedImageDao {

	public int addRelatedImage(RelatedImage relatedImage);

	// public int deleteRelatedImage(RelatedImage relatedImage);

	// public int updateRelatedImage(RelatedImage relatedImage);

	//public List<RelatedImage> queryRelatedImagesByKey(int relatedId, int type);

	//public RelatedImage queryFirstRelatedImagesByKey(int relatedId, int type);

	public String queryFirstImageUrlByKey(int relatedId, int type);
	
	public List<String> queryImageUrlsByKey(int relatedId, int type);
}
