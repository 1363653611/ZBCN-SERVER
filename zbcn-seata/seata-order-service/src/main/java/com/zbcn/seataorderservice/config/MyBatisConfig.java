package com.zbcn.seataorderservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *  mybatis 的扫描路径
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/21 13:45
 */
@Configuration
@MapperScan({"com.zbcn.seataorderservice.dao"})
public class MyBatisConfig {
}
