package kr.go.mhc.mhcapp.sv.controller;

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
	@RequestMapping( value="/serveyMain.do", method = RequestMethod.GET)
	public ModelAndView serveryMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		 ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("app/sv/servey");
		  modelAndView.addObject("flag", param.get("flag"));
		  modelAndView.addObject("isDo", param.get("isDo"));
		return modelAndView;
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
		
		List<Map<String,String>> rsList = serveyService.selectServeyList(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		//Map<String,String> data = new HashMap<String,String>();
		List<Map<String,String>> rsList = serveyService.selectServeyCodeList(param);   
	
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 설문지 마스터 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyMasterInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> serveyMasterInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = serveyService.selectServeyLstQnaCD(param);   
		
		if(rsList.size()==0){
			serveyService.serveyMasterInsert(param);   //마스터 insert
		}
		rsMap.put("rsList", rsList);
		/*if(rsCount>0){
			//rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.write.err"));
		}*/
		return rsMap;
	}	
	/**
	 * 설문지 영양평가 답변 도중나올시 anwer 0으로 초기화
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyAnswrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> serveyAnswrUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsList = serveyService.serveyAnswrUpdate(param);   
		
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}	
	/**
	 * 설문지 마스터 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyMastrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateServeyMaster(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsList = serveyService.updateServeyMaster(param);   
		
		rsMap.put("rsList", rsList);
		/*if(rsCount>0){
			//rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.write.err"));
		}*/
		return rsMap;
	}	
	/**
	 * 설문지 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/serveyAwrInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> serveyAwrInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = serveyService.serveyAwrInsert(param);   
		rsMap.put("rsList", rsCount);
		if(rsCount>0){
			//rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.write.err"));
		}
		return rsMap;
	}	
}