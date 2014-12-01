package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Dialog;

public interface DialogDao {
	public Dialog queryDialogById(long id);

	public int addDialog(Dialog dialog);

	public int deleteDialogById(long id);

	public boolean isDialogExistsById(long id);

	public long queryLatestDialogId();

	public int incrementCount(long id);

	public int decrementCount(long id);

}
