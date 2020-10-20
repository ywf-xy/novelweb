<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>歪风小说</title>
   <%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico" type="image/x-icon" />--%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/index.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="Header">
    <div>
        <a href="${pageContext.request.contextPath}/" class="log"><img src="${pageContext.request.contextPath}/static/picture/log.png"/></a>
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
                <dd >
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
                    <dd >
                        <div>
                            <span><img src="" alt="404"></span>
                            <a href="" >欢迎！${user_auth.nick_name}</a>
                            <a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
                        </div>
                    </dd>
                </c:if>
                <c:if test="${user_reader.nick_name!=null}">
                    <dd >
                        <div>
                            <span><img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}" alt="404" id="headimage"></span>
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
        <a href="${pageContext.request.contextPath}/"class='now'><span>首页</span></a>
        <a href="${pageContext.request.contextPath}/xuanhuan"><span>玄幻</span></a>
        <a href="${pageContext.request.contextPath}/kehuan"><span>科幻</span></a>
        <a href="${pageContext.request.contextPath}/lishi"><span>历史</span></a>
        <a href="${pageContext.request.contextPath}/dushi"><span>都市</span></a>
        <a href="${pageContext.request.contextPath}/wuxia"><span>武侠</span></a>
        <a href="${pageContext.request.contextPath}/wangyou"><span>网游</span></a>
        <a href="${pageContext.request.contextPath}/rank"><span>排行</span></a>
        <a href="${pageContext.request.contextPath}/classification"><span>分类</span></a>
        <a href="${pageContext.request.contextPath}/auth/authorUI"><span>作家专区</span></a>
    </div>
</div>
<!--主体-->
<div class="Main_1">
    <div id="wrap_1">
        <!--轮播-->
        <div id="wrap_11">
            <div id="wrap_11_content">
                <!--轮播大图-->
                <div class="currentImg">
                    <a href=""><img src="" alt="404" class="currentImg_show"/></a>
                </div>
                <!--缩略图-->
                <div class="readyImg">
                    <ul>
                        <li class="current"><a href=""><img src="" alt="404" /></a></li>
                        <li class="normal"><a href=""><img src="" alt="404" /></a></li>
                        <li class="normal"><a href=""><img src="" alt="404" /></a></li>
                        <li class="normal"><a href=""><img src="" alt="404" /></a></li>
                    </ul>
                </div>
                <!--简介-->
                <div class="currentImgMsg">
                    <ul>
                        <li class="current_msg">
                            <h3 class="currentImg_tittle"><a href="">斗破苍穹</a></h3>
                            <p class="currentImg_msg">这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？请观斗破苍穹</p>
                        </li>
                        <li class="normal_msg">
                            <h3 class="currentImg_tittle"><a href="">御九天</a></h3>
                            <p class="currentImg_msg">这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？请观斗破苍穹</p>
                        </li>
                        <li class="normal_msg">
                            <h3 class="currentImg_tittle"><a href="">魁拔神探</a></h3>
                            <p class="currentImg_msg">这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？请观斗破苍穹</p>
                        </li>
                        <li class="normal_msg">
                            <h3 class="currentImg_tittle"><a href="">神魔书</a></h3>
                            <p class="currentImg_msg">这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？请观斗破苍穹</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--编辑推荐-->
        <div id="wrap_12">
            <div id="wrap_12_content">
                <!--标题-->
                <div class="wrap_12_tittle"><h2>编辑强推</h2></div>
                <div class="wrap_12_mian">
                    <div id="recommend_1">
                        <h3><a href="">斗破苍穹</a></h3>
                        <p>这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？</p>
                    </div>
                    <div id="recommend_2">
                        <h3><a href="">斗破苍穹</a></h3>
                        <p>这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？</p>
                    </div>
                    <div id="recommend_3">
                        <h3><a href="">斗破苍穹</a></h3>
                        <p>这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？</p>
                    </div>
                    <div  id="recommend_4">
                        <h3><a href="">斗破苍穹</a></h3>
                        <p>这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？</p>
                    </div>
                </div>
            </div>
        </div>
        <!--本周强推-->
        <div id="wrap_13">
            <div id="wrap_13_content">
                <!--标题-->
                <div id="wrap_13_tittle"><h3>本周推荐</h3></div>
                <!--排行-->
                <div id="wrap_13_main">
                    <ul id="wrap_13_list">
                        <li><span class="wrap_13_list_1">1</span><a href="">万古神帝</a></li>
                        <li><span class="wrap_13_list_2">2</span><a href="">圣墟</a></li>
                        <li><span class="wrap_13_list_3">3</span><a href="">元尊</a></li>
                        <li><span class="wrap_13_list_4">4</span><a href="">史上最强店主</a></li>
                        <li><span class="wrap_13_list_5">5</span><a href="">九星毒奶</a></li>
                        <li><span class="wrap_13_list_6">6</span><a href="">飞剑问道</a></li>
                        <li><span class="wrap_13_list_7">7</span><a href="">兼职无常后我红了</a></li>
                        <li><span class="wrap_13_list_8">8</span><a href="">都市逆天神医</a></li>
                        <li><span class="wrap_13_list_9">9</span><a href="">一念永恒</a></li>
                        <li><span class="wrap_13_list_10">10</span><a href="">大主宰</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="Main_2">
    <!--榜单-->
    <div id="wrap_2">
        <!--榜单230*x left:15-->
        <div id="wrap_21">
            <h3>历史榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_22">
            <h3>网游榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_23">
            <h3>武侠榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_24">
            <h3>都市榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_25">
            <h3>仙侠榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_26">
            <h3>科幻榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_27">
            <h3>玄幻榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrap_28">
            <h3>军事榜</h3>
            <div>
                <ul>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_1">1</span></div>
                        <div class="list_bookname"><a href="">完美世界</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_2">2</span></div>
                        <div class="list_bookname"><a href="">从我是特种兵开始阅读变强</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_3">3</span></div>
                        <div class="list_bookname"><a href="">系统逼我做皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_4">4</span></div>
                        <div class="list_bookname"><a href="">大唐第一长子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_5">5</span></div>
                        <div class="list_bookname"><a href="">坤宁</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_6">6</span></div>
                        <div class="list_bookname"><a href="">三国之曹家逆子</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_7">7</span></div>
                        <div class="list_bookname"><a href="">三国之最强皇帝</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_8">8</span></div>
                        <div class="list_bookname"><a href="">小阁老</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_9">9</span></div>
                        <div class="list_bookname"><a href="">唐砖</a></div>
                    </li>
                    <li>
                        <div class="list_num"><span class="wrap_2_list_10">10</span></div>
                        <div class="list_bookname"><a href="">明天下</a></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div style="width: 100%;heigh:30px;float: left"><p style="width: 100%;heigh:30px;float: left"></p></div>
<div class="Footer">
    <p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>