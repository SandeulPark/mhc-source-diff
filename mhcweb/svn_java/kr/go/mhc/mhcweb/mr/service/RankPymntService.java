package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : RankPymntService.java
 * @Description : 관리자 WEB에서 사용하는 배송및지급 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.28		이은주			최초생성
 *
 * @author gst
 * @since 2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface RankPymntService {

	//배송 및 지급 보건기관 목록
	public List<Map<String, Object>> orgCdList(Map<String, Object> param) throws Exception;
	
	//배송 및 지급 목록조회
	public List<Map<String, Object>> rankPymntList(Map<String, Object> param) throws Exception;
	
	//배송 및 지급 목록 count
	public Map<String, Object> rankPymntCnt(Map<String, Object> param) throws Exception;
	
	//배송 및 지급 배송완료 업데이트
	public void updatePymnt(Map<String, Object> param) throws Exception;
	
	//배송 및 지급 건강포인트 내역
	public List<Map<String, Object>> healthPointList(Map<String, Object> param) throws Exception;
	
	//배송 및 지급 수상내역
	public List<Map<String, Object>> awardList(Map<String, Object> param) throws Exception;
}
