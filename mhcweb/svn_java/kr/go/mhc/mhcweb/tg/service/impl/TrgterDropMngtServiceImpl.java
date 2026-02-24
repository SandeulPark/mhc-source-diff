package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.TrgterDropMngtService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : TrgterDropMngtService.java
 * @Description : 관리자 WEB에서 사용하는 대상자탈락관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.07.12		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.07.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.TrgterDropMngtService")
public class TrgterDropMngtServiceImpl implements TrgterDropMngtService{

	@Resource(name= "web.tg.TrgterDropMngtDAO")
	private TrgterDropMngtDAO trgterDropMngtDAO;
	
	//대상자정보관리 대상자 목록 조회
	@Override
	public List<Map<String, Object>> trgterDropMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterDropMngtDAO.trgterDropMngtList(param);
	}
	
	//대상자정보관리 중도탈락 업데이트
	@Override
	public void updateTrgterDrop(Map<String, Object> param) throws Exception {
		trgterDropMngtDAO.updateTrgterDrop(param);
	}
	
	//대상자정보관리 중도탈락 조회
	@Override
	public Map<String, Object> selectTrgterDrop(Map<String, Object> param) throws Exception {
		return trgterDropMngtDAO.selectTrgterDrop(param);
	}
	
	//대상자정보관리 중도탈락 취소
	@Override
	public Map<String, Object> cancelTrgterDrop(Map<String, Object> param) throws Exception {
		return trgterDropMngtDAO.cancelTrgterDrop(param);
	}
}
