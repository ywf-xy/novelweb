<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>歪风小说</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
          type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bookshelf.css"/>

    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
            charset="UTF-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/bookshelf.js" type="text/javascript"
            charset="UTF-8"></script>
</head>
<body>
<div class="Header">
    <div>
        <a href="${pageContext.request.contextPath}/" class="log"><img
                src="${pageContext.request.contextPath}/static/picture/log.png"/></a>
        <div class="so">
            <select id="selectmodel">
                <option label="novel">小说</option>
                <option label="auth">作者</option>
            </select>
            <input type="text" name="searchbox" id="searchbbox" required="required"/>
            <button class="sub" onclick="searchbtn()">搜索</button>
        </div>
        <dl class="nav">
            <c:if test="${user_auth.nick_name==null && user_reader.nick_name==null}">
                <dd>
                    <div>
                        <span class="login_log"></span>
                        <a href="${pageContext.request.contextPath}/loginAndRegist/login">登录</a>
                    </div>
                </dd>
                <dd>
                    <div>
                        <span class="login_log"></span>
                        <a href="${pageContext.request.contextPath}/loginAndRegist/regist">注册</a>
                    </div>
                </dd>
            </c:if>
            <c:if test="${user_auth.nick_name!=null}">
                <dd>
                    <div>
                        <span><img src="" alt="404"></span>
                        <a href="">${user_auth.nick_name}</a>
                        <a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
                    </div>
                </dd>
            </c:if>
            <c:if test="${user_reader.nick_name!=null}">
                <dd>
                    <div>
                        <span>
                            <img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}"
                                 alt="404" id="headimage">
                        </span>
                        <a href="${pageContext.request.contextPath}/reader/getusermsg">欢迎！${user_reader.nick_name}</a>
                        <a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
                    </div>
                </dd>
            </c:if>

            <dd>
                <div>
                    <span class="booklib_log"></span>
                    <c:if test="${user_reader.nick_name!=null}">
                        <a href="/wfRead/reader/personshelf?nick_name=${user_reader.nick_name}" >书架</a>
                    </c:if>
                    <c:if test="${user_auth.nick_name!=null}">
                        <a href="/wfRead/reader/personshelf?nick_name=${user_auth.nick_name}">书架</a>
                    </c:if>
                    <c:if test="${user_reader.nick_name==nulls&&user_auth.nick_name==null}">
                        <a href="">书架</a>
                    </c:if>
                </div>
            </dd>
        </dl>
    </div>
</div>
<div class="NavBar">
    <div>
        <a href="${pageContext.request.contextPath}/"><span>首页</span></a>
        <a href="${pageContext.request.contextPath}/xuanhuan"><span>玄幻</span></a>
        <a href="${pageContext.request.contextPath}/kehuan"><span>科幻</span></a>
        <a href="${pageContext.request.contextPath}/lishi"><span>历史</span></a>
        <a href="${pageContext.request.contextPath}/dushi"><span>都市</span></a>
        <a href="${pageContext.request.contextPath}/wuxia"><span>武侠</span></a>
        <a href="${pageContext.request.contextPath}/wangyou"><span>网游</span></a>
        <a href="${pageContext.request.contextPath}/rank"><span>排行</span></a>
        <a href="${pageContext.request.contextPath}/classification"><span>分类</span></a>
    </div>
</div>
<!----主体-->
<div id="main">
    <div id="tips_head" align="center">
        <span>您的书架可收藏20本，已收藏 0 本。 </span>
    </div>
    <br/>
    <hr style="width: 800px;"/>
    <table id="books" align="center">
        <thead>
        <tr>
            <td>书名</td>
            <td>最新章节</td>
            <td>书签</td>
            <td>更新日期</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${bookshelfs}" var="item">
            <tr>
                <td>${item.reader_name}</td>
                <td>${item.novel_name.book_name}</td>
                <td>${item.bookmark}</td>
                <td><fmt:formatDate value="${item.novel_name.update_time}" type="both"/></td>
                <td><button name="${item.id}" class="deletebtn">删除</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<!--底部-->
<div class="Footer">
    <p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>
