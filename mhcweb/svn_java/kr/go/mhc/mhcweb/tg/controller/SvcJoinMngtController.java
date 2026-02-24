package kr.go.mhc.mhcweb.tg.controller;

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
import kr.go.mhc.mhcweb.tg.service.HealthMngtCnslService;
import kr.go.mhc.mhcweb.tg.service.SvcJoinMngtService;

/**
 * @Class Name : SvcJoinMngtController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여 관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.22		이은주			최초생성
 * @	2016.12.01		이은주			군분류 이력 insert 추가.
 *
 * @author gst
 * @since 2016.08.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class SvcJoinMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.SvcJoinMngtService")
	private SvcJoinMngtService svcJoinMngtService;
	
	@Resource(name = "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	
	
	/**
	 * 서비스참여관리 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngt.do", method= RequestMethod.GET)
	public String svcJoinMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		//Map<String, Object> rsMap = svcJoinMngtService.getSvcJoinMngtCnt(param);
		//model.addAttribute("rsMap", rsMap);
		return "web/tg/svcJoinMngt";
	}
	
	/**
	 * 서비스참여관리 선정인원 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcCount.do")
	public @ResponseBody Map<String, Object> svcCount(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = svcJoinMngtService.getSvcJoinMngtCnt(param);
		
		return rsMap;
	}
	
	/**
	 * 서비스참여관리 예비대상자 건수 조회 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = svcJoinMngtService.getSvcJoinMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 서비스참여관리 상세 화면 호출
	 * 기본정보 및 질환정보
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtDtls.do", method= RequestMethod.POST)
	public String svcJoinMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMapBasic = svcJoinMngtService.getSvcJoinMngtDtlsBasic(param);
		param.put("trgChk","Y");	//TRGT_YY 조회조건 추가
		List<Map<String, Object>> rsListCnsl  = svcJoinMngtService.getSvcJoinMngtDtlsCnsl(param);
		List<Map<String, Object>> rsList  = svcJoinMngtService.getSvcJoinMngtDtlsRisk(param);
		
		param.put("preTrgterYn", "Y");
		param.put("CNSL_NO", rsMapBasic.get("CNSL_NO"));
		param.put("PRE_TRGTER_NO", rsMapBasic.get("PRE_TRGTER_NO"));
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);						
		model.addAttribute("examList", examList);

		// 의사선정 권한 확인 추가 202412
		param.put("CMMN_CD","SV061");
		List<Map<String, String>> rsAuthList = cmmnService.selectCmmnCd(param);

		model.addAttribute("rsAuthList", rsAuthList);
		model.addAttribute("rsMapBasic", rsMapBasic);
		model.addAttribute("rsListCnsl", rsListCnsl);
		model.addAttribute("rsList", rsList);
		return "web/tg/svcJoinMngtDtls";
	}
	
	/**
	 * 서비스참여관리 상세 화면 그리드
	 * 위험 요인
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtDtlsRisk.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinMngtDtlsRisk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		param.put("PRE_TRGTER_NO", param.get("PRE_TRGTER_NO"));

		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		List<Map<String, Object>> rsList  = svcJoinMngtService.getSvcJoinMngtDtlsRisk(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));

		
		return rsMap;
	}
	
	/**
	 * 서비스참여관리 상담일자 선택 후
	 * 질환정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtSelCnsl.do", method= RequestMethod.GET)
	public @ResponseBody Map<String, Object> svcJoinMngtSelCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		List<Map<String, Object>> rsListDiss  = svcJoinMngtService.getSvcJoinMngtDtlsDiss(param);
		
		rsMap.put("rsListDiss", rsListDiss);
		
		return rsMap;
	}
	
	/**
	 * 서비스참여관리 상담일자 선택 후
	 * 상담내용 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtSelCnsl2.do", method= RequestMethod.GET)
	public @ResponseBody Map<String, Object> svcJoinMngtSelCnsl2(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> cnslDateMap = svcJoinMngtService.getSvcJoinMngtDtlsCnslDate(param);
		rsMap.put("cnslDateMap", cnslDateMap);
		
		return rsMap;
		
	}
	
	/**
	 * 서비스참여관리 update 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateSvcJoinMngt.do", method= RequestMethod.POST)
	//@Transactional(isolation = Isolation.READ_COMMITTED)
	public String updateSvcJoinMngt(HttpServletRequest request, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CNSL_DE", param.get("CNSL_DE").toString().replace("-", ""));
		
		/* 21.06.18 chyoon 대상자 선정 중복 오류로 인해 
		 * update / insert 전에 한번 더 조회
		 * 값이 있으면 update / 없으면 insert로 가게끔.
		 * 재등록자 > 기존 서비스 번호 존재 > 
		 */ 		
		int dupCnt = svcJoinMngtService.chkDupSvcNo(param);		
		
		System.out.println("dupCnt =====> " + dupCnt);
		System.out.println("SVC_MNGT_NO =====> " + param.get("SVC_MNGT_NO"));

		// 만성질환 여부 확인 후, Y 아닐 경우, 만성질환군 입력 방지
		if(!param.get("CHRONIC_DISEASES_YN").toString().equals("Y")){
			param.put("CHRONIC_CD", "");
		}
		
		//case 1. 웹페이지에서 호출시 서비스 번호 없음, 중복 없음(신규) ==> insert
		if("".equals(param.get("SVC_MNGT_NO")) && dupCnt == 0) {			
			svcJoinMngtService.newInsertSvcJoinMngt(param); 
	        System.out.println("SVC_MNGT_NO 1111 끝남 =====> " + param.get("SVC_MNGT_NO"));
		}
		//case 2. 웹페이지에서 호출시 서비스 번호 없음, 중복 있음(동시 작업) ==> update
		else if("".equals(param.get("SVC_MNGT_NO")) && dupCnt != 0) {			
			param.put("DUP", "DUP");
			svcJoinMngtService.updateSvcJoinMngt(param);
		}		
		//case 3. 웹페이지에서 호출시 서비스 번호 있음(기존 인원) ==> update
		else if(!"".equals(param.get("SVC_MNGT_NO"))) {			
			svcJoinMngtService.updateSvcJoinMngt(param); 
		}
		
		//보편(GU) -> 사업(TR) 업데이트
		svcJoinMngtService.updateTrgterFlag(param);
		svcJoinMngtService.insertHistory(param);							//2016.12.01
		
		model.addAttribute("cnsl_no", param.get("CNSL_SN"));
		
		return "redirect:../pageNavi.do?menuCd=NTG130";
	}
	
	/**
	 * 서비스참여관리 검사결과보기 선택
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoin_pop.do", method= RequestMethod.GET)
	public String svcJoinPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = svcJoinMngtService.getSvcJoinMngtRsltPop(param);
		model.addAttribute("rsList", rsList);
		return "web/tg/svcJoinPop";
	}

	/**
	 * 서비스참여관리 질환자 선정 모달 표출
	 * 20251218 박양수 주임 요청
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinMngtDiseasePop.do", method= RequestMethod.GET)
	public String svcJoinMngtDiseasePop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/tg/svcJoinMngtDiseasePop";
	}
	
	/**
	 * 서비스참여관리 신규 클릭 시 군분류 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selSvcJoinMngtGunClas.do", method= RequestMethod.GET)
	public @ResponseBody Map<String, Object> selSvcJoinMngtGunClas(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = svcJoinMngtService.getSelSvcJoinMngtGunClas(param);

		return rsMap;
		
	}
	
	@RequestMapping(value="/svcJoinCnslPop.do")
	public String svcJoinCnslPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		param.put("trgChk","L");	//TRGT_YY 조회조건 추가
		List<Map<String, Object>> rsList = svcJoinMngtService.getSvcJoinMngtDtlsCnsl(param);
		model.addAttribute("rsList", rsList);
		return "web/tg/svcJoinCnslPop";
	}
	
	/**
	 *  탈락대상자정보백업 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/droptrageterInfoExcelList.do")
	public @ResponseBody Map<String, Object> droptrageterInfoExcelList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = svcJoinMngtService.droptrageterInfoExcelList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 건강위험요인 변화 결과보기 선택
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthRiskReducePop.do", method= RequestMethod.GET)
	public String healthRiskReducePop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = svcJoinMngtService.getHealthRiskReducePop(param);
		model.addAttribute("rsList", rsList);
		return "web/tg/healthRiskReducePop";
	}
	
	/**
	 * 건강 행태 변화 결과보기 선택
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthBehevImpPop.do", method= RequestMethod.GET)
	public String healthBehevImpPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = svcJoinMngtService.getHealthBehevImpPop(param);
		model.addAttribute("rsList", rsList);
		return "web/tg/healthBehevImpPop";
	}
	
	
	
}