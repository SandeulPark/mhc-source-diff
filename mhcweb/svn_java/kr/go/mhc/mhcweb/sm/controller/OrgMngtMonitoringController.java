package kr.go.mhc.mhcweb.sm.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sm.service.OrgMngtMonitoringService;
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

/**
 * @Class Name : OrgMngtMonitoringController.java
 * @Description : 기관관리자 모니터링 컨트롤러 Class
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class OrgMngtMonitoringController extends DMultiActionController {
	
	@Resource(name = "web.sm.OrgMngtMonitoringService")
	private OrgMngtMonitoringService orgMngtMonitoringService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 기관관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orgMngtMonitoring.do")
	public String orgMngMonitoring(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/orgMngtMonitoring";
	}

	/**
	 * 기관관리자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/orgMngtMonitoringList.do")
	public @ResponseBody
	Map<String, Object> getOrgMngtMonitoringList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = orgMngtMonitoringService.getOrgMngtMonitoringList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 휴면계정 관리자 삭제
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteOrgMngtDormant.do")
	public @ResponseBody
	Map<String, Object> deleteOrgMngtDormant(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String msg = "삭제되었습니다.";
		String chkYn = "Y";

		try{
			String userId = StringUtil.nvl(String.valueOf(param.get("USER_ID")));
			List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
			param.put("userIter", userIter);

			orgMngtMonitoringService.deleteOrgMngtDormant(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
			msg = "삭제 실패하였습니다.";
		}

		rsMap.put("msg", msg);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 휴면계정 관리자 삭제
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releaseOrgMngtDormant.do")
	public @ResponseBody
	Map<String, Object> releaseOrgMngtDormant(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String msg = "해제되었습니다.";
		String chkYn = "Y";

		try{
			String userId = StringUtil.nvl(String.valueOf(param.get("USER_ID")));
			List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
			param.put("userIter", userIter);

			orgMngtMonitoringService.releaseOrgMngtDormant(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
			msg = "해제 실패하였습니다.";
		}

		rsMap.put("msg", msg);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 2개월이상 미접속 관리자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/orgMngtUnconnectList.do")
	public @ResponseBody
	Map<String, Object> getOrgMngtUnconnectList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = orgMngtMonitoringService.getOrgMngtUnconnectList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
