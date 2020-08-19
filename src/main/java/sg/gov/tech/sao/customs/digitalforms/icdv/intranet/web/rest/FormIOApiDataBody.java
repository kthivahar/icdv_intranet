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

        List dataGrid = (List) data.get("dataGrid");

        if (dataGrid != null && dataGrid.size() > 0) {
            for (Object itemObject : dataGrid) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                ImportInformation importInformation = new ImportInformation();
                importInformation.setDescriptionOfGoods(item.get("descriptionOfGoods").toString());
                importInformation.setHsCode(item.get("hsCode").toString());
                importCertAndDeliVerifn.addImportInformations(importInformation);
            }
        }
        importCertAndDeliVerifn.setExternalId("externalXXXX");
        return importCertAndDeliVerifn;
    }
}
