package com.univ.controller;

import com.univ.domain.Single;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author univ
 * date 2023/10/24
 */
@RestController
@RequestMapping("/db")
public class DBController {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping("/get")
    public List<Single> get() {
        return sqlSessionTemplate.selectList("SingleMapper.getById", 1L);
    }
}
