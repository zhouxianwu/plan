package com.chn.energy.service.impl;

import com.chn.energy.dao.DeptMapper;
import com.chn.energy.model.Dept;
import com.chn.energy.model.DeptExample;
import com.chn.energy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouxianwu on 2019/3/28.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDept() {
        DeptExample example = new DeptExample();
        DeptExample.Criteria criteria = example.createCriteria();
        return deptMapper.selectByExample(example);
    }
}
