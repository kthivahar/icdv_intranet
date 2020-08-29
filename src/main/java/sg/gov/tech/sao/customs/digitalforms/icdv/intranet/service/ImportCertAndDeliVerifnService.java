package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ImportCertAndDeliVerifn}.
 */
public interface ImportCertAndDeliVerifnService {

    /**
     * Save a importCertAndDeliVerifn.
     *
     * @param importCertAndDeliVerifn the entity to save.
     * @return the persisted entity.
     */
    ImportCertAndDeliVerifn save(ImportCertAndDeliVerifn importCertAndDeliVerifn);

    /**
     * Get all the importCertAndDeliVerifns.
     *
     * @return the list of entities.
     */
    List<ImportCertAndDeliVerifn> findAll();


    /**
     * Get the "id" importCertAndDeliVerifn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImportCertAndDeliVerifn> findOne(Long id, boolean isUpdateContentUrl);

    /**
     * Delete the "id" importCertAndDeliVerifn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
