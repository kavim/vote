package com.api.vote.controllers;

import com.api.vote.models.custom.ElectionFinishReturn;
import com.api.vote.services.ElectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/election")
public class ElectionController {
    final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping("/finish")
    public ResponseEntity<ElectionFinishReturn> finishElection(){
        HashMap votes = electionService.finish();

        System.out.println(electionService.getError());

        if (votes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ElectionFinishReturn(electionService.getError(), null, null));
        }

        ElectionFinishReturn electionFinishReturn = new ElectionFinishReturn("Election finished", LocalDateTime.now().toString(), votes);

        return ResponseEntity.status(HttpStatus.OK).body(electionFinishReturn);
    }
}
