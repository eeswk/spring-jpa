package com.sample.springjpa.springjpa.service;

import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.Team;
import com.sample.springjpa.springjpa.repository.MemberRepository;
import com.sample.springjpa.springjpa.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    private TeamRepository teamRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }


    @Transactional
    public void memberTeamCreate(Long memberId, Team team) {

        teamRepository.save(team);

        Member member = memberRepository.findById(memberId).get();
        member.setTeam(team);
        memberRepository.save(member);
    }



    @Transactional
    public void memberTeamUpdate(Long memberId, Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        Member member = memberRepository.findById(memberId).get();
        member.setTeam(team);
        memberRepository.save(member);
    }
}
