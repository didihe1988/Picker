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
                        <label for="search" style="display: none">搜索</label>
                        <input type="text" id="search" name="s" placeholder="搜索书、笔记或人...">
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
		<div id="message_container">
			<div class="back_nav clear_fix">
				<!--<a data-pjax href="/">-->
				<div class="go_back" onclick="go_back()">
					<i class="icon-circle-arrow-left"></i>返回上一页
				</div>
				<!--</a>-->
			</div>

			<div id="message_list">
				<c:forEach var="message" items="${messageList}">
					<c:choose>
						<c:when test="${message.type==18 }">
							<div class="message clear_fix">
								<div style="width: 40px">
									<img src="<c:url value="${message.producerAvatarUrl}"/>">
								</div>
								<div style="width: 860px">
									<span><a
										href="<c:url value="/user/${message.producerId}"/>">${message.producerName}</a></span>
									<span>回答了你的问题</span> <span><a
										href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a></span>
								</div>
							</div>
						</c:when>
						<c:when test="${message.type==19 }">
							<div class="message clear_fix">
								<div style="width: 40px">
									<img src="<c:url value="${message.producerAvatarUrl}"/>">
								</div>
								<div style="width: 860px">
									<span><a
										href="<c:url value="/user/${message.producerId}"/>">${message.producerName}</a></span>
									<span>赞了你的问题</span> <span><a
										href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a></span>
								</div>
							</div>
						</c:when>
						<c:when test="${message.type==20}">
							<div class="message clear_fix">
								<div style="width: 40px">
									<img src="<c:url value="${message.producerAvatarUrl}"/>">
								</div>
								<div style="width: 860px">
									<span><a
										href="<c:url value="/user/${message.producerId}"/>">${message.producerName}</a></span>
									<span>赞了你的答案</span> <span><a
										href="<c:url value="/detail/${message.relatedSourceId}"/>">${message.relatedSourceContent}</a></span>
								</div>
							</div>
						</c:when>
						<c:when test="${message.type==22}">
							<div class="message clear_fix">
								<div style="width: 40px">
									<img src="<c:url value="${message.producerAvatarUrl}"/>">
								</div>
								<div style="width: 860px">
									<span><a
										href="<c:url value="/user/${message.producerId}"/>">${message.producerName}</a></span>
									<span>赞了你的笔记</span> <span><a href="#">${message.relatedSourceContent}</a></span>
								</div>
							</div>
						</c:when>

						<c:when test="${message.type==25 }">
							<div class="message clear_fix">
								<div style="width: 40px">
									<img src="<c:url value="${message.producerAvatarUrl}"/>">
								</div>
								<div style="width: 860px">
									<span><a
										href="<c:url value="/user/${message.producerId}"/>">${message.producerName}</a></span>
									<span>关注了你</span>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<!--xx**-->
	</div>
</body>
</html>