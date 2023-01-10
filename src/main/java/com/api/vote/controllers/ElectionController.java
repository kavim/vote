package com.api.vote.controllers;

import com.api.vote.models.ElectionModel;
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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/election")
public class ElectionController {
    final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping("/finish")
    public ResponseEntity<Object> finishElection(){

        Optional<ElectionModel> electionServiceOptional = electionService.getLastOpenElection();
        if (!electionServiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ElectionFinishReturn("No election found", null, null));
        }

        electionService.finishLastElection(electionServiceOptional.get());

        HashMap votes = electionService.calcResults(electionServiceOptional.get());
        
        if (votes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ElectionFinishReturn(electionService.getError(), null, null));
        }

//        for (Object value : votes.values()) {
//            if (value == null) {
//                ElectionModel electionModel = electionService.createSecondTurnElection("2", electionServiceOptional.get().getYear());
//
//            }
//        }

        ElectionFinishReturn electionFinishReturn = new ElectionFinishReturn("Election finished", LocalDateTime.now().toString(), votes);

        return ResponseEntity.status(HttpStatus.OK).body(electionFinishReturn);
    }
}
