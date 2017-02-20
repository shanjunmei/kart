package com.ffzx.service;

import java.util.List;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.Game;
import com.ffzx.kart.model.OrderInfo;
import com.ffzx.kart.model.User;
import com.ffzx.kart.vo.GameModel;
import com.ffzx.kart.vo.GameUserInfoModel;

public interface GameService extends BaseService<Game,String>{

	public String saveOrUpdate(GameModel gameModel,List<Game> dataList,User user)throws Exception;
	
	public List<GameUserInfoModel> getGameUserInfoModelList(Game entity)throws Exception;

	/**
	 * 根据订单更新场次已预订数
	 * @param order
	 * @param type 0 为加，1为减
	 */
	void updateBespeak(OrderInfo order,int type);

	public String findMaxSeq();
}
