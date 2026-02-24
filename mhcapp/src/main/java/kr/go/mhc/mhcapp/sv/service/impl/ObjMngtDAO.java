package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : ObjMngtDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 영양평가결과 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2016.08.12		오명빈				최초생성
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.ObjMngtDAO")
public class ObjMngtDAO extends DMultiEgovAbstractMapper{
	

	/**
	 * 목표관리_상담 일자 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectObjCnsl(Map<String,Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.objmngt.selectObjCnsl", param);
		return rsList;
	}
	
	/**
	 * 목표관리_영양 상담 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjNutMngt(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhcapp.sv.objmngt.selectObjNutMngt", param);
		return rsList;
	}
	
	/**
	 * 식품섭취군별 권장 섭취 횟수 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectIntakeStnd(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhcapp.sv.objmngt.selectIntakeStnd", param);
		return rsList;
	}
	
	/**
	 * 목표관리_신체활동 상담 및 목표 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectObjMngtBodyActInfo(Map<String, Object> param) throws Exception {
		return selectOne("mhcapp.sv.objmngt.selectObjMngtBodyActInfo", param);
	}
	
	/**
	 * 목표관리_건강관리 상담 및 목표 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectObjMngtHealthMngtInfo(Map<String, Object> param) throws Exception {
		return selectOne("mhcapp.sv.objmngt.selectObjMngtHealthMngtInfo", param);
	}
	
	/**
	 * 목표관리_신체활동 근력 운동 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjMngtBodyPartExcsInfo(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhcapp.sv.objmngt.selectObjMngtBodyPartExcsInfo", param);
		return rsList;
	}
	
	/**
	 * 근력 운동 상세 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBodyPartExcsInfo(Map<String,Object> param) throws Exception{
		return selectOne("mhcapp.sv.objmngt.selectBodyPartExcsInfo", param);
	}
}
