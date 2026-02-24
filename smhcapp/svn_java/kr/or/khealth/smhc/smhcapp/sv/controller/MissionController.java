package kr.or.khealth.smhc.smhcapp.sv.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcapp.cm.service.SettingService;
import kr.or.khealth.smhc.smhcapp.sv.service.MissionService;


/**
 * @Class Name : MissionController.java
 * @Description : 미션 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2020.10.11		김태일	       최초생성
 *
 * @author  theJOIN
 * @since   2020.10.11
 * @version 1.0
 * @see
 * 
 */

@Controller
@RequestMapping(value = "/smhcapp/sv")
public class MissionController extends DMultiActionController {
	@Resource(name = "smhcapp.sv.MissionService")
	private MissionService missionService;
	
	@Resource(name="smhcapp.cm.SettingService")
	private SettingService settingService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 미션카드 조회
	 * @param USER_ID(필수), SVC_NO(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectTodayMissionList.do")
	public @ResponseBody Map<String, Object> selectTodayMissionList(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			//비 인가 아이디 접속시 디폴트 미션 부여
//			if(missionService.todaySuccYn(param)==null){
//				missionService.insertDefaultMissionSet(param);
//			}
			
			//가로 모드, 세로모드 확인
			//Map<String, Object> vrMap = settingService.selectVerticalModeYn(param); 		
			Map<String, Object> vrMap = settingService.selectUsersOption(param); 
			String verticalModeYn = String.valueOf(vrMap.get("VERTICAL_MODE_YN"));	//대시보드 기본형, 타일형 여부 
			String mealSeqUseYn = String.valueOf(vrMap.get("MEAL_SEQ_USE_YN")); 	//식사미션 카드 순차 나열 여부 
											
			param.put("VERTICAL_MODE_YN", verticalModeYn);
			param.put("MEAL_SEQ_USE_YN", mealSeqUseYn);
			rsMap.put("MEAL_SEQ_USE_YN", mealSeqUseYn);
						
			if(missionService.userHaveMissionSttus(param).equals("Y")){
				rsMap.put("USER_MISSION_STTUS", "Y");
				String todaySuccYn = missionService.todaySuccYn(param);
				if(todaySuccYn.equals("N")){//일반 미션 카드 불러오기	
					if(mealSeqUseYn.equals("Y")) {
						//식사미션 확인
						Map<String, Object> mealMap = missionService.selectMealMission(param);
						String mealDtls = String.valueOf(mealMap.get("MEAL_DTLS")); // 01, 02, 03
						param.put("MEAL_DTLS", mealDtls);
					}
					List<Map<String, Object>> missionList = missionService.selectMissionList(param);
					rsMap.put("TODAY_SUCC_YN", todaySuccYn);
					rsMap.put("MISSIONLIST", missionList);
				}else if(todaySuccYn.equals("Y")){// 미션 완료 화면 불러 오기
					List<Map<String, Object>> missionList = missionService.selectMissionList(param);
					rsMap.put("TODAY_SUCC_YN", todaySuccYn);
					rsMap.put("MISSIONLIST", missionList);
				}else{
					rsMap.put("USER_MISSION_STTUS", "N");
				}
			}else{
				rsMap.put("USER_MISSION_STTUS", "N");
			}
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
			System.out.println(e.toString());
		}
		return rsMap;
	}
	
	/**
	 * 미션수행 타임라인
	 * @param USER_ID(필수), SVC_NO(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectTodayMissionTimeline.do")
	public @ResponseBody Map<String, Object> selectTodayMissionTimeline(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {				
				//식사미션 카드 순차 나열 여부
				List<Map<String, Object>> missionList = missionService.selectTodayMissionTimeline(param);
				rsMap.put("TODAY_SUCC_YN", missionService.todaySuccYn(param));
				rsMap.put("MISSIONLIST", missionList);
				rsMap.put("USER_MISSION_STTUS", missionService.userHaveMissionSttus(param));
				//rsMap.put("USER_MISSION_STTUS", missionService.userHaveMissionSttus(param));
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
			System.out.println(e.toString());
		}
		return rsMap;
	}
	
	/**
	 * 클릭 이벤트 미션 관리 (2020.10.18 현재 복약 완료)
	 * @param USER_ID(필수), SVC_NO(필수), MISSION_CD(필수), MISSION_CD_DTLS(필수), DRUG_CD(복약미션 전용), DRUG_SN(복약미션 전용) 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/takeClickMission.do")
	public @ResponseBody Map<String, Object> takeClickMission(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			if(param.get("MISSION_SUCC_YN").equals("Y")){ //미션 성공
				missionService.insertClickMissionMngt(param);// 미션 수행 등록
				Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
				rsMap.put("MISSION_SUCC_YN", missionService.thisMissionCompleteYn(param));
				rsMap.put("MISSION_POP_INF", missionInf);
				if(rsMap.get("MISSION_SUCC_YN").equals("Y")){//금일 해당 미션 완료 여부 조회
					//자체미션 포인트 점수 유동적 변경으로 인한 포인트 획득 맥스 값 제거
					//if(missionService.selectGetMonthMissionPoint(param)>=155){// 포인트 미부여
					//	rsMap.put("POPUP_TYPE", "NOPOINT");
					//}else{	
						//포인트 들어갔는지 확인
						int pointCnt = 0;
						pointCnt = missionService.getMissionPoint(param);
						if(pointCnt == 0) {	
							param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
							missionService.insertMissionPoint(param);
							rsMap.put("POPUP_TYPE", "GOPOINT");
						}else {
							rsMap.put("POPUP_TYPE", "NOPOINT");
						}
					//}
					
				}else {//아직 완료 안됨 (시간대 별 미수행 미션이 남은 경우)
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}
				rsMap.put("chkYn", "Y");
			}else{// 미션 건너뜀 (포인트 없음)
				missionService.insertClickMissionMngt(param);
				Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
				rsMap.put("POPUP_TYPE", "NOPOINT");
				rsMap.put("MISSION_POP_INF", missionInf);
				rsMap.put("chkYn", "Y");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 물마시기 미션 관리 (2020.10.18 현재 변경안 진행 미정)
	 * @param USER_ID(필수), SVC_NO(필수), MISSION_CD(필수), MISSION_CD_DTLS(필수), WATER_CONT(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/takeWaterMission.do")
	public @ResponseBody Map<String, Object> takeWaterMission(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			int waterCount = Integer.valueOf(String.valueOf(param.get("WATER_CONT")));
			Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
			
			//물마시기 횟수가 8회 이상 insert되는 경우가 있어 이미 insert된 watercount 확인
			int savedWaterCount = missionService.getWaterCount(param);
			
			if(savedWaterCount < 8) {
				if(waterCount > 7){//물마시기 미션 완료 (카운트 및 미션 완료후 제공 정책에 따라 포인트 제공)
					param.put("MISSION_SUCC_YN", "Y");
					missionService.mergeWaterCount(param);
					if(missionService.thisMissionCompleteYn(param).equals("Y")){//금일 해당 미션 완료 여부 조회
							if(missionService.selectGetMonthMissionPoint(param)>=155){// 포인트 미부여
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}else{
								//포인트 들어갔는지 확인
								int pointCnt = 0;
								pointCnt = missionService.getMissionPoint(param);
								if(pointCnt == 0) {
									param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
									missionService.insertMissionPoint(param);
									rsMap.put("POPUP_TYPE", "GOPOINT");
								}else {
									rsMap.put("POPUP_TYPE", "NOPOINT");
								}
							}
					}else {//아직 완료 안됨 (시간대 별 미수행 미션이 남은 경우)
						rsMap.put("POPUP_TYPE", "NOPOINT");
					}
				}else if(waterCount < 8){// 물마시기 count 올리기 (포인트 없음)
					rsMap.put("POPUP_TYPE", "NOPOINT");
					param.put("MISSION_SUCC_YN", "N");
					missionService.mergeWaterCount(param);
				}			
			}
			
			rsMap.put("MISSION_SUCC_YN", missionService.thisMissionCompleteYn(param));
			rsMap.put("MISSION_POP_INF", missionInf);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}
	
	@RequestMapping(value = "/takeFluidMission.do")
	public @ResponseBody Map<String, Object> takeFluidMission(@ModelAttribute Map<String, Object> param){
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			int scsCount = Integer.valueOf(String.valueOf(param.get("SCS_CONT")));
			String resType = (String)param.get("RES_TYPE");
			Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
			String missionSuccYn = (String)param.get("MISSION_SUCC_YN");
			
			if(resType.equals("CLK")){
				if(scsCount == 0) missionSuccYn = "N";
				else missionSuccYn = "Y";
			}
			param.put("MISSION_SUCC_YN", missionSuccYn);
			missionService.mergeFluidCount(param);
			
			if(missionSuccYn == "N"){
				rsMap.put("POPUP_TYPE", "NOPOINT");
			}else{
				String thisMissionCompleteYn = missionService.thisMissionCompleteYn(param);
				String missionCompleteYn = missionService.missionCompleteYn(param);
				if(thisMissionCompleteYn.equals("Y") && missionCompleteYn.equals("Y")){
					if(missionService.selectGetMonthMissionPoint(param) < 155){
						int pointCnt = missionService.getMissionPoint(param);
						if(pointCnt == 0){
							param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
							missionService.insertMissionPoint(param);
							rsMap.put("POPUP_TYPE", "GOPOINT");
						}else rsMap.put("POPUP_TYPE", "NOPOINT");
					}else rsMap.put("POPUP_TYPE", "NOPOINT");
				}else rsMap.put("POPUP_TYPE", "NOPOINT");
			}
			rsMap.put("MISSION_POP_INF", missionInf);
			rsMap.put("chkYn", "Y");
		
		} catch (Exception e) {
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/*public Map<String, Object> setFluidMissionParam(String missionSuccYn, Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
			param.put("MISSION_SUCC_YN", missionSuccYn);
			missionService.mergeFluidCount(param);
			if(missionSuccYn == "N"){
				rsMap.put("POPUP_TYPE", "NOPOINT");
			}else{
				String thisMissionCompleteYn = missionService.thisMissionCompleteYn(param);
				String missionCompleteYn = missionService.missionCompleteYn(param);
				if(thisMissionCompleteYn.equals("Y") && missionCompleteYn.equals("Y")){
					if(missionService.selectGetMonthMissionPoint(param) < 155){
						int pointCnt = missionService.getMissionPoint(param);
						if(pointCnt == 0){
							param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
							missionService.insertMissionPoint(param);
							rsMap.put("POPUP_TYPE", "GOPOINT");
						}else rsMap.put("POPUP_TYPE", "NOPOINT");
					}else rsMap.put("POPUP_TYPE", "NOPOINT");
				}else rsMap.put("POPUP_TYPE", "NOPOINT");
			}
			rsMap.put("MISSION_POP_INF", missionInf);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}*/
	
	/**
	 * 활동량계 운동 시간에 따른 운동 미션 갱신 관리
	 * @param USER_ID(필수), SVC_NO(필수), MISSION_CD(필수 'M002 고정'), MISSION_CD_DTLS(필수 '01'고정) 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excMissionConduct.do")
	public @ResponseBody Map<String, Object> excMissionConduct(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
						
		try {
			//금일 운동 및 걸음시간 확인
			//걸음 미션 처리
			Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
			Map<String, Object> missionMap = missionService.actTimeMissionChk(param);						
			String missionChk = StringUtil.nvl(missionMap.get("ACT_TIME_MISSION_CHK_YN"));
			String totActTm = StringUtil.nvl(missionMap.get("TOT_ACT_TM"));
			String totActCnt = StringUtil.nvl(missionMap.get("TOT_ACT_CNT"));
			
			String missionCompYn = missionService.thisMissionCompleteYn(param);			
			
			if(missionChk.equals("NO_MISSION")){//미션 배정 받지 않음
				rsMap.put("POPUP_TYPE", "NOPOINT");
			}else if(missionChk.equals("NY_MISSION")){//미션을 수행한 상태가 아님
				rsMap.put("POPUP_TYPE", "NOPOINT");
			}else if(missionChk.equals("COMP_MISSION")){//미션 수행 조건이 충족됨
				// 활동 미션 완료 처리
				if(missionCompYn.equals("Y")){//이미 해당 미션의 완료처리가 됬을때					
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}else {//미션 완료 처리프로세스 수행
					int rsInt = 0;
					rsInt += missionService.insertExcMissionSucc(param);															
						if(missionService.selectGetMonthMissionPoint(param)>=155){// 포인트 미부여							
							rsMap.put("POPUP_TYPE", "NOPOINT");
						}else{				
							//포인트 들어갔는지 확인
							int pointCnt = 0;
							pointCnt = missionService.getMissionPoint(param);
							if(pointCnt == 0) {
								param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));						
								missionService.insertMissionPoint(param);
								rsMap.put("POPUP_TYPE", "GOPOINT");
							}else {								
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}
						}					
				}
			}else {
				rsMap.put("POPUP_TYPE", "NOPOINT");
			}
			rsMap.put("MISSION_SUCC_YN", missionCompYn);
			rsMap.put("MISSION_POP_INF", missionInf);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}
	
	/**
	 * 혈압계, 혈당계, 체성분계 측정 미션 관련 미션 처리 로직
	 * @param USER_ID(필수), SVC_NO(필수), MISSION_CD(필수), MISSION_CD_DTLS('01'고정) 
	 * @return .pic_choice_box a.camera_cancel{color: #fff;background: #FF0000;} .step_check_box div.done.device{background-color:#03369D;}
	 * @throws Exception 
	 */
	@RequestMapping(value = "/measureMissionConduct.do")
	public @ResponseBody Map<String, Object> measureMissionConduct(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String missionCd = String.valueOf(param.get("MISSION_CD"));			
		
		try {
			param.put("MISSION_CD_DTLS", "01");
			Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
			rsMap.put("MISSION_POP_INF", missionInf);
						
			if(missionCd.equals("W001")){
				String hasWeekMissionYn = missionService.hasWeekMissionYn(param);				
				if(hasWeekMissionYn.equals("PASS_WAIT_MISSION")){
					//체중 등록 했는지 한번 더 확인
					int hasWeightRegCnt = missionService.hasWeightRegCnt(param);
					if(hasWeightRegCnt > 0) {
						int rsInt = 0;
						rsInt += missionService.insertExcMissionSucc(param);															
							if(missionService.selectGetMonthMissionPoint(param)>=10){// 포인트 미부여
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}else{
								//포인트 들어갔는지 확인
								int pointCnt = 0;
								pointCnt = missionService.getMissionPoint(param);
								if(pointCnt == 0) {			
									param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
									missionService.insertMissionPoint(param);
									rsMap.put("POPUP_TYPE", "GOPOINT");
								}else {
									rsMap.put("POPUP_TYPE", "NOPOINT");
							}
						}
					}else {
						rsMap.put("POPUP_TYPE", "NOPOINT");
					}
				}else if(hasWeekMissionYn.equals("ALREADY_PASS_MISSION")){// 해당 측정 미션을 이미 완료 했을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}else if(hasWeekMissionYn.equals("NO_MISSION")){//해당 미션을 부여 받지 않았을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}
			}else if(missionCd.equals("M005")){
				String hasWeekMissionYn = missionService.hasWeekMissionYn(param);		
				if(hasWeekMissionYn.equals("PASS_WAIT_MISSION")){
					//혈당 등록 했는지 한번 더 확인
					if(missionService.bloodPressMissionAvailable(param).equals("PASS")){
						int rsInt = 0;
						//rsInt += missionService.insertExcMissionSucc(param);							
						if(missionService.selectGetMonthMissionPoint(param)>=25){// 포인트 미부여
							rsMap.put("POPUP_TYPE", "NOPOINT");
						}else{
							//포인트 들어갔는지 확인
							int pointCnt = 0;
							pointCnt = missionService.getMissionPoint(param);
							if(pointCnt == 0) {	
								param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
								//missionService.insertMissionPoint(param);
								rsMap.put("POPUP_TYPE", "GOPOINT");
							}else {
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}
						}
					}else {
						rsMap.put("POPUP_TYPE", "NOPOINT");
					}
				}else if(hasWeekMissionYn.equals("ALREADY_PASS_MISSION")){// 해당 측정 미션을 이미 완료 했을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}else if(hasWeekMissionYn.equals("NO_MISSION")){//해당 미션을 부여 받지 않았을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}	
			}else {
				String hasMeasureMissionYn = missionService.hasMeasureMissionYn(param);
				if(hasMeasureMissionYn.equals("PASS_WAIT_MISSION")){// 해당 측정 미션 완료처리가 필요 할때
					if(missionCd.equals("M008")){
						//미션 성공 들어갔는지 한번 더 확인
						int missionDupCnt = 0;
						missionDupCnt = missionService.getMissionDupCnt(param);						
						if(missionDupCnt == 0){
							int rsInt = 0;
							rsInt += missionService.insertExcMissionSucc(param);						
							if(missionService.selectGetMonthMissionPoint(param)>=62){// 포인트 미부여
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}else{
								//포인트 들어갔는지 확인
								int pointCnt = 0;
								pointCnt = missionService.getMissionPoint(param);
								if(pointCnt == 0) {	
									param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
									missionService.insertMissionPoint(param);
									rsMap.put("POPUP_TYPE", "GOPOINT");
								}else {
									rsMap.put("POPUP_TYPE", "NOPOINT");
								}
							}
						}
					}else if(missionCd.equals("M004")){
						rsMap.put("POPUP_TYPE", "NOPOINT");
					}else{
						int rsInt = 0;
						rsInt += missionService.insertExcMissionSucc(param);						
						if(missionService.selectGetMonthMissionPoint(param)>=155){// 포인트 미부여
							rsMap.put("POPUP_TYPE", "NOPOINT");
						}else{	
							//포인트 들어갔는지 확인
							int pointCnt = 0;
							pointCnt = missionService.getMissionPoint(param);
							if(pointCnt == 0) {	
								param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
								missionService.insertMissionPoint(param);
								rsMap.put("POPUP_TYPE", "GOPOINT");
							}else {
								rsMap.put("POPUP_TYPE", "NOPOINT");
							}
						}
					}
				}else if(hasMeasureMissionYn.equals("ALREADY_PASS_MISSION")){// 해당 측정 미션을 이미 완료 했을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}else if(hasMeasureMissionYn.equals("NO_MISSION")){//해당 미션을 부여 받지 않았을때
					rsMap.put("POPUP_TYPE", "NOPOINT");
				}
			}
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}		
		
		return rsMap;		
	}
	
	@RequestMapping(value = "/weekMissionUse.do")
	public @ResponseBody Map<String, Object> weekMissionUse(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsWeekMissionUse = missionService.weekMissionUse(param);
		
		rsMap.put("weekMissionUse", rsWeekMissionUse);
		
		return rsMap;
	}
	
	@RequestMapping(value = "/firstWeekReport.do")
	public @ResponseBody Map<String, Object> firstWeekReport(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsWeekAvgActCnt = missionService.weekAvgActCnt(param);
		//21.07.30 추가
		List<Map<String, Object>> rsActChck = missionService.weekActChck(param);
		
		Map<String, Object> rsWeekDrugCnt = missionService.weekDrugCnt(param);
		rsMap.put("weekActChck", rsActChck);
		rsMap.put("weekAvgActCnt", rsWeekAvgActCnt);
		rsMap.put("weekDrugCnt", rsWeekDrugCnt);
		
		return rsMap;
	}
	
	@RequestMapping(value = "/weekReport.do")
	public @ResponseBody Map<String, Object> weekReport(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, Object> rsWeekAvgBloodSugar = missionService.weekAvgBloodSugar(param);
		Map<String, Object> rsWeekMealCnt = missionService.weekMealCnt(param);
		Map<String, Object> rsWeekAvgBloodPress = missionService.weekAvgBloodPress(param);
		Map<String, Object> rsWeekWeight = missionService.weekWeight(param);
		List<Map<String, Object>> rsWeekWaterChck = missionService.weekWaterChck(param);
		List<Map<String, Object>> rsWeekOutChck = missionService.weekOutChck(param);
		List<Map<String, Object>> rsWeekActBandChck = missionService.weekActBandChck(param);
		
		//21.07.05 추가
		List<Map<String, Object>> rsWeekWeightChck = missionService.weekWeightChck(param);
		List<Map<String, Object>> rsWeekWeightList = missionService.weekWeightList(param);
		List<Map<String, Object>> rsWeekPressChck = missionService.weekPressChck(param);
		List<Map<String, Object>> rsWeekPressList = missionService.weekPressList(param);
		List<Map<String, Object>> rsWeekSugarBeforeMealChck = missionService.weekSugarBeforeMealChck(param);
		List<Map<String, Object>> rsWeekSugarBeforeMealList = missionService.weekSugarBeforeMealList(param);
		List<Map<String, Object>> rsWeekSugarAfterMealChck = missionService.weekSugarAfterMealChck(param);
		List<Map<String, Object>> rsWeekSugarAfterMealList = missionService.weekSugarAfterMealList(param);
				
		rsMap.put("weekAvgBloodSugar", rsWeekAvgBloodSugar);
		rsMap.put("weekMealCnt", rsWeekMealCnt);
		rsMap.put("weekAvgBloodPress", rsWeekAvgBloodPress);
		rsMap.put("weekWeight", rsWeekWeight);
		rsMap.put("weekWaterChck", rsWeekWaterChck);
		rsMap.put("weekOutChck", rsWeekOutChck);
		rsMap.put("weekActBandChck", rsWeekActBandChck);
		rsMap.put("weekWeightChck", rsWeekWeightChck);
		rsMap.put("weekWeightList", rsWeekWeightList);
		rsMap.put("weekPressChck", rsWeekPressChck);
		rsMap.put("weekPressList", rsWeekPressList);
		rsMap.put("weekSugarBeforeMealChck", rsWeekSugarBeforeMealChck);
		rsMap.put("weekSugarBeforeMealList", rsWeekSugarBeforeMealList);
		rsMap.put("weekSugarAfterMealChck", rsWeekSugarAfterMealChck);
		rsMap.put("weekSugarAfterMealList", rsWeekSugarAfterMealList);
		return rsMap;
	}
	
	
	@RequestMapping(value = "/monthReport.do")
	public @ResponseBody Map<String, Object> monthReport(@ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		//활동량		
		Map<String, Object> rsMonthAvgAct = missionService.monthAct(param);
		
		//혈당
		Map<String, Object> rsMonthAvgBloodSugar = missionService.monthBloodSugar(param);
		
		//혈압
		Map<String, Object> rsMonthAvgBloodPress = missionService.monthBloodPress(param);
		
		//체중
		Map<String, Object> rsMonthAvgWeight = missionService.monthWeight(param);
						
		rsMap.put("monthAvgAct", rsMonthAvgAct);
		rsMap.put("monthAvgBloodSugar", rsMonthAvgBloodSugar);
		rsMap.put("monthAvgBloodPress", rsMonthAvgBloodPress);
		rsMap.put("monthAvgWeight", rsMonthAvgWeight);
		return rsMap;
	}
	
	@RequestMapping(value = "/monthAvgReport.do")
	public @ResponseBody List<Map<String, Object>> monthAvgReport(@ModelAttribute Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsMap = new ArrayList<Map<String,Object>>();
		String mission_id = (String) param.get("MISSION_ID");
		
		if(mission_id.equals("1")) rsMap = missionService.monthAvgAct(param);
		else if(mission_id.equals("2")) rsMap = missionService.monthAvgBloodSugar(param);
		else if(mission_id.equals("3")) rsMap = missionService.monthAvgBloodPress(param);
		else if(mission_id.equals("4")) rsMap = missionService.monthAvgWeight(param);
	
		return rsMap;
	}
}
