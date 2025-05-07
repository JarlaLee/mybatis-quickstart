package com.yushenglll.quickstart.mybatisdemo;

import com.yushenglll.quickstart.mybatisdemo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MyBatis 快速入门方法
 */
public class MybatisDemo {

    public static void main(String[] args) throws IOException {
        // 1. 加载 Mybatis 的核心配置文件，获取SqlSessionFatory

        // 定义配置文件的路径
        String resource = "mybatis-config.xml";

        // 返回一个字节输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        // 2. 获取SqlSession的对象 -- 用于执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行 SQL 语句
        List<User> users =  sqlSession.selectList("test.selectAll"); // 传入SQL 语句的 唯一标识。

        System.out.println(users);

        // 4. 释放资源
        sqlSession.close();
    }
}
