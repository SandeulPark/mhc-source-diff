package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : GridDAO.java
 * @Description : 그리드샘플 페이지 DataBase 연동 관리하는 Class
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
@Repository("GridDAO")
public class GridDAO extends DMultiEgovAbstractMapper{
	
	public int getGridTotalRowCount()	throws Exception {
				
		int rsList = selectOne("mhc.common.grid.gridDataCount");	
		
		return rsList;  
	}
	
	public List<Map<String, String>> getGridDataList(Map<String, Object> param) throws Exception {
				
		List<Map<String,String>> rsList = selectList("mhc.common.grid.gridDataList", param);	

		return rsList;  
	}
}
