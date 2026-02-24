package kr.or.khealth.smhc.smhcapp.sv.service;

import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;

public interface MissionService {

	public List<Map<String, Object>> selectMissionList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectTodayMissionTimeline(Map<String, Object> param) throws Exception;
	
	public String todaySuccYn(Map<String, Object> param) throws Exception;
	
	public int insertDefaultMissionSet(Map<String, Object> param) throws Exception;
	
	public int insertClickMissionMngt(Map<String, Object> param) throws Exception;
	
	public int mergeWaterCount(Map<String, Object> param) throws Exception;
	
	public int mergeFluidCount(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectMissionPopInf(Map<String, Object> param) throws Exception;
	
	public String thisMissionCompleteYn(Map<String, Object> param) throws Exception;
	
	public String missionCompleteYn(Map<String, Object> param) throws Exception;
	
	public int selectGetMonthMissionPoint(Map<String, Object>param) throws Exception;
	
	public int insertMissionPoint(Map<String, Object> param)throws Exception;
	
	public Map<String, Object> actTimeMissionChk(Map<String, Object> param) throws Exception;
	
	public int insertExcMissionSucc(Map<String, Object> param) throws Exception;
	
	public String hasMeasureMissionYn(Map<String, Object> param) throws Exception;
	
	public String bloodPressMissionAvailable(Map<String, Object> param) throws Exception;
	
	public String userHaveMissionSttus(Map<String, Object> param) throws Exception;
	
	public String hasWeekMissionYn(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekWaterChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekOutChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekActBandChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekMissionUse(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekAvgActCnt(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekDrugCnt(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekAvgBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekMealCnt(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekAvgBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> weekWeight(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> weekWeightChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekWeightList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> weekPressChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekPressList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> weekSugarBeforeMealChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekSugarBeforeMealList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> weekSugarAfterMealChck(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> weekSugarAfterMealList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> weekActChck(Map<String, Object> param) throws Exception;

	public int hasWeightRegCnt(Map<String, Object> param) throws Exception;

	public int getWaterCount(Map<String, Object> param) throws Exception;
	
	public int getMealCount(Map<String, Object> param) throws Exception;

	public int getMissionPoint(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectMealMission(Map<String, Object> param) throws Exception;

	public Map<String, Object> monthAct(Map<String, Object> param) throws Exception;

	public Map<String, Object> monthBloodSugar(Map<String, Object> param) throws Exception;

	public Map<String, Object> monthBloodPress(Map<String, Object> param) throws Exception;

	public Map<String, Object> monthWeight(Map<String, Object> param) throws Exception;

	public int getMissionDupCnt(Map<String, Object> param)  throws Exception;
	
	public List<Map<String, Object>> monthAvgAct(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> monthAvgBloodSugar(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> monthAvgBloodPress(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> monthAvgWeight(Map<String, Object> param) throws Exception;
}
