package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.DialogDao;
import com.didihe1988.picker.model.Dialog;
import com.didihe1988.picker.service.DialogService;

@Service
@Transactional
public class DialogServiceImpl implements DialogService {
	@Autowired
	private DialogDao dialogDao;

	@Override
	public Dialog getDialogById(long id) {
		// TODO Auto-generated method stub
		return dialogDao.queryDialogById(id);
	}

	@Override
	public int addDialog(Dialog dialog) {
		// TODO Auto-generated method stub
		if (dialog == null) {
			return Status.NULLPOINTER;
		}
		int status = dialogDao.addDialog(dialog);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteDialogById(long id) {
		// TODO Auto-generated method stub
		if (!isDialogExistsById(id)) {
			return Status.NOT_EXISTS;
		}
		dialogDao.deleteDialogById(id);
		return Status.SUCCESS;
	}

	@Override
	public boolean isDialogExistsById(long id) {
		// TODO Auto-generated method stub
		return dialogDao.isDialogExistsById(id);
	}

	@Override
	public long getLatestDialogId() {
		// TODO Auto-generated method stub
		return dialogDao.queryLatestDialogId();
	}

	@Override
	public int incrementCount(long id) {
		// TODO Auto-generated method stub
		return dialogDao.incrementCount(id);
	}

	@Override
	public int decrementCount(long id) {
		// TODO Auto-generated method stub
		return dialogDao.decrementCount(id);
	}

}
