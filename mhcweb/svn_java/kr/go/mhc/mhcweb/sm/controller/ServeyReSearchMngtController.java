package kr.go.mhc.mhcweb.sm.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.sm.service.ServeyReSearchMngtService;

/**
 * @Class Name : BoardController.java
 * @Description : 게시판 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class ServeyReSearchMngtController extends DMultiActionController {
	
	@Resource(name = "web.sm.ServeyReSearchMngtService")
	private ServeyReSearchMngtService serveyReSearchMngtService;
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 관리자용 설문조사 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serveyReSearchMngt.do")
	public String serveyReSearchMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/serveyReSearchMngt";
	}
	
	/**
	 * 관리자용 설문조사 통계 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serveyReSearchStatsMngt.do")
	public String serveyReSearchStatsMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/serveyReSearchStatsMngt";
	}
		
	
	/**
	 * 설문조사 화면 설문개요정보 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchList.do")
	public @ResponseBody Map<String, Object> serveryResearchList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		System.out.println("#####################################");
		System.out.println("orgClf ===> " + param.get("orgClf"));
		System.out.println("subclf ===> " + param.get("SUBMIT_CLF"));
		System.out.println("#####################################");
		
		if("KH".equals(param.get("orgClf"))){
			rsList = serveyReSearchMngtService.serveyResearchList(param);	
		}else if("HL".equals(param.get("orgClf"))){
			rsList = serveyReSearchMngtService.serveyResearchSumryList(param);
		}
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	
	/**
	 * 설문조사 설문완료 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyEndList.do")
	public @ResponseBody Map<String, Object> serveyEndList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
				
			rsList = serveyReSearchMngtService.serveyEndList(param);	 
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 설문조사 설문완료 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyAppMeasureList.do")
	public @ResponseBody Map<String, Object> serveyAppMeasureList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();		
				
		rsList = serveyReSearchMngtService.serveyAppMeasureList(param);			
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);		
		return rsMap;
	}
	
	/**
	 * 설문개요 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyReSearchMngtDtls.do")
	public String serveyReSearchMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = serveyReSearchMngtService.serveyResearchSumryDtls(param);
		model.addAttribute("sumryInfo", rsMap);
		return "web/sm/serveyReSearchMngtDtls";
	}
	
	/**
	 * 설문개요 상세 기관목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchSumryDtls.do")
	public @ResponseBody Map<String, Object> serveyResearchSumryDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = serveyReSearchMngtService.serveyResearchSumryList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	
	/**
	 * 설문항목 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyReSearchMngtReg.do")
	public String serveyReSearchMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		String pageGubun = param.get("pageGubun") == null ? "" : (String)param.get("pageGubun");
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		if("edit".equals(pageGubun)){
			rsMap = serveyReSearchMngtService.serveyResearchSumryDtls(param);
		}
		model.addAllAttributes(param);
		model.addAttribute("sumryInfo", rsMap);
		return "web/sm/serveyReSearchMngtReg";
	}
	
	/**
	 * 설문항목 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveryResearchItemList.do")
	public @ResponseBody Map<String, Object> serveryResearchItemList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = serveyReSearchMngtService.serveyResearchItemList(param);
		List<Map<String, Object>> rsList2 = serveyReSearchMngtService.serveyRegOrgList(param);
		if(rsList2.size() != 0) {
			rsMap.put("rsList2", rsList2);
		}
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 설문항목 대상 기관 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyRegOrgList.do")
	public @ResponseBody Map<String, Object> serveyRegOrgList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();				
		List<Map<String, Object>> rsList2 = serveyReSearchMngtService.serveyRegOrgList(param);
		if(rsList2.size() != 0) {
			rsMap.put("rsList2", rsList2);
			rsMap.put("id", param.get("id"));		
		}		
		return rsMap;
	}
	
	
	/**
	 * 설문항목 삭제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchItemDel.do")
	public @ResponseBody Map<String, Object> serveyResearchItemDel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String msg = "삭제되었습니다.";
		String chkYn = "Y";
		
		try{
			serveyReSearchMngtService.serveyResearchItemDel(param);
		}catch(Exception e){
			e.printStackTrace();
			msg = "삭제 실패하였습니다.";
			chkYn = "N";
		}
		
		rsMap.put("msg", msg);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 설문조사 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchRegit.do")
	public @ResponseBody Map<String, Object> serveyResearchRegit(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String msg = "저장되었습니다.";
		String chkYn = "Y";
		String serveySn = param.get("serveySn") == null ? "" : (String) param.get("serveySn");		
				
		try{
			serveySn = serveyReSearchMngtService.serveyResearchRegit(param);
						 
			//대상자용 설문조사의 경우 푸시 체크
			if(StringUtil.nvl(String.valueOf(param.get("page")),"").equals("trgtServey")){
				param.put("serveySn", serveySn);				
				chkPushSnd(req,param);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
			msg = "저장 실패하였습니다.";
		}
		
		rsMap.put("msg", msg);
		rsMap.put("chkYn", chkYn);
		rsMap.put("serveySn", serveySn);
		return rsMap;
	}
	
	/**
	 * 대상자용 설문조사 등록시 푸시 메시지를 전송했는지 확인하고 미전송시 전송
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	private void chkPushSnd(HttpServletRequest req, Map<String, Object> param){
		
		System.out.println("ServeyReSearchMngtController / chkPushSnd param ====> " + param);
		
		param.put("sndOrgCd", param.get("SESS_ORG_CD"));
		
		try{
			//1. 설문조사 사용여부 Y + 설문조사 게시여부 Y일 경우 푸시 발송
			if(StringUtil.nvl(String.valueOf(param.get("USE_YN")),"").equals("Y") && StringUtil.nvl(String.valueOf(param.get("DISP_YN")),"").equals("Y")){				
				//2. 해당 설문조사 푸시 발송 여부 확인
								
				Map<String, Object> selPushMas = (Map<String, Object>)pushService.selectPushMasServey(param);
				if(selPushMas == null){
					//3. 푸시 정보 확인
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap = pushService.getPushSetInfo(param);
					
					if(pMap != null && !pMap.isEmpty()) {		

						String pushTitle = StringUtil.nvl(String.valueOf(pMap.get("PUSH_TITLE")),"");
						String pushCont = StringUtil.nvl(String.valueOf(pMap.get("PUSH_CONT")),"");
						String pushLinkPage = StringUtil.nvl(String.valueOf(param.get("pushLinkPage")),String.valueOf(pMap.get("PUSH_LINK_PAGE")));
						String noticeTitle = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_TITLE")),"");
						String noticeCont = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_CONT")),"");
						String noticeLinkPage = StringUtil.nvl(String.valueOf(param.get("noticeLinkPage")),String.valueOf(pMap.get("NOTICE_LINK_PAGE")));
						
						// 4. 푸시 값 설정
						param.put("pushTitle", pushTitle);
						param.put("pushCont", pushCont);
						param.put("pushLinkPage", pushLinkPage);
						param.put("noticeTitle", noticeTitle);
						param.put("noticeCont", noticeCont);
						param.put("noticeLinkPage", noticeLinkPage);
						
						//5. 설문조사 시작일이 오늘 날짜일 경우 현재 날짜로 예약시간 저장 / 오늘 이후 날짜일 경우 선택한 날짜 + 오전9시로 예약시간 저장
						if(!StringUtil.nvl(String.valueOf(param.get("DISP_BGN_DE")),"").equals("")){
				    		param.put("reqClf", "20");
							SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
							
					    	String dispBgnDeStr = String.valueOf(param.get("DISP_BGN_DE"));					    	
					    	Date today = new Date();
					    	Date dispBgnDe = dateFormatter.parse(dispBgnDeStr);
					    	
					    	param.put("resrvtDe", dispBgnDeStr.replace("-", ""));
					    	param.put("resrvtTm", dispBgnDe.after(today) ? "090000" : timeFormatter.format(today));
					    	
						}
						pushMessageUtil.setReqData(req,param);
						pushService.sendPushData(pushMessageUtil,param);
					}
				}
			//4. 설문 미사용, 삭제이거나 미게시일 경우 미발송된 푸시가 있을 경우(예약) 삭제
			}else {
				param.put("gb", "delete");
				Map<String, Object> selPushMas = (Map<String, Object>)pushService.selectPushMasServey(param);
				
				if(selPushMas != null){
					param.put("SND_SN", selPushMas.get("SND_SN"));
					pushService.deletePushMasHist(param);
				}				
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 설문조사 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyReSearchMngt_pop.do")
	public String serveyReSearchMngt_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		
		String mainPopYn = String.valueOf(param.get("MAIN_POPYN"));
				
		if(!"pre".equals(param.get("gb").toString())){			
			if(StringUtil.nvl(String.valueOf(param.get("SUBMIT_CLF")),"") == "") {				
				String submitClf = serveyReSearchMngtService.serveyResearchAnswrClf(param);							
				param.put("SUBMIT_CLF", submitClf);
			}			
			rsMap = serveyReSearchMngtService.serveyResearchAnswrMastr(param);
			rsList = serveyReSearchMngtService.serveyResearchAnswrList(param);
		}
		
		model.addAttribute("sumryInfo", rsMap);
		model.addAttribute("itemList", rsList);
		model.addAllAttributes(param);
		return "web/sm/serveyReSearchMngt_pop";
	}
	
	@RequestMapping(value="/serveyStatsMngt_pop.do")
	public String serveyStatsMngt_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> sumryInfo = serveyReSearchMngtService.serveyResearchAnswrMastr(param);
		List<Map<String, Object>> itemList = serveyReSearchMngtService.serveyAnswrStatsList(param);
		List<Map<String, Object>> itemInpList = serveyReSearchMngtService.serveyAnswrInpStatsList(param);
		
		sumryInfo.put("isServeyContSumryYn", "N");
		
		String serveyContSumry = StringUtil.nvl(String.valueOf(sumryInfo.get("SERVEY_CONT_SUMRY")));		
		
		if(serveyContSumry != "") {
			serveyContSumry = serveyContSumry.replace("\n", "<br>");
			sumryInfo.put("SERVEY_CONT_SUMRY", serveyContSumry);
			sumryInfo.put("isServeyContSumryYn", "Y");
		}

		model.addAttribute("sumryInfo", sumryInfo);
		model.addAttribute("itemList", itemList);
		model.addAttribute("itemInpList", itemInpList);
		model.addAllAttributes(param);
		
		return "web/sm/serveyStatsMngt_pop";
	}
	
	@RequestMapping(value="/serveyStatsMngt.do")
	public @ResponseBody Map<String, Object> serveyStatsMngt(HttpServletRequest request, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, Object> sumryInfo = serveyReSearchMngtService.serveyResearchAnswrMastr(param);
		//설문 대상자수, 설문 응답자 수
		Map<String, Object> serveyCnt = new HashMap<String, Object>();
		if(param.get("DISP_TRGT_CLF").equals("TR")) {
			serveyCnt = serveyReSearchMngtService.getTrgtServeyUserCnt(param);
		}else {
			serveyCnt = serveyReSearchMngtService.getServeyUserCnt(param);
		}			
		//설문 항목 리스트
		List<Map<String, Object>> itemList = serveyReSearchMngtService.serveyAnswrStatsList(param);
		//설문 항목 리스트(주관식)
		List<Map<String, Object>> itemInpList = serveyReSearchMngtService.serveyAnswrInpStatsList(param);

		rsMap.put("sumryInfo", sumryInfo);
		rsMap.put("serveyCnt", serveyCnt);
		rsMap.put("itemList", itemList);
		rsMap.put("itemInpList", itemInpList);
				
		return rsMap;
	}
	
	@RequestMapping(value="/serveyStatsMngtPrint.do")
	public String serveyStatsMngtPrint(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		param.put("TRGT_ORG_NM", URLDecoder.decode(String.valueOf(param.get("TRGT_ORG_NM")), "UTF-8"));
		
		Map<String, Object> sumryInfo = serveyReSearchMngtService.serveyResearchAnswrMastr(param);
		sumryInfo.put("isServeyContSumryYn", "N");
		String serveyContSumry = StringUtil.nvl(String.valueOf(sumryInfo.get("SERVEY_CONT_SUMRY")));			
		if(serveyContSumry != "") {
			serveyContSumry = serveyContSumry.replace("\n", "<br>");
			sumryInfo.put("SERVEY_CONT_SUMRY", serveyContSumry);
			sumryInfo.put("isServeyContSumryYn", "Y");
		}
		
		Map<String, Object> serveyCnt = new HashMap<String, Object>();
		if(param.get("DISP_TRGT_CLF").equals("TR")) {
			serveyCnt = serveyReSearchMngtService.getTrgtServeyUserCnt(param);
		}else {
			serveyCnt = serveyReSearchMngtService.getServeyUserCnt(param);
		}	
		List<Map<String, Object>> itemList = serveyReSearchMngtService.serveyAnswrStatsList(param);
		List<Map<String, Object>> itemInpList = serveyReSearchMngtService.serveyAnswrInpStatsList(param);

		model.addAttribute("sumryInfo", sumryInfo);
		model.addAttribute("serveyCnt", serveyCnt);
		model.addAttribute("itemList", itemList);
		model.addAttribute("itemInpList", itemInpList);
		model.addAllAttributes(param);
		
		return "web/sm/serveyStatsMngtPrint";
	}
	
	
	/**
	 * 설문답변 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyResearchAnswrInsert.do")
	public @ResponseBody Map<String, Object> serveyResearchAnswrInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			serveyReSearchMngtService.serveyResearchAnswrInsert(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 보건소선택 목록리스트
	 */
	@RequestMapping(value= "/orgListPop.do", method = RequestMethod.GET)
	public String orgListPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sm/regOrgListPop";
	}
	
	/**
	 * 보건소 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regOrgList.do")
	public @ResponseBody Map<String, Object> regOrgList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String gb = StringUtil.nvl(String.valueOf(param.get("gb")));
				
		int rsCnt = 0;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();
		if(gb.isEmpty()) {
			rsList = serveyReSearchMngtService.regOrgList(param);
		}		
		rsCnt = serveyReSearchMngtService.regOrgListCnt(param);		
		rsMap.put("rsCnt", rsCnt);
		rsMap.put("rsList", rsList);		

		return rsMap;
	}
	
	/**
	 * 대상자 APP 설문통계 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyAppMeasurePop.do")
	public String serveyAppMeasurePop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();		
		
		String SERVEY_TYPE ="";
		String SEARCH_DATE=(String) param.get("SEARCH_DATE");
		String SEARCH_DATE_STS="";
		
		if(SEARCH_DATE.length()==8) {
			SEARCH_DATE_STS="DAY";
		}else if(SEARCH_DATE.length()==6){
			SEARCH_DATE_STS="MONTH";
		}else {
			SEARCH_DATE_STS="YEAR";
		}
		param.put("SEARCH_DATE_STS", SEARCH_DATE_STS);
		rsList = serveyReSearchMngtService.serveyAppDtlsMeasureList(param);
				
		if (rsList.size() != 0) {
		for(int i=0; i<rsList.size(); i++) {
			
			String SERVEY_CONT=(String) rsList.get(i).get("SERVEY_CONT");
			String str = SERVEY_CONT.replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", " ");
			rsList.get(i).put("SERVEY_CONT", str);
			String faq =   (String) rsList.get(i).get("FAQ");
			if(faq != null) {						
				String faqTemp[] = faq.split(",");
				for(int a=0; a<faqTemp.length; a++) {
					rsList.get(i).put("FAQ"+a, faqTemp[a]);
					rsList.get(i).put("FAQ_LENGTH", faqTemp.length-1);
				}
			}else {//sub질문
				rsList.get(i).put("FAQ0", "예");
				rsList.get(i).put("FAQ1", "아니요");
				rsList.get(i).put("FAQ_LENGTH", 1);
			}
		}
		
		
		model.addAttribute("itemList", rsList);
		model.addAttribute("SERVEY_TYPE", SERVEY_TYPE);		
		model.addAllAttributes(param);
		}
		return "web/sm/serveyAppMeasurePop";
	}

	/**
	 * 대상자용 설문조사 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serveyTrgtReSearchMngt.do")
	public String serveyTrgtReSearchMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		
		return "web/sm/serveyTrgtReSearchMngt";
	}
	
	/**
	 * 대상자용 설문조사 통계 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serveyTrgtReSearchStatsMngt.do")
	public String serveyAppMeasure(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/serveyTrgtReSearchStatsMngt";
	}

	/**
	 * 설문조사 화면 설문개요정보 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyTrgtResearchList.do")
	public @ResponseBody Map<String, Object> serveyTrgtResearchList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();

		rsList = serveyReSearchMngtService.serveyTrgtResearchList(param);

		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}

	/**
	 * 대상자용 설문항목 등록 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyTrgtReSearchMngtReg.do")
	public String serveyTrgtReSearchMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		String pageGubun = param.get("pageGubun") == null ? "" : (String)param.get("pageGubun");
		Map<String, Object> rsMap = new HashMap<String, Object>();

		if("edit".equals(pageGubun)){
			rsMap = serveyReSearchMngtService.serveyResearchSumryDtls(param);
		}
		model.addAllAttributes(param);
		model.addAttribute("sumryInfo", rsMap);
		return "web/sm/serveyTrgtReSearchMngtReg";
	}

	/**
	 * 대상자용 설문개요 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyTrgtReSearchMngtDtls.do")
	public String serveyTrgtReSearchMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String, Object> rsMap = serveyReSearchMngtService.serveyTrgtReSearchMngtDtls(param);
		String serveyContSumry = StringUtil.nvl(String.valueOf(rsMap.get("SERVEY_CONT_SUMRY")));
		
		if(serveyContSumry != "") {
			rsMap.put("SERVEY_CONT_SUMRY", serveyContSumry);
		}
		
		model.addAttribute("selList", selList);
		model.addAttribute("sumryInfo", rsMap);
		
		return "web/sm/serveyTrgtReSearchMngtDtls";
	}


	/**
	 * 설문개요 상세 설문 대상자 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serveyTrgtResearchSumryDtls.do")
	public @ResponseBody Map<String, Object> serveyTrgtResearchSumryDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = serveyReSearchMngtService.serveyTrgtResearchSumryDtls(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}


	/**
	 * 대상자용 설문조사 설문완료 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trgtServeyEndList.do")
	public @ResponseBody Map<String, Object> trgtServeyEndList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();

		rsList = serveyReSearchMngtService.trgtServeyEndList(param);

		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}


}
