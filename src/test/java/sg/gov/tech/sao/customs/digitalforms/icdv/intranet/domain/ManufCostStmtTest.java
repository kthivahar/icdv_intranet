package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.TestUtil;

public class ManufCostStmtTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufCostStmt.class);
        ManufCostStmt manufCostStmt1 = new ManufCostStmt();
        manufCostStmt1.setId(1L);
        ManufCostStmt manufCostStmt2 = new ManufCostStmt();
        manufCostStmt2.setId(manufCostStmt1.getId());
        assertThat(manufCostStmt1).isEqualTo(manufCostStmt2);
        manufCostStmt2.setId(2L);
        assertThat(manufCostStmt1).isNotEqualTo(manufCostStmt2);
        manufCostStmt1.setId(null);
        assertThat(manufCostStmt1).isNotEqualTo(manufCostStmt2);
    }
}
