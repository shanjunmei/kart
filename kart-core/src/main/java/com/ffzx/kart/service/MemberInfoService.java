package com.ffzx.kart.service;

import com.ffzx.common.service.BaseService;
import com.ffzx.kart.model.MemberInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface MemberInfoService extends BaseService<MemberInfo,String>{

    /**
     * 查询或保存用户信息
     * @param memberInfo
     * @return
     */
    public MemberInfo findOrSave(MemberInfo memberInfo);
}
