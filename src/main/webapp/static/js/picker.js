﻿/**
 * Created by youzh_000 on 2014/7/24.
 */

//---------------------------------base part------------------------------

/**
 * Created by ns on 14-7-6.
 */

$(document).ready(function(){
    nav_thing();
    pjax_init();
});

function nano_template(template, data) {
    //hack patch. 为了解决模板中图片地址错误地问题
    data['hack'] = {
        s: '<!--',
        e: '-->'
    };
    return template.replace(/\{([\w\.\(\)]*)\}/g, function(str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++){
            if(keys[i].match(/\(\)/)) v = v[keys[i].replace(/\(\)/, '')]();
            else v = v[keys[i]];
        }
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
}

//关于导航条的动作
function nav_thing(){
    $('#nav_mail_icon').find('a').hover(function () {
        $('#nav_mail_cnt').css({'background-color': '#009EDA'});
    }, function () {
        $('#nav_mail_cnt').css({'background-color': '#FF9933'});
    });
    $('#nav_msg').find('a').hover(function () {
        $('#nav_msg_cnt').css({'background-color': '#009EDA'});
    }, function () {
        $('#nav_msg_cnt').css({'background-color': '#FF9933'});
    });
}

//消息，私信信息获取
function get_cnt() {
    $.get('/new_message_count', function (req) {
        if(req['mail']){
            $('#nav_mail_cnt').html(req['mail']).css({display: 'inline-block'});
        }
        else{
            $('#nav_mail_cnt').html('0').css({display: 'none'})
        }
        if(req['message']){
            $('#nav_msg_cnt').html(req['message']).css({display: 'inline-block'});
        }
        else{
            $('#nav_msg_cnt').html('0').css({display: 'none'});
        }
    });
}

var g_pre_page = '';
function pre_page_url() {
    if(arguments[0]){
        g_pre_page = arguments[0];
        return arguments[0];
    }
    else{
        return g_pre_page;
    }
}

function pjax_init() {
    if ($.support.pjax) {
        $(document).on('click', 'a[data-pjax]', function(event) {
            pre_page_url(window.location.href);
            $.pjax.click(event, {container: $('#main')});
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

//--------------------------------------index part-------------------------------
function get_drafts_count() {
    if(localStorage['count']){
        $('#drafts_count').html(localStorage['count'])
    }
}

function show_full(feed_text) {
    var p = feed_text.parents('.feeds');

    if(p.find('.feed_full_text').html() == ''){
        NProgress.start();
        $.ajax({url: '/full/'+feed_text.data('passage-id'), success: function (req) {
            NProgress.done();
            p.children('.feed_brief').hide();
            p.find('.feed_full_text').html(req['data']);
            p.find('.feed_full').show();
        }, error: function () {
            NProgress.done();
        }});
    }else{
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
        up: function (pid, feeds) {
            $.ajax({url: '/up/'+pid, success: function (req) {
                if(req.status == 'success'){
                    feeds.find('.up').hide();
                    feeds.find('.cancel_up').fadeIn();
                }
            }});
        },
        cancel_up: function (pid, feeds) {
            $.ajax({url: '/cancel_up/'+pid, success: function (req) {
                if(req.status == 'success'){
                    feeds.find('.cancel_up').hide();
                    feeds.find('.up').fadeIn();
                }
            }});
        },
        watch: function (pid, feeds) {
            $.ajax({url: '/watch/' + pid, success: function (req) {
                if (req.status == 'success') {
                    feeds.find('.watch').hide();
                    feeds.find('.cancel_watch').fadeIn();
                }
            }});
        },
        cancel_watch: function (pid, feeds) {
            $.ajax({url: '/cancel_watch/'+pid, success: function (req) {
                if(req.status == 'success') {
                    feeds.find('.cancel_watch').hide();
                    feeds.find('.watch').fadeIn();
                }
            }})
        },
        get_comment: function (pid, feeds) {
            var comments_dom = feeds.find('.comments');
            feeds.find('.show_comment').hide();
            feeds.find('.hide_comment').show();
            if(comments_dom.find('.comments_list').html() == '')
            {
                comments_dom.show();
                NProgress.start();
                var error_handle = function () {
                    NProgress.done();
                    comments_dom.find('.waiting').hide();
                };
                $.ajax({url: '/comment/'+pid, success: function (req) {
                    if(req.status == 'success'){
                        $.each(req.comments, function (i, comment) {
                            var new_comment_dom =
                                $(nano_template($('#comment_template').html(), {'comment': comment}));
                            comments_dom.find('.comments_list').append(new_comment_dom);
                        });
                        NProgress.done();
                        comments_dom.find('.waiting').hide();
                        comments_dom.find('.comments_list').slideDown();
                    }else{
                        error_handle();
                    }
                }, error: error_handle});
            }else{
                comments_dom.slideDown();
            }
        },
        hide_comment: function (pid, feeds) {
            feeds.find('.hide_comment').hide();
            feeds.find('.show_comment').show();
            feeds.find('.comments').slideUp();
        }
    };

    action[tool_div.data('action')](tool_div.data('passage-id'), tool_div.parents('.feeds'));
}

//-------------------------------------message part---------------------------------
function go_back() {
    var pre = pre_page_url();
    if(pre!='' && pre!=window.location.href){
        history.go(-1);
    }
    else{
        window.location.href='/';
    }
}

//-------------------------------------mail part-------------------------------------
function toggle_my_reply(mail) {
    mail.find('.my_replies').toggle();
}

function read_flag(button) {
    var mail_id = button.data('mail-id');

    button.html('<i class="icon-spinner icon-spin"></i>');
    var error_handle = function () {
        button.html('已读');
    };
    $.ajax({url: '/mail_read_flag/'+mail_id, success: function (req) {
        if(req.status == 'success'){
            button.hide();
        }
        else{
            error_handle();
        }
    }, error: error_handle});
}

//------------------------------------user part-----------------------------------------
/**
 * Created by youzh_000 on 2014/7/15.
 */

function do_follow(t) {
    t.hide();
    t.parent().find('.cancel_follow').show();
}

function cancel_follow(t) {
    t.hide();
    t.parent().find('.follow_action').show();
}

//面板之间的切换操作及动画
function set_init_panel(obj) {
    var flag_class = 'current_panel';
    if($('.'+flag_class).length == 0){
        obj.addClass(flag_class);
        return true;
    }
    return false;
}

function panel_action(t) {
    var handles = {
        'flag_class': 'current_panel',
        'current_panel': function () {
            var cp = $('.'+handles.flag_class);
            if(cp.length == 0){
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
            this.showPanel($('#questions_panel'));
        },
        'answers': function () {
            this.showPanel($('#answers_panel'));
        },
        'notes': function () {
            //修改url
            window.history.pushState(null, '全部笔记', '/user/1234/notes/1');
            var notes_dom = $('#notes_panel');
            //check
            if(notes_dom.find('.content').data('current_page') == 0){
                //第一次更新
                $.ajax({
                    url: '/user/1234/notes/1/get',
                    type: 'get',
                    success: function (req) {
                        notes_dom.find('.wait').hide();
                        var notes = [];
                        var with_picture_template =
                            $('#notes_feed_with_picture_template').html();
                        var no_picture_template =
                            $('#notes_feed_with_no_picture_template').html();
                        $.each(req['notes'], function (i, note) {
                            if(note['picture']){
                                notes.push(nano_template(with_picture_template, {note: note}))
                            }else{
                                notes.push(nano_template(no_picture_template, {note: note}))
                            }
                        });

                        //生成分页
                        var current_page = parseInt(req['current_page']);
                        var total_page = parseInt(req['total_page']);
                        if(total_page > 1){
                            var pagination_template = $('#pagination_template').html();
                            var handle = {
                                gen_link: function (page) {
                                    return '/'
                                },
                                pre: function () {
                                    if(current_page == 1){
                                        return '';
                                    }else{
                                        return '<li><a href="' +
                                            this.gen_link(current_page-1) +
                                            '">&laquo;</a></li>';
                                    }
                                },
                                paging: function () {
                                    var p = [];
                                    for(var i = 1; i <= total_page; ++i){
                                        if(i == current_page){
                                            p.push(nano_template(
                                                '<li class="active"><a href="{t.link}">{t.page}</a></li>', {t:{
                                                    link: this.gen_link(i), page:i
                                                }}));
                                        }
                                        else{
                                            p.push(nano_template(
                                                '<li><a href="{t.link}">{t.page}</a></li>', {t:{
                                                    link: this.gen_link(i), page:i
                                                }}));
                                        }
                                    }
                                    return p.join('');
                                },
                                next: function () {
                                    if(current_page == total_page){
                                        return '';
                                    }else{
                                        return '<li><a href="' +
                                            this.gen_link(current_page+1) +
                                            '#">&raquo;</a></li>';
                                    }
                                }
                            };
                            notes.push(nano_template(pagination_template, {pagination: handle}))
                        }

                        notes_dom.find('.content').html(notes.join(''));
                    },
                    error: function (req) {
                        console.log('error')
                    }
                })
            }
            this.showPanel(notes_dom);
        },
        'showPanel': function (e) {
            var cp = handles.current_panel();
            if(e.attr('id') === cp.attr('id'))
                return;

            cp.animate({left: -cp.outerWidth(true)}, function () {
                $(this).hide();
                $(this).css({left: 0});
                e.fadeIn();
                cp.removeClass(handles.flag_class);
                e.addClass(handles.flag_class);
            }).bind(this);
        },
        'slideBack': function (e) {
            var cp = handles.current_panel();
            if(e.attr('id') === cp.attr('id'))
                return;

            cp.animate({left: cp.outerWidth(true)}, function () {
                $(this).hide();
                $(this).css({left: 0});
                e.fadeIn();
                cp.removeClass(handles.flag_class);
                e.addClass(handles.flag_class);
            });
        }
    };

    if(t.data('tab')){
        handles[t.data('tab')]();
    }
    else{
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


function group_search(form){
    var name = form.find('input').val();
    if(name == ''){
        return false;
    }
    //动态请求
    NProgress.start();
    $.ajax({
        url: '/group_search',
        type: 'get',
        data: {group_name: name},
        success: function (req) {
            NProgress.done();

            var groups = [];
            $.each(req['groups'], function (i, group) {
                groups.push(nano_template($('#group_info_template').html(),
                    {group: group}));
            });
            $('#group_search_content').html(groups.join(''));
        },
        error: function (req) {
            NProgress.done();
            $('#group_search_content').html('查询出错')
        }
    });

    return false;   //阻止form提交
}
//-----------------------------------browse part-------------------------------

function gen_dom(post) {
    if(post['picture'] == undefined || post['picture'] ==''){
        post['text_part'] = nano_template(
            $('#text_with_no_picture_template').html(), {post: post});
    }
    else{
        post['text_part'] = nano_template(
            $('#text_with_picture_template').html(), {post: post}
        );
    }
    if(post['type'] == 'note'){
        var new_dom = nano_template(
            $('#note_template').html(), {post: post}
        )
    }
    else{
        //question
        var new_dom = nano_template(
            $('#question_template').html(), {post: post}
        )
    }
    return new_dom;
}

/*
传入一个flag元素(load_next_flag或者load_previous_flag)。通过ajax动态加载内容
 */
function load_page(flag_dom) {
    if(flag_dom.data('lock') == true || flag_dom.data('end') == true){
        return;
    }
    flag_dom.data('lock', true);        //上锁
    flag_dom.slideDown();

    if(flag_dom.data('end') == false){
        var book_id = $('#book_meta').data('book-id');
        var start_page = flag_dom.data('start-page');
        var filter = $('.filter').find('.active').data('filter');
        var direct = flag_dom.data('direction');
        var error_handle = function(req){
            flag_dom.hide();
            flag_dom.data('lock', false);   //解锁
        };
        $.ajax({
            url: '/page/'+book_id+'/'+start_page,
            type: 'get',
            data: {filter: filter, direction: direct},
            success: function (req) {
                flag_dom.hide();
                if(req.status == 'success'){
                    //展示内容，封面，问答，笔记。
                    if(req.page == '0'){
                        var new_dom = $(nano_template(
                            $('#cover_page_template').html(), {book: req.book}));

                        if(direct == 'up'){
                            flag_dom.data('end', true);
                            $('#pages_container').prepend(new_dom);
                            //保持目前窗口不移动. 20是flag_dom的上下margin
                            $(window).scrollTop(
                                $(window).scrollTop()+new_dom.height()-flag_dom.height()-20
                            );
                        }
                        else
                            $('#pages_container').append(new_dom);
                    }
                    else{
                        var feeds = [];
                        $.each(req['posts'], function (i, post) {
                            feeds.push(gen_dom(post));
                        });
                        req['feeds'] = feeds.join('');
                        var new_dom = $(
                            nano_template($('#page_template').html(), {req: req}));
                        if(direct == 'up'){
                            $('#pages_container').prepend(new_dom);
                            $(window).scrollTop(
                                //保持目前窗口不移动. 20是flag_dom的上下margin
                                $(window).scrollTop()+new_dom.height()-flag_dom.height()-20
                            )
                        }
                        else
                            $('#pages_container').append(new_dom);
                    }
                    if(req.end == 'true')
                    {
                        flag_dom.data('end', true);
                        //没有内容了
                        if(direct == 'down')
                            $('#pages_container').append(
                                $(nano_template($('#page_end_template').html(), {})));
                    }
                    if(direct == 'up')
                        flag_dom.data('start-page', parseInt(req.page)-1);
                    else
                        flag_dom.data('start-page', parseInt(req.page)+1);
                    flag_dom.data('lock', false);       //解锁
                }
                else
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
    $(window).scroll(function () {
        if(timer){
            clearTimeout(timer);
        }

        var settled = false;
        timer = setTimeout(function () {
            //设置当前显示的页数
            $('.page').each(function (i, c) {
                if(!settled) {
                    var top = $(c).offset().top;
                    var scrollTop = $(window).scrollTop() + 50;
                    var page = $(c).data('page');
                    if(top < scrollTop && scrollTop < top+$(c).height()){
                        var word_dom = $('.current_page').find('.word')
                        if(word_dom.html() != page){
                            word_dom.hide().html($(c).data('page')).fadeIn();
                        }
                        settled = true
                    }
                }
            });

            //底部触发更新
            if($(window).scrollTop()+$(window).height() == $(document).height()){
                load_page($('#load_next_flag'));
            }
            if($(window).scrollTop() == 0){
                load_page($('#load_previous_flag'));
            }
            //TODO: listen special key, turn up the time limit
        }, 10);
    })
}

//---------------------------------detail part------------------------------

function clean_url(url) {
    return url.replace(/\?.*/, '').replace(/#+/, '')
}

function create_editor(){
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

function autosave() {
    if($('#epiceditor').length){
        editor.save();
        var content = editor.exportFile();
        var dir = JSON.parse(localStorage.getItem('dir'));
        if(dir == null)
            dir = {};
        var key = clean_url(current_url);
        if(content == ''){
            delete dir[key];
        }
        else{
            var date = new Date();
            var now_time = date.toLocaleTimeString();
            if(dir[key] == undefined){
                dir[key] = {
                    create: now_time,
                    last_modified: now_time,
                    title: $('.detail_title').html()
                }
            }
            else{
                dir[key]['last_modified'] = now_time;
            }
            localStorage.setItem('dir', JSON.stringify(dir));
            localStorage.setItem('count', Object.keys(dir).length);
            localStorage.setItem(key, content);
        }

        setTimeout(autosave, 500);
    }
}

function editor_listened() {
    editor.on('load', function () {
        var dir = JSON.parse(localStorage.getItem('dir'));
        var key = clean_url(location.href);
        if(dir == null || dir[key] == undefined){
            editor.importFile('epiceditor', '');
        }
        else{
            editor.importFile('epiceditor', localStorage[key])
        }
    });
}