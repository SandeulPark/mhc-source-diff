package kr.or.khealth.smhc.common;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.util.JSONWrappedObject;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;


public class MappingJsonpHttpMessageConverter extends MappingJacksonHttpMessageConverter{
	private final Log logger = LogFactory.getLog(getClass());
	private boolean prefixJson = false;
	
	private String prefixCallback = "callback(";
	private String suffixCallback = ")";
	
	public void setPrefixJson(boolean prefixJson){
		this.prefixJson = prefixJson;
	}
	
	public void setPrefixCallback(String prefixCallback){
		this.prefixCallback = prefixCallback;
	}
	
	public void setSuffixCallback(String suffixCallback){
		this.suffixCallback = suffixCallback;
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator = super.getObjectMapper().getJsonFactory().createJsonGenerator(outputMessage.getBody(),encoding);

		
		try{
			if(this.prefixJson){
				jsonGenerator.writeRaw("{}&&");
			}
			JSONWrappedObject jsonWrappedObject = new JSONWrappedObject(prefixCallback, suffixCallback, object);
			super.getObjectMapper().writeValue(jsonGenerator, jsonWrappedObject);
		}catch(JsonProcessingException ex){
			throw new HttpMessageNotWritableException("Coud not write JSON:"+ex.getMessage(),ex);
		}
	}
	
	
}