package kr.go.mhc.mhcapp.sv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.ServeyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ServeyController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.ServeyService")
	private ServeyService serveyService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 설문지 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyMain.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyMainInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		rsMap.put("flag", param.get("flag"));
		rsMap.put("isDo", param.get("isDo"));
		return rsMap;
	}	
	
	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = serveyService.selectServeyList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyCodeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyCodeList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = serveyService.selectServeyCodeList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 설문지 마스터 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyMasterInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertServeyMaster(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = serveyService.selectServeyLstQnaCD(param);
			if(rsList.size()==0){
				serveyService.serveyMasterInsert(param);   //마스터 insert
			}
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 설문지 영양평가 답변 도중나올시 anwer 0으로 초기화
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyAnswrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateServeyAnswr(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsList = 0;
		String chkYn = "N";
		
		try{
			rsList = serveyService.serveyAnswrUpdate(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 설문지 마스터 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyMastrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateServeyMastr(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsList = 0;
		String chkYn = "N";
		
		try{
			rsList = serveyService.updateServeyMaster(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 설문지 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyAwrInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertServeyAwr(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			String answrCd = (String)param.get("ANSWR_CD_1");
			String serveyCd = (String) param.get("SERVEY_CD");
			if(answrCd.indexOf(serveyCd) > -1){
				rsCount = serveyService.serveyAwrInsert(param);
			}
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsCount", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 설문 답변 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectServeyAnwerList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyAnwerList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = serveyService.selectServeyAnwerList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 설문지 답변 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyAwrDel.do", method = RequestMethod.POST)
	public void serveyAwrDel(@ModelAttribute Map param, ModelMap model) throws Exception{
		serveyService.serveyAwrDel(param);
	}

	/**
	 * 설문조사 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trgtServeyMaster.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> trgtServeyMaster(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsMap_ser = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = serveyService.serveyResearchAnswrList(param);
		List<Map<String, Object>> rsList2 = serveyService.serveyResearchAnswrMastr(param);
		rsMap_ser.put("rsList", rsList);
		rsMap_ser.put("sumryInfo", rsList2);

		rsMap.put("rsList", rsMap_ser);

		return rsMap;
	}

	/**
	 * 설문답변 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchAnswrInsert.do")
	public @ResponseBody Map<String, Object> serveyResearchAnswrInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			serveyService.serveyResearchAnswrInsert(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}

		rsMap.put("chkYn", chkYn);
		return rsMap;
	}



}