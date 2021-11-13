package ru.krasilova.otus.microservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.krasilova.otus.microservices.database.entity.Contract;
import ru.krasilova.otus.microservices.kafka.dto.ContractEventDto;
import ru.krasilova.otus.microservices.kafka.producer.ContractProducer;
import ru.krasilova.otus.microservices.kafka.producer.ContractTopic;
import ru.krasilova.otus.microservices.model.MethodType;
import ru.krasilova.otus.microservices.service.ContractService;


@RestController
public class ContractController {

    private final ContractService contractService;
    private final ContractProducer producer;

    public ContractController(ContractService contractService, ContractProducer producer) {
        this.contractService = contractService;
        this.producer = producer;
    }



    @GetMapping("/contract/version")
    public ResponseEntity<String> getTestStatus(@RequestHeader(value = "X-UserId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body("1");
    }

    @PostMapping("/contract/")
    public ResponseEntity<Contract> createContract(@RequestBody Contract newContract, @RequestHeader(value = "X-UserId") String userId) {
        if (userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var httpStatus = HttpStatus.CREATED;
        try {
            final var contract = contractService.save(newContract);
            producer.sendContactEvent(
                    ContractEventDto.builder()
                            .contract(contract)
                            .profileId(newContract.getProfileId())
                            .methodType(MethodType.ADD)
                            .build(), ContractTopic.CREATE_CONTRACT);
            return ResponseEntity.status(httpStatus).body(contract);
        } catch (Exception ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @DeleteMapping("/contract/{contractId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long contractId, @RequestHeader(value = "X-UserId") String userId) {
        if (userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var httpStatus = HttpStatus.NO_CONTENT;
        try {
            final var contract = contractService.findById(contractId);
            contractService.delete(contractId);
            producer.sendContactEvent(
                    ContractEventDto.builder()
                            .contract(contract)
                            .profileId(contract.getProfileId())
                            .methodType(MethodType.DELETE)
                            .build(), ContractTopic.DELETE_CONTRACT);
        } catch (Exception ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @PutMapping("/contract/")
    public ResponseEntity<Contract> updateProfile(@RequestBody Contract newContract, @RequestHeader(value = "X-UserId") String userId) {
        if (userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var httpStatus = HttpStatus.OK;
        try {
            final var contract = contractService.save(newContract);
            producer.sendContactEvent(
                    ContractEventDto.builder()
                            .contract(contract)
                            .profileId(contract.getProfileId())
                            .build(), ContractTopic.UPDATE_CONTRACT);
            return ResponseEntity.status(httpStatus).body(contract);
        } catch (Exception ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new Contract());
    }


}
