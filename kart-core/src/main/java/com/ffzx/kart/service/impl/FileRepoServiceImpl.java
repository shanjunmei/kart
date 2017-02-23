package com.ffzx.kart.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.kart.mapper.FileRepoMapper;
import com.ffzx.kart.model.FileRepo;
import com.ffzx.kart.service.FileRepoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class FileRepoServiceImpl extends BaseServiceImpl<FileRepo, String> implements FileRepoService {

    @Resource
    private FileRepoMapper mapper;

    @Override
    public FileRepoMapper getMapper() {
        return mapper;
    }
}
