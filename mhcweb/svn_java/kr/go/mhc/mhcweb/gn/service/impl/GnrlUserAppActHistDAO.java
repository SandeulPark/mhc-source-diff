package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;



/**
 * @Class Name : GnrlUserInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자 상태 정보 변경 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlUserAppActHistDAO")
public class GnrlUserAppActHistDAO extends DMultiEgovAbstractMapper{
		
	
	public List<Map<String,Object>> trgtAppActList(Map<String,Object>param) throws Exception{
		List<Map<String,Object>> rsMap = selectList("mhc.web.gn.gnrluserinfomngt.trgtAppActList", param);
		return rsMap;
	}
	
	public int trgtAppActListCount(Map<String, Object> param) throws Exception {		
		int rsInt = selectOne("mhc.web.gn.gnrluserinfomngt.trgtAppActListCount",param);	
		return rsInt;  
	}
}
