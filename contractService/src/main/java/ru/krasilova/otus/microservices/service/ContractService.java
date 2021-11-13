package ru.krasilova.otus.microservices.service;

import java.util.Optional;
import ru.krasilova.otus.microservices.database.entity.Contract;


public interface ContractService {

    Contract save(Contract contract);

    void delete(Long id);

    void deleteAllByProfileId(Long profileId);

    Contract findById(Long id);

    Optional<Contract> findFirstByProfileIdAndMarketPlaceFond(Long profileId, boolean marketPlaceFond);
}
