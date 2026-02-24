package kr.go.mhc.mhcweb.gn.controller;

import java.util.ArrayList;
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
import kr.go.mhc.mhcweb.gn.service.GnMainService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





@Controller
@RequestMapping(value= "/gn")
public class GnMainController extends DMultiActionController {
	@Resource(name = "gnMainService")
	private GnMainService gnMainService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	@RequestMapping(value = "/main.do")
	public String mainView(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String,Object>> cmntyList = gnMainService.getCmntyList(param);
		List<Map<String,Object>> getGrpGenderCnt = gnMainService.getGrpGenderCnt(param);
		
		
		model.addAttribute("cmntyList",cmntyList);
		model.addAttribute("getGrpGenderCnt",getGrpGenderCnt);
		return "web/gn/main";
	}
	
	/**
	 * 메인 데이터 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMainData.do")
	public @ResponseBody Map<String, Object> getMainData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		
		//그룹 현황
		Map<String, Object> grpJoinInfo = gnMainService.getGrpJoinInfo(param);		
		String grpJoinApprovalY =  String.valueOf(grpJoinInfo.get("GRP_MB_CNT_Y")); // 참여 승인 인원
		String grpJoinApprovalN =  String.valueOf(grpJoinInfo.get("GRP_MB_CNT_N")); // 누적 참여 요청 인원
		String todayGrpJoinApprovalN = String.valueOf(grpJoinInfo.get("TODAY_GRP_MB_CNT_N")); // 금일 참여 요청 인원		
		String usingGrpCnt = String.valueOf(grpJoinInfo.get("USING_GRP_CNT")); // 사용중인 그룹 개수
		
		System.out.println("### grpJoinApprovalY ===> " + grpJoinApprovalY);
		System.out.println("### grpJoinApprovalN ===> " + grpJoinApprovalN);
		System.out.println("### todayGrpJoinApprovalN ===> " + todayGrpJoinApprovalN);
		System.out.println("### usingGrpCnt ===> " + usingGrpCnt);
		
		rsMap.put("grpJoinApprovalY", grpJoinApprovalY);
		rsMap.put("grpJoinApprovalN", grpJoinApprovalN);
		rsMap.put("todayGrpJoinApprovalN", todayGrpJoinApprovalN);
		rsMap.put("usingGrpCnt", usingGrpCnt);
		
		//활동 현황		
		Map<String, Object> sportActivityInfo = gnMainService.getSportActivityInfo(param); // 스포츠 활동 인증
		Map<String, Object> selfMissionInfo = gnMainService.getSelfMissionInfo(param); // 셀프미션
		Map<String, Object> mealDiaryInfo = gnMainService.getMealDiaryInfo(param); // 식사일기
				
		String sportActivityCnt = String.valueOf(sportActivityInfo.get("SPORTACTIVITY_CNT")); // 스포츠 활동 인증 참여 인원
		String todaySportActivityCnt = String.valueOf(sportActivityInfo.get("TODAY_SPORTACTIVITY_CNT")); // 금일 스포츠 활동 인증 참여 인원
		String todaySelfMissionCnt = String.valueOf(selfMissionInfo.get("TODAY_SELFMISSION_CNT")); // 금일 셀프 미션 수행 인원
		String todayMealDiaryCnt = String.valueOf(mealDiaryInfo.get("TODAY_MEALDIARY_CNT")); // 금일 식사일기 작성 인원
				
		System.out.println("### sportActivityCnt ===> " + sportActivityCnt);
		System.out.println("### todaySportActivityCnt ===> " + todaySportActivityCnt);
		System.out.println("### todaySelfMissionCnt ===> " + todaySelfMissionCnt);
		System.out.println("### todayMealDiaryCnt ===> " + todayMealDiaryCnt);
		
		rsMap.put("sportActivityCnt",sportActivityCnt);
		rsMap.put("todaySportActivityCnt",todaySportActivityCnt);
		rsMap.put("todaySelfMissionCnt", todaySelfMissionCnt);
		rsMap.put("todayMealDiaryInfo", todayMealDiaryCnt);
		
		//걸음수 랭킹 현황
		List<Map<String,Object>> pointRankingList = gnMainService.getPointRankingSn(param);
		List<Map<String,Object>> getStepRankingList = gnMainService.getStepRankingList(param);
		
		
		rsMap.put("getStepRankingList",getStepRankingList);
		rsMap.put("pointRankingList",pointRankingList);
		
		
		
		return rsMap;
	}	

	
	@RequestMapping(value= "/getGrpGenderCnt.do", method= RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>getGrpGenderCnt(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String,Object>> getGrpGenderCnt = gnMainService.getGrpGenderCnt(param);					
		JSONArray array = JSONArray.fromObject(getGrpGenderCnt);		
		List<Map<String,Object>> result= new ArrayList<Map<String,Object>>();
		Map<String, Object> reMap = new HashMap<String, Object>();		
		
		System.out.println("#########################");
		System.out.println(getGrpGenderCnt);
		System.out.println(array);
		System.out.println("array.size ===> " + array.size());
		System.out.println("#########################");
		
		if(array.size() == 0) {
			reMap.put("MAN_RATE", "0");
			reMap.put("FEMALE_RATE", "0");			
		}else if(array.size() == 1) {
			if(array.getJSONObject(0).get("GENDER").equals("M")) {
				reMap.put("MAN_RATE", "100");
				reMap.put("FEMALE_RATE", "0");
			}else {
				reMap.put("MAN_RATE", "0");
				reMap.put("FEMALE_RATE", "100");
			}
		}else{
			reMap.put("MAN_RATE", array.getJSONObject(0).getString("GENDER_RATE"));
			reMap.put("FEMALE_RATE", array.getJSONObject(1).getString("GENDER_RATE"));
		}
				
		result.add(reMap);
		return result;
	}
	
	@RequestMapping(value= "/getAgeCnt.do", method= RequestMethod.POST )
	public List<Map<String,Object>> getAgeCnt(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String,Object>> getAgeCnt = gnMainService.getAgeCnt(param);
		return getAgeCnt;
	}
}

