package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : RecSentMngtService.java
 * @Description : 관리자 WEB에서 사용하는 추천문장 관리하는  DAO class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.04.13		양현우 		최초생성
 * @author theJoin
 * @since 2020.04.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.st.RecSentMngtDAO")
public class RecSentMngtDAO  extends DMultiEgovAbstractMapper{

	/**
	 * 추천 문장 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRecSentMngt(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsMap = selectList("mhc.web.st.recSentMngt.recSentMngtList", param);
		return rsMap;  
	}
	/**
	 * 추천 문장 히스트 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	
	public int recSentMngtListHist(Map<String, Object> param) throws Exception{
		int rsInt= insert("mhc.web.st.recSentMngt.InsertRecSentMngtHist", param);
			rsInt+=update("mhc.web.st.recSentMngt.updRecSentMngt", param);		
		
		return rsInt;
	}
	
	public List<Map<String,Object>> getSelRecSentMngt(Map<String,Object>param) throws Exception{
		List<Map<String,Object>> rsMap = selectList("mhc.web.st.recSentMngt.selRecSentMngtList", param);
		return rsMap;
	}
	
}
