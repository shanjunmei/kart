package com.ffzx.kart.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.ActivityMapper;
import com.ffzx.kart.model.Activity;
import com.ffzx.kart.service.ActivityService;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity,String> implements ActivityService {
	
	@Resource
	protected ActivityMapper mapper;
	
	@Override
    public ActivityMapper getMapper() {
        return mapper;
    }

}
