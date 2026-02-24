package kr.go.mhc.mhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sv.service.ComnCnslMngtService;
import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @Class Name : ComnCnslMngtController.java
 * @Description : 관리자 WEB에서 사용하는 일반상담을 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.20		오샘이			최초생성
 *
 * @author gst
 * @since 2016.09.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class ComnCnslMngtController extends DMultiActionController {

	@Resource(name= "web.sv.ComnCnslMngtService")
	private ComnCnslMngtService comnCnslMngtService;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private TrgterInfoMngtService trgterInfoMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 일반상담 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/comnCnslMngtMain.do", method= RequestMethod.GET)
	public String cnslReqMain(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		try{
			if(param.get("mainCnslClf") != null){
				model.addAttribute("mainCnslClf",param.get("mainCnslClf"));
				model.addAttribute("mainTodayYn",param.get("mainTodayYn"));
				model.addAttribute("mainTrgtYY",param.get("mainTrgtYY"));
				model.addAttribute("mainSchYn",param.get("mainSchYn"));
			}
			
			Map<String,?> fm = RequestContextUtils.getInputFlashMap(req);
			model.addAttribute("cnslSn", fm.get("cnslSn"));
		}catch(Exception e){
			LOG.debug("e>>>>>>"+e);
		}
		
		model.addAllAttributes(param);
		return "web/sv/comnCnsl";
	}
	
	@RequestMapping(value="/sv/cnslAllList.do")
	public @ResponseBody Map<String, Object> cnslAllList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = comnCnslMngtService.selectAllCnslList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.putAll(param);
		return rsMap;
	}
	
	@RequestMapping(value="/sv/comnCnslDtls.do")
	public String comnCnslDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> userInfo = trgterInfoMngtService.trgterInfoMngtDtls(param);
		Map<String, Object> cnslInfo = trgterInfoMngtService.selectGeneralCnsl(param);

		param.put("attchFileSn", cnslInfo.get("ATTCH_FILE_SN"));

		// 일반상담 첨부파일  조회 추가 jeeeeey 20231108
		if(!"".equals(cnslInfo.get("ATTCH_FILE_SN")) && cnslInfo.get("ATTCH_FILE_SN") != null) {
			List<Map<String, Object>> fileInfo = cmmnService.selectAttchFile(param);
			model.addAttribute("fileInfo", fileInfo); // 일반상담 첨부파일 조회 추가 jeeeeey 20231108
		}
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("cnslInfo", cnslInfo);
		model.addAllAttributes(param);
		return "web/sv/comnCnslDtls";
	}

	@RequestMapping(value="/sv/comnCnslImgPop.do", method= RequestMethod.GET)
	public String comnCnslImgPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		String attchFileSn = StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
		String attchFileDtlsSn = StringUtil.nvl(String.valueOf(param.get("attchFileDtlsSn")));

		attchFileSn = attchFileSn.replaceAll("[^0-9]", "");
		attchFileDtlsSn = attchFileDtlsSn.replaceAll("[^0-9]", "");

		param.put("attchFileSn", attchFileSn);
		param.put("attchFileDtlsSn", attchFileDtlsSn);


		Map<String, Object> cnslInfo = trgterInfoMngtService.selectGeneralCnsl(param);
		List<Map<String, Object>> fileInfo = cmmnService.selectAttchFile(param); // 일반상담 첨부파일 조회 추가 jeeeeey 20231108

		//model.addAttribute("cnslInfo", cnslInfo);
		model.addAttribute("fileInfo", fileInfo); // 일반상담 첨부파일 조회 추가 jeeeeey 20231108

		model.addAllAttributes(param);
		return "web/sv/comnCnslImgPop";
	}
	
	
	/**
	 * 상담미완료 조회 탭 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/cnslNonCompList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> cnslNonCompList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = comnCnslMngtService.getCnslNonCompList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		rsMap.put("schSel1", param.get("schSel1"));		
		rsMap.put("schSel2", param.get("schSel2"));				
		rsMap.put("schSel3", param.get("schSel3"));				
		rsMap.put("schSel4", param.get("schSel4"));				
		return rsMap;
	}
	
	/**
	 * 상담완료 조회 탭 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/cnslCompList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> cnslCompList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = comnCnslMngtService.getCnslCompList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 실시간상담요청 조회 탭 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/realTimeCnslReqList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> realTimeCnslReqList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = comnCnslMngtService.getRealTimeCnslReqList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}	

}
