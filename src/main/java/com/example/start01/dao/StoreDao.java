package com.example.start01.dao;

import com.example.start01.dto.StoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreDao {
    List<StoreDao> StoreAll(Map<String, Integer> map);

    void StoreAll(StoreDto store);

}
