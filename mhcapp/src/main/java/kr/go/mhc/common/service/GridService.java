package kr.go.mhc.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GridService.java
 * @Description : 그리드샘플 페이지 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.05		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface GridService {	
	
	/**
	 * PK 정보로 count 조회
	 * @param 
	 * @return ROW count 정보 
	 */
	public int getGridTotalRowCount() throws Exception;

	/**
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 단일 ROW 상세 정보 
	 */
	public List<Map<String, String>> getGridDataList(Map<String, Object> param) throws Exception;
	
}
