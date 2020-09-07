package com.me.datahandler.entity;


import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * test
 * @author 
 */
@Component
@Data
public class Test implements Serializable {
    private Integer id;

    private String category;

    private static final long serialVersionUID = 1L;
}