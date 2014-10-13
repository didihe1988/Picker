package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
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
		if (relatedImage == null) {
			return Status.NULLPOINTER;
		}
		int status = relatedImageDao.addRelatedImage(relatedImage);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

}
