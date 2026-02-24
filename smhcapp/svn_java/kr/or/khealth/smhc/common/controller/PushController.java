package kr.or.khealth.smhc.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;






import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.service.PushService;
import kr.or.khealth.smhc.common.util.StringUtil;

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
	
	
	/////푸쉬테스트
	
	@RequestMapping(value="/pushTokenTest.do")
	public void pushTokenTest(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		String token = "dubmnBNeRCms-lgh_NYlnW:APA91bH_ihd9pIq3usLvgPuKdvmEp7RGd5-T-ItPuci6Yst6CtLW2kTlY9DoR5llQOMViak2PWniZ3JhtzHqhGjmlNXgdZ34nhknn59BHeUZfRSU8h2D9Qls1NaxIzlbbzZiqOApUP9l";
		//(String token, String title, String message, String osType)
		token = param.get("token")!=null?String.valueOf(param.get("token")):token;
		System.out.println("pushTokenTest::title="+String.valueOf(param.get("title")));
		System.out.println("pushTokenTest::cont="+String.valueOf(param.get("cont")));
		pushMessageUtil.sendPushToToken(token, String.valueOf(param.get("title")), String.valueOf(param.get("cont")), "AND");
	}
	
	@RequestMapping(value="/pushTopicTest.do")
	public void pushTopicTest(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> pushParam = new HashMap<String, Object>();		
		
		String topic = "T001";
		//(String token, String title, String message, String osType)
		topic = param.get("topic")!=null?String.valueOf(param.get("topic")):topic;
		System.out.println("pushTokenTest::title="+String.valueOf(param.get("title")));
		System.out.println("pushTokenTest::cont="+String.valueOf(param.get("cont")));
		
		pushMessageUtil.sendPushToTopic(topic,String.valueOf(param.get("title")), String.valueOf(param.get("cont")), "AND", pushParam);
	}
	

	
	@RequestMapping(value="/sendPushToTopic.do")
	public @ResponseBody Map<String, Object> sendPushToTopic(HttpServletRequest req, @ModelAttribute Map<String,Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> infoMap = new HashMap<String, Object>();
		String chkYn = "N";

		try{
			infoMap.put("USER_ID", param.get("SESS_USER_ID"));
			infoMap.put("ORG_CD", param.get("ORG_CD"));	
			String msgCode = param.get("msgCode")!=null?param.get("msgCode").toString():"M"; //CM011 전체공지:A, 기관공지:O, 커뮤니티공지:C, 모바일공지:M
			String msgSn = param.get("msgSn")!=null?param.get("msgSn").toString():"0001";  //TN_SV_PUSH_SND_MASTR 전송번호  SND_SN
			String osType = param.get("osType")!=null?param.get("osType").toString():"AND"; //안드로이드 : AND 아이폰 : IOS
			String topic = param.get("topic")!=null?param.get("topic").toString():"test";
			String title = param.get("title")!=null?param.get("title").toString():"test";
			String body = param.get("body")!=null?param.get("body").toString():"test";
			pushMessageUtil.sendPushToTopic(topic, title, body, osType, param);			
			

			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}

		rsMap.put("chkYn", chkYn);
		
		return rsMap;
	}
}
