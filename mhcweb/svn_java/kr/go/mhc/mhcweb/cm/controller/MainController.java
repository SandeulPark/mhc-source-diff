package kr.go.mhc.mhcweb.cm.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.cm.service.MainService;

import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends DMultiActionController{ 
	@Resource(name="mainService")
	private MainService mainService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/main.do")
	public String mainView(@ModelAttribute Map param, ModelMap model) throws Exception{
//		Map<String, Object> trgterJoinSttusMap = mainService.getTrgterJoinSttus(param);
//		Map<String, Object> svcContinuePerMap = mainService.getSvcContinuePer(param);
//		Map<String, Object> weekSvcJoinPerMap = mainService.getWeekSvcJoinPer(param);
//		Map<String, Object> todaySvcJoinCntMap = mainService.getTodaySvcJoinCnt(param);
//		Map<String, Object> realTimeCnsl = mainService.getRealTimeCnsl(param);
//		Map<String, Object> normalCnsl = mainService.getNormalCnsl(param);
//		Map<String, Object> intenseCnsl = mainService.getIntenseCnsl(param);
//		Map<String, Object> visitCnsl = mainService.getVisitCnsl(param);
		List<Map<String,Object>> noticeList = mainService.getNoticeList(param);
//		int HealthDisorderInfoTrgterCnt = mainService.selectHealthDisorderInfoTrgter(param);
//		int SvcNoJoinTrgterCnt = mainService.selectSvcNoJoinTrgter(param);		
//		int SvcSchNotCreateCnt = mainService.selectSvcSchNotCreateCnt(param);
		for(int i=0; i < noticeList.size(); i++){
			String TITLE = noticeList.get(i).get("TITLE").toString(); 
			if(TITLE.length() > 40){
				noticeList.get(i).put("TITLE", TITLE.substring(0, 39)+"..."); 
			}
		}
//		model.addAttribute("trgterJoinSttusMap",trgterJoinSttusMap);
//		model.addAttribute("svcContinuePerMap",svcContinuePerMap);
//		model.addAttribute("weekSvcJoinPerMap",weekSvcJoinPerMap);
//		model.addAttribute("todaySvcJoinCntMap",todaySvcJoinCntMap);
//		model.addAttribute("realTimeCnsl",realTimeCnsl);
//		model.addAttribute("normalCnsl",normalCnsl);
//		model.addAttribute("intenseCnsl",intenseCnsl);
//		model.addAttribute("visitCnsl",visitCnsl);
//		model.addAttribute("HealthDisorderInfoTrgterCnt",HealthDisorderInfoTrgterCnt);
//		model.addAttribute("SvcNoJoinTrgterCnt",SvcNoJoinTrgterCnt);
//		model.addAttribute("SvcSchNotCreateCnt",SvcSchNotCreateCnt);
		model.addAttribute("noticeList",noticeList);
		return "web/main";   
	}	
	
	/**
	 * 메인화면 팝업공지 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPopNoticeList.do")
	public @ResponseBody Map<String, Object> getPopNoticeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			List<Map<String, Object>> rsList = mainService.getPopNoticeList(param);
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			chkYn = "N";
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 팝업공지 확인 업데이트(다시보지않기 설정 시)
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updPopNoticeCnfm.do")
	public @ResponseBody Map<String, Object> updPopNoticeCnfm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			mainService.updPopNoticeCnfm(param);
		}catch(Exception e){
			chkYn = "N";
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	@RequestMapping(value="/printFileDownload.do")
	public void printFileDownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		
		try{
			byte b[] = new byte[1024];
			
			res.reset();
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition",  "attachment;filename=IEPageSetupX.exe");
			
			fis = new FileInputStream("/WAS/jeus/mhcweb/WEB-INF/lib/IEPageSetupX.exe");
			sos = res.getOutputStream();
			
			int numRead;
			while((numRead = fis.read(b, 0, b.length))!= -1){
				sos.write(b, 0, numRead);
			}
			sos.flush();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null)	fis.close();
			if(sos!=null)	sos.close();
		}
	}
	
	/**
	 * 스트리밍 서버에서 mediaID 수신
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMediaID.do")
	public String getMediaID(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		
		return "web/cm/getMediaId";
	}
	
	/**
	 * 서버 시간 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getServerTime.do")
	public @ResponseBody Map<String, Object> getServerTime(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = mainService.getServerTime(param);
		return rsMap;
	}
	
	/**
	 * 대상자 특이 사항 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/trgterSpecialNotePop.do", method=RequestMethod.GET)
	public String trgterSpecialNotePop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = mainService.getTrgterInfo(param);
		model.addAttribute("rsMap", rsMap);
		return "web/cm/trgterSpecialNotePop";
	}
	
	
	/**
	 * 대상자 특이 사항 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTrgterSpecialNote.do")
	public @ResponseBody Map<String, Object> getTrgterSpecialNote(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mainService.getTrgterSpecialNote(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 목표상담 메모 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getObjCnslMemo.do")
	public @ResponseBody Map<String, Object> getObjCnslMemo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mainService.getObjCnslMemo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 집중상담 메모 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getIntenseCnslMemo.do")
	public @ResponseBody Map<String, Object> getIntenseCnslMemo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mainService.getIntenseCnslMemo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 특이 사항 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSpecialNote.do")
	public @ResponseBody Map<String, Object> updateSpecialNote(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int updateChkck = mainService.updateSpecialNote(param);
		rsMap.put("updateChkck", updateChkck);
		return rsMap;
	}
	
	/**
	 * 대상자 특이 사항 삭제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteSpecialNote.do")
	public @ResponseBody Map<String, Object> deleteSpecialNote(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int updateChkck = mainService.deleteSpecialNote(param);
		rsMap.put("updateChkck", updateChkck);
		return rsMap;
	}
	
	/**
	 * 메인 공지 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/mainNoticePop.do", method=RequestMethod.GET)
	public String mainNoticePop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{		
		return "web/cm/mainNoticePop";
	}
	
	/**
	 * 즐겨찾기 메뉴 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFavoriteMenu.do")
	public @ResponseBody Map<String, Object> getFavoriteMenu(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mainService.getFavoriteMenu(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	
	/**
	 * 즐겨찾기 메뉴 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFavoriteMenu.do")
	public @ResponseBody Map<String, Object> saveFavoriteMenu(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int updateChkck = mainService.saveFavoriteMenu(param);
		rsMap.put("updateChkck", updateChkck);
		return rsMap;
	}
	
	/**
	 * 즐겨찾기 메뉴 삭제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteFavoriteMenu.do")
	public @ResponseBody Map<String, Object> deleteFavoriteMenu(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int updateChkck = mainService.deleteFavoriteMenu(param);
		rsMap.put("updateChkck", updateChkck);
		return rsMap;
	}	
	
	/**
	 * 메인 데이터 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMainData.do")
	public @ResponseBody Map<String, Object> getMainData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, Object> trgterJoinSttusMap = mainService.getTrgterJoinSttus(param);
		Map<String, Object> svcContinuePerMap = mainService.getSvcContinuePer(param);
		Map<String, Object> weekSvcJoinPerMap = mainService.getWeekSvcJoinPer(param);
		Map<String, Object> todaySvcJoinCntMap = mainService.getTodaySvcJoinCnt(param);
		Map<String, Object> realTimeCnsl = mainService.getRealTimeCnsl(param);
		Map<String, Object> normalCnsl = mainService.getNormalCnsl(param);
		Map<String, Object> intenseCnsl = mainService.getIntenseCnsl(param); // 집중상담 2025 포함 이전 (건강위험군 )
		Map<String, Object> intenseCnslNew = mainService.getIntenseCnslNew(param); // 집중상담 2026년 이후 (자가관리군)
		Map<String, Object> visitCnsl = mainService.getVisitCnsl(param);

		// 2025.11 보건소의 해당년도 집중상담 여부
		List<Map<String,String>> orgDtls = orgMngtService.getOrgDtlsList(param);

		//2023.11.13 누적참여자수, 누적탈락자수 추가 chyoon
		Map<String,Object> accumulateSvcJoinCntMap = mainService.getAccumulateSvcJoinCnt(param);
		Map<String,Object> accumulateSvcDropCntMap = mainService.getAccumulateSvcDropCnt(param);
		
		
		int HealthDisorderInfoTrgterCnt = mainService.selectHealthDisorderInfoTrgter(param);
		int SvcNoJoinTrgterCnt = mainService.selectSvcNoJoinTrgter(param);		
		int SvcSchNotCreateCnt = mainService.selectSvcSchNotCreateCnt(param);
		
		rsMap.put("trgterJoinSttusMap",trgterJoinSttusMap);
		rsMap.put("svcContinuePerMap",svcContinuePerMap);
		rsMap.put("weekSvcJoinPerMap",weekSvcJoinPerMap);
		rsMap.put("todaySvcJoinCntMap",todaySvcJoinCntMap);
		rsMap.put("realTimeCnsl",realTimeCnsl);
		rsMap.put("normalCnsl",normalCnsl);
		rsMap.put("intenseCnsl",intenseCnsl);
		rsMap.put("intenseCnslNew",intenseCnslNew);
		rsMap.put("visitCnsl",visitCnsl);
		rsMap.put("HealthDisorderInfoTrgterCnt",HealthDisorderInfoTrgterCnt);
		rsMap.put("SvcNoJoinTrgterCnt",SvcNoJoinTrgterCnt);
		rsMap.put("SvcSchNotCreateCnt",SvcSchNotCreateCnt);

		// 2025.11 보건소 해당년도 집중상담 여부 추가
		rsMap.put("orgDtlsMap",orgDtls);
		
		//2023.11.13 추가
		rsMap.put("accumulateSvcJoinCnt", accumulateSvcJoinCntMap);
		rsMap.put("accumulateSvcDropCnt", accumulateSvcDropCntMap);
		
		return rsMap;
	}	
	
	/**
	 * 보건소목록 및 권한 목록 팝업 호출
	 */
	@RequestMapping(value= "/getOrgAuthListPop.do", method = RequestMethod.GET)
	public String visitResrvtTrgterAdd(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/cm/orgAuthListPop";
	}		
	
	
	/**
	 * 보건소 목록 및 권한 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrgAuthList.do")
	public @ResponseBody Map<String, Object> getOrgAuthList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mainService.getOrgAuthList(param);
		rsMap.put("rsList", rsList);

		return rsMap;
	}

	/**
	 * 메인화면 통합관제 소명 자료 팝업공지 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPopCallingMaterialNoticeList.do")
	public @ResponseBody Map<String, Object> getPopCallingMaterialNoticeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			List<Map<String, Object>> rsList = mainService.getPopCallingMaterialNoticeList(param);
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			chkYn = "N";
			e.printStackTrace();
		}

		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 메인화면 통합관제 소명 자료 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/mainCallingMaterialNoticePop.do", method=RequestMethod.GET)
	public String mainCallingMaterialNoticePop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return "web/cm/mainCallingMaterialNoticePop";
	}
	
	/**
	 * 메인화면 팝업공지 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPopServeyList.do")
	public @ResponseBody Map<String, Object> getPopServeyList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			List<Map<String, Object>> rsList = mainService.getPopServeyList(param);
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			chkYn = "N";
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
}
