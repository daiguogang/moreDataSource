package com.moredatasource.demo.dataSource.mybatisPlusDataSource.mybatisPlusDao1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ProductPlusMapper extends BaseMapper<Map<String,Object>> {

    Map<String,Object> getProductInfo();
}
