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
						<span id="go_index"><a data-pjax href="/">Picker</a></span>
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
					<div class="col-33">
						<span class="nav_link"><a href="#">发现</a></span>
					</div>
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
					<a data-pjax href="/user/1234">
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

		<!-- *** change here *** -->
		<!-- 发送私信面板 -->
		<div id="create_group_panel" class="shadow">
			<div class="title clear_fix">
				<div style="float: left">创建圈子</div>
				<div class="point_cursor"
					onclick="hide_panel($('#create_group_panel'), $('#cancel_create_group'))"
					style="float: right">
					<i class="icon-remove"></i>
				</div>
			</div>

			<div class="content">
				<input type="text" placeholder="圈子名称" id="group_name">
				<textarea id="group_describe" placeholder="圈子描述" rows="5"></textarea>
				<input type="button"
					style="float: right; padding: 5px; margin-top: 10px" value="创建"
					onclick="create_group()" />
			</div>
		</div>
		<div id="cancel_create_group" class="cancel_panel"
			onclick="hide_panel($('#create_group_panel'), $(this))"></div>
		<!-- *** change end *** -->

		<div id="groups_content">
			<div id="groups_content_head" class="clear_fix">
				<div class="title">我的圈子</div>
				<div class="show_up">
					<a href="javascript:void(0);" onclick="show_search_bar();"><i
						class="icon-search"></i>加入</a>
				</div>

				<div class="search">
					<form class="group_search" onsubmit="return group_search($(this))">
						<input name="name" placeholder="圈子名称">
						<button type="submit" class="btn btn-success">查找</button>
					</form>
				</div>

				<div class="create">
					<a href="javascript:void(0);" onclick="show_create_group();"><i
						class="icon-bullhorn"></i>创建</a>
				</div>
			</div>
			<div id="group_search_content">
				<!-- 
                <div class="group_info">
                    <div class="group_name"><a data-pjax href="/group/1234">大刘粉</a></div>
                    <div class="group_intro">读完《三体》去写两万字读后感。</div>
                    <div class="group_bar">
                        <span class="left">777位成员</span>
                        <span class="left">2013-7-11加入</span>
                        <span class="time">创建于2012-7-12</span>
                    </div>
                </div>
                <div class="group_info">
                    <div class="group_name"><a data-pjax href="/group/1234">红楼研究</a></div>
                    <div class="group_intro">世人都晓神仙好，惟有功名忘不了！古今将相在何方？荒冢一堆草没了。世人都晓神仙好，只有金银忘不了！终朝只恨聚无多，及到多时眼闭了。</div>
                    <div class="group_bar">
                        <span class="left">123位成员</span>
                        <span class="left">2013-7-1加入</span>
                        <span class="time">创建于2012-7-22</span>
                    </div>
                </div>
             -->
				<c:forEach var="circle" items="${circleList}">
					<div class="group_info">
						<div class="group_name">
							<a data-pjax href=<c:url value="/group/${circle.id}"/>><c:out
									value='${circle.name}' /></a>
						</div>
						<div class="group_intro">
							<c:out value='${circle.describe}' />
						</div>
						<div class="group_bar">
							<span class="left"><c:out value='${circle.memberNum}' />位成员</span>
							<span class="left"><c:out value='${circle.memberJoinTime}' />加入</span>
							<span class="time">创建于<c:out
									value='${circle.strDate}' /></span>
						</div>
					</div>
				</c:forEach>
			</div>
			<script>
				function show_search_bar() {
					$('#groups_content').find('.show_up').hide();
					$('#groups_content').find('.search').fadeIn();
				}
			</script>
			<!--
            <div>
                <form class="group_search" onsubmit="return group_search($(this))">
                    <input name="name" placeholder="圈子名称">
                    <button type="submit" class="btn btn-success">查找</button>
                </form>
            </div>
            <div id="group_search_content">
            </div>
            -->
		</div>

		<div id="templates">
			<div id="group_info_template">
				<div class="group_info">
					<!-- {hack.e}
                    <div class="group_name"><a href="{group.link}">{group.name}</a></div>
                    <div class="group_intro">{group.intro}</div>
                    <div class="group_bar">
                        <span class="left">{group.number_of_people}位成员</span>
                        <span class="left"><a href="{group.join_link}">+申请加入</a></span>
                        <span class="time">创建于{group.create_date}</span>
                    </div>
                    {hack.s} -->
				</div>
			</div>
		</div>
		<!--xx**-->
	</div>
</body>
</html>