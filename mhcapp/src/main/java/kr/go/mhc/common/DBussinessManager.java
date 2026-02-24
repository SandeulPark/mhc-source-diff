package kr.go.mhc.common;

import org.apache.log4j.Logger;


import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class DBussinessManager { 
	
	protected final  Logger LOG = Logger.getLogger(this.getClass());
	
	protected void writeMap(Map map) {
		Iterator itr = map.entrySet().iterator();
		Map.Entry e = null;
		String tmp = "";
		
		LOG.debug("writeMap start----------------------------------------------");

		while (itr.hasNext()) {
			e = (Map.Entry) itr.next();
			tmp += e.getKey() + " :" + e.getValue() + "\n";
		}
		LOG.debug(tmp);
		LOG.debug("writeMap end------------------------------------------------");

	}
	
	protected void writeList(List list) {
		LOG.debug("writeMap start==============================================");
		for(int i = 0 ; i < list.size();++i){
			writeMap((Map)list.get(i));
		}
		LOG.debug("writeMap end=================================================");
	}
}
