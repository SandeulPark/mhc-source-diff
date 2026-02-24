package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcapp.sv.service.MissionService;

@Service("smhcapp.sv.MissionService")
public class MissionServiceImpl extends EgovAbstractServiceImpl implements MissionService{
	
	@Resource(name="smhcapp.sv.MissionDAO")
	private MissionDAO missionDAO;

	@Override
	public List<Map<String, Object>> selectMissionList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.selectMissionList(param);
	}

	@Override
	public List<Map<String, Object>> selectTodayMissionTimeline(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.selectTodayMissionTimeline(param);
	}

	@Override
	public String todaySuccYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.todaySuccYn(param);
	}
	

	@Override
	public int insertDefaultMissionSet(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.insertDefaultMissionSet(param);
	}

	@Override
	public int insertClickMissionMngt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.insertClickMissionMngt(param);
	}
	
	@Override
	public int mergeWaterCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.mergeWaterCount(param);
	}
	
	@Override
	public int mergeFluidCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.mergeFluidCount(param);
	}

	@Override
	public Map<String, Object> selectMissionPopInf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.selectMissionPopInf(param);
	}

	@Override
	public String thisMissionCompleteYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.thisMissionCompleteYn(param);
	}
	
	@Override
	public String missionCompleteYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.missionCompleteYn(param);
	}

	@Override
	public int selectGetMonthMissionPoint(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.selectGetMonthMissionPoint(param);
	}

	@Override
	public int insertMissionPoint(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.insertMissionPoint(param);
	}

	@Override
	public Map<String, Object> actTimeMissionChk(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.actTimeMissionChk(param);
	}

	@Override
	public int insertExcMissionSucc(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.insertExcMissionSucc(param);
	}

	@Override
	public String hasMeasureMissionYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.hasMeasureMissionYn(param);
	}

	@Override
	public String bloodPressMissionAvailable(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.bloodPressMissionAvailable(param);
	}

	@Override
	public String userHaveMissionSttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.userHaveMissionSttus(param);
	}

	@Override
	public String hasWeekMissionYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.hasWeekMissionYn(param);
	}
	
	@Override
	public List<Map<String, Object>> weekWaterChck(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekWaterChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekOutChck(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekOutChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekActBandChck(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekActBandChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekMissionUse(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekMissionUse(param);
	}
	
	@Override
	public Map<String, Object> weekAvgActCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekAvgActCnt(param);
	}
	
	@Override
	public Map<String, Object> weekDrugCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekDrugCnt(param);
	}
	
	@Override
	public Map<String, Object> weekAvgBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekAvgBloodSugar(param);
	}
	
	@Override
	public Map<String, Object> weekMealCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekMealCnt(param);
	}
	
	@Override
	public Map<String, Object> weekAvgBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekAvgBloodPress(param);
	}
	
	@Override
	public Map<String, Object> weekWeight(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.weekWeight(param);
	}

	@Override
	public List<Map<String, Object>> weekWeightChck(Map<String, Object> param) throws Exception {		
		return missionDAO.weekWeightChck(param);
	}

	@Override
	public List<Map<String, Object>> weekWeightList(Map<String, Object> param) throws Exception {		
		return missionDAO.weekWeightList(param);
	}
	
	@Override
	public List<Map<String, Object>> weekPressChck(Map<String, Object> param) throws Exception {
		return missionDAO.weekPressChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekPressList(Map<String, Object> param) throws Exception {
		return missionDAO.weekPressList(param);
	}

	@Override
	public List<Map<String, Object>> weekSugarBeforeMealChck(Map<String, Object> param) throws Exception {		
		return missionDAO.weekSugarBeforeMealChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekSugarBeforeMealList(Map<String, Object> param) throws Exception {		
		return missionDAO.weekSugarBeforeMealList(param);
	}

	@Override
	public List<Map<String, Object>> weekSugarAfterMealChck(Map<String, Object> param) throws Exception {
		return missionDAO.weekSugarAfterMealChck(param);
	}
	
	@Override
	public List<Map<String, Object>> weekSugarAfterMealList(Map<String, Object> param) throws Exception {
		return missionDAO.weekSugarAfterMealList(param);
	}

	@Override
	public List<Map<String, Object>> weekActChck(Map<String, Object> param) throws Exception {		
		return missionDAO.weekActChck(param);
	}

	@Override
	public int hasWeightRegCnt(Map<String, Object> param) throws Exception {		
		return missionDAO.hasWeightRegCnt(param);
	}

	@Override
	public int getWaterCount(Map<String, Object> param) throws Exception {		
		return missionDAO.getWaterCount(param);
	}
	
	@Override
	public int getMealCount(Map<String, Object> param) throws Exception {		
		return missionDAO.getMealCount(param);
	}

	@Override
	public int getMissionPoint(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.getMissionPoint(param);
	}

	@Override
	public Map<String, Object> selectMealMission(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.selectMealMission(param);
	}

	@Override
	public Map<String, Object> monthAct(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthAct(param);
	}

	@Override
	public Map<String, Object> monthBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthBloodSugar(param);
	}

	@Override
	public Map<String, Object> monthBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthBloodPress(param);
	}

	@Override
	public Map<String, Object> monthWeight(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return  missionDAO.monthWeight(param);
	}
	
	@Override
	public List<Map<String, Object>> monthAvgAct(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthAvgAct(param);
	}
	
	@Override
	public List<Map<String, Object>> monthAvgBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthAvgBloodSugar(param);
	}
	
	@Override
	public List<Map<String, Object>> monthAvgBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthAvgBloodPress(param);
	}
	
	@Override
	public List<Map<String, Object>> monthAvgWeight(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.monthAvgWeight(param);
	}

	@Override
	public int getMissionDupCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionDAO.getMissionDupCnt(param);
	}
}
