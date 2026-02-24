package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.tg.MeasrDataMngtDAO")
public class MeasrDataMngtDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> getTrgterDuplicationChkList(Map<String, Object> param)throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.measrdatamngt.selectTrgterDuplicationChkList",param);
		return rsList;
	}
	
	public List<Map<String, String>> getTrgterBodyCompDataList(Map<String, Object> param)throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.measrdatamngt.selectTrgterBodyCompDataList",param);
		return rsList;
	}
	
	public List<Map<String, String>> getTrgterBloodSugarDataList(Map<String, Object> param)throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.measrdatamngt.selectTrgterBloodSugarDataList",param);
		return rsList;
	}
	
	public List<Map<String, String>> getBodyCompDataDel(Map<String, Object> param)throws Exception {
		insert("mhc.web.tg.measrdatamngt.insertMeasrMastrHis",param);
		insert("mhc.web.tg.measrdatamngt.insertBodyCompHis",param);
		delete("mhc.web.tg.measrdatamngt.deleteMeasrMastrData",param);
		delete("mhc.web.tg.measrdatamngt.deleteBodyCompData",param);
		List<Map<String, String>> rsList = selectList("mhc.web.tg.measrdatamngt.selectTrgterBodyCompDataList",param);
		return rsList;
	}
	
	public List<Map<String, String>> getBloodSugarDataUp(Map<String, Object> param)throws Exception {
		update("mhc.web.tg.measrdatamngt.updateBloodSugarData",param);
		List<Map<String, String>> rsList = selectList("mhc.web.tg.measrdatamngt.selectTrgterBloodSugarDataList",param);
		return rsList;
	}

}
