package kr.go.mhc.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.PushService;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/push")
public class PushController extends DMultiActionController{ 
	
	@Resource(name="common.pushService")
	private PushService pushService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/pushUpdateStatus.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUpdateStatus(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();

		try{
			LOG.debug("rcvUserId===="+param.get("rcvUserId"));
			pushService.updatePushStatus(param);
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/pushUpdateCnfm.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUpdateCnfm(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			LOG.debug("rcvUserId===="+param.get("rcvUserId"));
			pushService.pushUpdateCnfm(param);
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	
	/**
	 * 2017.03.07 상담 알림 전송
	 */
	@RequestMapping( value="/selectAdmTokenList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectAdmTokenList(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> tokenList = new ArrayList<Map<String,Object>>();
		try {
			tokenList = pushService.selectAdmTokenList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rsMap.put("tokenList", tokenList);
		return rsMap;
	}
	
	@RequestMapping( value="/insertPushData.do", method=RequestMethod.POST )
	public @ResponseBody Map<String,Object> insertPushData(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		pushMessageUtil.setReqData(req,param);
		if(pushMessageUtil.sendNotifition()){
			pushService.insertPushHis(pushMessageUtil.getResultMap());
		}
				param.put("sndSnNum", pushMessageUtil.getResultMap().get("sndSnNum"));
				pushService.updateSndSnNum(param);
		return pushMessageUtil.getResultMap();
	}
	
	@RequestMapping( value="/sendPushUsers.do", method=RequestMethod.POST )
	public @ResponseBody Map<String,Object> sendPushUsers(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String userId = StringUtil.nvl(String.valueOf(param.get("userId")));
		if(!"".equals(userId)){
			List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
			param.put("userIter", userIter);
		}
		List<Map<String,Object>> sendList = pushService.selectSendList(param);
		
		if(sendList != null){
			if(sendList.size() > 0){
				if(pushMessageUtil.sendPushList(sendList)){// 푸시 전송
					pushService.updatePushHis(pushMessageUtil.getResultMap());
					rsMap.put("resultList", pushMessageUtil.getResultMap());
				}
			}
		}
		return rsMap;
	}
}
