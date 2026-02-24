package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : WorkHistInfoServiceDAO.java
 * @Description : 업무 이력 정보를 조회하는 DAO
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


@Repository("web.st.WorkHistInfoServiceDAO")
public class WorkHistInfoServiceDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> workHistConInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.st.workhistinfo.workHistConInfoList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> workHistPerSchInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.st.workhistinfo.workHistPerSchInfoList",param);
		return rsList;
	}

	public int workHistConInfoListCount(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.st.workhistinfo.workHistConInfoListCount", param);
		return rsList;
	}

	public int workHistPerSchInfoListCount(Map<String, Object> param) {
		int rsList = selectOne("mhc.web.st.workhistinfo.workHistPerSchInfoListCount", param);
		return rsList;
	}

	public List<Map<String, String>> workHistDownloadInfoList(Map<String, Object> param) {
		List<Map<String, String>> rsList = selectList("mhc.web.st.workhistinfo.workHistDownloadInfoList",param);
		return rsList;
	}

	public int workHistDownloadInfoListCount(Map<String, Object> param) {
		int rsList = selectOne("mhc.web.st.workhistinfo.workHistDownloadInfoListCount", param);
		return rsList;
	}	
	
}
