package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnrlMngterRegMngtService;

@Service("web.gn.GnrlMngterRegMngtService")
public class GnrlMngterRegMngtServiceImpl extends EgovAbstractServiceImpl implements GnrlMngterRegMngtService{

	@Resource(name="web.gn.GnrlMngterRegMngtDAO")
	private GnrlMngterRegMngtDAO gnrlMngterRegMngtDAO;

	@Override
	public List<Map<String, String>> getRegMngterList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.getRegMngterList(param);
	}

	@Override
	public int saveMngter(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.saveMngter(param);
	}

	@Override
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMngterRegMngtDAO.getManagerDuplicationCnt(param);
	}

	@Override
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.updateApprovalYn(param);
	}

	@Override
	public int updatedn1Use(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.updatedn1Use(param);
	}

	@Override
	public int updatedn2Use(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.updatedn2Use(param);
	}

	@Override
	public Map<String, Object> getdigiSignPopUp(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.getdigiSignPopUp(param);
	}

	@Override
	public List<Map<String, String>> permissionsList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.permissionsList(param);
	}

	@Override
	public List<Map<String, String>> userAuthList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.userAuthList(param);
	}

	@Override
	public List<Map<String, String>> permissionsTrgterList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.permissionsTrgterList(param);
	}

	@Override
	public List<Map<String, String>> perMenuList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.perMenuList(param);
	}

	@Override
	public List<Map<String, String>> selectPerMenuList(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.selectPerMenuList(param);
	}

	@Override
	public List<Map<String, String>> selectPerMenuCnt(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.selectPerMenuCnt(param);
	}

	@Override
	public List<Map<String, String>> selectAuthKey(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.selectAuthKey(param);
	}

	@Override
	public void perMenuGInsert(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.perMenuGInsert(param);
	}

	@Override
	public void authListInsert(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.authListInsert(param);
	}

	@Override
	public void authListUpdate(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.authListUpdate(param);
	}

	@Override
	public void authListUpdate2(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.authListUpdate2(param);
	}

	@Override
	public void authDelete(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.authDelete(param);
	}

	@Override
	public void authList(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.authList(param);
	}

	@Override
	public void userAuthDelete(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.userAuthDelete(param);
	}

	@Override
	public void orgAuthUsersInsert(Map<String, Object> param) throws Exception {
		gnrlMngterRegMngtDAO.orgAuthUsersInsert(param);
	}

	@Override
	public List<Map<String, String>> userPerCheck(Map<String, Object> param) throws Exception {
		return gnrlMngterRegMngtDAO.userPerCheck(param);
	}

	@Override
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMngterRegMngtDAO.getOrgAuthList(param);
	}

	@Override
	public List<Map<String, Object>> slectErrReport(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMngterRegMngtDAO.slectErrReport(param);
	}

	@Override
	public List<Map<String, Object>> checkRegUser(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMngterRegMngtDAO.checkRegUser(param);
	}

}
