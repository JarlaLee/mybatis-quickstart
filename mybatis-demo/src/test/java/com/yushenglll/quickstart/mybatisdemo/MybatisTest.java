package com.yushenglll.quickstart.mybatisdemo;

import com.yushenglll.quickstart.mybatisdemo.mapper.BrandMapper;
import com.yushenglll.quickstart.mybatisdemo.mapper.UserMapper;
import com.yushenglll.quickstart.mybatisdemo.pojo.Brand;
import com.yushenglll.quickstart.mybatisdemo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试代码
 */
public class MybatisTest {

    @Test
    public void testSelectAll() throws Exception {
        // 1. 加载 Mybatis 的核心配置文件，获取SqlSessionFatory

        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的

        List<Brand> brands = brandMapper.selectAllBrand();
        System.out.println(brands);

        // 4. 释放资源
        sqlSession.close();

        // 如果使用Spring 框架之后，这些配置都不需要了
    }


    @Test
    public void testSelectBrandById() throws Exception {
        // 1. 加载 Mybatis 的核心配置文件，获取SqlSessionFatory

        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的

        Brand brand = brandMapper.selectBrandById(1);
        System.out.println(brand);

        // 4. 释放资源
        sqlSession.close();

        // 如果使用Spring 框架之后，这些配置都不需要了
    }
        /**
         * 直接通过多个条件作为参数，查询SQL
         * 这里好像不在需要@Param 注解了
         */
//    @Test
//    public void testSelectByCondition() throws IOException {
//        // 定义测试条件
//        int status = 1;
//        String companyName = "华为";
//        String brandName = "华为";
//
//
//        // 定义配置文件的路径
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        // 2. 获取SqlSession的对象 -- 用于执行Sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3. 执行SQL 语句
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
//
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
//        System.out.println(brands);
//
//        // 4. 释放资源
//        sqlSession.close();
//        // 如果使用Spring 框架之后，这些配置都不需要了
//
//
//        // 不要在java代码中拼接 %companyName%
//        // 将SQL的写在SQL层，将Java写在java层
//    }

    /**
     * 通过将多个条件分装成Brand对象。然后完成SQL 查询任务
     */
//    @Test
//    public void testSelectByCondition() throws IOException {
//        // 定义测试条件
//        int status = 1;
//        String companyName = "华为";
//        String brandName = "华为";
//
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//
//
//        // 定义配置文件的路径
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        // 2. 获取SqlSession的对象 -- 用于执行Sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3. 执行SQL 语句
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
//
//        List<Brand> brands = brandMapper.selectByCondition(brand);
//        System.out.println(brands);
//
//        // 4. 释放资源
//        sqlSession.close();
//        // 如果使用Spring 框架之后，这些配置都不需要了
//
//
//        // 不要在java代码中拼接 %companyName%
//        // 将SQL的写在SQL层，将Java写在java层
//    }


//    /**
//     * 使用 Map 的方式封装查询条件，完成条件查询
//     * @throws IOException
//     */
//    @Test
//    public void testSelectByCondition() throws IOException {
//        // 定义测试条件
//        int status = 1;
//        String companyName = "华为";
//        String brandName = "华为";
//
//        Map map = new HashMap();
//        map.put("status", status);
//        map.put("companyName", companyName);
//        map.put("brandName", brandName);
//
//
//        // 定义配置文件的路径
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        // 2. 获取SqlSession的对象 -- 用于执行Sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3. 执行SQL 语句
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
//
//        List<Brand> brands = brandMapper.selectByCondition(map);
//        System.out.println(brands);
//
//        // 4. 释放资源
//        sqlSession.close();
//    }


    // 现存问题
    // 如果使用静态SQL 语句，对于多个条件的查询情况。如果其中某一个条件为空值，就会导致没有任何的查询结果
    // 但是使用者的意图是 忽略值为空的条件的意思。
    // 这时候需要使用条件查询

    /**
     * 多条件动态条件查询
     * @throws IOException
     */
    @Test
    public void testSelectByCondition() throws IOException {
        // 定义测试条件
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        Map map = new HashMap();
        map.put("status", status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);

        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的

        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectBySingleCondition() throws IOException {
        // 定义测试条件
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        Brand brand = new Brand();
        // brand.setStatus(status);
        brand.setCompanyName(companyName);


        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的

        List<Brand> brands = brandMapper.selectBySingleCondition(brand);
        System.out.println(brands);

        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        // 定义测试条件
        int status = 1;
        int ordered = 150;
        String companyName = "家乐美";
        String brandName = "家乐美";
        String description = "这是李佳乐自己的公司";


        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);


        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        // 可以添加参数，自动开始事务
        // 传递true 参数
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
        brandMapper.add(brand);

        // 提交事务
        sqlSession.commit();

        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd2() throws IOException {
        // 定义测试条件
        int status = 1;
        int ordered = 150;
        String companyName = "家乐美";
        String brandName = "家乐美";
        String description = "这是李佳乐自己的公司";


        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);


        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        // 可以添加参数，自动开始事务
        // 传递true 参数
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
        brandMapper.add(brand);
        System.out.println("新添加数据的id是" + brand.getId());

        // 提交事务
        sqlSession.commit();

        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        // 定义测试条件
        int id = 5;
        int ordered = 100000;
        String companyName = "佳乐美11111";

        Brand brand = new Brand();
        brand.setId(id);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);


        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        // 可以添加参数，自动开始事务
        // 传递true 参数
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
        brandMapper.update(brand);

        // 提交事务
        sqlSession.commit();

        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        // 定义测试条件
        int[] ids = {5};

        // 定义配置文件的路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession的对象 -- 用于执行Sql
        // 可以添加参数，自动开始事务
        // 传递true 参数
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行SQL 语句
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class); //mybatis 实现的
        brandMapper.deleteByIds(ids);

        // 提交事务
        sqlSession.commit();

        // 4. 释放资源
        sqlSession.close();
    }

}
