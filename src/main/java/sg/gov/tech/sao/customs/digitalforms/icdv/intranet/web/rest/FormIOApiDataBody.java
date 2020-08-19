package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FormIOApiDataBody extends LinkedHashMap<String, LinkedHashMap> {

    public static ImportCertAndDeliVerifn getImportCertAndDeliveryCerAndVerif(FormIOApiDataBody formIOApiDataBody) {
        LinkedHashMap<String, LinkedHashMap> request = formIOApiDataBody.get("request");
        LinkedHashMap data = request.get("data");

        ImportCertAndDeliVerifn importCertAndDeliVerifn = new ImportCertAndDeliVerifn();
        importCertAndDeliVerifn.setCompanyName(data.get("ApplicantCompanyName").toString());
        importCertAndDeliVerifn.setUniqueEntityNumberUen(data.get("UniqueEntityNumberUen").toString());

        Object obj = data.get("dataGrid");
//        LinkedHashMap dataGrid = new ArrayList<>();
//
//        if (dataGrid != null && dataGrid.size() > 0) {
//            for (LinkedHashMap<String, String> item : dataGrid) {
//                ImportInformation importInformation = new ImportInformation();
//                importInformation.setDescriptionOfGoods(item.get("descriptionOfGoods"));
//                importInformation.setHsCode(item.get("hsCode"));
//                importCertAndDeliVerifn.addImportInformations(importInformation);
//            }
//        }
        return importCertAndDeliVerifn;
    }
}
