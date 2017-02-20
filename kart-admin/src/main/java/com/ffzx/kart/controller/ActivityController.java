package com.ffzx.kart.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.kart.model.Activity;
import com.ffzx.kart.model.ActivityExample;
import com.ffzx.kart.model.FileRepo;
import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.User;
import com.ffzx.kart.util.DateUtil;
import com.ffzx.kart.util.JsonConverter;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.util.CodeGenerator;
import com.ffzx.service.ActivityService;
import com.ffzx.service.FileRepoService;

/**
 * 车辆控制器层
 * 
 * @author king.zhang
 * @date 2016年12月19日 下午14:28:31
 * @version 1.0
 */

@Controller
@RequestMapping("/Activity")
public class ActivityController extends BaseController<Activity, String, ActivityExample>{

	@Resource
	private ActivityService service;
	
	@Resource
    private FileRepoService fileRepoService;

	@Override
	public ActivityService getService() {
		return service;
	}
	
	/**
	 * 修改禁用状态
	 */
	@RequestMapping(value = "updateActFlag")
	@ResponseBody
	public ResultVo updateActFlag(String id, String status,HttpSession session){
		ResultVo resultVo = new ResultVo();
		Activity activity = service.findById(id);
		if(activity!=null){
			activity.setStatus(status);
		}
		service.update(activity);
		resultVo.setStatus("success");
        resultVo.setInfoStr(status.equals("0")?"启用成功":"禁用成功");	
		return resultVo;
	}
	
	@RequestMapping("details")
    public String form(String id,String view , ModelMap modelMap) {
		try {
			Activity activity = service.findById(id);
	        if (activity == null) {
	        	activity = createEntity();
	        }
	            
	        if(StringUtils.isEmpty(view)){
	        	view="0";
	        }
	        modelMap.put("entity", activity);
	        modelMap.put("view", view);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "Activity/Form";
    }
	
	@RequestMapping("saveOrUpdate")
    @ResponseBody
    public ResultVo save(Activity entity,String startDateTimeStr,String endDateTimeStr) {
		ResultVo resultVo = new ResultVo();
		try {
			boolean isCreate = false;
			Date current = new Date();
			User user = getCurrentUser();
			
			if (StringUtils.isBlank(entity.getId())) {
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
			entity.setStartDateTime(StringUtils.isNotEmpty(startDateTimeStr)==true?DateUtil.parseDate(startDateTimeStr):new Date());
			entity.setEndDateTime(StringUtils.isNotEmpty(endDateTimeStr)==true?DateUtil.parseDate(endDateTimeStr):new Date());
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
			e.printStackTrace();
		}
        return resultVo;
    }
	
	
	@RequestMapping("upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        logger.info("upload start ");
        logger.info(file.getOriginalFilename());
        FileRepo fileRepo = new FileRepo();
        fileRepo.setName(file.getOriginalFilename());
        fileRepo.setContentType(file.getContentType());
        try {
            fileRepo.setContent(file.getBytes());
            fileRepoService.add(fileRepo);
            
//            String path=request.getSession().getServletContext().getRealPath("/")+File.separator+"banner"; 
//            File targetFile = new File(path + "\\" + fileRepo.getId()+"_"+file.getOriginalFilename()); // 保存
//    		try {
//    			file.transferTo(targetFile);
//    		} catch (Exception e) {
//    			logger.error("",e);
//    		}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info(JsonConverter.toJson(fileRepo));
        return fileRepo;
    }

	@Override
	public ActivityExample createExample() {
		return new ActivityExample();
	}

	@Override
	public Activity createEntity() {
		return new Activity();
	}
	
}
