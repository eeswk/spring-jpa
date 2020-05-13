package com.sample.springjpa.springjpa.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String name;
    private int age;


    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Member(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
