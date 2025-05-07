package com.yushenglll.quickstart.mybatisdemo.mapper;

import com.yushenglll.quickstart.mybatisdemo.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    /**
     * 查询所有的 Brand 数据
     */
    List<Brand> selectAllBrand();

    /**
     * 根据id 查询某一个 brand 的数据
     */
    Brand selectBrandById(int id);

    /**
     * 条件查询
     * 1. 散装参数
     * 2. 对象参数
     * 3. map集合参数
     * @param status
     * @param companyName
     * @param brandName
     * @return
     */
//    List<Brand> selectByCondition(int status, String companyName, String brandName);

//    // 将多个查询条件封装成一个对象
//    List<Brand> selectByCondition(Brand brand);


    // 注意使用Map 方法的时候，需要确保SQL 语句中的占位符的名称和 Map 的key的名称一样
    List<Brand> selectByCondition(Map map);

    /**
     * 由于用户可以动态选择传入的参数
     * 最好的方式是通过Brand传递参数
     * @return
     */
    List<Brand> selectBySingleCondition(Brand brand);

    /**
     * 添加一个新的brand
     * @param brand
     */
    void add(Brand brand);


    void update(Brand brand);

    /**
     * 根据 id 数组批量删除
     * 包括一个id 和 多个 id
     * 不清楚为什么现在不加Param 成功运行，
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);
}
