package kr.go.mhc.mhcweb.gn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import kr.go.mhc.mhcweb.gn.service.GnrlGroupMngtService;

/**
 * @Class Name : GnrlGroupMngtController.java
 * @Description : 관리자 WEB에서 사용하는 일반사용자 그룹관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.23		이태석			최초생성
 * 
 * @author thejoin
 * @since 2019.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/gn")
public class GnrlGroupMngtController extends DMultiActionController{
	@Resource(name = "web.gn.GnrlGroupMngtService")
	private GnrlGroupMngtService gnrlGroupMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 그룹관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gnrlGroupMngt.do", method = RequestMethod.GET)
	public String cmntyGSet(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/gn/gnrlGroupMngt";
	}
	
	/**
	 * 그룹 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectGnrlGroupList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectGnrlGroupList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGnrlGroupList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}
	
	/**
	 * 그룹 참여자 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectGroupJoinList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectGroupJoinList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGroupJoinList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 그룹 참여 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gnrlGroupJoinYnPop.do", method= RequestMethod.GET)
	public String gnrlGroupJoinYnPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("joinYnIndex", param.get("joinYnBtn").toString().substring(10));
		return "web/gn/gnrlGroupJoinYnPop";
	}
	
	/**
	 * 그룹 참여 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateJoinYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateJoinYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = gnrlGroupMngtService.updateJoinYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 추가 대상자 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gnrlGroupMngtPop.do", method = RequestMethod.GET)
	public String selectAddGroupUserPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/gn/gnrlGroupMngtPop";
	}
	
	/**
	 * 추가 사용자 조회 (팝업)
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectAddGroupUserList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectAddGroupUserList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectAddGroupUserList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 그룹 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/insertGroupUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertGroupUser(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlGroupMngtService.insertGroupUser(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGroupJoinList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 참여자 제외
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteJoinGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteJoinGroup(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlGroupMngtService.deleteJoinGroup(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGroupJoinList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 신규 그룹 순번 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectNewGroupSn.do", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> selectNewGroupSn( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = gnrlGroupMngtService.selectNewGroupSn(param);
		return rsMap;
	}
	
	/**
	 * 신규 그룹 등록
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/insertNewGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertNewGroup(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlGroupMngtService.insertNewGroup(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGnrlGroupList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 그룹 수정
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateGroup(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlGroupMngtService.updateGroup(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlGroupMngtService.selectGnrlGroupList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 그룹관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/groupActSttus.do", method = RequestMethod.GET)
	public String groupActSttus(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/gn/groupActSttus";
	}
	
	/**
	 * 일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectDayAct.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectDayAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectDayAct(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 요일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectDayWeekAct.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectDayWeekAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectDayWeekAct(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 시간대별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectTmAct.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTmAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectTmAct(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 성별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectGenderUserCnt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectGenderUserCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectGenderUserCnt(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 연령별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectAgeUserCnt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectAgeUserCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectAgeUserCnt(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 성별/연령별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectGenderAgeAct.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectGenderAgeAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectGenderAgeAct(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 그룹 별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectGruopRank.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectGruopRank(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectGruopRank(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 거
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectActCntList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectActCntList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String startDateStr = (String) param.get("startDate");
        String endDateStr = (String) param.get("endDate");

        // SimpleDateFormat을 이용하여 문자열을 Date로 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // 날짜 범위 생성
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (!calendar.getTime().after(endDate)) {
            dates.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        param.put("dates",dates);
        System.out.println("dates ===" + dates );
		List<Map<String, Object>> rsList = gnrlGroupMngtService.selectActCntList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 보편 기관 전체 그룹 개인정보 제3자 동의서 작성 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gnrlGroupAgreePop.do", method= RequestMethod.GET)
	public String gnrlGroupAgreePop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> prvAgr = gnrlGroupMngtService.selectOrgPrivacyAgree(param);
		model.addAttribute("prvAgr", prvAgr);

		return "web/gn/gnrlGroupAgreePop";
	}
	
	/**
	 * 보편 기관 전체 그룹 개인정보 제3자 동의서 미리보기 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gnrlGroupAgreeShowPop.do", method= RequestMethod.GET)
	public String gnrlGroupAgreeShowPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> prvAgr = gnrlGroupMngtService.selectOrgPrivacyAgree(param);
		model.addAttribute("prvAgr", prvAgr);

		return "web/gn/gnrlGroupAgreeShowPop";
	}

	/**
	 * 보편 기관 개인정보 제3자 동의서 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertOrgPrivacyAgree.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertOrgPrivacyAgree(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlGroupMngtService.insertOrgPrivacyAgree(param);
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("result", "success");
		return rsMap;
	}

}
