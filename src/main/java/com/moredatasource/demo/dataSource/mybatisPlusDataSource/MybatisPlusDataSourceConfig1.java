package com.moredatasource.demo.dataSource.mybatisPlusDataSource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
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


/**
 * mybatis-plus 主数据源ds1配置
 * 多数据源配置依赖数据源配置
 *  mybatis-plus是mybatis的增强版，只增加，不影响。也就是说使用mybatis-plus兼容原来所有的mybatis代码和配置。
 * 然后又做了很多增加和简化使用，具体看官网教程https://mybatis.plus/
 * @see MybatisPlusDataSourceConfig1
 */
@Configuration
@MapperScan(basePackages = "com.moredatasource.demo.dataSource.mybatisPlusDataSource.mybatisPlusDao1", sqlSessionTemplateRef = "plusDs1SqlSessionTemplate")
public class MybatisPlusDataSourceConfig1 {

    // @Primary 表示这个数据源是默认数据源
    @Primary
    // @Bean 将返回的这个对象放到spring容器中
    @Bean("plusDs1SqlSessionFactory")
    // Qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mybatisPlusMapper1/**/*.xml"));
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
//                        .setFormat(true),
        });
//        sqlSessionFactory.setGlobalConfig(new GlobalConfig().setBanner(false));
        return sqlSessionFactory.getObject();
    }

    // 事务支持
    @Primary
    @Bean(name = "plusDs1TransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "plusDs1SqlSessionTemplate")
    // 获取mybatis 的核心对象 SqlSessionTemplate
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("plusDs1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
