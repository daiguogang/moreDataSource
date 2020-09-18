package com.moredatasource.demo;

import com.moredatasource.demo.dataSource.mybatisPlusDataSource.mybatisPlusDao1.ProductPlusMapper;
import com.moredatasource.demo.dataSource.mybatisPlusDataSource.mybatisPlusDao2.EmployeePlusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class MybatisPlusDataSource {

    @Autowired
    ProductPlusMapper productPlusMapper;

    @Autowired
    EmployeePlusMapper employeePlusMapper;

    @Test
    public void mybatisDataSourceTest() {
//        Map<String, Object> result1 = productMapper.getProductInfo();
        Map<String, Object> result1 = productPlusMapper.selectById(3);
        System.out.println("result1:" + result1);

//        Map<String, Object> result2 = employeeMapper.getEmployeeInfo();
        Map<String, Object> result2 = employeePlusMapper.selectById(2);
        System.out.println("result2:" + result2);
    }
}
