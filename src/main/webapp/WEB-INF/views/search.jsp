<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<!--显示顺序 书，人，笔记-->
		<div class="row">
			<div class="col-5"></div>
			<div class="col-90">
				<!--书籍-->
				<div class="search_part">
					<div class="search_split">
						<a href="#"><i class="icon-book"></i>相关书籍</a>
					</div>
					<div class="row">
						<c:forEach var="book" items="${bookList}">
							<div class="col-33">
								<div class="row">
									<div class="col-25">
										<a href="#"> <img
											src="<c:url value="${book.imageUrl}"/>">
										</a>
									</div>
									<div class="col-5"></div>
									<div class="col-65 cover_page">
										<div class="name" style="margin-bottom: 10px">
											<a href="<c:url value="/browse/${book.id}/feeds/1"/>">${book.bookName}</a>
										</div>
										<div class="info">
											作者：<span>[美] ${book.writer} </span>
										</div>
										<div class="info">
											出版：<span>${book.press}</span>
										</div>
										<div class="info">
											年份：<span>2007-9</span>
										</div>
									</div>
									<div class="col-5"></div>
								</div>
							</div>
						</c:forEach>
						<!--
						<div class="col-33">
							<div class="row">
								<div class="col-25">
									<a href="#"> <img src="/static/images/books/1.jpg">
									</a>
								</div>
								<div class="col-5"></div>
								<div class="col-65 cover_page">
									<div class="name" style="margin-bottom: 10px">
										<a href="#">灿烂千阳</a>
									</div>
									<div class="info">
										作者：<span>[美] 卡勒德·胡赛尼 </span>
									</div>
									<div class="info">
										出版：<span>上海人民出版社</span>
									</div>
									<div class="info">
										年份：<span>2007-9</span>
									</div>
								</div>
								<div class="col-5"></div>
							</div>
						</div>
						<div class="col-34">
							<div class="row">
								<div class="col-25">
									<a href="#"> <img src="/static/images/books/1.jpg">
									</a>
								</div>
								<div class="col-5"></div>
								<div class="col-65 cover_page">
									<div class="name" style="margin-bottom: 10px">
										<a href="#">灿烂千阳</a>
									</div>
									<div class="info">
										作者：<span>[美] 卡勒德·胡赛尼 </span>
									</div>
									<div class="info">
										出版：<span>上海人民出版社</span>
									</div>
									<div class="info">
										年份：<span>2007-9</span>
									</div>
								</div>
								<div class="col-5"></div>
							</div>
						</div>
						<div class="col-33">
							<div class="row">
								<div class="col-25">
									<a href="#"> <img src="/static/images/books/1.jpg">
									</a>
								</div>
								<div class="col-5"></div>
								<div class="col-65 cover_page">
									<div class="name" style="margin-bottom: 10px">
										<a href="#">灿烂千阳</a>
									</div>
									<div class="info">
										作者：<span>[美] 卡勒德·胡赛尼 </span>
									</div>
									<div class="info">
										出版：<span>上海人民出版社</span>
									</div>
									<div class="info">
										年份：<span>2007-9</span>
									</div>
								</div>
								<div class="col-5"></div>
							</div>
						</div>-->
					</div>
				</div>
				<!--用户-->
				<div class="search_part">
					<div class="search_split">
						<a href="#"><i class="icon-user"></i>成员</a>
					</div>

					<div class="row">
                        <c:forEach var="user" items="${userList}">
                            <div class="col-33">
                                <div class="row">
                                    <div class="col-15">
                                        <img src="<c:url value="${user.avatarUrl}"/>" class="user">
                                    </div>
                                    <div class="col-5"></div>
                                    <div class="col-75 user">
                                        <div class="name">
                                            <a href="<c:url value="/user/${user.id}"/>"">${user.username}</a>
                                        </div>
                                        <div>
                                            <span> <a href="#" class="gray">关注 ${user.followNum}</a></span>
                                            <span> <a href="#" class="gray">提问 ${user.questionNum}</a></span>
                                            <span> <a href="#" class="gray">回答 ${user.answerNum}</a></span>
                                            <span> <a href="#" class="gray">笔记 ${user.noteNum}</a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!--
						<div class="col-33">
							<div class="row">
								<div class="col-15">
									<img src="static/images/photo/2.jpg" class="user">
								</div>
								<div class="col-5"></div>
								<div class="col-75 user">
									<div class="name">
										<a href="#">王师傅</a>
									</div>
									<div>
										<span> <a href="#" class="gray">关注 3.1k</a>
										</span> <span> <a href="#" class="gray">提问 12</a>
										</span> <span> <a href="#" class="gray">回答 32</a>
										</span> <span> <a href="#" class="gray">笔记 64</a>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-34">
							<div class="row">
								<div class="col-15">
									<img src="static/images/photo/2.jpg" class="user">
								</div>
								<div class="col-5"></div>
								<div class="col-75 user">
									<div class="name">
										<a href="#">王师傅</a>
									</div>
									<div>
										<span> <a href="#" class="gray">关注 3.1k</a>
										</span> <span> <a href="#" class="gray">提问 12</a>
										</span> <span> <a href="#" class="gray">回答 32</a>
										</span> <span> <a href="#" class="gray">笔记 64</a>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-33">
							<div class="row">
								<div class="col-15">
									<img src="static/images/photo/2.jpg" class="user">
								</div>
								<div class="col-5"></div>
								<div class="col-75 user">
									<div class="name">
										<a href="#">王师傅</a>
									</div>
									<div>
										<span> <a href="#" class="gray">关注 3.1k</a>
										</span> <span> <a href="#" class="gray">提问 12</a>
										</span> <span> <a href="#" class="gray">回答 32</a>
										</span> <span> <a href="#" class="gray">笔记 64</a>
										</span>
									</div>
								</div>
							</div>
						</div>
                        -->
					</div>
				</div>
				<!--笔记/问答-->
				<div class="search_part">
					<div class="search_split">
						<a href="#"><i class="icon-lightbulb"></i>问题 · 笔记 · 附件</a>
					</div>
                     <!--
					<div class="feeds">
						<div class="browse_list_meta clear_fix">
							<div class="title">
								<a data-pjax href="/detail/777">历史上，有没有哪两个人真的算得上是一辈子的敌人？</a>
							</div>

						</div>
						<div class="browse_brief clear_fix">
							<div class="feed_text_wrap">
								<a data-pjax href="/detail/777">
									<div class="feed_text">爆个冷门：京剧界的梅程。
										梅兰芳是程砚秋正式磕过头的师父，程砚秋借助梅兰芳的提携，也在梅兰芳演出的天河配中出演过小宫女。但是这并不能说他们不会成为宿敌。程砚秋在罗瘿公辅助下，逐渐形成易于别派的程腔。
										梅派有太真外传，程派就有梅妃。梅派有凤还巢，程派就有锁麟囊。</div>
								</a>
							</div>
							<div style="width: 2%; height: 1px; float: left"></div>
							<div class="feed_picture">
								<img src="/static/images/content/1.png">
							</div>
						</div>
					</div>

					<div class="feeds">
						<div class="browse_list_meta clear_fix">
							<div class="title note">
								<i class="icon-edit"></i>笔记
							</div>
						</div>
						<div class="browse_brief clear_fix">
							<div class="feed_text_wrap">
								<a href="/">
									<div class="feed_text">
										我前段时间不远万里去塞舌尔参加了叉烧的婚礼（如果你们还记得他），酒过三巡，不，准确的说是酒过好多好多巡，在我的循循善诱下，丫终于透露了心声，最爱的还是朔姑娘。我这次有了经验，没有搂住他，刻意保持了一米的距离，语重心长的套用了一段话：

										那个人永远活在时间里了，你把她拉不出来，自己也回不去，就这样吧，让她安静的留在那里吧。...</div>
								</a>
							</div>
							<div style="width: 2%; height: 1px; float: left"></div>
							<div class="feed_picture">
								<img src="/static/images/content/1.png">
							</div>
						</div>
					</div>
                    -->
					<c:forEach var="feed" items="${feedList}">
							<c:if test="${feed.type ==1}">
								<div class="feeds">
									<div class="browse_list_meta clear_fix">
										<div class="title">
											<a data-pjax href="<c:url value="/detail/${feed.id}"/>">${feed.title}</a>
										</div>
						

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a data-pjax href="/detail/777">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
								
									</div>
									<div class="feed_tool_bar">
										<c:choose>
											<c:when test="${feed.follow}">
												<div style="display: none" class="line watch"
													data-action="watch" data-passage-id="${feed.id}"
													onclick="tool_bar_action($(this))">
													<i class="icon-plus"></i>关注问题
												</div>
												<div class="line cancel_watch" data-action="cancel_watch"
													data-passage-id="${feed.id}" onclick="tool_bar_action($(this))">
													<i class="icon-ok"></i>取消关注
												</div>
											</c:when>
											<c:otherwise>
												<div class="line watch" data-action="watch"
													data-passage-id="${feed.id}" onclick="tool_bar_action($(this))">
													<i class="icon-plus"></i>关注问题
												</div>
												<div style="display: none" class="line cancel_watch"
													data-action="cancel_watch" data-passage-id="${feed.id}"
													onclick="tool_bar_action($(this))">
													<i class="icon-ok"></i>取消关注
												</div>
											</c:otherwise>
										</c:choose>

										<div class="line show_comment" data-action="get_comment"
											data-url="<c:url value="/question/${feed.id}/comments"/>"
											data-passage-id="${book.id}"
											onclick="tool_bar_action($(this))">
											<i class="icon-comments-alt"></i><span id="comment_num">${feed.commentNum}</span>条评论
										</div>
										<div style="display: none" class="line hide_comment"
											data-action="hide_comment" data-passage-id="123"
											onclick="tool_bar_action($(this))">
											<i class="icon-double-angle-up"></i>收起评论
										</div>
										<div class="line" data-action="show_all_answer"
											data-passage-id="123">
											<i class="icon-lightbulb" onclick="tool_bar_action($(this))"></i>${feed.answerNum}个回答
										</div>
										<span class="time">${feed.strDate}</span>
									</div>
									<div style="clear: both"></div>
									<div class="comments clear_fix">
										<div class="waiting">
											<i class="icon-spinner icon-spin"></i>
										</div>
										<div class="comments_list"></div>

										<div class="do_comment">
											<div class="comment clear_fix">
												<input id="content" type="text">
												<button class="btn btn-success" onclick="add_comment($(this),${feed.id},'question')">提交</button>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${feed.type ==2}">
								<div class="feeds">
									<div class="browse_list_meta clear_fix">
										<div class="title note">
											<i class="icon-edit"></i>笔记
										</div>
									

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a href="/">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
									
									</div>
									<div class="feed_tool_bar">

										<div class="line show_comment" data-action="get_comment"
											data-url="<c:url value="/note/${feed.id}/comments"/>"
											data-passage-id="${book.id}"
											onclick="tool_bar_action($(this))">
											<i class="icon-comments-alt"></i><span id="comment_num">${feed.commentNum}</span>条评论
										</div>
										<div style="display: none" class="line hide_comment"
											data-action="hide_comment" data-passage-id="123"
											onclick="tool_bar_action($(this))">
											<i class="icon-double-angle-up"></i>收起评论
										</div>
										<span class="time">${feed.strDate}</span>
									</div>
									<div style="clear: both"></div>
									<div class="comments clear_fix">
										<div class="waiting">
											<i class="icon-spinner icon-spin"></i>
										</div>
										<div class="comments_list"></div>

										<div class="do_comment">
											<div class="comment clear_fix">
												<input id="content" type="text">
												<button class="btn btn-success" onclick="add_comment($(this),${feed.id},'note')">提交</button>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${feed.type ==3}">
								<div class="feeds">
									<div class="browse_list_meta clear_fix">
										<div class="title note">
											<i class="icon-edit"></i>附件
										</div>
										

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a href="/">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
									
									</div>
									<div class="feed_tool_bar">

										<div class="line show_comment" data-action="get_comment"
											data-url="<c:url value="/note/${feed.id}/comments"/>"
											data-passage-id="${book.id}"
											onclick="tool_bar_action($(this))">
											<i class="icon-comments-alt"></i><span id="comment_num">${feed.commentNum}</span>条评论
										</div>
										<div style="display: none" class="line hide_comment"
											data-action="hide_comment" data-passage-id="123"
											onclick="tool_bar_action($(this))">
											<i class="icon-double-angle-up"></i>收起评论
										</div>
										<span class="time">${feed.strDate}</span>
									</div>
									<div style="clear: both"></div>
									<div class="comments clear_fix">
										<div class="waiting">
											<i class="icon-spinner icon-spin"></i>
										</div>
										<div class="comments_list"></div>

										<div class="do_comment">
											<div class="comment clear_fix">
												<input id="content" type="text">
												<button class="btn btn-success" onclick="add_comment($(this),${feed.id},'note')">提交</button>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						
					</c:forEach>
				</div>
				<div class="col-5"></div>
			</div>

			<!--xx**-->
		</div>
</body>
</html>