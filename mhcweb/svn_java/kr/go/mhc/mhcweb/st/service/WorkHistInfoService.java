package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : WorkHistInfoService.java
 * @Description : 업무 이력 정보를 조회하는 Interface
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

public interface WorkHistInfoService {

	public List<Map<String, String>> workHistConInfoList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> workHistPerSchInfoList(Map<String, Object> param) throws Exception;

	public int workHistConInfoListCount(Map<String, Object> param) throws Exception;
	
	public int workHistPerSchInfoListCount(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> workHistDownloadInfoList(Map<String, Object> param) throws Exception;

	public int workHistDownloadInfoListCount(Map<String, Object> param) throws Exception;

}
