package com.didihe1988.picker.service.interfaces;

import java.util.List;

import com.didihe1988.picker.model.display.SearchResult;

public interface SearchService {

	public List<SearchResult> search(String key);

}
