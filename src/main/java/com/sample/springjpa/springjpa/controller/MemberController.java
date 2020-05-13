package com.sample.springjpa.springjpa.controller;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.springjpa.springjpa.controller.dto.TeamReq;
import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.MemberDTO;
import com.sample.springjpa.springjpa.entity.QMember;
import com.sample.springjpa.springjpa.entity.Team;
import com.sample.springjpa.springjpa.repository.MemberRepository;
import com.sample.springjpa.springjpa.repository.MemberRepositoryCustom;
import com.sample.springjpa.springjpa.repository.TeamRepository;
import com.sample.springjpa.springjpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberController {


    @Autowired
    MemberRepository repository;

    @Autowired
    MemberRepositoryCustom repositoryCustom;

    @Autowired
    MemberService memberService;

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/")
    public String index() {
        System.out.println("hello world!");
        return "hello world";
    }
    @PostConstruct
    public void init() {

        /*
        Team team1 = new Team();
        team1.setName("DEV");

        Team team2 = new Team();
        team2.setName("OP");

        Member member1 = new Member("hello1", 1);
        member1.setTeam(team1);

        Member member2 = new Member("hello2", 2);
        member2.setTeam(team2);


*/
        Team dev = new Team("DEV");
        Team oper = new Team("OPER");
        teamRepository.save(dev);
        teamRepository.save(oper);


        Member member1 = new Member("hello1", 1);
        member1.setTeam(dev);
        repository.save(member1);


        Member member2 = new Member("hello2", 2);
        member2.setTeam(oper);
        repository.save(member2);


        Member member3 = new Member("hello3", 3);
        member3.setTeam(dev);
        repository.save(member3);






/*
        teamRepository.save(team1);
        repository.save(new Member("hello1", 1, team1));
        //repository.save(member1);
        repository.save(new Member("hello2", 2));
        //repository.save(member2);
        repository.save(new Member("hello3", 3));
        repository.save(new Member("hello4", 4));
*/

    }

    @GetMapping("/hello")
    public Member member() {
        return repository.findByName("hello1");
    }

    @RequestMapping("/hello1")
    public Page<Member> search() {
        PageRequest pageRequest = new PageRequest(0, 10);
        return repository.findByName("hello1", pageRequest);
    }


    @RequestMapping("/members")
    public List<MemberDTO> members() {
        List<Member> list = repository.findAll();

        List<MemberDTO> resultList = list.stream().map(m -> new MemberDTO(m.getId(), m.getName(), m.getTeam().getName()))
                .collect(Collectors.toList());
        return resultList;
    }

    @GetMapping("/member/{memberId}")
    public MemberDTO member(@PathVariable("memberId") Long memberId) {
        Member member = repository.findById(memberId).orElse(null);
        System.out.println(member);

        if (member == null) {
            return new MemberDTO();
        }
        return new MemberDTO(member.getId(), member.getName(), member.getTeam().getName());
    }

    @RequestMapping("/member/name/{username}")
    public Page<MemberDTO> getMember(@PathVariable("username") String username) {
        PageRequest pageRequest = new PageRequest(0, 10);
        return repository.findByUsername1(username, pageRequest);
    }

    @RequestMapping("/member/group")
    public Page<Member> getMember() {
        PageRequest pageRequest = new PageRequest(0, 10);
        return repository.findByUsernameGroupby(pageRequest);
    }

    @GetMapping("/member/{memberId}/team")
    public @ResponseBody Team teamInfo(@PathVariable("memberId")Long memberId) throws MalformedURLException {
        Member member = repository.findById(memberId).orElse(null);
        return member.getTeam();
    }



    @PostMapping("/member/{memberId}/team")
    public ResponseEntity<?> updateTeam(@PathVariable("memberId")Long memberId, @RequestBody TeamReq teamReq) throws URISyntaxException {
        System.out.println(memberId);
        System.out.println(teamReq.getName());
        System.out.println(teamReq.getId());
        Team team = new Team(teamReq.getName());
        memberService.memberTeamCreate(memberId, team);

        URI location = new URI("/member/" + memberId + "/team/" + team.getId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity("{}", responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping("/member/{memberId}/team/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable("memberId")Long memberId, @PathVariable("teamId")Long teamId) throws URISyntaxException {

        memberService.memberTeamUpdate(memberId, teamId);

        URI location = new URI("/member/" + memberId + "/team");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity("{}", responseHeaders, HttpStatus.CREATED);
    }



    @PersistenceContext
    EntityManager em;




    @RequestMapping("/hellodsl")
    public List<Member> helloQueryDSL() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMember m = QMember.member;
       return queryFactory.selectFrom(m)
                .where(m.age.lt(18).and(m.name.contains("hello")))
                .orderBy(m.name.desc())
                .fetch();
    }

    @RequestMapping("/memberdsl/{id}")
    public @ResponseBody MemberDTO getMemberByQueryDSL(@PathVariable("id") Long id) {
        return repositoryCustom.getMemberQuerydsl(id);
    }

}


