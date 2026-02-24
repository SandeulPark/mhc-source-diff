package kr.or.khealth.smhc.smhcapp.ms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcapp.ms.service.MeasrMastrService;
import kr.or.khealth.smhc.smhcapp.sv.service.impl.MissionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ms.measrMastrService")
public class MeasrMastrServiceImpl extends EgovAbstractServiceImpl implements MeasrMastrService{
	
	protected Logger LOG = LoggerFactory.getLogger(MeasrMastrServiceImpl.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Resource(name="ms.measrMastrDAO")
    private MeasrMastrDAO measrMastrDAO;
	
	@Resource(name="smhcapp.sv.MissionDAO")
	private MissionDAO missionDAO;
	

	@Override
	public Map<String,Object> getMeasrSeq() throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.getMeasrSeq();
	}
	public int insertActivityLink(List<Map<String, Object>> datas) throws Exception {
		int nCnt = 0;
		try{
			Map<String,Object> param = getMeasrSeq();
				Iterator<Map<String,Object>> iter = datas.iterator();
				String modelNm = null; 
				
				while (iter.hasNext()) {
					param.putAll(iter.next());			
					param.put("SESS_USER_ID", param.get("USER_ID"));
					
					if(param.get("AUTO_MANU_CLF") == null){
						param.put("AUTO_MANU_CLF", "A");
					}
					
					//param.put("AUTO_MANU_CLF", "A");
					param.put("MEASR_TRGT_CLF", "10");
					param.put("MEASR_RSLT", "S");
					param.put("MEASR_MODEL_NM", param.get("MEASR_MODEL_NM")!=null?param.get("MEASR_MODEL_NM").toString():param.get("DEVICE_NM").toString());
					param.put("MEASR_BROADCASTID", param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null);
					modelNm = param.get("DEVICE_NM")!=null?param.get("DEVICE_NM").toString():null;
					
					if(nCnt==0){
						measrMastrDAO.insertMeasrMas(param);
					}
					String tableNm = param.get("tableNm")!=null?param.get("tableNm").toString():null;
					if(tableNm!=null){
						
						// 오류 데이터 걸러내기
						Map<String, Object> validRslt = validateData(param);
						Boolean valid = (Boolean) validRslt.get("valid");
						
						if(valid) {						
							if("TN_MS_ACT".equals(tableNm)){							
								measrMastrDAO.insertAct(param);
							}else if("TN_MS_HEART_RATE_ARR".equals(tableNm)){
								measrMastrDAO.insertHeartRateArr(param);
							}else if("TN_MS_RUNNING_STATUS".equals(tableNm)){
								measrMastrDAO.insertRunningStatus(param);
							}else if("TN_MS_RUNNING_HR_ARR".equals(tableNm)){
								measrMastrDAO.insertRunningHrArr(param);
							}
						}
					}
					nCnt++;
				}
				
				
				//프로시저 테스트
				//System.out.println("프로시저로 넘어갈 파라미터 !!! "+nCnt+"  "+param.toString());
				if(nCnt>0){
					//보정쿼리 삽입 Q8
					System.out.println("modelNm="+modelNm);
					if(modelNm!=null&&("InBodyBand2".equals(modelNm)||"InLabWATCH".equals(modelNm))){
						measrMastrDAO.updateMeasrTotalCnt(param);
					}
					
					measrMastrDAO.callProcActDataNew(param);
				}
				
		}catch(Exception e){
			e.printStackTrace();
			nCnt = -1;
		}
		return nCnt; 	
	}
	
	@Override
	public int insertBloodPressLink(List<Map<String, Object>> datas) throws Exception {
		// TODO Auto-generated method stub
		
		Date time = new Date();
		String time1 = dateFormat.format(time);	
		
		StringBuffer str = new StringBuffer();
		
		str.append("### insertBloodPressLink [ " + time1 + " ]");
		
		int nCnt = 0;
		int insertCnt = 0;
		Map<String,Object> param = getMeasrSeq();
		try{

				Iterator<Map<String,Object>> iter = datas.iterator();
				String equipCd = null; 
				
				while (iter.hasNext()) {
					param.putAll(iter.next());			
					param.put("SESS_USER_ID", param.get("USER_ID"));
					equipCd = param.get("DEVICE_NM")!=null?param.get("DEVICE_NM").toString():null;
					
					param.put("AUTO_MANU_CLF", "A");
					param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30  
					param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
					param.put("MEASR_MODEL_NM", param.get("MEASR_MODEL_NM")!=null?param.get("MEASR_MODEL_NM").toString():param.get("DEVICE_NM").toString());
					param.put("MEASR_BROADCASTID", param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null);
					
					str.append(" / USER_ID : " + param.get("USER_ID"));
					String tableNm = param.get("tableNm")!=null?param.get("tableNm").toString():null;
					if(tableNm!=null){
						//todo : 판정값 가져오는 로직 추가 해야됨
						int dupleChk = measrMastrDAO.duplChkBloodPress(param);
						if(dupleChk == 0){
							if(nCnt==0){
								measrMastrDAO.insertMeasrMas(param);
								nCnt++;
							}							
							measrMastrDAO.insertBloodPress(param);
							//callBack  ==> Like measureMissionConduct.do 
							if(insertCnt==0&&param.get("MEASR_DE").toString().equals(getToDay())){
								str.append(" / BLOOD_PRESS_MISSION_PROCESS_START");
								int rsInt = 0;
								Map<String, Object> pointInfMap = new HashMap<String,Object>();
								param.put("MISSION_CD_DTLS", "01");
								param.put("USER_ID", param.get("SESS_USER_ID"));
								param.put("MISSION_CD", "M004");
								param.put("SVC_NO", missionDAO.getSvcNo(param));
								String hasMeasureMissionYn = missionDAO.hasMeasureMissionYn(param);
								str.append(" / MISSION_YN : " + hasMeasureMissionYn);
								if(hasMeasureMissionYn.equals("PASS_WAIT_MISSION")){
									str.append(" / INSERT MISSION SUCCESS");
									rsInt += missionDAO.insertExcMissionSucc(param);
									pointInfMap = missionDAO.selectMissionPopInf(param);
									if(missionDAO.selectGetMonthMissionPoint(param)<=155){
										str.append(" / MISSION_POINT <= 155 / GOPOINT!");
										param.put("OBTAIN_POINT", pointInfMap.get("ALWNC_POINT"));
										rsInt += missionDAO.insertMissionPoint(param);
										
									}
								}
								
								insertCnt++;
							}
						
						}
					}
				}
				
		}catch(Exception e){
			e.printStackTrace();
			nCnt = -1;
		}
		System.out.println(str);
		return nCnt;
	}
	
	@Override
	public int insertBloodSugarLink(List<Map<String, Object>> datas) throws Exception {
		
		Date time = new Date();
		String time1 = dateFormat.format(time);	
		
		StringBuffer str = new StringBuffer();	
		
		str.append("### insertBloodSugarLink [ " + time1 + " ]");
		
		int nCnt = 0;
		int insertCnt = 0;
		try{
			Map<String,Object> param = getMeasrSeq();
				Iterator<Map<String,Object>> iter = datas.iterator();
				String equipCd = null; 
				
				str.append(" / USER_ID : " + param.get("USER_ID"));
								
				while (iter.hasNext()) {					
					param.putAll(iter.next());			
					param.put("SESS_USER_ID", param.get("USER_ID"));
					equipCd = param.get("DEVICE_NM")!=null?param.get("DEVICE_NM").toString():null;
					
					param.put("AUTO_MANU_CLF", "A");
					param.put("MEASR_TRGT_CLF", "40"); 	//측정대상구분_혈당 : MS001_40  
					param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
					param.put("MEASR_MODEL_NM", param.get("MEASR_MODEL_NM")!=null?param.get("MEASR_MODEL_NM").toString():param.get("DEVICE_NM").toString());
					param.put("MEASR_BROADCASTID", param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null);
					
										
					String tableNm = param.get("tableNm")!=null?param.get("tableNm").toString():null;
					if(tableNm!=null){
						//todo : 판정값 가져오는 로직 추가 해야됨
						int dupleChk = measrMastrDAO.duplChkBloodSugar(param);
						if(dupleChk == 0){
							if(nCnt==0){
								measrMastrDAO.insertMeasrMas(param);
								nCnt++;
							}	
							measrMastrDAO.insertBloodSugar(param);							
							//callBack  ==> Like measureMissionConduct.do 
							if(insertCnt==0&&param.get("MEASR_DE").toString().equals(getToDay())){
								str.append(" / BLOOD_SUGAR_MISSION_PROCESS_START");
								int rsInt = 0;
								Map<String, Object> pointInfMap = new HashMap<String,Object>();
								param.put("MISSION_CD_DTLS", "01");
								param.put("USER_ID", param.get("SESS_USER_ID"));
								param.put("MISSION_CD", "M005");
								param.put("SVC_NO", missionDAO.getSvcNo(param));
								if(missionDAO.hasWeekMissionYn(param).equals("PASS_WAIT_MISSION")) {
									str.append(" / INSERT MISSION SUCCESS");
									rsInt += missionDAO.insertExcMissionSucc(param);
									pointInfMap = missionDAO.selectMissionPopInf(param);
									if(missionDAO.selectGetMonthMissionPoint(param)<=21){
										str.append(" / MISSION_POINT <= 21 / GOPOINT!");
										param.put("OBTAIN_POINT", pointInfMap.get("ALWNC_POINT"));
										rsInt += missionDAO.insertMissionPoint(param);
										
									}
								}
								
								insertCnt++;
							}
						}
					}
				}
				
		}catch(Exception e){
			e.printStackTrace();
			nCnt = -1;
		}
		System.out.println(str);
		return nCnt;
	}
	
	
	
	@Override
	public int insertBodyCompLink(List<Map<String, Object>> datas) throws Exception {
		
		Date time = new Date();
		String time1 = dateFormat.format(time);	
		
		StringBuffer str = new StringBuffer();	
		
		str.append("### insertBodyCompLink [ " + time1 + " ]");
		int nCnt = 0;
		int insertCnt = 0;
		try{
			Map<String,Object> param = getMeasrSeq();
				Iterator<Map<String,Object>> iter = datas.iterator();
				String equipCd = null; 
				
				while (iter.hasNext()) {
					param.putAll(iter.next());			
					param.put("SESS_USER_ID", param.get("USER_ID"));
					equipCd = param.get("DEVICE_NM")!=null?param.get("DEVICE_NM").toString():null;
					
					param.put("MEASR_TRGT_CLF", "20"); 	//측정대상구분_체성분 : MS001_20    
					param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
					String measrModelNm = param.get("MEASR_MODEL_NM")!=null?param.get("MEASR_MODEL_NM").toString():param.get("DEVICE_NM").toString();	
					String measrBroadCastId = param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null;
					param.put("MEASR_MODEL_NM", measrModelNm);
					param.put("MEASR_BROADCASTID", param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null);					
										
					//삼성헬스로 들어올 경우 AUTO_MANU_CLF 파라미터 받아서 전달
					if("SELFMEASR".equals(measrModelNm) && "S".equals(measrBroadCastId)) {
						param.put("AUTO_MANU_CLF", param.get("AUTO_MANU_CLF")!=null?param.get("AUTO_MANU_CLF").toString():"A");						
					}else {
						param.put("AUTO_MANU_CLF", "A");
					}
					
					str.append(" / USER_ID : " + param.get("USER_ID") + " / MODEL_NM : " + measrModelNm);
					//인바디에서 체성분 들어오는 부분은 막음.
					if(!measrModelNm.equals("InBodyBand2")){					
						if(nCnt==0){						
							measrMastrDAO.insertMeasrMas(param);
						}						
						String tableNm = param.get("tableNm")!=null?param.get("tableNm").toString():null;
						if(tableNm!=null){
							//todo : 판정값 가져오는 로직 추가 해야됨
							int hasWeightRegCnt = missionDAO.hasWeightRegCnt(param);
							if(hasWeightRegCnt < 1) measrMastrDAO.insertBodyComp(param);
						}
						nCnt++;						
						//callBack  ==> Like measureMissionConduct.do 
						if(insertCnt==0){
							str.append(" / BODY_COMP_MISSION_PROCESS_START");
							int rsInt = 0;
							Map<String, Object> pointInfMap = new HashMap<String,Object>();
							param.put("MISSION_CD_DTLS", "01");
							param.put("USER_ID", param.get("SESS_USER_ID"));
							param.put("MISSION_CD", "W001");
							param.put("SVC_NO", missionDAO.getSvcNo(param));
							String hasWeekMissionYn = missionDAO.hasWeekMissionYn(param);
							str.append(" / MISSION_YN : " + hasWeekMissionYn);
							if(hasWeekMissionYn.equals("PASS_WAIT_MISSION")){
								str.append(" / INSERT MISSION SUCCESS");
								rsInt += missionDAO.insertExcMissionSucc(param);
								pointInfMap = missionDAO.selectMissionPopInf(param);
								if(missionDAO.selectGetMonthMissionPoint(param)<10){
									str.append(" / MISSION_POINT < 10 / GOPOINT!");
									param.put("OBTAIN_POINT", pointInfMap.get("ALWNC_POINT"));
									rsInt += missionDAO.insertMissionPoint(param);
								}
							}
							insertCnt++;
						}
						
					}
				}
				
		}catch(Exception e){
			e.printStackTrace();
			nCnt = -1;
		}		
		System.out.println(str);		
		return nCnt;
	}
	
	@Override
	public int insertSleepLink(List<Map<String, Object>> datas) throws Exception {
		// TODO Auto-generated method stub
		Date time = new Date();
		String time1 = dateFormat.format(time);	
		
		StringBuffer str = new StringBuffer();	
		
		str.append("### insertSleepLink [ " + time1 + " ]");
		int nCnt = 0;
		int insertCnt = 0;
		try{
			Map<String,Object> param = getMeasrSeq();
				Iterator<Map<String,Object>> iter = datas.iterator();
				String equipCd = null; 
				
				while (iter.hasNext()) {
					param.putAll(iter.next());			
					param.put("SESS_USER_ID", param.get("USER_ID"));
					equipCd = param.get("DEVICE_NM")!=null?param.get("DEVICE_NM").toString():null;
					
					param.put("AUTO_MANU_CLF", "A");
					param.put("MEASR_TRGT_CLF", "50"); 	//측정대상구분_수면 : MS001_50  
					param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
					String measrModelNm = param.get("MEASR_MODEL_NM")!=null?param.get("MEASR_MODEL_NM").toString():param.get("DEVICE_NM").toString();	
					String measrBroadCastId = param.get("BROADCAST_ID")!=null?param.get("BROADCAST_ID").toString():null;
					param.put("MEASR_MODEL_NM", measrModelNm);
					param.put("MEASR_BROADCASTID", measrBroadCastId);					
															
					str.append(" / USER_ID : " + param.get("USER_ID") + " / MODEL_NM : " + measrModelNm);
										
					if(nCnt==0){						
						measrMastrDAO.insertMeasrMas(param);
					}						
					String tableNm = param.get("tableNm")!=null?param.get("tableNm").toString():null;
					if(tableNm!=null){		
						measrMastrDAO.insertSleep(param);
					}
					nCnt++;	
				}
				
		}catch(Exception e){
			e.printStackTrace();
			nCnt = -1;
		}		
		System.out.println(str);		
		return nCnt;
	}
	
	
	
	@Override
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("insertBloodPress ::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		if(param.get("insList") != null){
			int rtInt = 0;
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				int dupleChk = measrMastrDAO.duplChkBloodPress(insMap);
				if(dupleChk==0){
					insMap.put("JUDGE_VAL", insMap.get("BLOOD_PRESS_MAX"));
					Map<String,Object> measrJudge = selectMeasrJudge(insMap);
					
					if(measrJudge!=null&&measrJudge.size()>0)
						insMap.putAll(measrJudge);	

					if (Long.parseLong(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")))
							> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
						rtnMap.putAll(insMap);
					}

					insMap.putAll(getMeasrSeq());
					measrMastrDAO.insertMeasrMas(insMap);
					measrMastrDAO.insertBloodPress(insMap);
					rtInt++;
				}
			}
			rtnMap.put("insertCnt", rtInt);
			return rtnMap;
		} else {
			param.put("USER_ID", param.get("SESS_USER_ID"));
			int dupleChk = measrMastrDAO.duplChkBloodPress(param);
			if(dupleChk==0){
				param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
				Map<String,Object> measrJudge = selectMeasrJudge(param);
				
				if(measrJudge!=null&&measrJudge.size()>0)
					param.putAll(measrJudge);	

				param.putAll(getMeasrSeq()); 
				measrMastrDAO.insertMeasrMas(param);
				measrMastrDAO.insertBloodPress(param);
			}
			return param;
		}
	}
	
	@Override
	public Map<String,Object> updateBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		
		param.put("USER_ID", param.get("SESS_USER_ID"));
		rtInt = measrMastrDAO.updateMeasrMas(param);
		param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
		Map measrJudge = selectMeasrJudge(param);
		
		if(measrJudge!=null&&measrJudge.size()>0)
			param.putAll(measrJudge);	
		
		rtInt = measrMastrDAO.updateBloodPress(param);	
		
		return param;
	}
	
	@Override
	public Map<String,Object> deleteBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = measrMastrDAO.deleteBloodPress(param);	
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		
		return param;
	}
	
	@Override
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		if(param.get("insList") != null){
			int rtInt = 0;
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				int dupleChk = measrMastrDAO.duplChkBloodSugar(insMap);
				if(dupleChk==0){
					insMap.put("JUDGE_VAL", insMap.get("BLOOD_SUGAR"));
					Map<String,Object> measrJudge = selectMeasrJudge(insMap);
					if(measrJudge!=null&&measrJudge.size()>0)
						insMap.putAll(measrJudge);	
					
					if (Long.parseLong(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")))
							> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
						rtnMap.putAll(insMap);
					}

					insMap.putAll(getMeasrSeq());
					measrMastrDAO.insertMeasrMas(insMap);
					measrMastrDAO.insertBloodSugar(insMap);
					rtInt++;
				}
			}
			rtnMap.put("insertCnt", rtInt);
			return rtnMap; 
		} else {
			if(param.get("arr_MEASR_DE")!=null){
				String[] arr_measrDe = (String[]) param.get("arr_MEASR_DE");
				String[] arr_measrTm = (String[]) param.get("arr_MEASR_TM");
				String[] arr_bloodSugar = (String[]) param.get("arr_BLOOD_SUGAR");
				String[] arr_meal = (String[]) param.get("arr_MEAL_CLF");
				
				param.put("USER_ID", param.get("SESS_USER_ID"));
				if(arr_measrDe.length == arr_bloodSugar.length){
					for(int i=0; i<arr_measrDe.length; i++){					
						
						param.put("MEASR_DE", arr_measrDe[i]);
						param.put("MEASR_TM", arr_measrTm[i]);
						param.put("BLOOD_SUGAR", arr_bloodSugar[i]);
						param.put("MEAL_CLF", arr_meal[i]);
						
						int dupleChk = measrMastrDAO.duplChkBloodSugar(param);
						if(dupleChk==0){
							param.put("JUDGE_VAL", param.get("BLOOD_SUGAR"));
							Map<String,Object> measrJudge = selectMeasrJudge(param);
							if(measrJudge!=null&&measrJudge.size()>0)
								param.putAll(measrJudge);		

							if (Long.parseLong(StringUtil.nvl(param.get("MEASR_DE")) + StringUtil.nvl(param.get("MEASR_TM")))
									> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
								rtnMap.putAll(param);
							}

							param.putAll(getMeasrSeq());
							measrMastrDAO.insertMeasrMas(param);
							measrMastrDAO.insertBloodSugar(param);
						}

						System.out.println("blood sugar ==="+param);
					}
				}
			}
			return param; 
		}
	}
	
	@Override
	public Map<String,Object> updateBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		
		Map measrJudge = selectMeasrJudge(param);
		
		if(measrJudge!=null&&measrJudge.size()>0)
			param.putAll(measrJudge);	
		
		rtInt = measrMastrDAO.updateBloodSugar(param);	
		
		return param;
	}
	
	@Override
	public Map<String,Object> deleteBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = measrMastrDAO.deleteBloodSugar(param);	
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		
		return param;
	}
	
	@Override
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map rtMap = new HashMap<String, Object>();
		if(param.get("JUDGE_VAL")!=null&&!"".equals(param.get("JUDGE_VAL"))){ 
			rtMap = measrMastrDAO.selectMeasrJudge(param);
		}
		return rtMap;
	}
	

	@Override
	public int insertActDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertActDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertActDta(param);
		}
		return nCnt;
	}
	

	@Override
	public int insertHRArrDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = insMap.get("HEARTRATES").toString();
				insMap.put("HEARTRATES", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertHRArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertHRArrDta(param);
		}
		return nCnt;
	}
	
	@Override
	public int insertHRDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertHRDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertHRDta(param);
		}
		return nCnt;
	}
	
	//boram s	
	
	public int insertRunningStatusDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String stateList = String.valueOf(insMap.get("STATELIST"));
				insMap.put("STATELIST", stateList.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningStatusDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningStatusDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningHRArrDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEARTRATES"));
				insMap.put("HEARTRATES", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningHRArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRArrDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningHRDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningHRDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRDta(param);
		}
		return nCnt;
	}
	
	public int insertRunningCalorieArrDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String calories = String.valueOf(insMap.get("CALORIES"));
				insMap.put("CALORIES", calories.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorieArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRArrDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningCalorieDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorieDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningCalorieDta(param);
		}
		return nCnt;	  	  
	}
	//boram e
	

	@Override
	public int insertBodyCompDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub	
		
		
		return measrMastrDAO.insertBodyCompDta(param);
	}
	
	@Override
	public int insertBodyComp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		param.put("USER_ID", param.get("SESS_USER_ID"));
		param.putAll(getMeasrSeq());
		measrMastrDAO.insertMeasrMas(param);
//		rtInt = measrMastrDAO.insertBodyCompDta(param);
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertBodyComp(insMap);
				nCnt++;
			}
		}else{
			nCnt = 1;
			measrMastrDAO.insertBodyComp(param);
		}
		return nCnt;
	}
	
	@Override
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = -1;
		

			rtInt = measrMastrDAO.updatePairDeviceInfo(param);
		
		if(!param.get("EQUIP_CD").equals("Gmate") && !param.get("EQUIP_CD").equals("")){
			rtInt = measrMastrDAO.mergeEquipInfo(param);			
		}
		
		return rtInt;
	}
	
	@Override
	public String callProcActIns(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		return measrMastrDAO.callProcActIns(param);
	}
	
	public int mergeEquipInfo(Map<String, Object> param) throws Exception {
		
		return measrMastrDAO.mergeEquipInfo(param);
		
	}
	
	public Map<String,Object> searchSerialNo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.searchSerialNo(param);
	}
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> rsMap = new HashMap<String,Object>();
			rsMap = measrMastrDAO.deviceUserInfo(param);
		return rsMap;
	}
	
	public List<Map<String,Object>> searchSerialNoList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.searchSerialNoList(param);
	}
	

	@Override
	public Map<String,Object> insertBodyCompManu_old(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = 0;
		try{
			String MEASR_SN = param.get("MEASR_SN")==null?"":(String)param.get("MEASR_SN");
			//신장정보
			Map<String, Object> hMap = measrMastrDAO.selectHeightInfo(param);
			String height = hMap.get("HEIGHT")==null?"":hMap.get("HEIGHT").toString();
			if(!"".equals(height)){
				param.put("USER_ID", param.get("SESS_USER_ID"));
				param.put("HEIGHT", height);
				param.put("MEASR_TRGT_CLF", "20");
				param.put("MEASR_RSLT", "S");
				param.put("AUTO_MANU_CLF", "M");
				System.out.println("service param>>"+param);
				//신규
				if("".equals(MEASR_SN)){
					param.putAll(getMeasrSeq());
					rtInt = measrMastrDAO.insertMeasrMas(param);
				}else{
					System.out.println("update");
					rtInt = measrMastrDAO.updateMeasrMas(param);
				}
				rtInt = measrMastrDAO.insertBodyCompManu(param);
				param.put("rtInt", rtInt);
			}
		}catch(Exception e){
			LOG.debug(e.toString());
		}
		return param;
	}	
	
	@Override
	public Map<String, Object> deleteBodyCompManu(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		int rtInt = 0;
		
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		rtInt = measrMastrDAO.deleteBodycomp(param);
		param.put("rtInt", rtInt);
		return param;
	}
	
	

	/********************************** OpenApi 적용 관련 START ***************************************/
	@Override
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.checkLastData(param);
	}

	@Override
	public int insertAct(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");

			Map<String, Object> subParam = new HashMap<String,Object>();
			subParam.put("USER_ID", insList.get(0).get("USER_ID"));
			Map<String, Object> putParam = measrMastrDAO.checkLastData(subParam);
			if(putParam != null){
				subParam.putAll(putParam);
			}
			String measrSn = StringUtil.nvl(subParam.get("MEASR_SN"));
			String startDt = StringUtil.nvl(subParam.get("START_DT"));
			if(!"".equals(startDt)){
				startDt = startDt.substring(0, 10);
			}
			int actCnt = Integer.parseInt(StringUtil.nvl(subParam.get("TOT_ACT_CNT"),"0"));
			
			Map<String,Object> insMap =null;
			for (int i = 0; i < insList.size(); i++) {
				insMap = insList.get(i);
				insMap.putAll(param);
				
//				System.out.println("insMap.get(MEASR_DE) : "+insMap.get("MEASR_DE")+", insMap.get(MEASR_TM) : "+insMap.get("MEASR_TM")+", last subParam.get(MEASR_TM) : "+subParam.get("MEASR_TM")+", startDt : "+startDt
//						+"\nlast tot_act_cnt : "+actCnt+", insMap.get(TOT_ACT_CNT) : "+insMap.get("TOT_ACT_CNT"));
//				if ( !"".equals(measrSn) && !"".equals(startDt) ) {
////					if (Integer.parseInt(startDt) > Integer.parseInt(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")).substring(0, 2))) {
////						continue;
////					}
//					System.out.println("no continue!!!!!!!!");
//					//저장되어있는 최종 측정일시와 수신받은 데이터 비교하여 시간까지 같으면
//					if ( startDt.equals(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")).substring(0, 2)) ) {
//						//저장되어 있는 최종 측정시간이 5959가 아니면
//						if ( !(StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("2959")) && !(StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("5959")) ) {
//							//저장되어 있는 총보수와 측정시간이 수신받은 총보수와 측정시간과 비교하여 수신받은 데이터가 높으면 기존 데이터 삭제 후 신규 추가
//							if( (actCnt < Integer.parseInt(StringUtil.nvl(insMap.get("TOT_ACT_CNT"),"0")) 
//									&& (Integer.parseInt(StringUtil.nvl(subParam.get("MEASR_TM"),"1")) < Integer.parseInt(StringUtil.nvl(insMap.get("MEASR_TM"),"0")))) ) {
//								measrMastrDAO.deleteAct(subParam);
//								insMap.put("MEASR_SN", subParam.get("MEASR_SN"));
//								System.out.println("delete act!!!!!!!!!!!!!");
//							}
//						}
//						if ( StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("5959") && StringUtil.nvl(insMap.get("MEASR_TM")).endsWith("5959")) {
//							if (actCnt < Integer.parseInt(StringUtil.nvl(insMap.get("TOT_ACT_CNT"),"0"))) {
//								measrMastrDAO.deleteAct(subParam);
//								insMap.put("MEASR_SN", subParam.get("MEASR_SN"));
//								System.out.println("5959 delete act!!!!!!!!!!!!!");
//							}
//						}
//					}
//				}
				if ("".equals(StringUtil.nvl(insMap.get("MEASR_SN"),""))) {
					param.putAll(getMeasrSeq());
					insMap.put("MEASR_SN", param.get("MEASR_SN"));
					measrMastrDAO.insertMeasrMas(insMap);
				}
//				System.out.println("insert act!!!!!!!!!  insMap == "+insMap);
				measrMastrDAO.insertAct(insMap);
//				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")){
//					measrMastrDAO.callProcInbodyActData(insMap);
//				}
			}
			if(insMap!=null){
				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")||(insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH")&&!insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH2020"))){
					//measrMastrDAO.callProcInbodyActData(insMap);
					measrMastrDAO.updateInbodyTotalCnt(insMap);
				}
//				else if(insMap.get("MEASR_MODEL_NM").toString().contains("HC92")){
//					measrMastrDAO.updateMeasrTotalCnt(insMap);
//				}
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			measrMastrDAO.insertAct(param);
		}
		
//		measrMastrDAO.insertActNew(param);
		measrMastrDAO.callProcActDataNew(param);
		
		return nCnt;
	}
	
	@Override
	public int insertHeartRate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String measrTm = (String) insMap.get("MEASR_TM");
				String chkStr = measrTm.substring(3,6);
				
				if(!"459".equals(chkStr) && !"959".equals(chkStr)){
					int min = Integer.parseInt(measrTm.substring(3,4));
					int sec = Integer.parseInt(measrTm.substring(4,6));
					
					if(min < 5){
						min = 4;
					}else{
						min = 9;
					}					
					sec = 59;
					
					measrTm = measrTm.substring(0,3) + Integer.toString(min) + Integer.toString(sec);
					insMap.put("MEASR_TM", measrTm);
				}
				
				insMap.putAll(param);
				measrMastrDAO.insertHeartRate(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			Map<String,Object> insMap = null;
			for (int i = 0; i < insList.size(); i++) {
				insMap = insList.get(i);
				insMap.putAll(param);
				
//				int excsCnt = Integer.parseInt(StringUtil.nvl(insMap.get("EXCS_CNT"),"0"));
//				1. 잘못된 운동보수 데이터(100만 이상) 인지 판단 
//				if (excsCnt > 1000000) {
////					1-1. 잘못된 운동보수 데이터인 경우 TN_MS_RUNNING_STATUS_DISORD 테이블에 INSERT       
//					measrMastrDAO.insertRunningStatusDisOrd(insMap);
//				}
				
//				2. 이틀에 걸친 데이터 인지 판단
//				2-1. 이틀에 걸친 데이터 인 경우 하루씩 TN_MS_RUNNING_STATUS 테이블 INSERT
//				if (!StringUtil.nvl(insMap.get("EXCS_START_DE")).equals(StringUtil.nvl(insMap.get("EXCS_END_DE")))) {
////					2-1-1. 종료일자 0시부터 종료시간까지 활동량 합계 계산
//					Map<String,Object> endCntMap = new HashMap<String,Object>();
////					endCntMap.put("SESS_USER_ID", param.get("USER_ID"));
////					endCntMap.put("EXCS_END_DE", param.get("EXCS_END_DE"));
////					endCntMap.put("EXCS_END_TM", param.get("EXCS_END_TM"));
////					endCntMap = measrMastrDAO.checkActCnt(endCntMap);
//					long restTm = Integer.parseInt(StringUtil.nvl(insMap.get("REST_TM"),"0"));
//					int endCnt = 0;//Integer.parseInt(StringUtil.nvl(endCntMap.get("ACT_CNT"),"0"));
////					if (endCnt > excsCnt) {
////						endCnt = excsCnt;
////					}
//					
//					String endBgnDt = StringUtil.nvl(insMap.get("EXCS_END_DE")) + "000000";
//					String endEndDt = StringUtil.nvl(insMap.get("EXCS_END_DE")) + StringUtil.nvl(insMap.get("EXCS_END_TM"));
//					long endActTm = SimpleDateUtil.getFromToDiffForSeconed(endBgnDt, endEndDt);
//					
////					2-1-1-1. 167만보의 경우 평균 걸음수로 활동량 계산
////					if (excsCnt > 1000000) {
//						int actCnt_167 = (int)((endActTm / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
////						if (endCnt > actCnt_167) {
//							endCnt = actCnt_167;
////						}
////					}
//					
//					if(endActTm < restTm){
//						restTm = endActTm;
//					}		
//							
////					2-1-2. 당일 데이터 TN_MS_RUNNING_STATUS 테이블에 INSERT
//					endCntMap = new HashMap<String,Object>();
//					endCntMap.putAll(insMap);
//					endCntMap.put("EXCS_START_DE", insMap.get("EXCS_END_DE"));
//					endCntMap.put("EXCS_START_TM", "000000");
//					endCntMap.put("EXCS_CNT", endCnt);
//					endCntMap.put("EXCS_TM", endActTm);
//					endCntMap.put("REST_TM", restTm);
//					measrMastrDAO.insertRunningStatus(endCntMap);
//					
//					
////					2-1-3. 시작시간부터 시작일자 23시 59분 59초까지 활동량 합계 계산
//					Map<String,Object> startCntMap = new HashMap<String,Object>();
////					startCntMap.put("SESS_USER_ID", param.get("USER_ID"));
////					startCntMap.put("EXCS_START_DE", param.get("EXCS_START_DE"));
////					startCntMap.put("EXCS_START_TM", param.get("EXCS_START_TM"));
////					startCntMap = measrMastrDAO.checkActCnt(startCntMap);
//
//					int startCnt = 0;//Integer.parseInt(StringUtil.nvl(startCntMap.get("ACT_CNT"),"0"));
////					if (startCnt > excsCnt) {
////						startCnt = excsCnt;
////					}
//					
//					String startBgnDt = StringUtil.nvl(insMap.get("EXCS_START_DE")) + StringUtil.nvl(insMap.get("EXCS_START_TM"));
//					String startEndDt = StringUtil.nvl(insMap.get("EXCS_START_DE")) + "235959";
//					long startActTm = SimpleDateUtil.getFromToDiffForSeconed(startBgnDt, startEndDt);
//					
////					2-1-3-1. 167만보의 경우 평균 걸음수로 활동량 계산
//					if (excsCnt > 1000000) {
//						actCnt_167 = (int)((endActTm / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
////						if (startCnt > actCnt_167) {
//							startCnt = actCnt_167;
////						}
//					}
//					else {
//						startCnt = excsCnt - endCnt;
//					}
//					
//					if(startActTm < restTm){
//						restTm = startActTm;
//					}
//					
////					2-1-4. 전일 데이터 TN_MS_RUNNING_STATUS 테이블에 INSERT
//					startCntMap = new HashMap<String,Object>();
//					startCntMap.putAll(insMap);
//					startCntMap.put("EXCS_END_DE", insMap.get("EXCS_START_DE"));
//					startCntMap.put("EXCS_END_TM", "235959");
//					startCntMap.put("EXCS_CNT", startCnt);
//					startCntMap.put("EXCS_TM", startActTm);
//					startCntMap.put("REST_TM", restTm);
//					measrMastrDAO.insertRunningStatus(startCntMap);
//				}
////				2-2. 하루짜리 데이터 인 경우 TN_MS_RUNNING_STATUS 테이블 INSERT
//				else {
//					2-2-1. 167만보의 경우 평균 걸음수로 활동량 계산
//					if (excsCnt > 1000000) {
//						int actCnt_167 = (int)((Integer.parseInt(StringUtil.nvl(insMap.get("EXCS_TM"),"0")) / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
//						insMap.put("EXCS_CNT", actCnt_167);
//					}
					
					measrMastrDAO.insertRunningStatus(insMap);
//				}
				
				
			}//end-for
			
			if(insMap!=null){
				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")||(insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH")&&!insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH2020"))){
					Map actMaxSn = measrMastrDAO.selectActMaxSn(insMap);
					actMaxSn.put("SESS_USER_ID", insMap.get("SESS_USER_ID"));
					measrMastrDAO.updateInbodyTotalCnt(actMaxSn);
					measrMastrDAO.callProcActDataNew(param);
				}
//				else if(insMap.get("MEASR_MODEL_NM").toString().contains("HC92")){
//					Map actMaxSn = measrMastrDAO.selectActMaxSn(insMap);
//					actMaxSn.put("SESS_USER_ID", insMap.get("SESS_USER_ID"));
//					measrMastrDAO.updateMeasrTotalCnt(actMaxSn);
//					measrMastrDAO.callProcActDataNew(param);
//				}
			}
			
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningCalorie(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorie(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningHR(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningHR(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertHeartRateArr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEART_RATE_LIST"));
				insMap.put("HEART_RATE_LIST", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertHeartRateArr(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	@Override
	public int insertRunningHrArr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEART_RATE_LIST"));
				insMap.put("HEART_RATE_LIST", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningHrArr(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	/********************************** OpenApi 적용 관련 END ***************************************/
	
	public List<Map<String,Object>> selectDeviceSerial(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectDeviceSerial(param);
	}
	
	public List<Map<String,Object>> selectEquipItem(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectEquipItem(param);
	}
	
	public List<Map<String,Object>> selectUserOta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectUserOta(param);
	}
	
	public int insertUserOta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.insertUserOta(param);
	}
	

	/********************************** 혈당계 CLF값 보정 함수 ***************************************/
	@Override
	public int updateLstBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.updateLstBloodSugar(param);
	}
	/********************************** 혈당계 CLF값 보정 함수 ***************************************/
	/*********************** 연동 결과값 산출 (M004,M005,W001 각 혈압, 혈당, 체중) ***********************/
	@Override
	public Map<String, Object> getMeasureResultInf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.getMeasureResultInf(param);
	}
	/*********************** 연동 결과값 산출 (M004,M005,W001 각 혈압, 혈당, 체중) ***********************/
	
	public String getToDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
		return strToday;
	}

	/**
	 * 체성분 수동입력 등록
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */		
	public Map<String,Object> insertBodyCompManu(Map<String, Object> param) throws Exception {
		int rtInt = 0;
		try{
			String MEASR_SN = param.get("MEASR_SN")==null?"":(String)param.get("MEASR_SN");	
			Map<String, Object> hMap = measrMastrDAO.selectHeightInfo(param);						
			String height = hMap.get("HEIGHT")==null?"":hMap.get("HEIGHT").toString();
			param.put("HEIGHT", height);
			param.put("USER_ID", param.get("SESS_USER_ID"));				
			param.put("MEASR_TRGT_CLF", "20");
			param.put("MEASR_RSLT", "S");
			param.put("AUTO_MANU_CLF", "M");
			System.out.println("### insertBodyCompManu param>>"+param);			
			if("".equals(MEASR_SN)){
				param.putAll(getMeasrSeq());
				rtInt = measrMastrDAO.insertMeasrMas(param);
			}			
			rtInt = measrMastrDAO.insertBodyCompManu(param);
			param.put("rtInt", rtInt);
			
		}catch(Exception e){
			LOG.debug(e.toString());
		}
		return param;
	}
	
	/**
	 * 혈당 수동입력 등록
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */		
	@Override
	public Map<String,Object> insertBloodSugarManu(Map<String, Object> param){
		int rtInt = 0;
	
		try{			
			param.put("MEASR_TRGT_CLF", "40");
			param.put("MEASR_RSLT", "S");			
			param.put("AUTO_MANU_CLF", "M");
			
			System.out.println("### insertBloodSugarManu param>>"+param);
			
			int dupleChk = measrMastrDAO.duplChkBloodSugar(param);
			if(dupleChk == 0){
				param.putAll(measrMastrDAO.getMeasrSeq());
				param.put("SESS_USER_ID", param.get("USER_ID"));
				measrMastrDAO.insertMeasrMas(param);	
				measrMastrDAO.insertBloodSugar(param);
			}
			if(param.get("MEASR_DE").toString().equals(getToDay())){				
				int rsInt = 0;
				Map<String, Object> pointInfMap = new HashMap<String,Object>();
				param.put("MISSION_CD_DTLS", "01");
				param.put("USER_ID", param.get("SESS_USER_ID"));
				param.put("MISSION_CD", "M005");
				param.put("SVC_NO", missionDAO.getSvcNo(param));
				//if(missionDAO.hasMeasureMissionYn(param).equals("PASS_WAIT_MISSION")){
				if(missionDAO.hasWeekMissionYn(param).equals("PASS_WAIT_MISSION")) {
					rsInt += missionDAO.insertExcMissionSucc(param);
					pointInfMap = missionDAO.selectMissionPopInf(param);
					if(missionDAO.selectGetMonthMissionPoint(param)<=21){
						param.put("OBTAIN_POINT", pointInfMap.get("ALWNC_POINT"));
						rsInt += missionDAO.insertMissionPoint(param);
						
					}
				}				
			}
			

		}catch(Exception e){
			LOG.debug(e.toString());
		}
		return param;
	}
	
	/**
	 * 혈압 수동입력 등록
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> insertBloodPressManu(Map<String, Object> param) throws Exception {
		int rtInt = 0;
		
		try{			
			param.put("MEASR_TRGT_CLF", "30");
			param.put("MEASR_RSLT", "S");
			param.put("AUTO_MANU_CLF", "M");
						
			System.out.println("### insertBloodPressManu param>>"+param);
			
			int dupleChk = measrMastrDAO.duplChkBloodPress(param);
			if(dupleChk == 0){
				param.putAll(measrMastrDAO.getMeasrSeq());
				param.put("SESS_USER_ID", param.get("USER_ID"));
				measrMastrDAO.insertMeasrMas(param);	
				measrMastrDAO.insertBloodPress(param);
			}
						
			if(param.get("MEASR_DE").toString().equals(getToDay())){
				System.out.println("BLOOD_PRESS_MISSION_PROCESS_START");
				int rsInt = 0;
				Map<String, Object> pointInfMap = new HashMap<String,Object>();
				param.put("MISSION_CD_DTLS", "01");
				param.put("USER_ID", param.get("SESS_USER_ID"));
				param.put("MISSION_CD", "M004");
				param.put("SVC_NO", missionDAO.getSvcNo(param));
				if(missionDAO.hasMeasureMissionYn(param).equals("PASS_WAIT_MISSION")){									
					rsInt += missionDAO.insertExcMissionSucc(param);
					pointInfMap = missionDAO.selectMissionPopInf(param);
					if(missionDAO.selectGetMonthMissionPoint(param)<=155){
						param.put("OBTAIN_POINT", pointInfMap.get("ALWNC_POINT"));
						rsInt += missionDAO.insertMissionPoint(param);
						
					}
				}				
			}

		}catch(Exception e){
			LOG.debug(e.toString());
		}
		return param;
	}
	@Override
	public Map<String, Object> lstBloodSugarSaveChk(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.lstBloodSugarSaveChk(param);
	}
	@Override
	public int updateLstBloodSugarClf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.updateLstBloodSugarClf(param);
	}
	@Override
	public Map<String, Object> selectLastData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rtMap = new HashMap<String,Object>();
		
		String deviceType = "";
		if(param.get("DEVICE_TYPE")!=null&&!"".equals(param.get("DEVICE_TYPE"))) {			
			deviceType = String.valueOf(param.get("DEVICE_TYPE"));

			if("pedometer".equals(deviceType)) {
				rtMap = measrMastrDAO.selectPedometerLstData(param);
			}else if("bodyComp".equals(deviceType)) {
				rtMap = measrMastrDAO.selectBodyCompLstData(param);
			}else if("bloodsugar".equals(deviceType)) {
				int cnt = measrMastrDAO.chkBloodSugarLstData(param);
				if(cnt > 0) {
					rtMap = measrMastrDAO.selectBloodSugarLstData(param);				
					rtMap.put("result", "0");	
				}else {
					rtMap.put("result", "1");					
					rtMap.put("msg", "dataCnt is 0");
				}
			}else if("bloodpress".equals(deviceType)) {
				int cnt = measrMastrDAO.chkBloodPressLstData(param);
				if(cnt > 0) {
					rtMap = measrMastrDAO.selectBloodPressLstData(param);
					rtMap.put("result", "0");	
				}else {
					rtMap.put("result", "1");
					rtMap.put("msg", "dataCnt is 0");
				}
			}
		}else {
			rtMap.put("result", "2");
			rtMap.put("msg", "deviceType is null");
		}
		return rtMap;
	}
	
	private Map<String, Object> validateData(Map<String,Object> param){
		
		// 오류 데이터 걸러내기
		List<String> errorCodes = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();
		Boolean valid = true;	
						
		//테이블에 따른 분기
		String tblNm = StringUtil.nvl(param.get("tableNm"));
		
		//1.걸음수 / 칼로리 테이블		
		if(tblNm.equals("TN_MS_ACT") || tblNm.equals("TN_MS_RUNNING_CALORIE")) {
			//MEASR_DE, MEASR_TM, TOT_ACT_CNT 값 체크	
			String measrDe = StringUtil.nvl(param.get("MEASR_DE"), "");
			//1_1. MEASR_DE : NULL인 경우
			if(measrDe.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"MEASR_DE\"");
				valid = false;
			//1_2. MEASR_DE : 년도날짜 형식에 맞지 않는 경우(자리수 맞지 않는 경우)
			}else if (!measrDe.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid date format for MEASR_DE: " + measrDe);
			    valid = false;
			//1_3. MEASR_DE : 유효성 보완코드
			} else {
			    // 날짜 유효성 검사 (존재하지 않는 날짜 체크)
			    try {
			        int year = Integer.parseInt(measrDe.substring(0, 4));
			        int month = Integer.parseInt(measrDe.substring(4, 6));
			        int day = Integer.parseInt(measrDe.substring(6, 8));
	
			        Calendar calendar = Calendar.getInstance();
			        calendar.setLenient(false);
			        calendar.set(year, month - 1, day); // Calendar는 0부터 시작하는 month 사용
	
			        // 실제 존재하는 날짜인지 확인 (set() 후 get()을 하면 예외 발생 가능)
			        calendar.getTime(); 
	
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid date value for MEASR_DE: " + measrDe);
			        valid = false;
			    }
			}		
			String measrTm = StringUtil.nvl(param.get("MEASR_TM"), "");
			//2_1. MEASR_TM : NULL인 경우
			if(measrTm.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"MEASR_TM\"");
				valid = false;
			//2_2. MEASR_TM : 시간형식에 맞지 않는 경우(자리수 맞지 않는경우)
			}else if (!measrTm.matches("^(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid time format for MEASR_TM: " + measrTm);
			    valid = false;
			//2_3. MEASR_TM : 유효성 보완코드
			} else {
			    try {
			        int hour = Integer.parseInt(measrTm.substring(0, 2));
			        int minute = Integer.parseInt(measrTm.substring(2, 4));
			        int second = Integer.parseInt(measrTm.substring(4, 6));
	
			        if (hour > 23 || minute > 59 || second > 59) {
			            throw new IllegalArgumentException("Invalid time value");
			        }
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid time value for MEASR_TM: " + measrTm);
			        valid = false;
			    }
			}
			if(tblNm.equals("TN_MS_ACT")) {
				//3. TOT_ACT_CNT : 130000보 이상 넘는 수치가 들어오는 경우
				if(Integer.parseInt(String.valueOf(param.get("TOT_ACT_CNT"))) > (int)130000) {
					errorCodes.add("30");
					errorMessages.add("TOT_ACT_CNT is exceeds 130000");
					valid = false;
				}
			}
			
		//2.운동 테이블
		}else if(tblNm.equals("TN_MS_RUNNING_STATUS")) {
			//EXCS_START_DE, EXCS_START_TM, EXCS_END_DE, EXCS_END_TM 값이 이상수치 인 경우		
			String excsStartDe = StringUtil.nvl(param.get("EXCS_START_DE"), "");
			//1_1. EXCS_START_DE : NULL인 경우
			if(excsStartDe.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"EXCS_START_DE\"");
				valid = false;
			//1_2. EXCS_START_DE : 년도날짜 형식에 맞지 않는 경우(자리수 맞지 않는 경우)
			}else if (!excsStartDe.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid date format for EXCS_START_DE: " + excsStartDe);
			    valid = false;
			//1_3. MEASR_DE : 유효성 보완코드
			} else {
			    // 날짜 유효성 검사 (존재하지 않는 날짜 체크)
			    try {
			        int year = Integer.parseInt(excsStartDe.substring(0, 4));
			        int month = Integer.parseInt(excsStartDe.substring(4, 6));
			        int day = Integer.parseInt(excsStartDe.substring(6, 8));
	
			        Calendar calendar = Calendar.getInstance();
			        calendar.setLenient(false);
			        calendar.set(year, month - 1, day); // Calendar는 0부터 시작하는 month 사용
	
			        // 실제 존재하는 날짜인지 확인 (set() 후 get()을 하면 예외 발생 가능)
			        calendar.getTime(); 
	
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid date value for EXCS_START_DE: " + excsStartDe);
			        valid = false;
			    }
			}
			String excsStartTm = StringUtil.nvl(param.get("EXCS_START_TM"), "");
			//2_1. EXCS_START_TM : NULL인 경우
			if(excsStartTm.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"EXCS_START_TM\"");
				valid = false;
			//2_2. EXCS_START_TM : 시간형식에 맞지 않는 경우(자리수 맞지 않는경우)
			}else if (!excsStartTm.matches("^(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid time format for EXCS_START_TM: " + excsStartTm);
			    valid = false;
			//2_3. EXCS_START_TM : 유효성 보완코드
			} else {
			    try {
			        int hour = Integer.parseInt(excsStartTm.substring(0, 2));
			        int minute = Integer.parseInt(excsStartTm.substring(2, 4));
			        int second = Integer.parseInt(excsStartTm.substring(4, 6));
	
			        if (hour > 23 || minute > 59 || second > 59) {
			            throw new IllegalArgumentException("Invalid time value");
			        }
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid time value for EXCS_START_TM: " + excsStartTm);
			        valid = false;
			    }
			}
			String excsEndDe = StringUtil.nvl(param.get("EXCS_END_DE"), "");
			//3_1. EXCS_END_DE : NULL인 경우
			if(excsEndDe.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"EXCS_END_DE\"");
				valid = false;
			//3_2. EXCS_END_DE : 년도날짜 형식에 맞지 않는 경우(자리수 맞지 않는 경우)
			}else if (!excsEndDe.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid date format for EXCS_END_DE: " + excsEndDe);
			    valid = false;
			//3_3. EXCS_END_DE : 유효성 보완코드
			} else {
			    // 날짜 유효성 검사 (존재하지 않는 날짜 체크)
			    try {
			        int year = Integer.parseInt(excsEndDe.substring(0, 4));
			        int month = Integer.parseInt(excsEndDe.substring(4, 6));
			        int day = Integer.parseInt(excsEndDe.substring(6, 8));
	
			        Calendar calendar = Calendar.getInstance();
			        calendar.setLenient(false);
			        calendar.set(year, month - 1, day); // Calendar는 0부터 시작하는 month 사용
	
			        // 실제 존재하는 날짜인지 확인 (set() 후 get()을 하면 예외 발생 가능)
			        calendar.getTime(); 
	
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid date value for EXCS_END_DE: " + excsEndDe);
			        valid = false;
			    }
			}
			String excsEndTm = StringUtil.nvl(param.get("EXCS_END_TM"), "");
			//4_1. EXCS_END_TM : NULL인 경우
			if(excsEndTm.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"EXCS_END_TM\"");
				valid = false;
			//4_2. EXCS_END_TM : 시간형식에 맞지 않는 경우(자리수 맞지 않는경우)
			}else if (!excsEndTm.matches("^(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid time format for EXCS_END_TM: " + excsEndTm);
			    valid = false;
			//4_3. EXCS_END_TM : 유효성 보완코드
			} else {
			    try {
			        int hour = Integer.parseInt(excsEndTm.substring(0, 2));
			        int minute = Integer.parseInt(excsEndTm.substring(2, 4));
			        int second = Integer.parseInt(excsEndTm.substring(4, 6));
	
			        if (hour > 23 || minute > 59 || second > 59) {
			            throw new IllegalArgumentException("Invalid time value");
			        }
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid time value for EXCS_END_TM: " + excsEndTm);
			        valid = false;
			    }
			}		
			
		//3.심박수 / 운동심박수 테이블
		}else if(tblNm.equals("TN_MS_HEART_RATE_ARR") || tblNm.equals("TN_MS_RUNNING_HR_ARR")) {
			//START_DE, START_TM 값이 이상수치 인 경우		
			String startDe = StringUtil.nvl(param.get("START_DE"), "");
			//1_1. START_DE : NULL인 경우
			if(startDe.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"START_DE\"");
				valid = false;
			//1_2. START_DE : 년도날짜 형식에 맞지 않는 경우(자리수 맞지 않는 경우)
			}else if (!startDe.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid date format for START_DE: " + startDe);
			    valid = false;
			//1_3. START_DE : 유효성 보완코드
			} else {
			    // 날짜 유효성 검사 (존재하지 않는 날짜 체크)
			    try {
			        int year = Integer.parseInt(startDe.substring(0, 4));
			        int month = Integer.parseInt(startDe.substring(4, 6));
			        int day = Integer.parseInt(startDe.substring(6, 8));
	
			        Calendar calendar = Calendar.getInstance();
			        calendar.setLenient(false);
			        calendar.set(year, month - 1, day); // Calendar는 0부터 시작하는 month 사용
	
			        // 실제 존재하는 날짜인지 확인 (set() 후 get()을 하면 예외 발생 가능)
			        calendar.getTime(); 
	
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid date value for START_DE: " + startDe);
			        valid = false;
			    }
			}
			String startTm = StringUtil.nvl(param.get("START_TM"), "");
			//2_1. START_TM : NULL인 경우
			if(startTm.equals("")) {
				errorCodes.add("10");
				errorMessages.add("ORA-01400:cannot insert NULL into \"SMHCADM\"."+tblNm+".\"START_TM\"");
				valid = false;
			//2_2. START_TM : 시간형식에 맞지 않는 경우(자리수 맞지 않는경우)
			}else if (!startTm.matches("^(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$")) {
			    errorCodes.add("20");
			    errorMessages.add("Invalid time format for START_TM: " + startTm);
			    valid = false;
			//2_3. START_TM : 유효성 보완코드
			} else {
			    try {
			        int hour = Integer.parseInt(startTm.substring(0, 2));
			        int minute = Integer.parseInt(startTm.substring(2, 4));
			        int second = Integer.parseInt(startTm.substring(4, 6));
	
			        if (hour > 23 || minute > 59 || second > 59) {
			            throw new IllegalArgumentException("Invalid time value");
			        }
			    } catch (Exception e) {
			        errorCodes.add("20");
			        errorMessages.add("Invalid time value for START_TM: " + startTm);
			        valid = false;
			    }
			}
		}
		
		
		//오류 데이터 발생시 DB INSERT				
		if(!errorCodes.isEmpty()) {	
			//ERROR코드 중복 제거 추가
			Set<String> uniqueErrorCodes = new LinkedHashSet<String>(errorCodes);
			
			StringBuilder ecStr = new StringBuilder();
			StringBuilder emStr = new StringBuilder();
			/*for(int j=0; j<uniqueErrorCodes.size(); j++) {
				ecStr.append(errorCodes.get(j));
				if(j < errorCodes.size() -1) {
					ecStr.append(",");
				}
			}*/
			for (String code : uniqueErrorCodes) {
	            if (ecStr.length() > 0) ecStr.append(",");
	            ecStr.append(code);
	        }
			for(int j=0; j<errorMessages.size(); j++) {
				emStr.append(errorMessages.get(j));
				if(j < errorMessages.size() -1) {
					emStr.append(" | ");
				}	
			}
			param.put("ERROR_CD", ecStr.toString());
			param.put("ERROR_MSG", emStr.toString());
			
			//에러 테이블에 들어갈 유저 정보(ORG_CD, USER_NM, APP_VERSION, OS_TYPE, OS_VERSION) 조회			
			Map<String, Object> uMap = new HashMap<String, Object>();
			uMap = measrMastrDAO.getUserInfoForActErr(param);
			param.putAll(uMap);
			
			measrMastrDAO.insertActDataErrHis(param);
		}
		
		//결과 리턴
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("valid", valid);
		
		return result;		
	}
	
}
