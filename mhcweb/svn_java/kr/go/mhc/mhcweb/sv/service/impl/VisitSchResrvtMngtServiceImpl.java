package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import kr.go.mhc.common.util.StringUtil;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sv.service.VisitSchResrvtMngtService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Class Name : VisitSchResrvtMngtServiceImpl.java
 * @Description : 관리자 WEB에서 방문일정을 관리하는 DAO와 연동 관리하는 Class
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

@Service("web.sv.VisitSchResrvtMngtService")
public class VisitSchResrvtMngtServiceImpl extends EgovAbstractServiceImpl implements VisitSchResrvtMngtService{

	@Resource(name = "web.sv.VisitSchResrvtMngtServiceDAO")
	private VisitSchResrvtMngtServiceDAO visitSchResrvtMngtServiceDAO;

	@Override
	public List<Map<String, Object>> getVisitSchResrvtMonthList(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitSchResrvtMonthList(param);
	}

	@Override
	public List<Map<String, Object>> getVisitSchResrvtDayList(Map<String, Object> param) throws Exception {

		String fixRmk ="t30";
		String isResrvSchd="N";

		// 분 먼저 확인
		List<Map<String, Object>> rmkMap = visitSchResrvtMngtServiceDAO.selectVisitResrvtRMK(param);
		for(Map<String, Object> rmk : rmkMap){
			fixRmk = rmk.get("RMK").toString();
			param.put("RMK", fixRmk);
			isResrvSchd = "Y";
		}

		param.put("isResrvSchd", isResrvSchd); // 예약 일정이 있는지 여부
		List<Map<String, Object>> rsMap = visitSchResrvtMngtServiceDAO.getVisitSchResrvtDayList(param);

		return rsMap;
	}

	@Override
	public List<Map<String, Object>> getVisitSchResrvtTimeList(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitSchResrvtTimeList(param);
	}

	@Override
	public List<Map<String, Object>> getBundleSetTimeList(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getBundleSetTimeList(param);
	}

	@Override
	public List<Map<String, Object>> getBundleSetPlaceList(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getBundleSetPlaceList(param);
	}

	@Override
	public int saveVisitSchBuldleInfo(Map<String, Object> param) throws Exception {

		// 제외 할 요일
		String excpStr = StringUtil.nvl((String)param.get("EXCP_STR"));
		if(StringUtil.makeStringToIterator(excpStr).size() > 0){
			param.put("excpStrList", StringUtil.makeStringToIterator(excpStr));
		}

		// 선택 장소
		String placeStr = StringUtil.nvl((String)param.get("PLACE_STR"));
		if(StringUtil.makeStringToIterator(placeStr).size() > 0){
			param.put("placeStrList", StringUtil.makeStringToIterator(placeStr));
		}

		// 입력 시간
		String hourStr = StringUtil.nvl((String)param.get("HOUR_STR"));
		if(StringUtil.makeStringToIterator(hourStr).size() > 0){
			param.put("hourStrList", StringUtil.makeStringToIterator(hourStr));
		}
		
		// 조합... 입력 시간 + 입력 분,
		List<String> timeRange = makeTimeRanges((String)param.get("HOUR_STR"), (String)param.get("TIME_STR"));
		// 조합한 시간 timeRange 로 공통코드 값 받아오기
		param.put("timeRange", timeRange);
		param.put("RMK", "t" + param.get("SELECTED_MIN").toString());
		List<Map<String, Object>> timeCodeList = visitSchResrvtMngtServiceDAO.getBundleSetTimeList(param);
		// 공통코드 조회된 값 param에 넣기
		param.put("timeStrList", timeCodeList);
		
		int rsInt = visitSchResrvtMngtServiceDAO.saveVisitSchBuldleInfo(param);		
		return rsInt; 
	}

	// 일괄 등록 시:분 ~ 시:분 조합 만들기
	public static List<String> makeTimeRanges(String hourStr, String timeStr) {
		String[] hours = hourStr.split(",");
		String[] timeParts = timeStr.split(",");
		List<String> ranges = new ArrayList<>();

		   for (String h : hours) {
			   int hour = Integer.parseInt(h);
			   for (String t : timeParts) {
				   int startMin = Integer.parseInt(t.substring(0, 2));
				   int endMin   = Integer.parseInt(t.substring(2));

				   String start = String.format("%02d:%02d", hour, startMin);
				   // 끝나는 시간이 60분이면 → 다음 시(hour+1)의 00분
				   String end;
				   if (endMin == 60) {
					   end = String.format("%02d:%02d", hour + 1, 0);
				   } else {
					   end = String.format("%02d:%02d", hour, endMin);
				   }
				   ranges.add(start + " ~ " + end);
			   }
		   }
		   return ranges;
   }
	
	
	@Override
	public List<Map<String, Object>> getVisitResrvtTrgterAddList(Map<String, Object> param) throws Exception {

		
		return visitSchResrvtMngtServiceDAO.getVisitResrvtTrgterAddList(param);
	}	
	
	@Override
	public void saveVisitSchResrvtCancel(Map<String, Object> param) throws Exception {
		visitSchResrvtMngtServiceDAO.saveVisitSchResrvtCancel(param);
	}		
	
	
	@Override
	public int saveVisitSchSetInfo(Map<String, Object> param) throws Exception {
		int rsInt = visitSchResrvtMngtServiceDAO.saveVisitSchSetInfo(param);
		return rsInt;
	}

	@Override
	public int saveVisitSchDtls(Map<String, Object> param) throws Exception {

		String placeData = (String) param.get("PLACE_PARAM");
		String datSet = placeData.replaceAll("&quot;", "\"");     //UPDATE 데이터 처리
		JSONArray array = JSONArray.fromObject(datSet);

		List<Map<String, Object>> placeList = new ArrayList<Map<String,Object>>();

		  for(int i=0; i<array.size(); i++) {
			  JSONObject obj = (JSONObject) array.get(i);
			  Map<String, Object> placeParam = new HashMap<String, Object>();

			  placeParam.put("ORG_CD", param.get("SESS_ORG_CD").toString());
			  placeParam.put("SEL_DATE", param.get("SEL_DATE").toString());
			  placeParam.put("VISIT_TM_CD", param.get("VISIT_TM_CD").toString());

			  placeParam.put("PLACE_SN", obj.get("PLACE_SN"));
			  placeParam.put("VISIT_SET_CNT", obj.get("VISIT_SET_CNT"));
			  placeParam.put("VISIT_WEEK_CD", obj.get("VISIT_WEEK_CD"));

		  	placeList.add(placeParam);
		  }
		  param.put("placeParam", placeList);


		return visitSchResrvtMngtServiceDAO.saveVisitSchDtls(param);
	}

	@Override
	public int saveVisitResrvtTrgter(Map<String, Object> param) throws Exception {
		int rsInt = visitSchResrvtMngtServiceDAO.saveVisitResrvtTrgter(param);
		return rsInt;
	}		

	@Override
	public int insertVisitResrvtTrgter(Map<String, Object> param) throws Exception {
		int rsInt = visitSchResrvtMngtServiceDAO.insertVisitResrvtTrgter(param);
		return rsInt;
	}	
	@Override
	public List<Map<String, Object>> getVisitSchResrvtExcel(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitSchResrvtExcel(param);
	}
	
	@Override
	public List<Map<String, Object>> getVisitDeTmList(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitDeTmList(param);
	}
	
	@Override
	public void saveVisitSchResrvtChange(Map<String, Object> param) throws Exception {
		visitSchResrvtMngtServiceDAO.saveVisitSchResrvtChange(param);
		
	}	
	
	@Override
	public List<Map<String, Object>> getVisitSchResrvtChangeChk(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitSchResrvtChangeChk(param);
	}
	
	@Override
	public List<Map<String, Object>> getVisitMidWeekList() throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitMidWeekList();
	}

	@Override
	public Map<String, String> selectMyWeek(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.selectMyWeek(param);
	}
	
	@Override
	public List<Map<String, String>> selectVisitResrvtDeTm(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.selectVisitResrvtDeTm(param);
	}

	@Override
	public List<Map<String, Object>> getVisitPlace(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getVisitPlace(param);
	}

	@Override
	public Map<String, Object> getNewVisitPlaceSn(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.getNewVisitPlaceSn(param);
	}

	@Override
	public int saveVisitPlace(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.saveVisitPlace(param);
	}

	@Override
	public int deleteVisitPlace(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.updateVisitPlaceUseYN(param);
	}

	@Override
	public List<Map<String, Object>> selectResrvtVisitPlace(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.selectResrvtVisitPlace(param);
	}

	@Override
	public Map<String, Object> selectResrvtUserCount(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.selectResrvtUserCount(param);
	}

	@Override
	public Map<String, Object> selectResrvtCountByVisitDe(Map<String, Object> param) throws Exception {
		return visitSchResrvtMngtServiceDAO.selectResrvtCountByVisitDe(param);
	}
}
