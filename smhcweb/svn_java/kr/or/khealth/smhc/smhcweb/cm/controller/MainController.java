package kr.or.khealth.smhc.smhcweb.cm.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.cm.service.MainService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends DMultiActionController{ 
	@Resource(name="mainService")
	private MainService mainService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/main.do")
	public String mainView(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> serverTime = mainService.selectServerTime(param);
		Map<String, Object> selectSvcStatusIng = mainService.selectSvcStatusIng(param);
		Map<String, Object> selectTodaySvcStatusReg = mainService.selectTodaySvcStatusReg(param);
		model.addAttribute("serverTime",serverTime);  //시간 조회 
		model.addAttribute("selectSvcStatusIng",selectSvcStatusIng);  //전체 대상자 사용 인원 조회
		model.addAttribute("selectTodaySvcStatusReg",selectTodaySvcStatusReg);  //금일 대상자 사용 인원 조회
		return "web/main";   
	}
	
	@RequestMapping( value="/tabletMain.do")
	public String tabeletMainView(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> serverTime = mainService.selectServerTime(param);
		Map<String, Object> selectSvcStatusIng = mainService.selectSvcStatusIng(param);
		Map<String, Object> selectTodaySvcStatusReg = mainService.selectTodaySvcStatusReg(param);
		model.addAttribute("serverTime",serverTime);  //시간 조회 
		model.addAttribute("selectSvcStatusIng",selectSvcStatusIng);  //전체 대상자 사용 인원 조회
		model.addAttribute("selectTodaySvcStatusReg",selectTodaySvcStatusReg);  //금일 대상자 사용 인원 조회
		return "web/tabletMain";   
	}	
		
	
	/**
	 * 달력 총원 수 조회 
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSeniorCalendarMainList.do")
	public @ResponseBody Map<String, Object> selectSeniorCalendarMainList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> calendarMainList = mainService.selectSeniorCalendarMainList(param);
		rsMap.put("calendarMainList", calendarMainList);
		return rsMap;
	}
	
	/**
	 * 달력 클릭 대상자 리스트 조회
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSeniorFaceToFaceVisitList.do")
	public @ResponseBody Map<String, Object> selectSeniorFaceToFaceVisitList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>>  rsList = mainService.selectSeniorFaceToFaceVisitList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 금일 측정현황 정보 조회
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectTodayMeasrInfo.do")
	public @ResponseBody Map<String, Object> selectTodayMeasrInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = mainService.selectTodayMeasrInfo(param);
		return rsMap;
	}	
	
	/**
	 * 스트리밍 서버에서 mediaID 수신
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMediaID.do")
	public String getMediaID(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		
		return "web/cm/getMediaId";
	}
}
