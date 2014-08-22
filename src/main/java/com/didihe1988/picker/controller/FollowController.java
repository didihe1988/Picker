package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class FollowController {

	@Autowired
	private FollowService followService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/follow/list_byfollowerid.do")
	public String listByFollowerId(HttpServletRequest request, ModelMap modelMap) {
		int followerId = HttpUtils.getIntegerFromReqeust(request, "followerId");
		List<Follow> followList = followService
				.getFollowListByFollowerId(followerId);
		modelMap.addAttribute("followList", followList);
		for (int i = 0; i < followList.size(); i++) {
			System.out.println(followList.get(i).toString());
		}
		return "followlist";
	}

	@RequestMapping(value = "/follow/list_byfolloweduserid.do")
	public String listByFollowedUserId(HttpServletRequest request,
			ModelMap modelMap) {
		int followedUserId = HttpUtils.getIntegerFromReqeust(request,
				"followedUserId");
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(followedUserId);
		modelMap.addAttribute("followList", followList);
		for (int i = 0; i < followList.size(); i++) {
			System.out.println(followList.get(i).toString());
		}
		return "followlist";
	}

	@RequestMapping(value = "/follow/listall_fortest.do")
	public String listAllForTest(ModelMap modelMap) {
		List<Follow> followList = followService.getAllFollowForTest();
		System.out.print(followList);
		modelMap.addAttribute("followList", followList);
		return "followlist";
	}

	@RequestMapping(value = "/follow/add.do")
	public String addFollow(HttpServletRequest request) {
		/*
		 * ���follow
		 */
		int sourceType = HttpUtils.getIntegerFromReqeust(request, "sourceType");
		int sourceId = HttpUtils.getIntegerFromReqeust(request, "sourceId");
		int followerId = HttpUtils.getSessionUserId(request);
		int userId = followerId;
		String userName = HttpUtils.getSessionUserName(request);
		// �Լ����ܹ�ע�Լ�
		if ((sourceId == followerId) && (sourceType == Follow.FOLLOW_USER)) {
			return "followlist";
		}
		Follow follow = new Follow(sourceType, followerId, sourceId);
		followService.addFollow(follow);

		/*
		 * XXX��ע����
		 */
		if (sourceType == Follow.FOLLOW_USER) {
			messageService.addMessageByRecerver(userId,
					Message.MESSAGE_USER_FOLLOWED, followerId, userName,
					sourceId, Message.NULL_RelatedSourceContent);
		}

		/*
		 * ֪ͨ��ע�� С�� (����ע��)��ע��һ��XXX����
		 */
		if (sourceType == Follow.FOLLOW_QUESTION) {
			Question question = questionService.getQuestionById(sourceId);
			String relatedSourceContent = StringUtils.confineStringLength(
					question.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FOLLOWQUESTION, userId, userName,
					sourceId, relatedSourceContent);
		}

		return "/follow/listall_fortest.do";
	}

	@RequestMapping(value = "/follow/delete.do")
	public String deleteFollow(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		Follow follow = followService.getFollowById(id);
		if (follow == null) {
			return "followlist";
		}
		followService.deleteFollow(follow);
		return "followlist";
	}

}
