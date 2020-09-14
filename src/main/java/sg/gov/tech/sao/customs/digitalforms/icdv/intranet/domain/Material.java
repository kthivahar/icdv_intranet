package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Material.
 */
@Entity
@Table(name = "material")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "hscode")
    private String hscode;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "name_of_manufacturer")
    private String nameOfManufacturer;

    @Column(name = "value_of_materials_non_originating")
    private Double valueOfMaterialsNonOriginating;

    @Column(name = "value_of_material_originating")
    private Double valueOfMaterialOriginating;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Material description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHscode() {
        return hscode;
    }

    public Material hscode(String hscode) {
        this.hscode = hscode;
        return this;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public Material countryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getNameOfManufacturer() {
        return nameOfManufacturer;
    }

    public Material nameOfManufacturer(String nameOfManufacturer) {
        this.nameOfManufacturer = nameOfManufacturer;
        return this;
    }

    public void setNameOfManufacturer(String nameOfManufacturer) {
        this.nameOfManufacturer = nameOfManufacturer;
    }

    public Double getValueOfMaterialsNonOriginating() {
        return valueOfMaterialsNonOriginating;
    }

    public Material valueOfMaterialsNonOriginating(Double valueOfMaterialsNonOriginating) {
        this.valueOfMaterialsNonOriginating = valueOfMaterialsNonOriginating;
        return this;
    }

    public void setValueOfMaterialsNonOriginating(Double valueOfMaterialsNonOriginating) {
        this.valueOfMaterialsNonOriginating = valueOfMaterialsNonOriginating;
    }

    public Double getValueOfMaterialOriginating() {
        return valueOfMaterialOriginating;
    }

    public Material valueOfMaterialOriginating(Double valueOfMaterialOriginating) {
        this.valueOfMaterialOriginating = valueOfMaterialOriginating;
        return this;
    }

    public void setValueOfMaterialOriginating(Double valueOfMaterialOriginating) {
        this.valueOfMaterialOriginating = valueOfMaterialOriginating;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Material)) {
            return false;
        }
        return id != null && id.equals(((Material) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Material{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", hscode='" + getHscode() + "'" +
            ", countryOfOrigin='" + getCountryOfOrigin() + "'" +
            ", nameOfManufacturer='" + getNameOfManufacturer() + "'" +
            ", valueOfMaterialsNonOriginating=" + getValueOfMaterialsNonOriginating() +
            ", valueOfMaterialOriginating=" + getValueOfMaterialOriginating() +
            "}";
    }
}
