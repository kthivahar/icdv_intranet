package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.impl;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportCertAndDeliVerifnService;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ImportCertAndDeliVerifnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ImportCertAndDeliVerifn}.
 */
@Service
@Transactional
public class ImportCertAndDeliVerifnServiceImpl implements ImportCertAndDeliVerifnService {

    private final Logger log = LoggerFactory.getLogger(ImportCertAndDeliVerifnServiceImpl.class);

    private final ImportCertAndDeliVerifnRepository importCertAndDeliVerifnRepository;

    public ImportCertAndDeliVerifnServiceImpl(ImportCertAndDeliVerifnRepository importCertAndDeliVerifnRepository) {
        this.importCertAndDeliVerifnRepository = importCertAndDeliVerifnRepository;
    }

    @Override
    public ImportCertAndDeliVerifn save(ImportCertAndDeliVerifn importCertAndDeliVerifn) {
        log.debug("Request to save ImportCertAndDeliVerifn : {}", importCertAndDeliVerifn);
        return importCertAndDeliVerifnRepository.save(importCertAndDeliVerifn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImportCertAndDeliVerifn> findAll() {
        log.debug("Request to get all ImportCertAndDeliVerifns");
        return importCertAndDeliVerifnRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ImportCertAndDeliVerifn> findOne(Long id) {
        log.debug("Request to get ImportCertAndDeliVerifn : {}", id);
        return importCertAndDeliVerifnRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImportCertAndDeliVerifn : {}", id);
        importCertAndDeliVerifnRepository.deleteById(id);
    }
}
