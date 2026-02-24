package kr.or.khealth.smhc.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@SuppressWarnings("deprecation")
public class JsonpView extends MappingJacksonJsonView{
	public static final String DEFAULT_CONTENT_TYPE="application/javascript";

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String[]>params = request.getParameterMap();
		if(params.containsKey("callback")){
			//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
			response.getOutputStream().write(new String(params.get("callback")[0]+"(").getBytes());
			super.render(model,request,response);
			response.getOutputStream().write(new String(");").getBytes());
			response.setContentType(DEFAULT_CONTENT_TYPE);
		}else{
			//System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
			super.render(model, request, response);
		}
	}
	
}
