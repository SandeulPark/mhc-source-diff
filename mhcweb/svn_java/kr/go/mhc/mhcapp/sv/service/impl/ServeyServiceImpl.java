package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.ServeyService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mhcapp.sv.ServeyService")
public class ServeyServiceImpl extends EgovAbstractServiceImpl implements ServeyService{
	
	@Resource(name="mhcapp.sv.ServeyDAO")
    private ServeyDAO serveyDAO;

	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyList(param);
	}
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyCodeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyCodeList(param);
	}
	
	/**
	 * 설문 마지막 코드 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyLstQnaCD(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyLstQnaCD(param);
	}

	/**
	 * 설문지 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyAwrInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyAwrInsert(param);
	}
	
	/**
	 * 설문지 마스터 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyMasterInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyMasterInsert(param);
	}
	
	/**
	 * 설문지 영양 체크리스트 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyAnswrUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyAnswrUpdate(param);
	}
	
	/**
	 * 설문지 마스터 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int updateServeyMaster(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.updateServeyMaster(param);
	}
}
