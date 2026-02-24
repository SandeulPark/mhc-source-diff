package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.stringtemplate.v4.ST;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("smhcapp.sv.CommunityDAO")
public class CommunityDAO extends DMultiEgovAbstractMapper {

	
	public List<Map<String, Object>> selectCommunityList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> communityList = selectList("smhcapp.sv.community.selectCommunityList", param);
		return communityList;
	}
	
	public int checkCommunityInfo(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.community.checkCommunityInfo", param);
		return rsInt;
	}
	
	public Map<String, Object> getCommunitySttus(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("smhcapp.sv.community.getCommunitySttus", param);
		return rsMap;
	}
	
	//커뮤니티 상세조회
	public Map<String, Object> selectCommunityCont(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("smhcapp.sv.community.selectCommunityCont", param);
		return rsMap;
	}
	
	//커뮤니티 댓글 조회
	public List<Map<String, Object>> selectCommunityCmmnt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> cmmntList = selectList("smhcapp.sv.community.selectCommunityCmmnt", param);
		return cmmntList;
	}
	
	//댓글등록
	public int insertComment(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		param.put("CMMNT_SN", selectOne("smhcapp.sv.community.getCmmntSn", param));
		rsInt += insert("smhcapp.sv.community.insertComment", param);
		return rsInt;
	}
	
	//댓글 좋아요
	public int mergeCmmntLike(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt += insert("smhcapp.sv.community.mergeCmmntLike", param);
		return rsInt;
	}
	
	//조회수
	public int updateRdCnt(Map<String, Object> param) throws Exception{
		return update("smhcapp.sv.community.updateRdCnt", param);
	}
	
	
	
	
	//댓글 수정
	public int updateCmmnt(Map<String, Object> param) throws Exception{
		return update("smhcapp.sv.community.updateCmmnt", param);
	}

	//댓글 신고
	public void commentRptSubmit(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("smhcapp.sv.community.commentRptSubmitCnt", param);
		update("smhcapp.sv.community.commentRptSubmit", param);		
	}
	
	//사용자 차단
	public void userBlockSubmit(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		insert("smhcapp.sv.community.userBlockSubmit", param);
	}
}
