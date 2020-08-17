package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.TestUtil;

public class ImportInformationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImportInformation.class);
        ImportInformation importInformation1 = new ImportInformation();
        importInformation1.setId(1L);
        ImportInformation importInformation2 = new ImportInformation();
        importInformation2.setId(importInformation1.getId());
        assertThat(importInformation1).isEqualTo(importInformation2);
        importInformation2.setId(2L);
        assertThat(importInformation1).isNotEqualTo(importInformation2);
        importInformation1.setId(null);
        assertThat(importInformation1).isNotEqualTo(importInformation2);
    }
}
