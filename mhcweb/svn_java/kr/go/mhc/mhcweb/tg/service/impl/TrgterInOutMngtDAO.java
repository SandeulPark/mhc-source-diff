package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterInOutMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자 전입/전출 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.11.06		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.11.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.TrgterInOutMngtDAO")
public class TrgterInOutMngtDAO extends EgovAbstractMapper{

	/**
	 * 대상자 전입/전출 관리 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTrgterInOutMngtList(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.tg.trgterinoutmngt.selectTrgterInOutMngtList", param);	
		return rsList;  		
	}
	
	/**
	 * 전입 대상자 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTrgterInInfoChk(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhc.web.tg.trgterinoutmngt.selectTrgterInInfoChk", param);	
		return rsMap;  		
	}	
	
	/**
	 * 대상자 전입 요청 정보 신규 입력
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int insertTrgterInReqInfo(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhc.web.tg.trgterinoutmngt.insertTrgterInReqInfo", param);

		return rsInt;
	}
	
	/**
	 * 대상자 전입 요청 정보 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int updateTrgterInReqInfo(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.tg.trgterinoutmngt.updateTrgterInReqInfo", param);
		return rsInt;		
	}
	
	/**
	 * 대상자 전입 요청 승인 완료(프로시저 호출)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateTrgterInApprovalYn(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhc.web.tg.trgterinoutmngt.CALL_PRC_TN_CM_TRGTER_IN_OUT_UPD", param);
		return rsMap;
	}
}
