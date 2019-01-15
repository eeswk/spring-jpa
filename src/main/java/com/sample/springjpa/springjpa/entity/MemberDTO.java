package com.sample.springjpa.springjpa.entity;

import com.querydsl.core.annotations.QueryProjection;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;
    private String name;

    @QueryProjection
    public MemberDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
