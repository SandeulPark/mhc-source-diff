package kr.go.mhc.mhcweb.gn.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.gn.service.GnrlSelfMissionMonitoringService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/gn")
public class GnrlSelfMissionMonitoringController extends DMultiActionController {

	@Resource(name = "web.gn.GnrlSelfMissionMonitoringService")
	private GnrlSelfMissionMonitoringService gnrlSelfMissionMonitoringService;

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 셀프미션모니터링 화면 호출
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selfMissionMonitoring.do", method = RequestMethod.GET)
	public String selfMissionMonitoring(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/gn/selfMissionMonitoring";
	}


	/**
	 * 셀프미션 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSelfMissionList.do")
	public @ResponseBody Map<String,Object> getSelfMissionList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlSelfMissionMonitoringService.getSelfMissionList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 셀프미션모니터링 팝업 화면 호출
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selfMissionPop.do", method = RequestMethod.GET)
	public String selfMissionPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/gn/selfMissionPop";
	}


	/**
	 * 셀프미션 상세 팝업 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSelfMissionDtls.do")
	public @ResponseBody Map<String,Object> getSelfMissionDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlSelfMissionMonitoringService.getSelfMissionDtls(param);

		rsMap.put("rsList", rsList);
		return rsMap;
	}


}