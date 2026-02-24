package kr.go.mhc.mhcweb.tg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import kr.go.mhc.mhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.go.mhc.mhcweb.tg.service.HealthExamMngtService;
import kr.go.mhc.mhcweb.tg.service.SvcJoinMngtService;

/**
 * @Class Name : HealthExamMngtController.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.20		이은주			최초생성
 * @	2016.11.21		이은주			삭제추가
 *
 * @author gst
 * @since 2016.08.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class HealthExamMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.HealthExamMngtService")
	private HealthExamMngtService healthExamMngtService;
	
	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;
	
	@Resource(name= "web.tg.SvcJoinMngtService")
	private SvcJoinMngtService svcJoinMngtService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강검진관리 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthExamMngt.do", method= RequestMethod.GET)
	public String healthExamMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		param.put("TEMPLATE_CLF", "10");
		List<Map<String, Object>> cnslTemplateNm = intensiveBodyActObstyCnslService.getCnslTemplateNm(param);
		
		// 2025.11 여기에 기관 중간검진 진행 여부 함께..
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		param.put("THIS_YEAR" , "Y"); // 올해
		List<Map<String, String>> orgDtlsList = orgMngtService.getOrgDtlsList(param);

		String thisYear = "";
		String midExamUseYn = "";
		if(orgDtlsList.size() > 0) {
			thisYear = orgDtlsList.get(0).get("TRGT_YY");
			midExamUseYn = orgDtlsList.get(0).get("MID_EXAM_USE_YN");
		}


		model.addAttribute("cnslTemplateNm", cnslTemplateNm);
		model.addAttribute("orgDtlsList", thisYear + "_" + midExamUseYn);
		return "web/tg/healthExamMngt";
	}
	
	/**
	 * 건강검진관리 조회 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthExamMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthExamMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = healthExamMngtService.getHealthExamList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 건강검진관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthExamMngtDtls.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthExamMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("TEMPLATE_CLF", "10");
		Map<String, Object> rsMap = healthExamMngtService.getHealthExamMngtDtls(param);
		return rsMap;
	}
	
	
	/**
	* 건강검진관리 계측정보저장
	* @param
	* @return
	* @throws Exception
	*/
	@RequestMapping(value= "/updateHealthExamBody.do", method= RequestMethod.GET)
	public @ResponseBody Map<String, Object> updateHealthExam(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		param.put("EXAM_DE", param.get("EXAM_DE").toString().replace("-", ""));
		healthExamMngtService.updateHealthExamBody(param);
		rsMap.put("EXAM_SN", param.get("EXAM_SN"));
		return rsMap;
	}
	
	/**
	 * 건강검진관리 혈액검사정보저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateHealthExamBld.do", method= RequestMethod.POST)
	public void updateHealthExamBld(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("EXAM_DE", param.get("EXAM_DE").toString().replace("-", ""));
		healthExamMngtService.updateHealthExamBld(param);
	}

	/**
	 * 건강검진관리 만성질환정보 저장
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateHealthExamChronic.do", method=RequestMethod.POST)
	public void updateHealthExamChronic(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("EXAM_DE", param.get("EXAM_DE").toString().replace("-", ""));
		
		healthExamMngtService.updateHealthExamChronic(param);
	}
	
	/**
	 * 건강검진관리 인바디 정보 저장 검사일자 조회 및 팝업 창 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getSelHealthExamDE.do", method= RequestMethod.GET)
	public String getSelHealthExamDE(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		System.out.println("############################################ ");
		return "web/tg/healthExamMngtPop";
	}
	
	/**
	 * 건강검진관리 인바디 정보 저장 검사일자 팝업 창 데이터 불러오기
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getSelHealthExamData.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSelHealthExamData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
//		Map<String, Object> rsMapExamDE = healthExamMngtService.getSelHealthExamDE(param);
		Map<String, Object> rsMapExam = healthExamMngtService.getHealthBodyComp(param);
		List<Map<String, Object>> rsListExamDE = healthExamMngtService.getSelHealthExamDEList(param);
		
//		rsMap.put("rsMapExamDE", rsMapExamDE);
		rsMap.put("rsListExamDE", rsListExamDE);
		rsMap.put("rsMapExam", rsMapExam);
		
		return rsMap;
	}
	
	/**
	 * 건강검진관리 인바디 정보 체성분결과 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateHealthBodyComp.do")
	public void updateHealthBodyComp(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthExamMngtService.updateHealthBodyComp(param);
	}
	
	/**
	 * 건강검진관리 검사완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateHealthComplete.do")
	public Map<String, Object> updateHealthComplete(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = healthExamMngtService.updateHealthComplete(param);
		
		return rsMap;
	}
	
	/**
	 * 의사 자동판정 완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/autoHealthComplete.do")
	public Map<String, Object> autoHealthComplete(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = healthExamMngtService.updateHealthComplete(param);	// 기존로직
		
		// 1. svcJoinMngtService에 넘길 파라미터를 정의한다.
		param.put("TRGTER_STTUS", "20");	// 예비대상자상태 (20:참여결정) 
		param.put("CNSL_NEED_YN", "Y");		// 상담필요여부 (Y 고정)
		param.put("cntData", "52");			// 고정 52
		
		// 빈값이지만 맵에 put (안하면 mybatis에서 오류남)
		param.put("HEALTH_STTUS", "");
		param.put("F_CNSL_CONT", "");
		param.put("CNSL_DE_MID", "");
		param.put("M_CNSL_CONT", "");
		param.put("CNSL_DE_LST", "");
		param.put("L_CNSL_CONT", "");
		param.put("OBJ_ACT", "");
		param.put("OBJ_DIET", "");
		param.put("RMK", "");
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMdd");
		Date date = new Date();
		param.put("CNSL_DE", sdf.format(date));	// 상담일자
		
		List<Map<String, Object>> riskMapList = svcJoinMngtService.getSvcJoinMngtDtlsRisk(param);
		Map<String, Object> riskMap = riskMapList.get(0);
		
		if(riskMap.get("BLOOD_PRESS_JUDGE_NM").equals("위험")) {
			param.put("RISK_BLOOD_PRESS_CHK", "Y");	// 위험 혈압체크
		} else {
			param.put("RISK_BLOOD_PRESS_CHK", "N");	// 위험 혈압체크
		}
		
		if(riskMap.get("BLOOD_SUGAR_JUDGE_NM").equals("위험")) {
			param.put("RISK_BLOOD_SUGAR_CHK", "Y");	// 위험 혈당체크
		} else {
			param.put("RISK_BLOOD_SUGAR_CHK", "N");	// 위험 혈당체크
		}
		
		if(riskMap.get("WAIST_MSMT_JUDGE_NM").equals("위험")) {
			param.put("RISK_WAIST_MSMT_CHK", "Y");	// 위험 허리둘레체크
		} else {
			param.put("RISK_WAIST_MSMT_CHK", "N");	// 위험 허리둘레체크
		}		
		
		if(riskMap.get("HDL_CHOL_JUDGE_NM").equals("위험")) {
			param.put("RISK_HDL_CHOL_CHK", "Y");	// 위험 HDL체크
		} else {
			param.put("RISK_HDL_CHOL_CHK", "N");	// 위험 HDL체크
		}	
		
		if(riskMap.get("NEUTRAL_FAT_JUDGE_NM").equals("위험")) {
			param.put("RISK_NEUTRAL_FAT_CHK", "Y");	// 위험 중성지방체크
		} else {
			param.put("RISK_NEUTRAL_FAT_CHK", "N");	// 위험 중성지방체크
		}	
		
		Map<String, Object> gunParam = new HashMap<String, Object>();
		gunParam.put("HDL", param.get("RISK_HDL_CHOL_CHK"));
		gunParam.put("NEUTRAL", param.get("RISK_NEUTRAL_FAT_CHK"));
		gunParam.put("SUGAR", param.get("RISK_BLOOD_SUGAR_CHK"));
		gunParam.put("BLOOD", param.get("RISK_BLOOD_PRESS_CHK"));
		gunParam.put("WAIST", param.get("RISK_WAIST_MSMT_CHK"));
		Map<String, Object> gunMap = svcJoinMngtService.getSelSvcJoinMngtGunClas(gunParam);
		
		param.put("LCLAS_CD", gunMap.get("LCLAS_CD"));	// 대분류
		param.put("MCLAS_CD", gunMap.get("MCLAS_CD"));	// 중분류
		param.put("SCLAS_CD", gunMap.get("SCLAS_CD"));	// 소분류
		param.put("GCLAS_CD", gunMap.get("GCLAS_CD"));	// 군분류
		
		svcJoinMngtService.newInsertSvcJoinMngt(param);
		
		Map<String, Object> hisMap = svcJoinMngtService.getSvcMngtNoForUserId(param);
		param.put("SVC_MNGT_NO", hisMap.get("SVC_MNGT_NO"));
		svcJoinMngtService.insertHistory(param);							//2016.12.01
		
		return rsMap;
	}	
	
	/**
	 * 건강검진관리 상세조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectHealthMngtDetail.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHealthMngtDetail(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> detailMap = healthExamMngtService.selectHealthMngtDetail(param);
		
		rsMap.put("detailMap", detailMap);
		
		return rsMap;
	}	
	
	/**
	 * 건강검진관리 저장여부 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSaveYnCheck.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSaveYnCheck(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = healthExamMngtService.selectSaveYnCheck(param);
		return rsMap;
	}
	
	/**
	 * 건강검진관리 삭제 - cnsl_no 사용으로 변경 jeeeeey 20231107
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delHealthMngt.do", method= RequestMethod.POST)
	public void delHealthMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthExamMngtService.delHealthMngt(param);
	}
	
	/**
	 * 주민등록번호 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/saveResNo.do")
	public @ResponseBody Map<String, Object> updateResNo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = healthExamMngtService.updateResNo(param);
		return rsMap;
	}
	
	/**
	 * 생년월일 수정
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateBirth.do")
	public void updateBirth(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthExamMngtService.updateBirth(param);
		
	}
	
	/**
	 * 검진 생성 시 데이터 존재 여부 체크
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/healthExamCntChk.do")
	public @ResponseBody Map<String, Object> healthExamCntChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = healthExamMngtService.healthExamCntChk(param);
		return rsMap;
	}
	
	/**
	 * POCT 검진 완료 데이터 인입 여부 체크
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/poctSaveYnChk.do")
	public @ResponseBody Map<String, Object> poctSaveYnChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = healthExamMngtService.poctSaveYnChk(param);
		
		return rsMap;		
	}
	
}
