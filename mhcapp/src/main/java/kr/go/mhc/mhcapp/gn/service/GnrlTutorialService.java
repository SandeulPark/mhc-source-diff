package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlTutorialService.java
 * @Description : 보편건강 App에서 사용하는 튜토리얼 서비스 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.10.29		chyoon			최초생성
 * 		
 * @author chyoon
 * @since 2021.10.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlTutorialService {
	
	/**
	 * 튜토리얼 실행 여부 조회
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public List<Map<String, String>> checkTutoYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 튜토리얼 확인 업데이트
	 * @param param
	 * @throws Exception
	 */
	public void updateTutoYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 튜토리얼 리셋
	 * @param param
	 */
	public void resetTutorial(Map<String, Object> param) throws Exception;
	
	/**
	 * 튜토리얼 사용 여부 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, String>> tutoUseYn(Map<String, Object> param) throws Exception;

}
