package kr.go.mhc.mhcweb.gn.service.impl;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("web.gn.GnrlSelfMissionMonitoringDAO")
public class GnrlSelfMissionMonitoringDAO extends DMultiEgovAbstractMapper {

    public List<Map<String, Object>> getSelfMissionList(Map<String, Object> param) throws Exception {
        List<Map<String,Object>> rsList = selectList("mhc.web.gn.slfnMntr.getSelfMissionList", param);
        return rsList;
    }

    public List<Map<String, Object>> getSelfMissionDtls(Map<String, Object> param) throws Exception {
        List<Map<String,Object>> rsList = selectList("mhc.web.gn.slfnMntr.getSelfMissionDtls", param);
        return rsList;
    }
}
