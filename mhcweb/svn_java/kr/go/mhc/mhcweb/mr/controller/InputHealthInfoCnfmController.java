package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.InputHealthInfoCnfmService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : InputHealthInfoCnfmController.java
 * @Description : 자가입력 건강정보 확인 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/mr")
public class InputHealthInfoCnfmController extends DMultiActionController {
	
	@Resource(name = "web.mr.InputHealthInfoCnfmService")
	private InputHealthInfoCnfmService inputHealthInfoCnfmService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 자가입력 건강정보 확인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/inputHealthInfoCnfm.do")
	public String boardQna(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/mr/inputHealthInfoCnfm";
	}
	
	/**
	 * 자가입력 건강정보 확인 대상자 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/trgterInfoList.do")
	public @ResponseBody Map<String, Object> trgterInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectTrgterInfoList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	
	/*신체활동 탭 조회 start*/
	/**
	 * 신체활동 탭 활동목표 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterActList.do")
	public @ResponseBody Map<String, Object> selTrgterActList(@ModelAttribute Map<String, Object>param, ModelMap model )throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterActList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 신체활동 탭 활동량 누적, 평균 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterTotActCnt.do")
	public @ResponseBody Map<String, Object> selTrgterTotActCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterTotActCnt(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 신체활동 탭 서비스 주차별 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterWKInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 신체활동 탭 일자별 활동량 측정 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dayActDataList.do")
	public @ResponseBody Map<String, Object> dayActDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectDayActDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 신체활동 탭 날짜별, 요일별 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterActDEList.do")
	public @ResponseBody Map<String, Object> selTrgterActDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListDE = inputHealthInfoCnfmService.selTrgterActDEList(param);
		List<Map<String, Object>> rsListDY = inputHealthInfoCnfmService.selTrgterActDYList(param);
		Map<String, Object> rsMapDEAVG = inputHealthInfoCnfmService.selTrgterActDEAVG(param);
		rsMap.put("rsListDE", rsListDE);
		rsMap.put("rsListDY", rsListDY);
		rsMap.put("rsMapDEAVG", rsMapDEAVG);
		return rsMap;
	}
	/*신체활동 탭 조회 end*/
	
	
	
	/*체성분 탭 조회 start*/
	/**
	 * 체성분 탭 체중목표 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterWeightList.do")
	public @ResponseBody Map<String, Object> selTrgterWeightList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterWeightList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 체성분 탭 서비스 주차별 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterWeightWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterWeightWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterWeightWKInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 체성분 탭 일자별 측정 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dayBodyCompDataList.do")
	public @ResponseBody Map<String, Object> dayBodyCompDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectDayBodyCompDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 체성분 탭 일자별 측정값 변화 차트 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterWeightDEList.do")
	public @ResponseBody Map<String, Object> selTrgterWeightDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterWeightDEList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 체성분 탭 체성분 시작, 종료 비교(테이블) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterWeightSTED.do")
	public @ResponseBody Map<String, Object> selTrgterWeightSTED(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMapSTED = inputHealthInfoCnfmService.selTrgterWeightSTED(param);
		return rsMapSTED;
	}
	/*체성분 탭 조회 end*/
	
	
	
	/*혈압 탭 조회 start*/
	/**
	 * 혈압 탭 최근측정 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldPress.do")
	public @ResponseBody Map<String, Object> selTrgterBldPress(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = inputHealthInfoCnfmService.selTrgterBldPress(param);
		return rsMap;
	}
	
	/**
	 * 혈압 탭 일자별 측정값 변화 차트 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldPressDEList.do")
	public @ResponseBody Map<String, Object> selTrgterBldPressDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterBldPressDEList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 혈압 탭 기간 평균, 최초 측정 비교 (테이블) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldPressSTAVG.do")
	public @ResponseBody Map<String, Object> selTrgterBldPressSTAVG(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = inputHealthInfoCnfmService.selTrgterBldPressSTAVG(param);
		return rsMap;
	}
	
	/**
	 * 혈압 탭 서비스 주차별 현황 (그리드) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterBldWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterBldWKInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 혈압 탭  이상 측정정보(그리드) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pressDisorderExamInfo.do")
	public @ResponseBody Map<String, Object> pressDisorderExamInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selPressDisorderExamInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 혈압 탭 혈압 일자별 측정 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dayBloodPressDataList.do")
	public @ResponseBody Map<String, Object> dayBloodPressDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectDayBloodPressDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/*혈압 탭 조회 end*/
	
	
	
	/*혈당 탭 조회 start*/
	/**
	 * 혈당 탭 혈당 최근 측정현황 (테이블)정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldSugar.do")
	public @ResponseBody Map<String, Object> selTrgterBldSugar(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = inputHealthInfoCnfmService.selTrgterBldSugar(param);
		return rsMap;
	}
	
	/**
	 * 혈당 탭 측정값 변화 (차트) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldSugarDEList.do")
	public @ResponseBody Map<String, Object> selTrgterBldSugarDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterBldSugarDEList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 혈당 탭 기간 평균, 최초 측정 비교 (테이블) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldSugarSTAVG.do")
	public @ResponseBody Map<String, Object> selTrgterBldSugarSTAVG(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = inputHealthInfoCnfmService.selTrgterBldSugarSTAVG(param);
		return rsMap;
	}
	
	/**
	 * 혈당 탭 서비스 주차별 현황 (그리드) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selTrgterBldSugarWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterBldSugarWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selTrgterBldSugarWKInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 혈당 탭 이상 측정정보(그리드) 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sugarDisorderExamInfo.do")
	public @ResponseBody Map<String, Object> sugarDisorderExamInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selSugarDisorderExamInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 혈당 탭 혈당 일자별 측정 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dayBloodSugarDataList.do")
	public @ResponseBody Map<String, Object> dayBloodSugarDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectDayBloodSugarDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/*혈당 탭 조회 end*/

	/*복약 탭 조회 start */

	/**
	 * 복약 탭 - 복용약 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selDrugInfo.do")
	public @ResponseBody Map<String, Object> selDrugInfoList(@ModelAttribute  Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selectDrugInfoList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 복약 탭 - 복약 일자별 복용 현황 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selDrugMissionAnswerList.do")
	public @ResponseBody Map<String, Object> selDrugMissionAnswerList(@ModelAttribute  Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = inputHealthInfoCnfmService.selDrugMissionAnswerList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}

}
