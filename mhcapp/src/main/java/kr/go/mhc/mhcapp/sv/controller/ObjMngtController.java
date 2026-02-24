package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.ObjMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjMngtController extends DMultiActionController{ 

	@Resource(name="mhcapp.sv.ObjMngtService")
	private ObjMngtService objMngtService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 목표관리_상담 일자 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/selectObjCnsl.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectObjCnsl(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, String>> rsList = objMngtService.selectObjCnsl(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 목표관리_영양 상담 상세 페이지 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/selectObjNutMngt.do", method = RequestMethod.GET)
	public String selectObjNutMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/objNutMngt";
	}
	
	/**
	 * 목표관리_영양 상담 상세 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/selectObjNutMngtDtls.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectObjNutMngtDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, String>> intakeStndList = objMngtService.selectIntakeStnd(param);
		List<Map<String, String>> objNutMngtList = objMngtService.selectObjNutMngt(param);
		rsMap.put("intakeStndList", intakeStndList);
		rsMap.put("objNutMngtList", objNutMngtList);
		return rsMap;
	}
	
	/**
	 * 목표관리_신체활동 상담 및 목표 상세 페이지 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectObjBodyActMngt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectObjBodyActMngt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, String> objBodyActInfo = objMngtService.selectObjMngtBodyActInfo(param);
		param.put("MUSCLE_EXCS_PRSCRPT", objBodyActInfo.get("MUSCLE_EXCS_PRSCRPT"));
		List<Map<String, String>> objBodyPartExcsInfo = objMngtService.selectObjMngtBodyPartExcsInfo(param);
		//String musclePart[] = "어깨,팔,가슴,복부,대퇴앞,대퇴뒤,등,종아리".split(",");
		
		rsMap.put("objBodyActInfo", objBodyActInfo);
		rsMap.put("objBodyPartExcsInfo", objBodyPartExcsInfo);
		//model.addAttribute("musclePart", musclePart);
		rsMap.put("param",param);
		return rsMap;
	}
		
	/**
	 * 목표관리_건강관리 상담 및 목표 상세 페이지 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectObjHealthMngt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectObjHealthMngt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, String> objHealthInfo = objMngtService.selectObjMngtHealthMngtInfo(param);
						
		rsMap.put("objHealthInfo", objHealthInfo);		
		
		return rsMap;
	}	
	
	/**
	 * 근력 운동 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excsGuidePopInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> excsGuidePopInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String bodyPartExcsCd = param.get("BODY_PART_EXCS_CD")==null?"":(String)param.get("BODY_PART_EXCS_CD");
		if(!"".equals(bodyPartExcsCd)){
			rsMap.putAll(objMngtService.selectBodyPartExcsInfo(param));
		}
		rsMap.put("param", param);
		return rsMap;
	}
}
