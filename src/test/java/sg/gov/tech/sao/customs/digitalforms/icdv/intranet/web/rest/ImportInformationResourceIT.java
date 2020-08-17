package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.IcdvIntranetApp;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ImportInformationRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ImportInformationService;

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

/**
 * Integration tests for the {@link ImportInformationResource} REST controller.
 */
@SpringBootTest(classes = IcdvIntranetApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImportInformationResourceIT {

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_OF_GOODS = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_OF_GOODS = "BBBBBBBBBB";

    private static final String DEFAULT_HS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;

    @Autowired
    private ImportInformationRepository importInformationRepository;

    @Autowired
    private ImportInformationService importInformationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImportInformationMockMvc;

    private ImportInformation importInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImportInformation createEntity(EntityManager em) {
        ImportInformation importInformation = new ImportInformation()
            .externalId(DEFAULT_EXTERNAL_ID)
            .descriptionOfGoods(DEFAULT_DESCRIPTION_OF_GOODS)
            .hsCode(DEFAULT_HS_CODE)
            .unit(DEFAULT_UNIT)
            .quantity(DEFAULT_QUANTITY)
            .value(DEFAULT_VALUE);
        return importInformation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImportInformation createUpdatedEntity(EntityManager em) {
        ImportInformation importInformation = new ImportInformation()
            .externalId(UPDATED_EXTERNAL_ID)
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .hsCode(UPDATED_HS_CODE)
            .unit(UPDATED_UNIT)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE);
        return importInformation;
    }

    @BeforeEach
    public void initTest() {
        importInformation = createEntity(em);
    }

    @Test
    @Transactional
    public void createImportInformation() throws Exception {
        int databaseSizeBeforeCreate = importInformationRepository.findAll().size();
        // Create the ImportInformation
        restImportInformationMockMvc.perform(post("/api/import-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importInformation)))
            .andExpect(status().isCreated());

        // Validate the ImportInformation in the database
        List<ImportInformation> importInformationList = importInformationRepository.findAll();
        assertThat(importInformationList).hasSize(databaseSizeBeforeCreate + 1);
        ImportInformation testImportInformation = importInformationList.get(importInformationList.size() - 1);
        assertThat(testImportInformation.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testImportInformation.getDescriptionOfGoods()).isEqualTo(DEFAULT_DESCRIPTION_OF_GOODS);
        assertThat(testImportInformation.getHsCode()).isEqualTo(DEFAULT_HS_CODE);
        assertThat(testImportInformation.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testImportInformation.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testImportInformation.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createImportInformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = importInformationRepository.findAll().size();

        // Create the ImportInformation with an existing ID
        importInformation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImportInformationMockMvc.perform(post("/api/import-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importInformation)))
            .andExpect(status().isBadRequest());

        // Validate the ImportInformation in the database
        List<ImportInformation> importInformationList = importInformationRepository.findAll();
        assertThat(importInformationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImportInformations() throws Exception {
        // Initialize the database
        importInformationRepository.saveAndFlush(importInformation);

        // Get all the importInformationList
        restImportInformationMockMvc.perform(get("/api/import-informations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(importInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].descriptionOfGoods").value(hasItem(DEFAULT_DESCRIPTION_OF_GOODS)))
            .andExpect(jsonPath("$.[*].hsCode").value(hasItem(DEFAULT_HS_CODE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getImportInformation() throws Exception {
        // Initialize the database
        importInformationRepository.saveAndFlush(importInformation);

        // Get the importInformation
        restImportInformationMockMvc.perform(get("/api/import-informations/{id}", importInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(importInformation.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.descriptionOfGoods").value(DEFAULT_DESCRIPTION_OF_GOODS))
            .andExpect(jsonPath("$.hsCode").value(DEFAULT_HS_CODE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingImportInformation() throws Exception {
        // Get the importInformation
        restImportInformationMockMvc.perform(get("/api/import-informations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImportInformation() throws Exception {
        // Initialize the database
        importInformationService.save(importInformation);

        int databaseSizeBeforeUpdate = importInformationRepository.findAll().size();

        // Update the importInformation
        ImportInformation updatedImportInformation = importInformationRepository.findById(importInformation.getId()).get();
        // Disconnect from session so that the updates on updatedImportInformation are not directly saved in db
        em.detach(updatedImportInformation);
        updatedImportInformation
            .externalId(UPDATED_EXTERNAL_ID)
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .hsCode(UPDATED_HS_CODE)
            .unit(UPDATED_UNIT)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE);

        restImportInformationMockMvc.perform(put("/api/import-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImportInformation)))
            .andExpect(status().isOk());

        // Validate the ImportInformation in the database
        List<ImportInformation> importInformationList = importInformationRepository.findAll();
        assertThat(importInformationList).hasSize(databaseSizeBeforeUpdate);
        ImportInformation testImportInformation = importInformationList.get(importInformationList.size() - 1);
        assertThat(testImportInformation.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testImportInformation.getDescriptionOfGoods()).isEqualTo(UPDATED_DESCRIPTION_OF_GOODS);
        assertThat(testImportInformation.getHsCode()).isEqualTo(UPDATED_HS_CODE);
        assertThat(testImportInformation.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testImportInformation.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testImportInformation.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingImportInformation() throws Exception {
        int databaseSizeBeforeUpdate = importInformationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImportInformationMockMvc.perform(put("/api/import-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importInformation)))
            .andExpect(status().isBadRequest());

        // Validate the ImportInformation in the database
        List<ImportInformation> importInformationList = importInformationRepository.findAll();
        assertThat(importInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImportInformation() throws Exception {
        // Initialize the database
        importInformationService.save(importInformation);

        int databaseSizeBeforeDelete = importInformationRepository.findAll().size();

        // Delete the importInformation
        restImportInformationMockMvc.perform(delete("/api/import-informations/{id}", importInformation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImportInformation> importInformationList = importInformationRepository.findAll();
        assertThat(importInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
