package kr.go.mhc.mhcapp.gn.controller;

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
import kr.go.mhc.mhcapp.gn.service.GnrlVisitReservationService;


/**
 * @Class Name : GnrlVisitReservationController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 방문예약을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	     수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.09.27		이태석			최초생성
 * 		
 *
 * @author thejoin
 * @since 2019.09.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/gn")
public class GnrlVisitReservationController extends DMultiActionController{ 

	@Resource(name="mhcapp.gn.gnrlVisitReservationService")
	private GnrlVisitReservationService gnrlVisitReservationService;
	
	@ModelAttribute
	public Map<String, Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 보건소 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectOrgNmList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectOrgNmList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			List<Map<String,String>> rsList = gnrlVisitReservationService.selectOrgNmList(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
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
		
		try{
			List<Map<String,String>> rsList = gnrlVisitReservationService.selectVisitCalendarSet(param); 
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
		try{
			List<Map<String,String>> rsList = gnrlVisitReservationService.selectVisitDeTm(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
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
		
		Map<String,String> rsMap = gnrlVisitReservationService.insertVisitReservationInfo(param); 
			
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
			List<Map<String,String>> rsList = gnrlVisitReservationService.selectVisitReservationList(param); 
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
		
		Map<String,String> rsMap = gnrlVisitReservationService.selectVisitReservationCnt(param); 
			
		return rsMap;
	}
	
	/**
	 * 예약 상태 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectResveSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> selectResveSttus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String,String> rsMap = gnrlVisitReservationService.selectResveSttus(param); 
			
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
		gnrlVisitReservationService.cancelVisitReservationInfo(param);
	}	
	
	/**
	 * 기관 설정 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectOrgCalendarSetCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> selectOrgCalendarSetCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String,String> rsMap = gnrlVisitReservationService.selectOrgCalendarSetCnt(param); 
			
		return rsMap;
	}
}
