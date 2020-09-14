package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ManufCostStmt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufCostStmtRepository extends JpaRepository<ManufCostStmt, Long> {
}
