﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/static/lib/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/static/lib/nprogress/nprogress.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/static/lib/fake_bootstrap/fake_bootstrap.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/static/css/picker.css">

<script src="/static/js/jquery.js"></script>
<script src="/static/lib/nprogress/nprogress.js"></script>
<script src="/static/lib/jquery.pjax.js"></script>
<script src="/static/js/picker.js"></script>
<script src="/static/js/epiceditor/js/epiceditor.min.js"></script>
<script src="/static/js/ajaxfileupload.js"></script>
</head>

<!--TODO: AJAX取消息-->
<body onload="gen_pagination(${curPage},${totalPage})">
	<!-- set curUserId -->
	<%
		int curUserId = (Integer) request.getSession().getAttribute(
				"picker_userId");
	%>
	<c:set var="curUserId" scope="session" value="<%=curUserId%>" />
	<!--set curUserId end-->
	<div id="nav" class="row">
		<div id="nav_content">
			<div class="col-13">
				<div id="logo" class="row">
					<div class="col-25">
						<img src="/static/images/elements/logo.png">
					</div>
					<div class="col-65">
						<span id="go_index"><a data-pjax href="/dynamic/1">Picker</a></span>
					</div>
				</div>
			</div>
			<div class="col-22">
				<div id="search_bar">
					<!--<i class="icon-search"></i>-->
					<form action="/search" method="get">
						<label for="search" style="display: none">搜索</label> <input
							type="text" id="search" name="s" placeholder="搜索书、笔记或人...">
					</form>
				</div>
			</div>
			<div class="col-25">
				<div class="row">
                    <!--
					<div class="col-33">
						<span class="nav_link"><a href="#">发现</a></span>
					</div>
                    -->
					<div class="col-33">
						<span class="nav_link"><a data-pjax href="/group">圈子</a></span>
					</div>
					<div class="col-33">
						<span id="nav_msg" class="nav_link"><a data-pjax
							href="/message">消息</a></span> <span id="nav_msg_cnt">0</span>
					</div>
				</div>
			</div>
			<div class="col-40">
				<!--user bar-->
				<div style="float: right">
					<a data-pjax href="<c:url value="/user/${curUserId}"/> ">
						<div id="nav_user">
							<div id="nav_photo">
								<img src="/static/images/photo/0.png">
							</div>
							<div id="nav_id">韩寒</div>
						</div>
					</a>
					<div id="nav_mail">
						<div id="nav_mail_icon">
							<a data-pjax href="/mail"><i class="icon-envelope-alt"
								style="margin-right: 5px"></i>私信</a>
						</div>
						<div id="nav_mail_cnt">0</div>
					</div>
					<div id="nav_logout">
						<a href="#"><i class="icon-signout" style="margin-right: 5px"></i>登出</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="height: 55px; width: 100%; position: relative"></div>

	<div id="main">
		<!--**xx-->
		<!--feed-->
		<div class="row">
			<div class="col-70">
				<div>
					<!--内容区-->
					<div id="feed_split">
						<i class="icon-bookmark-empty"></i>最新动态
					</div>


					<c:forEach var="message" items="${messageList}">
						<!--   MESSAGE_FOLLOWED_ASKQUESTION  -->
						<c:if test="${message.type==1 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 提出问题@
											<a href="<c:url value="/browse/${message.parentId}/feeds/1"/>">《${message.parentName}》</a>
										</div>
										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span>!-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_ANSWER_QUESTION  -->
						<c:if test="${message.type==2 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 回答问题
											<a href="<c:url value="/browse/${message.parentId}/feeds/1"/>"></a>
										</div>
										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_FAVORITE_QEUSTION  -->
						<c:if test="${message.type==3 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 赞同问题@
											<a href="<c:url value="/browse/${message.parentId}/feeds/1"/>">《${message.parentName}》</a>
										</div>
										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">

										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
									
											
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
									
											<div class="line show_comment" data-action="get_comment"
											data-url="<c:url value="/question/${message.relatedSourceId}/comments"/>"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_FAVORITE_ANSWER  -->
						<c:if test="${message.type==4}">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 赞同回答@
											<a href="<c:url value="/detail/${message.parentId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_FAVORITE_NOTE  -->
						<c:if test="${message.type==5 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 赞同笔记@
											<a href="<c:url value="/browse/${message.parentId}/feeds/1"/>">《${message.parentName}》</a>
										</div>
										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/detail/${message.parentId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
											<!-- 
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											 -->
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_ADDBOUGHT  -->
						<c:if test="${message.type==8}">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 添加书籍@
											<a
												href="<c:url value="/browse/${message.relatedSourceId}/feeds/1"/>">《${message.relatedSourceContent}》</a>
										</div>

									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.extraContent} 著<!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注书籍
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_ADDCIRCLE  -->
						<c:if test="${message.type==12 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 创建圈子
											<!-- 
											<a href="<c:url value="/group/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a>
											 -->
										</div>

										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/group/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a>
										</div>

									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.extraContent} <!--<span class="feed_show_full">完整显示</span>-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>加入圈子
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>退出圈子
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>
						<!--  MESSAGE_FOLLOWED_JOINCIRCLE  -->
						<c:if test="${message.type==13 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span>
											加入圈子&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<!-- 
											<a href="<c:url value="/group/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a>
											 -->
										</div>

										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/group/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a>
										</div>

									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.extraContent} <!--<span class="feed_show_full">完整显示</span> !-->
										</div>
										<div class="feed_tool_bar">
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>加入圈子
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>退出圈子
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
								<div class="feed_full clear_fix">
									<div class="feed_full_text"></div>
									<div style="width: 100%">
										<div class="feed_tool_bar">
											<div class="up" data-action="up" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<span class="up_icon"><i class="icon-thumbs-up"></i>赞</span>
												<span class="cnt up_cnt">31</span>
											</div>
											<div style="display: none" class="line cancel_up"
												data-action="cancel_up" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消赞<span class="cnt">32</span>
											</div>
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a href="/detail/11"><i class="icon-lightbulb"></i>2条其他回答</a>
											</div>
										</div>
										<div class="feed_pack_up" onclick="show_brief($(this))">
											<i class="icon-arrow-up"></i>收起
										</div>
									</div>
								</div>

								<div class="comments clear_fix">
									<div class="waiting">
										<i class="icon-spinner icon-spin"></i>
									</div>
									<div class="comments_list"></div>

									<div class="do_comment">
										<div class="comment clear_fix">
											<input type="text">
											<button class="btn btn-success">提交</button>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<!--   MESSAGE_FOLLOWED_ADDNOTE  -->
						<c:if test="${message.type==14 }">
							<div class="feeds">
								<div class="feed_meta clear_fix">
									<div class="feed_user_photo">
										<img src="<c:url value="${message.producerAvatarUrl}"/>">
									</div>
									<div class="feed_meta_word">
										<div class="feed_approve">
											<span class="feed_user">${message.producerName}</span> 添加笔记@
											<a href="<c:url value="/browse/${message.parentId}/feeds/1"/>">《${message.parentName}》</a>
										</div>
										<div class="feed_title">
											<a data-pjax
												href="<c:url value="/detail/${message.parentId}"/>">${message.extraContent}</a>
										</div>
									</div>
									<div class="feed_time">${message.strDate}</div>
								</div>
								<div class="feed_brief clear_fix">
									<div class="feed_text_wrap">
										<div class="feed_text" data-passage-id="123"
											onclick="show_full($(this))">
											${message.relatedSourceContent} <!--<span class="feed_show_full">完整显示</span> -->
										</div>
										<div class="feed_tool_bar">
											<!-- 
											<div class="line watch" data-action="watch"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-plus"></i>关注问题
											</div>
											<div style="display: none" class="line cancel_watch"
												data-action="cancel_watch" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-ok"></i>取消关注
											</div>
											 -->
											<div class="line show_comment" data-action="get_comment"
												data-passage-id="123" onclick="tool_bar_action($(this))">
												<i class="icon-comments-alt"></i>32条评论
											</div>
											<div style="display: none" class="line hide_comment"
												data-action="hide_comment" data-passage-id="123"
												onclick="tool_bar_action($(this))">
												<i class="icon-double-angle-up"></i>收起评论
											</div>
											<div class="line link" data-action="show_all_answer"
												data-passage-id="123">
												<a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>2个其他回答</a>
											</div>
										</div>
									</div>
									<div style="width: 2%; height: 1px; float: left"></div>
									<div class="feed_picture">
										<img src="<c:url value="${message.imageUrl}"/>">
									</div>
								</div>
								<div style="clear: both"></div>
							</div>
						</c:if>

					</c:forEach>

					<div style="text-align: center">
						<ul class="pagination">
							<li><a href="#">&laquo;</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">6</a></li>
							<li><a href="#">7</a></li>
							<li><a href="#">8</a></li>
							<li><a href="#">9</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-5"></div>
			<div class="col-25">
				<!--边栏-->
				<div id="my_books">
					<a data-pjax href="/user/1234"><i class="icon-book"></i>我的书籍
						&raquo;</a>
				</div>
				<div id="books_table">
					<c:forEach var="book" items="${bookList}" varStatus="status"
						step="1">
						<c:if test="${status.count%3==1 }">
							<div class="row">
								<div class="col-30">
									<a data-pjax href="<c:url value="/browse/${book.id}/feeds/1"/>"><img
										src="<c:url value="${book.imageUrl}"/>"
										style="max-width: 100%"></a>
								</div>
								<div class="col-5"></div>
						</c:if>
						<c:if test="${status.count%3==2 }">
							<div class="col-30">
								<a data-pjax href="<c:url value="/browse/${book.id}/feeds/1"/>"><img
									src="<c:url value="${book.imageUrl}"/>" style="max-width: 100%"></a>
							</div>
							<div class="col-5"></div>
						</c:if>
						<c:if test="${status.count%3==0 }">
							<div class="col-30">
								<a data-pjax href="<c:url value="/browse/${book.id}/feeds/1"/>"><img
									src="<c:url value="${book.imageUrl}"/>" style="max-width: 100%"></a>
							</div>
				</div>
				</c:if>

				</c:forEach>
				<c:if test="${fn:length(bookList)%3!=0 }">
			</div>
			</c:if>

		</div>
		<div id="script_box">
			<a data-pjax href="#"><i class="icon-save"></i>草稿箱（<span
				id="drafts_count">0</span>）</a>
		</div>
		<script>
			get_drafts_count();
		</script>
	</div>
	</div>

	<div id="templates">
		<div id="comment_template">
			<div class="row comment_border">
				<!-- {hack.e}
                    <div class="col-85">
                        <div class="comment clear_fix">
                            <div class="row">
                                <div class="col-8">
                                    <img src="{comment.photo_link}">
                                </div>
                                <div class="col-2"></div>
                                <div class="col-90">
                                    <div class="comment_user_id"><a href="#">{comment.user_name}</a></div>
                                    <div class="comment_text">{comment.message}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-15">
                        <div class="comment_right clear_fix">
                            <div class="comment_time">{comment.time}</div>
                            <div class="comment_reply"><a href="#"><i class="icon-reply"></i>回复</a></div>
                        </div>
                    </div>
                    {hack.s} -->
			</div>
		</div>
	</div>
	<!--xx**-->
	</div>
</body>
</html>