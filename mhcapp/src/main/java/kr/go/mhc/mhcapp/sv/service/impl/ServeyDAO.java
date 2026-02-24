package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("mhcapp.sv.ServeyDAO")
public class ServeyDAO extends DMultiEgovAbstractMapper{

	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyList", param);	
		return rsList;  
	}
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyCodeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyCodeList", param);	
		return rsList;  
	}
	
	/**
	 * 설문 마지막 코드 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyLstQnaCD(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyLstQnaCD", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 답변 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAwrInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.servey.serveyAwrInsert", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 마스터 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyMasterInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.servey.serveyMasterInsert", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 영양 체크 리스트 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAnswrUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.servey.serveyAnswrUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 마스터 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int updateServeyMaster(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.servey.updateServeyMaster", param);

		// 최종설문 완료 시 최종검진 미수검 유무 확인 후 졸업처리
		// param.SN > 3 (최종설문) 일 때 SESS_SVC_MNGT_NO 로 최종검진 미수검 유무 조회. 미수검 처리 완료 시 졸업처리 210924
		if(param.get("SN").equals("3")){
			Map<String,String> rsMap = selectOne("mhcapp.sv.servey.selectFinNoExam", param);
			if(rsMap.get("FIN_NO_EXAM_SET_YN").equals("Y")){
				// 졸업
				param.put("USER_ID", rsMap.get("USER_ID"));
				param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));

				update("mhcapp.sv.servey.updateTrgterGraduation", param);
			}
		}
		return rsList;  
	}
	
	/**
	 * 설문 답변 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyAnwerList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyAnwerList", param);
		return rsList;
	}

	/**
	 * 설문지 답변 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public void serveyAwrDel(Map<String, Object> param) {
		// TODO Auto-generated method stub
		delete("mhcapp.sv.servey.deleteServeyAnwer", param);
	}

	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) {
		List<Map<String,Object>> rsList = selectList("mhcapp.sv.servey.serveyResearchAnswrList", param);
		return rsList;
	}

	public List<Map<String, Object>> serveyResearchAnswrMastr(Map<String, Object> param) {
		List<Map<String,Object>> rsList = selectList("mhcapp.sv.servey.serveyResearchAnswrMastr", param);
		return rsList;
	}

	/**
	 * 설문답변 마스터 정보 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrMastrInsert(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhcapp.sv.servey.serveyResearchAnswrMastrInsert", param);
		return rsInt;
	}

	/**
	 * 설문항목 답변 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrDel(Map<String, Object> param) throws Exception{
		int rsInt = delete("mhcapp.sv.servey.serveyResearchAnswrDel", param);
		return rsInt;
	}

	/**
	 * 설문항목 답변 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhcapp.sv.servey.serveyResearchAnswrInsert", param);
		return rsInt;
	}

}
