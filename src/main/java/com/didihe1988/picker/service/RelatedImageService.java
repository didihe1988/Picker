package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.RelatedImage;

public interface RelatedImageService {
	public int addRelatedImage(RelatedImage relatedImage);

	public List<RelatedImage> getRelatedImagesByKey(int relatedId, int type);

	public List<String> getImageUrlsByKey(int relatedId, int type);
}
