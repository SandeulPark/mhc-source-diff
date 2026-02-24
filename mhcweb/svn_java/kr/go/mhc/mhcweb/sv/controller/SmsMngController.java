package kr.go.mhc.mhcweb.sv.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.DateUtil;
import kr.go.mhc.common.util.EgovWebUtil;
import kr.go.mhc.common.util.FileUtil;
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.mhcweb.sv.service.SmsMngService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/sv")
public class SmsMngController extends DMultiActionController {
	
	@Resource(name = "web.sv.SmsMngService")
	private SmsMngService smsMngService;
	
	@Resource(name = "fileUtil")
	protected FileUtil fileUtil;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * SMS전송화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSet.do")
	public String smsSet(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		return "web/sv/smsSet";
	}
	
	/**
	 * SMS전송화면 대상자 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSendTrgterList.do")
	public @ResponseBody Map<String, Object> smsSendTrgterList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = null;
		if("grid1".equals(param.get("id"))){
			rsList = smsMngService.getSmsSendTrgterList(param);
		}else{
			rsList = smsMngService.getSmsSendManagerList(param);
		}
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * SMS전송화면 대상자 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSendTodayList.do")
	public @ResponseBody Map<String, Object> smsSendTodayList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = smsMngService.getSmsSendTodayList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * SMS전송 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sendSms.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> sendSms(final MultipartHttpServletRequest multiRequest, 
														@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		PushMessageUtil pushMsg = new PushMessageUtil();		
				
		int msgId = 0;
		int fileCnt = 0;
		
		String sndSn = pushMsg.getPushId();		
		String[] userIdArr = param.get("userId").toString().split("\\,");
		String[] userNmArr = param.get("userNm").toString().split("\\,");
		String[] mobileNoArr = param.get("mobileNo").toString().split("\\,");
		String smsSaveDir = "/home/mcs/McsAgent/file/mms";  	// 운영
		//String smsSaveDir = "C:\\McsAgent\\file\\mms";			// 로컬
		String contentData = "";
		String smsClf = (String) param.get("SMS_CLF");
		
		MultipartFile file;
		
		try{
			if("MMS".equals(smsClf)){
				if(!files.isEmpty()){
					File saveFolder = new File(smsSaveDir + File.separator + "img" + File.separator + DateUtil.getSysDatenf());
					if (!saveFolder.exists() || saveFolder.isFile()) {
						saveFolder.mkdirs();
					}
					
					Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
					
					
					while (itr.hasNext()) {
						Entry<String, MultipartFile> entry = itr.next();
	
						file = entry.getValue();
						String localFileNm = file.getOriginalFilename();
						
						if ("".equals(localFileNm)) {
							continue;
						}
						
						String svrFileNm = "img" + File.separator + DateUtil.getSysDatenf() + File.separator + DateUtil.getSysDate() + fileCnt + "_" + localFileNm;
						fileCnt++;
						
						String filePath = smsSaveDir + File.separator + svrFileNm;
						file.transferTo(new File(filePath));
						
						if("".equals(contentData)){
							contentData += svrFileNm + "^1^0";
						}else{
							contentData += "|" + svrFileNm + "^1^0";
						}
					}
					param.put("CONTENT_DATA", contentData);
					param.put("CONTENT_COUNT", fileCnt);
				}				
			}			
			
			param.put("SND_SN", sndSn);
			param.put("SND_CNT", userIdArr.length);
			if("10".equals(param.get("REQ_CLF"))){
				param.put("RESRVT_DT", "");
				param.put("RESRVT_TM", "");
			}
			
			smsMngService.saveSmsMaster(param);
			for(int i = 0; i < userIdArr.length; i++){				
				
				param.put("RCV_USER_ID",userIdArr[i]);
				param.put("RCV_USER_NM",userNmArr[i]);
				param.put("RCV_MOBILE_NO",mobileNoArr[i]);
				smsMngService.saveSmsHis(param);
				if("MMS".equals(smsClf)){
					msgId = smsMngService.getMmsMsgId();
					param.put("MSG_ID", msgId);
					smsMngService.saveMmsAgent(param);
				}else{
					msgId = smsMngService.getSmsMsgId();
					param.put("MSG_ID", msgId);
					smsMngService.saveSmsAgent(param);
				}
			}
			
			rsMap.put("rsMsg", "success");
			
		}catch(Exception e){
			e.printStackTrace();
			rsMap.put("rsMsg", "error");
		}
		
		return rsMap;
	}
	
	/**
	 * SMS발송현황 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSendSttus.do")
	public String smsSendSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		return "web/sv/smsSendSttus";
	}
	
	/**
	 * SMS발송현황 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSendList.do")
	public @ResponseBody Map<String, Object> selectSmsSendList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = smsMngService.getSmsSendList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * SMS발송현황 상세 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsSendDetailList.do")
	public @ResponseBody Map<String, Object> smsSendDetailList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = smsMngService.getSmsSendDetailList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자별현황화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsTrgterSttus.do")
	public String smsTrgterSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		return "web/sv/smsTrgterSttus";
	}
	
	/**
	 * 대상자별현황 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsTrgterSttusList.do")
	public @ResponseBody Map<String, Object> smsTrgterSttusList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = smsMngService.getSmsTrgterSttus(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * SMS 요금 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/smsCharge.do")
	public @ResponseBody Map<String, String> smsCharge(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String, String> rsMap = smsMngService.getSmsCharge(param);
				
		return rsMap;
	}

}
