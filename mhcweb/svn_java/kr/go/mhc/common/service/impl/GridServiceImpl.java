package kr.go.mhc.common.service.impl;


import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.service.GridService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :GridServiceImpl.java
 * @Description : 그리드샘플 페이지에 필요한 DAO와 연동 관리하는 Class
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
@Service("GridService")
public class GridServiceImpl extends EgovAbstractServiceImpl implements GridService{
	
	@Resource(name="GridDAO")
    private GridDAO gridDAO;
	
	@Override
	public int getGridTotalRowCount() throws Exception {
		
		return gridDAO.getGridTotalRowCount();
	}	
	
	@Override
	public List<Map<String, String>> getGridDataList(Map<String, Object> param)
			throws Exception {
		return gridDAO.getGridDataList(param);
	}
}
