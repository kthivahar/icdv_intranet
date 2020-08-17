package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImportCertAndDeliVerifn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportCertAndDeliVerifnRepository extends JpaRepository<ImportCertAndDeliVerifn, Long> {
}
