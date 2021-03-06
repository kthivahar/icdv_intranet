package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.config.Constants;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT));
    }
}
