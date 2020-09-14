package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.IcdvIntranetApp;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ManufCostStmt;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ManufCostStmtRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ManufCostStmtResource} REST controller.
 */
@SpringBootTest(classes = IcdvIntranetApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ManufCostStmtResourceIT {

    private static final String DEFAULT_NAME_OF_MANUFACTURER = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_MANUFACTURER = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUE_ENTITY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_ENTITY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FTA_SCHEME = "AAAAAAAAAA";
    private static final String UPDATED_FTA_SCHEME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_QRVC = false;
    private static final Boolean UPDATED_IS_QRVC = true;

    private static final Boolean DEFAULT_IS_CTC = false;
    private static final Boolean UPDATED_IS_CTC = true;

    private static final Boolean DEFAULT_IS_MP = false;
    private static final Boolean UPDATED_IS_MP = true;

    private static final String DEFAULT_DESCRIPTION_OF_GOODS = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_OF_GOODS = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_HS_CODE_OF_GOODS = "AAAAAAAAAA";
    private static final String UPDATED_HS_CODE_OF_GOODS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_STATEMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_STATEMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_INCOTERM_OF_FTA = "AAAAAAAAAA";
    private static final String UPDATED_INCOTERM_OF_FTA = "BBBBBBBBBB";

    private static final Double DEFAULT_FOB_VALUE_OF_GOODS = 1D;
    private static final Double UPDATED_FOB_VALUE_OF_GOODS = 2D;

    private static final Long DEFAULT_NO_OF_MCS_UNITS = 1L;
    private static final Long UPDATED_NO_OF_MCS_UNITS = 2L;

    private static final Double DEFAULT_DIRECT_LABOUR_COST = 1D;
    private static final Double UPDATED_DIRECT_LABOUR_COST = 2D;

    private static final Double DEFAULT_DIRECT_OVERHEAD_COST = 1D;
    private static final Double UPDATED_DIRECT_OVERHEAD_COST = 2D;

    private static final Double DEFAULT_PROFIT = 1D;
    private static final Double UPDATED_PROFIT = 2D;

    private static final Double DEFAULT_OTHER_COST = 1D;
    private static final Double UPDATED_OTHER_COST = 2D;

    private static final Boolean DEFAULT_IS_DECLARED_1 = false;
    private static final Boolean UPDATED_IS_DECLARED_1 = true;

    private static final String DEFAULT_DECLARING_NAME_1 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARING_NAME_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DECLARE_POSITION_1 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARE_POSITION_1 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DECLARE_ON_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DECLARE_ON_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_DECLARED_2 = false;
    private static final Boolean UPDATED_IS_DECLARED_2 = true;

    private static final String DEFAULT_DECLARING_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARING_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DECLARE_POSITION_2 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARE_POSITION_2 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DECLARE_ON_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DECLARE_ON_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_DECLARED_3 = false;
    private static final Boolean UPDATED_IS_DECLARED_3 = true;

    private static final String DEFAULT_DECLARING_NAME_3 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARING_NAME_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DECLARE_POSITION_3 = "AAAAAAAAAA";
    private static final String UPDATED_DECLARE_POSITION_3 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DECLARE_ON_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DECLARE_ON_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_TOTAL_NON_ORIG_MAT = 1D;
    private static final Double UPDATED_TOTAL_NON_ORIG_MAT = 2D;

    private static final Double DEFAULT_TOTAL_ORIG_MAT = 1D;
    private static final Double UPDATED_TOTAL_ORIG_MAT = 2D;

    private static final Double DEFAULT_QVC_RVC = 1D;
    private static final Double UPDATED_QVC_RVC = 2D;

    @Autowired
    private ManufCostStmtRepository manufCostStmtRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManufCostStmtMockMvc;

    private ManufCostStmt manufCostStmt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufCostStmt createEntity(EntityManager em) {
        ManufCostStmt manufCostStmt = new ManufCostStmt()
            .nameOfManufacturer(DEFAULT_NAME_OF_MANUFACTURER)
            .uniqueEntityNumber(DEFAULT_UNIQUE_ENTITY_NUMBER)
            .ftaScheme(DEFAULT_FTA_SCHEME)
            .isQRVC(DEFAULT_IS_QRVC)
            .isCTC(DEFAULT_IS_CTC)
            .isMP(DEFAULT_IS_MP)
            .descriptionOfGoods(DEFAULT_DESCRIPTION_OF_GOODS)
            .model(DEFAULT_MODEL)
            .hsCodeOfGoods(DEFAULT_HS_CODE_OF_GOODS)
            .statementDate(DEFAULT_STATEMENT_DATE)
            .incotermOfFta(DEFAULT_INCOTERM_OF_FTA)
            .fobValueOfGoods(DEFAULT_FOB_VALUE_OF_GOODS)
            .noOfMCSUnits(DEFAULT_NO_OF_MCS_UNITS)
            .directLabourCost(DEFAULT_DIRECT_LABOUR_COST)
            .directOverheadCost(DEFAULT_DIRECT_OVERHEAD_COST)
            .profit(DEFAULT_PROFIT)
            .otherCost(DEFAULT_OTHER_COST)
            .isDeclared1(DEFAULT_IS_DECLARED_1)
            .declaringName1(DEFAULT_DECLARING_NAME_1)
            .declarePosition1(DEFAULT_DECLARE_POSITION_1)
            .declareOn1(DEFAULT_DECLARE_ON_1)
            .isDeclared2(DEFAULT_IS_DECLARED_2)
            .declaringName2(DEFAULT_DECLARING_NAME_2)
            .declarePosition2(DEFAULT_DECLARE_POSITION_2)
            .declareOn2(DEFAULT_DECLARE_ON_2)
            .isDeclared3(DEFAULT_IS_DECLARED_3)
            .declaringName3(DEFAULT_DECLARING_NAME_3)
            .declarePosition3(DEFAULT_DECLARE_POSITION_3)
            .declareOn3(DEFAULT_DECLARE_ON_3)
            .totalNonOrigMat(DEFAULT_TOTAL_NON_ORIG_MAT)
            .totalOrigMat(DEFAULT_TOTAL_ORIG_MAT)
            .qvcRvc(DEFAULT_QVC_RVC);
        return manufCostStmt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufCostStmt createUpdatedEntity(EntityManager em) {
        ManufCostStmt manufCostStmt = new ManufCostStmt()
            .nameOfManufacturer(UPDATED_NAME_OF_MANUFACTURER)
            .uniqueEntityNumber(UPDATED_UNIQUE_ENTITY_NUMBER)
            .ftaScheme(UPDATED_FTA_SCHEME)
            .isQRVC(UPDATED_IS_QRVC)
            .isCTC(UPDATED_IS_CTC)
            .isMP(UPDATED_IS_MP)
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .model(UPDATED_MODEL)
            .hsCodeOfGoods(UPDATED_HS_CODE_OF_GOODS)
            .statementDate(UPDATED_STATEMENT_DATE)
            .incotermOfFta(UPDATED_INCOTERM_OF_FTA)
            .fobValueOfGoods(UPDATED_FOB_VALUE_OF_GOODS)
            .noOfMCSUnits(UPDATED_NO_OF_MCS_UNITS)
            .directLabourCost(UPDATED_DIRECT_LABOUR_COST)
            .directOverheadCost(UPDATED_DIRECT_OVERHEAD_COST)
            .profit(UPDATED_PROFIT)
            .otherCost(UPDATED_OTHER_COST)
            .isDeclared1(UPDATED_IS_DECLARED_1)
            .declaringName1(UPDATED_DECLARING_NAME_1)
            .declarePosition1(UPDATED_DECLARE_POSITION_1)
            .declareOn1(UPDATED_DECLARE_ON_1)
            .isDeclared2(UPDATED_IS_DECLARED_2)
            .declaringName2(UPDATED_DECLARING_NAME_2)
            .declarePosition2(UPDATED_DECLARE_POSITION_2)
            .declareOn2(UPDATED_DECLARE_ON_2)
            .isDeclared3(UPDATED_IS_DECLARED_3)
            .declaringName3(UPDATED_DECLARING_NAME_3)
            .declarePosition3(UPDATED_DECLARE_POSITION_3)
            .declareOn3(UPDATED_DECLARE_ON_3)
            .totalNonOrigMat(UPDATED_TOTAL_NON_ORIG_MAT)
            .totalOrigMat(UPDATED_TOTAL_ORIG_MAT)
            .qvcRvc(UPDATED_QVC_RVC);
        return manufCostStmt;
    }

    @BeforeEach
    public void initTest() {
        manufCostStmt = createEntity(em);
    }

    @Test
    @Transactional
    public void createManufCostStmt() throws Exception {
        int databaseSizeBeforeCreate = manufCostStmtRepository.findAll().size();
        // Create the ManufCostStmt
        restManufCostStmtMockMvc.perform(post("/api/manuf-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufCostStmt)))
            .andExpect(status().isCreated());

        // Validate the ManufCostStmt in the database
        List<ManufCostStmt> manufCostStmtList = manufCostStmtRepository.findAll();
        assertThat(manufCostStmtList).hasSize(databaseSizeBeforeCreate + 1);
        ManufCostStmt testManufCostStmt = manufCostStmtList.get(manufCostStmtList.size() - 1);
        assertThat(testManufCostStmt.getNameOfManufacturer()).isEqualTo(DEFAULT_NAME_OF_MANUFACTURER);
        assertThat(testManufCostStmt.getUniqueEntityNumber()).isEqualTo(DEFAULT_UNIQUE_ENTITY_NUMBER);
        assertThat(testManufCostStmt.getFtaScheme()).isEqualTo(DEFAULT_FTA_SCHEME);
        assertThat(testManufCostStmt.isIsQRVC()).isEqualTo(DEFAULT_IS_QRVC);
        assertThat(testManufCostStmt.isIsCTC()).isEqualTo(DEFAULT_IS_CTC);
        assertThat(testManufCostStmt.isIsMP()).isEqualTo(DEFAULT_IS_MP);
        assertThat(testManufCostStmt.getDescriptionOfGoods()).isEqualTo(DEFAULT_DESCRIPTION_OF_GOODS);
        assertThat(testManufCostStmt.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testManufCostStmt.getHsCodeOfGoods()).isEqualTo(DEFAULT_HS_CODE_OF_GOODS);
        assertThat(testManufCostStmt.getStatementDate()).isEqualTo(DEFAULT_STATEMENT_DATE);
        assertThat(testManufCostStmt.getIncotermOfFta()).isEqualTo(DEFAULT_INCOTERM_OF_FTA);
        assertThat(testManufCostStmt.getFobValueOfGoods()).isEqualTo(DEFAULT_FOB_VALUE_OF_GOODS);
        assertThat(testManufCostStmt.getNoOfMCSUnits()).isEqualTo(DEFAULT_NO_OF_MCS_UNITS);
        assertThat(testManufCostStmt.getDirectLabourCost()).isEqualTo(DEFAULT_DIRECT_LABOUR_COST);
        assertThat(testManufCostStmt.getDirectOverheadCost()).isEqualTo(DEFAULT_DIRECT_OVERHEAD_COST);
        assertThat(testManufCostStmt.getProfit()).isEqualTo(DEFAULT_PROFIT);
        assertThat(testManufCostStmt.getOtherCost()).isEqualTo(DEFAULT_OTHER_COST);
        assertThat(testManufCostStmt.isIsDeclared1()).isEqualTo(DEFAULT_IS_DECLARED_1);
        assertThat(testManufCostStmt.getDeclaringName1()).isEqualTo(DEFAULT_DECLARING_NAME_1);
        assertThat(testManufCostStmt.getDeclarePosition1()).isEqualTo(DEFAULT_DECLARE_POSITION_1);
        assertThat(testManufCostStmt.getDeclareOn1()).isEqualTo(DEFAULT_DECLARE_ON_1);
        assertThat(testManufCostStmt.isIsDeclared2()).isEqualTo(DEFAULT_IS_DECLARED_2);
        assertThat(testManufCostStmt.getDeclaringName2()).isEqualTo(DEFAULT_DECLARING_NAME_2);
        assertThat(testManufCostStmt.getDeclarePosition2()).isEqualTo(DEFAULT_DECLARE_POSITION_2);
        assertThat(testManufCostStmt.getDeclareOn2()).isEqualTo(DEFAULT_DECLARE_ON_2);
        assertThat(testManufCostStmt.isIsDeclared3()).isEqualTo(DEFAULT_IS_DECLARED_3);
        assertThat(testManufCostStmt.getDeclaringName3()).isEqualTo(DEFAULT_DECLARING_NAME_3);
        assertThat(testManufCostStmt.getDeclarePosition3()).isEqualTo(DEFAULT_DECLARE_POSITION_3);
        assertThat(testManufCostStmt.getDeclareOn3()).isEqualTo(DEFAULT_DECLARE_ON_3);
        assertThat(testManufCostStmt.getTotalNonOrigMat()).isEqualTo(DEFAULT_TOTAL_NON_ORIG_MAT);
        assertThat(testManufCostStmt.getTotalOrigMat()).isEqualTo(DEFAULT_TOTAL_ORIG_MAT);
        assertThat(testManufCostStmt.getQvcRvc()).isEqualTo(DEFAULT_QVC_RVC);
    }

    @Test
    @Transactional
    public void createManufCostStmtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufCostStmtRepository.findAll().size();

        // Create the ManufCostStmt with an existing ID
        manufCostStmt.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufCostStmtMockMvc.perform(post("/api/manuf-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufCostStmt)))
            .andExpect(status().isBadRequest());

        // Validate the ManufCostStmt in the database
        List<ManufCostStmt> manufCostStmtList = manufCostStmtRepository.findAll();
        assertThat(manufCostStmtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManufCostStmts() throws Exception {
        // Initialize the database
        manufCostStmtRepository.saveAndFlush(manufCostStmt);

        // Get all the manufCostStmtList
        restManufCostStmtMockMvc.perform(get("/api/manuf-cost-stmts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufCostStmt.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameOfManufacturer").value(hasItem(DEFAULT_NAME_OF_MANUFACTURER)))
            .andExpect(jsonPath("$.[*].uniqueEntityNumber").value(hasItem(DEFAULT_UNIQUE_ENTITY_NUMBER)))
            .andExpect(jsonPath("$.[*].ftaScheme").value(hasItem(DEFAULT_FTA_SCHEME)))
            .andExpect(jsonPath("$.[*].isQRVC").value(hasItem(DEFAULT_IS_QRVC.booleanValue())))
            .andExpect(jsonPath("$.[*].isCTC").value(hasItem(DEFAULT_IS_CTC.booleanValue())))
            .andExpect(jsonPath("$.[*].isMP").value(hasItem(DEFAULT_IS_MP.booleanValue())))
            .andExpect(jsonPath("$.[*].descriptionOfGoods").value(hasItem(DEFAULT_DESCRIPTION_OF_GOODS)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].hsCodeOfGoods").value(hasItem(DEFAULT_HS_CODE_OF_GOODS)))
            .andExpect(jsonPath("$.[*].statementDate").value(hasItem(sameInstant(DEFAULT_STATEMENT_DATE))))
            .andExpect(jsonPath("$.[*].incotermOfFta").value(hasItem(DEFAULT_INCOTERM_OF_FTA)))
            .andExpect(jsonPath("$.[*].fobValueOfGoods").value(hasItem(DEFAULT_FOB_VALUE_OF_GOODS.doubleValue())))
            .andExpect(jsonPath("$.[*].noOfMCSUnits").value(hasItem(DEFAULT_NO_OF_MCS_UNITS.intValue())))
            .andExpect(jsonPath("$.[*].directLabourCost").value(hasItem(DEFAULT_DIRECT_LABOUR_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].directOverheadCost").value(hasItem(DEFAULT_DIRECT_OVERHEAD_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].profit").value(hasItem(DEFAULT_PROFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].otherCost").value(hasItem(DEFAULT_OTHER_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeclared1").value(hasItem(DEFAULT_IS_DECLARED_1.booleanValue())))
            .andExpect(jsonPath("$.[*].declaringName1").value(hasItem(DEFAULT_DECLARING_NAME_1)))
            .andExpect(jsonPath("$.[*].declarePosition1").value(hasItem(DEFAULT_DECLARE_POSITION_1)))
            .andExpect(jsonPath("$.[*].declareOn1").value(hasItem(sameInstant(DEFAULT_DECLARE_ON_1))))
            .andExpect(jsonPath("$.[*].isDeclared2").value(hasItem(DEFAULT_IS_DECLARED_2.booleanValue())))
            .andExpect(jsonPath("$.[*].declaringName2").value(hasItem(DEFAULT_DECLARING_NAME_2)))
            .andExpect(jsonPath("$.[*].declarePosition2").value(hasItem(DEFAULT_DECLARE_POSITION_2)))
            .andExpect(jsonPath("$.[*].declareOn2").value(hasItem(sameInstant(DEFAULT_DECLARE_ON_2))))
            .andExpect(jsonPath("$.[*].isDeclared3").value(hasItem(DEFAULT_IS_DECLARED_3.booleanValue())))
            .andExpect(jsonPath("$.[*].declaringName3").value(hasItem(DEFAULT_DECLARING_NAME_3)))
            .andExpect(jsonPath("$.[*].declarePosition3").value(hasItem(DEFAULT_DECLARE_POSITION_3)))
            .andExpect(jsonPath("$.[*].declareOn3").value(hasItem(sameInstant(DEFAULT_DECLARE_ON_3))))
            .andExpect(jsonPath("$.[*].totalNonOrigMat").value(hasItem(DEFAULT_TOTAL_NON_ORIG_MAT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalOrigMat").value(hasItem(DEFAULT_TOTAL_ORIG_MAT.doubleValue())))
            .andExpect(jsonPath("$.[*].qvcRvc").value(hasItem(DEFAULT_QVC_RVC.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getManufCostStmt() throws Exception {
        // Initialize the database
        manufCostStmtRepository.saveAndFlush(manufCostStmt);

        // Get the manufCostStmt
        restManufCostStmtMockMvc.perform(get("/api/manuf-cost-stmts/{id}", manufCostStmt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manufCostStmt.getId().intValue()))
            .andExpect(jsonPath("$.nameOfManufacturer").value(DEFAULT_NAME_OF_MANUFACTURER))
            .andExpect(jsonPath("$.uniqueEntityNumber").value(DEFAULT_UNIQUE_ENTITY_NUMBER))
            .andExpect(jsonPath("$.ftaScheme").value(DEFAULT_FTA_SCHEME))
            .andExpect(jsonPath("$.isQRVC").value(DEFAULT_IS_QRVC.booleanValue()))
            .andExpect(jsonPath("$.isCTC").value(DEFAULT_IS_CTC.booleanValue()))
            .andExpect(jsonPath("$.isMP").value(DEFAULT_IS_MP.booleanValue()))
            .andExpect(jsonPath("$.descriptionOfGoods").value(DEFAULT_DESCRIPTION_OF_GOODS))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.hsCodeOfGoods").value(DEFAULT_HS_CODE_OF_GOODS))
            .andExpect(jsonPath("$.statementDate").value(sameInstant(DEFAULT_STATEMENT_DATE)))
            .andExpect(jsonPath("$.incotermOfFta").value(DEFAULT_INCOTERM_OF_FTA))
            .andExpect(jsonPath("$.fobValueOfGoods").value(DEFAULT_FOB_VALUE_OF_GOODS.doubleValue()))
            .andExpect(jsonPath("$.noOfMCSUnits").value(DEFAULT_NO_OF_MCS_UNITS.intValue()))
            .andExpect(jsonPath("$.directLabourCost").value(DEFAULT_DIRECT_LABOUR_COST.doubleValue()))
            .andExpect(jsonPath("$.directOverheadCost").value(DEFAULT_DIRECT_OVERHEAD_COST.doubleValue()))
            .andExpect(jsonPath("$.profit").value(DEFAULT_PROFIT.doubleValue()))
            .andExpect(jsonPath("$.otherCost").value(DEFAULT_OTHER_COST.doubleValue()))
            .andExpect(jsonPath("$.isDeclared1").value(DEFAULT_IS_DECLARED_1.booleanValue()))
            .andExpect(jsonPath("$.declaringName1").value(DEFAULT_DECLARING_NAME_1))
            .andExpect(jsonPath("$.declarePosition1").value(DEFAULT_DECLARE_POSITION_1))
            .andExpect(jsonPath("$.declareOn1").value(sameInstant(DEFAULT_DECLARE_ON_1)))
            .andExpect(jsonPath("$.isDeclared2").value(DEFAULT_IS_DECLARED_2.booleanValue()))
            .andExpect(jsonPath("$.declaringName2").value(DEFAULT_DECLARING_NAME_2))
            .andExpect(jsonPath("$.declarePosition2").value(DEFAULT_DECLARE_POSITION_2))
            .andExpect(jsonPath("$.declareOn2").value(sameInstant(DEFAULT_DECLARE_ON_2)))
            .andExpect(jsonPath("$.isDeclared3").value(DEFAULT_IS_DECLARED_3.booleanValue()))
            .andExpect(jsonPath("$.declaringName3").value(DEFAULT_DECLARING_NAME_3))
            .andExpect(jsonPath("$.declarePosition3").value(DEFAULT_DECLARE_POSITION_3))
            .andExpect(jsonPath("$.declareOn3").value(sameInstant(DEFAULT_DECLARE_ON_3)))
            .andExpect(jsonPath("$.totalNonOrigMat").value(DEFAULT_TOTAL_NON_ORIG_MAT.doubleValue()))
            .andExpect(jsonPath("$.totalOrigMat").value(DEFAULT_TOTAL_ORIG_MAT.doubleValue()))
            .andExpect(jsonPath("$.qvcRvc").value(DEFAULT_QVC_RVC.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingManufCostStmt() throws Exception {
        // Get the manufCostStmt
        restManufCostStmtMockMvc.perform(get("/api/manuf-cost-stmts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManufCostStmt() throws Exception {
        // Initialize the database
        manufCostStmtRepository.saveAndFlush(manufCostStmt);

        int databaseSizeBeforeUpdate = manufCostStmtRepository.findAll().size();

        // Update the manufCostStmt
        ManufCostStmt updatedManufCostStmt = manufCostStmtRepository.findById(manufCostStmt.getId()).get();
        // Disconnect from session so that the updates on updatedManufCostStmt are not directly saved in db
        em.detach(updatedManufCostStmt);
        updatedManufCostStmt
            .nameOfManufacturer(UPDATED_NAME_OF_MANUFACTURER)
            .uniqueEntityNumber(UPDATED_UNIQUE_ENTITY_NUMBER)
            .ftaScheme(UPDATED_FTA_SCHEME)
            .isQRVC(UPDATED_IS_QRVC)
            .isCTC(UPDATED_IS_CTC)
            .isMP(UPDATED_IS_MP)
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .model(UPDATED_MODEL)
            .hsCodeOfGoods(UPDATED_HS_CODE_OF_GOODS)
            .statementDate(UPDATED_STATEMENT_DATE)
            .incotermOfFta(UPDATED_INCOTERM_OF_FTA)
            .fobValueOfGoods(UPDATED_FOB_VALUE_OF_GOODS)
            .noOfMCSUnits(UPDATED_NO_OF_MCS_UNITS)
            .directLabourCost(UPDATED_DIRECT_LABOUR_COST)
            .directOverheadCost(UPDATED_DIRECT_OVERHEAD_COST)
            .profit(UPDATED_PROFIT)
            .otherCost(UPDATED_OTHER_COST)
            .isDeclared1(UPDATED_IS_DECLARED_1)
            .declaringName1(UPDATED_DECLARING_NAME_1)
            .declarePosition1(UPDATED_DECLARE_POSITION_1)
            .declareOn1(UPDATED_DECLARE_ON_1)
            .isDeclared2(UPDATED_IS_DECLARED_2)
            .declaringName2(UPDATED_DECLARING_NAME_2)
            .declarePosition2(UPDATED_DECLARE_POSITION_2)
            .declareOn2(UPDATED_DECLARE_ON_2)
            .isDeclared3(UPDATED_IS_DECLARED_3)
            .declaringName3(UPDATED_DECLARING_NAME_3)
            .declarePosition3(UPDATED_DECLARE_POSITION_3)
            .declareOn3(UPDATED_DECLARE_ON_3)
            .totalNonOrigMat(UPDATED_TOTAL_NON_ORIG_MAT)
            .totalOrigMat(UPDATED_TOTAL_ORIG_MAT)
            .qvcRvc(UPDATED_QVC_RVC);

        restManufCostStmtMockMvc.perform(put("/api/manuf-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedManufCostStmt)))
            .andExpect(status().isOk());

        // Validate the ManufCostStmt in the database
        List<ManufCostStmt> manufCostStmtList = manufCostStmtRepository.findAll();
        assertThat(manufCostStmtList).hasSize(databaseSizeBeforeUpdate);
        ManufCostStmt testManufCostStmt = manufCostStmtList.get(manufCostStmtList.size() - 1);
        assertThat(testManufCostStmt.getNameOfManufacturer()).isEqualTo(UPDATED_NAME_OF_MANUFACTURER);
        assertThat(testManufCostStmt.getUniqueEntityNumber()).isEqualTo(UPDATED_UNIQUE_ENTITY_NUMBER);
        assertThat(testManufCostStmt.getFtaScheme()).isEqualTo(UPDATED_FTA_SCHEME);
        assertThat(testManufCostStmt.isIsQRVC()).isEqualTo(UPDATED_IS_QRVC);
        assertThat(testManufCostStmt.isIsCTC()).isEqualTo(UPDATED_IS_CTC);
        assertThat(testManufCostStmt.isIsMP()).isEqualTo(UPDATED_IS_MP);
        assertThat(testManufCostStmt.getDescriptionOfGoods()).isEqualTo(UPDATED_DESCRIPTION_OF_GOODS);
        assertThat(testManufCostStmt.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testManufCostStmt.getHsCodeOfGoods()).isEqualTo(UPDATED_HS_CODE_OF_GOODS);
        assertThat(testManufCostStmt.getStatementDate()).isEqualTo(UPDATED_STATEMENT_DATE);
        assertThat(testManufCostStmt.getIncotermOfFta()).isEqualTo(UPDATED_INCOTERM_OF_FTA);
        assertThat(testManufCostStmt.getFobValueOfGoods()).isEqualTo(UPDATED_FOB_VALUE_OF_GOODS);
        assertThat(testManufCostStmt.getNoOfMCSUnits()).isEqualTo(UPDATED_NO_OF_MCS_UNITS);
        assertThat(testManufCostStmt.getDirectLabourCost()).isEqualTo(UPDATED_DIRECT_LABOUR_COST);
        assertThat(testManufCostStmt.getDirectOverheadCost()).isEqualTo(UPDATED_DIRECT_OVERHEAD_COST);
        assertThat(testManufCostStmt.getProfit()).isEqualTo(UPDATED_PROFIT);
        assertThat(testManufCostStmt.getOtherCost()).isEqualTo(UPDATED_OTHER_COST);
        assertThat(testManufCostStmt.isIsDeclared1()).isEqualTo(UPDATED_IS_DECLARED_1);
        assertThat(testManufCostStmt.getDeclaringName1()).isEqualTo(UPDATED_DECLARING_NAME_1);
        assertThat(testManufCostStmt.getDeclarePosition1()).isEqualTo(UPDATED_DECLARE_POSITION_1);
        assertThat(testManufCostStmt.getDeclareOn1()).isEqualTo(UPDATED_DECLARE_ON_1);
        assertThat(testManufCostStmt.isIsDeclared2()).isEqualTo(UPDATED_IS_DECLARED_2);
        assertThat(testManufCostStmt.getDeclaringName2()).isEqualTo(UPDATED_DECLARING_NAME_2);
        assertThat(testManufCostStmt.getDeclarePosition2()).isEqualTo(UPDATED_DECLARE_POSITION_2);
        assertThat(testManufCostStmt.getDeclareOn2()).isEqualTo(UPDATED_DECLARE_ON_2);
        assertThat(testManufCostStmt.isIsDeclared3()).isEqualTo(UPDATED_IS_DECLARED_3);
        assertThat(testManufCostStmt.getDeclaringName3()).isEqualTo(UPDATED_DECLARING_NAME_3);
        assertThat(testManufCostStmt.getDeclarePosition3()).isEqualTo(UPDATED_DECLARE_POSITION_3);
        assertThat(testManufCostStmt.getDeclareOn3()).isEqualTo(UPDATED_DECLARE_ON_3);
        assertThat(testManufCostStmt.getTotalNonOrigMat()).isEqualTo(UPDATED_TOTAL_NON_ORIG_MAT);
        assertThat(testManufCostStmt.getTotalOrigMat()).isEqualTo(UPDATED_TOTAL_ORIG_MAT);
        assertThat(testManufCostStmt.getQvcRvc()).isEqualTo(UPDATED_QVC_RVC);
    }

    @Test
    @Transactional
    public void updateNonExistingManufCostStmt() throws Exception {
        int databaseSizeBeforeUpdate = manufCostStmtRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufCostStmtMockMvc.perform(put("/api/manuf-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufCostStmt)))
            .andExpect(status().isBadRequest());

        // Validate the ManufCostStmt in the database
        List<ManufCostStmt> manufCostStmtList = manufCostStmtRepository.findAll();
        assertThat(manufCostStmtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManufCostStmt() throws Exception {
        // Initialize the database
        manufCostStmtRepository.saveAndFlush(manufCostStmt);

        int databaseSizeBeforeDelete = manufCostStmtRepository.findAll().size();

        // Delete the manufCostStmt
        restManufCostStmtMockMvc.perform(delete("/api/manuf-cost-stmts/{id}", manufCostStmt.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ManufCostStmt> manufCostStmtList = manufCostStmtRepository.findAll();
        assertThat(manufCostStmtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
