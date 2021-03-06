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
		<div id="book_id" data-value="${book.id}" style="display: none"></div>
		<div id="book_name" data-value="${book.bookName}" style="display: none"></div>
		<div class="detail_title" style="display: none">${book.bookName}第12页的草稿</div>

		<!--图片上传功能 开始-->
		<div id="image_insert_panel" class="shadow">
			<form action="" method="POST" enctype="multipart/form-data">
				<div class="title clear_fix">
					<div style="float: left">上传图片</div>
					<div class="point_cursor"onclick="hide_panel($('#image_insert_panel'), $('#cancel_image_insert'))" style="float: right">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="content">
					<div>
						<a href="javascript:void(0)" onclick="show_upload_local_image()">上传本地图片</a>
						或 <a href="javascript:void(0)" onclick="show_link_outside_image()">引用站外图片</a>
					</div>

					<div id="upload_local_image">
						<input id="image_file" type="file" name="image" />
                        <input type="button" value="提交" onclick="image_upload()" />
					</div>
					<div id="link_image">
						<input id="outside_image_link" type="text" placeholder="图片地址" />
						<input type="button" value="添加" onclick="image_link()" />
					</div>
					<div id="result">
						将<span></span>添加到你想要插入图片的地方。
					</div>
				</div>
			</form>
		</div>

        <div id="file_upload_panel" class="shadow">
            <form>
                <div class="title clear_fix">
                    <div style="float: left">上传附件</div>
                    <div class="point_cursor"onclick="hide_panel($('#file_upload_panel'))" style="float: right">
                        <i class="icon-remove"></i>
                    </div>
                </div>
                <div class="content">
                    <div id="upload_attachment_file">
                        <input id="attachment_file" type="file" name="attachment" />
                        <input type="button" value="提交" onclick="file_upload()" />
                    </div>
                </div>
            </form>
        </div>

		<div id="cancel_image_insert" class="cancel_panel"
			onclick="hide_panel($('#image_insert_panel'), $(this))"></div>
		<div id="cancel_page_change" class="cancel_panel"
			onclick="hide_panel(null, $(this))"></div>
		<!--图片上传 结束-->
		<!--  /detail/112/12/create-->

		<form method="post"
			action=<c:url value="/detail/${book.id}/12/create"/>
			onsubmit="return submit_check();">
			<input type="hidden" name="page" value="" />
            <input type="hidden" name="raw" value="" />
            <input type="hidden" name="rendered" value="" />
            <input type="hidden" name="attachmentIds" value="" />
			<div style="padding-left: 100px; padding-right: 100px;">
				<div class="row">
					<div class="col-70">
						<div id="new_form_type">
                            <label onclick="choose_question();">
                                <input type="radio" name="type" value="question" checked  />提问
                            </label>

							<label onclick="choose_note();">
                                <input type="radio"	name="type" value="note" />笔记
                            </label>

                            <label onclick="choose_attachment();">
                                <input type="radio" name="type" value="attachment" />附件
                            </label>
						</div>
						<div id="new_form_title">
                            <span class="question" ><i class="icon-lightbulb"></i>提问：</span>
							<span class="note" style="display: none"><i class="icon-edit"></i>笔记：</span>
                            <span class="attachment" style="display: none"><i class="icon-paper-clip"></i>附件：</span>
                            <span> <input type="text" name="title" placeholder="标题(必填)" /> </span>
						</div>
						<div>
							<div id="epiceditor" style="height: 300px"></div>
							<div style="margin-top: 10px" class="row">
                                <span style="float: right;">
                                    <button type="submit" class="commit_button">提交</button>
                                </span>

                                <span class="upload_span">
                                    <button type="button" class="upload_button" onclick="show_file_upload_panel()">上传附件</button>
                                </span>

								<div style="clear: both"></div>
							</div>

                            <div style="margin-top: 10px;" class="attachment_part row">
                                <div id="list_title" class="title">已上传附件</div>
                                <div class="attachment_list">


                                </div>
                            </div>
						</div>
					</div>

					<div class="col-4"></div>

					<div class="col-26">
						<div class="row">
							<div id="p_new_book_meta" class="clear_fix">
								<div class="col-40">
									<img src=<c:url value="${book.imageUrl}"/> style="width: 100%">
								</div>
								<div class="col-10"></div>
								<div class="col-50">
									<div id="book_meta" data-book-id="123">
										<div class="book_info_2x">
											<a href=<c:url value="/browse/${book.id}/0"/>>${book.bookName}</a>
										</div>
										<div class="book_info_2x">${book.writer}</div>
										<div class="book_info_2x">${book.date}</div>
									</div>
								</div>
							</div>
							<div id="p_new_page" style="z-index: 901">
								<span>Page. </span> <span class="page" onclick="show_input();">${page}</span>
								<span><input type="text" style="width: 40px;"
									onblur="change_page($(this).val());"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>

        <!--<button onclick="test_attachment_list()"></button>-->
		<script>
			var editor = create_editor();
			editor_listened();
			editor.load();
			//给epiceditor添加图片上传按钮
			$('#epiceditor').find('iframe').contents().find(
					'button.image-insert-btn').on('click',
					show_image_insert_panel);
			var current_url = location.href; //初始设置防止pjax处理时index出错
			autosave();

			function choose_note() {
				$('#new_form_title').find('.question').hide();
                $('#new_form_title').find('.attachment').hide();
				$('#new_form_title').find('.note').show();
				$('#new_form_title').find('input')
						.attr('placeholder', '标题(必填)');
                $('.upload_span').hide();
                $('.attachment_part').hide();
			}

			function choose_question() {
				$('#new_form_title').find('.note').hide();
                $('#new_form_title').find('.attachment').hide();
				$('#new_form_title').find('.question').show();
				$('#new_form_title').find('input')
						.attr('placeholder', '标题(必填)');
                $('.upload_span').hide();
                $('.attachment_part').hide();
			}

            function choose_attachment() {
                $('#new_form_title').find('.note').hide();
                $('#new_form_title').find('.question').hide();
                $('#new_form_title').find('.attachment').show();
                $('#new_form_title').find('input').attr('placeholder', '标题(必填)');
                $('.upload_span').show();
                $('.attachment_part').show();
            }

			function show_input() {
				$('#p_new_page').find('.page').hide();
				$('#p_new_page').find('.page').html('');
				$('#p_new_page').find('input').show().focus();
				$('#cancel_page_change').show();
			}



			//当page改变，url地址无刷新改变。此函数用于生成新地址
			function gen_current_new_url(page) {
				var book_id = $('#book_id').data('value');
				return '/detail/' + book_id + '/' + page + '/new'
			}

			function change_page(val) {
				var p = parseInt(val, 10);
				if (isNaN(p) || p <= 0) {
					alert('请输入正确的页码(数字)。');
				} else {
					//修改草稿标题
					var new_title = '《' + $('#book_name').data('value') + '》第'
							+ p + '页';
					$('.detail_title').html(new_title);

					var old_url = current_url;
					history.replaceState({}, '第' + p + '页的笔记',
							gen_current_new_url(p));
					var new_url = location.href;
					//修改本地草稿箱
					var dir = JSON.parse(localStorage.getItem('dir'));
					dir[new_url] = dir[old_url];
					delete dir[old_url];
					localStorage.setItem('dir', JSON.stringify(dir));
					localStorage[new_url] = localStorage[old_url];
					delete localStorage[old_url];
					current_url = new_url;

					$('#p_new_page').find('input').hide();
					$('#p_new_page').find('.page').html(p);
					$('#p_new_page').find('.page').fadeIn();

					change_title(new_url, new_title)
				}
			}

			function submit_check() {


				var type = $('input[name=type]:checked').val();
				var title = $('#new_form_title').find('input').val();
				var page = parseInt($('#p_new_page').find('.page').html());
				var raw = $(editor.editor).html();

				//服务端检查, 标签可能引起安全问题
				var rendered = $(editor.previewer).html();
				if (!title) {
					alert('请填写标题。');
					return false;
				}

                if (!raw) {
                    alert('请填写内容。');
                    return false;
                }


				if (isNaN(page) || page <= 0) {
					var page_input = $('#p_new_page').find('input');
					if (page_input.css('display') != 'none') {
						change_page(page_input.val());
						hide_panel(null, $('#cancel_page_change'));
					} else {
						alert('请填写页码。');
					}
					return false;
				}

				if (!(title.trim()) && !(raw.trim())) {
					alert('标题和内容不能为空。');
					return false;
				}

                console.log($('.attachment_list').children('.attachment_entity').length);
                if($('.attachment_list').children('.attachment_entity').length==0)
                {
                    alert('还未上传附件。');
                    return false;
                }

				$('input[name=page]').val(page);
				$('input[name=raw]').val(raw);
				$('input[name=rendered]').val(rendered);
                set_attachment_list();

				//提交前删除草稿
				var dir = JSON.parse(localStorage['dir']);
				var link = clean_url(location.href);
				if (delete dir[link]) {
					localStorage['dir'] = JSON.stringify(dir);
					localStorage['count'] = Object.keys(dir).length;
					delete localStorage[link];
				}

				return true;
			}
		</script>
		<!--xx**-->

        <div id="attachment_entity_template">
        <!-- {hack.e}
        <div class="attachment_entity">
            <div class="col-70">
                <div class="attachment_id" style="display:none;">{attachmentId}</div>
                <div class="attachment_name" >勘误表.pdf</div>
            </div>
            <div class="col-30">
                <i class="icon-ok-sign attachment_icon" style="float:left"></i>
            </div>

            <div style="clear: both"></div>
        </div>
        {hack.s} -->

        </div>

	</div>
</body>
</html>