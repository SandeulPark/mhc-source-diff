package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.NoSmokeCounselService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : NoSmokeCounselServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 집중상담 (금연절주) impl
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		------		---------------------------
 * @	2016.07.01		허광일			최초생성
 *
 * @author gst
 * @since 2016.07.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.sv.NoSmokeCounselService")
public class NoSmokeCounselServiceImpl implements NoSmokeCounselService{
	@Resource(name="mhcapp.sv.NoSmokeCounselDAO")
    private NoSmokeCounselDAO noSmokeCounselDAO;

	/**
	 * 금연절주 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoSmokeCounselDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noSmokeCounselDAO.selectNoSmokeCounselDtls(param);
	}

	/**
	 * 금연절주 상세 목록 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoSmokeCounselAddFiles(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noSmokeCounselDAO.selectNoSmokeCounselAddFiles(param);
	}

	/**
	 * 금연절주 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoSmokeCounselList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noSmokeCounselDAO.selectNoSmokeCounselList(param);
	}
	
}