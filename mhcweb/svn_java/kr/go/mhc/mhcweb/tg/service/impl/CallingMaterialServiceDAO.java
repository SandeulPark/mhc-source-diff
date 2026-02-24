package kr.go.mhc.mhcweb.tg.service.impl;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("web.tg.CallingMaterialServiceDAO")
public class CallingMaterialServiceDAO extends DMultiEgovAbstractMapper{

	List<Map<String, String>> getCallingMaterialList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		if("Y".equals(param.get("EXCELEXPORT")) ) {
			String callingSn[] = param.get("CALLING_SN").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
			List callingSnList = new ArrayList();
			for (int i = 0; i < callingSn.length; i++) {
				callingSnList.add(callingSn[i]);
			}
			param.put("CALLING_SN", callingSnList);
		}
		List<Map<String, String>> rsList = selectList("mhc.web.tg.callingmaterial.selectCallingMaterialList",param);
		return rsList;
	}

	Map<String, String> getCallingMaterialEachOtherCnt(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.callingmaterial.selectCallingMaterialEachOtherCnt",param);
		return rsMap;
	}

	int callingMaterialExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		int dataCount = param.size();
		int InsertCount =0;
		for(int i=0; i < dataCount; i++){
			insert("mhc.web.tg.callingmaterial.insertCallingMaterial", param.get(i));
			InsertCount ++;
		}
		return InsertCount;
	}

	int updateCallingMaterInfo(Map<String, Object> param) throws Exception {
		return insert("mhc.web.tg.callingmaterial.updateCallingMaterInfo", param);
	}

	int deleteCallingMaterInfo(Map<String, Object> param) throws Exception {
		String callingSn[] = param.get("CALLING_SN").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		List callingSnList = new ArrayList();
		for(int i=0; i < callingSn.length; i++) {
			callingSnList.add(callingSn[i]);
		}
		param.put("CALLING_SN", callingSnList);

		return delete("mhc.web.tg.callingmaterial.deleteCallingMaterInfo", param);
	}

	int callingMaterialDupCheck(Map<String, Object> param) throws Exception {
		return selectOne("mhc.web.tg.callingmaterial.callingMaterialDupCheck", param);
	}
}
