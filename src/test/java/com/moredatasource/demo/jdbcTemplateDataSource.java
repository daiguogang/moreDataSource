package com.moredatasource.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@SpringBootTest
public class jdbcTemplateDataSource {

    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("ds2JdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Test
    public void testJdbcTemplae() {
        Map<String,Object> result = jdbcTemplate1.queryForMap("SELECT * FROM product_info where product_id = 3");
        System.out.println("jdbcTemplate数据源1："+ result);

        Map<String,Object> result2 = jdbcTemplate2.queryForMap("SELECT * FROM employees where id = 2");
        System.out.println("jdbcTemplate数据源2："+ result2);
    }
}
