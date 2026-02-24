package kr.go.mhc.mhcweb.tg.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.TrgterCmplMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tg")
public class TrgterCmplMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.TrgterCmplMngtService")
	private TrgterCmplMngtService trgterCmplMngtService;

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 완료 대상자 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterCmplMngt.do")
	public String trgterCmplMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/tg/trgterCmplMngt";
	}
	
	/**
	 * 완료 대상자 관리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterCmplMngtList.do")
	public @ResponseBody Map<String, Object> trgterCmplMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterCmplMngtService.trgterCmplMngtList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
}
