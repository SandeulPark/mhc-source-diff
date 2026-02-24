package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.TrgterAftMngtService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :TrgterAftMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.02		나연이			최초생성
 
 * @author thejoin
 * @since 2018.10.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.TrgterAftMngtService")
public class TrgterAftMngtServiceImpl  implements TrgterAftMngtService{
	
	@Resource(name= "web.tg.TrgterAftMngtDAO")
	private TrgterAftMngtDAO trgterAftMngtDAO;

	/**
	 * 추후관리 대상자 목록 조회
	 */
	@Override
	public List<Map<String, Object>> trgterAftMngtList(Map<String, Object> param) throws Exception{
		return trgterAftMngtDAO.trgterAftMngtList(param);
	}
	
	/**
	 * 추후관리 대상자 상세 정보 조회
	 */
	@Override
	public Map<String, Object> trgterAftMngtDtls(Map<String, Object> param) throws Exception{
		return trgterAftMngtDAO.trgterAftMngtDtls(param);
	}
	
	/**
	 * 추후관리 대상자 활동량 및 컨텐츠 정보 조회
	 */
	@Override
	public List<Map<String, Object>> trgterAftMngtInfo(Map<String, Object> param) throws Exception{
		return trgterAftMngtDAO.trgterAftMngtInfo(param);
	}
	
	/**
	 * 추후관리 스케줄 생성
	 */
	@Override
	public void createAfterSchedule(Map<String, Object> param) throws Exception{
		trgterAftMngtDAO.createAfterSchedule(param);
	}
	
	/**
	 * 추후관리 종료 여부 업데이트
	 */
	@Override
	public void updateAftMngtEndYn(Map<String, Object> param) throws Exception{
		trgterAftMngtDAO.updateAftMngtEndYn(param);
	}
	
	/**
	 * 추후관리 서비스 제공 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> trgterAfterMngtSchList(Map<String, Object> param) throws Exception{
		return trgterAftMngtDAO.trgterAfterMngtSchList(param);
	}
	
	/**
	 * 추후관리 서비스 종료
	 */
	public void trgterAftMngtEnd(Map<String, Object> param) throws Exception{
		trgterAftMngtDAO.trgterAftMngtEnd(param);
	}
}
