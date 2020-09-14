package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.IcdvIntranetApp;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Material;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.MaterialRepository;

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
 * Integration tests for the {@link MaterialResource} REST controller.
 */
@SpringBootTest(classes = IcdvIntranetApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaterialResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_HSCODE = "AAAAAAAAAA";
    private static final String UPDATED_HSCODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_MANUFACTURER = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_MANUFACTURER = "BBBBBBBBBB";

    private static final Double DEFAULT_VALUE_OF_MATERIALS_NON_ORIGINATING = 1D;
    private static final Double UPDATED_VALUE_OF_MATERIALS_NON_ORIGINATING = 2D;

    private static final Double DEFAULT_VALUE_OF_MATERIAL_ORIGINATING = 1D;
    private static final Double UPDATED_VALUE_OF_MATERIAL_ORIGINATING = 2D;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaterialMockMvc;

    private Material material;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Material createEntity(EntityManager em) {
        Material material = new Material()
            .description(DEFAULT_DESCRIPTION)
            .hscode(DEFAULT_HSCODE)
            .countryOfOrigin(DEFAULT_COUNTRY_OF_ORIGIN)
            .nameOfManufacturer(DEFAULT_NAME_OF_MANUFACTURER)
            .valueOfMaterialsNonOriginating(DEFAULT_VALUE_OF_MATERIALS_NON_ORIGINATING)
            .valueOfMaterialOriginating(DEFAULT_VALUE_OF_MATERIAL_ORIGINATING);
        return material;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Material createUpdatedEntity(EntityManager em) {
        Material material = new Material()
            .description(UPDATED_DESCRIPTION)
            .hscode(UPDATED_HSCODE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .nameOfManufacturer(UPDATED_NAME_OF_MANUFACTURER)
            .valueOfMaterialsNonOriginating(UPDATED_VALUE_OF_MATERIALS_NON_ORIGINATING)
            .valueOfMaterialOriginating(UPDATED_VALUE_OF_MATERIAL_ORIGINATING);
        return material;
    }

    @BeforeEach
    public void initTest() {
        material = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaterial() throws Exception {
        int databaseSizeBeforeCreate = materialRepository.findAll().size();
        // Create the Material
        restMaterialMockMvc.perform(post("/api/materials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(material)))
            .andExpect(status().isCreated());

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll();
        assertThat(materialList).hasSize(databaseSizeBeforeCreate + 1);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaterial.getHscode()).isEqualTo(DEFAULT_HSCODE);
        assertThat(testMaterial.getCountryOfOrigin()).isEqualTo(DEFAULT_COUNTRY_OF_ORIGIN);
        assertThat(testMaterial.getNameOfManufacturer()).isEqualTo(DEFAULT_NAME_OF_MANUFACTURER);
        assertThat(testMaterial.getValueOfMaterialsNonOriginating()).isEqualTo(DEFAULT_VALUE_OF_MATERIALS_NON_ORIGINATING);
        assertThat(testMaterial.getValueOfMaterialOriginating()).isEqualTo(DEFAULT_VALUE_OF_MATERIAL_ORIGINATING);
    }

    @Test
    @Transactional
    public void createMaterialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = materialRepository.findAll().size();

        // Create the Material with an existing ID
        material.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaterialMockMvc.perform(post("/api/materials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(material)))
            .andExpect(status().isBadRequest());

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll();
        assertThat(materialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMaterials() throws Exception {
        // Initialize the database
        materialRepository.saveAndFlush(material);

        // Get all the materialList
        restMaterialMockMvc.perform(get("/api/materials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(material.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].hscode").value(hasItem(DEFAULT_HSCODE)))
            .andExpect(jsonPath("$.[*].countryOfOrigin").value(hasItem(DEFAULT_COUNTRY_OF_ORIGIN)))
            .andExpect(jsonPath("$.[*].nameOfManufacturer").value(hasItem(DEFAULT_NAME_OF_MANUFACTURER)))
            .andExpect(jsonPath("$.[*].valueOfMaterialsNonOriginating").value(hasItem(DEFAULT_VALUE_OF_MATERIALS_NON_ORIGINATING.doubleValue())))
            .andExpect(jsonPath("$.[*].valueOfMaterialOriginating").value(hasItem(DEFAULT_VALUE_OF_MATERIAL_ORIGINATING.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMaterial() throws Exception {
        // Initialize the database
        materialRepository.saveAndFlush(material);

        // Get the material
        restMaterialMockMvc.perform(get("/api/materials/{id}", material.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(material.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.hscode").value(DEFAULT_HSCODE))
            .andExpect(jsonPath("$.countryOfOrigin").value(DEFAULT_COUNTRY_OF_ORIGIN))
            .andExpect(jsonPath("$.nameOfManufacturer").value(DEFAULT_NAME_OF_MANUFACTURER))
            .andExpect(jsonPath("$.valueOfMaterialsNonOriginating").value(DEFAULT_VALUE_OF_MATERIALS_NON_ORIGINATING.doubleValue()))
            .andExpect(jsonPath("$.valueOfMaterialOriginating").value(DEFAULT_VALUE_OF_MATERIAL_ORIGINATING.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMaterial() throws Exception {
        // Get the material
        restMaterialMockMvc.perform(get("/api/materials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaterial() throws Exception {
        // Initialize the database
        materialRepository.saveAndFlush(material);

        int databaseSizeBeforeUpdate = materialRepository.findAll().size();

        // Update the material
        Material updatedMaterial = materialRepository.findById(material.getId()).get();
        // Disconnect from session so that the updates on updatedMaterial are not directly saved in db
        em.detach(updatedMaterial);
        updatedMaterial
            .description(UPDATED_DESCRIPTION)
            .hscode(UPDATED_HSCODE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .nameOfManufacturer(UPDATED_NAME_OF_MANUFACTURER)
            .valueOfMaterialsNonOriginating(UPDATED_VALUE_OF_MATERIALS_NON_ORIGINATING)
            .valueOfMaterialOriginating(UPDATED_VALUE_OF_MATERIAL_ORIGINATING);

        restMaterialMockMvc.perform(put("/api/materials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaterial)))
            .andExpect(status().isOk());

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
        Material testMaterial = materialList.get(materialList.size() - 1);
        assertThat(testMaterial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMaterial.getHscode()).isEqualTo(UPDATED_HSCODE);
        assertThat(testMaterial.getCountryOfOrigin()).isEqualTo(UPDATED_COUNTRY_OF_ORIGIN);
        assertThat(testMaterial.getNameOfManufacturer()).isEqualTo(UPDATED_NAME_OF_MANUFACTURER);
        assertThat(testMaterial.getValueOfMaterialsNonOriginating()).isEqualTo(UPDATED_VALUE_OF_MATERIALS_NON_ORIGINATING);
        assertThat(testMaterial.getValueOfMaterialOriginating()).isEqualTo(UPDATED_VALUE_OF_MATERIAL_ORIGINATING);
    }

    @Test
    @Transactional
    public void updateNonExistingMaterial() throws Exception {
        int databaseSizeBeforeUpdate = materialRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaterialMockMvc.perform(put("/api/materials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(material)))
            .andExpect(status().isBadRequest());

        // Validate the Material in the database
        List<Material> materialList = materialRepository.findAll();
        assertThat(materialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaterial() throws Exception {
        // Initialize the database
        materialRepository.saveAndFlush(material);

        int databaseSizeBeforeDelete = materialRepository.findAll().size();

        // Delete the material
        restMaterialMockMvc.perform(delete("/api/materials/{id}", material.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Material> materialList = materialRepository.findAll();
        assertThat(materialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
