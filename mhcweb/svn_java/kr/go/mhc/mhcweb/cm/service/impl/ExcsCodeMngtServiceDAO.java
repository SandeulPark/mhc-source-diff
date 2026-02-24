package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import kr.go.mhc.common.DMultiEgovAbstractMapper;

@Repository("web.cm.ExcsCodeMngtServiceDAO")
public class ExcsCodeMngtServiceDAO extends DMultiEgovAbstractMapper {

	/**
	 * 운동코드 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExcsCodeList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.excscodemngt.selectExcsCodeList", param);	
		return rsList;  
	}
	
	/**
	 * 운동코드 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertExcsCode(Map<String, Object> param) throws Exception{
		int rsInt;

		Map<String, String> rsMap = selectOne("mhc.web.cm.excscodemngt.selectNewExcsCd", param);
		param.put("EXCS_CD", rsMap.get("EXCS_CD"));
		rsInt = insert("mhc.web.cm.excscodemngt.insertExcsCode", param);
		return rsInt;
	}

	
	/**
	 * 운동코드 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateExcsCode(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.cm.excscodemngt.updateExcsCode", param);	
		return rsInt;
	}	

	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateExcsCodeApprovalYn(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.cm.excscodemngt.updateExcsCodeApprovalYn", param);			
		return rsInt;		
		
	}
	
}
