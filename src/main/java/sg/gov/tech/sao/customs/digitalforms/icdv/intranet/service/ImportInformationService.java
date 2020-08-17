package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ImportInformation}.
 */
public interface ImportInformationService {

    /**
     * Save a importInformation.
     *
     * @param importInformation the entity to save.
     * @return the persisted entity.
     */
    ImportInformation save(ImportInformation importInformation);

    /**
     * Get all the importInformations.
     *
     * @return the list of entities.
     */
    List<ImportInformation> findAll();


    /**
     * Get the "id" importInformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImportInformation> findOne(Long id);

    /**
     * Delete the "id" importInformation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
