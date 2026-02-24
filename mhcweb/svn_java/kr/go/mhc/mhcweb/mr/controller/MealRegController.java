package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.mr.service.MealRegService;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import kr.go.mhc.mhcweb.sv.service.IntensiveCnslMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MealRegController.java
 * @Description : 관리자 WEB에서 사용하는 식단등록 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.05		이은주			최초생성
 * 
 * @author gst
 * @since 2016.12.05
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class MealRegController extends DMultiActionController {
	
	@Resource(name= "web.mr.MealRegService")
	private MealRegService mealRegService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 식단등록 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealReg.do")
	public String mealReg(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		model.addAttribute("rsOrgDtlsList", rsOrgDtlsList);
		return "web/mr/mealReg";
	}
	
	/**
	 * 식단등록 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealRegList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mealRegList(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		// 검색조건 년도 안 넘어오면 올해 값 넣어주기
		if("".equals(StringUtil.nvl(String.valueOf(param.get("TRGT_YY"))))) {
			param.put("THIS_YEAR", "Y"); // 올해
		}
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		// org_dtls 에 데이터 있음.
		if(!rsOrgDtlsList.isEmpty()) {
			param.put("INTENS_CNSL_USE_YN", rsOrgDtlsList.get(0).get("INTENS_CNSL_USE_YN")); // 중간검진 진행 여부 저장
		}

		List<Map<String, Object>> rsList = mealRegService.mealRegList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 식단등록 팝업창 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealRegPop.do")
	public String mealRegPop(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		model.addAllAttributes(param);
		return "web/mr/mealRegPop";
	}
	
	/**
	 * 식단등록 팝업, 식사일자 선택시
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealRegPopDtls.do")
	public @ResponseBody Map<String, Object> mealRegPopDtls(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		//식사상세내용.
		Map<String, Object> rsDtlsOne = mealRegService.mealRegPopDtls(param);
		//식사상세내용2.
		List<Map<String, Object>> rsDtlsList = mealRegService.mealRegPopDtls2(param);
		
		rsMap.put("rsDtlsOne", rsDtlsOne);
		rsMap.put("rsDtlsList", rsDtlsList);
		
		return rsMap;
	}
}
