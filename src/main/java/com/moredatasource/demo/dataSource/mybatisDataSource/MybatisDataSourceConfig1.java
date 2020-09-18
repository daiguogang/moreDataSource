package com.moredatasource.demo.dataSource.mybatisDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//增加一个SpringBoot配置类
//        mybatis多数据源的原理是根据不同包，调用不同的数据源，你只需要把你的mapper.java和mapper.xml(我喜欢叫dao.java和dao.xml)写在某个package中，springboot自动帮你实现数据源切换
//        核心代码就这句
//@MapperScan(basePackages ="com.web.ds2.**.dao", sqlSessionTemplateRef = "ds2SqlSessionTemplate")
//用来指定包扫描指定sqlSessionTemplateRef
//        和sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/web/ds2/**/*.xml"));
//        用来指定mapper.xml的路径

/**
 * Mybatis主数据源ds1配置
 * 多数据源配置依赖数据源配置
 *
 * @see MybatisDataSourceConfig1
 */
@Configuration
@MapperScan(basePackages = "com.moredatasource.demo.dataSource.mybatisDataSource.mybatisDao1", sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class MybatisDataSourceConfig1 {

    // @Primary 表示这个数据源是默认数据源
    @Primary
    // @Bean 将返回的这个对象放到spring容器中
    @Bean("ds1SqlSessionFactory")
    // Qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mybatisMapper1/**/*.xml"));
        return sqlSessionFactory.getObject();
    }

    // 事务支持
    @Primary
    @Bean(name = "ds1TransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "ds1SqlSessionTemplate")
    // 获取mybatis 的核心对象 SqlSessionTemplate
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
