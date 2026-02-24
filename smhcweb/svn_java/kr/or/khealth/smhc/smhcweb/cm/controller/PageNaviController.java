package kr.or.khealth.smhc.smhcweb.cm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;






import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;

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
	public String selectUserList(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{


		String SESS_ISMOBILE = (String) param.get("SESS_ISMOBILE");		
		
		//웹에서 로그인 시 
//		if(SESS_ISMOBILE == null || "".equals(SESS_ISMOBILE)){
	
			List<Map<String,String>> menuList = cmmnService.selectCmmnMenu(param);
			model.addAttribute("menuList", menuList);
			if(param.get("menuCd") == null){
				param.put("menuCd", "SCM100");
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
		//모바일 기기 로그인 시 
//		}else{
//
//			if(param.get("menuCd") == null || param.get("menuCd") ==""){
//				param.put("menuCd", "TCM100");
//			}
//			
//			Map<String,String> menuInfo = cmmnService.selectCmmnMenuInfo(param);
//			String menuUrl = (String) param.get("MENU_URL");
//			
//			if(menuUrl != null && !"".equals(menuUrl)){
//				menuInfo.put("MENU_URL", menuUrl);
//			}			
//			model.addAttribute("menuInfo", menuInfo);
//		}	
		return "web/pageNavi";   
	}	
	
	//웹페이지 시작점
	@RequestMapping( value="/pageNaviDtls.do", method= RequestMethod.POST)
	public String pageNaviDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		model.addAllAttributes(param);

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
		List<Map<String,String>> mrlist = new ArrayList<Map<String, String>>();
		List<Map<String,String>> svlist = new ArrayList<Map<String, String>>();

		
		for(int i=0;i<menuList.size();i++){
			String menuCd = menuList.get(i).get("UPPER_MENU_CD");
			if ("CM100".equals(menuCd.substring(1))) menuCd = menuList.get(i).get("MENU_CD");
			if("TG".equals(menuCd.substring(1,3))){
				tglist.add(menuList.get(i));
			}else if("MR".equals(menuCd.substring(1,3))){
				mrlist.add(menuList.get(i));
			}else if("SV".equals(menuCd.substring(1,3))){
				svlist.add(menuList.get(i));
			}
		}
		model.addAttribute("menuList", menuList);
		model.addAttribute("tglist",tglist);
		model.addAttribute("mrlist",mrlist);
		model.addAttribute("svlist",svlist);
		return "web/cm/siteMap";
	}
}
