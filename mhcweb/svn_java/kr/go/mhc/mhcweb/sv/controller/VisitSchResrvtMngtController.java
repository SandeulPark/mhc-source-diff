package kr.go.mhc.mhcweb.sv.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sv.service.VisitSchResrvtMngtService;

/**
 * @Class Name : VisitSchResrvtMngtController.java
 * @Description : 관리자 WEB에서 방문 예약 일정을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.06.12		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.06.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class VisitSchResrvtMngtController extends DMultiActionController{

	@Resource(name = "web.sv.VisitSchResrvtMngtService")
	private VisitSchResrvtMngtService visitSchResrvtMngtService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 방문일정 예약관리 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/visitSchResrvtMngtMain.do", method = RequestMethod.GET)
	public String visitSchResrvtMngtMain(@ModelAttribute Map param, ModelMap model) throws Exception {
				
		return "web/sv/visitSchResrvtMngt";
	}
	
	/**
	 * 방문일정예약 월별 예약 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitSchResrvtMonthList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchResrvtMonthList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsMonthList = visitSchResrvtMngtService.getVisitSchResrvtMonthList(param);
		rsMap.put("rsMonthList", rsMonthList);
		
		
		
		return rsMap;
	}
	
	/**
	 * 방문일정예약 일별 예약 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/visitSchResrvtDayList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchResrvtDayList(@ModelAttribute Map param, ModelMap model) throws Exception {

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));

		// 검색조건 년도 안 넘어오면 올해 값 넣어주기
		if("".equals(StringUtil.nvl(String.valueOf(param.get("SEL_DATE"))))) {
			param.put("THIS_YEAR", "Y"); // 올해
		} else {
			param.put("TRGT_YY", param.get("SEL_DATE").toString().substring(0,4));
		}
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		// org_dtls 에 데이터 있음.
		if(!rsOrgDtlsList.isEmpty()) {
			param.put("MID_EXAM_USE_YN", rsOrgDtlsList.get(0).get("MID_EXAM_USE_YN")); // 중간검진 진행 여부 저장
		}

		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsDayList = visitSchResrvtMngtService.getVisitSchResrvtDayList(param);
		rsMap.put("rsDayList", rsDayList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		rsMap.put("fixRmk", param.get("RMK").toString());
		rsMap.put("isResrvSchd", param.get("isResrvSchd").toString());
		
		return rsMap;		
	}
	
	/**
	 * 방문일정예약 시간대별 예약 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitSchResrvtTimeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchResrvtTimeList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitSchResrvtTimeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;		
	}
	
	
	/**
	 * 방문일정예약 설정 일괄 등록 팝업 호출
	 */
	@RequestMapping(value= "/visitBundleSetPop.do", method = RequestMethod.GET)
	public String visitBundleSetPop(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/sv/visitBundleSetPop";
	}		
	
	
	/**
	 * 방문일정예약 설정 일괄 시간 목록
	 */
	@RequestMapping(value= "/visitSchBundleTimeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchBundleTimeList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsBundleList = visitSchResrvtMngtService.getBundleSetTimeList(param);
		rsMap.put("rsBundleList", rsBundleList);

		return rsMap;		
	}

	/**
	 * 방문일정예약 일괄등록 장소 목록
	 */
	@RequestMapping(value= "/visitSchBundlePlaceList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchBundlePlaceList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsBundleList = visitSchResrvtMngtService.getBundleSetPlaceList(param);
		rsMap.put("rsBundlePlaceList", rsBundleList);

		return rsMap;
	}
	
	
	/**
	 * 방문일정예약 설정 일괄 등록 정보 저장
	 */
	
	@RequestMapping(value= "/saveVisitSchBuldleInfo.do", method = RequestMethod.POST)
	public @ResponseBody int saveVisitSchBuldleInfo(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = visitSchResrvtMngtService.saveVisitSchBuldleInfo(param);	
		return rsInt;
	}	
	
	
	/**
	 * 방문일정예약 설정 예약일정변경 팝업 호출
	 */
	@RequestMapping(value= "/visitResrvtChange.do", method = RequestMethod.GET)
	public String visitResrvtChangePop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("VISIT_DE", param.get("VISIT_DE"));

		return "web/sv/visitResrvtChangePop";
	}		
	
	/**
	 * 방문일정예약 설정 예약 등록 추가 팝업 호출
	 */
	@RequestMapping(value= "/visitResrvtTrgterAdd.do", method = RequestMethod.GET)
	public String visitResrvtTrgterAdd(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sv/visitResrvtTrgterAddPop";
	}	
	
	/**
	 * 방문일정예약 설정 예약 등록 추가 목록 조회
	 */
	
	@RequestMapping(value= "/visitResrvtTrgterAddList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> visitResrvtTrgterAddList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {		
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitResrvtTrgterAddList(param);
		if(rsList.size() > 0) 		{
			for(int i=0; i<rsList.size(); i++) {		// 날짜 리스트

				param.put("SESS_USER_ID", rsList.get(i).get("USER_ID"));
				Map<String, String> myWeekMap  = visitSchResrvtMngtService.selectMyWeek(param);	// key : myWeek , value : 내 현재 주차

				int myWeek = Integer.parseInt(myWeekMap.get("MY_WEEK").replaceAll(" ", ""));	// 현재 내 주차 (int)
				int myWeekMon = Integer.parseInt(myWeekMap.get("MY_WEEK_MON").replaceAll(" ", ""));	// 현재 내 주차의 월요일 (int)
				int visitWeekMon = Integer.parseInt(myWeekMap.get("VISIT_WEEK_MON").replaceAll(" ", ""));	// 방문 주차의 월요일 (int)

				Map<String, Object> paramMap = new HashMap<>();

				paramMap.put("visitDe", param.get("SEL_DATE").toString());
				paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD").toString());
				paramMap.put("VISIT_WEEK_CD", param.get("VISIT_WEEK_CD").toString());
				List<Map<String,String>> rsTmList = visitSchResrvtMngtService.selectVisitResrvtDeTm(paramMap);

				if(rsTmList.size() > 0) {
					for(Map<String, String> tmMap : rsTmList) {

						int resultDe = diffDay(param.get("SEL_DATE").toString(), myWeekMap.get("MY_WEEK_MON").replaceAll(" ", "")); // 방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀다.
						int resultMyWeek = myWeek;
						int resultWeek = 0;	// 방문예정일 주차에서 내 주차를 뺀만큼의 주차
						if(resultDe > 0) {
							resultWeek = resultDe / 7;
						}
						resultMyWeek += resultWeek;
						String myWeekCd = this.computeWeekCd(myWeek);
						String resultMyWeekCd = this.computeWeekCd(resultMyWeek);

						System.out.println("------------------------------------------------------------------");
						System.out.println("방문예정일 : " + param.get("SEL_DATE"));
						System.out.println("방문예정일 주차의 월요일 : " + visitWeekMon);
						System.out.println("내 주차의 월요일 : " + myWeekMon);
						System.out.println("방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀 일수 : " + resultDe);
						System.out.println("방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀 주차 : " + resultWeek + " 주");
						System.out.println("내 주차 : " + myWeek);
						System.out.println("내 주차코드 : " + myWeekCd);
						System.out.println("계산된 주차코드 : " + resultMyWeekCd);
						System.out.println("계산된 주차 : " + resultMyWeek);
						System.out.println("DB에 있는 주차코드 : " + tmMap.get("RESV_WEEK_CNT_CD"));
						System.out.println("tmMap : " + tmMap.toString());
						System.out.println("------------------------------------------------------------------");


						if("0".equals(tmMap.get("RESV_WEEK_CNT_CD").replaceAll(" ", ""))) {		// 예약주차가 0:전체 이면 Y(노출) 세팅
							rsList.get(i).put("visitFlag", "Y");
							break;
						} else if(resultMyWeekCd.equals(tmMap.get("RESV_WEEK_CNT_CD").replaceAll(" ", ""))) {
							// 존재한다.
							rsList.get(i).put("visitFlag", "Y");
							break;
						} else {
							// 존재안함
							rsList.get(i).put("visitFlag", "N");
							continue;
						}
					}
				} else {
					rsList.get(i).put("visitFlag", "N");
				}
			}
			for(int i=0; i<rsList.size(); i++) {	// 방문시간이 없으면 리스트 삭제
				if(rsList.get(i).get("visitFlag").equals("N")) {
					rsList.remove(i);
					i--;
				}
			}
		}
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.put("selDate", param.get("SEL_DATE"));

		return rsMap;
	}	
	
	
	/**
	 * 방문일정예약 설정 대상자 추가 등록
	 */
	
	@RequestMapping(value= "/insertVisitResrvtTrgter.do", method = RequestMethod.POST)
	public @ResponseBody int insertVisitResrvtTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = visitSchResrvtMngtService.insertVisitResrvtTrgter(param);	

		return rsInt;
	}	
	
	/**
	 * 방문일정예약 설정 대상자 일정 변경 저장
	 */
	
	@RequestMapping(value= "/saveVisitResrvtTrgter.do", method = RequestMethod.POST)
	public @ResponseBody int saveVisitResrvtTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = visitSchResrvtMngtService.saveVisitResrvtTrgter(param);	

		return rsInt;
	}		
	
	/**
	 * 대상자 방문일정예약 정보 반려 처리
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitSchResrvtCancel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> visitSChResrvtCancel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		visitSchResrvtMngtService.saveVisitSchResrvtCancel(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitSchResrvtTimeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}	
	
	
	/**
	 * 방문일정예약 설정 등록 정보 저장
	 */
	@RequestMapping(value= "/saveVisitSchSetInfo.do", method = RequestMethod.POST)
	public @ResponseBody int saveVisitSchSetInfo(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = visitSchResrvtMngtService.saveVisitSchSetInfo(param);	

		return rsInt;
	}		

	/**
	 * 방문일정예약 설정 상세 정보 등록 정보 저장
	 */
	@RequestMapping(value= "/saveVisitSchDtls.do", method = RequestMethod.POST)
	public @ResponseBody int saveVisitSchDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("new", "new");
		visitSchResrvtMngtService.saveVisitSchSetInfo(param);
		int rsInt = visitSchResrvtMngtService.saveVisitSchDtls(param);

		return rsInt;
	}

	/**
	 * 엑셀 팝업 호출
	 */
	@RequestMapping(value= "/visitSchResrvtExcelPop.do", method = RequestMethod.GET)
	public String visitSchResrvtExcelPop(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/sv/visitSchResrvtExcelPop";
	}		

	/**
	 * 방문예약 대상자 엑셀 추출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectVisitSchResrvtExcel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchResrvtExcel(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitSchResrvtExcel(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;		
	}
	
	/**
	 * 방문예약 가능 시간 조회 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/getVisitDeTmList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitDeTmList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();

		Map<String, String> myWeek = new HashMap<>();
		String weekCd = "";
		// 선택된 대상자 대표 USER_ID param 에 넣기
		param.put("SESS_USER_ID", param.get("USER_ID").toString());	
		param.put("SEL_DATE", param.get("visitDe").toString());

		// 선택한 날짜의 주차 찾기
		myWeek = visitSchResrvtMngtService.selectMyWeek(param);
		// 해당 주차의 주차 코드
		weekCd = computeWeekCd(Integer.parseInt(myWeek.get("VISIT_WEEK").toString()));
		param.put("VISIT_WEEK_CD", weekCd);

		// 방문 시간/ 방문 장소 검색
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitDeTmList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("TYPE", param.get("TYPE").toString());

		return rsMap;
	}
	
	/**
	 * 대상자 방문일정예약 정보 반려 처리
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitSchResrvtChange.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> visitSchResrvtChange(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		visitSchResrvtMngtService.saveVisitSchResrvtChange(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitSchResrvtTimeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;

	}
	
	/**
	 * 대상자 방문일정예약 정보 반려 처리 가능 여부 조회
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitSchResrvtChangeChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitSchResrvtChangeChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		List<Map<String, Object>> rsChkList = visitSchResrvtMngtService.getVisitSchResrvtChangeChk(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		rsMap.put("rsChkList", rsChkList);
		return rsMap;
	}
	
	/**
	 * 중간 주차 리스트 가져오기
	 * @author 최대길 (2020-07-13) 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/visitMidWeekList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitMidWeekList() throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsDayList = visitSchResrvtMngtService.getVisitMidWeekList();
		rsMap.put("rsDayList", rsDayList);

		return rsMap;		
	}
	
	public String computeWeekCd(int week) {

		String myWeekCd;
		
		if(week >= 2 && week <= 11) {
			myWeekCd = "1";
		} else if(week >= 12 && week <= 15) {
			myWeekCd = "2";
		} else if(week >= 16 && week <= 23) {
			myWeekCd = "3";
		} else if(week >= 24 && week <= 27) {
			myWeekCd = "4";			
		} else {
			myWeekCd = "0";
		}		
		return myWeekCd;
	}
	
	public int diffDay(String visitMon, String myWeekMon) {
		long diff;
		try {
			Date visitMonday = new SimpleDateFormat("yyyyMMdd").parse(visitMon);
			Date myWeekMonday = new SimpleDateFormat("yyyyMMdd").parse(myWeekMon);
			
			diff = visitMonday.getTime() - myWeekMonday.getTime();
		} catch (ParseException e) {
			diff = 0;
		}
		return (int)(diff / (1000 * 60 * 60 * 24));
	}


	/**
	 * 방문예약 장소 조회
	 */
	@RequestMapping(value= "/selectResrvtVisitPlace.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectResrvtVisitPlace(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.selectResrvtVisitPlace(param);
		rsMap.put("rsList", rsList);

		return rsMap;
	}

	/**
	 * 방문예약 장소 조회 팝업용 기본 정보
	 */
	@RequestMapping(value= "/getVisitPlace.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVisitPlace(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitPlace(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));

		return rsMap;
	}

	/**
	 * 방문 장소 관리 팝업 호출
	 */
	@RequestMapping(value= "/visitSchReservtPlacePop.do", method = RequestMethod.GET)
	public String visitSchReservtPlacePop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/visitSchReservtPlacePop";
	}

	/**
	 * 신규 장소 순번 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/newVisitPlaceSn.do", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> newVisitPlaceSn( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = visitSchResrvtMngtService.getNewVisitPlaceSn(param);
		return rsMap;
	}

	/**
	 * 방문 장소 정보 저장
	 */
	@RequestMapping(value= "/saveVisitPlace.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveVisitPlace(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = visitSchResrvtMngtService.saveVisitPlace(param);

		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitPlace(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));

		return rsMap;
	}

	/**
	 * 방문 장소 정보 삭제
	 */
	@RequestMapping(value= "/deleteVisitPlace.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteVisitPlace(@ModelAttribute Map param, ModelMap model) throws Exception {
		// 1) 방문 장소 정보 삭제 
		int rsInt = visitSchResrvtMngtService.deleteVisitPlace(param);

		// TODO 2) 방문 장소 같이 예약된 정보 삭제


		// 3) "???"  또 뭐가 있을까
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = visitSchResrvtMngtService.getVisitPlace(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));

		return rsMap;
	}

	/**
	 * 해당 장소에 예약된 인원 수 확인
	 * @param Param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getResrvtUserCount.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectResrvtUserCount(@ModelAttribute Map Param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = visitSchResrvtMngtService.selectResrvtUserCount(Param);
		return rsMap;
	}

	/**
	 *  일괄 예약시 한 날짜에 여러 분 단위 겹치지 않도록 예약 내역 조회
	 * @param Param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getResrvtCountByVisitDe.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectResrvtCountByVisitDe(@ModelAttribute Map Param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = visitSchResrvtMngtService.selectResrvtCountByVisitDe(Param);
		return rsMap;
	}

}
