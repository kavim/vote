package com.api.vote.controllers;

import com.api.vote.dtos.VoteDto;
import com.api.vote.models.ElectionModel;
import com.api.vote.models.ElectorModel;
import com.api.vote.models.VoteModel;
import com.api.vote.repositories.ElectorRepository;
import com.api.vote.services.ElectionService;
import com.api.vote.services.ElectorService;
import com.api.vote.services.VoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/votes")
public class VoteController {
    final VoteService voteService;
    final ElectionService electionService;
    final ElectorService electorService;
    public VoteController(VoteService voteService, ElectionService electionService,
                          ElectorService electorService) {
        this.voteService = voteService;
        this.electionService = electionService;
        this.electorService = electorService;
    }
    @PostMapping("/place")
    public ResponseEntity<Object> vote(@RequestBody @Valid VoteDto voteDto) {

        Optional<ElectorModel> electorModelOptional = electorService.getElectorByDocument(voteDto.getDocument());
        if (!electorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elector not found");
        }

        Optional<ElectionModel> electionModelOptional = electionService.getLastOpenElection();
        if (!electionModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No election found");
        }

//        Optional<ElectorModel> voteModelOptinal = voteService.checkVote(electionModelOptional.get(), electorModelOptional.get());
//        if (!voteModelOptinal.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Elector has already voted");
//        }

        if(voteService.checkVote(electionModelOptional.get(), electorModelOptional.get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Elector has already voted");
        }

        var voteModel = new VoteModel();
        BeanUtils.copyProperties(voteDto, voteModel);
        voteModel.setElectionModel(electionModelOptional.get());
        voteModel.setElectorModel(electorModelOptional.get());

        voteService.save(voteModel);

//        if(voteService.checkVote(electionModelOptional.get(), electorModelOptional.get())) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This elector has already voted");
//        }

//
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VoteReturn(voteService.getError(), null));

        return ResponseEntity.status(HttpStatus.OK).body("Vote registered");
    }

}
