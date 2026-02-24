package kr.go.mhc.mhcweb.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.cm.service.ExcsCodeMngtService;



@Controller
@RequestMapping(value = "/cm")
public class ExcsCodeMngtController  extends DMultiActionController {
	@Resource(name = "web.cm.ExcsCodeMngtService")
	private ExcsCodeMngtService excsCodeMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 운동코드 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excsCodeMngt.do")
	public String excsCodeMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/cm/excsCodeMngt";
	}
	
	/**
	 * 운동코드 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getExcsCodeList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getExcsCodeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = excsCodeMngtService.getExcsCodeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 운동코드 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/insertExcsCode.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertExcsCode(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = excsCodeMngtService.insertExcsCode(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 운동코드 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateExcsCode.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateExcsCode(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = excsCodeMngtService.updateExcsCode(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 관리자 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/excsCodeRegMngtPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/cm/excsCodeRegMngtPop";
	}
	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateExcsCodeApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateExcsCodeApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = excsCodeMngtService.updateExcsCodeApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	
	
	
	
}
