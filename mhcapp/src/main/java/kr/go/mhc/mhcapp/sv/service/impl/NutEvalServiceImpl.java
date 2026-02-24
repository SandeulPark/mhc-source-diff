package kr.go.mhc.mhcapp.sv.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.NutEvalService;

@Service("mhcapp.sv.NutEvalService")
public class NutEvalServiceImpl implements NutEvalService{

	@Resource(name="mhcapp.sv.NutEvalDAO")
    private NutEvalDAO nutEvalDAO;
	
	/**
	 * 영양평가1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList1(param);
	}
	
	/**
	 * 영양평가1 사진 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalAttchFileList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalAttchFileList(param);
	}
	/**
	 * 영양평가2 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList2(param);
	}
	/**
	 * 영양평가3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList3(param);
	}
	/**
	 * 2017.03.06 이태석 추가(사진,동영상 보기)
	 * 영양평가4 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList4(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList4(param);
	}
	
	/**
	 * 평가기간조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectEvalPeriod(Map<String, Object> param) throws Exception{
		return nutEvalDAO.selectEvalPeriod(param);
	}
	
	/**
	 * 끼니별 칼로리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMealCalList(Map<String, Object> param) throws Exception{
		return nutEvalDAO.selectMealCalList(param);
	}
	
	/**
	 * 요일별, 끼니별 칼로리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectWeekMealCalList(Map<String, Object> param) throws Exception{
		return nutEvalDAO.selectWeekMealCalList(param);
	}	
	
	
	/**
	 * 집중상담 자동발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception{
		return nutEvalDAO.selectAutoSendYn(param);
	}		

}
