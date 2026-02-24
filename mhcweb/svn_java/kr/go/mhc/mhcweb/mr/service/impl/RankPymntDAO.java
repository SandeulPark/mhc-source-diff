package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.hsqldb.lib.HashMap;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
  * @Class Name : RankPymntDAO.java
 * @Description : 관리자 WEB에서 사용하는 배송및지급 업무 DataBase 연동 관리하는 Class
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

@Repository("web.mr.RankPymntDAO")
public class RankPymntDAO extends EgovAbstractMapper {
	
	//배송 및 지급 보건기관 목록
	public List<Map<String, Object>> orgCdList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.rankpymnt.orgCdList", param);
		return rsList;
	}
	
	//배송 및 지급 목록 조회
	public List<Map<String, Object>> rankPymntList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.rankpymnt.rankPymntList", param);
		return rsList;
	}
	
	//배송 및 지급 목록 COUNT
	public Map<String, Object> rankPymntCnt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.mr.rankpymnt.rankPymntCnt", param);
		return rsMap;
	}
	
	//배송 및 지급 배송완료 업데이트
	public void updatePymnt(Map<String, Object> param) throws Exception {
		String[] al_user_id = param.get("USER_ID_s").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] al_rank_sn = param.get("RANK_SN_s").toString().replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		
		for(int i=0; i<al_user_id.length; i++) {
			param.put("USER_ID", al_user_id[i]);
			param.put("RANK_SN", al_rank_sn[i]);
			update("mhc.web.mr.rankpymnt.updatePymnt", param);			
		}
	}
	
	//배송 및 지급 건강포인트 내역
	public List<Map<String, Object>> healthPointList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.rankpymnt.healthPointList", param);
		return rsList;
	}
	
	//배송 및 지급 수상내역
	public List<Map<String, Object>> awardList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.rankpymnt.awardList", param);
		return rsList;
	}
}
