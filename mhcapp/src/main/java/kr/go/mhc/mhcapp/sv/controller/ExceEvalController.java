package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.ExceEvalService;

@Controller
public class ExceEvalController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.sv.ExceEvalService")
	private ExceEvalService exceEvalService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 운동평가결과 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalMain.do", method = RequestMethod.GET)
	public String exceEvalMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return "app/sv/exceEval";
	}
	
	/**
	 * 운동평가결과 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalMainData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalMainData(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> periodList = exceEvalService.selectPeriod(param);
		Map<String,String> actRate = exceEvalService.selectExceEvalList1_1(param);
		List<Map<String,String>> objHRSucRate = exceEvalService.selectExceEvalList2_1(param);
		Map<String,String> rsExceInfo = exceEvalService.selectExceEvalList3(param);
		Map<String,String> rsAvgCalInfo = exceEvalService.selectExceEvalList3_2(param);
		Map<String,String> rsInfo = exceEvalService.selectExceEvalList4(param);
		Map<String,String> rsCalActInto = exceEvalService.selectExceEvalList4_1(param);
		List<Map<String,String>> cnslAttachList = exceEvalService.selectExceEvalList5(param);
		
		rsMap.put("period", periodList); 				// 평가기간
		rsMap.put("actRate", actRate); 				// 걸음수,달성률
		rsMap.put("objHRSucRate", objHRSucRate);		// 심박수 달성률
		rsMap.put("rsExceInfo", rsExceInfo); 			// 운동 정보
		rsMap.put("rsAvgCalInfo", rsAvgCalInfo);		// 일평균 칼로리
		rsMap.put("rsInfo", rsInfo); 					// 평가내용 및 결과
		rsMap.put("rsCalActInto", rsCalActInto);		// 칼로리 운동 조회
		rsMap.put("cnslAttachList", cnslAttachList); 	// 추가 콘텐츠
		
		return rsMap;
	}
	
	
	@RequestMapping( value="/exceEvalData1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalData1(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> periodList = exceEvalService.selectPeriod(param);
		Map<String,String> actRate = exceEvalService.selectExceEvalList1_1(param);
		rsMap.put("period", periodList); 				// 평가기간
		rsMap.put("actRate", actRate); 				// 걸음수,달성률
		return rsMap;
	}
	
	@RequestMapping( value="/exceEvalData2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalData2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> objHRSucRate = exceEvalService.selectExceEvalList2_1(param);
		rsMap.put("objHRSucRate", objHRSucRate);		// 심박수 달성률
		return rsMap;
	}
	
	@RequestMapping( value="/exceEvalData3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalData3(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,String> rsExceInfo = exceEvalService.selectExceEvalList3(param);
		Map<String,String> rsAvgCalInfo = exceEvalService.selectExceEvalList3_2(param);
		rsMap.put("rsExceInfo", rsExceInfo); 			// 운동 정보
		rsMap.put("rsAvgCalInfo", rsAvgCalInfo);		// 일평균 칼로리
		return rsMap;
	}
	
	@RequestMapping( value="/exceEvalData4.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalData4(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,String> rsInfo = exceEvalService.selectExceEvalList4(param);
		Map<String,String> rsCalActInto = exceEvalService.selectExceEvalList4_1(param);		
		
		//자동발송 여부 로직 추가
		Map<String,String> autoChkMap = exceEvalService.selectAutoSendYn(param);		
		String autoSendYn  = String.valueOf(autoChkMap.get("AUTO_SEND_YN"));
		String visitCnslSn = ""; 
		
		if("Y".equals(autoSendYn)){
			visitCnslSn = String.valueOf(autoChkMap.get("VISIT_CNSL_SN"));		
			rsMap.put("VISIT_CNSL_SN", visitCnslSn);	//중간 방문상담 건강관리 정보 조회			
		}
		rsMap.put("AUTO_SEND_YN", autoSendYn);			//자동발송 여부 조회			
		
		rsMap.put("rsInfo", rsInfo); 					// 평가내용 및 결과
		rsMap.put("rsCalActInto", rsCalActInto);		// 칼로리 운동 조회
		return rsMap;
	}
	
	@RequestMapping( value="/exceEvalData5.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalData5(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> cnslAttachList = exceEvalService.selectExceEvalList5(param);
		rsMap.put("cnslAttachList", cnslAttachList); 	// 추가 콘텐츠
		return rsMap;
	}
	
	
	/**
	 * 운동평가결과 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exceEvalChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = exceEvalService.selectExceEvalList1(param);
		List<Map<String,String>> rsList3 = exceEvalService.selectExceEvalList2(param);
		List<Map<String,String>> rsList5 = exceEvalService.selectExceEvalList3_1(param);
		
		rsMap_sb.put("chart", rsList1); 			// 활동량 차트
		rsMap_sb.put("chart2", rsList3); 			// 심박수 차트
		rsMap_sb.put("chart3", rsList5); 			// 요일별 평균 소모칼로리 차트
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
		
	/**
	 * 좋아요 Y/N update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateEvalGood.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityGood(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		exceEvalService.updateEvalGood(param);
//		rsMap.put("msg", getMsg("common.write.succ"));
		
		return rsMap;
	}	
	
	/**
	 * 2017.03.06 이태석 추가(사진,동영상 보기)
	 * 영양평가결과4 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalList5.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList5(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = exceEvalService.selectExceEvalList5(param);	
		rsMap_sb.put("cnslAttachList", rsList1);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	//---------------------------------------웹접근시--------------------------------------------
	/**
	 * 운동평가결과 화면 호출
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/exceEvalMain.do", method = RequestMethod.GET)
	public String exceEvalMainWeb(@ModelAttribute Map param, ModelMap model) throws Exception{		
		model.addAllAttributes(param);
		return "app/sv/exceEval";
	}	
}
