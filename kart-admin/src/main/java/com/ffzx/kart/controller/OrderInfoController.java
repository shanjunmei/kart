package com.ffzx.kart.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.kart.model.*;
import com.ffzx.kart.pingxx.Pay;
import com.ffzx.kart.util.DateUtil;
import com.ffzx.kart.vo.OrderModel;
import com.ffzx.kart.vo.SaveOrderModel;
import com.ffzx.kart.service.GameService;
import com.ffzx.kart.service.OrderDetailService;
import com.ffzx.kart.service.OrderInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 订单控制器
 * @author liujunjun
 * @time：2017年1月17日 下午2:54:31
 * @version 1.0.0
 */
@Controller
@RequestMapping("/OrderInfo")
public class OrderInfoController extends BaseController<OrderInfo, String, OrderInfoExample>{

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
	@Resource
	private OrderInfoService orderService;

	@Resource
	private GameService gameService;

	@Resource
	private OrderDetailService orderDetailService;

	@Override
	public OrderInfoService getService() {
		return orderService;
	}

	@Override
	public OrderInfoExample createExample() {
		return new OrderInfoExample();
	}

	@Override
	public OrderInfo createEntity() {
		return new OrderInfo();
	}


	@RequestMapping("list")
	public String toList(String verificationCode, ModelMap modelMap) {
		modelMap.put("verificationCode",verificationCode);
		return getListPath();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("queryData")
	@ResponseBody
	public ResultVo query(OrderInfo entity) {
		ResultVo resultVo = new ResultVo();
		try {
			String indexStr = getParameter("pageIndex");
			String sizeStr = getParameter("pageSize");
			Page<OrderInfo> page = null;
			int total = 0;
			if (StringUtils.isNotBlank(indexStr)) {
				page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
			}
			OrderInfoExample example = new OrderInfoExample();
			OrderInfoExample.Criteria criteria = example.createCriteria();
			if(StringUtils.isNotEmpty(entity.getMemberPhone())){			//手机号
				criteria.andMemberPhoneEqualTo(entity.getMemberPhone());
			}
			if(StringUtils.isNotEmpty(entity.getCode())){					//订单号
				criteria.andCodeEqualTo(entity.getCode());
			}
			if(StringUtils.isNotEmpty(entity.getGameCode())){				//场次编码
				criteria.andGameCodeEqualTo(entity.getGameCode());
			}
			if(entity.getUseTime() != null){								//验证时间
				String useTime = DateUtil.formatDate(entity.getUseTime());
				criteria.andUseTimeGreaterThanOrEqualTo(DateUtil.parse(useTime + " 00:00:00"))
						.andUseTimeLessThan(DateUtil.parse(useTime + " 23:59:59"));
			}
			if(StringUtils.isNotEmpty(entity.getVerificationCode())){		//验证码
				criteria.andVerificationCodeEqualTo(entity.getVerificationCode());
			}
			if(StringUtils.isNotEmpty(entity.getOrderSource())){			//来源
				criteria.andOrderSourceEqualTo(entity.getOrderSource());
			}

			if(StringUtils.isNotEmpty(entity.getStatus())){//订单状态
			    criteria.andStatusEqualTo(entity.getStatus());
            }

			Date newDate = new Date();
			if(StringUtils.isNotEmpty(entity.getUseState())){				//使用状态
				if(entity.getUseState().equals("0")){//0：未使用
					criteria.andUseStateEqualTo("0")						//--未使用
					 .andStartTimeGreaterThanOrEqualTo(newDate)  			//---使用时间大于当前时间
					 .andEffectiveTimeEqualTo(DateUtil.formatDate(newDate));//---有效日期等于当前时间
				}else if(entity.getUseState().equals("1")){//1：已过期
					criteria.andUseStateEqualTo("0")						//--未使用
					 .andStartTimeLessThanOrEqualTo(newDate);				//---使用时间逍小于当前时间
				}else if(entity.getUseState().equals("2")){//2：不在使用期
					criteria.andUseStateEqualTo("0")						//--未使用
					 .andStartTimeGreaterThanOrEqualTo(newDate)				//---使用时间大于当前时间
					 .andEffectiveTimeNotEqualTo(DateUtil.formatDate(newDate));//---有效日期不等于当前时间
				}else if(entity.getUseState().equals("3")){//3：已使用
					criteria.andUseStateEqualTo("1")
                            .andStartTimeLessThan(newDate);
				}else if(entity.getUseState().equals("4")){//4：已验证
                    criteria.andUseStateEqualTo("1")
                            .andStartTimeGreaterThanOrEqualTo(newDate);
                }
			}

			example.setOrderByClause("last_update_date desc");
			List<OrderInfo> dataList = getService().selectByExample(example);
			List<OrderModel> list = new ArrayList<OrderModel>();
			for(OrderInfo info : dataList){
				OrderModel model = new OrderModel();
				list.add(getService().getOrderModelById(info.getId()));
			}
			total = (int) page.getTotal();
			resultVo.setRecordsTotal(total);
			resultVo.setInfoData(list);
		} catch (Exception e) {
			logger.info("",e);
		}
		return resultVo;
	}

	/**
	 * 查看订单详情
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("toForm")
	public String toForm(String id, ModelMap modelMap)  {
		OrderModel orderModel = getService().getOrderModelById(id);

		modelMap.put("entity", orderModel);

		try {
			if(orderModel != null && orderModel.getOrderInfo() != null &&orderModel.getOrderInfo().getCode() != null) {
				modelMap.put("detailList", getService().getGameUserInfoModelList(orderModel.getOrderInfo()));
			}
		}catch (Exception e){}

		return getFormPath();
	}

	/**
	 * 加载新增界面
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd() {
		return getBasePath() + "/Add";
	}

	@RequestMapping("add")
	public String add(OrderModel orderModel){
		return "";
	}

	/**
	 * 加载验证码界面
	 * @return
	 */
	@RequestMapping("toVerificationCode")
	public String toVerificationCode() {
		return getBasePath() + "/VerificationCode";
	}

	/**
	 * 验证验证码
	 * @return
	 */
	@RequestMapping("verificationCode")
	public String verificationCode(String code, ModelMap modelMap) {
		OrderModel orderModel = getService().getOrderModelByVerificationCode(code);

		if(StringUtils.isEmpty(code)){
			return getBasePath() + "/VerificationCode";
		}

		if(orderModel == null){
			modelMap.put("verificationCode", "2");
			modelMap.put("code", code);
			return getBasePath() + "/VerificationCode";
		}
		modelMap.put("entity", orderModel);
		try {
			if(orderModel != null && orderModel.getOrderInfo() != null &&orderModel.getOrderInfo().getCode() != null) {
				modelMap.put("detailList", getService().getGameUserInfoModelList(orderModel.getOrderInfo()));
			}
		}catch (Exception e){}

		modelMap.put("verificationCode", "1");

		if(orderModel.getUseState().equals("0")) {
			return getBasePath() + "/UseVerificationCode";
		}else{
			return getFormPath();
		}
	}

	/**
	 * 获取指定时间的可以预定的所有场次
	 * @param date
	 * @return
	 */
	@RequestMapping("getGames")
	@ResponseBody
	public List<Game> getGames(String date){
		//获取场次信息
		List<Game> gameList = new ArrayList<Game>();
		try {
			GameExample example = new GameExample();
			if (StringUtils.isNotEmpty(date)) {
				example.createCriteria().andEffectiveTimeEqualTo(date);
			}
			example.createCriteria().andPredeterminedStateEqualTo("0");
			example.setOrderByClause("start_time");
			gameList = gameService.selectByExample(example);

		} catch (Exception e) {

		}
		return gameList;
	}

	/**
	 * 获得场次信息以及当场参与人的明细
	 * @param code
	 * @return
	 */
	@RequestMapping("getGameDetail")
	@ResponseBody
	public Map getGameDetail(String code){
		Map map = new HashMap();
		Game game = gameService.findByCode(code);
		map.put("game",game);

		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetailExample example = new OrderDetailExample();
		example.createCriteria().andGameCodeEqualTo(code)
                .andStatusEqualTo("0");
		list = orderDetailService.selectByExample(example);
		map.put("list",list);

		return map;
	}

	/**
	 * 线下购买或使用线上购买的订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public ResultVo saveOrUpdate(HttpServletRequest request, HttpServletResponse response){
		ResultVo resultVo = new ResultVo();

		String toPath = request.getParameter("toPath");

		SaveOrderModel model = new SaveOrderModel();
		model.setNumbers(request.getParameterValues("number"));
		model.setFullNames(request.getParameterValues("fullName"));
		model.setPhones(request.getParameterValues("phone"));
		model.setIdNumbers(request.getParameterValues("idNumber"));
		model.setGameCode(request.getParameter("gameCode"));
		model.setOrderCode(request.getParameter("orderCode"));
		model.setBuyCount(request.getParameter("buyNum"));
		model.setFavorablePrice(request.getParameter("preferentialPrice"));
		model.setTotalPrice(request.getParameter("countPrice"));

		int result = orderService.saveOrUpdate(model,getCurrentUser());

		if(result == 0) {
			resultVo.setStatus("success");
			if(toPath.equals("Add")) {
				resultVo.setUrl(getBasePath() + "/toList.do");
			}else if(toPath.equals("UseVerificationCode")){
				resultVo.setUrl(getBasePath() + "/toVerificationCode.do");
			}
		}else{
			resultVo.setStatus("error");
			if(result == 1){
				resultVo.setInfoStr("找不到场次信息，请重新操作！");
			}else if(result == 2){
				resultVo.setInfoStr("订单信息有误，请重新操作！");
			}else if(result == 3){
				resultVo.setInfoStr("参赛人信息不全，请重新操作！");
			}else{
                resultVo.setInfoStr("本场次购买数量剩余：" + (result-4));
            }
		}

		return resultVo;
	}

    /**
     * 退款
     * @param id
     * @return
     */
	@RequestMapping("isRefund")
    @ResponseBody
	public ResultVo isRefund(String id){
        ResultVo resultVo = new ResultVo();

        OrderInfo info = this.findByPK(id);
        if(info != null && info.getStatus().equals("1")) {
            if("1".equals(info.getOrderSource())) {
                int result = this.getService().refund(info);
                if (result > 0) {
                    resultVo.setStatus("success");
                    resultVo.setInfoStr("退款成功！");
                    return resultVo;
                }
            }
			if("0".equals(info.getOrderSource())){
				Pay.refundApply(info);
                resultVo.setStatus("success");
                resultVo.setInfoStr("退款申请成功，待微信退款！");
                return resultVo;
			}
        }else{
            resultVo.setStatus("error");
            resultVo.setInfoStr("该订单不允许退款！");
            return resultVo;
        }
        return resultVo;
    }
}
