package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01		이은주			최초생성
 
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.TrgterInfoMngtService")
public class TrgterInfoMngtServiceImpl  implements TrgterInfoMngtService{
	
	@Resource(name= "web.tg.TrgterInfoMngtDAO")
	private TrgterInfoMngtDAO trgterInfoMngtDAO;

	//대상자정보관리 대상자 목록 조회
	@Override
	public List<Map<String, Object>> trgterInfoMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.trgterInfoMngtList(param);
	}

	//대상자정보관리 대상자 상세 조회
	@Override
	public Map<String, Object> trgterInfoMngtDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.trgterInfoMngtDtls(param);
	}

	//대상자정보관리 대상자 저장
	@Override
	public void updateTrgterInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		trgterInfoMngtDAO.updateTrgterInfo(param);
	}

	//대상자정보관리 신체활동 탭 활동목표
	@Override
	public List<Map<String, Object>> selTrgterActList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterActList(param);
	}
	
	//대상자정보관리 신체활동 탭 일자별
	@Override
	public List<Map<String, Object>> selTrgterActDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterActDEList(param);
	}

	//대상자정보관리 신체활동 탭 일자별 평균
	@Override
	public Map<String, Object> selTrgterActDEAVG(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterActDEAVG(param);
	}
	
	//대상자정보관리 신체활동 탭 요일별
	@Override
	public List<Map<String, Object>> selTrgterActDYList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterActDYList(param);
	}

	//대상자정보관리 신체활동 탭 누적, 평균
	@Override
	public List<Map<String, Object>> selTrgterTotActCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterTotActCnt(param);
	}

	//대상자정보관리 신체활동 탭 주차별 현황
	@Override
	public List<Map<String, Object>> selTrgterWKInfo(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterWKInfo(param);
	}

	//대상자정보관리 체성분 탭 체중목표
	@Override
	public List<Map<String, Object>> selTrgterWeightList(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterWeightList(param);
	}

	//대상자정보관리 체성분 탭 측정값 변화 (일자별)
	@Override
	public List<Map<String, Object>> selTrgterWeightDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterWeightDEList(param);
	}

	//대상자정보관리 체성분 탭 체성분 시작, 종료 비교
	@Override
	public Map<String, Object> selTrgterWeightSTED(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterWeightSTED(param);
	}

	//대상자정보관리 체성분 탭 서비스 주차별 현황
	@Override
	public List<Map<String, Object>> selTrgterWeightWKInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterWeightWKInfo(param);
	}

	//대상자정보관리 혈압 최근 측정현황
	@Override
	public Map<String, Object> selTrgterBldPressList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldPressList(param);
	}

	//대상자정보관리 혈압 측정값 변화 (일자별)
	@Override
	public List<Map<String, Object>> selTrgterBldPressDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldPressDEList(param);
	}

	//대상자정보관리 혈압 기간 평균, 최초 측정 비교
	@Override
	public Map<String, Object> selTrgterBldPressSTAVG(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldPressSTAVG(param);
	}

	//대상자정보관리 혈압 서비스 주차별 현황
	@Override
	public List<Map<String, Object>> selTrgterBldWKInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldWKInfo(param);
	}
	
	//대상자정보관리 혈압 이상 측정정보(그리드)
	@Override
	public List<Map<String, Object>> selPressDisorderExamInfo(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selPressDisorderExamInfo(param);
	}
	
	//대상자정보관리 혈압 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 
	@Override
	public void updateDisorderExamProc(Map<String, Object> param) throws Exception {
		trgterInfoMngtDAO.updateDisorderExamProc(param);
	}

	//대상자정보관리 혈당 최근 측정현황 (테이블)
	@Override
	public Map<String, Object> selTrgterBldSugarList(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldSugarList(param);
	}

	//대상자정보관리 혈당 측정값 변화 (차트)
	@Override
	public List<Map<String, Object>> selTrgterBldSugarDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldSugarDEList(param);
	}

	//대상자정보관리 혈당 기간 평균, 최초 측정 비교 (테이블)
	@Override
	public Map<String, Object> selTrgterBldSugarSTAVG(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldSugarSTAVG(param);
	}

	//대상자정보관리 혈당 서비스 주차별 현황 (그리드)
	@Override
	public List<Map<String, Object>> selTrgterBldSugarWKInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterBldSugarWKInfo(param);
	}
	
	//대상자정보관리 혈당 이상 측정보 (그리드)
	@Override
	public List<Map<String, Object>> selSugarDisorderExamInfo(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selSugarDisorderExamInfo(param);
	}
	
	//대상자정보관리 검진기록 검진결과 (그리드)
	@Override
	public List<Map<String, Object>> selTrgterExamRsltList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterExamRsltList(param);
	}

	//대상자정보관리 검진기록 검사결과 (테이블)
	@Override
	public Map<String, Object> selTrgterExamRslt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterExamRslt(param);
	}

	//대상자정보관리 서비스참여 탭 건강정보 측정 상세
	@Override
	public List<Map<String, Object>> selTrgterSvcHealthInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selTrgterSvcHealthInfo(param);
	}
	
	//대상자정보관리 서비스참여 탭 건강정보 측정 실천률
	@Override
	public Map<String, Object> selTrgterSvcHealthInfoPracRate(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selTrgterSvcHealthInfoPracRate(param);
	}

	//대상자정보관리 상담 탭 방문상담 조회
	@Override
	public List<Map<String, Object>> selectVisitCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selectVisitCnslList(param);
	}
	
	//대상자정보관리 상담 탭 집중상담 조회
	@Override
	public List<Map<String, Object>> selectFocusCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selectFocusCnslList(param);
	}
	
	//대상자정보관리 상담 탭 일반상담 조회
	@Override
	public List<Map<String, Object>> selectGeneralCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selectGeneralCnslList(param);
	}
	
	//대상자정보관리 상담 탭 실시간상담 조회
	@Override
	public List<Map<String, Object>> selectRealtimeCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selectRealtimeCnslList(param);
	}

	//대상자정보관리 상담 탭 일반상담 상담확인 팝업
	@Override
	public Map<String, Object> selectGeneralCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selectGeneralCnsl(param);
	}
	
	@Override
	public Map<String, Object> selectGeneralCnslChk(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.selectGeneralCnslChk(param);
	}
	
	@Override
	public void updateGeneralCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		trgterInfoMngtDAO.updateGeneralCnsl(param);
	}

	//대상자정보관리 상담 탭 실시간 상담 1:1 상담 요청  중복 체크
	@Override
	public int countRealTimeCnslChk(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.countRealTimeCnslChk(param);
	}
	
	//대상자정보관리 중도탈락 업데이트
	@Override
	public void updateDrop(Map<String, Object> param) throws Exception {
		trgterInfoMngtDAO.updateDrop(param);
	}
	
	//대상자정보관리 중도탈락 조회
	@Override
	public Map<String, Object> selectDrop(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selectDrop(param);
	}
	
	//대상자정보관리 중도탈락 취소
	@Override
	public Map<String, Object> cancelDrop(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.cancelDrop(param);
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 활동량 일자별 현황 (그리드)
	@Override
	public List<Map<String, Object>> getDayActDataList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.getDayActDataList(param);
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 체성분 일자별 현황 (그리드)
	@Override
	public List<Map<String, Object>> getDayBodyCompDataList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.getDayBodyCompDataList(param);
	}

	//2017.02.23 이태석 추가
	//대상자정보관리 혈압 일자별 현황 (그리드)
	@Override
	public List<Map<String, Object>> getDayBloodPressDataList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.getDayBloodPressDataList(param);
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 혈당 일자별 현황 (그리드)
	@Override
	public List<Map<String, Object>> getDayBloodSugarDataList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.getDayBloodSugarDataList(param);
	}

	//2017.06.16 추가
	//대상자정보관리 심박수 탭 심박목표 (그리드)	
	@Override
	public List<Map<String, Object>> selHeartGoal(Map<String, Object> param)throws Exception {
		return trgterInfoMngtDAO.selHeartGoal(param);
	}

	//2017.06.16 추가
	//대상자정보관리 심박수 탭 목표심박달성률 (그리드)		
	@Override
	public List<Map<String, Object>> selHeartAchiRate(Map<String, Object> param)throws Exception {
		return trgterInfoMngtDAO.selHeartAchiRate(param);
	}

	//2017.06.16 추가
	//대상자정보관리 심박수 탭 일자별 심박측정정보 (차트)		
	@Override
	public List<Map<String, Object>> selDayHeartData(Map<String, Object> param)throws Exception {
		return trgterInfoMngtDAO.selDayHeartData(param);
	}

	//2017.06.16 추가
	//대상자정보관리 심박수 탭 시간대별 심박측정정보 (차트)			
	@Override
	public List<Map<String, Object>> selHourHeartData(Map<String, Object> param)throws Exception {
		return trgterInfoMngtDAO.selHourHeartData(param);
	}
	
	//대상자 모니터링 메모 조회
	@Override
	public List<Map<String,Object>> selectMemoDtls(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.selectMemoDtls(param);
	}
	
	//대상자 모니터링 메모 신규 저장
	@Override
	public int insertMemo(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.insertMemo(param);
	}
	
	//2018.06.19 유준영 추가
		//대상자정보관리 대상자 이력정보  조회
		@Override
		public List<Map<String, Object>> getTrgterMeasrInfoExcelAct(Map<String, Object> param) throws Exception {
			return trgterInfoMngtDAO.getTrgterMeasrInfoExcelActList(param);
		}
		
		@Override
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBodyComp(Map<String, Object> param) throws Exception {
			return trgterInfoMngtDAO.getTrgterMeasrInfoExcelBodyCompList(param);
		}
		
		@Override
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodPress(Map<String, Object> param) throws Exception {
			return trgterInfoMngtDAO.getTrgterMeasrInfoExcelBloodPressList(param);
		}
		
		@Override
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodSugar(Map<String, Object> param) throws Exception {
			return trgterInfoMngtDAO.getTrgterMeasrInfoExcelBloodSugarList(param);
		}	
	//대상자 통합검색 리스트 조회(대상자 검색 팝업)
	@Override
	public List<Map<String, Object>> trgterCbSearchList(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.trgterCbSearchList(param);
	}
	
	//대상자 통합검색 관리목표-신체활동
	@Override
	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.bodyGoalList(param);
	}
	
	//대상자 통합검색 관리목표-영양
	@Override
	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception{
		return trgterInfoMngtDAO.nutriGoalList(param);
	}

	//대상자 통합검색 목표정보 조회
	@Override
	public List<Map<String, Object>> trgterCbInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.trgterCbInfo(param);
	}

	@Override
	public List<Map<String, Object>> selHourHeartData2(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterInfoMngtDAO.selHourHeartData2(param);
	}
	
	
}
