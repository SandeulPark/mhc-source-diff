package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.RecSentMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name : RecSentMngtService.java
 * @Description : 관리자 WEB에서 사용하는 추천문장 관리하는  DAO와 연동 관리하는 class
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

@Service("web.st.RecSentMngtService")
public class RecSentMngtServiceImpl extends EgovAbstractServiceImpl implements  RecSentMngtService{
	
	@Resource(name="web.st.RecSentMngtDAO")
	private RecSentMngtDAO recSentMngtDAO;

	@Override
	public List<Map<String, Object>> getRecSentMngt(Map<String, Object> param)throws Exception {
		return recSentMngtDAO.getRecSentMngt(param);
	}

	@Override
	public int recSentMngtListHist(Map<String, Object> param) throws Exception {
		return recSentMngtDAO.recSentMngtListHist(param);
	}

	@Override
	public List<Map<String, Object>> getSelRecSentMngt(Map<String, Object> param)throws Exception {
		return recSentMngtDAO.getSelRecSentMngt(param);
	}

}
