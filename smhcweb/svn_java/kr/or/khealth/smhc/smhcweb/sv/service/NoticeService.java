package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : NoticeService.java
 * @Description : 관리자 WEB에서 사용하는 긴급 공지 사항 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.08.04		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.08.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface NoticeService {
	
	int selectNoticeListCount(Map<String, Object> param) throws Exception;

	List<Map<String, String>> selectNoticeList(Map<String, Object> param) throws Exception;

	Map<String, Object> noticeDupChk(Map<String, Object> param) throws Exception;

	int noticeReg(Map<String, Object> param) throws Exception;

	Map<String, Object> selectNoticeDetailData(Map<String, Object> param) throws Exception;

	Map<String, Object> selectNoticeData(Map<String, Object> param) throws Exception;

	int noticeUpdate(Map<String, Object> param) throws Exception;

	int deleteNotice(Map<String, Object> param) throws Exception;
	


}
