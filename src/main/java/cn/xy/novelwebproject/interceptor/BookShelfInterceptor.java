package cn.xy.novelwebproject.interceptor;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Reader;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookShelfInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				Reader reader = (Reader) request.getSession().getAttribute("user_reader");
				Author author = (Author) request.getSession().getAttribute("user_auth");
				boolean flag = false;
				if (reader!=null&&reader.getNick_name()!=null&&!"".equals(reader.getNick_name())){
						flag = true;
				}
				return flag;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		}
}
