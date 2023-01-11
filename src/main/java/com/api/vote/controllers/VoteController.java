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
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.POST, path = "/place")
    public ResponseEntity<Object> vote(@RequestBody @Valid VoteDto voteDto) {

        Optional<ElectorModel> electorModelOptional = electorService.getElectorByDocument(voteDto.getDocument());
        if (!electorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elector not found");
        }

        Optional<ElectionModel> electionModelOptional = electionService.getLastOpenElection();
        if (!electionModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No election found");
        }

        if(voteService.checkVote(electionModelOptional.get(), electorModelOptional.get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Elector has already voted");
        }

        var voteModel = new VoteModel();
        if (electionModelOptional.get().getTurn().equals("1")) {
            BeanUtils.copyProperties(voteDto, voteModel);
            voteModel.setElectionModel(electionModelOptional.get());
            voteModel.setElectorModel(electorModelOptional.get());
        } else {
            // prevent elector to vote in a candidate that is not related with the current election
        }

        try {
            voteService.save(voteModel);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("The vote was placed successfully");
    }

}
