package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;

/**
 * Spring Data  repository for the ImportInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportInformationRepository extends JpaRepository<ImportInformation, Long> {
}
