package kr.go.mhc.mhcapp.sv.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.MyHealthMainDashService;
import kr.go.mhc.mhcapp.sv.service.RankService;


@Controller
public class RankController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.RankService")
	private RankService rankService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 랭킹 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankMain.do", method = RequestMethod.GET)
	public String rankMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/rank/ranking";
	}	
	
	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{			
			List<Map<String,String>> orgList = rankService.selectRankInfo(param); //보건소
			param.put("SCH_TYPE", "");
			List<Map<String,String>> rankList = rankService.selectRankInfo(param); //내점수
			
			rsMap_sb.put("orgList", orgList);
			rsMap_sb.put("rankList", rankList);
			
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 랭킹 내점수 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankMyScoreList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankMyScoreList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList1 = rankService.selectRankMyScoreList1(param);   
			List<Map<String,String>> rsList2 = rankService.selectRankMyScoreList2(param);   
			
			data.put("mission1", rsList1);
			data.put("mission2", rsList2);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		rsMap.put("rsList", data);
		return rsMap;
	}
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankDtlsList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		List<Map<String,String>> rsList = null;
		List<Map<String,String>> myList = null;
		List<Map<String,String>> myList2 = null;
		String chkYn = "N";
		
		try{
			if(param.containsKey("RANK_CATEGORY")){
				String category = param.get("RANK_CATEGORY").toString();
				if("1".equals(category)){
					param.put("SCH_TYPE", "ALL");
					rsList = rankService.selectRankDtlsList(param);   //전국 순위목록
				}else if("2".equals(category)){
					param.put("SCH_TYPE", "GENDER");
					rsList = rankService.selectRankDtlsList(param);   //성별 순위목록
				}else if("3".equals(category)){
					param.put("SCH_TYPE", "AGE");
					rsList = rankService.selectRankDtlsList(param);   //연령 순위목록
				}else if("4".equals(category)){
					param.put("SCH_TYPE", "ORG");
					rsList = rankService.selectRankDtlsList(param);   //보건소 순위목록
				}
			}
			myList = rankService.selectRankDtls(param); // 내순위
			rsMap_sb.put("rsList", rsList);
			rsMap_sb.put("myList", myList);
			chkYn = "Y";			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 포인트 이력 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/pointList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectPointList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList1 = rankService.selectRankList(param); //내점수
			List<Map<String,String>> rsList2 = rankService.selectPointList(param); //포인트 이력
			
			rsMap_sb.put("rankList", rsList1);
			rsMap_sb.put("pointList", rsList2);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 걸음수 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actRankList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> actRankList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{			
			List<Map<String,String>> rankList = rankService.selectActRankInfo(param); //전체
			rsMap_sb.put("rankList", rankList);
			
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 걸음수 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actRankDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> actRankDtlsList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		List<Map<String,String>> rsList = null;
		List<Map<String,String>> myList = null;
		String chkYn = "N";
		
		try{
			
			if(param.containsKey("RANK_CATEGORY")){ // 내순위
				String category = param.get("RANK_CATEGORY").toString();
				if("1".equals(category)){
					param.put("SCH_TYPE", "ALL");
					rsList = rankService.selectActRankList(param);   //전국 순위목록
				}else if("2".equals(category)){
					param.put("SCH_TYPE", "GENDER");
					rsList = rankService.selectActRankList(param);   //성별 순위목록
				}else if("3".equals(category)){
					param.put("SCH_TYPE", "AGE");
					rsList = rankService.selectActRankList(param);   //연령 순위목록
				}else if("4".equals(category)){
					param.put("SCH_TYPE", "ORG");
					rsList = rankService.selectActRankList(param);   //보건소 순위목록
				}
			}
			myList = rankService.selectActRankStatData(param);
			rsMap_sb.put("rsList", rsList);
			rsMap_sb.put("myList", myList);
			chkYn = "Y";			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 걸음수 랭킹 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actRankChartData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> actRankChartData(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		List<Map<String,String>> rsList = null;
		String chkYn = "N";
		
		try{
			rsList = rankService.selectActRankChartInfo(param);
			rsMap_sb.put("rsList", rsList);
			chkYn = "Y";			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 건강 포인트 획득 팝업 처리
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/realTimePointPop.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> realTimePointPop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		List<Map<String,String>> rsList = null;
		List<Map<String,String>> rsCount = null;
		System.out.println("파람 ="+param+"끝");
		String chkYn = "N";
		String msg = "";
		String missionScore = "";
		param.put("userId", model.get("SESS_USER_ID"));
		boolean insertFlag = false;
								
		try{
			String missionCd = (String) param.get("MISSION_CD");
			int regCnt = 0;
			int maxScore = 0;
			
			// 미션 별 최대 점수
			if("0027".equals(missionCd) || "0028".equals(missionCd) || "0029".equals(missionCd)
					|| "0031".equals(missionCd) || "0032".equals(missionCd) || "0033".equals(missionCd)){
				maxScore = 60;
			}else if("0030".equals(missionCd) || "0039".equals(missionCd)){
				maxScore = 155;
			}else if("0034".equals(missionCd)){
				maxScore = 5;
			}else if("0035".equals(missionCd)){
				maxScore = 10;
			}else if("0036".equals(missionCd)){
				maxScore = 15;
			}else if("0037".equals(missionCd) || "0038".equals(missionCd)){
				maxScore = 190;
			}else if("0040".equals(missionCd) || "0041".equals(missionCd)){
				maxScore = 4;
			}else if("0042".equals(missionCd)){
				maxScore = 40;
			}else if("0043".equals(missionCd) || "0044".equals(missionCd) || "0045".equals(missionCd)){
				maxScore = 30;
			}else if("0047".equals(missionCd)){
				maxScore = 62;
			}
			//2022년 랭킹 간소화 추가
			// 식사 사진 등록 : 영양 집중상담 기간 내 1회 가능(1회 1점), 집중상담이 월 2회 되는 경우가 있을 수 있어 2점으로 설정
			else if("0103".equals(missionCd)) { 
				maxScore = 2;
			// 영양,신체,월간리포트 확인 1회 5점 최대 월 2회
			}else if("0105".equals(missionCd) || "0106".equals(missionCd) || "0107".equals(missionCd)) { 
				maxScore = 10;
			// 활동량계 업데이트, 식생활 미션 실천여부, 식사일기, 운동일기, 게시판 댓글달기 (1일 1회 1점)
			}else if("0100".equals(missionCd) || "0101".equals(missionCd) || "0102".equals(missionCd) 
					|| "0104".equals(missionCd) || "0108".equals(missionCd)) { 
				//2022 랭킹 간소화 추가(maxScore : 월별 일수 최대)
				Calendar cal = new GregorianCalendar();
				maxScore = cal.getActualMaximum(Calendar.DAY_OF_MONTH);				
			}			
			param.put("MAX_SCORE", maxScore);
			
			// 랭킹 월 조회
			rsList = rankService.selectRankYm(param);
			param.putAll(rsList.get(0));
			
			// 랭킹 주 조회
			rsList = rankService.selectRankWeek(param);
			param.putAll(rsList.get(0));
			
			if("0034".equals(missionCd) || "0035".equals(missionCd) || "0036".equals(missionCd)
				// 2022 랭킹 간소화 추가 영양식사사진등록, 영양집중상담확인 ,신체집중상담 확인,월간리포트확인	
				|| "0103".equals(missionCd)|| "0105".equals(missionCd) || "0106".equals(missionCd) || "0107".equals(missionCd)){ 
				param.put("WEEK_NM", param.get("CNSL_NO"));
			}
			
			// 달성 여부 확인(활동량 30일 연속 업데이트, 식생활미션 30일 연속 확인 제외), 2022 랭킹 간소화 추가
			rsList = rankService.selectMissionChk(param);
			regCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("REG_CNT")));
			
			// 미션 달성 하지 않은 경우
			if(regCnt == 0 || "0042".equals(missionCd)){				
				// 미션 점수 조회
				rsList = rankService.selectMissionScore(param);
				param.putAll(rsList.get(0));
				
				// 랭킹 달성 점수 조회
				rsList = rankService.selectMissionAchvScore(param);
				param.putAll(rsList.get(0));
				
				int achvScore = Integer.parseInt(String.valueOf(param.get("ACHV_SCORE")));
				
				// 달성점수가 최대점수보다 작은 경우에만 처리
				if(achvScore < maxScore){
				
					// 활동량 주5일 이상 업데이트
					if("0027".equals(missionCd)){
						int measrDeCnt = 0;
						rsList = rankService.selectMission_00270028(param);
						
						if(rsList.size() > 0){
							measrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("MEASR_DE_CNT")));
							
							if(measrDeCnt >= 5){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					// 활동량 [가산] 주7일 연속 업데이트
					else if("0028".equals(missionCd)){
						int measrDeCnt = 0;
						rsList = rankService.selectMission_00270028(param);
						
						if(rsList.size() > 0){
							measrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("MEASR_DE_CNT")));
							
							if(measrDeCnt >= 7){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					// 활동량 [가산] 한달(30일) 연속 업데이트
					/*
					else if("0029".equals(missionCd)){
						int measrDeCnt = 0;
						rsList = rankService.selectMission_0029(param);
						
						if(rsList.size() > 0){
							measrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("MEASR_DE_CNT")));
							
							if(measrDeCnt >= 30){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					*/
					// 일일 목표걸음수 달성(활동량계 및 스마트폰 자체 걸음수 둘 중 하나라도 달성 시)
					else if("0030".equals(missionCd)){
						int totActCnt = 0;
						int walkCnt = 0;
						rsList = rankService.selectMission_0030(param);
						
						if(rsList.size() > 0){
							totActCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("TOT_ACT_CNT")));
							walkCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("WALK_CNT")));
							
							if(totActCnt >= walkCnt){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}						
					}
					// 일일 12000보 달성(추가점수)
					else if("0047".equals(missionCd)){
						int totActCnt = 0;
						rsList = rankService.selectMission_0030(param);
						
						if(rsList.size() > 0){
							totActCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("TOT_ACT_CNT")));
							
							if(totActCnt >= 12000){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}						
					}
					// 주간 식생활미션 주5일 이상 확인
					else if("0031".equals(missionCd)){
						int answrDeCnt = 0;
						rsList = rankService.selectMission_00310032(param);
						
						if(rsList.size() > 0){
							answrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("ANSWR_DE_CNT")));
							
							if(answrDeCnt >= 5){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					// 식생활 미션 [가산] 주7일 모두 확인
					else if("0032".equals(missionCd)){
						int answrDeCnt = 0;
						rsList = rankService.selectMission_00310032(param);
						
						if(rsList.size() > 0){
							answrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("ANSWR_DE_CNT")));
							
							if(answrDeCnt >= 7){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					// 식생활 미션 [가산] 한달(30일) 연속으로 확인
					/*
					else if("0033".equals(missionCd)){
						int answrDeCnt = 0;
						rsList = rankService.selectMission_0033(param);
						
						if(rsList.size() > 0){
							answrDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("ANSWR_DE_CNT")));
							
							if(answrDeCnt >= 30){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					*/
					// 영양 집중상담 내용확인, 집중상담 기간 내 식사사진등록, 신체활동 집중상담 내용확인, 식사기록 입력, 운동기록 입력, 설문
					else if("0034".equals(missionCd) || "0035".equals(missionCd) || "0036".equals(missionCd) || "0037".equals(missionCd) || "0039".equals(missionCd)
							|| "0043".equals(missionCd) || "0044".equals(missionCd) || "0045".equals(missionCd)){
						rankService.insertMissionAchvDta(param);
						insertFlag = true;
					}
					// 식사기록 [가산] 주7일 모두 확인
					else if("0038".equals(missionCd)){
						int mealDeCnt = 0;
						rsList = rankService.selectMission_0038(param);
						
						if(rsList.size() > 0){
							mealDeCnt = Integer.parseInt(String.valueOf(rsList.get(0).get("MEAL_DE_CNT")));
							
							if(mealDeCnt >= 7){
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					// 게시판 덧글달기(단, 게시물 당 1회)
					else if("0040".equals(missionCd)){
						rsList = rankService.selectMission_0040(param);
						
						if(rsList.size() > 0){
							param.put("CMMNT_SN", String.valueOf(rsList.get(0).get("CMMNT_SN")));
							rankService.insertMissionAchvDta(param);
							insertFlag = true;
						}
					}
					// 게시판 감정표현(단, 게시물 당 1회)
					else if("0041".equals(missionCd)){
						rsList = rankService.selectMission_0041(param);
						
						if(rsList.size() > 0){
							rankService.insertMissionAchvDta(param);
							insertFlag = true;
						}
					}
					// 콘텐츠 내용 SNS 공유하기
					else if("0042".equals(missionCd)){
						rsList = rankService.selectMission_0042(param);
						
						if(rsList.size() > 0){
							rankService.insertMissionAchvDta(param);
							insertFlag = true;
						}
					}
					/*
					 * 2022 랭킹 간소화 추가					
					 */
					// 활동량계 업데이트 시 점수 부여(1일 1회)
					else if("0100".equals(missionCd)) {
						rsList = rankService.selectMission_0100(param);
						rsCount = rankService.selectCompleteMission(param);
						if(rsList.size() > 0){
							if(rsCount.size() == 0) {
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					//식생활 실천 여부 확인(1일 1회)
					else if("0101".equals(missionCd)) {
						rsList = rankService.selectMission_0101(param);
						rsCount = rankService.selectCompleteMission(param);
						if(rsList.size() > 0){
							if(rsCount.size() == 0) {
							rankService.insertMissionAchvDta(param);
							insertFlag = true;
							}
						}
					}
					//식사 일기 등록, 집중상담 기간 내 식사사진등록, 운동 일기 등록, 영양 집중 상담 확인, 신체활동 집중 상담 확인, 월간 레포트 확인
					else if("0102".equals(missionCd) || "0103".equals(missionCd) || "0104".equals(missionCd)){
						rsCount = rankService.selectCompleteMission(param);
						if(rsCount.size() == 0) {
							rankService.insertMissionAchvDta(param);
							insertFlag = true;
						}
					}else if("0105".equals(missionCd) || "0106".equals(missionCd) || "0107".equals(missionCd)) {
						List<Map<String,String>> standardPointSts = null;
						rsCount = rankService.selectCompleteMission(param);
						standardPointSts = rankService.selectStandardPointSts(param);
						System.out.println("standardPointSts = "+standardPointSts );
						if(standardPointSts.size() > 1) {//10점
							if(rsCount.size() < 2) {
								System.out.println("222222222222");
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}else {
							if(rsCount.size() == 0) {
								System.out.println("1111111111");
								rankService.insertMissionAchvDta(param);
								insertFlag = true;
							}
						}
					}
					//게시판 댓글 달기(증진원 게시물만, 게시물당 1회, 1일 1회)
					else if("0108".equals(missionCd)) {
						if(param.get("CMNTY_CD").equals("K001001")){
							System.out.println("CMNTY_CD ===> " + param.get("CMNTY_CD"));
							// 오늘 다른 게시물에 댓글 단적 있는지 확인 > 1일 1회 기준 제거로 주석 처리
							//param.put("MODE", "1");							
							//rsList = rankService.selectMission_0108(param);							
							//if(rsList.size() == 0) {
								// 해당 게시물 이전 날짜에 댓글 단적 있는지 확인
								param.put("MODE", "2");	
								rsList = rankService.selectMission_0108(param);
								if(rsList.size() == 0) {
									// 해당 게시물 댓글 단것이 오늘 날짜인지 확인
									param.put("MODE", "3");
									rsList = rankService.selectMission_0108(param);
									rsCount = rankService.selectCompleteMission(param);
									if(rsList.size() > 0){
										// 맞을 경우 미션 성공
										if(rsCount.size() == 0) {
										rankService.insertMissionAchvDta(param);
										insertFlag = true;
										}
									}
								}
							//}
						}												
					}					
				}
			}
			rsMap_sb.put("missionCd", missionCd);
			if(insertFlag){
				msg = String.valueOf(param.get("MISSION_NM"));
				missionScore = String.valueOf(param.get("MISSION_SCORE"));
			}
			chkYn = "Y";			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap_sb.put("msg", msg);
		rsMap_sb.put("missionScore", missionScore);
		
		rsMap.put("rsList", rsMap_sb);
		rsMap.put("chkYn", chkYn);
		
		return rsMap;
	}
	
	
	/**
	 * 뱃지 획득 현황
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/badgeAchvSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> badgeAchvSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap     = new HashMap<String,Object>();
		Map<String,Object> badgeMap  = new HashMap<String,Object>();
		
		String chkYn = "N";
		
		try{			
			badgeMap  = rankService.selectBadgeAchvSttus(param); //전체
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("badgeMap", badgeMap);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 뱃지 획득 상세 목록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/badgeAchvDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> badgeAchvDtlsList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object>       rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rankList = new ArrayList<Map<String,Object>>();
		String chkYn = "N";
		
		try{			
			rankList = rankService.selectBadgeAchvDtlsList(param); 
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rankList", rankList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
}