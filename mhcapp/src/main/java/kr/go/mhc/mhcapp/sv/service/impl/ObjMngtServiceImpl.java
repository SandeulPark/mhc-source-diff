package kr.go.mhc.mhcapp.sv.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.ObjMngtService;

@Service("mhcapp.sv.ObjMngtService")
public class ObjMngtServiceImpl implements ObjMngtService{

	@Resource(name="mhcapp.sv.ObjMngtDAO")
    private ObjMngtDAO objMngtDAO;
	
	/**
	 * 목표관리_상담 일자 조회
	 */
	public List<Map<String,String>> selectObjCnsl(Map<String,Object> param) throws Exception{
		return objMngtDAO.selectObjCnsl(param);
	}
	
	
	/**
	 * 목표관리_영양 상담 상세 조회
	 */
	@Override
	public List<Map<String, String>> selectObjNutMngt(Map<String, Object> param) throws Exception{
		return objMngtDAO.selectObjNutMngt(param);
	}
	
	/**
	 * 식품섭취군별 권장 섭취 횟수 정보
	 */
	@Override
	public List<Map<String, String>> selectIntakeStnd(Map<String, Object> param) throws Exception{
		return objMngtDAO.selectIntakeStnd(param);
	}
	
	/**
	 * 목표관리_신체활동 상담 및 목표 정보 조회
	 */
	@Override
	public Map<String, String> selectObjMngtBodyActInfo(Map<String, Object> param) throws Exception {
		return objMngtDAO.selectObjMngtBodyActInfo(param);
	}
	
	/**
	 * 목표관리_목표관리 상담 및 목표 정보 조회
	 */
	@Override
	public Map<String, String> selectObjMngtHealthMngtInfo(Map<String, Object> param) throws Exception {
		return objMngtDAO.selectObjMngtHealthMngtInfo(param);
	}
	
	/**
	 * 목표관리_신체활동 근력 운동 정보 조회
	 */
	@Override
	public List<Map<String, String>> selectObjMngtBodyPartExcsInfo(Map<String, Object> param) throws Exception{
		return objMngtDAO.selectObjMngtBodyPartExcsInfo(param);
	}
	
	/**
	 * 근력 운동 상세 정보
	 */
	@Override
	public Map<String, Object> selectBodyPartExcsInfo(Map<String, Object> param) throws Exception{
		return objMngtDAO.selectBodyPartExcsInfo(param);
	}
}

