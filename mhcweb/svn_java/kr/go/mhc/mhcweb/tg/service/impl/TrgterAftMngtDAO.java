package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterAftMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.02		나연이			최초생성
 *
 * @author thejoin
 * @since 2018.10.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.TrgterAftMngtDAO")
public class TrgterAftMngtDAO extends EgovAbstractMapper{
	
	/**
	 * 추후관리 대상자 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgterAftMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgteraftmngt.selectTrgterAftMngtList", param);
		return rsList;
	}
	
	/**
	 * 추후관리 대상자 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> trgterAftMngtDtls(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgteraftmngt.selectTrgterAftMngtDtls", param);
		return rsMap;
	}
	
	/**
	 * 추후관리 대상자 활동량 및 컨텐츠 정보 조회	
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgterAftMngtInfo(Map<String, Object> param) throws Exception{
		String id = param.get("id") == null ? "" : param.get("id").toString();
		String sql = "cbGrid1".equals(id) ? "ActUseDe" : ("cbGrid2".equals(id) ? "ActMeasrDe" : "ContCnfm");
		
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgteraftmngt.select"+sql+"List", param);
		return rsList;
	}
	
	/**
	 * 추후관리 스케줄 생성
	 * @param param
	 * @return
	 */
	public void createAfterSchedule(Map<String, Object> param) throws Exception{
		insert("mhc.web.tg.trgteraftmngt.createAfterSchedule", param);
		update("mhc.web.tg.trgteraftmngt.updateAftMngtEndYn", param);
	}
	
	/**
	 * 추후관리 종료 여부 업데이트
	 * @param param
	 * @throws Exception
	 */
	public void updateAftMngtEndYn(Map<String, Object> param) throws Exception{
		update("mhc.web.tg.trgteraftmngt.updateAftMngtEndYn", param); 
	}
	
	/**
	 * 추후관리 서비스 제공 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgterAfterMngtSchList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgteraftmngt.selectAfterMngtSchList", param);
		return rsList;
	}
	
	/**
	 * 추후관리 서비스 종료
	 * @param param
	 * @throws Exception
	 */
	public void trgterAftMngtEnd(Map<String, Object> param) throws Exception{
		delete("mhc.web.tg.trgteraftmngt.trgterAftMngtEnd", param);
		update("mhc.web.tg.trgteraftmngt.updateAftMngtEndYn", param); 
	}	
}
