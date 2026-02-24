package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : InputHealthInfoCnfmDAO.java
 * @Description : 관리자 WEB에서 사용하는 게시판 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.InputHealthInfoCnfmDAO")
public class InputHealthInfoCnfmDAO extends DMultiEgovAbstractMapper {

	/**
	 * 자가입력 건강정보 확인 대상자 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectTrgterInfoList", param);
		return rsList;  
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동목표 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterActList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterActList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 누적,평균 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterTotActCnt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterTotActCnt", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 서비스 주차별 현황 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterWKInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterWKInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 일자별 측정 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayActDataList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDayActDataList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 날짜별, 요일별 차트 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterActDEList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterActDEList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동  요일별 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterActDYList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterActDYList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인  신체활동 일자별 평균 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterActDEAVG(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterActDEAVG", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 체중목표 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterWeightList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterWeightList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 서비스 주차별 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterWeightWKInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterWeightWKInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 일자별 측정 정보 조회 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayBodyCompDataList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDayBodyCompDataList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 일자별 측정값 변화 차트 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterWeightDEList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterWeightDEList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 시작, 종료 비교 테이블 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterWeightSTED(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterWeightSTED", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 최근 측정 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterBldPress(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterBldPress", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 측정값 변화 차트 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterBldPressDEList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterBldPressDEList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 평균, 최초측정 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterBldPressSTAVG(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterBldPressSTAVG", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 서비스 주차별 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterBldWKInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterBldWKInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 이상 측정정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selPressDisorderExamInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selPressDisorderExamInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 일자별 측정 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayBloodPressDataList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDayBloodPressDataList", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 최근측정 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterBldSugar(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterBldSugar", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 측정값 변화 차트 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterBldSugarDEList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterBldSugarDEList", param);
		return rsList;
	}

	/**
	 * 자가입력 건강정보 확인 혈당 평균, 최초평균 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selTrgterBldSugarSTAVG(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.mr.inputhealthinfocnfm.selTrgterBldSugarSTAVG", param);
		return rsMap;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 서비스 주차별 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTrgterBldSugarWKInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selTrgterBldSugarWKInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 이상 측정정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selSugarDisorderExamInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selSugarDisorderExamInfo", param);
		return rsList;
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 일자별 측정 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayBloodSugarDataList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDayBloodSugarDataList", param);
		return rsList;
	}

	/**
	 * 자가입력 건강정보 확인 복약  복용약 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDrugInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDrugInfoList", param);
		return rsList;
	}

	/**
	 * 자가입력 건강정보 확인 복약 일자별 복용 현황 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDrugMissionAnswerList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.inputhealthinfocnfm.selectDrugMissionAnswerList", param);
		return rsList;
	}
}
