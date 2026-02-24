package kr.go.mhc.mhcweb.tg.controller;

import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.TrgterFormMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tg")
public class TrgterFormMngtcontroller extends DMultiActionController{
	
	@Resource(name = "web.tg.TrgterFormMngtService")
	private TrgterFormMngtService trgterFormMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자 서식관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterFormMngt.do")
	public String trgterFormMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
	 return "web/tg/trgterFormMngt";	
    }
	
	
	/**
	 * 대상자 개인정보 동의 전자서명 파일 View 호출
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterFormView.do", method = RequestMethod.GET)
	public String getTrgterFormView(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		
		Map<String, Object> rsMap =  trgterFormMngtService.getTrgterFormViewInfo(param);
		
		Clob clobSign1 = (Clob)rsMap.get("USER_SIGN1");		
		String STR_USER_SIGN1 = StringUtil.clobToString(clobSign1);

		
		rsMap.put("USER_SIGN1", STR_USER_SIGN1);

		//주민등록번호 미동의 시 주민번호 항목 NULL 업데이트
		String trgtYy = String.valueOf( param.get("TRGT_YY"));
		String returnUrl;
		

		if("2018".equals(trgtYy)){
			returnUrl = "web/tg/trgterFormView";	
		}else{
			returnUrl = "web/tg/trgterFormView_2019";									
		}

		

		
		model.addAttribute("rsMap",rsMap);

		
		return returnUrl;
	}	
	
	/**
	 * 대상자 서식관리 리스트 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getTrgterFormMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getTrgterFormMngtList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = trgterFormMngtService.getTrgterFormMngtList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}	
}
