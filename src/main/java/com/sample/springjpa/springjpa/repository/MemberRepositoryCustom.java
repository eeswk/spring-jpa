package com.sample.springjpa.springjpa.repository;

import com.sample.springjpa.springjpa.entity.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    MemberDTO getMemberQuerydsl(Long id);

    Page<MemberDTO> getMemberList(Pageable pageable);
}
