package kr.go.mhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.service.CommonService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : CommonServiceImpl.java
 * @Description : 모바일 헬스케어에서 사용하는 통합공통업무에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("common.cmmnService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService{
	
	@Resource(name="common.cmmnDAO")
    private CommonDAO cmmnDAO;

	@Override
	public List<Map<String, String>> selectCmmnCd(Map<String, Object> param)
			throws Exception {
		
		return cmmnDAO.selectCmmnCd(param);
	}
	
	@Override
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param)
			throws Exception {
		
		return cmmnDAO.selectCmmnMenu(param);
	}
	
	@Override
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param)
			throws Exception {
		
		return cmmnDAO.selectCmmnMenuInfo(param);
	}

	@Override
	public String selectAttchFileSnSeq() throws Exception {
		// TODO Auto-generated method stub
		return cmmnDAO.selectAttchFileSnSeq();
	}

	@Override
	public String selectAttchFileDtlsSn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return cmmnDAO.selectAttchFileDtlsSn(param);
	}

	@Override
	public int insertAttchFile(List<Map<String,String>> fileList) throws Exception {
		// TODO Auto-generated method stub
		return cmmnDAO.insertAttchFile(fileList);
	}

	@Override
	public int deleteAttchFileInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cmmnDAO.deleteAttchFileInfo(param);
	}

	@Override
	public int updateAttchFileUseYn(Map<String, Object> param) throws Exception {
		return cmmnDAO.updateAttchFileUseYn(param);
	}

	@Override
	public List<Map<String, Object>> selectAttchFile(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return cmmnDAO.selectAttchFile(param);
	}

	@Override
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cmmnDAO.insertCmmnLogInfo(param);
	}
	
	@Override
	public void updatePairingLogInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cmmnDAO.updatePairingLogInfo(param);
	}

	@Override
	public void insertAppErrRport(Map<String,String> fileList) throws Exception {
		// TODO Auto-generated method stub
		cmmnDAO.insertAppErrRport(fileList);
	}
	
	@Override
	public Map<String, String> selectAppErrRport(Map<String, String> param) throws Exception {
		return cmmnDAO.selectAppErrRport(param);
	}

	@Override
	public Map<String, String> selectAppErrRportGnUser(Map<String, String> param) throws Exception {
		return cmmnDAO.selectAppErrRportGnUser(param);
	}

	@Override
	public void insertAttchFile(Map<String,String> fileList) throws Exception {
		// TODO Auto-generated method stub
		cmmnDAO.insertAttchFile(fileList);
	}
}
