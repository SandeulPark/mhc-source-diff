package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.InputHealthInfoCnfmService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :InputHealthInfoCnfmServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 게시판에 필요한 DAO와 연동 관리하는 Class
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

@Service("web.mr.InputHealthInfoCnfmService")
public class InputHealthInfoCnfmServiceImpl extends EgovAbstractServiceImpl implements InputHealthInfoCnfmService {

	@Resource(name="web.mr.InputHealthInfoCnfmDAO")
	private InputHealthInfoCnfmDAO inputHealthInfoCnfmDAO;
	

	/**
	 * 자가입력 건강정보 확인 대상자 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> selectTrgterInfoList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selectTrgterInfoList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동목표 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterActList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterActList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 누적,평균 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterTotActCnt(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterTotActCnt(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 서비스 주차별 현황 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterWKInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterWKInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 일자별 측정 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selectDayActDataList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selectDayActDataList(param);
	}

	/**
	 * 자가입력 건강정보 확인 신체활동 활동량 날짜별, 요일별 차트 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterActDEList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterActDEList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 신체활동  요일별 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterActDYList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterActDYList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인  신체활동 일자별 평균 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterActDEAVG(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterActDEAVG(param);
	}
	/**
	 * 자가입력 건강정보 확인 체성분 체중목표 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterWeightList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterWeightList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 서비스 주차별 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterWeightWKInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterWeightWKInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 일자별 측정 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selectDayBodyCompDataList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selectDayBodyCompDataList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 일자별 측정값 변화 차트 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterWeightDEList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterWeightDEList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 체성분 시작, 종료 비교 테이블 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterWeightSTED(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterWeightSTED(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 최근 측정 현황 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterBldPress(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldPress(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 측정값 변화 차트 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterBldPressDEList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldPressDEList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 평균, 최초측정 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterBldPressSTAVG(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldPressSTAVG(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 서비스 주차별 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterBldWKInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldWKInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 이상 측정정보 조회
	 */
	@Override
	public List<Map<String, Object>> selPressDisorderExamInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selPressDisorderExamInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈압 일자별 측정 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selectDayBloodPressDataList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selectDayBloodPressDataList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 최근측정 현황 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterBldSugar(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldSugar(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 측정값 변화 차트 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterBldSugarDEList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldSugarDEList(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 평균, 최초평균 정보 조회
	 */
	@Override
	public Map<String, Object> selTrgterBldSugarSTAVG(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldSugarSTAVG(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 서비스 주차별 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selTrgterBldSugarWKInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selTrgterBldSugarWKInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 이상 측정정보 조회
	 */
	@Override
	public List<Map<String, Object>> selSugarDisorderExamInfo(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selSugarDisorderExamInfo(param);
	}
	
	/**
	 * 자가입력 건강정보 확인 혈당 일자별 측정 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selectDayBloodSugarDataList(Map<String, Object> param) throws Exception{
		return inputHealthInfoCnfmDAO.selectDayBloodSugarDataList(param);
	}

	/**
	 * 복약 탭 - 복용약 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selectDrugInfoList(Map<String, Object> param) throws Exception {
		return inputHealthInfoCnfmDAO.selectDrugInfoList(param);
	}

	/**
	 * 복약 탭 - 복약 일자별 복용 현황 정보 조회
	 */
	@Override
	public List<Map<String, Object>> selDrugMissionAnswerList(Map<String, Object> param) throws Exception {
		return inputHealthInfoCnfmDAO.selectDrugMissionAnswerList(param);
	}
}
