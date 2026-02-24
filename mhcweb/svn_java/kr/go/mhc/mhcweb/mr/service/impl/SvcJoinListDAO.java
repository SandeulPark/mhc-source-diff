package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SvcJoinListDAO.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무 DataBase 연동 관리하는 Class
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

@Repository("web.mr.SvcJoinListDAO")
public class SvcJoinListDAO extends EgovAbstractMapper {
	
	/**
	 * 서비스 참여목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectSvcJoinList(Map<String,Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.mr.svcjoinlist.selectSvcJoinList", param); 
		return rsList;
	}
	
	
}
