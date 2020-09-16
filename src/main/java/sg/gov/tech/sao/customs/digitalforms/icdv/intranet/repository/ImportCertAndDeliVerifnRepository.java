package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

/**
 * Spring Data  repository for the ImportCertAndDeliVerifn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportCertAndDeliVerifnRepository extends JpaRepository<ImportCertAndDeliVerifn, Long> {

    @Modifying
    @Query("update ImportCertAndDeliVerifn icdv set icdv.status = ?1 where icdv.id = ?2")
    int setStatusForICDV(Status status, Long id);
}
