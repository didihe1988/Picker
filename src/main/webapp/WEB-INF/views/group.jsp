﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</head>

<!--TODO: AJAX取消息-->
<body>
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
		<div id="group_content">
			<div id="group_title">
				<c:out value='${circle.name}' />
				:
			</div>
			<div id="group_intro">
				<!--  
				世人都晓神仙好，惟有功名忘不了！<br />古今将相在何方？荒冢一堆草没了。<br />世人都晓神仙好，只有金银忘不了！<br />终朝只恨聚无多，及到多时眼闭了。<br />-->
				<c:out value='${circle.describe}' />
			</div>
			<div class="peoples">
				<!-- people 1 -->
				<!--  
				<div class="people">
					<div class="row">
						<div class="col-6 photo">
							<img src="/static/images/photo/1.jpg">
						</div>
						<div class="col-76">
							<div class="people_name">
								<span><a href="#">门卫</a></span> <span>, 裁判主宰了比赛</span>
							</div>
							<div class="people_labels">
								<i class="icon-tags"></i> <span>电子科技大学</span> <span>实验班</span> <span>作家协会</span>
							</div>
						</div>
						<div class="col-18">
							<div class="people_follow">
								<div class="follow_action" style="float: right"
									onclick="do_follow($(this))">
									<i class="icon-plus"></i> 加关注
								</div>
								<div class="cancel_follow" style="float: right; display: none"
									onclick="cancel_follow($(this))">
									<i class="icon-ok"></i> 取消关注
								</div>
							</div>
						</div>
					</div>
				</div>-->
				<c:forEach var="user" items="${userList}">
					<div class="people">
						<div class="row">
							<div class="col-6 photo">
								<img src="<c:url value="${user.avatarUrl}"/>">
							</div>
							<div class="col-76">
								<div class="people_name">
									<span><a href="<c:url value="/user/${user.id}"/>"><c:out
												value='${user.username}' /></a></span> <span>,<c:out
											value='${user.signature}' /></span>
								</div>
								<div class="people_labels">
									<i class="icon-tags"></i>
									<!-- 
									<span>电子科技大学</span> 
									<span>实验班</span>
									<span>作家协会</span>
									-->
									<c:forEach var="circle" items="${user.circleList}">
										<span>${circle.name}</span>
									</c:forEach>
								</div>
							</div>
							<div class="col-18">
								<div class="people_follow">
									<c:choose>
										<c:when test="${user.id ==curUserId}">
										</c:when>
										<c:when test="${user.follow == false}">
											<div class="follow_action" style="float: right"
												onclick="do_follow($(this),${user.id})">
												<i class="icon-plus"></i> 加关注
											</div>
											<div class="cancel_follow"
												style="float: right; display: none"
												onclick="cancel_follow($(this),${user.id})">
												<i class="icon-ok"></i> 取消关注
											</div>
										</c:when>
										<c:otherwise>
											<div class="follow_action"
												style="float: right; display: none"
												onclick="do_follow($(this),${user.id})">
												<i class="icon-plus"></i> 加关注
											</div>
											<div class="cancel_follow" style="float: right"
												onclick="cancel_follow($(this),${user.id})">
												<i class="icon-ok"></i> 取消关注
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-18">
									<!-- 
                            <div class="people_follow">
                                <div class="follow_action" style="float: right" onclick="do_follow($(this))">
                                    <i class="icon-plus"></i>加关注
                                </div>
                                <div class="cancel_follow" style="float: right;display: none" onclick="cancel_follow($(this))">
                                    <i class="icon-ok"></i> 取消关注
                                </div>
                            </div>
                             -->
								</div>
							</div>

						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!--xx**-->
	</div>
</body>
</html>