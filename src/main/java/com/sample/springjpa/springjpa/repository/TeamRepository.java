package com.sample.springjpa.springjpa.repository;

import com.sample.springjpa.springjpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
