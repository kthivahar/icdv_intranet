package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportInformationService;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation}.
 */
@RestController
@RequestMapping("/api")
public class ImportInformationResource {

    private final Logger log = LoggerFactory.getLogger(ImportInformationResource.class);

    private static final String ENTITY_NAME = "importInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImportInformationService importInformationService;

    public ImportInformationResource(ImportInformationService importInformationService) {
        this.importInformationService = importInformationService;
    }

    /**
     * {@code POST  /import-informations} : Create a new importInformation.
     *
     * @param importInformation the importInformation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new importInformation, or with status {@code 400 (Bad Request)} if the importInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/import-informations")
    public ResponseEntity<ImportInformation> createImportInformation(@RequestBody ImportInformation importInformation) throws URISyntaxException {
        log.debug("REST request to save ImportInformation : {}", importInformation);
        if (importInformation.getId() != null) {
            throw new BadRequestAlertException("A new importInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImportInformation result = importInformationService.save(importInformation);
        return ResponseEntity.created(new URI("/api/import-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /import-informations} : Updates an existing importInformation.
     *
     * @param importInformation the importInformation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated importInformation,
     * or with status {@code 400 (Bad Request)} if the importInformation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the importInformation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/import-informations")
    public ResponseEntity<ImportInformation> updateImportInformation(@RequestBody ImportInformation importInformation) throws URISyntaxException {
        log.debug("REST request to update ImportInformation : {}", importInformation);
        if (importInformation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImportInformation result = importInformationService.save(importInformation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, importInformation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /import-informations} : get all the importInformations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of importInformations in body.
     */
    @GetMapping("/import-informations")
    public List<ImportInformation> getAllImportInformations() {
        log.debug("REST request to get all ImportInformations");
        return importInformationService.findAll();
    }

    /**
     * {@code GET  /import-informations/:id} : get the "id" importInformation.
     *
     * @param id the id of the importInformation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the importInformation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/import-informations/{id}")
    public ResponseEntity<ImportInformation> getImportInformation(@PathVariable Long id) {
        log.debug("REST request to get ImportInformation : {}", id);
        Optional<ImportInformation> importInformation = importInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(importInformation);
    }

    /**
     * {@code DELETE  /import-informations/:id} : delete the "id" importInformation.
     *
     * @param id the id of the importInformation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/import-informations/{id}")
    public ResponseEntity<Void> deleteImportInformation(@PathVariable Long id) {
        log.debug("REST request to delete ImportInformation : {}", id);
        importInformationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
