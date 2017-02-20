//轮询主线程
package com.ffzx.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskWorker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run() {
        TimeTask<?> task = null;
        int interval = 0;
        task = TaskDataHolder.queue.poll();

        if (task != null) {
            Date currentTime = new Date();
            Date lastExcueteTime = task.getLastExcuteTime();
            Date nextExcuteTime = task.getNextExcuteTime();
            interval = task.getInterval();
            try {

                if (interval > 0) {
                    if ((nextExcuteTime.before(currentTime) && currentTime.after(TaskExcutor.calcNextExcuteTime(lastExcueteTime, interval)))) {// 执行时间在最后执行时间之后
                        task.getTask().excuete();
                        taskUpdate(task);
                        TaskExcutor.add(task);
                    } else {
                        TaskExcutor.add(task);
                    }

                }

            } catch (Exception e) {
                logger.info("task exucete fail :", e);
                taskUpdate(task);
                TaskExcutor.add(task);
            }
        }

    }

    private void taskUpdate(TimeTask<?> task) {
        Date currentTime = new Date();
        Date lastExcuteTime = task.getLastExcuteTime();
        task.setNextExcuteTime(TaskExcutor.calcNextExcuteTime(currentTime, task.getInterval()));
        task.setLastExcuteTime(currentTime);
        logger.info("createTime : " + date2Str(task.getCreateTime()) + ",lastExcuteTime :" + date2Str(task.getLastExcuteTime()) + " ,nextExcuteTime : " + date2Str(task.getNextExcuteTime()) + ", interval :" + task.getInterval());
    }

    public String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }

}