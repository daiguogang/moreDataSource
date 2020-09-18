package com.moredatasource.demo;

import com.moredatasource.demo.dataSource.mybatisDataSource.mybatisDao1.ProductMapper;
import com.moredatasource.demo.dataSource.mybatisDataSource.mybatisDao2.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class MybatisDataSource {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void mybatisDataSourceTest() {
        Map<String, Object> result1 = productMapper.getProductInfo();
        System.out.println("result1:" + result1);

        Map<String, Object> result2 = employeeMapper.getEmployeeInfo();
        System.out.println("result2:" + result2);
    }
}
