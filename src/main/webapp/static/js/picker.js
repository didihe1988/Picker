﻿/**
 * Created by youzh_000 on 2014/7/24.
 */

//---------------------------------base part------------------------------
/**
 * Created by ns on 14-7-6.
 */

$(document).ready(function () {
    nav_thing();
    pjax_init();
});

function nano_template(template, data) {
    // hack patch. 为了解决模板中图片地址错误地问题
    data['hack'] = {
        s: '<!--',
        e: '-->'
    };
    return template.replace(/\{([\w\.\(\)]*)\}/g, function (str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++) {
            if (keys[i].match(/\(\)/))
                v = v[keys[i].replace(/\(\)/, '')]();
            else
                v = v[keys[i]];
        }
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
}

//关于导航条的动作
function nav_thing() {
    $('#nav_mail_icon').find('a').hover(function () {
        $('#nav_mail_cnt').css({
            'background-color': '#009EDA'
        });
    }, function () {
        $('#nav_mail_cnt').css({
            'background-color': '#FF9933'
        });
    });
    $('#nav_msg').find('a').hover(function () {
        $('#nav_msg_cnt').css({
            'background-color': '#009EDA'
        });
    }, function () {
        $('#nav_msg_cnt').css({
            'background-color': '#FF9933'
        });
    });
}

//消息，私信信息获取
function get_cnt() {
    $.get('/new_message_count', function (req) {
        if (req['mail']) {
            $('#nav_mail_cnt').html(req['mail']).css({
                display: 'inline-block'
            });
        } else {
            $('#nav_mail_cnt').html('0').css({
                display: 'none'
            })
        }
        if (req['message']) {
            $('#nav_msg_cnt').html(req['message']).css({
                display: 'inline-block'
            });
        } else {
            $('#nav_msg_cnt').html('0').css({
                display: 'none'
            });
        }
    });
}

var g_pre_page = '';
function pre_page_url() {
    if (arguments[0]) {
        g_pre_page = arguments[0];
        return arguments[0];
    } else {
        return g_pre_page;
    }
}

function pjax_init() {
    if ($.support.pjax) {
        $(document).on('click', 'a[data-pjax]', function (event) {
            pre_page_url(window.location.href);
            $.pjax.click(event, {
                container: $('#main')
            });
        })
    }
    $(document).on('pjax:send', function () {
        NProgress.start();
    });
    $(document).on('pjax:complete', function () {
        NProgress.done();
    });
    $(document).on('pjax:end', function () {
        get_cnt();
    })
}

// --------------------------------------index
// part-------------------------------
function get_drafts_count() {
    if (localStorage['count']) {
        $('#drafts_count').html(localStorage['count'])
    }
}

function show_full(feed_text) {
    var p = feed_text.parents('.feeds');

    if (p.find('.feed_full_text').html() == '') {
        NProgress.start();
        $.ajax({
            url: '/full/' + feed_text.data('passage-id'),
            success: function (req) {
                NProgress.done();
                p.children('.feed_brief').hide();
                p.find('.feed_full_text').html(req['data']);
                p.find('.feed_full').show();
            },
            error: function () {
                NProgress.done();
            }
        });
    } else {
        p.children('.feed_brief').hide();
        p.find('.feed_full').show();
    }
}

function show_brief(feed_pack_up) {
    var p = feed_pack_up.parents('.feeds');
    p.find('.feed_full').hide();
    p.find('.feed_brief').show();
}

function tool_bar_action(tool_div) {
    var action = {
        // change req.status from 'success' to 0
        up: function (pid, feeds) {
            $.ajax({
                url: '/json/answer/' + pid + '/subscribe',
                success: function (req) {
                    // should set req.favoriteNum
                    // if (req.status == 0) {
                    feeds.find('.up').hide();
                    feeds.find('.cancel_up').fadeIn();

                    // }
                }
            });
        },
        cancel_up: function (pid, feeds) {
            $.ajax({
                url: '/json/answer/' + pid + '/withdraw_subscribe',
                success: function (req) {
                    // if (req.status == 0) {
                    feeds.find('.cancel_up').hide();
                    feeds.find('.up').fadeIn();
                    // }
                }
            });
        },
        watch: function (pid, feeds) {
            $.ajax({
                url: '/json/question/' + pid + '/follow',
                success: function (req) {
                    if (req.status == 0) {
                        feeds.find('.watch').hide();
                        feeds.find('.cancel_watch').fadeIn();
                    }
                }
            });
        },
        cancel_watch: function (pid, feeds) {
            $.ajax({
                url: '/json/question/' + pid + '/withdraw_follow',
                success: function (req) {
                    if (req.status == 0) {
                        feeds.find('.cancel_watch').hide();
                        feeds.find('.watch').fadeIn();
                    }
                }
            })
        },
        get_comment: function (pid, feeds, url) {
            var comments_dom = feeds.find('.comments');
            feeds.find('.show_comment').hide();
            feeds.find('.hide_comment').show();
            if (comments_dom.find('.comments_list').html() == '') {
                comments_dom.show();
                NProgress.start();
                var error_handle = function () {
                    NProgress.done();
                    comments_dom.find('.waiting').hide();
                };
                $.ajax({
                    url: url,
                    success: function (req) {
                        if (req.status == 'success') {
                            $.each(req.comments, function (i, comment) {
                                var new_comment_dom = $(nano_template($(
                                    '#comment_template').html(), {
                                    'comment': comment
                                }));
                                comments_dom.find('.comments_list').append(
                                    new_comment_dom);
                            });
                            NProgress.done();
                            comments_dom.find('.waiting').hide();
                            comments_dom.find('.comments_list').slideDown();
                        } else {
                            error_handle();
                        }
                    },
                    error: error_handle
                });
            } else {
                comments_dom.slideDown();
            }
        },
        hide_comment: function (pid, feeds) {
            feeds.find('.hide_comment').hide();
            feeds.find('.show_comment').show();
            feeds.find('.comments').slideUp();
        },

        get_attachment: function (pid, feeds, url) {
            var attachments_dom = feeds.find('.attachments');
            feeds.find('.show_attachment').hide();
            feeds.find('.hide_attachment').show();

            if (attachments_dom.find('.attachments_list').html() == '') {
                attachments_dom.show();
                NProgress.start();
                var error_handle = function () {
                    NProgress.done();
                    attachments_dom.find('.waiting').hide();
                };
                $.ajax({
                    url: url,
                    success: function (req) {
                        $.each(req['attachmentList'], function (i, attachment) {
                            var new_attachment_dom = $(nano_template($(
                                '#att_template').html(), {
                                'attachment': attachment
                            }));
                            attachments_dom.find('.attachments_list').append(
                                new_attachment_dom);
                        });
                        NProgress.done();
                        attachments_dom.find('.waiting').hide();
                        attachments_dom.find('.attachments_list').slideDown();
                    },
                    error: error_handle
                });
            } else {
                attachments_dom.slideDown();
            }
        },
        hide_attachment: function (pid, feeds) {
            feeds.find('.hide_attachment').hide();
            feeds.find('.show_attachment').show();
            feeds.find('.attachments').slideUp();
        }
    };

    var url = tool_div.data('url');
    if (url)
        url = url.replace('{}', tool_div.data('passage-id'));
    action[tool_div.data('action')](tool_div.data('passage-id'), tool_div
        .parents('.feeds'), url);
}

// -------------------------------------message
// part---------------------------------
function go_back() {
    var pre = pre_page_url();
    if (pre != '' && pre != window.location.href) {
        history.go(-1);
    } else {
        window.location.href = '/dynamic/1';
    }
}

// -------------------------------------mail
// part-------------------------------------
function toggle_my_reply(mail) {
    mail.find('.my_replies').toggle();
}

function read_flag(button) {
    var mail_id = button.data('mail-id');

    button.html('<i class="icon-spinner icon-spin"></i>');
    var error_handle = function () {
        button.html('已读');
    };
    $.ajax({
        url: '/mail_read_flag/' + mail_id,
        success: function (req) {
            if (req.status == 'success') {
                button.hide();
            } else {
                error_handle();
            }
        },
        error: error_handle
    });
}

function reply_message(receiver_id, name) {
    $('#receiver_id').data('value', receiver_id);
    $('#receiver_name').html(name);
    $('#message_panel').show();
    $('#cancel_message_panel').show();
}

// ------------------------------------user
// part-----------------------------------------
/**
 * Created by youzh_000 on 2014/7/15.
 */

function merge_url(a, b) {
    // a+b
    a = a.replace(/\/\//g, '/');
    b = b.replace(/\/\//g, '/');
    a = a + '/';
    return (a + b).replace(/\/\//g, '/')
}

function do_follow(t, user_id) {
    t.hide();
    $.ajax({
        url: '/json/user/' + user_id + '/follow',
        type: 'get',
        success: function () {
            t.parent().find('.cancel_follow').show();
        },
        error: function () {
            console.log('error')
        }
    })
}

function cancel_follow(t, user_id) {
    t.hide();
    $.ajax({
        url: '/json/user/' + user_id + '/withdraw_follow',
        type: 'get',
        success: function () {
            t.parent().find('.follow_action').show();
        },
        error: function () {
            console.log('error')
        }
    })
}

//面板之间的切换操作及动画
function set_init_panel(obj) {
    var flag_class = 'current_panel';
    if ($('.' + flag_class).length == 0) {
        obj.addClass(flag_class);
        return true;
    }
    return false;
}

function gen_paging(current_page, total_page, base_url) {
    //生成分页
    if (total_page > 1) {
        var pagination_template = $('#pagination_template').html();
        var handle = {
            gen_link: function (page) {
                return merge_url(base_url, page.toString())
            },
            pre: function () {
                if (current_page == 1) {
                    return '';
                } else {
                    return '<li><a href="' + this.gen_link(current_page - 1)
                        + '">&laquo;</a></li>';
                }
            },
            paging: function () {
                var p = [];
                for (var i = 1; i <= total_page; ++i) {
                    if (i == current_page) {
                        p
                            .push(nano_template(
                                '<li class="active"><a href="{t.link}">{t.page}</a></li>',
                                {
                                    t: {
                                        link: this.gen_link(i),
                                        page: i
                                    }
                                }));
                    } else {
                        p.push(nano_template(
                            '<li><a href="{t.link}">{t.page}</a></li>', {
                                t: {
                                    link: this.gen_link(i),
                                    page: i
                                }
                            }));
                    }
                }
                return p.join('');
            },
            next: function () {
                if (current_page == total_page) {
                    return '';
                } else {
                    return '<li><a href="' + this.gen_link(current_page + 1)
                        + '">&raquo;</a></li>';
                }
            }
        };
        return nano_template(pagination_template, {
            pagination: handle
        })
    } else {
        return null;
    }
}

function panel_action(t) {
    var handles = {
        'flag_class': 'current_panel',
        'current_panel': function () {
            var cp = $('.' + handles.flag_class);
            if (cp.length == 0) {
                $('#home_panel').addClass(handles.flag_class);
                return $('#home_panel');
            }
            return cp;
        },
        'search_label': function () {
            this.showPanel($('#group_search_panel'));
        },
        'follow': function () {
            this.showPanel($('#follow_panel'));
        },
        'followed': function () {
            this.showPanel($('#followed_panel'));
        },
        'questions': function () {
            this.q_a_n_action('questions', $('#questions_url').data('value'),
                $('#questions_panel'), '全部提问', $(
                    '#questions_feed_template').html(), $(
                    '#questions_feed_template').html());
        },
        'answers': function () {
            this.q_a_n_action('answers', $('#answers_url').data('value'),
                $('#answers_panel'), '全部回答', $(
                    '#answers_feed_with_picture_template').html(), $(
                    '#answers_feed_with_no_picture_template').html());
        },
        'notes': function () {
            //修改url
            this.q_a_n_action('notes', $('#notes_url').data('value'),
                $('#notes_panel'), '全部笔记', $(
                    '#notes_feed_with_picture_template').html(), $(
                    '#notes_feed_with_no_picture_template').html());
        },
        // questions, answers, notes action.basic the same
        'q_a_n_action': function (type, show_url, dom, title, with_picture_template, no_picture_template) {
            //修改url
            window.history.pushState(null, title, show_url);
            // check
            if (dom.find('.content').data('current_page') == 0) {
                //第一次更新
                $.ajax({
                    // url: /json/user/1234/notes/1
                    url: merge_url('/json', show_url),
                    type: 'get',
                    success: function (req) {
                        dom.find('.wait').hide();
                        var part = [];
                        $.each(req[type], function (i, q) {
                            if (q['picture']) {
                                part.push(nano_template(with_picture_template,
                                    {
                                        q: q
                                    }))
                            } else {
                                part.push(nano_template(no_picture_template, {
                                    q: q
                                }))
                            }
                        });

                        part
                            .push(gen_paging(parseInt(req['current_page']),
                                parseInt(req['total_page']), merge_url(
                                    $('#user_home_url').data(
                                        'value'), type)));
                        dom.find('.content').html(part.join(''));
                    },
                    error: function (req) {
                        console.log('error')
                    }
                })
            }
            this.showPanel(dom);
        },
        'showPanel': function (e) {
            var cp = handles.current_panel();
            if (e.attr('id') === cp.attr('id'))
                return;

            cp.animate({
                left: -cp.outerWidth(true)
            }, function () {
                $(this).hide();
                $(this).css({
                    left: 0
                });
                e.fadeIn();
                cp.removeClass(handles.flag_class);
                e.addClass(handles.flag_class);
            }).bind(this);
        },
        'slideBack': function (e) {
            var cp = handles.current_panel();
            var panel_id_to_change_url = [ 'notes_panel', 'questions_panel',
                'answers_panel' ];
            for (var i in panel_id_to_change_url) {
                if (cp.attr('id') == panel_id_to_change_url[i])
                    window.history.pushState(null, '用户主页',
                        $('#user_home_url').data('value'));
            }
            if (e.attr('id') === cp.attr('id'))
                return;

            cp.animate({
                left: cp.outerWidth(true)
            }, function () {
                $(this).hide();
                $(this).css({
                    left: 0
                });
                e.fadeIn();
                cp.removeClass(handles.flag_class);
                e.addClass(handles.flag_class);
            });
        }
    };

    if (typeof (t) == 'string') {
        handles[t]();
        return;
    }

    if (t.data('tab')) {
        handles[t.data('tab')]();
    } else {
        handles.slideBack($('#home_panel'));
    }
}

//动态简介与详细信息的切换
function user_feed_show_full(t) {
    t.hide();
    t.parent().find('.user_feed_all').show();
}

function user_feed_show_brief(t) {
    t.parent().parent().find('.user_feed_brief').show();
    t.parent().hide();
}

function group_search(form) {
    var name = form.find('input').val();
    if (name == '') {
        return false;
    }
    //动态请求
    NProgress.start();
    $.ajax({
        url: '/group_search',
        type: 'get',
        data: {
            group_name: name
        },
        success: function (req) {
            NProgress.done();

            var groups = [];
            $.each(req['groups'], function (i, group) {
                groups.push(nano_template($('#group_info_template').html(), {
                    group: group
                }));
            });
            $('#group_search_content').html(groups.join(''));
        },
        error: function (req) {
            NProgress.done();
            $('#group_search_content').html('查询出错')
        }
    });

    return false; //阻止form提交
}

function show_create_group() {
    $('#create_group_panel').show();
    $('#cancel_create_group').show();
}

function create_group() {
    if ($('#group_name').val().trim() == ''
        || $('#group_describe').val().trim() == '') {
        alert('圈子名称和描述不能为空');
        return;
    }

    NProgress.start();
    $.ajax({
        // TODO
        url: '/circle/add',
        type: 'post',
        data: {
            'name': $('#group_name').val().trim(),
            'describe': $('#group_describe').val().trim()
        },
        success: function () {
            NProgress.done();
            location.reload();
        },
        error: function () {
            NProgress.done();
            alert('创建出错');
        }
    });
}

function show_message_panel() {
    $('#message_panel').show();
    $('#cancel_message_panel').show();
}

function send_message(receiver_id) {
    NProgress.start();
    if ($("#message_content").val().trim() == '') {
        alert('消息不能为空');
        return;
    }
    $.ajax({
        url: '/pmessage/send',
        type: 'post',
        data: {
            "receiverId": receiver_id,
            "content": $("#message_content").val()
        },
        success: function () {
            NProgress.done();
            /*
             hide_panel($('#message_panel'), $('#cancel_message_panel'));
             $("#message_content").val('');*/
            //先强制刷新页面
            location.reload(true);
        },
        error: function () {
            NProgress.done();
            console.log('send error')
        }
    });
}

// -----------------------------------browse part-------------------------------

function gen_dom(post) {
    if (post['picture'] == undefined || post['picture'] == '') {
        post['text_part'] = nano_template($('#text_with_no_picture_template')
            .html(), {
            post: post
        });
    } else {
        post['text_part'] = nano_template($('#text_with_picture_template')
            .html(), {
            post: post
        });
    }
    if (post['type'] == 'note') {
        var new_dom = nano_template($('#note_template').html(), {
            post: post
        })
    } else {
        // question
        var new_dom = nano_template($('#question_template').html(), {
            post: post
        })
    }
    return new_dom;
}

/*
 * 传入一个flag元素(load_next_flag或者load_previous_flag)。通过ajax动态加载内容
 */
function load_page(flag_dom) {
    if (flag_dom.data('lock') == true || flag_dom.data('end') == true) {
        return;
    }
    flag_dom.data('lock', true); //上锁
    flag_dom.slideDown();

    if (flag_dom.data('end') == false) {
        var book_id = $('#book_meta').data('book-id');
        var start_page = flag_dom.data('start-page');
        var filter = $('.filter').find('.active').data('filter');
        var direct = flag_dom.data('direction');
        var error_handle = function (req) {
            flag_dom.hide();
            flag_dom.data('lock', false); //解锁
        };
        $.ajax({
            url: '/page/' + book_id + '/' + start_page,
            type: 'get',
            data: {
                filter: filter,
                direction: direct
            },
            success: function (req) {
                flag_dom.hide();
                if (req.status == 'success') {
                    //展示内容，封面，问答，笔记。
                    if (req.page == '0') {
                        var new_dom = $(nano_template($('#cover_page_template')
                            .html(), {
                            book: req.book
                        }));
                        if (direct == 'up') {
                            flag_dom.data('end', true);
                            $('#pages_container').prepend(new_dom);
                            //保持目前窗口不移动. 20是flag_dom的上下margin
                            $(window).scrollTop(
                                    $(window).scrollTop() + new_dom.height()
                                    - flag_dom.height() - 20);
                        } else
                            $('#pages_container').append(new_dom);
                    } else {
                        var feeds = [];
                        $.each(req['posts'], function (i, post) {
                            feeds.push(gen_dom(post));
                        });
                        req['feeds'] = feeds.join('');
                        var new_dom = $(nano_template($('#page_template')
                            .html(), {
                            req: req
                        }));
                        if (direct == 'up') {
                            $('#pages_container').prepend(new_dom);
                            $(window).scrollTop(
                                //保持目前窗口不移动. 20是flag_dom的上下margin
                                    $(window).scrollTop() + new_dom.height()
                                    - flag_dom.height() - 20)
                        } else
                            $('#pages_container').append(new_dom);
                    }
                    if (req.end == 'true') {
                        flag_dom.data('end', true);
                        //没有内容了
                        if (direct == 'down')
                            $('#pages_container').append(
                                $(nano_template($('#page_end_template')
                                    .html(), {})));
                    }
                    if (direct == 'up')
                        flag_dom.data('start-page', parseInt(req.page) - 1);
                    else
                        flag_dom.data('start-page', parseInt(req.page) + 1);
                    flag_dom.data('lock', false); //解锁
                } else
                    error_handle(req);
            },
            error: function (req) {
                error_handle(req);
            }
        });
    }
}

//  监听scroll事件，当滚动停止时响应
function browse_page_change_init() {
    var timer = null;
    $(window).scroll(
        function () {
            if (timer) {
                clearTimeout(timer);
            }

            var settled = false;
            timer = setTimeout(function () {
                //设置当前显示的页数
                $('.page')
                    .each(
                    function (i, c) {
                        if (!settled) {
                            var top = $(c).offset().top;
                            var scrollTop = $(window)
                                .scrollTop() + 50;
                            var page = $(c).data('page');
                            if (top < scrollTop
                                && scrollTop < top
                                + $(c).height()) {
                                var word_dom = $(
                                    '.current_page').find(
                                    '.word');
                                if (word_dom.html() != page) {
                                    find_cur_section(page);
                                    word_dom.hide().html(
                                        $(c).data('page'))
                                        .fadeIn();
                                }

                                settled = true
                            }
                        }
                    });

                //底部触发更新 改成分页所以暂时不用
                /*
                 if ($(window).scrollTop() + $(window).height() == $(
                 document).height()) {
                 load_page($('#load_next_flag'));
                 }*/
                if ($(window).scrollTop() == 0) {
                    load_page($('#load_previous_flag'));
                }
                // TODO: listen special key, turn up the time limit
            }, 10);
        })
}

// ---------------------------------detail part------------------------------

function clean_url(url) {
    return url.replace(/\?.*/, '').replace(/#+/, '')
}

function create_editor() {
    var opts = {
        container: 'epiceditor',
        textarea: null,
        basePath: '/static/js/epiceditor',
        clientSideStorage: true,
        localStorageName: 'epiceditor',
        useNativeFullscreen: true,
        parser: marked,
        file: {
            name: 'epiceditor',
            defaultContent: '',
            autoSave: false
        },
        theme: {
            base: '/themes/base/epiceditor.css',
            preview: '/themes/preview/github.css',
            editor: '/themes/editor/epic-light.css'
        },
        button: {
            preview: true,
            fullscreen: true,
            bar: "auto"
        },
        focusOnLoad: false,
        shortcut: {
            modifier: 18,
            fullscreen: 70,
            preview: 80
        },
        string: {
            togglePreview: '预览模式',
            toggleEdit: '编辑模式',
            toggleFullscreen: '进入全屏'
        },
        autogrow: false
    };
    return new EpicEditor(opts);
}

function change_title(key, new_title) {
    var dir = JSON.parse(localStorage.getItem('dir'));
    if (dir == null || typeof (dir[key]) == 'undefined')
        return;
    dir[key]['title'] = new_title;
    localStorage.setItem('dir', JSON.stringify(dir))
}

function autosave(no_repeat) {
    if ($('#epiceditor').length) {
        editor.save();
        var content = editor.exportFile();
        var dir = JSON.parse(localStorage.getItem('dir'));
        if (dir == null)
            dir = {};
        var key = clean_url(current_url);
        if (content == '') {
            delete dir[key];
            localStorage.setItem('dir', JSON.stringify(dir));
            localStorage['count'] = Object.keys(dir).length;
            delete localStorage[key];
        } else {
            var date = new Date();
            var now_time = date.toLocaleTimeString();
            if (dir[key] == undefined) {
                dir[key] = {
                    create: now_time,
                    last_modified: now_time,
                    title: $('.detail_title').html()
                }
            } else {
                dir[key]['last_modified'] = now_time;
            }
            localStorage.setItem('dir', JSON.stringify(dir));
            localStorage.setItem('count', Object.keys(dir).length);
            localStorage.setItem(key, content);
        }

        if (typeof (no_repeat) == "undefined")
            setTimeout(autosave, 500);

    }
}

function editor_listened() {
    editor.on('load', function () {
        var dir = JSON.parse(localStorage.getItem('dir'));
        var key = clean_url(location.href);
        if (dir == null || dir[key] == undefined) {
            editor.importFile('epiceditor', '');
        } else {
            editor.importFile('epiceditor', localStorage[key])
        }
    });
}

// ----------------------------insert image part------------------------------

function show_image_insert_panel() {
    $('#cancel_image_insert').show();
    $('#image_insert_panel').fadeIn();
}

function hide_panel(panel, cancel_panel) {
    if (panel)
        panel.hide();
    if (cancel_panel)
        cancel_panel.hide();
}

function image_upload() {
    if (!$('#image_file').val()) {
        console.log('choose file first');
        return;
    }
    $('#upload_local_image').find('input[type=button]').attr('value',
        '提交中...');
    try {
        $.ajaxFileUpload({
            url: '/image_upload',
            secureuri: false,
            fileElementId: 'image_file',
            dataType: 'json',
            success: function (data) {
                if (data['status'] == 'success') {
                    $('#result').find('span').html(gen_mark(data['url']));
                    $('#result').slideDown();
                } else {
                    alert('请选择图片文件');
                }

                $('#upload_local_image').find('input[type=button]').attr(
                    'value', '提交');
            },
            error: function (data) {
                alert('error');
                $('#upload_local_image').find('input[type=button]').attr(
                    'value', '提交');
            }
        });
    } catch (e) {
        $('#upload_local_image').find('input[type=button]')
            .attr('value', '提交');
    }
}

function image_link() {
    var url = $('#outside_image_link').val();
    if (url == '') {
        alert('请先输入图片地址');
        return;
    }
    $('#result').find('span').html(gen_mark(url));
    $('#result').slideDown();
}

function show_upload_local_image() {
    $('#link_image').hide();
    $('#result').hide();
    $('#upload_local_image').show();
}

function show_link_outside_image() {
    $('#upload_local_image').hide();
    $('#result').hide();
    $('#link_image').show();
}

function gen_mark(url) {
    return '![](' + url + ')';
}

function show_go_to_page_panel() {
    $('#go_to_page_panel').find('#current_page').html($('.word').html());
    $('#go_to_page_panel').show();
    $('#cancel_page_jump').show();
}

function go_to_page(page) {
    var p = location.href;
    while (p.lastIndexOf('/') == p.length - 1) {
        p = p.substr(0, p.length - 1)
    }
    $.pjax({
        url: p.substr(0, p.lastIndexOf('/') + 1) + page,
        container: '#main'
    })
}

// --------------------------------submit
// part--------------------------------------

function submit_answer() {
    var raw = $(editor.editor).html();

    //服务端检查, 标签可能引起安全问题
    var rendered = $(editor.previewer).html();

    if (!(raw.trim())) {
        alert('内容不能为空');
        return false;
    }

    $('input[name=raw]').val(raw);
    $('input[name=rendered]').val(rendered);

    //提交前删除草稿
    return true;
}

// --------------author lml 14/12/10----------------
function add_comment(submit_btn, commented_id, type) {
    if (!((type == 'answer') || (type == 'question') || (type == 'note'))) {
        return;
    }
    var comments_dom = submit_btn.parents('.comments');
    var content = comments_dom.find('#content');
    $.ajax({
        url: '/' + type + '/comment/add',
        type: 'post',
        data: {
            commentedId: commented_id,
            content: content.val()
        },
        success: function (req) {
            if (req.status == 0) {
                //re get comment content and commentNum
                content.val("");
                comments_dom.find('.comments_list').empty();
                var feeds_dom = comments_dom.parents('.feeds');
                feeds_dom.find('#comment_num').text(req.commentNum);
                var show_comment = feeds_dom.find('.show_comment');
                tool_bar_action(show_comment);
            }
            console.log(req);
        },
        error: function () {
            console.log('error');
        }
    });
}

function set_create_new_feed_url(a_dom, book_id) {
    var page = $('.word').text();
    a_dom.attr('href', '/detail/' + book_id + '/' + page + '/new');
}

//---user.jsp---
function choose_panel_action(type, page) {
    switch (type) {
        case 0:
            reset_specific_url_div($('#questions_url'), page);
            panel_action($('#questions'));
            break;
        case 1:
            reset_specific_url_div($('#answers_url'), page);
            panel_action($('#answers'));
            break;
        case 2:
            reset_specific_url_div($('#notes_url'), page);
            panel_action($('#notes'));
            break;
        default :
            break;
    }
}

function reset_specific_url_div(url_div, page) {
    var raw_url = url_div.data('value');
    var root_url = raw_url.substr(0, raw_url.lastIndexOf('/') + 1);
    url_div.data('value', root_url.concat(page));
}


//---browse.jsp inventory---

function switch_inventory(book_id) {
    var inventory_switch = $('.inventory_switch');
    if (inventory_switch.find('.title').text() == "展开目录") {
        if ($('.inventory').children().length > 0) {
            $('.inventory').show();
        }
        else {
            get_inventory(book_id);
        }
        inventory_switch.find('.title').text("收起目录");
        inventory_switch.find('i').removeClass("icon-double-angle-down");
        inventory_switch.find('i').addClass("icon-double-angle-up");
    }
    else {
        inventory_switch.find('.title').text("展开目录");
        inventory_switch.find('i').removeClass("icon-double-angle-up");
        inventory_switch.find('i').addClass("icon-double-angle-down");
        $('.inventory').hide();
    }
}

function get_inventory(book_id) {
    $.ajax({
        url: '/json/book/' + book_id + '/sections',
        type: 'get',
        success: function (req) {
            $(req['sectionList']).each(function (index, section) {
                var new_chapter_dom = $(nano_template($(
                    '#chapter_template').html(), {
                    'section': section
                }));
                console.log(new_chapter_dom);
                $('.inventory').append(new_chapter_dom);
                $(section['subSections']).each(function (index, subSection) {
                    var new_section_dom = $(nano_template($(
                        '#section_template').html(), {
                        'section': subSection
                    }));
                    $('.inventory').append(new_section_dom);
                });

            });
            set_apostrophes();
        },
        error: function () {
            console.log('error');
        }
    });

}

function set_apostrophes() {
    var inv_entities = $('.inv_entity');
    for (var i = 0; i < inv_entities.length; i++) {
        set_apostrophe($(inv_entities[i]));
    }

}

function set_apostrophe(inv_entity) {
    inv_entity.find('.apostrophe').width(inv_entity.width() - get_entity_content_width(inv_entity))
        .text('- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ');
}

function get_entity_content_width(inv_entity) {
    if (inv_entity.has('.section_title').length > 0) {
        return inv_entity.find('.section_title').width() + inv_entity.find('.inv_page').width() + 40;
    }
    return inv_entity.find('.chapter_title').width() + inv_entity.find('.inv_page').width() + 20;
}

//---browse.jsp---
function choose_content(book_id, page) {
    var type = $('#cur_content_type').text();
    var url_div = $('#' + type + 's_url');
    reset_specific_url_div(url_div, page);
    switch_content($('#' + type + '_content'), book_id);
}

//switch content among question/note/attachment
function switch_content(filter_div, book_id) {
    filter_div.addClass('active');
    filter_div.siblings().removeClass('active');
    var type = (filter_div.data('filter'));
    var url_div = $('#' + type + 's_url');
    $.ajax({
        url: '/json' + url_div.data('value'),
        type: 'get',
        success: function (req) {
            window.history.pushState(null, "类型", url_div.data('value'));
            $('#pages_container').empty();
            var cur_page;
            $(req['feedList']).each(function (index, post) {
                var new_post_dom;
                if (post.type == 1) {
                    new_post_dom = $(nano_template($(
                        '#question_template').html(), {
                        'post': post
                    }));
                }
                else if (post.type == 2) {
                    new_post_dom = $(nano_template($(
                        '#note_template').html(), {
                        'post': post
                    }));
                }
                else if (post.type == 3) {
                    new_post_dom = $(nano_template($(
                        '#attachment_template').html(), {
                        'post': post
                    }));
                }
                if (cur_page == post.page) {
                    new_post_dom.find('.page_split').hide();
                }
                $('#pages_container').append(new_post_dom);
                cur_page = post.page;
            });

            var pagination = gen_paging(parseInt(req['current_page']),
                parseInt(req['total_page']),
                    '/browse/' + book_id + '/' + type + 's');
            $('#pages_container').append(pagination);
        },
        error: function () {
            console.log('error');
        }
    });
}

//index.jsp
function gen_pagination(current_page, total_page) {
    var pagination_div = gen_paging(current_page, total_page,
        '/dynamic');
    $('.pagination').replaceWith(pagination_div);
}

//---mail.jsp---
function get_replies(mail, dialog_id, cur_user_id) {
    var my_replies = mail.find('.my_replies');
    $.ajax({
        url: '/json/pmessage/' + dialog_id + '/dp',
        type: 'get',
        success: function (req) {
            my_replies.empty();
            $(req['privateMessageList']).each(function (index, mail) {
                var template_name = 'replier_template';
                if (cur_user_id == mail.senderId) {
                    template_name = 'sender_template';
                }
                var new_reply_dom = $(nano_template($(
                        '#' + template_name).html(), {
                    'mail': mail
                }));
                my_replies.append(new_reply_dom);
            });
            my_replies.toggle();
        },
        error: function () {
            console.log('error');
        }
    });
}

//---new.jsp---
function show_file_upload_panel() {
    //$('#cancel_image_insert').show();
    $('#file_upload_panel').fadeIn();
}

function file_upload() {

    if (!$('#attachment_file').val()) {
        console.log('请先选择文件');
        return;
    }

    $('#upload_attachment_file').find('input[type=button]').attr('value',
        '提交中...');
    try {
        var file_name = get_file_name($('#attachment_file').val());
        $.ajaxFileUpload({
            url: '/json/attachment_upload',
            secureuri: false,
            fileElementId: 'attachment_file',
            dataType: 'json',
            success: function (data) {
                $('.upload_button').text("继续上传");
                $('#upload_attachment_file').find('input[type=button]').attr('value',
                    '提交');
                $('#file_upload_panel').fadeOut();

                console.log(data);
                if (data['attachmentId'])
                    var new_attachment_entity_dom = $(nano_template($(
                        '#attachment_entity_template').html(), {
                        'attachmentId': data['attachmentId']
                    }));

                $(new_attachment_entity_dom).find('.attachment_name').text(file_name);
                $('#list_title').show();
                $('.attachment_list').append(new_attachment_entity_dom);

            },
            error: function (data) {

                alert('error');
                $('#upload_attachment_file').find('input[type=button]').attr(
                    'value', '提交');
            }
        });
    } catch (e) {
        $('#upload_attachment_file').find('input[type=button]')
            .attr('value', '提交');
    }
}

function get_file_name(file_path) {
    return file_path.substr(file_path.lastIndexOf('\\') + 1);
}

function set_attachment_list() {
    var attachmentIds = '';
    var entities = $('.attachment_list').children('.attachment_entity');
    $(entities).each(function (index, entity) {
        attachmentIds += $(entity).find('.attachment_id').text() + '.';
    });
    //去掉最后一个点
    attachmentIds = attachmentIds.substring(0, attachmentIds.length - 1);
    console.log($('input[name=attachmentIds]').val());
    $('input[name=attachmentIds]').val(attachmentIds);

}

function hide_panel(panel) {
    panel.hide();
}

function test_get_page() {
    console.log($('.regular_page:first').data('page'));
    console.log($('.regular_page:last').data('page'));
}

function find_cur_section(page) {
    $('.inv_entity').removeClass("cur_inventory");
    $('.inv_entity').each(function (index, entity) {
            if ((page >= $(entity).children('.inv_start_page').text()) && (page <= $(entity).children('.inv_end_page').text())) {
                $(entity).addClass("cur_inventory");
            }
        }
    );
}

function inventory_jump(entity) {

    var inv_start_page = entity.children('.inv_start_page').text();
    if ((inv_start_page > $('.regular_page:last').data('page')) || (inv_start_page < $('.regular_page:first').data('page'))) {
        $('.notice').text("所选章节不在当前页");
        $('.notice').fadeIn().delay(300).fadeOut(50);
    }
    else {
        var pages = $('.regular_page');
        for (var i = 0; i < pages.length; i++) {
            if ($(pages[i]).data('page') >= inv_start_page) {
                //加上导航条距离
                $("html,body").scrollTop($(pages[i]).offset().top - 50);
                if ($(pages[i]).data('page') > entity.children('.inv_end_page').text()) {
                    $('.notice').text("没有该章节资源，已跳转到临近章节");
                    $('.notice').fadeIn().delay(300).fadeOut(50);
                }
                break;

            }
        }
    }

}

//browse.jsp
function load_page_cover(book_id) {
    $.ajax({
        url: '/page/' + book_id + '/0',
        type: 'get',
        success: function (req) {
            if (req.status == 'success') {
                if (req.page == '0') {
                    var new_dom = $(nano_template($('#cover_page_template')
                        .html(), {
                        book: req.book
                    }));

                    $('#pages_container').prepend(new_dom);
                    /*
                    //保持目前窗口不移动. 20是flag_dom的上下margin
                    $(window).scrollTop(
                            $(window).scrollTop() + new_dom.height()
                            - flag_dom.height() - 20);*/
                }

            } else
                error_handle(req);
        },
        error: function (req) {
            error_handle(req);
        }
    });
}
