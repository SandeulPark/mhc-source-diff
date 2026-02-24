package kr.go.mhc.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.service.CommonUserService;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	LoginManager loginManager = LoginManager.getInstance();
	
	@Resource(name="common.userService")
	private CommonUserService commUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub				
		String viewGbn = StringUtil.nvl(String.valueOf(request.getSession().getAttribute("SESS_ISMOBILE")));
		String loginId = (String)request.getSession().getAttribute("SESS_USER_ID");
		String loginIp = (String)loginManager.getUserIp(request.getSession());
				
		String domainName = request.getServerName();	
				
		System.out.println("##############################");
		System.out.println("### domainName ===> " + domainName);
		System.out.println("### loginId ===> " + loginId);
		System.out.println("### loginIp ===> " + loginIp);
		System.out.println("### RequestURI() " + request.getRequestURI());
		System.out.println("##############################");
		
		
		//동시 접속 session 처리
		if(loginIp!=null && !"".equals(loginIp) && !loginIp.isEmpty() && !"admin".equals(loginId)){			
			if(domainName.equalsIgnoreCase("mhcweb.mhcadm.com")) {
				response.sendRedirect(request.getContextPath()+"/login/loginPageVpn.do");
			}else {
				response.sendRedirect(request.getContextPath()+"/");
			}
			return false;
		}		
		
		if(loginId!=null&&!loginId.isEmpty()){
			return true;
		}
		
		//동영상 등록 후 세션 끊어지는 현상 조치
		if(request.getRequestURI().contains("/getMediaID.do")){			
			System.out.println("SESSION_REDIRECT === > " + request.getParameter("userId"));
			Map<String, Object> param = new HashMap<String,Object>();			
			param.put("USER_ID", request.getParameter("userId"));
			Map<String,Object>	rsMap		= commUserService.checkSessionUser(param);
			Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");
			sessMap.put("USER_ID", request.getParameter("userId"));			
			if (!"".equals(sessMap.get("USER_ID"))) {
				HttpSession session = request.getSession();	
				session.setAttribute(sessMap.get("USER_ID"), loginManager);
				for (String name : sessMap.keySet()) {
					System.out.println("SESS_" + name + " : " + sessMap.get(name));
					session.setAttribute("SESS_" + name, sessMap.get(name));
				}
			}			
			return true;
		}
				
		if(request.getRequestURI().contains("/sm/saveMngt.do")
				||request.getRequestURI().contains("/tg/checkOverlapId.do")
                ||request.getRequestURI().contains("/managerDuplicationCnt2.do")
				){
			return true;
		}
		//정상적인 세션정보가 없으면 로그인실패 alert 후 로그인페이지로 이동
		if(viewGbn.indexOf("APP") > 0){
			response.sendRedirect(request.getContextPath()+"/?isMobile=APP");
		}else{
			if(domainName.equalsIgnoreCase("mhcweb.mhcadm.com")) {
				response.sendRedirect(request.getContextPath()+"/login/loginOutVpn.do");
			}else {
				response.sendRedirect(request.getContextPath()+"/");
			}
		}
//		response.sendRedirect(request.getContextPath()+"/login/loginPage.do");
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
