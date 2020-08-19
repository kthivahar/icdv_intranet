package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FormIOApiDataBody {

    public static ImportCertAndDeliVerifn getImportCertAndDeliveryCerAndVerif(LinkedHashMap formIOApiDataBody) {
        LinkedHashMap request = (LinkedHashMap) formIOApiDataBody.get("request");
        LinkedHashMap data = (LinkedHashMap) request.get("data");

        ImportCertAndDeliVerifn importCertAndDeliVerifn = new ImportCertAndDeliVerifn();
        importCertAndDeliVerifn.setCompanyName(data.get("ApplicantCompanyName").toString());
        importCertAndDeliVerifn.setUniqueEntityNumberUen(data.get("UniqueEntityNumberUen").toString());
        importCertAndDeliVerifn.setAddress(data.get("address").toString());
        importCertAndDeliVerifn.setNameOfContactPerson(data.get("NameOfContactPerson").toString());
        importCertAndDeliVerifn.setContactNo(data.get("ContactNo").toString());
        importCertAndDeliVerifn.setDesignation(data.get("Designation").toString());
        importCertAndDeliVerifn.setEmail(data.get("Email").toString());
        importCertAndDeliVerifn.setExporterCompanyName(data.get("ExporterCompanyName").toString());
        importCertAndDeliVerifn.setExporterAddress(data.get("ExporterAddress").toString());
        importCertAndDeliVerifn.setEuCompanyName(data.get("EUCompanyName").toString());
        importCertAndDeliVerifn.setCountry(data.get("country").toString());

        List dataGrid = (List) data.get("dataGrid");

        if (dataGrid != null && dataGrid.size() > 0) {
            for (Object itemObject : dataGrid) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                ImportInformation importInformation = new ImportInformation();
                importInformation.setDescriptionOfGoods(item.get("descriptionOfGoods").toString());
                importInformation.setHsCode(item.get("hsCode").toString());
                importInformation.setUnit(item.get("unit").toString());
                importInformation.setQuantity(Long.valueOf(item.get("quantity")));
                importCertAndDeliVerifn.addImportInformations(importInformation);
            }
        }
        importCertAndDeliVerifn.setExternalId("externalXXXX");
        return importCertAndDeliVerifn;
    }
}
