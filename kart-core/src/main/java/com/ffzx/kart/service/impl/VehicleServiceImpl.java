package com.ffzx.kart.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.VehicleMapper;
import com.ffzx.kart.model.Vehicle;
import com.ffzx.kart.service.VehicleService;

@Service
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,String> implements VehicleService {
	
	@Resource
	protected VehicleMapper mapper;
	
	@Override
    public VehicleMapper getMapper() {
        return mapper;
    }

}
