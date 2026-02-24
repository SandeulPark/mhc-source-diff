package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface PhisPreTrgterMngtService {

	public List<Map<String, String>> phisPreTrgterRegitList(Map<String, Object> param) throws Exception;

	public Map<String, Object> phisPreTrgterRegitExamInfo(Map<String, Object> param) throws Exception;

	public Map<String, Object> phisNewPreTrgterRegit(Map<String, Object> param) throws Exception;

	public Map<String, Object> phisHealthExamRsltPop(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectUserPhisExamRslt(Map<String, Object> param) throws Exception;

	public Map<String, Object> regitExamPhisRslt(Map<String, Object> param) throws Exception;

	
}
