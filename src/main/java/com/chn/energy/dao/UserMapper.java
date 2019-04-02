package com.chn.energy.dao;

import com.chn.energy.model.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    int delete(Integer id);

    int insert(User record);

    User selectById(Integer id);

    User selectByName(String userName);

    int update(User record);

    List<User> queryList(Map params);

    int count(Map params);
}