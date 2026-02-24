package kr.go.mhc.mhcweb.cm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.CommonService;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/")
public class PageNaviController extends DMultiActionController{ 
//	@Resource(name="common.cmmnService")
//	private CommonService cmmnService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	//웹페이지 시작점
	@RequestMapping( value="/pageNavi.do")
	public String selectUserList(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model,HttpSession session) throws Exception{
		
		model.addAttribute("loginId",session.getAttribute("SESS_loginId"));
		if(session.getAttribute("AUTH_MENU") != null) { //권한이 등록된 아이디
			String mdCd = (String) param.get("menuCd");
			param.put("mdCd", mdCd);
			if(mdCd != null) { //선택한 텝메뉴
				 if(mdCd.equals("NTG100") || mdCd.equals("NBM100") || mdCd.equals("NHM100") || mdCd.equals("NNT100") || mdCd.equals("NAT100") || mdCd.equals("NOS100") || mdCd.equals("NPM100") || mdCd.equals("NYT100")){
						  List<Map<String,String>> authData =(List<Map<String, String>>) session.getAttribute("AUTH_MENU");//권한정보					 					 
						  List<Map<String,String>> menuOrdData = cmmnService.selectMenuCd(param);//현재 선택한 중메뉴 CD값
											  
			  forout : for(int i=0; i<authData.size(); i++) { //세션 사이즈만큼
						  for(int j=0; j<menuOrdData.size(); j++) {
							  if(menuOrdData.get(j).get("MENU_CD").equals(authData.get(i).get("MENU_CD"))){//소메뉴가 없는 메뉴 루프
								  if(authData.get(i).get("AUTH_RE").equals("Y")) {
									  param.put("menuCd", authData.get(i).get("MENU_CD"));
									  break forout;
								  }
							  }else {
								  if(authData.get(i).get("UPPER_MENU_CD").equals(menuOrdData.get(j).get("MENU_CD"))){//소메뉴 루프
									  if(authData.get(i).get("AUTH_RE").equals("Y")) {
										  param.put("menuCd", authData.get(i).get("MENU_CD"));
										  break forout;
									  }
								  }
							  }
						  }				 
			  		}
	 			}
			}
		}
		
		List<Map<String,String>> menuList = cmmnService.selectCmmnMenu(param);
		if(param.get("menuCd") != null) {
			List<Map<String,String>> selectMenuUpCd = cmmnService.selectMenuUpCd(param);			
			//model.addAttribute("UPPER_MENU_CD", selectMenuUpCd.get(0).get("UPPER_MENU_CD"));//상위 메뉴코드 가져오기위한
		}
		
		model.addAttribute("menuList", menuList);
		
		if(param.get("menuCd") == null){
			param.put("menuCd", "WCM100");
		}else{
			model.addAttribute("leftMenuCd", param.get("menuCd"));
			model.addAttribute("topMenuCd", getTopMenuCd(param.get("menuCd").toString(),menuList));
		}
		
		Map<String,String> menuInfo = cmmnService.selectCmmnMenuInfo(param);
		String menuUrl = (String) param.get("MENU_URL");
		
		if(menuUrl != null && !"".equals(menuUrl)){
			menuInfo.put("MENU_URL", menuUrl);
		}
		model.addAttribute("menuInfo", menuInfo);
		
		if(!"".equals(StringUtil.nvl(String.valueOf(param.get("REQ_SEARCH_INFO"))))){
			model.addAttribute("REQ_SEARCH_INFO", param.get("REQ_SEARCH_INFO"));
		}
		if(!"".equals(StringUtil.nvl(String.valueOf(param.get("TRGT_YY"))))){
			model.addAttribute("TRGT_YY", param.get("TRGT_YY"));
		}
		
		//2017.03.16 나연이 추가
		if("WSV200".equals(param.get("menuCd"))){
			Map<String, ?> fm = RequestContextUtils.getInputFlashMap(req);
			try{
				model.addAttribute("fm", fm);
			}catch(Exception e){
				fm.clear();
				LOG.debug("e>>>>>>>>"+e);
			}
		}
		return "web/pageNavi";   
	}	
	
	//웹페이지 시작점
	@RequestMapping( value="/pageNaviDtls.do", method= RequestMethod.POST)
	public String pageNaviDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		model.addAllAttributes(param);

		if(param.get("menuCd") != null) {
			List<Map<String,String>> selectMenuUpCd = cmmnService.selectMenuUpCd(param);			
			model.addAttribute("UPPER_MENU_CD", selectMenuUpCd.get(0).get("UPPER_MENU_CD"));
		}
		
		List<Map<String,String>> menuList = cmmnService.selectCmmnMenu(param);
		model.addAttribute("menuList", menuList);
		
		String menuCd = (String) param.get("menuCd");
		model.addAttribute("leftMenuCd", menuCd);
		model.addAttribute("topMenuCd", getTopMenuCd(menuCd,menuList));
		
		String menuUrl = StringUtil.nvl(String.valueOf(param.get("DTLS_MENU_URL")));
		Map<String,String> menuInfo = cmmnService.selectCmmnMenuInfo(param);
		if(!"".equals(menuUrl)) menuInfo.put("MENU_URL", menuUrl);
		model.addAttribute("menuInfo", menuInfo);
		
		return "web/pageNavi";
	}	
	
	private String getTopMenuCd(String target, List<Map<String,String>> menuList) {
		
		if (menuList == null) return "";
		if (target == null) return "";
		
		String upperMenuCd = "";
		String menuLvl = "";
		for (int i = 0; i < menuList.size(); i++) {
			if (target.equals(menuList.get(i).get("MENU_CD"))) {
				upperMenuCd = menuList.get(i).get("UPPER_MENU_CD");
				menuLvl = String.valueOf(menuList.get(i).get("MENU_LVL"));
				break;
			}
		}
		
		if ("3".equals(menuLvl)) {
			for (int i = 0; i < menuList.size(); i++) {
				if (upperMenuCd.equals(menuList.get(i).get("MENU_CD"))) {
					upperMenuCd = menuList.get(i).get("UPPER_MENU_CD");
					menuLvl = String.valueOf(menuList.get(i).get("MENU_LVL"));
					break;
				}
			}
		} else if ("1".equals(menuLvl)) {
			upperMenuCd = target;
		}
		
		return upperMenuCd;
	}
	
	/**
	 * 사이트맵
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/siteMap.do")
	public String siteMap(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		List<Map<String,String>> menuList = cmmnService.selectCmmnMenu(param);
		List<Map<String,String>> tglist = new ArrayList<Map<String, String>>();
		List<Map<String,String>> bmlist = new ArrayList<Map<String, String>>();
		List<Map<String,String>> hmlist = new ArrayList<Map<String, String>>();
		List<Map<String,String>> ntlist = new ArrayList<Map<String, String>>();
		List<Map<String,String>> atlist = new ArrayList<Map<String, String>>();
		
		for(int i=0;i<menuList.size();i++){
			String menuCd = menuList.get(i).get("UPPER_MENU_CD");
			if ("CM100".equals(menuCd.substring(1))) menuCd = menuList.get(i).get("MENU_CD");
			if("TG".equals(menuCd.substring(1,3))){
				tglist.add(menuList.get(i));
			}else if("BM".equals(menuCd.substring(1,3))){
				bmlist.add(menuList.get(i));
			}else if("HM".equals(menuCd.substring(1,3))){
				hmlist.add(menuList.get(i));
			}else if("NT".equals(menuCd.substring(1,3))){
				ntlist.add(menuList.get(i));
			}else if("AT".equals(menuCd.substring(1,3))){
				atlist.add(menuList.get(i));
			}
		}
		
		model.addAttribute("menuList", menuList);
		model.addAttribute("tglist",tglist);
		model.addAttribute("bmlist",bmlist);
		model.addAttribute("hmlist",hmlist);
		model.addAttribute("ntlist",ntlist);
		model.addAttribute("atlist",atlist);
		return "web/cm/siteMap";
	}
}
