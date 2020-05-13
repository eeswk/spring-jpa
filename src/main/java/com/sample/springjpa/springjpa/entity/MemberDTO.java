package com.sample.springjpa.springjpa.entity;

import com.querydsl.core.annotations.QueryProjection;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;
    private String name;

    private String teamName;

    @QueryProjection
    public MemberDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberDTO() {
    }

    public MemberDTO(Long id, String name, String teamName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
