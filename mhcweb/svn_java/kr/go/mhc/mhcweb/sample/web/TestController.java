package kr.go.mhc.mhcweb.sample.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.CommonService;
import kr.go.mhc.mhcweb.sample.service.TestService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/push")
public class TestController extends DMultiActionController{ 
	@Resource(name="testService")
	private TestService testService;

//	@Resource(name="common.cmmnService")
//	private CommonService cmmnService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	@RequestMapping( value="/fileSample.do", method = RequestMethod.GET)
	public String fileSample(@ModelAttribute Map param, ModelMap model) throws Exception{
		param.put("attchFileSn", "59");
		List<Map<String,Object>> rsList = cmmnService.selectAttchFile(param);   
		model.addAttribute("rsList", rsList);	
		
		
		return "web/sample/sampleFile";   
	}	
	
	//차트화면 조회
	@RequestMapping( value="/dateSample.do", method = RequestMethod.GET)
	public String dateSample(@ModelAttribute Map param, ModelMap model) throws Exception{
//		List<Map<String,Object>> rsList = testService.getList(param);   
//		model.addAttribute("rsList", rsList);	
		
		
		return "web/sample/sampleDate";   
	}	

	//차트화면 조회
	@RequestMapping( value="/charSample.do", method = RequestMethod.GET)
	public String charSample(@ModelAttribute Map param, ModelMap model) throws Exception{
//		List<Map<String,Object>> rsList = testService.getList(param);   
//		model.addAttribute("rsList", rsList);	
		
		
		return "web/sample/sampleChart";   
	}	
	
	//목록조회
	@RequestMapping( value="/testList.do", method = RequestMethod.GET)
	public String selectUserList(@ModelAttribute Map param, ModelMap model) throws Exception{
		List<Map<String,Object>> rsList = testService.getList(param);   
		model.addAttribute("rsList", rsList);	
		
		
		return "web/sample/sampleList";   
	}	
	
	//상세조회
	@RequestMapping( value="/testDetail.do", method = RequestMethod.GET)
	public String selectUserDetail(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map rsMap = testService.getDetail(param);   
		model.addAttribute("rsMap", rsMap);	
		return "web/sample/sampleDetail";   
	}		
	
	//신규입력화면
	@RequestMapping(value="/testInsertView.do", method = RequestMethod.GET)
	public String testInsertView(@ModelAttribute Map param, ModelMap model) throws Exception{    
		return "web/sample/sampleWrite";    
	}
	
	//수정화면
	@RequestMapping(value="/testEdit.do", method = RequestMethod.GET)
	public String testEdit(@ModelAttribute Map param, ModelMap model) throws Exception{    
		Map rsMap = testService.getDetail(param);   
		model.addAttribute("rsMap", rsMap);	

		return "web/sample/sampleEdit";    
	}	
	
	//신규입력
	@RequestMapping(value="/testSave.do", method = RequestMethod.GET)
	public ModelMap testSave(@ModelAttribute Map param, ModelMap model) throws Exception{    
		
		try{				
			testService.insert(param);		
		
			model.put("msg",getLogMsg("common.write.succ")); 	  					
			LOG.info(getLogMsg("common.write.succ")); 
			model.put("chkYn","Y");			
		}catch(Exception e){			
			model.put("msg",getLogMsg("common.write.succ")); 	  	 
			LOG.error(e,e.fillInStackTrace());
			model.put("chkYn","N");				
		}	
		return model;
	}
	
	//수정
	@RequestMapping(value="/testUpdate.do", method = RequestMethod.GET)
	public ModelMap testUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{    
		
		try{				
			testService.update(param);			
		
			model.put("msg",getLogMsg("common.write.succ")); 	  					
			LOG.info(getLogMsg("common.write.succ"));
			model.put("chkYn","Y");					
		}catch(Exception e){			
			model.put("msg",getLogMsg("common.write.succ")); 	  	 
			LOG.error(e,e.fillInStackTrace());
			model.put("chkYn","N");			
		}	
		return model;
	}	
	
	//삭제
	@RequestMapping(value="/testDelete.do", method = RequestMethod.POST)
	public ModelMap testDelete(@ModelAttribute Map param, ModelMap model) throws Exception{    
		
		try{				
			testService.delete(param);
			
			model.put("msg",getLogMsg("common.delete.succ")); 	  			
			LOG.info(getLogMsg("common.delete.succ")); 	
			model.put("chkYn","Y");			
			
		}catch(Exception e){			
			model.put("msg",getLogMsg("common.delete.err")); 	  	 
			LOG.error(e,e.fillInStackTrace()); 	
			model.put("chkYn","N");				
		}		
		return model;   
	}	

	//신규입력화면
	@RequestMapping(value="/samplekakao.do", method = RequestMethod.GET)
	public String samplekakao(@ModelAttribute Map param, ModelMap model) throws Exception{    
		return "app/sample/samplekakao";
	}
	
}
