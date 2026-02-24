package kr.go.mhc.mhcweb.tg.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("web.tg.TrgterCmplMngtDAO")
public class TrgterCmplMngtDAO extends EgovAbstractMapper{
	//대상자정보관리 대상자 목록 조회
	public List<Map<String, Object>> trgterCmplMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgtercmplmngt.trgterCmplMngtList", param);
		return rsList;
	}
}
