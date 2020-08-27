package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Content;

/**
 * Spring Data  repository for the Content entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
