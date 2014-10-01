package com.didihe1988.picker.service;

import com.didihe1988.picker.model.Dialog;

public interface DialogService {
	public Dialog getDialogById(long id);

	public int addDialog(Dialog dialog);

	public int deleteDialogById(long id);

	public boolean isDialogExistsById(long id);

	public long getLatestDialogId();

	public int incrementCount(long id);

	public int decrementCount(long id);
}
