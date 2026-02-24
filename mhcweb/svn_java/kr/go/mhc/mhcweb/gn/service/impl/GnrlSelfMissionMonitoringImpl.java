package kr.go.mhc.mhcweb.gn.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnrlSelfMissionMonitoringService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("web.gn.GnrlSelfMissionMonitoringService")
public class GnrlSelfMissionMonitoringImpl extends EgovAbstractServiceImpl implements GnrlSelfMissionMonitoringService {

    @Resource(name="web.gn.GnrlSelfMissionMonitoringDAO")
    private GnrlSelfMissionMonitoringDAO gnrlSelfMissionMonitoringDAO;


    @Override
    public List<Map<String, Object>> getSelfMissionList(Map<String, Object> param) throws Exception {
        return gnrlSelfMissionMonitoringDAO.getSelfMissionList(param);
    }

    @Override
    public List<Map<String, Object>> getSelfMissionDtls(Map<String, Object> param) throws Exception {
        return gnrlSelfMissionMonitoringDAO.getSelfMissionDtls(param);
    }
}

