package kr.go.mhc.mhcapp.gn.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlSelfMissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlSelfMissionController.java
 * @Description : 보편건강 App에서 사용하는 셀프미션 관리하는 컨트롤러 Class
 * @Modification Information
 *
 * @author thejoin
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/gn")
public class GnrlSelfMissionController extends DMultiActionController{ 

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@Resource(name="mhcapp.gn.GnrlSelfMissionService")
	private GnrlSelfMissionService gnrlSelfMissionService;

	/**
	 * 셀프미션 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/selfMissionList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectSelfMissionList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = gnrlSelfMissionService.selectSelfMissionList(param);

		rsMap.put("rsList", rsList);
		return rsMap;
	}

	/**
	 * 셀프미션 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/selectSelfMission.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectSelfMission(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsList = gnrlSelfMissionService.selectSelfMission(param);
		return rsList;
	}

	/**
	 * 셀프미션 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/insertSelfMission.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertSelfMission(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";

		try{
			rsCount = gnrlSelfMissionService.insertSelfMission(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 셀프미션 삭제
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/deleteSelfMission.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteSelfMission(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			rsMap = gnrlSelfMissionService.deleteSelfMission(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}

		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 셀프미션 완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/completeSelfMission.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> completeSelfMission(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			gnrlSelfMissionService.completeSelfMission(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}

		rsMap.put("chkYn", chkYn);
		return rsMap;
	}


	/**
	 * 셀프미션 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/selfMissionTotalPoint.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selfMissionTotalPoint(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();

		try{
			rsMap = gnrlSelfMissionService.selfMissionTotalPoint(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}

	/**
	 * 셀프미션 뱃지 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/selectBadge.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBadge(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();

		try{
			rsMap = gnrlSelfMissionService.selectBadge(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}

	/**
	 * 셀프미션 달성뱃지 Insert / Update
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/checkAchieveBadge.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkAchieveBadge(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			Map<String, String> results = gnrlSelfMissionService.checkAchieveBadge(param);

			if (results != null && param.get("ACHVM_BADGE") != results.get("BADGE_CLF")) {
				gnrlSelfMissionService.updateAchieveBadge(param); //UPDATE
				} else {
				gnrlSelfMissionService.insertAchieveBadge(param); //INSERT
				}

			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}

		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 셀프미션 뱃지 현황 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping( value="/selectSelfMissionBadgeStts.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectSelfMissionBadgeStts(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = gnrlSelfMissionService.selectSelfMissionBadgeStts(param);

		rsMap.put("rsList", rsList);
		return rsMap;
	}

}
