package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

/**
 * A ManufCostStmt.
 */
@Entity
@Table(name = "manuf_cost_stmt")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManufCostStmt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_of_manufacturer")
    private String nameOfManufacturer;

    @Column(name = "unique_entity_number")
    private String uniqueEntityNumber;

    @Column(name = "fta_scheme")
    private String ftaScheme;

    @Column(name = "is_qrvc")
    private Boolean isQRVC;

    @Column(name = "is_ctc")
    private Boolean isCTC;

    @Column(name = "is_mp")
    private Boolean isMP;

    @Column(name = "description_of_goods")
    private String descriptionOfGoods;

    @Column(name = "model")
    private String model;

    @Column(name = "hs_code_of_goods")
    private String hsCodeOfGoods;

    @Column(name = "statement_date")
    private ZonedDateTime statementDate;

    @Column(name = "incoterm_of_fta")
    private String incotermOfFta;

    @Column(name = "fob_value_of_goods")
    private Double fobValueOfGoods;

    @Column(name = "no_of_mcs_units")
    private Long noOfMCSUnits;

    @Column(name = "direct_labour_cost")
    private Double directLabourCost;

    @Column(name = "direct_overhead_cost")
    private Double directOverheadCost;

    @Column(name = "profit")
    private Double profit;

    @Column(name = "other_cost")
    private Double otherCost;

    @Column(name = "is_declared_1")
    private Boolean isDeclared1;

    @Column(name = "declaring_name_1")
    private String declaringName1;

    @Column(name = "declare_position_1")
    private String declarePosition1;

    @Column(name = "declare_on_1")
    private ZonedDateTime declareOn1;

    @Column(name = "is_declared_2")
    private Boolean isDeclared2;

    @Column(name = "declaring_name_2")
    private String declaringName2;

    @Column(name = "declare_position_2")
    private String declarePosition2;

    @Column(name = "declare_on_2")
    private ZonedDateTime declareOn2;

    @Column(name = "is_declared_3")
    private Boolean isDeclared3;

    @Column(name = "declaring_name_3")
    private String declaringName3;

    @Column(name = "declare_position_3")
    private String declarePosition3;

    @Column(name = "declare_on_3")
    private ZonedDateTime declareOn3;

    @Column(name = "total_non_orig_mat")
    private Double totalNonOrigMat;

    @Column(name = "total_orig_mat")
    private Double totalOrigMat;

    @Column(name = "qvc_rvc")
    private Double qvcRvc;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @OneToMany(mappedBy = "manufCostStmt", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Material> materials = new HashSet<>();

    @OneToMany(mappedBy = "manufCostStmt", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Content> contents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfManufacturer() {
        return nameOfManufacturer;
    }

    public ManufCostStmt nameOfManufacturer(String nameOfManufacturer) {
        this.nameOfManufacturer = nameOfManufacturer;
        return this;
    }

    public void setNameOfManufacturer(String nameOfManufacturer) {
        this.nameOfManufacturer = nameOfManufacturer;
    }

    public String getUniqueEntityNumber() {
        return uniqueEntityNumber;
    }

    public ManufCostStmt uniqueEntityNumber(String uniqueEntityNumber) {
        this.uniqueEntityNumber = uniqueEntityNumber;
        return this;
    }

    public void setUniqueEntityNumber(String uniqueEntityNumber) {
        this.uniqueEntityNumber = uniqueEntityNumber;
    }

    public String getFtaScheme() {
        return ftaScheme;
    }

    public ManufCostStmt ftaScheme(String ftaScheme) {
        this.ftaScheme = ftaScheme;
        return this;
    }

    public void setFtaScheme(String ftaScheme) {
        this.ftaScheme = ftaScheme;
    }

    public Boolean isIsQRVC() {
        return isQRVC;
    }

    public ManufCostStmt isQRVC(Boolean isQRVC) {
        this.isQRVC = isQRVC;
        return this;
    }

    public void setIsQRVC(Boolean isQRVC) {
        this.isQRVC = isQRVC;
    }

    public Boolean isIsCTC() {
        return isCTC;
    }

    public ManufCostStmt isCTC(Boolean isCTC) {
        this.isCTC = isCTC;
        return this;
    }

    public void setIsCTC(Boolean isCTC) {
        this.isCTC = isCTC;
    }

    public Boolean isIsMP() {
        return isMP;
    }

    public ManufCostStmt isMP(Boolean isMP) {
        this.isMP = isMP;
        return this;
    }

    public void setIsMP(Boolean isMP) {
        this.isMP = isMP;
    }

    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    public ManufCostStmt descriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
        return this;
    }

    public void setDescriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
    }

    public String getModel() {
        return model;
    }

    public ManufCostStmt model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHsCodeOfGoods() {
        return hsCodeOfGoods;
    }

    public ManufCostStmt hsCodeOfGoods(String hsCodeOfGoods) {
        this.hsCodeOfGoods = hsCodeOfGoods;
        return this;
    }

    public void setHsCodeOfGoods(String hsCodeOfGoods) {
        this.hsCodeOfGoods = hsCodeOfGoods;
    }

    public ZonedDateTime getStatementDate() {
        return statementDate;
    }

    public ManufCostStmt statementDate(ZonedDateTime statementDate) {
        this.statementDate = statementDate;
        return this;
    }

    public void setStatementDate(ZonedDateTime statementDate) {
        this.statementDate = statementDate;
    }

    public String getIncotermOfFta() {
        return incotermOfFta;
    }

    public ManufCostStmt incotermOfFta(String incotermOfFta) {
        this.incotermOfFta = incotermOfFta;
        return this;
    }

    public void setIncotermOfFta(String incotermOfFta) {
        this.incotermOfFta = incotermOfFta;
    }

    public Double getFobValueOfGoods() {
        return fobValueOfGoods;
    }

    public ManufCostStmt fobValueOfGoods(Double fobValueOfGoods) {
        this.fobValueOfGoods = fobValueOfGoods;
        return this;
    }

    public void setFobValueOfGoods(Double fobValueOfGoods) {
        this.fobValueOfGoods = fobValueOfGoods;
    }

    public Long getNoOfMCSUnits() {
        return noOfMCSUnits;
    }

    public ManufCostStmt noOfMCSUnits(Long noOfMCSUnits) {
        this.noOfMCSUnits = noOfMCSUnits;
        return this;
    }

    public void setNoOfMCSUnits(Long noOfMCSUnits) {
        this.noOfMCSUnits = noOfMCSUnits;
    }

    public Double getDirectLabourCost() {
        return directLabourCost;
    }

    public ManufCostStmt directLabourCost(Double directLabourCost) {
        this.directLabourCost = directLabourCost;
        return this;
    }

    public void setDirectLabourCost(Double directLabourCost) {
        this.directLabourCost = directLabourCost;
    }

    public Double getDirectOverheadCost() {
        return directOverheadCost;
    }

    public ManufCostStmt directOverheadCost(Double directOverheadCost) {
        this.directOverheadCost = directOverheadCost;
        return this;
    }

    public void setDirectOverheadCost(Double directOverheadCost) {
        this.directOverheadCost = directOverheadCost;
    }

    public Double getProfit() {
        return profit;
    }

    public ManufCostStmt profit(Double profit) {
        this.profit = profit;
        return this;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public ManufCostStmt otherCost(Double otherCost) {
        this.otherCost = otherCost;
        return this;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public Boolean isIsDeclared1() {
        return isDeclared1;
    }

    public ManufCostStmt isDeclared1(Boolean isDeclared1) {
        this.isDeclared1 = isDeclared1;
        return this;
    }

    public void setIsDeclared1(Boolean isDeclared1) {
        this.isDeclared1 = isDeclared1;
    }

    public String getDeclaringName1() {
        return declaringName1;
    }

    public ManufCostStmt declaringName1(String declaringName1) {
        this.declaringName1 = declaringName1;
        return this;
    }

    public void setDeclaringName1(String declaringName1) {
        this.declaringName1 = declaringName1;
    }

    public String getDeclarePosition1() {
        return declarePosition1;
    }

    public ManufCostStmt declarePosition1(String declarePosition1) {
        this.declarePosition1 = declarePosition1;
        return this;
    }

    public void setDeclarePosition1(String declarePosition1) {
        this.declarePosition1 = declarePosition1;
    }

    public ZonedDateTime getDeclareOn1() {
        return declareOn1;
    }

    public ManufCostStmt declareOn1(ZonedDateTime declareOn1) {
        this.declareOn1 = declareOn1;
        return this;
    }

    public void setDeclareOn1(ZonedDateTime declareOn1) {
        this.declareOn1 = declareOn1;
    }

    public Boolean isIsDeclared2() {
        return isDeclared2;
    }

    public ManufCostStmt isDeclared2(Boolean isDeclared2) {
        this.isDeclared2 = isDeclared2;
        return this;
    }

    public void setIsDeclared2(Boolean isDeclared2) {
        this.isDeclared2 = isDeclared2;
    }

    public String getDeclaringName2() {
        return declaringName2;
    }

    public ManufCostStmt declaringName2(String declaringName2) {
        this.declaringName2 = declaringName2;
        return this;
    }

    public void setDeclaringName2(String declaringName2) {
        this.declaringName2 = declaringName2;
    }

    public String getDeclarePosition2() {
        return declarePosition2;
    }

    public ManufCostStmt declarePosition2(String declarePosition2) {
        this.declarePosition2 = declarePosition2;
        return this;
    }

    public void setDeclarePosition2(String declarePosition2) {
        this.declarePosition2 = declarePosition2;
    }

    public ZonedDateTime getDeclareOn2() {
        return declareOn2;
    }

    public ManufCostStmt declareOn2(ZonedDateTime declareOn2) {
        this.declareOn2 = declareOn2;
        return this;
    }

    public void setDeclareOn2(ZonedDateTime declareOn2) {
        this.declareOn2 = declareOn2;
    }

    public Boolean isIsDeclared3() {
        return isDeclared3;
    }

    public ManufCostStmt isDeclared3(Boolean isDeclared3) {
        this.isDeclared3 = isDeclared3;
        return this;
    }

    public void setIsDeclared3(Boolean isDeclared3) {
        this.isDeclared3 = isDeclared3;
    }

    public String getDeclaringName3() {
        return declaringName3;
    }

    public ManufCostStmt declaringName3(String declaringName3) {
        this.declaringName3 = declaringName3;
        return this;
    }

    public void setDeclaringName3(String declaringName3) {
        this.declaringName3 = declaringName3;
    }

    public String getDeclarePosition3() {
        return declarePosition3;
    }

    public ManufCostStmt declarePosition3(String declarePosition3) {
        this.declarePosition3 = declarePosition3;
        return this;
    }

    public void setDeclarePosition3(String declarePosition3) {
        this.declarePosition3 = declarePosition3;
    }

    public ZonedDateTime getDeclareOn3() {
        return declareOn3;
    }

    public ManufCostStmt declareOn3(ZonedDateTime declareOn3) {
        this.declareOn3 = declareOn3;
        return this;
    }

    public void setDeclareOn3(ZonedDateTime declareOn3) {
        this.declareOn3 = declareOn3;
    }

    public Double getTotalNonOrigMat() {
        return totalNonOrigMat;
    }

    public ManufCostStmt totalNonOrigMat(Double totalNonOrigMat) {
        this.totalNonOrigMat = totalNonOrigMat;
        return this;
    }

    public void setTotalNonOrigMat(Double totalNonOrigMat) {
        this.totalNonOrigMat = totalNonOrigMat;
    }

    public Double getTotalOrigMat() {
        return totalOrigMat;
    }

    public ManufCostStmt totalOrigMat(Double totalOrigMat) {
        this.totalOrigMat = totalOrigMat;
        return this;
    }

    public void setTotalOrigMat(Double totalOrigMat) {
        this.totalOrigMat = totalOrigMat;
    }

    public Double getQvcRvc() {
        return qvcRvc;
    }

    public ManufCostStmt qvcRvc(Double qvcRvc) {
        this.qvcRvc = qvcRvc;
        return this;
    }

    public void setQvcRvc(Double qvcRvc) {
        this.qvcRvc = qvcRvc;
    }

    public Status getStatus() {
        return status;
    }

    public ManufCostStmt status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public ManufCostStmt createdOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public ManufCostStmt materials(Set<Material> materials) {
        this.materials = materials;
        return this;
    }

    public ManufCostStmt addMaterial(Material material) {
        this.materials.add(material);
        material.setManufCostStmt(this);
        return this;
    }

    public ManufCostStmt removeMaterial(Material material) {
        this.materials.remove(material);
        material.setManufCostStmt(null);
        return this;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public ManufCostStmt contents(Set<Content> contents) {
        this.contents = contents;
        return this;
    }

    public ManufCostStmt addContent(Content content) {
        this.contents.add(content);
        content.setManufCostStmt(this);
        return this;
    }

    public ManufCostStmt removeContent(Content content) {
        this.contents.remove(content);
        content.setManufCostStmt(null);
        return this;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManufCostStmt)) {
            return false;
        }
        return id != null && id.equals(((ManufCostStmt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManufCostStmt{" +
            "id=" + getId() +
            ", nameOfManufacturer='" + getNameOfManufacturer() + "'" +
            ", uniqueEntityNumber='" + getUniqueEntityNumber() + "'" +
            ", ftaScheme='" + getFtaScheme() + "'" +
            ", isQRVC='" + isIsQRVC() + "'" +
            ", isCTC='" + isIsCTC() + "'" +
            ", isMP='" + isIsMP() + "'" +
            ", descriptionOfGoods='" + getDescriptionOfGoods() + "'" +
            ", model='" + getModel() + "'" +
            ", hsCodeOfGoods='" + getHsCodeOfGoods() + "'" +
            ", statementDate='" + getStatementDate() + "'" +
            ", incotermOfFta='" + getIncotermOfFta() + "'" +
            ", fobValueOfGoods=" + getFobValueOfGoods() +
            ", noOfMCSUnits=" + getNoOfMCSUnits() +
            ", directLabourCost=" + getDirectLabourCost() +
            ", directOverheadCost=" + getDirectOverheadCost() +
            ", profit=" + getProfit() +
            ", otherCost=" + getOtherCost() +
            ", isDeclared1='" + isIsDeclared1() + "'" +
            ", declaringName1='" + getDeclaringName1() + "'" +
            ", declarePosition1='" + getDeclarePosition1() + "'" +
            ", declareOn1='" + getDeclareOn1() + "'" +
            ", isDeclared2='" + isIsDeclared2() + "'" +
            ", declaringName2='" + getDeclaringName2() + "'" +
            ", declarePosition2='" + getDeclarePosition2() + "'" +
            ", declareOn2='" + getDeclareOn2() + "'" +
            ", isDeclared3='" + isIsDeclared3() + "'" +
            ", declaringName3='" + getDeclaringName3() + "'" +
            ", declarePosition3='" + getDeclarePosition3() + "'" +
            ", declareOn3='" + getDeclareOn3() + "'" +
            ", totalNonOrigMat=" + getTotalNonOrigMat() +
            ", totalOrigMat=" + getTotalOrigMat() +
            ", qvcRvc=" + getQvcRvc() +
            ", status='" + getStatus() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            "}";
    }
}
