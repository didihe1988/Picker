package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.service.RelatedImageService;

@Service
@Transactional
public class RelatedImageServiceImpl implements RelatedImageService {
	@Autowired
	private RelatedImageDao relatedImageDao;

	@Override
	public int addRelatedImage(RelatedImage relatedImage) {
		// TODO Auto-generated method stub
		return relatedImageDao.addRelatedImage(relatedImage);
	}

	@Override
	public List<RelatedImage> getRelatedImagesByKey(int relatedId, int type) {
		// TODO Auto-generated method stub
		return relatedImageDao.queryRelatedImagesByKey(relatedId, type);
	}

	@Override
	public List<String> getImageUrlsByKey(int relatedId, int type) {
		// TODO Auto-generated method stub
		final List<RelatedImage> relatedImages = getRelatedImagesByKey(
				relatedId, type);
		if (relatedImages == null) {
			return null;
		} else {
			List<String> list = new ArrayList<String>();
			for (RelatedImage relatedImage : relatedImages) {
				list.add(relatedImage.getImageUrl());
			}
			return list;
		}
	}

}
