package kr.go.mhc.mhcapp.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.mr.service.AppGeneralCnslService;
import kr.go.mhc.mhcapp.mr.service.AppSportActivityService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : AppSportActivityServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 스포츠활동인증에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.11.25		윤찬호				최초생성
 *
 * @author chyoon
 * @since 2021.11.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.mr.AppSportActivityService")
public class AppSportActivityServiceImpl extends EgovAbstractServiceImpl implements AppSportActivityService{
	
	@Resource(name="mhcapp.mr.AppSportActivityDAO")
    private AppSportActivityDAO sportsDAO;

	@Override
	public int insertSportActivityUserInfo(Map<String, Object> param) throws Exception {		
		return sportsDAO.insertSportActivityUserInfo(param);
	}

	@Override
	public Map<String, Object> chkExistSportActivityUserInfo(Map<String, Object> param) throws Exception {
		return sportsDAO.chkExistSportActivityUserInfo(param);
	}

	@Override
	public void updateSportActivityUserAgree(Map<String, Object> param) throws Exception {
		sportsDAO.updateSportActivityUserAgree(param);
	}

	@Override
	public void updateSportActivityCertWrite(Map<String, Object> param) throws Exception {
		sportsDAO.updateSportActivityCertWrite(param);
		
	}

	@Override
	public void delUserInfo(Map<String, Object> param) throws Exception {
		sportsDAO.delUserInfo(param);		
	}

	@Override
	public Map<String, Object> getSportActivityWalkCnt(Map<String, Object> param) throws Exception {
		return sportsDAO.getSportActivityWalkCnt(param);
	}

	@Override
	public Map<String, Object> getSportActivityMeasureYmd(Map<String, Object> param) throws Exception {		
		return sportsDAO.getSportActivityMeasureYmd(param);
	}

	@Override
	public Map<String, Object> userInfo(String param) throws Exception {		
		return sportsDAO.userInfo(param);
	}

	@Override
	public List<Map<String, Object>> chkGnGroupActivityUserInfo(String param) throws Exception {
		return sportsDAO.chkGnGroupActivityUserInfo(param);		
	}

	
}
