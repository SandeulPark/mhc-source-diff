package kr.go.mhc.mhcweb.tg.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.PhisCnctTrgterCurService;
import kr.go.mhc.mhcweb.tg.service.PhisPreTrgterMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @Class Name : PhisCnctTrgterCurController.java
 * @Description : PHIS 연계 대상자 현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.02.11		양현우			최초생성
 *
 * @author thejoin
 * @since 2018.04.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/tg")
public class PhisPreTrgterMngtController extends DMultiActionController {

	@Resource(name ="web.tg.PhisPreTrgterMngtService")
	private PhisPreTrgterMngtService phisPreTrgterMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	/**
	 * 국가건강검진 수검자 예비 대상자 등록 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisPreTrgterRegit.do")
	public String phisPreTrgterRegit(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/phisPreTrgterRegit";
	}
	
	/**
	 * 국가건강검진 수검자 예비 대상자 리스트 호출 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisPreTrgterRegitList.do")
	public @ResponseBody Map<String, Object> phisPreTrgterRegitList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = phisPreTrgterMngtService.phisPreTrgterRegitList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 국가건강검진 수검자 검진 정보 모달 호출 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisPreTrgterRegitPop.do", method= RequestMethod.GET, produces="text/html; charset=UTF-8")
	public String phisPreTrgterRegitPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = phisPreTrgterMngtService.phisPreTrgterRegitExamInfo(param);
		model.addAllAttributes(rsMap);		
		return "web/tg/phisPreTrgterRegitPop";
	}
	
	
	/**
	 * 선택한 국가건강검진 수검자 예비대상자로 등록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisPreTrgterDataInsert.do")
	public @ResponseBody Map<String, Object> phisPreTrgterDataInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		
		try {
            // dataList가 JSON 문자열로 들어오므로 파싱
            String jsonStr = (String) param.get("dataList");
            List<Map<String, Object>> dataList = new ObjectMapper().readValue(jsonStr, List.class);

            for (Map<String, Object> row : dataList) {
                System.out.println("데이터 삽입: " + row);
                row.put("SESS_USER_ID", param.get("SESS_USER_ID"));
                row.put("SESS_ORG_CD", param.get("SESS_ORG_CD"));
                row.put("EXAM_SN", "1");
                
                //예비대상자 등록, 검진결과 등록, 의사 선정 의뢰
                rsMap = phisPreTrgterMngtService.phisNewPreTrgterRegit(row);            
            }                        
            rsMap.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            rsMap.put("status", "error");
            rsMap.put("message", e.getMessage());
        }
        
        return rsMap;
	}
	
	/**
	 * 중간/최종 건강검진 > 국가건강검진 이력 조회 모달 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisHealthExamRsltPop.do")
	public String phisHealthExamRsltPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		rsMap = phisPreTrgterMngtService.phisHealthExamRsltPop(param);
		model.addAttribute("CNSL_NO", param.get("cnslNo"));
		model.addAttribute("EXAM_SN", param.get("examSn"));
		model.addAttribute("PRE_TRGTER_NO", param.get("preTrgterNo"));
		model.addAllAttributes(rsMap);		
		return "web/tg/phisHealthExamRsltPop";
	}	
	
		
	/**
	 * 중간/최종 건강검진 > 국가건강검진 이력 조회 모달 호출 > 대상자 국가건강검진 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectUserPhisExamRslt.do")
	public @ResponseBody Map<String, Object> selectUserPhisExamRslt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = phisPreTrgterMngtService.selectUserPhisExamRslt(param);
		
		if(rsMap == null || rsMap.isEmpty()) {
			rsMap = new HashMap<String, Object>();
			rsMap.put("result", "NO_DATA");
		}
        
        return rsMap;
	}	
	
	
	/**
	 * 중간/최종 건강검진 > 국가건강검진 정보로 중간/최종 건강검진 등록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/regitExamPhisRslt.do")
	public @ResponseBody Map<String, Object> regitExamPhisRslt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		boolean hasUnscreenedData = false;
		
		//빈 값 확인
		String[] checkData = {
			"HEIGHT", "WEIGHT", "BLOOD_PRESS_MAX", "BLOOD_PRESS_MIN", "BLOOD_SUGAR_41", "WAIST_MSMT", "HDL_CHOL", "NEUTRAL_FAT"
		};		
		for(String key : checkData) {
			if("".equals(String.valueOf(param.get(key)))) hasUnscreenedData = true;
		}
		
		try {			
			rsMap = phisPreTrgterMngtService.regitExamPhisRslt(param);
			if(hasUnscreenedData) rsMap.put("result", "UNSCREENED_DATA"); 
			rsMap.put("status", "success");
		}catch(Exception e) {
			rsMap.put("status", "error");
			e.printStackTrace();
		}
        return rsMap;
	}		
}
