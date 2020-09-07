package com.me.datahandler.dao.mapper;

import com.me.datahandler.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TestMapper {
    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    Integer getMaxId();

    List<Map<String, Object>> getDataByIds(@Param("st") Integer st, @Param("en") Integer en);

    Integer updateByIds(@Param("map") Map<Integer, String> map);
}