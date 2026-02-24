package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : GnrlTutorialDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 튜토리얼에 DataBase 연동 관리하는 Class
 * @Modification Information @ @ 수정일 수정자 수정내용 
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.10.29		chyoon			최초생성
 *
 * @author chyoon
 * @since 2021.10.29
 * @version 1.0
 * @see Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.gn.gnrlTutorialDAO")
public class GnrlTutorialDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, String>> checkTutoYn(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhcapp.gn.tutorial.checkTutoYn", param);	
		return rsList; 
	}

	public void updateTutoYn(Map<String, Object> param) throws Exception {
		update("mhcapp.gn.tutorial.updateTutorialCnfmYn", param);
	}

	public void resetTutorial(Map<String, Object> param) throws Exception {
		update("mhcapp.gn.tutorial.resetTutorial", param);		
	}

	public List<Map<String, String>> tutoUseYn(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhcapp.gn.tutorial.tutoUseYn", param);	
		return rsList;
	}

}
