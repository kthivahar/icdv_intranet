package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.IcdvIntranetApp;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ImportCertAndDeliVerifnRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportCertAndDeliVerifnService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;
/**
 * Integration tests for the {@link ImportCertAndDeliVerifnResource} REST controller.
 */
@SpringBootTest(classes = IcdvIntranetApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImportCertAndDeliVerifnResourceIT {

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUE_ENTITY_NUMBER_UEN = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_ENTITY_NUMBER_UEN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EU_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EU_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_RADIO_1 = "AAAAAAAAAA";
    private static final String UPDATED_RADIO_1 = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.REJECTED;

    @Autowired
    private ImportCertAndDeliVerifnRepository importCertAndDeliVerifnRepository;

    @Autowired
    private ImportCertAndDeliVerifnService importCertAndDeliVerifnService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImportCertAndDeliVerifnMockMvc;

    private ImportCertAndDeliVerifn importCertAndDeliVerifn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImportCertAndDeliVerifn createEntity(EntityManager em) {
        ImportCertAndDeliVerifn importCertAndDeliVerifn = new ImportCertAndDeliVerifn()
            .externalId(DEFAULT_EXTERNAL_ID)
            .companyName(DEFAULT_COMPANY_NAME)
            .uniqueEntityNumberUen(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN)
            .address(DEFAULT_ADDRESS)
            .nameOfContactPerson(DEFAULT_NAME_OF_CONTACT_PERSON)
            .designation(DEFAULT_DESIGNATION)
            .contactNo(DEFAULT_CONTACT_NO)
            .email(DEFAULT_EMAIL)
            .exporterCompanyName(DEFAULT_EXPORTER_COMPANY_NAME)
            .exporterAddress(DEFAULT_EXPORTER_ADDRESS)
            .euCompanyName(DEFAULT_EU_COMPANY_NAME)
            .country(DEFAULT_COUNTRY)
            .radio1(DEFAULT_RADIO_1)
            .status(DEFAULT_STATUS);
        return importCertAndDeliVerifn;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImportCertAndDeliVerifn createUpdatedEntity(EntityManager em) {
        ImportCertAndDeliVerifn importCertAndDeliVerifn = new ImportCertAndDeliVerifn()
            .externalId(UPDATED_EXTERNAL_ID)
            .companyName(UPDATED_COMPANY_NAME)
            .uniqueEntityNumberUen(UPDATED_UNIQUE_ENTITY_NUMBER_UEN)
            .address(UPDATED_ADDRESS)
            .nameOfContactPerson(UPDATED_NAME_OF_CONTACT_PERSON)
            .designation(UPDATED_DESIGNATION)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .exporterCompanyName(UPDATED_EXPORTER_COMPANY_NAME)
            .exporterAddress(UPDATED_EXPORTER_ADDRESS)
            .euCompanyName(UPDATED_EU_COMPANY_NAME)
            .country(UPDATED_COUNTRY)
            .radio1(UPDATED_RADIO_1)
            .status(UPDATED_STATUS);
        return importCertAndDeliVerifn;
    }

    @BeforeEach
    public void initTest() {
        importCertAndDeliVerifn = createEntity(em);
    }

    @Test
    @Transactional
    public void createImportCertAndDeliVerifn() throws Exception {
        int databaseSizeBeforeCreate = importCertAndDeliVerifnRepository.findAll().size();
        // Create the ImportCertAndDeliVerifn
        restImportCertAndDeliVerifnMockMvc.perform(post("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isCreated());

        // Validate the ImportCertAndDeliVerifn in the database
        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeCreate + 1);
        ImportCertAndDeliVerifn testImportCertAndDeliVerifn = importCertAndDeliVerifnList.get(importCertAndDeliVerifnList.size() - 1);
        assertThat(testImportCertAndDeliVerifn.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testImportCertAndDeliVerifn.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getUniqueEntityNumberUen()).isEqualTo(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN);
        assertThat(testImportCertAndDeliVerifn.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testImportCertAndDeliVerifn.getNameOfContactPerson()).isEqualTo(DEFAULT_NAME_OF_CONTACT_PERSON);
        assertThat(testImportCertAndDeliVerifn.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testImportCertAndDeliVerifn.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testImportCertAndDeliVerifn.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testImportCertAndDeliVerifn.getExporterCompanyName()).isEqualTo(DEFAULT_EXPORTER_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getExporterAddress()).isEqualTo(DEFAULT_EXPORTER_ADDRESS);
        assertThat(testImportCertAndDeliVerifn.getEuCompanyName()).isEqualTo(DEFAULT_EU_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testImportCertAndDeliVerifn.getRadio1()).isEqualTo(DEFAULT_RADIO_1);
        assertThat(testImportCertAndDeliVerifn.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createImportCertAndDeliVerifnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = importCertAndDeliVerifnRepository.findAll().size();

        // Create the ImportCertAndDeliVerifn with an existing ID
        importCertAndDeliVerifn.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImportCertAndDeliVerifnMockMvc.perform(post("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isBadRequest());

        // Validate the ImportCertAndDeliVerifn in the database
        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkExternalIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = importCertAndDeliVerifnRepository.findAll().size();
        // set the field null
        importCertAndDeliVerifn.setExternalId(null);

        // Create the ImportCertAndDeliVerifn, which fails.


        restImportCertAndDeliVerifnMockMvc.perform(post("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isBadRequest());

        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = importCertAndDeliVerifnRepository.findAll().size();
        // set the field null
        importCertAndDeliVerifn.setCompanyName(null);

        // Create the ImportCertAndDeliVerifn, which fails.


        restImportCertAndDeliVerifnMockMvc.perform(post("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isBadRequest());

        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniqueEntityNumberUenIsRequired() throws Exception {
        int databaseSizeBeforeTest = importCertAndDeliVerifnRepository.findAll().size();
        // set the field null
        importCertAndDeliVerifn.setUniqueEntityNumberUen(null);

        // Create the ImportCertAndDeliVerifn, which fails.


        restImportCertAndDeliVerifnMockMvc.perform(post("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isBadRequest());

        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllImportCertAndDeliVerifns() throws Exception {
        // Initialize the database
        importCertAndDeliVerifnRepository.saveAndFlush(importCertAndDeliVerifn);

        // Get all the importCertAndDeliVerifnList
        restImportCertAndDeliVerifnMockMvc.perform(get("/api/import-cert-and-deli-verifns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(importCertAndDeliVerifn.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].uniqueEntityNumberUen").value(hasItem(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].nameOfContactPerson").value(hasItem(DEFAULT_NAME_OF_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].exporterCompanyName").value(hasItem(DEFAULT_EXPORTER_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].exporterAddress").value(hasItem(DEFAULT_EXPORTER_ADDRESS)))
            .andExpect(jsonPath("$.[*].euCompanyName").value(hasItem(DEFAULT_EU_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].radio1").value(hasItem(DEFAULT_RADIO_1)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getImportCertAndDeliVerifn() throws Exception {
        // Initialize the database
        importCertAndDeliVerifnRepository.saveAndFlush(importCertAndDeliVerifn);

        // Get the importCertAndDeliVerifn
        restImportCertAndDeliVerifnMockMvc.perform(get("/api/import-cert-and-deli-verifns/{id}", importCertAndDeliVerifn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(importCertAndDeliVerifn.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.uniqueEntityNumberUen").value(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.nameOfContactPerson").value(DEFAULT_NAME_OF_CONTACT_PERSON))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.exporterCompanyName").value(DEFAULT_EXPORTER_COMPANY_NAME))
            .andExpect(jsonPath("$.exporterAddress").value(DEFAULT_EXPORTER_ADDRESS))
            .andExpect(jsonPath("$.euCompanyName").value(DEFAULT_EU_COMPANY_NAME))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.radio1").value(DEFAULT_RADIO_1))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingImportCertAndDeliVerifn() throws Exception {
        // Get the importCertAndDeliVerifn
        restImportCertAndDeliVerifnMockMvc.perform(get("/api/import-cert-and-deli-verifns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImportCertAndDeliVerifn() throws Exception {
        // Initialize the database
        importCertAndDeliVerifnService.save(importCertAndDeliVerifn);

        int databaseSizeBeforeUpdate = importCertAndDeliVerifnRepository.findAll().size();

        // Update the importCertAndDeliVerifn
        ImportCertAndDeliVerifn updatedImportCertAndDeliVerifn = importCertAndDeliVerifnRepository.findById(importCertAndDeliVerifn.getId()).get();
        // Disconnect from session so that the updates on updatedImportCertAndDeliVerifn are not directly saved in db
        em.detach(updatedImportCertAndDeliVerifn);
        updatedImportCertAndDeliVerifn
            .externalId(UPDATED_EXTERNAL_ID)
            .companyName(UPDATED_COMPANY_NAME)
            .uniqueEntityNumberUen(UPDATED_UNIQUE_ENTITY_NUMBER_UEN)
            .address(UPDATED_ADDRESS)
            .nameOfContactPerson(UPDATED_NAME_OF_CONTACT_PERSON)
            .designation(UPDATED_DESIGNATION)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .exporterCompanyName(UPDATED_EXPORTER_COMPANY_NAME)
            .exporterAddress(UPDATED_EXPORTER_ADDRESS)
            .euCompanyName(UPDATED_EU_COMPANY_NAME)
            .country(UPDATED_COUNTRY)
            .radio1(UPDATED_RADIO_1)
            .status(UPDATED_STATUS);

        restImportCertAndDeliVerifnMockMvc.perform(put("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImportCertAndDeliVerifn)))
            .andExpect(status().isOk());

        // Validate the ImportCertAndDeliVerifn in the database
        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeUpdate);
        ImportCertAndDeliVerifn testImportCertAndDeliVerifn = importCertAndDeliVerifnList.get(importCertAndDeliVerifnList.size() - 1);
        assertThat(testImportCertAndDeliVerifn.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testImportCertAndDeliVerifn.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getUniqueEntityNumberUen()).isEqualTo(UPDATED_UNIQUE_ENTITY_NUMBER_UEN);
        assertThat(testImportCertAndDeliVerifn.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testImportCertAndDeliVerifn.getNameOfContactPerson()).isEqualTo(UPDATED_NAME_OF_CONTACT_PERSON);
        assertThat(testImportCertAndDeliVerifn.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testImportCertAndDeliVerifn.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testImportCertAndDeliVerifn.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testImportCertAndDeliVerifn.getExporterCompanyName()).isEqualTo(UPDATED_EXPORTER_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getExporterAddress()).isEqualTo(UPDATED_EXPORTER_ADDRESS);
        assertThat(testImportCertAndDeliVerifn.getEuCompanyName()).isEqualTo(UPDATED_EU_COMPANY_NAME);
        assertThat(testImportCertAndDeliVerifn.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testImportCertAndDeliVerifn.getRadio1()).isEqualTo(UPDATED_RADIO_1);
        assertThat(testImportCertAndDeliVerifn.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingImportCertAndDeliVerifn() throws Exception {
        int databaseSizeBeforeUpdate = importCertAndDeliVerifnRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImportCertAndDeliVerifnMockMvc.perform(put("/api/import-cert-and-deli-verifns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importCertAndDeliVerifn)))
            .andExpect(status().isBadRequest());

        // Validate the ImportCertAndDeliVerifn in the database
        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImportCertAndDeliVerifn() throws Exception {
        // Initialize the database
        importCertAndDeliVerifnService.save(importCertAndDeliVerifn);

        int databaseSizeBeforeDelete = importCertAndDeliVerifnRepository.findAll().size();

        // Delete the importCertAndDeliVerifn
        restImportCertAndDeliVerifnMockMvc.perform(delete("/api/import-cert-and-deli-verifns/{id}", importCertAndDeliVerifn.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImportCertAndDeliVerifn> importCertAndDeliVerifnList = importCertAndDeliVerifnRepository.findAll();
        assertThat(importCertAndDeliVerifnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
