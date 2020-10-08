<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
		<meta charset="utf-8"/>
		<title>歪风小说</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/book.css"/>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/book.js" type="text/javascript"
		        charset="utf-8"></script>
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
												<span><img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}" alt="404" id="headimage"></span>
												<a href="${pageContext.request.contextPath}/reader/getusermsg">欢迎！${user_reader.nick_name}</a>
												<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
										</div>
								</dd>
						</c:if>

						<dd>
								<div>
										<span class="booklib_log"></span>
										<a href="">书架</a>
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
<div style="text-align: center;margin: 0px auto;padding: 5px 10px;width: 100%;background: #f7f7f7;height: 25px;ine-height: 25px;float: left;">
		<p style="display: none;">asdlas</p>
</div>
<!--主体-->
<div class="Main">
		<div class="Mina_left">
				<div id="Mian_left_head"><h2>玄幻</h2></div>
				<div class="book_msg">
						<div class="msg_left"><img src="" alt="404"/></div><!--220*283-->
						<dl class="msg_right">
								<h2>
										<dt><a href=""></a></dt>
								</h2>
								<dd id="type"><span>小说类别：</span><a href=""></a></dd>
								<dd id="auth"><span>作者：</span><a href=""></a></dd>
								<dd id="state"><span>状态：</span><a href=""></a></dd>
								<dd id="words"><span>字数：</span><a href=""></a></dd>
								<dd id="updatetime"><span>更新日期：</span><a href=""></a></dd>
						</dl>
						<dl class="mag_bottom">
								<div class="xz_title">小说下载 || 在线阅读</div>
								<ul class="xz_ul">
										<li><a href="" id="read">在线阅读</a></li>
										<li><a href="${pageContext.request.contextPath}/read/1" id="download">全本下载</a>
										</li>
								</ul>
						</dl>
				</div>
				<div class="other_msg">
						<!--小说简介-->
						<div class="bookintrobox">
								<div class="box_title">小说介绍</div>
								<div class="bookintro"><p>
										<strong>御九天: </strong>王峰创造了一款游戏“御九天”，在即将功成名就，走向人生巅峰的时候，穿越到了“九天世界”。 可是穿越必备的辅助系统呢？
										不是高富帅也就罢了，毕竟王家兄弟是个有才华的人，可，地牢小皮鞭开局是什么鬼？ 无外挂，不穿越，面对来自灵魂的拷问，三观奇正的老王开始了人生的第二次奋斗。
										兄弟们，老王又回来了！
								</p>
								</div>
						</div>
						<div class="bookmenubox">
								<div class="box_title">最新⑩章</div>

								<div class="newlist10">
										<ul>
												<li>
														<a href="/read/86475/46278563.html" target="_blank">第二百四十六章 情敌来了
																- 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46246999.html" target="_blank">第二百四十五章 黑吃黑
																- 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46246998.html" target="_blank">第二百四十四章
																赚钱的第一要素 - 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46224324.html" target="_blank">第二百四十三章
																能看能聊能亲能打 - 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46175065.html" target="_blank">第二百四十二章
																壮阳的小眼神 - 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46175063.html" target="_blank">第二百四十一章 德邦公国
																- 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46155098.html" target="_blank">第二百四十章 妲哥峰弟
																- 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46079084.html" target="_blank">第二百三十九章
																还是套路得人心 - 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/46079081.html" target="_blank">第二百三十八章 喝酒运动
																- 2020-07-27</a>
												</li>
												<li>
														<a href="/read/86475/45995526.html" target="_blank">第二百三十七章
																十三兽神将 - 2020-07-27</a>
												</li>
										</ul>
								</div>

								<div class="box_title">小说目录</div>
								<div id="bookmenu">
										<div id="bookmenu_list">
												<ul>
														<li>
																<a href="/read/86475/46278563.html" target="_blank">第二百四十六章
																		情敌来了 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46246999.html" target="_blank">第二百四十五章
																		黑吃黑 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46246998.html" target="_blank">第二百四十四章
																		赚钱的第一要素 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46224324.html" target="_blank">第二百四十三章
																		能看能聊能亲能打 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46175065.html" target="_blank">第二百四十二章
																		壮阳的小眼神 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46175063.html" target="_blank">第二百四十一章
																		德邦公国 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46155098.html" target="_blank">第二百四十章
																		妲哥峰弟 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46079084.html" target="_blank">第二百三十九章
																		还是套路得人心 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46079081.html" target="_blank">第二百三十八章
																		喝酒运动 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/45995526.html" target="_blank">第二百三十七章
																		十三兽神将 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46278563.html" target="_blank">第二百四十六章
																		情敌来了 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46246999.html" target="_blank">第二百四十五章
																		黑吃黑 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46246998.html" target="_blank">第二百四十四章
																		赚钱的第一要素 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46224324.html" target="_blank">第二百四十三章
																		能看能聊能亲能打 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46175065.html" target="_blank">第二百四十二章
																		壮阳的小眼神 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46175063.html" target="_blank">第二百四十一章
																		德邦公国 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46155098.html" target="_blank">第二百四十章
																		妲哥峰弟 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46079084.html" target="_blank">第二百三十九章
																		还是套路得人心 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/46079081.html" target="_blank">第二百三十八章
																		喝酒运动 - 2020-07-27</a>
														</li>
														<li>
																<a href="/read/86475/45995526.html" target="_blank">第二百三十七章
																		十三兽神将 - 2020-07-27</a>
														</li>
												</ul>

												<div class="bottom_box"><span class="more"><<<<<<<<<<<<<<<<<<<<显示更多>>>>>>>>>>>>>>>>>>>></显示更多></span>
												</div>

										</div>

								</div>

						</div>
						<div class="otherworkbox">
								<div class="box_title">相关作品</div>

								<div class="otherworkbox_body">
										<div class="box_header">
												作者
												<strong> <a href="" target="_blank">【骷髅精灵】</a> </strong>的相关作品
												<span id="showlist">+ 显示列表</span>
										</div>

										<div id="page" style="display: block;">
												<ul class="zznm">
														<li class="zzn1"><a href=""
														                    target="_blank">·穿越小说小说《星战风暴》全集下载</a></li>
														<li class="zzn2">字数：521万</li>
														<li class="zzn3">更新：2016-11-01</li>
												</ul>
										</div>

								</div>
						</div>
				</div>
		</div>
		<div class="Mian_right">
				<!--本类热门下载-->
				<div class="rank_tittle">本类热门下载</div>
				<div class="rank_list">
						<ul class="rank_list_ul">
								<li><span class="seq1"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq2"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq3"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq4"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq5"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq6"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq7"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq8"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq9"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq10"></span><a href="">《美食大帝》全集</a></li>
						</ul>
				</div>

				<!--编辑推荐-->
				<div class="rank_tittle">编辑推荐</div>
				<div class="rank_list">
						<ul class="rank_list_ul">
								<li><span class="seq1"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq2"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq3"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq4"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq5"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq6"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq7"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq8"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq9"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq10"></span><a href="">《美食大帝》全集</a></li>
						</ul>
				</div>
		</div>
</div>
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>
