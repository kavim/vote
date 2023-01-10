package com.api.vote.controllers;

import com.api.vote.models.ElectorModel;
import com.api.vote.services.ElectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/electors")
public class ElectorController {
    final ElectorService electorService;

    public ElectorController(ElectorService electorService) {
        this.electorService = electorService;
    }

    @GetMapping
    public ResponseEntity<List<ElectorModel>> getAllElectors(){
        return ResponseEntity.status(HttpStatus.OK).body(electorService.findAll());
    }
}
