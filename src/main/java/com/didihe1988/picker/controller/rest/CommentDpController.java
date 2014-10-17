package com.didihe1988.picker.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.dp.CommentDp;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class CommentDpController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/json/comment/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommentDp(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		CommentDp commentDp = commentService.getCommentDpByCommentId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_COMMENT,
				commentDp);
	}
}
