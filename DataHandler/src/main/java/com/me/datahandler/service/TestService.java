package com.me.datahandler.service;

import com.me.datahandler.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestMapper mapper;

    public List<Map<String, Object>> getData(Integer st, Integer en){
        List<Map<String, Object>> dataByIds = mapper.getDataByIds(st, en);
        System.out.println(dataByIds.size());
        return dataByIds;
    }
}
