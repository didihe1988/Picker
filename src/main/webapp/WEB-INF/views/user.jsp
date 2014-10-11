<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<span class="nav_link"><a data-pjax
							href=<c:url value="/group"/>>圈子</a></span>
					</div>
					<div class="col-33">
						<span id="nav_msg" class="nav_link"><a data-pjax
							href=<c:url value="/message"/>>消息</a></span> <span
							id="nav_msg_cnt">0</span>
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
							<div id="nav_id">韩寒</div>
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
		<div id="receiver_id" data-value="${user.id}" style="display: none"></div>
		<!-- 发送私信面板 -->
		<div id="message_panel" class="shadow">
			<div class="title clear_fix">
				<div style="float: left">发送私信</div>
				<div class="point_cursor"
					onclick="hide_panel($('#message_panel'), $('#cancel_message_panel'))"
					style="float: right">
					<i class="icon-remove"></i>
				</div>
			</div>

			<div class="content">
				<textarea id="message_content" placeholder="消息" rows="5"></textarea>
				<input type="button"
					style="float: right; padding: 5px; margin-top: 10px" value="发送"
					onclick="send_message($('#receiver_id').data('value'))" />
			</div>
		</div>
		<div id="cancel_message_panel" class="cancel_panel"
			onclick="hide_panel($('#message_panel'), $(this))"></div>
		<!-- *** change end *** -->

		<!--为 提问/回答/笔记 提供url定位-->
		<div id="user_home_url" data-value="/user/${user.id}"
			style="display: none"></div>
		<div id="notes_url" data-value="/user/${user.id}/notes/1"
			style="display: none"></div>
		<div id="answers_url" data-value="/user/${user.id}/answers/1"
			style="display: none"></div>
		<div id="questions_url" data-value="/user/${user.id}/questions/1"
			style="display: none"></div>
		<!--end-->

		<div class="row" style="margin-top: 10px">
			<div class="col-21">
				<div id="user_photo">
					<!--<img src="/static/images/big_photo/0.png">-->
					<img src="<c:url value="${user.avatarUrl}"/>" />
				</div>
				<c:choose>
					<c:when test="${(user.id!=curUserId)&&(user.follow==false)}">
						<div id="user_action">
							<div class="row">
								<!-- *** change here *** -->
								<div class="col-50" onclick="show_message_panel()">
									<div class="split">
										<i class="icon-envelope-alt"></i>发私信
									</div>
								</div>
								<!-- *** change end *** -->

								<div class="col-50">
									<div class="follow_action" onclick="do_follow($(this))">
										<i class="icon-plus"></i>加关注
									</div>
									<div class="cancel_follow" style="display: none"
										onclick="cancel_follow($(this))">
										<i class="icon-ok"></i>取消关注
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${(user.id!=curUserId)&&(user.follow==true)}">
						<div id="user_action">
							<div class="row">
								<!-- *** change here *** -->
								<div class="col-50" onclick="show_message_panel()">
									<div class="split">
										<i class="icon-envelope-alt"></i>发私信
									</div>
								</div>
								<!-- *** change end *** -->

								<div class="col-50">
									<div class="follow_action" onclick="do_follow($(this))"
										style="display: none">
										<i class="icon-plus"></i>加关注
									</div>
									<div class="cancel_follow" onclick="cancel_follow($(this))">
										<i class="icon-ok"></i>取消关注
									</div>
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
				<div id="labels">
					<div class="info">
						<span>加入<c:out value='${user.circleNum}' />个圈子
						</span> <span class="show_all" data-tab="search_label"
							onclick="panel_action($(this))"><i class="icon-search"></i></span>
					</div>
					<!-- 
					<span class="label"><a data-pjax href="/group/1234">一个</a></span> <span
						class="label"><a data-pjax href="/group/1234">电子科技大学</a></span> <span
						class="label"><a data-pjax href="/group/1234">实验班</a></span> <span
						class="label"><a data-pjax href="/group/1234">作家协会</a></span> <span
						class="label"><a data-pjax href="/group/1234">x年x班</a></span> -->
					<c:forEach var="circle" items="${circleList}">
						<span class="label"><a data-pjax
							href="<c:url value="/group/${circle.id}"/>"><c:out
									value='${circle.name}' /></a></span>
					</c:forEach>
				</div>
				<div class="follow">
					<div class="info">
						<span>关注<c:out value='${user.followOthersNum}' /> 人
						</span> <span class="show_all" data-tab="follow"
							onclick="panel_action($(this))"><i class="icon-list-ul"></i></span>
					</div>
					<div style="height: 35px; overflow: hidden">
						<c:forEach var="user" items="${followeeList}">
							<span><img src="<c:url value="${user.avatarUrl}"/>" /></span>
						</c:forEach>
					</div>
				</div>
				<div class="follow">
					<div class="info">
						<span>被 <c:out value='${user.followNum}' />人关注
						</span> <span class="show_all" data-tab="followed"
							onclick="panel_action($(this))"><i class="icon-list-ul"></i></span>
						<!--<span class="show_all"><a href="#">All»</a></span>-->
					</div>
					<div style="height: 35px; overflow: hidden;">
						<c:forEach var="user" items="${followerList}">
							<span><img src="<c:url value="${user.avatarUrl}"/>" /></span>
						</c:forEach>
					</div>
				</div>
				<div id="count" class="row">
					<div class="col-33 box split" data-tab="questions"
						onclick="panel_action($(this))">
						<div class="count_num">
							<c:out value='${user.questionNum}' />
						</div>
						<div class="count_word">提问</div>
					</div>
					<div class="col-33 box split" data-tab="answers"
						onclick="panel_action($(this))">
						<div class="count_num">
							<c:out value='${user.answerNum}' />
						</div>
						<div class="count_word">回答</div>
					</div>
					<div class="col-33 box" data-tab="notes"
						onclick="panel_action($(this))">
						<div class="count_num">
							<c:out value='${user.noteNum}' />
						</div>
						<div class="count_word">笔记</div>
					</div>
				</div>
			</div>
			<div class="col-2"></div>
			<div class="col-76" style="overflow: hidden">
				<div id="user_name">
					<div style="float: left;">
						<span class="name"><c:out value='${user.username}' /></span> <span
							class="signature">, <c:out value='${user.signature}' /></span>
					</div>
					<div class="joined_day">
						<span>Joined on <c:out value='${user.registerTime}' /></span>
					</div>
				</div>

				<!-- 默认显示界面 -->
				<div id="home_panel" class="content_panel">
					<div id="my_books">
						<ul>
							<c:forEach var="book" items="${bookList}">
								<li><a data-pjax href=<c:url value="/browse/${book.id}/0"/>><img
										src=<c:url value="${book.imageUrl}"/>></a></li>
							</c:forEach>
						</ul>
						<div style="height: 2rem">
							<div id="detail">
								<i class="icon-hand-right" style="margin-right: 5px"></i>共标记
								<c:out value='${user.bookNum}' />
								本
							</div>
						</div>
					</div>

					<div id="user_feeds">
						<div class="user_feed">
							<div>
								<span class="user_feed_label">添加了回答 @ </span> <span
									class="user_feed_book">《魔龙的狂舞》</span> <span class="time">1天前</span>
							</div>
							<div style="margin-top: 5px">
								<div class="feed_question_title">
									<a data-pjax href="/detail/777">千面神是不是真正的神祗？</a>
								</div>
								<div class="user_feed_brief"
									onclick="user_feed_show_full($(this))">红神只是千面之神的一面，当时贾昆等三人是被艾莉亚从火上救回，因而贾大人才说欠以火为代表的红神三命嘛，关于红神不是有句话说“唯死亡方能换取生命”嘛，个人理解呢。。</div>
								<div class="user_feed_all">
									红神只是千面之神的一面，当时贾昆等三人是被艾莉亚从火上救回，因而贾大人才说欠以火为代表的红神三命嘛，关于红神不是有句话说“唯死亡方能换取生命”嘛，个人理解呢。。
									<div class="feed_all_roll_up" style="text-align: right"
										onclick="user_feed_show_brief($(this))">
										<a href="javascript: void(0)">收起</a>
									</div>
								</div>
							</div>
						</div>

						<div class="user_feed">
							<div>
								<span class="user_feed_label">>添加了笔记 @ </span> <span
									class="user_feed_book">《灿烂千阳》</span> <span class="time">1天前</span>
							</div>
							<div style="margin-top: 5px">
								<!--<div class="feed_question_title"><a href="#">åé¢ç¥æ¯ä¸æ¯çæ­£çç¥ç¥ï¼</a></div>-->
								<div class="user_feed_brief"
									onclick="user_feed_show_full($(this))">私生女玛丽雅姆在父亲的宅院门口苦苦守候，回到家却看到因绝望而上吊自杀的母亲。那天是她十五岁的生日，而童年嘎然而止。玛丽雅姆随后由父亲安排远嫁喀布尔四十多岁的鞋匠拉希德，几经流产，终因无法生子而长期生活在家暴阴影之下。

									十八年后，少女莱拉的父母死于战火，青梅竹马的恋人也在战乱中失踪，举目无亲的莱拉别无选择，被迫嫁给拉希德。...</div>
								<div class="user_feed_all">
									私生女玛丽雅姆在父亲的宅院门口苦苦守候，回到家却看到因绝望而上吊自杀的母亲。那天是她十五岁的生日，而童年嘎然而止。玛丽雅姆随后由父亲安排远嫁喀布尔四十多岁的鞋匠拉希德，几经流产，终因无法生子而长期生活在家暴阴影之下。

									十八年后，少女莱拉的父母死于战火，青梅竹马的恋人也在战乱中失踪，举目无亲的莱拉别无选择，被迫嫁给拉希德。两名阿富汗女性各自带着属于不同时代的悲惨回忆，共同经受着战乱、贫困与家庭暴力的重压，心底潜藏着的悲苦与忍耐相互交织，让她们曾经水火不容，又让她们缔结情谊，如母女般相濡以沫。然而，多年的骗局终有被揭穿的一天……

									她们将做出如何的选择？她们的命运又将何去何从？

									关于不可宽恕的时代，不可能的友谊以及不可毁灭的爱。《灿烂千阳》再次以阿富汗战乱为背景，时空跨越三十年，用细腻感人的笔触描绘了阿富汗旧家族制度下苦苦挣扎的妇女，她们所怀抱的希望、爱情...
									<div class="feed_all_roll_up" style="text-align: right"
										onclick="user_feed_show_brief($(this))">
										<a href="javascript: void(0)">收起</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 我关注的人 -->
				<div id="follow_panel" class="content_panel" style="display: none">
					<div class="back_nav clear_fix">
						<div class="go_back" onclick="panel_action($(this))">
							<i class="icon-circle-arrow-left"></i> 返回
						</div>
					</div>
					<div class="peoples">
						<c:forEach var="user" items="${followeeList}">
							<div class="people">
								<div class="row">
									<div class="col-8">
										<img src="<c:url value="${user.avatarUrl}"/>">
									</div>
									<div class="col-74">
										<div class="people_name">
											<span><a href="<c:url value="/user/${user.id}"/>"><c:out
														value="${user.username}" /></a></span> <span>,<c:out
													value="${user.signature}" /></span>
										</div>
										<div class="people_labels">
											<i class="icon-tags"></i> <span>考试团</span> <span>斧头帮</span> <span>FFF</span>
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
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<!-- 关注我的人 -->
				<div id="followed_panel" class="content_panel" style="display: none">
					<div class="back_nav clear_fix">
						<div class="go_back" onclick="panel_action($(this))">
							<i class="icon-circle-arrow-left"></i>返回主页
						</div>
					</div>
					<div class="peoples">
						<c:forEach var="user" items="${followerList}">
							<div class="people">
								<div class="row">
									<div class="col-8">
										<img src="<c:url value="${user.avatarUrl}"/>">
									</div>
									<div class="col-74">
										<div class="people_name">
											<span><a href="<c:url value="/user/${user.id}"/>"><c:out
														value="${user.username}" /></a></span> <span>,<c:out
													value="${user.signature}" /></span>
										</div>
										<div class="people_labels">
											<i class="icon-tags"></i> <span>考试团</span> <span>斧头帮</span> <span>FFF</span>
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
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<!-- 圈子搜索 -->
				<div id="group_search_panel" class="content_panel"
					style="display: none">
					<div>
						<form class="group_search" onsubmit="return group_search($(this))">
							<input name="name" placeholder="圈子名称">
							<button type="submit" class="btn btn-success">查找</button>
						</form>
					</div>
					<div id="group_search_content"></div>
				</div>


				<div id="notes_panel" class="content_panel" style="display: none">
					<div class="back_nav clear_fix">
						<div class="go_back" onclick="panel_action($(this))">
							<i class="icon-circle-arrow-left"></i> 返回
						</div>
					</div>

					<!--动态加载-->
					<div class="content" data-current_page="0">
						<!-- 加载圈 -->
						<div class="wait">
							<i class="icon-spinner icon-spin"></i>
						</div>
					</div>
				</div>

				<div id="questions_panel" class="content_panel"
					style="display: none">
					<div class="back_nav clear_fix">
						<div class="go_back" onclick="panel_action($(this))">
							<i class="icon-circle-arrow-left"></i> 返回
						</div>
					</div>

					<!--动态加载-->
					<div class="content" data-current_page="0">
						<!-- 加载圈 -->
						<div class="wait">
							<i class="icon-spinner icon-spin"></i>
						</div>
					</div>
				</div>

				<div id="answers_panel" class="content_panel" style="display: none">
					<div class="back_nav clear_fix">
						<div class="go_back" onclick="panel_action($(this))">
							<i class="icon-circle-arrow-left"></i> 返回
						</div>
					</div>

					<!--动态加载-->
					<div class="content" data-current_page="0">
						<!-- 加载圈 -->
						<div class="wait">
							<i class="icon-spinner icon-spin"></i>
						</div>
					</div>
				</div>
			</div>
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

			<div id="questions_feed_template">
				<div class="feeds">
					<!-- {hack.e}
                    <div class="browse_list_meta clear_fix">
                        <div class="title">
                            <a data-pjax href="{q.link}">{q.title}</a>
                        </div>
                    </div>
                    <div class="feed_tool_bar" style="margin-left: -15px">
                        <div class="line show_comment" data-action="get_comment"><i class="icon-comments-alt"></i>{q.comment_cnt}条评论</div>
                        <div class="line link" data-action="show_all_answer"><a data-pjax href="/detail/11"><i class="icon-lightbulb"></i>{q.answers_cnt}个回答</a></div>
                    </div>
                    <div class="time">{q.time}</div>
                    <div style="clear: both"></div>
                    {hack.s} -->
				</div>
			</div>

			<div id="answers_feed_with_no_picture_template">
				<div class="feeds">
					<!-- {hack.e}
                    <div class="browse_list_meta clear_fix">
                        <div class="title">
                            <a data-pjax href="{q.link}">{q.title}</a>
                        </div>
                        <span class="time">{q.time}</span>
                    </div>
                    <div style="clear: both"></div>
                    <div class="browse_brief clear_fix">
                        <a data-pjax href="{q.link}">
                            <div class="feed_text">{q.brief}</div>
                        </a>
                    </div>
                    {hack.s} -->
				</div>
			</div>

			<div id="answers_feed_with_picture_template">
				<div class="feeds">
					<!-- {hack.e}
                    <div class="browse_list_meta clear_fix">
                        <div class="title">
                            <a data-pjax href="{q.link}">{q.title}</a>
                        </div>
                        <span class="time">{q.time}</span>
                    </div>
                    <div style="clear: both"></div>
                    <div class="browse_brief clear_fix">
                        <div class="feed_text_wrap">
                            <a data-pjax href="{q.link}">
                                <div class="feed_text">{q.brief}</div>
                            </a>
                        </div>
                        <div style="width: 2%; height: 1px; float: left"></div>
                        <div class="feed_picture">
                            <img src="{q.picture}">
                        </div>
                    </div>
                    {hack.s} -->
				</div>
			</div>

			<div id="notes_feed_with_picture_template">
				<div class="feeds">
					<!-- {hack.e}
                    <div class="browse_list_meta clear_fix">
                        <div class="title note">
                            <i class="icon-edit"></i>笔记：<a data-pjax href="/detail/11"><span class="text">{q.title}</span></a>
                        </div>
                    </div>
                    <div class="browse_brief clear_fix">
                        <div class="feed_text_wrap">
                            <a href="{q.link}">
                                <div class="feed_text">{q.brief}</div>
                            </a>
                        </div>
                        <div style="width: 2%; height: 1px; float: left"></div>
                        <div class="feed_picture">
                            <img src="{q.picture}">
                        </div>
                    </div>
                    <div class="time">{q.time}</div>
                    <div style="clear: both"></div>
                    {hack.s} -->
				</div>
			</div>

			<div id="notes_feed_with_no_picture_template">
				<div class="feeds">
					<!-- {hack.e}
                    <div class="browse_list_meta clear_fix">
                        <div class="title note">
                            <i class="icon-edit"></i>笔记：<a data-pjax href="/detail/11"><span class="text">{q.title}</span></a>
                        </div>
                    </div>

                    <div class="browse_brief clear_fix">
                        <div>
                            <a href="{q.link}">
                                <div class="feed_text">{q.brief}</div>
                            </a>
                        </div>
                    </div>
                    <div class="time">{q.time}</div>
                    <div style="clear: both"></div>
                    {hack.s} -->
				</div>
			</div>

			<div id="pagination_template">
				<div style="text-align: center">
					<!-- {hack.e}
                    <ul class="pagination">
                      {pagination.pre()}
                      {pagination.paging()}
                      {pagination.next()}
                    </ul>
                    {hack.s} -->
				</div>
			</div>
		</div>

		<script>
			//第一个显示界面是home_panel
			set_init_panel($('#home_panel'));
		</script>
		<!--xx**-->
	</div>
</body>
</html>