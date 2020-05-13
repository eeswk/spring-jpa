package com.sample.springjpa.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member > members;// = new ArrayList<>();

    @Builder
    public Team(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        if (members == null) {
            members = new ArrayList<>();
        }
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        List<Member> members = getMembers();
        members.add(member);
    }

}
