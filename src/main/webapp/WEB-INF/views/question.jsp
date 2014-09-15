<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/picker/static/lib/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/picker/static/lib/nprogress/nprogress.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/picker/static/lib/fake_bootstrap/fake_bootstrap.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/picker/static/css/picker.css">

<script src="/picker/static/js/jquery.js"></script>
<script src="/picker/static/lib/nprogress/nprogress.js"></script>
<script src="/picker/static/lib/jquery.pjax.js"></script>
<script src="/picker/static/js/picker.js"></script>
<script src="/picker/static/js/epiceditor/js/epiceditor.min.js"></script>

</head>

<!--TODO: AJAX取消息-->
<body>
	<div id="nav" class="row">
		<div id="nav_content">
			<div class="col-13">
				<div id="logo" class="row">
					<div class="col-25">
						<img src="/picker/static/images/elements/logo.png">
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
								<img src="/picker/static/images/photo/0.png">
							</div>
							<div id="nav_id">韩寒</div>
						</div>
					</a>
					<div id="nav_mail">
						<div id="nav_mail_icon">
							<a data-pjax href="/mail"><i class="icon-envelope-alt"
								style="margin-right: 5px"></i>cc</a>
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
		<div class="row">
			<div class="col-25">
				<div class="back_nav clear_fix">
					<div class="go_back" onclick="go_back()">
						<i class="icon-circle-arrow-left"></i> 返回前页
					</div>
				</div>

				<div id="detail_neighbors">
					<div class="neighbor_compose">
						<div>
							<span class="detail_side_split">P 17</span>
						</div>
						<ul>
							<li><a href="#">我是谁？</a></li>
							<li><a href="#">我从哪里来？</a></li>
							<li><a href="#">我到哪里去？</a></li>
						</ul>
						<div class="other_note_link">22条笔记 »</div>
					</div>

					<div class="neighbor_compose">
						<div>
							<span class="detail_side_split">P 18</span>
						</div>
						<ul>
							<li><a href="#">千面神的起源？</a></li>
							<li class="focused_question"><i class="icon-hand-right"></i>千面神是真正的神祗吗？</li>
							<li><a href="#">贾坤显示的是真面目吗?</a></li>
						</ul>
						<div class="other_note_link">82条笔记 »</div>
					</div>

					<div class="neighbor_compose">
						<div>
							<span class="detail_side_split">P 22</span>
						</div>
						<ul>
							<li><a href="#">龙妈打算什么时候去维斯洛特？</a></li>
						</ul>
						<div class="other_note_link">17条笔记 »</div>
					</div>
				</div>
			</div>
			<div class="col-5"></div>

			<div class="col-70">
				<div class="detail_title">
					<c:out value='${question.title}' />
				</div>

				<div class="portion">
					<div class="row">
						<div class="col-96">
							<div class="user_id">
								<c:out value='${question.askerName}' />
								, Yo
							</div>
							<div class="detail_approve">
								<span><c:out value='${question.answerNum}' />回答来自</span> <span>韩寒、</span>
								<span>王明明、</span> <span>欧文</span> <span
									class="detail_approve_more">等人</span>
							</div>
							<div class="detail_info">
								<!-- Markdown html here -->
								<!--  
								布拉弗斯，无面者的神。<br />
								神有千面，这个比光之王的一神论还要牛逼啊，不管你们怎么信别的神，所有神都是千面神的一面，归根结底还是信了我千面神。<br />
								哟西。<br />-->
								<c:out value='${question.content}' />
							</div>
						</div>
						<div class="col-4">
							<img style="max-width: 100%"
								src="<c:url value='${question.askerAvatarUrl}' />">
						</div>
					</div>
					<div style="clear: both"></div>

					<!--toolbar 区域-->
					<div class="feeds clear_fix">
						<div class="feed_tool_bar clear_fix">
							<div class="line watch" data-action="watch" data-passage-id="123"
								onclick="tool_bar_action($(this))">
								<i class="icon-plus"></i>关注问题
							</div>
							<div style="display: none" class="line cancel_watch"
								data-action="cancel_watch" data-passage-id="123"
								onclick="tool_bar_action($(this))">
								<i class="icon-ok"></i>取消关注
							</div>
							<div class="line show_comment" data-action="get_comment"
								data-passage-id="123" onclick="tool_bar_action($(this))">
								<i class="icon-comments-alt"></i>
								<c:out value='${question.commentNum}' />
								条评论
							</div>
							<div style="display: none" class="line hide_comment"
								data-action="hide_comment" data-passage-id="123"
								onclick="tool_bar_action($(this))">
								<i class="icon-double-angle-up"></i>收起评论
							</div>
							<span class="time"><c:out value='${question.date}' /></span>
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
				</div>

				<!--编辑器-->
				<div class="portion">
					<div class="editor_label">
						<i class="icon-pencil"></i>添加答案
					</div>
					<div id="epiceditor"></div>
					<div>
						<span style="float: right;">
							<button type="button" class="commit_button">提交回答</button>
						</span>
						<div style="clear: both"></div>
					</div>
				</div>

				<div class="portion ans_split">
					<c:out value='${question.answerNum}' />
					回答
				</div>
				<!--  
				<div class="portion">
					<div class="col-96">
						<div class="user_id">韩寒, 小四快来关注我</div>
						<div class="detail_approve">
							<span>6赞同来自</span> <span>韩寒、</span> <span>王明明、</span> <span>欧文</span>
							<span class="detail_approve_more">等人</span>
						</div>
					</div>
					<div class="col-4">
						<img style="max-width: 100%"
							src="/picker/static/images/photo/2.jpg">
					</div>
					<div style="clear: both"></div>
					<div class="detail_info">
					
						布拉弗斯，无面者的神。<br />
						神有千面，这个比光之王的一神论还要牛逼啊，不管你们怎么信别的神，所有神都是千面神的一面，归根结底还是信了我千面神。<br />
						哟西。<br />
					</div>

					<div class="feeds clear_fix">
						<div class="feed_tool_bar clear_fix">
							<div class="up" data-action="up" data-passage-id="123"
								onclick="tool_bar_action($(this))">
								<span class="up_icon"><i class="icon-thumbs-up"></i>赞</span> <span
									class="cnt up_cnt">31</span>
							</div>
							<div style="display: none" class="line cancel_up"
								data-action="cancel_up" data-passage-id="123"
								onclick="tool_bar_action($(this))">
								<i class="icon-ok"></i>取消赞<span class="cnt">32</span>
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
							<span class="time">47分钟前</span>
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
				</div>-->
				<c:forEach var="answer" items="${answerList}">
					<div class="portion">
						<div class="col-96">
							<div class="user_id">
								<c:out value='${answer.replierName}' />
								, 小四快来关注我
							</div>
							<div class="detail_approve">
								<span><c:out value='${answer.favoriteNum}' />赞同来自</span> <span>韩寒、</span>
								<span>王明明、</span> <span>欧文</span> <span
									class="detail_approve_more">等人</span>
							</div>
						</div>
						<div class="col-4">
							<img style="max-width: 100%"
								src="<c:url value='${answer.replierAvatarUrl}' />">
						</div>
						<div style="clear: both"></div>
						<div class="detail_info">
							<c:out value='${answer.content}' />
						</div>

						<div class="feeds clear_fix">
							<div class="feed_tool_bar clear_fix">
								<div class="up" data-action="up" data-passage-id="123"
									onclick="tool_bar_action($(this))">
									<span class="up_icon"><i class="icon-thumbs-up"></i>赞</span> <span
										class="cnt up_cnt"><c:out value='${answer.favoriteNum}' /></span>
								</div>
								<div style="display: none" class="line cancel_up"
									data-action="cancel_up" data-passage-id="123"
									onclick="tool_bar_action($(this))">
									<i class="icon-ok"></i>取消赞<span class="cnt"><c:out
											value='${answer.favoriteNum+1}' /></span>
								</div>
								<div class="line show_comment" data-action="get_comment"
									data-passage-id="123" onclick="tool_bar_action($(this))">
									<i class="icon-comments-alt"></i>
									<c:out value='${answer.commentNum}' />
									条评论
								</div>
								<div style="display: none" class="line hide_comment"
									data-action="hide_comment" data-passage-id="123"
									onclick="tool_bar_action($(this))">
									<i class="icon-double-angle-up"></i>收起评论
								</div>
								<span class="time"><c:out value='${answer.date}' /></span>
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
					</div>
				</c:forEach>
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

		<script>
			var editor = create_editor();
			editor_listened();
			editor.load();
			var current_url = location.href; //初始设置防止pjax处理时index出错
			autosave(location.href);
		</script>
		<!--xx**-->
	</div>
</body>
</html>