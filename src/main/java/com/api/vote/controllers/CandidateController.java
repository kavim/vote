package com.api.vote.controllers;

import com.api.vote.dtos.CandidateDto;
import com.api.vote.models.CandidateModel;
import com.api.vote.services.CandidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate")
public class CandidateController {
    final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveCandidate(@RequestBody @Valid CandidateDto candidateDto) {

        if(candidateService.existsByName(candidateDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: ,,,,!");
        }
        if(candidateService.existsByNumber(candidateDto.getNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: ,,,!");
        }

        var candidateModel = new CandidateModel();
        BeanUtils.copyProperties(candidateDto, candidateModel);
        candidateModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.save(candidateModel));
    }

    @GetMapping
    public ResponseEntity<List<CandidateModel>> getAllCandidates(){
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCandidate(@PathVariable(value = "id") Long id){
        Optional<CandidateModel> CandidateModelOptional = candidateService.findById(id);
        return CandidateModelOptional.<ResponseEntity<Object>>map(candidateModel -> ResponseEntity.status(HttpStatus.OK).body(candidateModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found."));
    }
}
