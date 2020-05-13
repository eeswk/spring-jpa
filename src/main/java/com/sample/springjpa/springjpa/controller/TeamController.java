package com.sample.springjpa.springjpa.controller;

import com.sample.springjpa.springjpa.controller.dto.TeamDTO;
import com.sample.springjpa.springjpa.controller.dto.TeamReq;
import com.sample.springjpa.springjpa.entity.Team;
import com.sample.springjpa.springjpa.repository.TeamRepository;
import com.sample.springjpa.springjpa.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<?> creatge(@RequestBody TeamReq teamReq) throws URISyntaxException {

        teamRepository.save(teamReq.toEntity());

        /*URI location = new URI("/team/" + teamReq.getId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);*/
        return new ResponseEntity("{}", HttpStatus.CREATED);
    }

    @GetMapping("/teams")
    public List<TeamDTO> list() {
        return teamService.findAll();
    }
}
