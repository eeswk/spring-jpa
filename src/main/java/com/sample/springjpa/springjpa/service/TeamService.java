package com.sample.springjpa.springjpa.service;

import com.sample.springjpa.springjpa.controller.dto.TeamDTO;
import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.MemberDTO;
import com.sample.springjpa.springjpa.entity.Team;
import com.sample.springjpa.springjpa.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Transactional(readOnly = true)
    public List<TeamDTO> findAll() {

        List<Team> teams = teamRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<TeamDTO> resultList = teams.stream().map(t -> modelMapper.map(t, TeamDTO.class)).collect(Collectors.toList());

        /*
        List<TeamDTO> resultList = teams.stream()
                .map(t -> {
                    TeamDTO teamDTO = new TeamDTO(t.getId(), t.getName());
                    List<Member> members = t.getMembers();
                    List<MemberDTO> resultMembers = members.stream().map(m -> new MemberDTO(m.getId(), m.getName())).collect(Collectors.toList());
                    teamDTO.setMembers(resultMembers);

                    return teamDTO;
                }).collect(Collectors.toList());

         */
        return resultList;
    }
}
