package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcJoinListService.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.06.20		나연이			최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SvcJoinListService {

	/**
	 * 서비스 참여목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectSvcJoinList(Map<String,Object> param) throws Exception;

}
