package com.ffzx.kart.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.constant.Constant;
import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.GameExample;
import com.ffzx.kart.model.GameExample.Criteria;
import com.ffzx.kart.model.User;
import com.ffzx.kart.util.DateUtil;
import com.ffzx.kart.vo.GameModel;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.service.GameService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 车辆控制器层
 * 
 * @author king.zhang
 * @date 2016年12月19日 下午14:28:31
 * @version 1.0
 */

@Controller
@RequestMapping("/Game")
public class GameController extends BaseController<Game, String, GameExample>{

	@Resource
	private GameService service;

	@Override
	public GameService getService() {
		return service;
	}
	
	@SuppressWarnings("unchecked")
    @RequestMapping("list")
    @ResponseBody
    public ResultVo query(GameModel entity) {
        ResultVo resultVo = new ResultVo();
        try {
	        String indexStr = getParameter("pageIndex");
	        String sizeStr = getParameter("pageSize");
	        Page<Game> page = null;
	        int total = 0;
	        if (StringUtils.isNotBlank(indexStr)) {
	            page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
	        }
	        GameExample example = createExample();
	        Criteria criteria = example.createCriteria();
	        if(StringUtils.isNotEmpty(entity.getStartDate())){
	        	Date startDate = DateUtil.parseDate(entity.getStartDate());
	        	String startDateStr = DateUtil.formatDate(startDate);
	        	criteria.andEffectiveTimeGreaterThanOrEqualTo(startDateStr);
	        }
	        if(StringUtils.isNotEmpty(entity.getEndDate())){
	        	 Date endDate = DateUtil.parseDate(entity.getEndDate());
	        	 String endDateStr = DateUtil.formatDate(endDate);
	        	 criteria.andEffectiveTimeLessThanOrEqualTo(endDateStr);
	        }
	        if(StringUtils.isNotEmpty(entity.getCode())){
	        	 criteria.andCodeEqualTo(entity.getCode());
	        }
	        
	        if(StringUtils.isNotEmpty(entity.getType())){
	        	criteria.andTypeEqualTo(entity.getType());
	        } 
	        example.setOrderByClause("start_time");
	        List<Game> dataList = getService().selectByExample(example);
	        total = (int) page.getTotal();
	        resultVo.setRecordsTotal(total);
	        resultVo.setInfoData(dataList);
        } catch (Exception e) {
			logger.info("", e);
			resultVo.setStatus(Constant.ERROR);
			resultVo.setInfoStr(Constant.ERROR_MSG);
		}
        return resultVo;
    }
	
	/**
	 * 修改禁用状态
	 */
	@RequestMapping(value = "updateActFlag")
	@ResponseBody
	public ResultVo updateActFlag(String id, String status,HttpSession session){
		ResultVo resultVo = new ResultVo();
		try {
			Game game = service.findById(id);
			if(game!=null){
				game.setStatus(status);
			}
			service.update(game);
			resultVo.setStatus("success");
	        resultVo.setInfoStr(status.equals("0")?"启用成功":"禁用成功");	
			return resultVo;
		} catch (Exception e) {
			logger.info("", e);
			resultVo.setStatus(Constant.ERROR);
			resultVo.setInfoStr(Constant.ERROR_MSG);
		}
		return resultVo;
	}	
	
	/**
	 * 修改禁用状态
	 */
	@RequestMapping(value = "updateGame")
	@ResponseBody
	public ResultVo updateGame(Game game,HttpSession session){
		ResultVo resultVo = new ResultVo();
		try {
			service.updateSelective(game);
			resultVo.setStatus("success");
			resultVo.setUrl(getBasePath() + "/toList.do");
			return resultVo;
		} catch (Exception e) {
			logger.info("", e);
			resultVo.setStatus(Constant.ERROR);
			resultVo.setInfoStr(Constant.ERROR_MSG);
		}
		return resultVo;
	}	
	
	@RequestMapping("details")
    public String form(String id,String view , ModelMap modelMap) {
			List<GameUserInfoModel> gameUserInfoModelList=null;
		try {
	        Game entity = getService().findById(id);
	        if (entity == null) {
	            entity = createEntity();
	        }else{
	        	gameUserInfoModelList = service.getGameUserInfoModelList(entity);
	        }
	        if(StringUtils.isEmpty(view)){
	        	view="0";
	        }
	        modelMap.put("entity", entity);
	        modelMap.put("view", view);
	        modelMap.put("gameUserInfoModelList", gameUserInfoModelList);
		} catch (Exception e) {
			logger.info("",e);
		}
        return "Game/Edit";
    }
	
	
	
	@RequestMapping("saveOrUpdate")
    @ResponseBody
    public ResultVo save(GameModel entity) {
        ResultVo resultVo = new ResultVo();
        try {
        	
    	    GameExample example = createExample();
 	        Criteria criteria = example.createCriteria();
 	        if(StringUtils.isNotEmpty(entity.getStartDate())){
 	        	Date startDate = DateUtil.parseDate(entity.getStartDate());
 	        	String startDateStr = DateUtil.formatDate(startDate);
 	        	criteria.andEffectiveTimeGreaterThanOrEqualTo(startDateStr);
 	        }
 	        if(StringUtils.isNotEmpty(entity.getEndDate())){
 	        	 Date endDate = DateUtil.parseDate(entity.getEndDate());
 	        	 String endDateStr = DateUtil.formatDate(endDate);
 	        	 criteria.andEffectiveTimeLessThanOrEqualTo(endDateStr);
 	        }
 	       example.setOrderByClause("start_time");
 	       User user = getCurrentUser(); 
 	       List<Game> dataList = getService().selectByExample(example);
 	       String msg=service.saveOrUpdate(entity,dataList,user);
 	       if("success".equals(msg)){
 	    	  resultVo.setStatus("success");
 	 	      resultVo.setUrl(getBasePath() + "/toList.do");
 	       }else{
 	    	  resultVo.setStatus("validate");
 	    	  resultVo.setInfoStr(msg);
 	       }
 	      
        	
		} catch (Exception e) {
			logger.info("", e);
			resultVo.setStatus(Constant.ERROR);
			resultVo.setInfoStr(Constant.ERROR_MSG);
		}
        return resultVo;
    }

	@Override
	public GameExample createExample() {
		return new GameExample();
	}

	@Override
	public Game createEntity() {
		return new Game();
	}
	
}
