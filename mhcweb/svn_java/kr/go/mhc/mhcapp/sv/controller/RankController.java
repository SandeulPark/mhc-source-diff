package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.RankService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RankController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.RankService")
	private RankService rankService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 랭킹 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankMain.do", method = RequestMethod.GET)
	public String rankMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/rank/ranking";
	}	
	
	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//Map<String,String> data = new HashMap<String,String>();
		
		List<Map<String,String>> rsList = rankService.selectRankList(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	/**
	 * 차트 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//Map<String,String> data = new HashMap<String,String>();
		
		List<Map<String,String>> rsList = rankService.selectRankChartList(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/rankDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectRankDtlsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//Map<String,String> data = new HashMap<String,String>();
		
		List<Map<String,String>> rsList = rankService.selectRankDtlsList(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
}