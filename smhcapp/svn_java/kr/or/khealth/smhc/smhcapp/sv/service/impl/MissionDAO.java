package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("smhcapp.sv.MissionDAO")
public class MissionDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, Object>> selectMissionList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.selectMissionList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectTodayMissionTimeline(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.selectTodayMissionTimeline", param);
		return rsList;
	}
	
	public String todaySuccYn(Map<String, Object> param) throws Exception{
		String passYn = "N";
		// 일일 미션 확인
		String todaySuccYn = selectOne("smhcapp.sv.mission.todaySuccYn", param);
	    String weekSuccYn = selectOne("smhcapp.sv.mission.weekSuccYn", param);
	    String monthSuccYn = selectOne("smhcapp.sv.mission.monthSuccYn", param);
	    
	    // 모두 null일 경우 미션 없음
	    if (todaySuccYn == null && weekSuccYn == null && monthSuccYn == null) {
	        return "NO_MISSION";
	    }

	    // 하나라도 미수행했으면 미수행
	    if ("N".equals(todaySuccYn) || "N".equals(weekSuccYn) || "N".equals(monthSuccYn)) {
	    	passYn = "N";
	        return passYn;
	    }
	    // 그외
	    passYn = "Y";
		return passYn;
	}
	
	public int insertDefaultMissionSet(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.insertDefaultMissionSet", param);
		return rsInt;
	}
	 
	public int insertClickMissionMngt(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.insertClickMissionMngt", param);
		return rsInt;
	}
	
	public int mergeWaterCount(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.mergeWaterCount", param);
		return rsInt;
	}
	
	public int mergeFluidCount(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.mergeFluidCount", param);
		return rsInt;
	}
	
	public Map<String, Object> selectMissionPopInf(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.selectMissionPopInf", param);
		return rsMap;
	}
	
	public String thisMissionCompleteYn(Map<String, Object> param) throws Exception{
		String passYn = selectOne("smhcapp.sv.mission.thisMissionCompleteYn", param);
		return passYn;
	}
	
	public String missionCompleteYn(Map<String, Object> param) throws Exception{
		String passYn = selectOne("smhcapp.sv.mission.missionCompleteYn", param);
		return passYn;
	}
	
	public int selectGetMonthMissionPoint(Map<String, Object> param)throws Exception{
		int missionGetPoint = 0;
		missionGetPoint = selectOne("smhcapp.sv.mission.selectGetMonthMissionPoint", param);
		return missionGetPoint;
	}
	
	public int insertMissionPoint(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.insertMissionPoint", param);
		return rsInt;
	}
	/*
	public String actTimeMissionChk(Map<String, Object> param) throws Exception{
		String resultYn = "NO_DATA";
		if(selectOne("smhcapp.sv.mission.actTimeMissionChk", param)!=null){			
			resultYn = selectOne("smhcapp.sv.mission.actTimeMissionChk", param);
		}
		return resultYn;
	}
	*/
	public Map<String, Object> actTimeMissionChk(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.actTimeMissionChk", param);
		return rsMap;
	}	
	
	public int insertExcMissionSucc(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.mission.insertExcMissionSucc", param);
		return rsInt;
	}
	
	public String hasMeasureMissionYn(Map<String, Object> param) throws Exception{
		String hasYn = selectOne("smhcapp.sv.mission.hasMeasureMissionYn", param);
		return hasYn;
	}
	
	public String bloodPressMissionAvailable(Map<String, Object> param) throws Exception{
		String passsYn = selectOne("smhcapp.sv.mission.bloodPressMissionAvailable", param);
		return passsYn;
	}
	
	public String userHaveMissionSttus(Map<String, Object> param) throws Exception{
		String haveYn = selectOne("smhcapp.sv.mission.userHaveMissionSttus", param);
		return haveYn;
	}
	
	public String hasWeekMissionYn(Map<String, Object> param) throws Exception{
		String hasYn = selectOne("smhcapp.sv.mission.hasWeekMissionYn", param);
		return hasYn;
	}
	
	public List<Map<String, Object>> weekWaterChck(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekWaterChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekOutChck(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekOutChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekActBandChck(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekActBandChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekMissionUse(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekMissionUse", param);
		return rsList;
	}
	
	public Map<String, Object> weekAvgActCnt(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekAvgActCnt", param);
		return rsMap;
	}
	
	public Map<String, Object> weekDrugCnt(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekDrugCnt", param);
		return rsMap;
	}
	
	public Map<String, Object> weekAvgBloodSugar(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekAvgBloodSugar", param);
		return rsMap;
	}
	
	public Map<String, Object> weekMealCnt(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekMealCnt", param);
		return rsMap;
	}
	
	public Map<String, Object> weekAvgBloodPress(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekAvgBloodPress", param);
		return rsMap;
	}
	
	public Map<String, Object> weekWeight(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.sv.mission.weekWeight", param);
		return rsMap;
	}
	
	public int getSvcNo(Map<String, Object> param) throws Exception{
		return selectOne("smhcapp.sv.mission.getSvcNo",param);
	}

	public List<Map<String, Object>> weekWeightChck(Map<String, Object> param) {		
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekWeightChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekWeightList(Map<String, Object> param) {		
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekWeightList", param);
		return rsList;
	}

	public List<Map<String, Object>> weekPressChck(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekPressChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekPressList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekPressList", param);
		return rsList;
	}

	public List<Map<String, Object>> weekSugarBeforeMealChck(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekSugarBeforeMealChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekSugarBeforeMealList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekSugarBeforeMealList", param);
		return rsList;
	}

	public List<Map<String, Object>> weekSugarAfterMealChck(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekSugarAfterMealChck", param);
		return rsList;
	}
	
	public List<Map<String, Object>> weekSugarAfterMealList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekSugarAfterMealList", param);
		return rsList;
	}

	public List<Map<String, Object>> weekActChck(Map<String, Object> param) {	
		List<Map<String, Object>> rsList = selectList("smhcapp.sv.mission.weekActChck", param);
		return rsList;
	}

	public int hasWeightRegCnt(Map<String, Object> param) {		
		return selectOne("smhcapp.sv.mission.hasWeightRegCnt",param);
	}

	public int getWaterCount(Map<String, Object> param) {		
		return selectOne("smhcapp.sv.mission.getWaterCount",param);
	}
	
	public int getMealCount(Map<String, Object> param) {		
		return selectOne("smhcapp.sv.mission.getMealCount",param);
	}

	public int getMissionPoint(Map<String, Object> param) {
		return selectOne("smhcapp.sv.mission.getMissionPoint",param);
	}

	public Map<String, Object> selectMealMission(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.selectMealMission",param);
	}

	public Map<String, Object> monthAct(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.monthAct",param);
	}
	
	public Map<String, Object> monthBloodSugar(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.monthBloodSugar",param);
	}	

	public Map<String, Object> monthBloodPress(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.monthBloodPress",param);
	}

	public Map<String, Object> monthWeight(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.monthWeight",param);
	}
	
	public List<Map<String, Object>> monthAvgAct(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectList("smhcapp.sv.mission.monthAvgAct",param);
	}
	
	public List<Map<String, Object>> monthAvgBloodSugar(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectList("smhcapp.sv.mission.monthAvgBloodSugar",param);
	}
	
	public List<Map<String, Object>> monthAvgBloodPress(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectList("smhcapp.sv.mission.monthAvgBloodPress",param);
	}
	
	public List<Map<String, Object>> monthAvgWeight(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectList("smhcapp.sv.mission.monthAvgWeight",param);
	}

	public int getMissionDupCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("smhcapp.sv.mission.getMissionDupCnt",param);
	}
}
