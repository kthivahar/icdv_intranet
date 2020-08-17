package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImportInformation.
 */
@Entity
@Table(name = "import_information")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImportInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "description_of_goods")
    private String descriptionOfGoods;

    @Column(name = "hs_code")
    private String hsCode;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JsonIgnoreProperties(value = "importInformations", allowSetters = true)
    private ImportCertAndDeliVerifn importCertAndDeliVerifn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public ImportInformation externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    public ImportInformation descriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
        return this;
    }

    public void setDescriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
    }

    public String getHsCode() {
        return hsCode;
    }

    public ImportInformation hsCode(String hsCode) {
        this.hsCode = hsCode;
        return this;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getUnit() {
        return unit;
    }

    public ImportInformation unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ImportInformation quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public ImportInformation value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ImportCertAndDeliVerifn getImportCertAndDeliVerifn() {
        return importCertAndDeliVerifn;
    }

    public ImportInformation importCertAndDeliVerifn(ImportCertAndDeliVerifn importCertAndDeliVerifn) {
        this.importCertAndDeliVerifn = importCertAndDeliVerifn;
        return this;
    }

    public void setImportCertAndDeliVerifn(ImportCertAndDeliVerifn importCertAndDeliVerifn) {
        this.importCertAndDeliVerifn = importCertAndDeliVerifn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImportInformation)) {
            return false;
        }
        return id != null && id.equals(((ImportInformation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImportInformation{" +
            "id=" + getId() +
            ", externalId='" + getExternalId() + "'" +
            ", descriptionOfGoods='" + getDescriptionOfGoods() + "'" +
            ", hsCode='" + getHsCode() + "'" +
            ", unit='" + getUnit() + "'" +
            ", quantity=" + getQuantity() +
            ", value=" + getValue() +
            "}";
    }
}
