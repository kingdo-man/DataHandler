package com.me.datahandler;

import com.me.datahandler.dao.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DataHandleerApplicationTests {

    @Autowired
    private TestMapper testMapper;

    private static Integer MAX_ID = 132496725; // 从数据库查询
    /*static {
        MAX_ID = testDao.getMaxId();
    }*/
    private synchronized List<Integer> getStartId() {
        int y;
        int x = MAX_ID;
        if (MAX_ID-5000 <= 0) {
            y = 0;
        } else {
            y = MAX_ID-5000 <= 0?0:MAX_ID-5000;
            MAX_ID -= 5000;
        }
        List<Integer> list = new ArrayList<>();
        list.add(y);
        list.add(x);
        return list;
    }

    @Test
    void test() {
        long l0 = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        int num = MAX_ID % 5000 == 0 ? MAX_ID / 5000 : MAX_ID / 5000 + 1;
        CountDownLatch downLatch = new CountDownLatch(num);
        for (int i = 0;i < num;i ++){
            //while (MAX_ID > 0) {
            fixedThreadPool.submit(() -> {
                try {
                    long l1 = System.currentTimeMillis();
                    List<Integer> startId = getStartId();
                    Integer st = startId.get(0);
                    Integer en = startId.get(1);
                    //System.out.println(Thread.currentThread().getName() + " query id from:" + (st+1) +" to " + en);
                    // 查询数据获取Map
                    List<Map<String, Object>> list =  testMapper.getDataByIds(st,en);
                    if (list != null && list.size() > 0) {
                        Map<Integer,String > newMap = new HashMap<>();
                        for (Map<String, Object> map : list) {
                            Integer id = Integer.valueOf(map.get("id") + "");
                            String category =map.get("category")+"";
                            if (StringUtils.isBlank(category)) {
                                category += "ceshi";
                            } else {
                                category += ",ceshi";
                            }
                            newMap.put(id,category.replace("null,",""));
                        }
                        System.out.println("newMap size ========== " +newMap.size());
                        int x = testMapper.updateByIds(newMap);
                        long l2 = System.currentTimeMillis();
                        System.out.println(Thread.currentThread().getName() + " 成功更新 " + x + "条数据！！！！！用时：" + (l2-l1));
                    }
                } finally {
                    downLatch.countDown();
                }
            });
        }
        try {
            downLatch.await();
            fixedThreadPool.shutdown();
            long l3 = System.currentTimeMillis();
            System.out.println("处理数据：132496725 条，用时"+ (l3-l0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}