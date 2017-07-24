package com.demo;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
* Created by zhangxs7 on 2017/6/22.
*/
@Configuration
@ImportResource(locations={"classpath*:applicationContext-tx.xml",
        "classpath*:applicationContext-manager.xml",
        "classpath*:applicationContext-service.xml"})
public class DataBaseConfig {
    private final Logger logger = Logger.getLogger(DataBaseConfig.class);

//    @Autowired
//    private Environment env;
//
//    /**
//     * 创建数据源
//     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
//     */
//    @Bean
////    @Primary
//    public DataSource dataSource() throws Exception{
//        Properties props = new Properties();
//        props.put("driverClassName", env.getProperty("spring.datasource.driverClassName"));
//        props.put("url", env.getProperty("spring.datasource.url"));
//        props.put("username", env.getProperty("spring.datasource.username"));
//        props.put("password", env.getProperty("spring.datasource.password"));
//        return DruidDataSourceFactory.createDataSource(props);
//    }

//    @Bean
////    @Primary
//    public DataSource dataSource2() throws Exception{
//        Properties props = new Properties();
//        props.put("driverClassName", env.getProperty("spring.db2.datasource.driverClassName"));
//        props.put("url", env.getProperty("spring.db2.datasource.url"));
//        props.put("username", env.getProperty("spring.db2.datasource.username"));
//        props.put("password", env.getProperty("spring.db2.datasource.password"));
//        return DruidDataSourceFactory.createDataSource(props);
//    }

//    /**
//     * 根据数据源创建SqlSessionFactory
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
//        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
//        fb.setConfigLocation(new ClassPathResource(env.getProperty("mybatis.configLocation")));
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
//        return fb.getObject();
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory2(DataSource ds) throws Exception{
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
//        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
//        fb.setConfigLocation(new ClassPathResource(env.getProperty("mybatis.configLocation")));
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.db2.mapperLocations")));//指定xml文件位置
//        return fb.getObject();
//    }
}
