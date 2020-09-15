package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ManufCostStmtRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ManufCostStmtResource {

    private final Logger log = LoggerFactory.getLogger(ManufCostStmtResource.class);

    private static final String ENTITY_NAME = "manufCostStmt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufCostStmtRepository manufCostStmtRepository;

    public ManufCostStmtResource(ManufCostStmtRepository manufCostStmtRepository) {
        this.manufCostStmtRepository = manufCostStmtRepository;
    }

    /**
     * {@code POST  /manuf-cost-stmts} : Create a new manufCostStmt.
     *
     * @param manufCostStmt the manufCostStmt to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufCostStmt, or with status {@code 400 (Bad Request)} if the manufCostStmt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manuf-cost-stmts")
    public ResponseEntity<ManufCostStmt> createManufCostStmt(@RequestBody ManufCostStmt manufCostStmt) throws URISyntaxException {
        log.debug("REST request to save ManufCostStmt : {}", manufCostStmt);
        if (manufCostStmt.getId() != null) {
            throw new BadRequestAlertException("A new manufCostStmt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufCostStmt result = manufCostStmtRepository.save(manufCostStmt);
        return ResponseEntity.created(new URI("/api/manuf-cost-stmts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manuf-cost-stmts} : Updates an existing manufCostStmt.
     *
     * @param manufCostStmt the manufCostStmt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufCostStmt,
     * or with status {@code 400 (Bad Request)} if the manufCostStmt is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufCostStmt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manuf-cost-stmts")
    public ResponseEntity<ManufCostStmt> updateManufCostStmt(@RequestBody ManufCostStmt manufCostStmt) throws URISyntaxException {
        log.debug("REST request to update ManufCostStmt : {}", manufCostStmt);
        if (manufCostStmt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManufCostStmt result = manufCostStmtRepository.save(manufCostStmt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufCostStmt.getId().toString()))
            .body(result);
    }

    @PostMapping("/manufacturing-cost-stmts")
    public ResponseEntity<ManufCostStmt> createManufuringCostStmt(@RequestBody LinkedHashMap requestBody,
                                                                  HttpServletRequest request) throws URISyntaxException {
        String remoteIP = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        log.debug("Remote Address " + remoteIP + " Remote host " + remoteHost);
        log.debug("REST request to save Manuf cost stmt : {}", requestBody);

        ManufCostStmt manufCostStmt = FormIOApiDataBody.getManufactuingCostStmt(requestBody);

        if(manufCostStmt == null) {
            throw new BadRequestAlertException("A invalid request data", ENTITY_NAME, "idexists");
        }

        if (manufCostStmt.getId() != null) {
            throw new BadRequestAlertException("A new manufacturing cost stmt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufCostStmt result = manufCostStmtRepository.save(manufCostStmt);
        return ResponseEntity.created(new URI("/api/manufacturing-cost-stmts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manuf-cost-stmts} : get all the manufCostStmts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufCostStmts in body.
     */
    @GetMapping("/manuf-cost-stmts")
    public List<ManufCostStmt> getAllManufCostStmts() {
        log.debug("REST request to get all ManufCostStmts");
        return manufCostStmtRepository.findAll();
    }

    /**
     * {@code GET  /manuf-cost-stmts/:id} : get the "id" manufCostStmt.
     *
     * @param id the id of the manufCostStmt to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufCostStmt, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manuf-cost-stmts/{id}")
    public ResponseEntity<ManufCostStmt> getManufCostStmt(@PathVariable Long id) {
        log.debug("REST request to get ManufCostStmt : {}", id);
        Optional<ManufCostStmt> manufCostStmt = manufCostStmtRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(manufCostStmt);
    }

    /**
     * {@code DELETE  /manuf-cost-stmts/:id} : delete the "id" manufCostStmt.
     *
     * @param id the id of the manufCostStmt to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manuf-cost-stmts/{id}")
    public ResponseEntity<Void> deleteManufCostStmt(@PathVariable Long id) {
        log.debug("REST request to delete ManufCostStmt : {}", id);
        manufCostStmtRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
