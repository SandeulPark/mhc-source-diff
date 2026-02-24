package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.WorkHistInfoService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : WorkHistInfoServiceImpl.java
 * @Description : 업무 이력 정보를 조회하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.15		오샘이			최초생성
 *
 * @author theJoin
 * @since 2019.10.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Service("web.st.WorkHistInfoService")
public class WorkHistInfoServiceImpl implements WorkHistInfoService{
	
	@Resource(name="web.st.WorkHistInfoServiceDAO")
	private WorkHistInfoServiceDAO workHistInfoServiceDAO;

	@Override
	public List<Map<String, String>> workHistConInfoList(Map<String, Object> param) throws Exception {
		return workHistInfoServiceDAO.workHistConInfoList(param);
	}

	@Override
	public List<Map<String, String>> workHistPerSchInfoList(Map<String, Object> param) throws Exception {
		return workHistInfoServiceDAO.workHistPerSchInfoList(param);

	}

	@Override
	public int workHistConInfoListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return workHistInfoServiceDAO.workHistConInfoListCount(param);
	}

	@Override
	public int workHistPerSchInfoListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return workHistInfoServiceDAO.workHistPerSchInfoListCount(param);
	}

	@Override
	public List<Map<String, String>> workHistDownloadInfoList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return workHistInfoServiceDAO.workHistDownloadInfoList(param);
	}

	@Override
	public int workHistDownloadInfoListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return workHistInfoServiceDAO.workHistDownloadInfoListCount(param);
	}
	
}
