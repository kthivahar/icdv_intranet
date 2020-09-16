package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.impl;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Content;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ImportCertAndDeliVerifnRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportCertAndDeliVerifnService;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ImportCertAndDeliVerifn}.
 */
@Service
@Transactional
public class ImportCertAndDeliVerifnServiceImpl implements ImportCertAndDeliVerifnService {

    private Environment env;

    private final Logger log = LoggerFactory.getLogger(ImportCertAndDeliVerifnServiceImpl.class);

    private final ImportCertAndDeliVerifnRepository importCertAndDeliVerifnRepository;

    public ImportCertAndDeliVerifnServiceImpl(ImportCertAndDeliVerifnRepository importCertAndDeliVerifnRepository,
                                              Environment env) {
        this.importCertAndDeliVerifnRepository = importCertAndDeliVerifnRepository;
        this.env = env;
    }

    @Override
    public ImportCertAndDeliVerifn updateStatus(Status status, Long id) {
        importCertAndDeliVerifnRepository.setStatusForICDV(status, id);
        Optional<ImportCertAndDeliVerifn> optionalImportCertAndDeliVerifn
            = importCertAndDeliVerifnRepository.findById(id);
        if(optionalImportCertAndDeliVerifn.isPresent()) {
            return optionalImportCertAndDeliVerifn.get();
        }
        return null;
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
        return importCertAndDeliVerifnRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImportCertAndDeliVerifn> findOne(Long id, boolean updateContenturl) {
        log.debug("Request to get ImportCertAndDeliVerifn : {}", id);

        Optional<ImportCertAndDeliVerifn> optionalImportCertAndDeliVerifn
            = importCertAndDeliVerifnRepository.findById(id);
        if(optionalImportCertAndDeliVerifn.isPresent() && updateContenturl) {
            ImportCertAndDeliVerifn importCertAndDeliVerifn = optionalImportCertAndDeliVerifn.get();
            if(importCertAndDeliVerifn.getContents() != null && importCertAndDeliVerifn.getContents().size() > 0) {
                for (Content content : importCertAndDeliVerifn.getContents()) {
                    String signedUrl = getSignedURL(content.getName(), content.getUrl());
                    content.setUrl(signedUrl);
                }
            }
        }
        return optionalImportCertAndDeliVerifn;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImportCertAndDeliVerifn : {}", id);
        importCertAndDeliVerifnRepository.deleteById(id);
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
