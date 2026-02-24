package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthDisorderInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강 이상 정보 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.09.19		이현규		최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.HealthDisorderInfoDAO")
public class HealthDisorderInfoDAO extends DMultiEgovAbstractMapper {
	
	public List<Map<String, String>> selectHealthDisorderInfoList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.mr.healthdisorderinfo.selectHealthDisorderInfoList", param);
		return rsList;  
	}
}
