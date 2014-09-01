package com.didihe1988.picker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.CommentDp;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class CommentDpController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/json/comment/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommentDp(@PathVariable int id) {
		CommentDp commentDp = commentService.getCommentDpByCommentId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT, commentDp);
	}
}
