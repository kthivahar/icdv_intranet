package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Content;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;
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

    private Environment env;

    private final Logger log = LoggerFactory.getLogger(ManufCostStmtResource.class);

    private static final String ENTITY_NAME = "manufCostStmt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufCostStmtRepository manufCostStmtRepository;

    public ManufCostStmtResource(ManufCostStmtRepository manufCostStmtRepository, Environment env) {
        this.manufCostStmtRepository = manufCostStmtRepository;
        this.env = env;
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufCostStmt,
     * or with status {@code 400 (Bad Request)} if the manufCostStmt is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufCostStmt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manuf-cost-stmts")
    public ResponseEntity<ManufCostStmt> updateManufCostStmt(@RequestBody Object manufCostStmt1) throws URISyntaxException {
        log.debug("REST request to update ManufCostStmt : {}", manufCostStmt1);
        LinkedHashMap manufCostStmt = (LinkedHashMap) manufCostStmt1;
        if (!manufCostStmt.containsKey("id")) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        manufCostStmtRepository.setStatusForMCS(Status.valueOf(manufCostStmt.get("status").toString()), Long.parseLong(manufCostStmt.get("id").toString()));
        Optional<ManufCostStmt> result = manufCostStmtRepository.findById(Long.parseLong(manufCostStmt.get("id").toString()));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufCostStmt.get("id").toString()))
            .body(result.get());
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
        return manufCostStmtRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/manuf-cost-stmts-ext")
    public List<ManufCostStmt> getAllManufCostStmtsExt() {
        log.debug("REST request to get all ManufCostStmts");
        return manufCostStmtRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
        Optional<ManufCostStmt> optionalManufCostStmt = manufCostStmtRepository.findById(id);
        if(optionalManufCostStmt.isPresent()) {
            ManufCostStmt manufCostStmt = optionalManufCostStmt.get();
            if(manufCostStmt.getContents() != null && manufCostStmt.getContents().size() > 0) {
                for (Content content : manufCostStmt.getContents()) {
                    String signedUrl = getSignedURL(content.getName(), content.getUrl());
                    content.setUrl(signedUrl);
                }
            }
            return ResponseUtil.wrapOrNotFound(Optional.of(manufCostStmt));
        }
        return null;
    }

    /**
     * {@code GET  /manuf-cost-stmts/:id} : get the "id" manufCostStmt.
     *
     * @param id the id of the manufCostStmt to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufCostStmt, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manuf-cost-stmts-ext/{id}")
    public ResponseEntity<ManufCostStmt> getManufCostStmtExt(@PathVariable Long id) {
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

    private String getSignedURL(String objectName, String objectUrl) {

        MinioClient minioClient =
            MinioClient.builder()
                .endpoint(env.getProperty("app.minio.serverurl"))
                .credentials(env.getProperty("app.minio.accsskey"),
                    env.getProperty("app.minio.secretkey"))
                .build();
        try {
            String url =
                minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(env.getProperty("app.minio.bucketname"))
                        .object(objectName)
                        .expiry(60 * 60 * 1)
                        .build());

            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectUrl;
    }
}
