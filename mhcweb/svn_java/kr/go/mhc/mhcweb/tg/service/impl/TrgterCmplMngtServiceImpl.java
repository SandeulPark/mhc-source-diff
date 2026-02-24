package kr.go.mhc.mhcweb.tg.service.impl;

import kr.go.mhc.mhcweb.tg.service.TrgterCmplMngtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value="web.tg.TrgterCmplMngtService")
public class TrgterCmplMngtServiceImpl implements TrgterCmplMngtService{

	@Resource(name= "web.tg.TrgterCmplMngtDAO")
	private TrgterCmplMngtDAO trgterCmplMngtDAO;
	
	//완료 대상자 관리 목록 조회
	@Override
	public List<Map<String, Object>> trgterCmplMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterCmplMngtDAO.trgterCmplMngtList(param);
	}
}
