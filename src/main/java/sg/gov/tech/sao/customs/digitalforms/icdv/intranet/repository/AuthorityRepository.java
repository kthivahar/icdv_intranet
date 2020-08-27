package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
