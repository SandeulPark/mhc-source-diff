package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.cm.EduVideosMngtDAO")
public class EduVideosMngtDAO extends DMultiEgovAbstractMapper {
	
	public List<Map<String, String>> getEduVideosMngtList(Map<String, Object> param) throws Exception {		
		
		System.out.println();
		
		List<Map<String,String>> rsList = selectList("mhc.web.cm.eduvideosmngt.selectEduVideosMngtList", param);	
		return rsList;  
	}
	
	public Map<String, String> getEduVideosDtls(Map<String, Object> param) throws Exception {	
		
		Map<String,String> rsMap = selectOne("mhc.web.cm.eduvideosmngt.selectEduVideosDtls",param);	

		return rsMap;  
	}
	
	public void getEduVideosMngtRegInsert(Map<String, Object> param) throws Exception {

		insert("mhc.web.cm.eduvideosmngt.insertEduVideos", param);	
		  
	}
	
	public void getEduVideosMngtDel(Map<String, Object> param) throws Exception {
		
		update("mhc.web.cm.eduvideosmngt.deleteEduVideos", param);	
		
	}

}
