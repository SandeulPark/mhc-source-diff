package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : NoticeMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 공지 관리업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.07		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Repository("web.cm.NoticeMngtDAO")
public class NoticeMngtDAO extends DMultiEgovAbstractMapper{
	
	public int getNoticeMngtListCount(Map<String, Object> param)	throws Exception {

		int rsList = selectOne("mhc.web.cm.noticemngt.selectBizNoticeCount",param);	

		return rsList;  
	}
	
	public List<Map<String, String>> getNoticeMngtList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.cm.noticemngt.selectBizNoticeList", param);		
		return rsList;  
	}
	
	public void getNoticeMngtRegInsert(Map<String, Object> param) throws Exception {

		insert("mhc.web.cm.noticemngt.insertNotice", param);	
		  
	}
	
	public Map<String, String> getNoticeDtls(Map<String, Object> param) throws Exception {	
		String noticeClf = param.get("NOTICE_CLF")==null?"":(String)param.get("NOTICE_CLF");
		if(!"P".equals(noticeClf) && param.get("CNFM_YN").toString().equals("N")){
			insert("mhc.web.cm.noticemngt.insertNoticeCnfm", param);
		}
		
		Map<String,String> rsMap = selectOne("mhc.web.cm.noticemngt.selectNoticeDtls",param);	

		return rsMap;  
	}
	
	public List<Map<String, String>> getBefAftNoticeList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.cm.noticemngt.selectBefAftNoticeList", param);		
		return rsList;  
	}
	
	public void getNoticeMngtUp(Map<String, Object> param) throws Exception {
		
		update("mhc.web.cm.noticemngt.updateNoticeDtls", param);	
		  
	}
	
	public void getNoticeMngtDel(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.noticemngt.deleteNotice", param);	
		  
	}

	
	//공지사항 댓글 목록 조회
	public List<Map<String, Object>> selectNoticeCommentList(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.noticemngt.selectNoticeCommentList", param);		
		
		return rsList;
		
	}
	
	//공지사항 댓글 등록
	public void insertNoticeComment(Map<String, Object> param) throws Exception{
		insert("mhc.web.cm.noticemngt.insertNoticeComment", param);	
	}
	
	//공지사항 댓글 수정
	public void updateNoticeComment(Map<String, Object> param) throws Exception{
		update("mhc.web.cm.noticemngt.updateNoticeComment", param);	
	}	
	
	//공지사항 댓글 삭제
	public void deleteNoticeComment(Map<String, Object> param) throws Exception{
		update("mhc.web.cm.noticemngt.deleteNoticeComment", param);	
	} 
	
	//공지사항 대댓글 등록
	public void insertNoticeChildComment(Map<String, Object> param) throws Exception {
		insert("mhc.web.cm.noticemngt.insertNoticeChildComment", param);
	}	
	
	//공지사항 대댓글 삭제
	public void deleteNoticeChildComment(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.noticemngt.deleteNoticeChildComment", param);
	}
	
	//공지상세 파일목록
	public List<Map<String, String>> getAttachFileList(Map<String, String> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.noticemngt.selectAttachFileList", param);		
		return rsList;
	}
	
	public Map<String, String> getNoticeInquire(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.cm.noticemngt.selectNoticeInquire",param);	
		return rsMap;
	}
	
	public void insertNoticeInquire(Map<String, Object> param) throws Exception {
		insert("mhc.web.cm.noticemngt.insertNoticeInquire", param);
	}	
}
