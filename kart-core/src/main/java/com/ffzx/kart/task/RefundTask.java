package com.ffzx.kart.task;

import com.ffzx.service.OrderInfoService;
import com.ffzx.task.Task;
import com.ffzx.task.TaskExcutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/17.
 */
@Component
public class RefundTask implements Task {

    private final static Logger logger = LoggerFactory.getLogger(RefundTask.class);

    @Resource
    private OrderInfoService service;


    @Override
    public void excuete() {
        logger.info("RefundTask excuete");
        try {
            service.refundTask();
        } catch (Exception e) {
            logger.info("task excuete error", e);
        }

    }

    @PostConstruct
    public void init() {
        logger.info("RefundTask init");
        TaskExcutor.createAndAddTask(this, 30*60);
    }
}
