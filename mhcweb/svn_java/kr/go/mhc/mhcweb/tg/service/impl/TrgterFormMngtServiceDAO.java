package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.tg.TrgterFormMngtServiceDAO")
public class TrgterFormMngtServiceDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, String>> getTrgterFormMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.tg.trgterformmngt.selectTrgterFormMngtList",param);	
		return rsList;
	}
	
	public Map<String, Object> getTrgterFormViewInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterformmngt.selectTrgterFormViewInfo",param);	
		return rsMap;
	}
}
