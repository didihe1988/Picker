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
<script src="/static/js/ajaxfileupload.js"></script>

</head>

<!--TODO: AJAX取消息-->
<body onload="get_inventory();">
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
					<label for="search" style="display: none">搜索</label> <input
						type="text" id="search" name="search" placeholder="搜索书、笔记或人...">
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
		<div id="go_to_page_panel" class="shadow">
			<div class="title clear_fix">
				<div style="float: left">页数跳转</div>
				<div class="point_cursor"
					onclick="hide_panel($('#go_to_page_panel'), $('#cancel_page_jump'))"
					style="float: right">
					<i class="icon-remove"></i>
				</div>
			</div>

			<div class="content">
				当前在<span
					style="margin: 0 5px; font-family: 'georgia'; color: #99CCCC; font-size: 2rem;"
					id="current_page"></span>页，跳转至 <input type="text" name="to_page"
					id="to_page" style="width: 40px" /> <input type="button"
					value="Go"
					onclick="go_to_page($(this).parent().find('input[type=text]').val())" />
			</div>
		</div>
		<div id="cancel_page_jump" class="cancel_panel"
			onclick="hide_panel($('#go_to_page_panel'), $(this))"></div>

		<div class="row">
			<div class="col-70">
				<div id="load_previous_flag" data-start-page="0" data-end="false"
					data-lock="false" data-direction="up">
					<i class="icon-spinner icon-spin"></i>
				</div>


				<div id="pages_container">
					<c:forEach var="feed" items="${feedList}">

						<div class="page" data-page="${feed.page}">
							<c:if test="${feed.page !=curPage}">
								<div class="page_split">
									<div>
										<div>Page.${feed.page}</div>
									</div>
								</div>
							</c:if>
							<c:set var="curPage" value="${feed.page}" />
							<c:if test="${feed.type ==1}">
								<div class="feeds">
									<div class="browse_list_meta clear_fix">
										<div class="title">
											<a data-pjax href="<c:url value="/detail/${feed.id}"/>">${feed.title}</a>
										</div>
										<div class="photo">
											<img src=<c:url value="${feed.userAvatarUrl}"/>>
										</div>

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a data-pjax href="/detail/777">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
										<div class="feed_picture">
											<img src="/static/images/content/1.png">
										</div>
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
										<div class="photo">
											<img src=<c:url value="${feed.userAvatarUrl}"/>>
										</div>

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a href="/">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
										<div class="feed_picture">
											<img src="/static/images/content/1.png">
										</div>
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
										<div class="photo">
											<img src=<c:url value="${feed.userAvatarUrl}"/>>
										</div>

									</div>
									<div class="browse_brief clear_fix">
										<div class="feed_text_wrap">
											<a href="/">
												<div class="feed_text">${feed.content}</div>
											</a>
										</div>
										<div style="width: 2%; height: 1px; float: left"></div>
										<div class="feed_picture">
											<img src="/static/images/content/1.png">
										</div>
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
						</div>
					</c:forEach>
				</div>

				<div id="load_next_flag" data-start-page="110" data-end="false"
					data-lock="false" data-direction="down">
					<i class="icon-spinner icon-spin"></i>
				</div>

			</div>
			<div class="col-3"></div>
			<div class="col-27">
				<div class="browse_side_bar">
					<div class="row">
						<div class="col-20">
							<img src=<c:url value="${book.imageUrl}"/> style="width: 100%">
						</div>
						<div class="col-4"></div>
						<div class="col-25">
							<div id="book_meta" data-book-id="${book.id}">
								<div class="book_info">
									<a href="#">${book.bookName}</a>
								</div>
								<div class="book_info">${book.writer}</div>
								<div class="book_info">${book.date}</div>
							</div>
						</div>
						<div class="col-50">
							<div class="current_page">
								<span class="word">${page}</span>
							</div>
						</div>
					</div>
					<div class="row browse_tool">
						<div class="col-30 filter">
							<div class="active" data-filter="all">全部</div>
							<div data-filter="question" onclick="switch_content($(this),${book.id})">问答</div>
							<div data-filter="note" onclick="switch_content($(this),${book.id})">笔记</div>
                            <div data-filter="attachment" onclick="switch_content($(this),${book.id})">附件</div>
						</div>
						<div class="col-69 action">
							<div>
                                <a data-pjax href="#" onclick="set_create_new_feed_url($(this),${book.id})">
                                <i class="icon-pencil"></i>添加问题/笔记</a>
							</div>
							<div>
								<a href="#"><i class="icon-hand-right"></i>页数跳转</a>
							</div>
							<div>
								<a href="#"><i class="icon-filter"></i>筛选内容</a>
							</div>
						</div>
					</div>
					<div class="recommend_list">
						<div class="title">本书热门推荐</div>
						<ul>
							<li><a href="#">五步蛇的解药在哪里？</a></li>
							<li><a href="#">五棵松的真相是？</a></li>
							<li><a href="#">来，我们山寨一个知乎.</a></li>
							<li><a href="#">南美洲避开了两次世界大战，为什么现在没有发达国家？</a></li>
						</ul>
					</div>
					<div class="recommend_list">
						<div class="title">书籍推荐</div>
						<div class="row book_list">
							<div class="col-20">
								<a href="#"><img src="/static/images/books/5.jpg"></a>
							</div>
							<div class="col-5"></div>
							<div class="col-20">
								<a href="#"><img src="/static/images/books/1.jpg"></a>
							</div>
							<div class="col-5"></div>
							<div class="col-20">
								<a href="#"><img src="/static/images/books/4.jpg"></a>
							</div>
							<div class="col-5"></div>
							<div class="col-20">
								<a href="#"><img src="/static/images/books/2.jpg"></a>
							</div>
						</div>
					</div>
                    <!--
                    <div class="inventory">
                        <div class="chapter">第5章 向量代数与空间解析几何</div><div class="apostrophe">.....</div>18页
                            <div class="section">5.1 向量代数</div><div class="apostrophe">.....</div>26页
                            <div class="section">5.2 平面与空间直线</div><div class="apostrophe">.....</div>32页
                            <div class="section">5.3 曲面与空间曲线</div><div class="apostrophe">.....</div>38页
                        <div class="chapter">第6章 多元函数微分学</div><div class="apostrophe">.....</div>39页
                            <div class="section">6.1 多元函数的基本概念</div><div class="apostrophe">.....</div>40页
                            <div class="section">6.2 偏导数与全积分</div><div class="apostrophe">.....</div>46页
                            <div class="section">6.3 复合函数的求导法则</div><div class="apostrophe">.....</div>52页
                    </div>
                    -->

                    <div class="inventory">
                        <!--
                        <div class="inv_entity row">
                            <div class="chapter_title">第5章 向量代数与空间解析几何</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page">18页</div>
                        </div>
                        <div class="inv_entity row">
                            <div class="section_title">5.1 向量代数</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page">18页</div>
                        </div>
                        <div class="inv_entity row">
                            <div class="section_title">5.2 平面与空间直线</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page" float>26页</div>
                        </div>
                        <div class="inv_entity row">
                            <div class="chapter_title">第6章 多元函数微分学</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page">27页</div>
                        </div>
                        <div class="inv_entity row">
                            <div class="section_title">6.1 多元函数的基本概念</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page">27页</div>
                        </div>
                        <div class="inv_entity row">
                            <div class="section_title">6.2 偏导数与全积分</div>
                            <div class="apostrophe">-&nbsp;-&nbsp;</div>
                            <div class="inv_page" float>30页</div>
                        </div>
                        -->
                    </div>
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

				<div id="page_end_template">
					<div class="page" data-page="End">
						<!-- {hack.e}
                        <div class="page_split">
                            <div><div>To.The.End</div></div>
                        </div>
                        <div class="empty">
                            没有内容了
                        </div>
                        {hack.s} -->
					</div>
				</div>

				<div id="cover_page_template">
					<div class="page" data-page="0">
						<!-- {hack.e}
                        <div class="row">
                            <div class="col-33">
                                <img src="{book.cover_image_path}" style="width: 100%">
                            </div>
                            <div class="col-3"></div>
                            <div class="col-64 cover_page">
								<div class="name"><span>《{book.name}》</span></div>
                                <div class="info">作者：<span>{book.author}</span></div>
                                <div class="info">出版：<span>{book.publisher}</span></div>
                                <div class="info">年份：<span>{book.date}</span></div>
                                <div class="info">简介：</div>
                                <div class="brief">{book.brief}</div>
                            </div>
                        </div>
                        {hack.s} -->
					</div>
				</div>

				<div id="text_with_picture_template">
					<div class="browse_brief clear_fix">
						<!-- {hack.e}
                        <div class="feed_text_wrap">
                            <a href="/">
                                <div class="feed_text">{post.brief}</div>
                            </a>
                        </div>
                        <div style="width: 2%; height: 1px; float: left"></div>
                        <div class="feed_picture">
                            <img src="{post.picture}">
                        </div>
                        {hack.s} -->
					</div>
				</div>

				<div id="text_with_no_picture_template">
					<div class="browse_brief clear_fix">
						<!-- {hack.e}
                        <div>
                            <a href="/">
                                <div class="feed_text">{post.brief}</div>
                            </a>
                        </div>
                        {hack.s} -->
					</div>
				</div>

				<div id="note_template">
                <!-- {hack.e}
                <div class="page" data-page="{post.page}">
                    <div class="page_split">
                        <div>
                            <div>Page.{post.page}</div>
                        </div>
                    </div>
					<div class="feeds">

                        <div class="browse_list_meta clear_fix">
                            <div class="title note">
                                <i class="icon-edit"></i>笔记
                            </div>
                            <div class="photo">
                                <img src="{post.userAvatarUrl}">
                            </div>
                        </div>
                        {post.brief}
                        <div class="feed_tool_bar">
                            <div class="line watch" data-action="watch" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-plus"></i>关注问题</div>
                            <div style="display: none" class="line cancel_watch" data-action="cancel_watch" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-ok"></i>取消关注</div>
                            <div class="line show_comment" data-action="get_comment" data-url="/note/{}/comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-comments-alt"></i>32条评论</div>
                            <div style="display: none" class="line hide_comment" data-action="hide_comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-double-angle-up"></i>收起评论</div>
                            <span class="time">{post.strDate}</span>
                        </div>
                        <div style="clear: both"></div>
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
                </div>
                {hack.s} -->
				</div>

				<div id="question_template">
                <!-- {hack.e}
                <div class="page" data-page="{post.page}">
                    <div class="page_split">
                        <div>
                            <div>Page.{post.page}</div>
                        </div>
                    </div>
					<div class="feeds">

                        <div class="browse_list_meta clear_fix">
                            <div class="title">
                                <a href="#">{post.title}</a>
                            </div>
                            <div class="photo">
                                <img src="{post.userAvatarUrl}">
                            </div>
                        </div>
                        {post.brief}
                        <div class="feed_tool_bar">
                            <div class="line watch" data-action="watch" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-plus"></i>关注问题</div>
                            <div style="display: none" class="line cancel_watch" data-action="cancel_watch" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-ok"></i>取消关注</div>
                            <div class="line show_comment" data-action="get_comment" data-url="/post/{}/comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-comments-alt"></i>32条评论</div>
                            <div style="display: none" class="line hide_comment" data-action="hide_comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-double-angle-up"></i>收起评论</div>
                            <div class="line" data-action="show_all_answer" data-passage-id="{post.id}"><i class="icon-lightbulb" onclick="tool_bar_action($(this))"></i>2个回答</div>
                            <span class="time">{post.strDate}</span>
                        </div>
                        <div style="clear: both"></div>
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
                </div>
                {hack.s} -->
				</div>

                <div id="attachment_template">
                    <!-- {hack.e}
                    <div class="page" data-page="{post.page}">
                         <div class="page_split">
                            <div>
                                <div>Page.{post.page}</div>
                            </div>
                         </div>
                        <div class="feeds">
                            <div class="browse_list_meta clear_fix">
                                <div class="title">
                                    <a href="#">{post.title}</a>
                                </div>
                                <div class="photo">
                                    <img src="{post.userAvatarUrl}">
                                </div>
                            </div>
                            {post.describe}
                            <div class="feed_tool_bar">
                                <div class="line show_comment" data-action="get_comment" data-url="/post/{}/comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-comments-alt"></i>32条评论</div>
                                <div style="display: none" class="line hide_comment" data-action="hide_comment" data-passage-id="{post.id}" onclick="tool_bar_action($(this))"><i class="icon-double-angle-up"></i>收起评论</div>
                                <span class="time">{post.strDate}</span>
                            </div>
                            <div style="clear: both"></div>
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
                    </div>
                    {hack.s} -->
                </div>





				<div id="page_template">
					<div class="page" data-page="{req.page}">
						<div class="page_split">
							<div>
								<div>Page.{req.page}</div>
							</div>
						</div>
						{req.feeds}
					</div>
				</div>

                <div id="section_template">
                    <div class="inv_entity row">
                        <!-- {hack.e}
                        <div class="section_title">{section.num}&nbsp;{section.name}</div>
                        <div class="apostrophe">-&nbsp;-&nbsp;</div>
                        <div class="inv_page">{section.startPage}</div>
                        {hack.s} -->
                    </div>
                </div>

                <div id="chapter_template">
                    <div class="inv_entity row">
                        <!-- {hack.e}
                        <div class="chapter_title">{section.num}&nbsp;{section.name}</div>
                        <div class="apostrophe">-&nbsp;-&nbsp;</div>
                        <div class="inv_page">{section.startPage}</div>
                        {hack.s} -->
                    </div>
                </div>
			</div>
		</div>

		<script>
			browse_page_change_init();
		</script>
		<!--xx**-->
	</div>
</body>
</html>