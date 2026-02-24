package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.cm.service.MainService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	
	@Resource(name="mainDAO")
    private MainDAO mainDAO;

	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getTrgterJoinSttus(Map<String, Object> param) throws Exception {
		return mainDAO.getTrgterJoinSttus(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getSvcContinuePer(Map<String, Object> param)	throws Exception {
		return mainDAO.getSvcContinuePer(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getWeekSvcJoinPer(Map<String, Object> param)	throws Exception {
		return mainDAO.getWeekSvcJoinPer(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getTodaySvcJoinCnt(Map<String, Object> param) throws Exception {
		return mainDAO.getTodaySvcJoinCnt(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getRealTimeCnsl(Map<String, Object> param) throws Exception {
		return mainDAO.getRealTimeCnsl(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getNormalCnsl(Map<String, Object> param) throws Exception {
		return mainDAO.getNormalCnsl(param);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getIntenseCnsl(Map<String, Object> param) throws Exception {
		return mainDAO.getIntenseCnsl(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getIntenseCnslNew(Map<String, Object> param) throws Exception {
		return mainDAO.getIntenseCnslNew(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getVisitCnsl(Map<String, Object> param) throws Exception {
		return mainDAO.getVisitCnsl(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public List<Map<String,Object>> getNoticeList(Map<String, Object> param) throws Exception {
		return mainDAO.getNoticeList(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int selectLoginIdCheck(Map<String, Object> param) throws Exception {
		return mainDAO.selectLoginIdCheck(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int selectTrgterCheck(Map<String, Object> param) throws Exception {
		return mainDAO.selectTrgterCheck(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int selectHealthDisorderInfoTrgter(Map<String, Object> param) throws Exception {
		return mainDAO.selectHealthDisorderInfoTrgter(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int selectSvcNoJoinTrgter(Map<String, Object> param) throws Exception {
		return mainDAO.selectSvcNoJoinTrgter(param);
	}
	
	@Override
	public Map<String,Object> getServerTime(Map<String,Object> param) throws Exception{
		return mainDAO.getServerTime(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int selectSvcSchNotCreateCnt(Map<String, Object> param) throws Exception{
		return mainDAO.selectSvcSchNotCreateCnt(param);
	}
	
	@Override
	public Map<String,Object> getTrgterInfo(Map<String,Object> param) throws Exception{
		return mainDAO.getTrgterInfo(param);
	}
	
	@Override
	public List<Map<String,Object>> getTrgterSpecialNote(Map<String,Object> param) throws Exception{
		return mainDAO.getTrgterSpecialNote(param);
	}
	
	@Override
	public List<Map<String,Object>> getObjCnslMemo(Map<String,Object> param) throws Exception{
		return mainDAO.getObjCnslMemo(param);
	}
	
	@Override
	public List<Map<String,Object>> getIntenseCnslMemo(Map<String,Object> param) throws Exception{
		return mainDAO.getIntenseCnslMemo(param);
	}
	
	@Override
	public int updateSpecialNote(Map<String, Object> param) throws Exception{
		return mainDAO.updateSpecialNote(param);
	}
	
	@Override
	public int deleteSpecialNote(Map<String, Object> param) throws Exception{
		return mainDAO.deleteSpecialNote(param);
	}

	@Override
	public List<Map<String, Object>> getFavoriteMenu(Map<String, Object> param) throws Exception {
		return mainDAO.getFavoriteMenu(param);
	}
	
	@Override
	public int saveFavoriteMenu(Map<String, Object> param) throws Exception {
		return mainDAO.saveFavoriteMenu(param);
	}

	@Override
	public int deleteFavoriteMenu(Map<String, Object> param) throws Exception {
		return mainDAO.deleteFavoriteMenu(param);
	}

	@Override
	public List<Map<String, Object>> getPopNoticeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getPopNoticeList(param);
	}
	
	@Override
	public int updPopNoticeCnfm(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return mainDAO.updPopNoticeCnfm(param);
	}
	
	@Override
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getOrgAuthList(param);
	}

	@Override
	public List<Map<String, Object>> getPopCallingMaterialNoticeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getPopCallingMaterialNoticeList(param);
	}

	@Override
	public List<Map<String, Object>> getPopServeyList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getPopServeyList(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getAccumulateSvcJoinCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getAccumulateSvcJoinCnt(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Map<String, Object> getAccumulateSvcDropCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getAccumulateSvcDropCnt(param);
	}
}
