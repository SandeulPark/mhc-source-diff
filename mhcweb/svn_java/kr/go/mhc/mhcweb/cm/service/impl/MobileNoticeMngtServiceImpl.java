package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.cm.service.MobileNoticeMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :MobileNoticeMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 모바일 공지 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.10		이은주			최초생성
 
 * @author gst
 * @since 2016.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.cm.MobileNoticeMngtService")
public class MobileNoticeMngtServiceImpl extends EgovAbstractServiceImpl implements MobileNoticeMngtService {
	
	@Resource(name="web.cm.MobileNoticeMngtDAO")
	private MobileNoticeMngtDAO mobileNoticeMngtDAO;

	@Override
	public int getMobileNoticeMngtListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.getMobileNoticeMngtListCount(param);
	}

	@Override
	public List<Map<String, String>> getMobileNoticeMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.getMobileNoticeMngtList(param);
	}

	@Override
	public List<Map<String, String>> getMobileNoticeReg(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.getMobileNoticeReg(param);
	}
	
	@Override
	public void getMobileNoticeRegInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mobileNoticeMngtDAO.getMobileNoticeRegInsert(param);
	}

	@Override
	public Map<String, String> getMobileNoticeDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.getMobileNoticeDtls(param);
	}

	@Override
	public List<Map<String, String>> getBefAfMobileNoticeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.getBefAftMobileNoticeList(param);
	}

	@Override
	public void getMobileNoticeMngtUp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mobileNoticeMngtDAO.getMobileNoticeMngtUp(param);
	}

	@Override
	public void getMobileNoticeMngtDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mobileNoticeMngtDAO.getMobileNoticeMngtDel(param);
	}

	@Override
	public void getMobileNoticeMngtPostUp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mobileNoticeMngtDAO.getMobileNoticeMngtPostUp(param);
	}

	@Override
	public Map<String, String> mobileNoticeMngtInsertNumber(Map param) throws Exception {
		// TODO Auto-generated method stub
		return mobileNoticeMngtDAO.mobileNoticeMngtInsertNumber(param);
	}

	
}
