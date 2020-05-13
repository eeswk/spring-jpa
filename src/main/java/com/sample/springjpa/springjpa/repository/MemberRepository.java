package com.sample.springjpa.springjpa.repository;

import com.sample.springjpa.springjpa.entity.Member;
import com.sample.springjpa.springjpa.entity.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
    Page<Member> findByName(String name, Pageable pageable);


    @Query("select new com.sample.springjpa.springjpa.entity.MemberDTO(m.id, m.name, m.team.name) from Member m where m.name = :username")
    Page<MemberDTO> findByUsername1(String username, Pageable pageable);

    @Query("select m.name from Member m where m.name IN ('hello1') group by m.name")
    Page<Member> findByUsernameGroupby(Pageable pageable);


}
