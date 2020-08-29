package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.TestUtil;

public class ContentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Content.class);
        Content content1 = new Content();
        content1.setId(1L);
        Content content2 = new Content();
        content2.setId(content1.getId());
        assertThat(content1).isEqualTo(content2);
        content2.setId(2L);
        assertThat(content1).isNotEqualTo(content2);
        content1.setId(null);
        assertThat(content1).isNotEqualTo(content2);
    }
}