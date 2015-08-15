#Picker
Picker是一个基于实体书的知识分享平台。在Picker上，您可以自由地分享阅读过程中的心得体会，可以发布自己的疑问，也可以回答他人提出的精彩问题，展示自己博学多才的一面。使用Picker，可以使您获得一个愉快的阅读体验！<br>

Picker的后台使用Spring MVC+hibernate+mysql，实现添加书籍，提问、做笔记，上传附件、照片，关注用户、关注问题，私信、好友动态，好友圈等功能。<br>

下面是功能交互图:
<br>
![](https://github.com/didihe1988/Picker/raw/master/screenshot/function.png)

#package介绍
**Controller层**<br>
&nbsp;&nbsp;&nbsp;&nbsp;.controller:    提供了返回@ResponseBody的url，web端的动态渲染使用。<br>
&nbsp;&nbsp;&nbsp;&nbsp;.controller.rest(RESTful):   提供了返回Json格式的url，安卓端和web端的ajax使用。<br>
**Service层**<br>
&nbsp;&nbsp;&nbsp;&nbsp;.service:       Service层对外接口<br>
&nbsp;&nbsp;&nbsp;&nbsp;.service.impl:  对应Service层对外接口的实现<br>
&nbsp;&nbsp;&nbsp;&nbsp;.service.support.comment:  生成Answer的CommentDisplay和Feed的CommentDisplay<br>
&nbsp;&nbsp;&nbsp;&nbsp;.service.support.feed:  生成FeedDisplay(分为NoteDisplay和QuestionDisplay)<br>
**Dao层**<br>
&nbsp;&nbsp;&nbsp;&nbsp;.dao:           Dao层的对外接口<br>
&nbsp;&nbsp;&nbsp;&nbsp;.dao.impl(implement):   对应Dao层对外接口的实现<br>
&nbsp;&nbsp;&nbsp;&nbsp;.dao.interfaces:  根据Dao层的几个类共有的特点，抽出的接口(interface)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BaseDao:  封装了基本的Dao操作<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NumOperationDao: 该Dao类含有数字操作<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OperationValidation: 该Dao类含有验证合法性的操作<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RageRelatedOperation:  该Dao类含有页码相关操作<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SearchOperation: 该Dao类含有搜索操作<br>
**Model层**<br>
&nbsp;&nbsp;&nbsp;&nbsp;.model:         model层(bean层)<br>
&nbsp;&nbsp;&nbsp;&nbsp;.model.display:     为web端(安卓端)显示需要而创建的model，比如说一条提问显示的时候不仅需要提问自身的东西，还需要提问者的名字和头像<br>
&nbsp;&nbsp;&nbsp;&nbsp;.model.form:    web端(安卓端)提交(POST方法)时使用<br>
&nbsp;&nbsp;&nbsp;&nbsp;.model.interfaces: 根据model层的几个类共有的特点，抽出的接口(interface)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;isFavorite:  该model能被点赞<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;isFollow:    该mode能被关注<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SeachModel:  该model应可以被搜索<br>
&nbsp;&nbsp;&nbsp;&nbsp;.model.json:             web端传Json使用<br>
**其他**<br>
&nbsp;&nbsp;&nbsp;&nbsp;.common:       使用的常量内容<br>
&nbsp;&nbsp;&nbsp;&nbsp;.utils:        一些工具类<br>



#页面介绍
登陆\注册页面
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/login.png)
<br>
<br>
动态页面:显示您关注的好友的最新动态
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/dynamic.png)
<br>
<br>
搜索页面:可以搜索书籍、问题、笔记、附件、用户
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/search.png)
<br>
<br>
个人主页
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/profile.png)
<br>
<br>
个人主页-发表的笔记
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/profile_note.png)
<br>
<br>
书籍页面
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/book.png)
<br>
<br>
提问题\写笔记\上传附件
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/upload.png)
<br>
<br>
私信页面
![](https://github.com/didihe1988/Picker/raw/master/screenshot/pages/mail.png)
