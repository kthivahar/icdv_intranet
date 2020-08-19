package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

/**
 * A ImportCertAndDeliVerifn.
 */
@Entity
@Table(name = "import_cert_and_deli_verifn")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImportCertAndDeliVerifn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "external_id", nullable = false)
    private String externalId;

    @NotNull
    @Size(max = 100)
    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @NotNull
    @Column(name = "unique_entity_number_uen", nullable = false)
    private String uniqueEntityNumberUen;

    @Column(name = "address")
    private String address;

    @Column(name = "name_of_contact_person")
    private String nameOfContactPerson;

    @Column(name = "designation")
    private String designation;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "exporter_company_name")
    private String exporterCompanyName;

    @Column(name = "exporter_address")
    private String exporterAddress;

    @Column(name = "eu_company_name")
    private String euCompanyName;

    @Column(name = "country")
    private String country;

    @Column(name = "radio_1")
    private String radio1;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "importCertAndDeliVerifn", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImportInformation> importInformations = new HashSet<>();

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

    public ImportCertAndDeliVerifn externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ImportCertAndDeliVerifn companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUniqueEntityNumberUen() {
        return uniqueEntityNumberUen;
    }

    public ImportCertAndDeliVerifn uniqueEntityNumberUen(String uniqueEntityNumberUen) {
        this.uniqueEntityNumberUen = uniqueEntityNumberUen;
        return this;
    }

    public void setUniqueEntityNumberUen(String uniqueEntityNumberUen) {
        this.uniqueEntityNumberUen = uniqueEntityNumberUen;
    }

    public String getAddress() {
        return address;
    }

    public ImportCertAndDeliVerifn address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfContactPerson() {
        return nameOfContactPerson;
    }

    public ImportCertAndDeliVerifn nameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
        return this;
    }

    public void setNameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
    }

    public String getDesignation() {
        return designation;
    }

    public ImportCertAndDeliVerifn designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public ImportCertAndDeliVerifn contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public ImportCertAndDeliVerifn email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExporterCompanyName() {
        return exporterCompanyName;
    }

    public ImportCertAndDeliVerifn exporterCompanyName(String exporterCompanyName) {
        this.exporterCompanyName = exporterCompanyName;
        return this;
    }

    public void setExporterCompanyName(String exporterCompanyName) {
        this.exporterCompanyName = exporterCompanyName;
    }

    public String getExporterAddress() {
        return exporterAddress;
    }

    public ImportCertAndDeliVerifn exporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
        return this;
    }

    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }

    public String getEuCompanyName() {
        return euCompanyName;
    }

    public ImportCertAndDeliVerifn euCompanyName(String euCompanyName) {
        this.euCompanyName = euCompanyName;
        return this;
    }

    public void setEuCompanyName(String euCompanyName) {
        this.euCompanyName = euCompanyName;
    }

    public String getCountry() {
        return country;
    }

    public ImportCertAndDeliVerifn country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRadio1() {
        return radio1;
    }

    public ImportCertAndDeliVerifn radio1(String radio1) {
        this.radio1 = radio1;
        return this;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public Status getStatus() {
        return status;
    }

    public ImportCertAndDeliVerifn status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<ImportInformation> getImportInformations() {
        return importInformations;
    }

    public ImportCertAndDeliVerifn importInformations(Set<ImportInformation> importInformations) {
        this.importInformations = importInformations;
        return this;
    }

    public ImportCertAndDeliVerifn addImportInformations(ImportInformation importInformation) {
        this.importInformations.add(importInformation);
        importInformation.setImportCertAndDeliVerifn(this);
        return this;
    }

    public ImportCertAndDeliVerifn removeImportInformations(ImportInformation importInformation) {
        this.importInformations.remove(importInformation);
        importInformation.setImportCertAndDeliVerifn(null);
        return this;
    }

    public void setImportInformations(Set<ImportInformation> importInformations) {
        this.importInformations = importInformations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImportCertAndDeliVerifn)) {
            return false;
        }
        return id != null && id.equals(((ImportCertAndDeliVerifn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImportCertAndDeliVerifn{" +
            "id=" + getId() +
            ", externalId='" + getExternalId() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", uniqueEntityNumberUen='" + getUniqueEntityNumberUen() + "'" +
            ", address='" + getAddress() + "'" +
            ", nameOfContactPerson='" + getNameOfContactPerson() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", exporterCompanyName='" + getExporterCompanyName() + "'" +
            ", exporterAddress='" + getExporterAddress() + "'" +
            ", euCompanyName='" + getEuCompanyName() + "'" +
            ", country='" + getCountry() + "'" +
            ", radio1='" + getRadio1() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
