package ru.krasilova.otus.microservices.kafka.consumer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.krasilova.otus.microservices.database.entity.Contract;
import ru.krasilova.otus.microservices.kafka.consumer.ProfileTopic;
import ru.krasilova.otus.microservices.kafka.dto.ContractEventDto;
import ru.krasilova.otus.microservices.kafka.dto.ProfileEventDto;
import ru.krasilova.otus.microservices.kafka.producer.ContractProducer;
import ru.krasilova.otus.microservices.kafka.producer.ContractTopic;
import ru.krasilova.otus.microservices.model.MethodType;
import ru.krasilova.otus.microservices.service.ContractService;

@Component
@Slf4j
public class ProfileEventHandlerImpl implements ProfileEventHandler {

    private final ContractService contractService;
    private final ContractProducer producer;

    public ProfileEventHandlerImpl(ContractService contractService, ContractProducer producer) {
        this.contractService = contractService;
        this.producer = producer;
    }


    @Override
    @Transactional
    public void handle(ProfileEventDto profileEventDto, String topic) throws JsonProcessingException {
        log.info("Handle profile event {}", profileEventDto);
        final var profileId = profileEventDto.getProfile().getId();

        if (topic.equals(ProfileTopic.CREATE_PROFILE.getValue())) {
            final var contract = createContract(profileId);
            final var contractDto = ContractEventDto.builder()
                    .contract(contract)
                    .profileId(profileId)
                    .methodType(MethodType.ADD)
                    .build();
            producer.sendContactEvent(contractDto, ContractTopic.CREATE_CONTRACT);
        }
        if (topic.equals(ProfileTopic.DELETE_PROFILE.getValue())) {
            final var contractDto = ContractEventDto.builder()
                    .profileId(profileId)
                    .methodType(MethodType.DELETED_ALL)
                    .build();
            final var optionalContractWithFond =
                    contractService.findFirstByProfileIdAndMarketPlaceFond(profileId, true);
            if (optionalContractWithFond.isPresent()) {
                producer.sendContactEvent(contractDto, ContractTopic.DELETE_ALL_CONTRACT_REJECTED);
            } else {
                contractService.deleteAllByProfileId(profileId);
                producer.sendContactEvent(contractDto, ContractTopic.DELETE_ALL_CONTRACT);
            }
        }

    }

    private Contract createContract(Long profileId) {
        final var contract = Contract.builder()
                .profileId(profileId)
                .number(UUID.randomUUID().toString())
                .build();
        return contractService.save(contract);
    }


}
