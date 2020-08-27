package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;

/**
 * Spring Data  repository for the ImportCertAndDeliVerifn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportCertAndDeliVerifnRepository extends JpaRepository<ImportCertAndDeliVerifn, Long> {
}
