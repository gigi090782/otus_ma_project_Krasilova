package ru.krasilova.otus.microservices.database.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.krasilova.otus.microservices.database.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findById(Long ucpId);

    List<Contract> findByProfileId(Long profileId);

    Optional<Contract> findFirstByProfileIdAndMarketPlaceFond(Long profileId, boolean marketPlaceFond);

    void deleteAllByProfileId(Long profileId);

}
