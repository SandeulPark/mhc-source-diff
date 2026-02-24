package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface CallingMaterialService {

	List<Map<String, String>> getCallingMaterialList(Map<String, Object> param)throws Exception;

	Map<String, String> getCallingMaterialEachOtherCnt(Map<String, Object> param) throws Exception;

	int callingMaterialExcelGridInsert(List<Map<String, Object>> param) throws Exception;

	int updateCallingMaterInfo(Map<String, Object> param) throws Exception;

	int deleteCallingMaterInfo(Map<String, Object> param) throws Exception;

	int callingMaterialDupCheck(Map<String, Object> param) throws Exception;
}
