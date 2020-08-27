package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Content.
 */
@Entity
@Table(name = "content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "object_key")
    private String objectKey;

    @ManyToOne
    @JsonIgnoreProperties(value = "importInformations", allowSetters = true)
    @JsonBackReference
    private ImportCertAndDeliVerifn importCertAndDeliVerifn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Content name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Content url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgName() {
        return orgName;
    }

    public Content orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public Content bucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public Content objectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Content)) {
            return false;
        }
        return id != null && id.equals(((Content) o).id);
    }


    public ImportCertAndDeliVerifn getImportCertAndDeliVerifn() {
        return importCertAndDeliVerifn;
    }

    public Content importCertAndDeliVerifn(ImportCertAndDeliVerifn importCertAndDeliVerifn) {
        this.importCertAndDeliVerifn = importCertAndDeliVerifn;
        return this;
    }

    public void setImportCertAndDeliVerifn(ImportCertAndDeliVerifn importCertAndDeliVerifn) {
        this.importCertAndDeliVerifn = importCertAndDeliVerifn;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Content{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", bucketName='" + getBucketName() + "'" +
            ", objectKey='" + getObjectKey() + "'" +
            "}";
    }
}
