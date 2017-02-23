package com.ffzx.kart.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ffzx.kart.util.SerialCodeGenerator;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.GameMapper;
import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.OrderDetail;
import com.ffzx.kart.model.OrderDetailExample;
import com.ffzx.kart.model.OrderDetailExample.Criteria;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.model.OrderInfoExample;
import com.ffzx.kart.model.User;
import com.ffzx.kart.util.CodeGenerator;
import com.ffzx.kart.util.DateUtil;
import com.ffzx.kart.vo.GameModel;
import com.ffzx.kart.vo.GameUserInfoModel;
import com.ffzx.kart.service.GameService;
import com.ffzx.kart.service.MemberInfoService;
import com.ffzx.kart.service.OrderDetailService;
import com.ffzx.kart.service.OrderInfoService;

@Service
public class GameServiceImpl extends BaseServiceImpl<Game,String> implements GameService {
	
	@Resource
	protected GameMapper mapper;
	
	@Resource
	private OrderDetailService orderDetailService;
	
	@Resource
	private OrderInfoService orderInfoService;
	
	@Resource
	private MemberInfoService memberInfoService;

	@Resource
	private SerialCodeGenerator serialCodeGenerator;
	
	@Override
    public GameMapper getMapper() {
        return mapper;
    }

	
	@Override
	@Transactional
	public String saveOrUpdate(GameModel gameModel,List<Game> dataList,User user)throws Exception {
		String msg=createGame(gameModel,dataList,user);
		return msg;
	}
	
	private String createGame(GameModel gameModel,List<Game> dataList,User user)throws ParseException{
		StringBuffer errorMsg = new StringBuffer();
		StringBuffer successMsg = new StringBuffer();
		StringBuffer notEffectiveMsg = new StringBuffer();
		Map<String,Object> mapExist = new HashMap<String,Object>();//生成场次检查mapExist有没有保存的记录,有则跳过不生成数据
		Map<String,Object> mapNotExist = new HashMap<String,Object>();
		
		int errorNum=0;
		int successNum=0;
		int effectiveNum=0;
		
		
		//首次查询如果存在则保存mapExist,并且从集合里面删除,DB不删除
		//否则保存到notExist集合不删除,DB删除
		//同一天的数据只查一次
		if(dataList!=null && dataList.size()>0){
			for(int i=0;i<dataList.size();i++){
				Game game=dataList.get(i);
				
				Date addDate = DateUtil.parseDate(game.getEffectiveTime());
				
				//判断当前日期在不在可用范围之内
				//不在则条过
				int weekDay = DateUtil.getDay(addDate);
				//由于jdk时间转换周日为0，所以重新赋值
				if(weekDay==0){
					weekDay=7;
				}
				
				if(!rangeInDefined(weekDay, gameModel.getCanUseStratTime(), gameModel.getCanUseEndTime())){
					dataList.remove(i);
					continue;
				}
				
				if(mapExist.get(game.getEffectiveTime())!=null){
					dataList.remove(i);
					continue;
				}
				
				
				if(mapNotExist.get(game.getEffectiveTime())!=null){
					deleteById(dataList.get(i).getId());
					continue;
				}
				
				OrderInfoExample example = new OrderInfoExample();
				com.ffzx.kart.model.OrderInfoExample.Criteria orderInfoCriteria=example.createCriteria();
				orderInfoCriteria.andStartTimeGreaterThanOrEqualTo(DateUtil.parse(game.getEffectiveTime()+" 00:00:00"));
				orderInfoCriteria.andStartTimeLessThanOrEqualTo(DateUtil.parse(game.getEffectiveTime()+" 23:59:59"));
				int orderInfoCount= orderInfoService.countByExample(example);
				
				if(orderInfoCount>0){
					if(mapExist.get(game.getEffectiveTime())==null){
						mapExist.put(game.getEffectiveTime(), game);
						dataList.remove(i);
					}
					continue;
				}
				deleteById(dataList.get(i).getId());
				mapNotExist.put(game.getEffectiveTime(), game);
			}
		}
		String enddateStr = DateUtil.getDateAddOne(gameModel.getEndDate());
		int dayNum=DateUtil.getDateNum(gameModel.getStartDate(),enddateStr);
		if(dayNum>0){
			Date addDate = new Date();
			int singleGameTime = Integer.valueOf(gameModel.getSingleGameTime())*60;//玩的时间
			int intervalTime = Integer.valueOf(gameModel.getIntervalTime())*60; //间隔时间
			for(int i=0;i<dayNum;i++){
				//获取当前循环的日期
				if(i==0){
					addDate = DateUtil.parseDate(gameModel.getStartDate());
				}else{
					addDate = DateUtil.add(addDate, 1);
				}
				
				//判断当前日期在不在可用范围之内
				//不在则条过
				int weekDay = DateUtil.getDay(addDate);
				//由于jdk时间转换周日为0，所以重新赋值
				if(weekDay==0){
					weekDay=7;
				}
				
				String effectiveTime = DateUtil.formatDate(addDate);
				if(!rangeInDefined(weekDay, gameModel.getCanUseStratTime(), gameModel.getCanUseEndTime())){
					/*if(mapExist.get(effectiveTime)!=null){
						notEffectiveMsg.append(effectiveTime+" , ");
						effectiveNum++;
					}*/
					continue;
				}
				
				//生成场次检查mapExist有没有保存的记录,有则跳过不生成数据
				
				if(mapExist.get(effectiveTime)!=null){
					errorMsg.append(effectiveTime+" , ");
					errorNum++;
					continue;
				}else{
					successNum++;
				}
				
				Map<String,Integer> indexMap = new HashMap<String,Integer>();
				
				initParseGame(addDate,singleGameTime,intervalTime,gameModel.getMorningStartTime(),gameModel.getMorningEndTime(),gameModel,indexMap,user); //上午场次
				
				initParseGame(addDate,singleGameTime,intervalTime,gameModel.getAfternoonStartTime(),gameModel.getAfternoonEndTime(),gameModel,indexMap,user); //下午场次
				
				initParseGame(addDate,singleGameTime,intervalTime,gameModel.getNightStartTime(),gameModel.getNightEndTime(),gameModel,indexMap,user); //晚上场次
				
			}
		}
		
		if(successNum>0){
			successMsg.append("共"+successNum+"天成功生成场数 </br>");
		}
		
		if(errorNum>0){
			errorMsg.append("等日期共"+errorNum+"天已经有下单记录不能修改,请手动编辑</br>");
		}
		
		/*if(effectiveNum>0){
			notEffectiveMsg.append("等日期共"+effectiveNum+"天已经有下单记录但是不在有效期内,所以不能覆盖记录</br>");
		}*/
		
		String msg=successMsg.toString()+errorMsg.toString()+notEffectiveMsg.toString();
		
		return errorNum+effectiveNum>0?msg:"success";
		
	}
	
	public void initParseGame(Date addDate,int singleGameTime,int intervalTime,String gameStartTime,String gameEndTime,GameModel gameModel,Map<String,Integer> indexMap,User user)throws ParseException{
		if(StringUtils.isEmpty(gameStartTime) || StringUtils.isEmpty(gameEndTime)){
			return ;
		}
		//算出上午有多少场和没一场的开始时间和结束时间 
		gameStartTime+=":00";
		gameEndTime+=":00";
		String currDateStr =  DateUtil.formatDate(addDate);
		long morningStartTimeLong = DateUtil.dateTurnLong(currDateStr+" "+gameStartTime);
		long morningEndTimeLong = DateUtil.dateTurnLong(currDateStr+" "+gameEndTime);
		
		long countMornintTimeUse = morningEndTimeLong-morningStartTimeLong;
		
		long gameNum=countMornintTimeUse/(singleGameTime+intervalTime); //场次
		int gameNmu=indexMap.get("index")==null?1:(int)indexMap.get("index");
		String gameTimeStr="";
		for(int j=0;j<gameNum;j++){
			if(j==0){
				currDateStr+=" "+gameStartTime; //当前场次开始时间
			}else{
				currDateStr =  DateUtil.formatDate(addDate);
				currDateStr+=" "+gameTimeStr;
			}
			Date currDate = DateUtil.parse(currDateStr);
			long currDateLong = DateUtil.dateTurnLong(currDateStr);
			
			currDateLong+=singleGameTime;
			String resultDateStr = DateUtil.longTurnDate(currDateLong);
			Date resultDate = DateUtil.parse(resultDateStr); //当前场次结束时间
			
			currDateLong+=(long)intervalTime;
			
			String nextTimeStartStr = DateUtil.longTurnDate(currDateLong);
			Date nextTimeStart = DateUtil.parse(nextTimeStartStr); //当前场次结束时间
			
			gameTimeStr = DateUtil.formatTime(nextTimeStart);
			Game game=initGame(gameModel, currDate, resultDate,gameNmu,user);
			add(game);
			gameNmu++;
		}
		indexMap.put("index", gameNmu);
	}
	
	public  boolean rangeInDefined(int current, int min, int max){  
		if(current>=min && current<=max){
			return true;
		}
        return false;  
    }  
	
	private Game initGame(GameModel gameModel,Date startDate,Date endDate,int index,User user){
		Date date = new Date();
		Game game = new Game();
		game.setId(CodeGenerator.getUUID());
		game.setCode(CodeGenerator.code());
		game.setGameNum(index>=10?String.valueOf(index):"0"+index);
		game.setTime(gameModel.getSingleGameTime());
		game.setRetailPrice(gameModel.getPrice());
		game.setPreferentialPrice(gameModel.getPreferentialPrice());
		game.setParticipantsNumber("0");
		game.setBespeakNum(gameModel.getBespeakNum());
		game.setType(gameModel.getType());
		game.setPredeterminedState(gameModel.getPredeterminedState());
		game.setStatus("0");
		game.setStartTime(startDate);
		String effectiveTime = DateUtil.formatDate(endDate);
		game.setEffectiveTime(effectiveTime);
		String gameTime=DateUtil.formatTime(startDate);
		game.setGameTime(gameTime);
		game.setEndTime(endDate);
		String timeAndMinute = DateUtil.formatTimeHourAndMinute(startDate);
		game.setName(gameModel.getName()+" "+timeAndMinute);
		game.setCreateBy(user.getCode());
		game.setLastUpdateBy(user.getCode());
		game.setCreateDate(date);
		game.setLastUpdateDate(date);
		return game;
	}


	@Override
	public List<GameUserInfoModel> getGameUserInfoModelList(Game entity) throws Exception {
		OrderDetailExample example = new OrderDetailExample();
		Criteria orderDetailCriteria=example.createCriteria();
		orderDetailCriteria.andGameCodeEqualTo(entity.getCode());
		orderDetailCriteria.andStatusEqualTo("0");
		example.setOrderByClause("number*1");
		List<OrderDetail> orderDetailList =  orderDetailService.selectByExample(example);
		
		List<GameUserInfoModel> gameUserInfoModelList = orderDetailService.getGameUserInfoModelList(orderDetailList);
		
		if(gameUserInfoModelList.size()>0){
			return gameUserInfoModelList;
		}
		return null;
	}



	@Override
	public synchronized void updateBespeak(OrderInfo order,int type) {
		Game game=findByCode(order.getGameCode());
		if(game!=null){
			String participantsNumber=game.getParticipantsNumber();
			String buyCount=order.getBuyCount();
			if(org.apache.commons.lang3.StringUtils.isBlank(participantsNumber)){
				participantsNumber="0";
			}
			if(org.apache.commons.lang3.StringUtils.isBlank(buyCount)){
				buyCount="0";
			}
			if(type==0){
				game.setParticipantsNumber(String.valueOf(Integer.parseInt(participantsNumber)+Integer.parseInt(buyCount)));
			}else if(type==1){
				game.setParticipantsNumber(String.valueOf(Integer.parseInt(participantsNumber)-Integer.parseInt(buyCount)));
			}
			updateSelective(game);
		}
	}

	@Override
	public int add(Game entity) {
		entity.setCode(serialCodeGenerator.getNum("game",0));
		return super.add(entity);
	}

	@Override
	public String findMaxSeq() {
		OrderInfoExample example=new OrderInfoExample();
		example.setOrderByClause("code desc");
		PageHelper.startPage(1,1);
		PageHelper.orderBy("code desc");
		List<Game> list=selectByExample(example);
		if(list!=null&&list.size()>0){
			String code=null;
			code=list.get(0).getCode();
			code.substring(code.length()-4);
			code=String.format("%04d",Integer.valueOf(code)+1);
			return code;
		}
		return null;
	}
}
