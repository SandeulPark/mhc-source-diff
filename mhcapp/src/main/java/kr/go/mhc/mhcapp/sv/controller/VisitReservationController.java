package kr.go.mhc.mhcapp.sv.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import kr.go.mhc.mhcapp.sv.service.VisitReservationService;


/**
 * @Class Name : VisitReservationController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 방문예약을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.06.21		이태석			최초생성
 * 		
 *
 * @author thejoin
 * @since 2019.06.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class VisitReservationController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.sv.VisitReservationService")
	private VisitReservationService visitReservationService;
	
	@ModelAttribute
	public Map<String, Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * 방문 달력 설정 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectVisitCalendarSet.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectVisitCalendarSet(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		Map<String, String> myWeekMap  = visitReservationService.selectMyWeek(param);	// key : myWeek , value : 내 현재 주차 
		
		int myWeek = Integer.parseInt(myWeekMap.get("MY_WEEK").replaceAll(" ", ""));	// 현재 내 주차 (int)
		int myWeekMon = Integer.parseInt(myWeekMap.get("MY_WEEK_MON").replaceAll(" ", ""));	// 현재 내 주차의 월요일 (int)
		
		/**
		 * 	1. 오늘날짜기준으로 월요일 , 방문예약일 기준으로 월요일 을 구한다.
		 * 	2. 방문예약일 기준 월요일 - 오늘날짜기준 월요일
		 * 	3. 2번의 결과가 0 이면 내 현재주차와 방문예약주차가 같으므로, 기존로직대로 처리
		 * 	4. 2번의 결과가 n 이면 방문예약할때 당시의 내 주차를 n 만큼 더해준다  
		 * 	5. 이거를 하는 이유가 미래의 방문예약일에 설정된 주차가 내 주차인지 비교를 하기 위함이다.
		 */
		
		try{
			List<Map<String,String>> rsList = visitReservationService.selectVisitCalendarSet(param);
			LOG.debug("방문상담예약달력  최초리스트 : " + rsList.toString());
			if(rsList.size() > 0) {
				for(int i=0; i<rsList.size(); i++) {		// 날짜 리스트
					Map<String, Object> paramMap = new HashMap<>();
					
					paramMap.put("visitDe", rsList.get(i).get("VISIT_DE"));
					paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
					paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD"));
					List<Map<String,String>> rsTmList = visitReservationService.selectVisitDeTm(paramMap); 
					
					if(rsTmList.size() > 0) {
						for(Map<String, String> tmMap : rsTmList) {
							
							int visitMon = Integer.parseInt(rsList.get(i).get("VISIT_MON"));
							
							int resultDe = diffDay(rsList.get(i).get("VISIT_MON").toString(), myWeekMap.get("MY_WEEK_MON").replaceAll(" ", "")); // 방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀다.
							int resultMyWeek = myWeek;
							int resultWeek = 0;	// 방문예정일 주차에서 내 주차를 뺀만큼의 주차
							if(resultDe > 0) {
								resultWeek = resultDe / 7;
							}
							resultMyWeek += resultWeek;
							String myWeekCd = this.computeWeekCd(myWeek);
							String resultMyWeekCd = this.computeWeekCd(resultMyWeek);
							
							System.out.println("------------------------------------------------------------------");
							System.out.println("방문예정일 : " + rsList.get(i).get("VISIT_DE"));
							System.out.println("방문예정일 주차의 월요일 : " + visitMon);
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
								rsList.get(i).put("viewCss", "Y");
								break; 
							} else if(resultMyWeekCd.equals(tmMap.get("RESV_WEEK_CNT_CD").replaceAll(" ", ""))) {
								// 존재한다.
								rsList.get(i).put("viewCss", "Y");
								break;
							} else {
								// 존재안함
								rsList.get(i).put("viewCss", "N");
								continue;
							}							
						}
					} else {
						rsList.get(i).put("viewCss", "N");
					}
				}
			}
			
			rsMap.put("rsList", rsList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 방문가능 시간 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectVisitDeTm.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectVisitDeTm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try {
			
			Map<String, String> myWeekMap  = visitReservationService.selectMyWeek(param);	// key : myWeek , value : 내 현재 주차 
			
			int myWeek = Integer.parseInt(myWeekMap.get("MY_WEEK").replaceAll(" ", ""));	// 현재 내 주차 (int)
			int myWeekMon = Integer.parseInt(myWeekMap.get("MY_WEEK_MON").replaceAll(" ", ""));	// 현재 내 주차의 월요일 (int)
			
			
			/**
			 * TODO : 
			 * 	1. 오늘날짜기준으로 월요일 , 방문예약일 기준으로 월요일 을 구한다.
			 * 	2. 방문예약일 기준 월요일 - 오늘날짜기준 월요일
			 * 	3. 2번의 결과가 0 이면 내 현재주차와 방문예약주차가 같으므로, 기존로직대로 처리
			 * 	4. 2번의 결과가 n 이면 방문예약할때 당시의 내 주차를 n 만큼 더해준다  
			 * 	5. 이거를 하는 이유가 미래의 방문예약일에 설정된 주차가 내 주차인지 비교를 하기 위함이다.
			 */
		
		
			Map<String, Object> paramMap = new HashMap<>();
			
			paramMap.put("visitDe", param.get("visitDe").toString());
			paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID").toString());
			paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD").toString());
			List<Map<String,String>> rsTmList = visitReservationService.selectVisitDeTm(paramMap); 
			
			for(int i=0; i<rsTmList.size(); i++) {
				
				int visitMon = Integer.parseInt(computeMonday(param.get("visitDe").toString()));
				
				int resultDe = diffDay(param.get("visitDe").toString(), myWeekMap.get("MY_WEEK_MON").replaceAll(" ", "")); // 방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀다.
				int resultMyWeek = myWeek;
				int resultWeek = 0;	// 방문예정일 주차에서 내 주차를 뺀만큼의 주차
				if(resultDe > 0) {
					resultWeek = resultDe / 7;
				}
				resultMyWeek += resultWeek;
				String myWeekCd = this.computeWeekCd(myWeek);
				String resultMyWeekCd = this.computeWeekCd(resultMyWeek);
				
				System.out.println("------------------------------------------------------------------");
				System.out.println("방문예정일 : " + param.get("visitDe").toString());
				System.out.println("방문예정일 주차의 월요일 : " + visitMon);
				System.out.println("내 주차의 월요일 : " + myWeekMon);
				System.out.println("방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀 일수 : " + resultDe);
				System.out.println("방문예정일 주차의 월요일에서 내 주차의 월요일을 뺀 주차 : " + resultWeek + " 주");
				System.out.println("내 주차 : " + myWeek);
				System.out.println("내 주차코드 : " + myWeekCd);
				System.out.println("계산된 주차코드 : " + resultMyWeekCd);
				System.out.println("계산된 주차 : " + resultMyWeek);
				System.out.println("DB에 있는 주차코드 : " + rsTmList.get(i).get("RESV_WEEK_CNT_CD"));
				System.out.println("tmMap : " + rsTmList.get(i).toString());
				System.out.println("------------------------------------------------------------------");							
				
				
				if("0".equals(rsTmList.get(i).get("RESV_WEEK_CNT_CD").replaceAll(" ", ""))) {		// 예약주차가 0:전체 이면 Y(노출) 세팅
					rsTmList.get(i).put("visitFlag", "Y");
					continue; 
				} else if(resultMyWeekCd.equals(rsTmList.get(i).get("RESV_WEEK_CNT_CD").replaceAll(" ", ""))) {
					// 존재한다.
					rsTmList.get(i).put("visitFlag", "Y");
					continue;
				} else {
					// 존재안함
					rsTmList.get(i).put("visitFlag", "N");
					continue;
				}
			}

			System.out.println("방문상담예약시간  결과 : "+rsTmList.toString());
			
			for(int j=0; j<rsTmList.size(); j++) {	// 방문시간이 없으면 리스트 삭제
				if(rsTmList.get(j).get("visitFlag").equals("N")) {
					rsTmList.remove(j);
					j--;
				}
			}
			
			System.out.println("최종  결과 : "+rsTmList.toString());
			rsMap.put("rsList", rsTmList);

		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		return rsMap;
	}	
	
	/**
	 * 예약방문 정보 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/insertVisitReservationInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> insertVisitReservationInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String,String> rsMap = visitReservationService.insertVisitReservationInfo(param); 
			
		return rsMap;
	}	
	
	/**
	 * 예약 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectVisitReservationList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectVisitReservationList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String,String>> rsList = visitReservationService.selectVisitReservationList(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 예약 수 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectVisitReservationCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> selectVisitReservationCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String,String> rsMap = visitReservationService.selectVisitReservationCnt(param); 
			
		return rsMap;
	}
	
	/**
	 * 방문예약 취소
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cancelVisitReservationInfo.do", method = RequestMethod.POST)
	public void cancelVisitReservationInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		visitReservationService.cancelVisitReservationInfo(param);
	}	
	
	/**
	 * 예약 상태 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectResveSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> selectResveSttus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String,String> rsMap = visitReservationService.selectResveSttus(param); 
			
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
	
	public String computeMonday(String visitDt) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = sdf.parse(visitDt);
			System.out.println("date=" + date);
		} catch (ParseException e) {
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DATE, 2 - cal.get(Calendar.DAY_OF_WEEK));

		return sdf.format(cal.getTime());
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
}
