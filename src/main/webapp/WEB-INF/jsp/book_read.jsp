
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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/book_read.css"/>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/read.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
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
				<a href="${pageContext.request.contextPath}/" class='now'><span>首页</span></a>
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
<!--主体-->
<div class="content_read">
		<div class="box_con">
				<div class="con_top">
						<a href="/86475/" id="nav_novel">御九天</a> &gt; <span class="menu_title">第二百四十六章 情敌来了</span>
						<div id="page_set">
								<select onchange="font_ex()" id="fontselect" name="bcolor">
										<option value="宋体">字体</option>
										<option value="方正启体简体">默认</option>
										<option value="黑体">黑体</option>
										<option value="楷体_GB2312">楷体</option>
										<option value="微软雅黑">雅黑</option>
										<option value="方正启体简体">启体</option>
										<option value="宋体">宋体</option>
								</select>
								<select onchange="color_ex()" id="colorselect" name="bcolor">
										<option value="#000">颜色</option>
										<option value="#000">默认</option>
										<option value="#9370DB">暗紫</option>
										<option value="#2E8B57">藻绿</option>
										<option value="#2F4F4F">深灰</option>
										<option value="#778899">青灰</option>
										<option value="#800000">栗色</option>
										<option value="#6A5ACD">青蓝</option>
										<option value="#BC8F8F">玫褐</option>
										<option value="#F4A460">黄褐</option>
										<option value="#F5F5DC">米色</option>
										<option value="#F5F5F5">雾白</option>
								</select>
								<select onchange="fontsize_ex()" id="fontsizeselect" name="bcolor">
										<option value="#E9FAFF">大小</option>
										<option value="19pt">默认</option>
										<option value="10pt">10pt</option>
										<option value="12pt">12pt</option>
										<option value="14pt">14pt</option>
										<option value="16pt">16pt</option>
										<option value="18pt">18pt</option>
										<option value="20pt">20pt</option>
										<option value="22pt">22pt</option>
										<option value="25pt">25pt</option>
										<option value="30pt">30pt</option>
								</select>

								<span>背景颜色</span><select onchange="bgc_ex()" id="bgcselect" name="bcolor">
								<option value="#E9FAFF" style="background-color: #E9FAFF;">背景</option>
								<option value="#E9FAFF" style="background-color: #E9FAFF;">默认</option>
								<option value="#FFFFFF" style="background-color: #FFFFFF;">白雪</option>
								<option value="#000000" style="background-color: #000000;color:#FFFFFF;">漆黑</option>
								<option value="#FFFFED" style="background-color: #FFFFED;">明黄</option>
								<option value="#EEFAEE" style="background-color: #EEFAEE;">淡绿</option>
								<option value="#CCE8CF" style="background-color: #CCE8CF;">草绿</option>
								<option value="#FCEFFF" style="background-color: #FCEFFF;">红粉</option>
								<option value="#EFEFEF" style="background-color: #EFEFEF;">深灰</option>
								<option value="#F5F5DC" style="background-color: #F5F5DC;">米色</option>
								<option value="#D2B48C" style="background-color: #D2B48C;">茶色</option>
								<option value="#C0C0C0" style="background-color: #E7F4FE;">银色</option>
						</select>
								<span>宽度</span><select onchange="width_ex()" name="bcolor" id="bcolor">
								<option value="95%">宽度</option>
								<option value="95%">默认</option>
								<option value="85%">85%</option>
								<option value="76%">75%</option>
								<option value="67%">65%</option>
								<option value="53%">50%</option>
								<option value="41%">40%</option>
						</select>
								<span>夜间</span><input type="checkbox" name="night" id="night" onchange="light()"
								                      value="0">
						</div>

				</div>
				<div class="bookname">
						<h1> 第二百四十六章 情敌来了</h1>
						<div class="bottem1">
								<span class="recommend">投推荐票</span>
								<a href="" class="lastCatlog">上一章</a> ←
								<a href="" class="novelCatlog">章节目录</a> →
								<a href="" class="nextCatlog">下一章</a>
								<a href="">加入书签</a>
						</div>
				</div>
				<div id="content" style="width: 95%;">
						第二十七章损失惨重 “咔！”又是一道雷弧从雷泽闪了出来，莫无忌身体一扭，极为轻松的就避开了这一道雷弧。 莫无忌自己都惊叹不已，事实上在他开始闪避的时候，根本就没有想过能避开这一道雷弧。
						难怪听人说修炼者强大，他还没有开始修炼，仅仅是通了一条经脉就感觉整个人都轻松了一截，就好像传说中打通了任督二脉一般。那些通了数十条经脉，修炼过多年的家伙该有多厉害？
						又是数道闪雷落下，莫无忌没有办法全部避开，不过他已经可以移动。半柱香后，莫无忌再次被雷泽的闪雷劈了十数下后，终于冲出了雷泽。
						原本以为必死无疑的莫无忌，站在雷泽边缘，心里感叹不已。人生祸福，谁又能知道？如果不是经历这必死的一次，他岂能在自己炼制的开脉药液和雷泽闪雷的帮助下打通一条经脉？
						一条经脉打通用去了他八瓶开脉药液，外加半条命送去，这个代价也不算小。虽然莫无忌神识还有两瓶开脉药液，他却没有打算继续去被雷击想要打通第二条经脉。
						就算是莫无忌自己都清楚，他继续用开脉药液借助雷泽的雷击，也无法打通第二条经脉。若是不小心的话，说不定连小命都会送掉。
						莫无忌并不失望，能打通一条经脉已算是天大的意外之喜。更重要的是，让他掌握了一种可以开辟脉络的手段。假如脉络就是灵络的话，哪一天他开辟出来九十九条脉络，岂不是说他也是修炼天才？
						又是一声可怖的兽吼传来，莫无忌从意淫中清醒过来，他这才想起他还在雷雾森林，在他进入雷泽前，不远处还有两只强大的妖兽大战。
						现在那两头妖兽大战的地方一片狼藉，而两头妖兽却都不见了，又是几声嘶吼传来，莫无忌决定还是不要冒险出去。晚上出去是好，万一迷路进入了雷雾森林深处，可就完蛋了。
						就在距离雷泽不远的地方，莫无忌选择了一棵巨树爬了上去，在树杈的中间找了一个位置就这样半靠着。早已破烂的上衣被莫无忌撕开系在一起打成结，然后用这布将自己固定在树杈中间。
						在雷雾森林这种地方，他可不敢留在下面过夜。 …… 靠在树杈中间，莫无忌也不知道他是什么时候睡着的。他醒来的时候，已经有光从树的缝隙射下来，穿过朦朦胧胧的雾气落在他的身上。
						莫无忌四周观察了一下，确信没有任何危险，这才迅速解开打结的衣服从树上滑了下来。他必须要以最快的速度走出雷雾森林，而且还要一次成功，否则他很有可能永远留在这里。
						昨晚运气比较好，不代表他运气一直这么好。 莫无忌估计自己也不会再来这里，他还是仔细的观察了一下这个雷泽的位置。万一第二条经脉会需要这里的雷弧，说不定他还会冒一次险。
						在雷雾森林中，雷泽很多。但是能配合他打通经脉的雷泽恐怕并不好找，一旦遇见那些雷电强大的雷泽，一道雷弧下来，不等他经脉打通，他人就被打成碎渣了。
						双叶火焰草？莫无忌第一眼就认出了双叶火焰草。昨天晚上因为天色已晚，他并没有在意。此刻莫无忌才看清楚，在他昨晚冲进雷泽的位置，至少有三株双叶火焰草。
						双叶火焰草两片草叶分开，中间有一个火焰形状的草芯，极为好认。
						莫无忌没有立即去采摘双叶火焰草，这种草对寒凝有很大的作用，对他来说并没有什么用处。他现在最要紧的是赶紧找到出去的路，逃出雷雾森林再说。
						昨晚逃进雷雾森林的时候是黄昏时分，莫无忌依然很快就辨认出来了昨晚进来的方向。他心里略松了口气，按照时间计算，他所在的位置依然是在雷雾森林边缘。
						以最快的速度将那三株双叶火焰草挖出来，莫无忌按照印象中的方向，急速的冲了出去。他相信那些钻心蛇不会继续留在原地，肯定早已离开。那么一群钻心蛇全体出动，估计是因为什么原因造成的。不管是什么原因，他都不想在这里多逗留片刻。
						半个小时后，莫无忌的方向感觉渐渐消失，他心里有些焦急起来。若是不能在十分钟内找到出路，说明他是真的迷失了方向，那他或将再也找不到出去的路。 “莫无忌……”远处传来了一声叫喊。
						莫无忌还以为听错了，怎么有人叫他的名字？当那一声叫喊再次传来的时候，莫无忌确定他没有听错，的确是有人在叫他的名字。而且叫他名字的声音他还有些熟悉，应该就是丁布二。
						丁布二没有被钻心蛇咬死，还在找他，这让莫无忌很是开心。 有了声音就有了方向，莫无忌沿着丁布二发出声音的位置加快了步伐。仅仅几分钟时间，莫无忌就感觉到眼前豁然开朗。
						眼前一片片低矮的灌木荒原，让莫无忌激动的叫了出来，“布二，我在这边。” 他终于出来了。 十几个呼吸后，五道人影就从一片矮丘后面转了出来。
						“无忌，你竟然没事，实在是太好了……可是你怎么变成了这模样？”丁布二也没有想到莫无忌还活着，在这个地方过夜，还能活下来的那真是运气中的运气。只是莫无忌头发显然是被什么烧焦了，半的上身到处都是一条条的黑色痕迹，一条手臂还抬不起来，用一片破布绑着挂在脖子上。
						“布二，这次多谢你了。我迷迷糊糊还不知道往哪边走，就听见了你的叫声。”莫无忌感激的说道，他没有解释身上的焦黑原因。
						丁布二连忙道，“我只是想要碰碰运气，这才和小姐说叫你名字看看，没想到真的找到你了。我们十二个人来，现在加上你就只有六个人了。”
						莫无忌连忙上前感谢道，“多谢小姐关心，否则我在这里肯定走不出去。”
						寒凝看起来不但疲惫也有些狼狈，那一身火红的衣服，也早已有些破烂。见莫无忌感谢她，她摆了摆手，“能活下来，已经算是天大的运气。昨天我们一共死去了六人，其余人的遗体都找到了，只有你失踪了。丁布二请我来找找你，我就让大家一起来叫，也是略尽人事而已。”
						莫无忌看到寒凝身边除了那个侍女邵兰之外，还有彭茂华和看马匹的柴九，加上他和丁布二，正好只剩下一半。 这个时候，所有的人都将目光落在寒凝身上。她是寒府的小姐，自然是做主之人。
						寒凝带着歉意说道，“我也没有想到我们一靠近雷雾森林，就遇见了钻心蛇群。这种倒霉的事情都能碰上，也许我命中注定得不到双叶火焰草。” 彭茂华小心的问道，“小姐，那我们现在就回去吗？”
						寒凝的目光在彭茂华身上停留了好一会，才叹息一声说道，“我必须要得到双叶火焰草，这种草只有我们这一片雷雾森林边缘才有。这次的事情我也没有想到会这么危险，接下来我不会勉强大家，愿意和我一起去寻找双叶火焰草的留下来，不愿去的，可以先回去。”
						（请求推荐票支持！）
				</div>
				<div class="bottem2">
						<span onclick="" class="recommend">投推荐票</span>
						<a href="" class="lastCatlog">上一章</a> ←
						<a href="" class="novelCatlog">章节目录</a> →
						<a href="" class="nextCatlog">下一章</a>
						<a href="" onclick="">加入书签</a>
				</div>
		</div>
		<!--侧边栏数据-->
		<!--<script>
			  document.getElementById('d1').scrollTop=100;//通过scrollTop设置滚动到100位置
			 document.getElementById('d1').scrollLeft=200;//通过scrollTop设置滚动到200位置
		</script>-->
		<div class="siderManue">
				<div id="catlogList">

				</div>
		</div>
</div>
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>

