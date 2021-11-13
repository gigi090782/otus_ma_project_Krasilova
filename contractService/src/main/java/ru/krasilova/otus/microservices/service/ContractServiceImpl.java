package ru.krasilova.otus.microservices.service;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.krasilova.otus.microservices.database.entity.Contract;
import ru.krasilova.otus.microservices.database.repository.ContractRepository;
import ru.krasilova.otus.microservices.exception.InvalidDataException;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract save(Contract contract) {
        try {
            return contractRepository.save(contract);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException();
        }
    }

    @Override
    public void delete(Long id) {
        final var contract = contractRepository.findById(id);
        if (contract.isPresent()) {
            try {
                contractRepository.delete(contract.get());
            } catch (DataIntegrityViolationException e) {
                throw new InvalidDataException();
            }
        }
    }

    @Override
    public void deleteAllByProfileId(Long profileId) {
        contractRepository.deleteAllByProfileId(profileId);
    }

    @Override
    public Contract findById(Long id) {
        return contractRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Contract> findFirstByProfileIdAndMarketPlaceFond(Long profileId, boolean marketPlaceFond) {
        return contractRepository.findFirstByProfileIdAndMarketPlaceFond(profileId, marketPlaceFond);
    }


}
