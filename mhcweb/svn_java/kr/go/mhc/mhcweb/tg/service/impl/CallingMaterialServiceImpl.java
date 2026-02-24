package kr.go.mhc.mhcweb.tg.service.impl;

import kr.go.mhc.mhcweb.tg.service.CallingMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("web.tg.CallingMaterialService")
public class CallingMaterialServiceImpl implements CallingMaterialService{
	
	@Resource(name="web.tg.CallingMaterialServiceDAO")
	private CallingMaterialServiceDAO callingMateriaServicelDAO;

	@Override
	public List<Map<String, String>> getCallingMaterialList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.getCallingMaterialList(param);
	}

	@Override
	public Map<String, String> getCallingMaterialEachOtherCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.getCallingMaterialEachOtherCnt(param);
	}

	@Override
	public int callingMaterialExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.callingMaterialExcelGridInsert(param);
	}

	@Override
	public int updateCallingMaterInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.updateCallingMaterInfo(param);
	}

	@Override
	public int deleteCallingMaterInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.deleteCallingMaterInfo(param);
	}

	@Override
	public int callingMaterialDupCheck(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callingMateriaServicelDAO.callingMaterialDupCheck(param);
	}

}

