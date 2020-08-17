package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImportInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportInformationRepository extends JpaRepository<ImportInformation, Long> {
}
