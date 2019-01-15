package com.sample.springjpa.springjpa.controller;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.MemberDTO;
import com.sample.springjpa.springjpa.entity.QMember;
import com.sample.springjpa.springjpa.repository.MemberRepository;
import com.sample.springjpa.springjpa.repository.MemberRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class MemberController {


    @Autowired
    MemberRepository repository;

    @Autowired
    MemberRepositoryCustom repositoryCustom;

    @RequestMapping("/")
    public String index() {
        System.out.println("hello world!");
        return "hello world";
    }
    @PostConstruct
    public void init() {
        repository.save(new Member("hello1", 1));
        repository.save(new Member("hello2", 2));
        repository.save(new Member("hello3", 3));
        repository.save(new Member("hello4", 4));
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
    public List<Member> members() {
        return repository.findAll();
    }

    @RequestMapping("/members/{memberId}")
    public Member member(@PathVariable("memberId") Member member) { return member;}

    @RequestMapping("/member/name/{username}")
    public Page<Member> getMember(@PathVariable("username") String username) {
        PageRequest pageRequest = new PageRequest(0, 10);
        return repository.findByUsername1(username, pageRequest);
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

    @RequestMapping("/member/{id}")
    public @ResponseBody MemberDTO getMemberByQueryDSL(@PathVariable("id") Long id) {
        return repositoryCustom.getMemberQuerydsl(id);
    }

}

