package com.api.vote.controllers;

import com.api.vote.dtos.VoterDto;
import com.api.vote.models.VoterModel;
import com.api.vote.services.VoterService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/voter")
public class VoterController {
    final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveCandidate(@RequestBody @Valid VoterDto voterDto) {
        var candidateModel = new VoterModel();
        BeanUtils.copyProperties(voterDto, candidateModel);
        candidateModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(voterService.save(candidateModel));
    }
}
