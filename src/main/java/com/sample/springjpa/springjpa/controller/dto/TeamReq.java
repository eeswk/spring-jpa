package com.sample.springjpa.springjpa.controller.dto;

import com.sample.springjpa.springjpa.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeamReq {

    private Long id;
    private String name;

    public Team toEntity() {
        return Team.builder()
                .name(name)
                .build();

    }
}

