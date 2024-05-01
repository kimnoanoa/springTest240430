package com.ezen.www.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableScheduling
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.ezen.www.repository"})
public class RootConfig {
   
   
   @Autowired
   ApplicationContext applicationContext;
   
   @Bean
   public DataSource dataSource() {
      HikariConfig hikariConfig =new HikariConfig();
      //log4jdbc => DB의 로그를 찍을 수 있는 드라이버 설정 변경
      hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
      hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtest");
      hikariConfig.setUsername("springUser");
      hikariConfig.setPassword("mysql");
      
      
      //-----여기서부터 hikari 추가 설정
      hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
      hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수 (Max와 같은 갯수로 설정)
      
      hikariConfig.setConnectionTestQuery("SELECT now()"); //test 쿼리문
      hikariConfig.setPoolName("springHikaiCP");
      //추가설정
            hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", hikariConfig);
            // mysql 드라이버가 연결당 cache 사이즈 : 250~500 사이 권장
            hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSize", "250");
            // connection 당 캐싱힐 prepareStatement 의 개수 지정 옵션 : default 250
            hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSqlLimit", "true"); //기본값 설정
            //mysql 서버에서 최신 이슈가 있을 경우 지원을 받을 것인지 결정
            hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
            
            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
            
            return hikariDataSource;
            
   }
   
   @Bean
   public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sqlFactorybean =  new SqlSessionFactoryBean();
      sqlFactorybean.setDataSource(dataSource());
      sqlFactorybean.setMapperLocations(
            applicationContext.getResources("classpath:/mappers/*.xml"));
      //DB : _(스네이크 표기법) / JAVA : 카멜 표기법
      //file_name =>fileName
      //별칭 설정
      
      /*
       * sqlFactorybean.setConfigLocation(
       * applicationContext.getResources("classpath:/MybatisConfig.xml"));
       */
      sqlFactorybean.setConfigLocation(
            applicationContext.getResource("classpath:/MybatisConfig.xml"));

      
      return sqlFactorybean.getObject();
            
   }
   
   //트랜젝션 메니저 설정
   
   @Bean
   public DataSourceTransactionManager transactionManager() {
      return new DataSourceTransactionManager(dataSource());
   }

}