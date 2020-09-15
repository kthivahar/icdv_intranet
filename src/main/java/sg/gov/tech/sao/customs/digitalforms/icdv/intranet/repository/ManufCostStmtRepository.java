package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

/**
 * Spring Data  repository for the ManufCostStmt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufCostStmtRepository extends JpaRepository<ManufCostStmt, Long> {

    @Modifying
    @Query("update ManufCostStmt mcs set mcs.status = ?1 where mcs.id = ?2")
    int setStatusForMCS(Status status, Long id);
}
