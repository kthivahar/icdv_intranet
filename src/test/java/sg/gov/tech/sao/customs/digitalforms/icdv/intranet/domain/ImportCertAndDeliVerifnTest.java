package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.TestUtil;

public class ImportCertAndDeliVerifnTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImportCertAndDeliVerifn.class);
        ImportCertAndDeliVerifn importCertAndDeliVerifn1 = new ImportCertAndDeliVerifn();
        importCertAndDeliVerifn1.setId(1L);
        ImportCertAndDeliVerifn importCertAndDeliVerifn2 = new ImportCertAndDeliVerifn();
        importCertAndDeliVerifn2.setId(importCertAndDeliVerifn1.getId());
        assertThat(importCertAndDeliVerifn1).isEqualTo(importCertAndDeliVerifn2);
        importCertAndDeliVerifn2.setId(2L);
        assertThat(importCertAndDeliVerifn1).isNotEqualTo(importCertAndDeliVerifn2);
        importCertAndDeliVerifn1.setId(null);
        assertThat(importCertAndDeliVerifn1).isNotEqualTo(importCertAndDeliVerifn2);
    }
}
