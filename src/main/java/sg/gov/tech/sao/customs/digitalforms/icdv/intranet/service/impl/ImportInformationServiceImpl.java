package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.impl;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportInformationService;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ImportInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ImportInformation}.
 */
@Service
@Transactional
public class ImportInformationServiceImpl implements ImportInformationService {

    private final Logger log = LoggerFactory.getLogger(ImportInformationServiceImpl.class);

    private final ImportInformationRepository importInformationRepository;

    public ImportInformationServiceImpl(ImportInformationRepository importInformationRepository) {
        this.importInformationRepository = importInformationRepository;
    }

    @Override
    public ImportInformation save(ImportInformation importInformation) {
        log.debug("Request to save ImportInformation : {}", importInformation);
        return importInformationRepository.save(importInformation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImportInformation> findAll() {
        log.debug("Request to get all ImportInformations");
        return importInformationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ImportInformation> findOne(Long id) {
        log.debug("Request to get ImportInformation : {}", id);
        return importInformationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImportInformation : {}", id);
        importInformationRepository.deleteById(id);
    }
}
