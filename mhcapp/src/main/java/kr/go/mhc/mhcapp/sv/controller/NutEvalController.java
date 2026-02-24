package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.NutEvalService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NutEvalController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.NutEvalService")
	private NutEvalService nutEvalService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 영양평가결과 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalMain.do", method = RequestMethod.POST)
	public String rankMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		//	2017.03.06 이태석 추가(사진,동영상 페이지)
		List<Map<String,String>> CnslAttchFileChk = nutEvalService.selectNutEvalList4(param);
		
		if(CnslAttchFileChk.size() == 0){
			param.put("CnslAttchFileChk", "3");
		}else{
			param.put("CnslAttchFileChk", "4");
		}
		model.addAttribute("myhealth","slide");
		model.addAllAttributes(param);
		return "app/sv/nutEval";
		
	}
	
	/**
	 * 영양평가결과 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> nutEvalList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> CnslAttchFileChk = nutEvalService.selectNutEvalList4(param);

		//자동발송 여부 로직 추가
		Map<String,String> autoChkMap = nutEvalService.selectAutoSendYn(param);		
		String autoSendYn  = String.valueOf(autoChkMap.get("AUTO_SEND_YN"));
		String visitCnslSn = ""; 

		if("Y".equals(autoSendYn)){
			visitCnslSn = String.valueOf(autoChkMap.get("VISIT_CNSL_SN"));		
			rsMap.put("VISIT_CNSL_SN", visitCnslSn);	//중간 방문상담 건강관리 정보 조회			
			rsMap.put("CnslAttchFileChk", "2");					
		}else{
			if(CnslAttchFileChk.size() == 0){
				rsMap.put("CnslAttchFileChk", "3");
			}else{
				rsMap.put("CnslAttchFileChk", "4");
			}			
		}


		rsMap.put("myhealth", "slide");
		rsMap.put("AUTO_SEND_YN", autoSendYn);		//자동발송 여부 조회		

		List<Map<String,String>> rsList1 = nutEvalService.selectNutEvalList1(param);   
		List<Map<String,String>> rsAttch = nutEvalService.selectNutEvalAttchFileList(param);
		List<Map<String,String>> rsList2 = nutEvalService.selectNutEvalList2(param);
		List<Map<String,String>> periodList = nutEvalService.selectEvalPeriod(param);
		
		

		param.put("SVC_MNGT_NO", periodList.get(0).get("SVC_MNGT_NO"));
		param.put("F_WEEK_CNT", periodList.get(0).get("F_WEEK_CNT"));	//평가기간 시작 주차
		param.put("T_WEEK_CNT", periodList.get(0).get("T_WEEK_CNT"));	//평가기간 종료 주차
		
		List<Map<String,String>> practList = nutEvalService.selectNutEvalList3(param);  
		List<Map<String,String>> rsList4 = nutEvalService.selectNutEvalList4(param);	
		
		List<Map<String,String>> calChartList = nutEvalService.selectMealCalList(param);
		List<Map<String,String>> calWeekChartList = nutEvalService.selectWeekMealCalList(param);
		

		rsMap.put("rsMain", rsList1);
		rsMap.put("rsAttch", rsAttch);
		rsMap.put("rsList2", rsList2);
		rsMap.put("periodList", periodList);
		rsMap.put("practList", practList);
		rsMap.put("cnslAttachList", rsList4);
		rsMap.put("calChartList", calChartList);
		rsMap.put("calWeekChartList", calWeekChartList);
		
		return rsMap;
	}
	
	/**
	 * 영양평가결과1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList1(param);   
		List<Map<String,String>> rsAttch = nutEvalService.selectNutEvalAttchFileList(param);   

		rsMap.put("rsMain", rsList);
		rsMap.put("rsAttch", rsAttch);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList2(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList2(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList3(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> periodList = nutEvalService.selectEvalPeriod(param);
	
		param.put("SVC_MNGT_NO", periodList.get(0).get("SVC_MNGT_NO"));
		param.put("F_WEEK_CNT", periodList.get(0).get("F_WEEK_CNT"));	//평가기간 시작 주차
		param.put("T_WEEK_CNT", periodList.get(0).get("T_WEEK_CNT"));	//평가기간 종료 주차
		
		List<Map<String,String>> practList = nutEvalService.selectNutEvalList3(param);  
		
		rsMap.put("periodList", periodList);
		rsMap.put("practList", practList);
		return rsMap;
	}	

	/**
	 * 2017.03.03 이태석 추가(사진,동영상 보기)
	 * 영양평가결과 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList4.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList4(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = nutEvalService.selectNutEvalList4(param);	
		rsMap_sb.put("cnslAttachList", rsList1);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/******************************************************** WEB 미리보기 *****************************************************************/
	/**
	 * 영양평가결과 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/nutEvalMain.do", method = RequestMethod.GET)
	public String rankMainWeb(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		model.addAllAttributes(param);
		return "app/sv/nutEval";
	}	
	
	/**
	 * 영양평가결과1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/nutEvalList1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyListWeb(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList1(param);   
		List<Map<String,String>> rsAttch = nutEvalService.selectNutEvalAttchFileList(param);   

		rsMap.put("rsMain", rsList);
		rsMap.put("rsAttch", rsAttch);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/nutEvalList2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList2Web(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList2(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/nutEvalList3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList3Web(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList3(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
}
