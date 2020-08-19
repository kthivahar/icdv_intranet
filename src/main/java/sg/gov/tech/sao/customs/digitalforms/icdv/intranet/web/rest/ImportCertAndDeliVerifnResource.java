package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportCertAndDeliVerifnService;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn}.
 */
@RestController
@RequestMapping("/api")
public class ImportCertAndDeliVerifnResource {

    private final Logger log = LoggerFactory.getLogger(ImportCertAndDeliVerifnResource.class);

    private static final String ENTITY_NAME = "importCertAndDeliVerifn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImportCertAndDeliVerifnService importCertAndDeliVerifnService;

    public ImportCertAndDeliVerifnResource(ImportCertAndDeliVerifnService importCertAndDeliVerifnService) {
        this.importCertAndDeliVerifnService = importCertAndDeliVerifnService;
    }

    /**
     * {@code POST  /import-cert-and-deli-verifns} : Create a new importCertAndDeliVerifn.
     *
     * @param importCertAndDeliVerifn the importCertAndDeliVerifn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new importCertAndDeliVerifn, or with status {@code 400 (Bad Request)} if the importCertAndDeliVerifn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/import-cert-and-deli-verifns")
    public ResponseEntity<ImportCertAndDeliVerifn> createImportCertAndDeliVerifn(@Valid @RequestBody ImportCertAndDeliVerifn importCertAndDeliVerifn) throws URISyntaxException {
        log.debug("REST request to save ImportCertAndDeliVerifn : {}", importCertAndDeliVerifn);
        if (importCertAndDeliVerifn.getId() != null) {
            throw new BadRequestAlertException("A new importCertAndDeliVerifn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImportCertAndDeliVerifn result = importCertAndDeliVerifnService.save(importCertAndDeliVerifn);
        return ResponseEntity.created(new URI("/api/import-cert-and-deli-verifns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /import-cert-and-deli-verifns} : Updates an existing importCertAndDeliVerifn.
     *
     * @param importCertAndDeliVerifn the importCertAndDeliVerifn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated importCertAndDeliVerifn,
     * or with status {@code 400 (Bad Request)} if the importCertAndDeliVerifn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the importCertAndDeliVerifn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/import-cert-and-deli-verifns")
    public ResponseEntity<ImportCertAndDeliVerifn> updateImportCertAndDeliVerifn(@Valid @RequestBody ImportCertAndDeliVerifn importCertAndDeliVerifn) throws URISyntaxException {
        log.debug("REST request to update ImportCertAndDeliVerifn : {}", importCertAndDeliVerifn);
        if (importCertAndDeliVerifn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImportCertAndDeliVerifn result = importCertAndDeliVerifnService.save(importCertAndDeliVerifn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, importCertAndDeliVerifn.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /import-cert-and-deli-verifns} : get all the importCertAndDeliVerifns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of importCertAndDeliVerifns in body.
     */
    @GetMapping("/import-cert-and-deli-verifns")
    public List<ImportCertAndDeliVerifn> getAllImportCertAndDeliVerifns() {
        log.debug("REST request to get all ImportCertAndDeliVerifns");
        return importCertAndDeliVerifnService.findAll();
    }

    @PostMapping("/import-cert-and-deli-verifications")
    public ResponseEntity<ImportCertAndDeliVerifn> createImportCertAndDeliveryVerification(@RequestBody LinkedHashMap requestBody) throws URISyntaxException {
        log.debug("REST request to save ImportCertAndDeliVerifn : {}", requestBody);

        ImportCertAndDeliVerifn importCertAndDeliVerifn = FormIOApiDataBody.getImportCertAndDeliveryCerAndVerif(requestBody);

        if (importCertAndDeliVerifn.getId() != null) {
            throw new BadRequestAlertException("A new importCertAndDeliVerifn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImportCertAndDeliVerifn result = importCertAndDeliVerifnService.save(importCertAndDeliVerifn);
        return ResponseEntity.created(new URI("/api/import-cert-and-deli-verifns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /import-cert-and-deli-verifns/:id} : get the "id" importCertAndDeliVerifn.
     *
     * @param id the id of the importCertAndDeliVerifn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the importCertAndDeliVerifn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/import-cert-and-deli-verifns/{id}")
    public ResponseEntity<ImportCertAndDeliVerifn> getImportCertAndDeliVerifn(@PathVariable Long id) {
        log.debug("REST request to get ImportCertAndDeliVerifn : {}", id);
        Optional<ImportCertAndDeliVerifn> importCertAndDeliVerifn = importCertAndDeliVerifnService.findOne(id);
        importCertAndDeliVerifn
            .ifPresent(e-> log.debug("Import cert and delivery verification import items :  " + e.getImportInformations()));
        return ResponseUtil.wrapOrNotFound(importCertAndDeliVerifn);
    }

    /**
     * {@code DELETE  /import-cert-and-deli-verifns/:id} : delete the "id" importCertAndDeliVerifn.
     *
     * @param id the id of the importCertAndDeliVerifn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/import-cert-and-deli-verifns/{id}")
    public ResponseEntity<Void> deleteImportCertAndDeliVerifn(@PathVariable Long id) {
        log.debug("REST request to delete ImportCertAndDeliVerifn : {}", id);
        importCertAndDeliVerifnService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
