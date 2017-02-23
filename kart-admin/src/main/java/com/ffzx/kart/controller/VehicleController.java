package com.ffzx.kart.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.constant.Constant;
import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.kart.model.User;
import com.ffzx.kart.model.Vehicle;
import com.ffzx.kart.model.VehicleExample;
import com.ffzx.kart.util.CodeGenerator;
import com.ffzx.kart.service.VehicleService;

/**
 * 车辆控制器层
 * 
 * @author king.zhang
 * @date 2016年12月19日 下午14:28:31
 * @version 1.0
 */

@Controller
@RequestMapping("/Vehicle")
public class VehicleController extends BaseController<Vehicle, String, VehicleExample>{

	@Resource
	private VehicleService service;

	@Override
	public VehicleService getService() {
		return service;
	}
	
	@RequestMapping("saveOrUpdate")
    @ResponseBody
    public ResultVo save(Vehicle entity) {
		ResultVo resultVo = new ResultVo();
		try {
			
			Vehicle vehicle = service.findByCode(entity.getCode());
			
			boolean isCreate = false;
			Date current = new Date();
			User user = getCurrentUser();
			
			if (StringUtils.isBlank(entity.getId())) {
				if(vehicle!=null){
					resultVo.setStatus("error");
					resultVo.setInfoStr(entity.getCode()+"车辆编码已经存在,请重新输入");
					return resultVo;
				}
				isCreate = true;
				String id = CodeGenerator.getUUID();
				
				entity.setId(id);
				if (StringUtils.isBlank(entity.getCode())) {
					String code = CodeGenerator.code();
					entity.setCode(code);
				}
				entity.setLastUpdateDate(current);
				entity.setCreateDate(current);
				if (user != null) {
					entity.setCreateBy(user.getCode());
					entity.setLastUpdateBy(user.getCode());
				}
			}
			int ret = 0;
			if (isCreate) {
	           /* String fileImage=imageId+"_"+imageName;
	            entity.setImage(fileImage);*/
				getService().add(entity);
			} else {
				getService().updateSelective(entity);
			}
			if (ret > 0) {
				//成功
			}
			resultVo.setStatus("success");
			resultVo.setUrl(getBasePath() + "/toList.do");
		} catch (Exception e) {
			logger.info("",e);
			resultVo.setStatus(Constant.ERROR);
			resultVo.setInfoStr(Constant.ERROR_MSG);
		}
        return resultVo;
    }

	@Override
	public VehicleExample createExample() {
		return new VehicleExample();
	}

	@Override
	public Vehicle createEntity() {
		return new Vehicle();
	}
	
}
