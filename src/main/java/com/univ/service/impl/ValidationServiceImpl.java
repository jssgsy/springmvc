package com.univ.service.impl;

import org.springframework.stereotype.Service;

import com.univ.domain.ValidationDemo;
import com.univ.service.ValidationService;

/**
 * @author univ
 * @date 2019/11/8 8:10 PM
 * @description
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validParamObject(ValidationDemo validationDemo) {

    }

    @Override
    public void validParamPrimitive(String name, int age) {

    }

    @Override
    public int validReturnValud() {

        return 0;
    }
}
