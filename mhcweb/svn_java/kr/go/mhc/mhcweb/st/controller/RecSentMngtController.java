package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.st.service.RecSentMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : RecSentMngtController.java
 * @Description : 관리자 WEB에서 사용하는 추천문장 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.04.13		양현우 		최초생성
 * @author theJoin
 * @since 2020.04.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/st")
public class RecSentMngtController extends DMultiActionController{
	
	@Resource(name ="web.st.RecSentMngtService")
	private RecSentMngtService recSentMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	
	/**
	 * 추천문장  화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/recSentMngt.do")
	public String recSentMngt(@ModelAttribute Map<String,Object> param , ModelMap model) throws Exception{
		return "web/st/recSentMngt";
	}
	
	/**
	 * 추천문장  리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/recSentMngtList.do")
	public @ResponseBody Map<String, Object> recSentMngtList(@ModelAttribute Map<String,Object> param , ModelMap model)throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = recSentMngtService.getRecSentMngt(param);
		rsMap.put("rsList",rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 추천화면 히스트 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/recSentMngtListHist.do",method=RequestMethod.POST)
	public @ResponseBody int recSentMngtListHist(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		int rsInt = 0;
		String searchInfo = StringUtil.nvl((String)param.get("SENTENCE_SN"));
		if(!"".equals(searchInfo)){
			param.put("arr", StringUtil.makeStringToIterator(searchInfo));
		}
		rsInt = recSentMngtService.recSentMngtListHist(param);
		return rsInt;
	}
	
	@RequestMapping(value= "/getSelRecSentMngt.do")
	public @ResponseBody Map<String, Object> getSelRecSentMngt(@ModelAttribute Map<String,Object> param , ModelMap model)throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = recSentMngtService.getSelRecSentMngt(param);
		rsMap.put("rsList", rsList);
		return rsMap;
		}
	}
