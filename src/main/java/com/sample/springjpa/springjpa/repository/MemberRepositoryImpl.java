package com.sample.springjpa.springjpa.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.MemberDTO;


import com.sample.springjpa.springjpa.entity.QMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public MemberRepositoryImpl() {
        super(Member.class);
    }




    @Override
    public MemberDTO getMemberQuerydsl(Long id) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
        QMember member = QMember.member;
        return queryFactory.select(Projections.constructor(MemberDTO.class, member.id, member.name))
                .from(member)
                .where(member.id.eq(id)).fetchOne();
    }

    @Override
    public Page<MemberDTO> getMemberList(Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
        QMember member = QMember.member;

        QueryResults<MemberDTO> results = queryFactory.select(Projections.constructor(MemberDTO.class))
                .from(member)
                .orderBy(member.name.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
