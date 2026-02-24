package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.tg.service.TrgterFormMngtService;

@Service("web.tg.TrgterFormMngtService")
public class TrgterFormMngtServiceImpl implements TrgterFormMngtService{
	
	@Resource(name="web.tg.TrgterFormMngtServiceDAO")
	private TrgterFormMngtServiceDAO trgterFormMngtServiceDAO;
	
	@Override
	public List<Map<String, String>> getTrgterFormMngtList(Map<String, Object> param) throws Exception {
		return trgterFormMngtServiceDAO.getTrgterFormMngtList(param);
	}
	
	@Override
	public Map<String, Object> getTrgterFormViewInfo(Map<String, Object> param) throws Exception {
		return trgterFormMngtServiceDAO.getTrgterFormViewInfo(param);
	}
	
	
	
}
