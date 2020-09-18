package com.moredatasource.demo.dataSource.mybatisDataSource.mybatisDao1;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {

    Map<String,Object> getProductInfo();
}
