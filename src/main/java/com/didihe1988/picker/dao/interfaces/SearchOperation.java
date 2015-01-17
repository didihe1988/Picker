package com.didihe1988.picker.dao.interfaces;

import java.util.List;

public interface SearchOperation<T> {

	public List<T> search(String string,boolean isLimited); 
}
