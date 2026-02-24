package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.IntensiveCnslMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



/**
 * @Class Name :IntensiveCnslMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 집중 상담업무를 관리하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01		장슬기			최초생성
 
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.IntensiveCnslMngtService")
public class IntensiveCnslMngtServiceImpl extends EgovAbstractServiceImpl implements IntensiveCnslMngtService{
	
	@Resource(name= "web.sv.IntensiveCnslMngtServiceDAO")
	private IntensiveCnslMngtServiceDAO intensiveCnslMngtServiceDAO;

	@Override
	public List<Map<String, Object>> getIntensiveCnslMngtList(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getIntensiveCnslMngtList(param);
	}

	@Override
	public Map<String, String> getCountIntensiveCnsl(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCountIntensiveCnsl(param);
	}

	@Override
	public Map<String, String> getBasicUserInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getBasicUserInfo(param);
	}

	@Override
	public List<Map<String, Object>> getGoalMngt(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getGoalMngt(param);
	}

	@Override
	public Map<String, String> getCnslHistory(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCnslHistory(param);
	}

	@Override
	public List<Map<String, Object>> getMealRegDe(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealRegDe(param);
	}

	@Override
	public List<Map<String, Object>> getCnslDe(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCnslDe(param);
	}

	@Override
	public Map<String,Object> getMealDietInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealDietInfo(param);
	}
	
	@Override
	   public Map<String, String> getIntakeSttus(Map<String, Object> param) throws Exception {
		   return intensiveCnslMngtServiceDAO.getIntakeSttus(param);
	   }

	 @Override
	   public List<Map<String, String>> getEvalActive(Map<String, Object> param) throws Exception {
		   return intensiveCnslMngtServiceDAO.getEvalActive(param);
	  }

	@Override
	public int updateIntensiveCnslEval(Map<String, Object> param) throws Exception {
		int rsInt = intensiveCnslMngtServiceDAO.updateIntensiveCnslEval(param);
		return rsInt;
	}

	@Override
	public int updateSubmit(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.updateSubmit(param);
	}
	
	@Override
	public int deleteIntensiveCnslEval(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.deleteIntensiveCnslEval(param);
	}

	@Override
	public Map<String, String> selectHealthCnslDtls(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.selectHealthCnslDtls(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthCnslAttchFiles(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.selectHealthCnslAttchFiles(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthCnslBottomList(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.selectHealthCnslBottomList(param);
	}
	
	@Override
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCnslTemplateNm(param);
	}
	
	@Override
	public List<Map<String, Object>> getPractMissionRslt(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getPractMissionRslt(param);
	}	
	
	// 2017.03.03 이태석 추가(파일첨부)
	@Override
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCnslAttchList(param);
	}

	// 식사일기 관련 정보 추가	
	@Override
	public List<Map<String, Object>> getMealDiaryList(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealDiaryList(param);
	}

	@Override
	public List<Map<String, Object>> getMealDiaryInputInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealDiaryInputInfo(param);
	}

	@Override	
	public Map<String,Object> getMealAssayRslt(Map<String, Object> param) throws Exception{
		return intensiveCnslMngtServiceDAO.getMealAssayRslt(param);
	}

	
	@Override
	public List<Map<String, Object>> getCRFPerRslt(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCRFPerRslt(param);
	}
	
	
	public List<Map<String, Object>> getMealDivCal(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealDivCal(param);	
	}		


	@Override
	public List<Map<String, Object>> getMealNutriRslt(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealNutriRslt(param);
	}
	
	@Override
	public Map<String,Object> getMealEtcRslt(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealEtcRslt(param);
	}	
	
	//집중상담 기능 개선(2017.05.25)
	@Override
	public List<Map<String, Object>> getPrdTotalinfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getPrdTotalinfo(param);
	}	

	
	@Override
	public List<Map<String, Object>> getSvWeekTotalnfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getSvWeekTotalnfo(param);
	}
	
	
	public List<Map<String, Object>> getCalTotalInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCalTotalInfo(param);	
	}		


	@Override
	public List<Map<String, Object>> getCRFTotalnfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getCRFTotalnfo(param);
	}
	
	@Override
	public  List<Map<String, Object>> getMealAvgInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealAvgInfo(param);
	}	
	
	
	

	
	public List<Map<String, Object>> getMealAssayTotalInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealAssayTotalInfo(param);	
	}		


	@Override
	public List<Map<String, Object>> getMealEtcTotalInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealEtcTotalInfo(param);
	}
	
	@Override
	public  List<Map<String, Object>> getMealNutriTotalInfo(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getMealNutriTotalInfo(param);
	}		
	
	
	@Override
	public List<Map<String, Object>> getCnslContHist(Map<String, Object> param) throws Exception{
		return intensiveCnslMngtServiceDAO.getCnslContHist(param);
	}

	@Override
	public int updateNutriAllSubmit(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.updateNutriAllSubmit(param);
	}

	
	//20191209 양현우 추가
	@Override
	public List<Map<String, Object>> selectIntensiveCnslMngtListPop(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.selectIntensiveCnslMngtListPop(param);
	}

	@Override
	public int updateIntensiveCnslMngtListPop(Map<String, Object> param)throws Exception {
		return intensiveCnslMngtServiceDAO.updateIntensiveCnslMngtListPop(param);
	}

	@Override
	public List<Map<String, Object>> getAlgoPop(Map<String, Object> param)throws Exception {
		return intensiveCnslMngtServiceDAO.getAlgoPop(param);
	}
	
	@Override
	public List<Map<String, Object>> getAutoAlgoPop(Map<String, Object> param)throws Exception {
		return intensiveCnslMngtServiceDAO.getAutoAlgoPop(param);
	}
	@Override
	public List<Map<String, Object>> getAlgoPopSetting(Map<String, Object> param)throws Exception {
		return intensiveCnslMngtServiceDAO.getAlgoPopSetting(param);
	}
//추가
	@Override
	public Map<String, String> getManagerType(Map<String, Object> param) throws Exception {
		return intensiveCnslMngtServiceDAO.getManagerType(param);
	}
}
