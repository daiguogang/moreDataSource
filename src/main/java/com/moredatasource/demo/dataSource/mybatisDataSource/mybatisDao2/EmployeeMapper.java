package com.moredatasource.demo.dataSource.mybatisDataSource.mybatisDao2;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface EmployeeMapper {
    Map<String,Object> getEmployeeInfo();
}
