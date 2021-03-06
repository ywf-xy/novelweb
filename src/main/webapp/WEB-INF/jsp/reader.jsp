<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
		<meta charset="utf-8"/>
		<title>歪风小说</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
			  type="image/x-icon"/>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reader.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/utils/list_bar.css"/>

		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/jquery-form.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
				charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/reader.js" type="text/javascript"
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
						<c:if test="${user_reader.nick_name!=null}">
								<dd>
										<div>
                        <span>
                            <img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}"
								 alt="404" class="headimage">
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
												<a href="/wfRead/reader/personshelf?nick_name=${user_reader.nick_name}">书架</a>
										</c:if>
										<c:if test="${user_reader.nick_name==null}">
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
				<a href="${pageContext.request.contextPath}/auth/authorUI"><span>作家专区</span></a>
		</div>
</div>
<!--主体-->
<div class="Main_1">
		<div class="wrapper">
				<!--侧边栏-->
				<div class="siderBar">
						<ul class="siderBar_list">
								<li class="active"><a href="#">首页</a></li>
								<li class=""><a href="#">阅读记录</a></li>
								<li class=""><a href="#">我的书评</a></li>
						</ul>
						<ul class="siderBar_list">
								<li class=""><a href="#">会员中心</a></li>
								<li class=""><a href="#">个人资料</a></li>
						</ul>
				</div>
				<!--主要部分-->
				<div class="contentGroup">
						<!--首页部分-->
						<div class="group1" style="display: block;">
								<div class="group1_topbox">
										<!--头像-->
										<div class="user_picture">
												<a href="" class="user_picture_a">
														<c:if test="${readermsg.headimage!=null}">
																<img src="${pageContext.request.contextPath}/static/user-photo/${readermsg.nick_name}/${readermsg.headimage}"
																	 alt="404" class="user_img"/>
														</c:if>
														<c:if test="${readermsg.headimage==null}">
														<img src="${pageContext.request.contextPath}/static/picture/default_user.png"
															 alt="404"
															 class="user_img"/></a>
												</c:if>

												<!--是否为vip-->
												<a href=""></a>
										</div>
										<!--用户简略信息-->
										<div class="user_msg">
												<h3>
														<a href="" id="user_name">${readermsg.nick_name}</a>
														<a href="" class="vip_leave">${readermsg.vip}</a>
												</h3>

												<div class="user_data">
													<span>
														<a href="">关注<b>0</b></a>
														<a href="">粉丝<b>${readermsg.fans}</b></a>
													</span>
												</div>

										</div>

								</div>

								<ul class="user_msg_list">
										<li class="msg_list">
												<div class="msg_list_title"><b>账户余额</b></div>
												<div class="msg_list_data"><a
														href=""><b>${readermsg.account_balance}</b>书币</a></div>
												<a href="" class="msg_list_more">充钱(就能变强)</a>
										</li>
										<li class="msg_list">
												<div class="msg_list_title"><b>我的票夹</b></div>
												<div class="msg_list_data">
														<span><b>${readermsg.monthly_tickets}</b>月票</span></div>
												<a href="" class="msg_list_more">立即查看</a></li>
										<li class="msg_list">
												<div class="msg_list_title"><b>我的书架</b></div>
												<div class="msg_list_data"><b>${readermsg.mybookshelf.size()}</b>本藏书
												</div>
												<a href="/wfRead/reader/personshelf?nick_name=${readermsg.nick_name}"
												   class="msg_list_more">立即查看</a></li>
								</ul>

						</div>

						<!--阅读记录部分-->
						<div class="group2" style="display: none;">
								<h2>我的阅读记录</h2>

								<div class="record_list">
										<table>
												<thead>
												<tr>
														<td>编号</td>
														<td>书名</td>
														<td>章节</td>
														<td>时间</td>
												</tr>
												</thead>
												<tbody>

												</tbody>
										</table>
										<div style="width: 100%;height: 100px;display: block;text-align: center;">
												<!--分页-->
												<div class="page_nav">
																<div class="page_nav_content">
																		<ul class="page_nav_list">
																				<li><a id="firstpage">首页</a></li>
																				<li><a id="prepage">上一页</a></li>
																				<li><a class="active_page" id="1th_page">1</a></li>
																				<li><a class="stay_page" id="2th_page">2</a></li>
																				<li><a class="stay_page" id="3th_page">3</a></li>
																				<li><a class="stay_page" id="4th_page">4</a></li>
																				<li><a class="stay_page" id="5th_page">5</a></li>
																				<li><a id="nextpage">下一页</a></li>
																				<li><a id="lastpage">尾页</a></li>
																		</ul>

																		<div class="page_opt">
																				<input type="text" class="current_page" value="1"/> /
																				<span class="all_page">999</span>&nbsp;页
																				<a id="gotopage">GO</a>
																		</div>
																</div>
														</div>
										</div>
								</div>
								<%----%>
						</div>

						<!--书评部分-->
						<div class="group4" style="display: none;">
								<h2>我的书评</h2>
								<div class="comment">
										<div class="tabs"><a href="" class="checked">发表的书评</a></div>
										<div class="comment_list">
												<table>
														<thead>
														<tr>
																<td>书评</td>
																<td>发布时间</td>
																<td>所在书评区</td>
														</tr>
														</thead>
														<tbody>
														<tr>
																<td></td>
																<td></td>
																<td></td>
														</tr>
														</tbody>
												</table>
												<div style="width: 100%;height: 100px;display: block;text-align: center;">
														<p>没有书评</p>
												</div>
										</div>
								</div>
						</div>

						<!--会员部分-->
						<div class="group5" style="display: none;">

						</div>

						<!--个人部分-->
						<div class="group6" style="display: none;">
								<h2>个人资料</h2>
								<div class="person_msg">
										<div class="tabs">
												<a class="checked">基本设置</a>
												<a class="">头像设置</a>
										</div>
										<div class="content">
												<div id="tabTarget1">
														<dl>
																<dd><label>昵称：</label><input type="text" id="nick_name"
																							 name="nick_name"
																							 value="${readermsg.nick_name}"/><span
																		id="nick_nameerror" class="erro"></span></dd>
																<dd><label>性别：</label><input type="radio" value="男"
																							 checked="checked"
																							 name="sex"/>男&nbsp;&nbsp;<input
																		type="radio" value="女" name="sex"/>女<span
																		id="sexerror" class="erro"></span></dd>
																<dd><label>生日：</label><input type="date" id="birthday"
																							 name="birthday"
																							 value="${readermsg.birth}"/><span
																		id="birthdayerror" class="erro"></span></dd>
																<dd><label>地址：</label><label>
																		<textarea id="addresstext"
																				  name="address">${readermsg.address}</textarea>
																</label><span id="addresserror" class="erro"></span>
																</dd>
																<dd><label>简介：</label><label>
																		<textarea id="introtext" name="intro" rows="5"
																				  cols="40">${readermsg.intro}</textarea>
																</label><span id="introerror" class="erro"></span>
																</dd>
																<dd>
																		<button class="tab1_sub">提交</button>
																</dd>
														</dl>
												</div>
												<div id="tabTarget2" style="display: none;">
														<c:if test="${readermsg.headimage!=null}">
																<img src="${pageContext.request.contextPath}/static/user-photo/${readermsg.nick_name}/${readermsg.headimage}"
																	 alt="404"/>
														</c:if>
														<c:if test="${readermsg.headimage==null}">
																<img src="${pageContext.request.contextPath}/static/picture/default_user.png"
																	 alt="404"/>
														</c:if>
														<div class="attention_text">
																<p>支持jpg、gif、png格式图片，上传文件大小不超过10MB。</p>
														</div>
														<div>
																<form action="/wfRead/reader/imgupload" method="post"
																	  enctype="multipart/form-data"
																	  id="fileupform">
																		<input type="file" class="fileup" name="imgfile"
																			   value="上传头像"/>
																</form>

																<button class="sysimg">选择系统文件</button>
														</div>
														<div class="systemImgs" style="display: none;">
																<ul>
																		<li class="systemImgs_item"><input
																				class="systemImgs_input" type="radio"
																				id=""/><label class="systemImgs_label"
																							  for=""><img src=""
																										  alt="404"/></label>
																		</li>
																</ul>
														</div>
														<button class="img_sub" id="imgupload">上传</button>
												</div>
												<div id="tabTarget3" style="display: none;">
														旧的密码：<input type="password" id="oldpwd"><span
														id="pwd_erro"></span><br>
														新的密码：<input type="text" id="newpwd"><br>
														确认密码：<input type="text" id="confirmpwd"><br>
														<span id="newpwd_erro"></span><br>
														<input type="button" value="修改"><br>
												</div>
										</div>
								</div>

						</div>

				</div>

		</div>

</div>
<!--底部-->
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>

