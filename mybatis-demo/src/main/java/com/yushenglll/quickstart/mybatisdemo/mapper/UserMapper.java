package com.yushenglll.quickstart.mybatisdemo.mapper;

import com.yushenglll.quickstart.mybatisdemo.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
}
