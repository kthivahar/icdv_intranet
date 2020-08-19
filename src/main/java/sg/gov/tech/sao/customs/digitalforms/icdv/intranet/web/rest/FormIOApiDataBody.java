package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportCertAndDeliVerifn;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.ImportInformation;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class FormIOApiDataBody {

    public static ImportCertAndDeliVerifn getImportCertAndDeliveryCerAndVerif(LinkedHashMap formIOApiDataBody) {
        LinkedHashMap request = (LinkedHashMap) formIOApiDataBody.get("request");
        LinkedHashMap data = (LinkedHashMap) request.get("data");

        ImportCertAndDeliVerifn importCertAndDeliVerifn = new ImportCertAndDeliVerifn();
        if(data.get("ApplicantCompanyName") != null) {
            importCertAndDeliVerifn.setCompanyName(data.get("ApplicantCompanyName").toString());
        }
        if(data.get("UniqueEntityNumberUen") != null) {
            importCertAndDeliVerifn.setUniqueEntityNumberUen(data.get("UniqueEntityNumberUen").toString());
        }
        if(data.get("address") != null) {
            importCertAndDeliVerifn.setAddress(data.get("address").toString());
        }
        if(data.get("NameOfContactPerson") != null) {
            importCertAndDeliVerifn.setNameOfContactPerson(data.get("NameOfContactPerson").toString());
        }
        if(data.get("ContactNo") != null) {
            importCertAndDeliVerifn.setContactNo(data.get("ContactNo").toString());
        }
        if(data.get("Designation") != null) {
            importCertAndDeliVerifn.setDesignation(data.get("Designation").toString());
        }
        if(data.get("Email") != null) {
            importCertAndDeliVerifn.setEmail(data.get("Email").toString());
        }
        if(data.get("ExporterCompanyName") != null) {
            importCertAndDeliVerifn.setExporterCompanyName(data.get("ExporterCompanyName").toString());
        }
        if(data.get("ExporterAddress") != null) {
            importCertAndDeliVerifn.setExporterAddress(data.get("ExporterAddress").toString());
        }
        if(data.get("EUCompanyName") != null) {
            importCertAndDeliVerifn.setEuCompanyName(data.get("EUCompanyName").toString());
        }
        if(data.get("country") != null) {
            importCertAndDeliVerifn.setCountry(data.get("country").toString());
        }

        List dataGrid = (List) data.get("dataGrid");

        if (dataGrid != null && dataGrid.size() > 0) {
            for (Object itemObject : dataGrid) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                ImportInformation importInformation = new ImportInformation();
                if(item.get("descriptionOfGoods") != null) {
                    importInformation.setDescriptionOfGoods(item.get("descriptionOfGoods").toString());
                }
                if(item.get("hsCode") != null) {
                    importInformation.setHsCode(item.get("hsCode").toString());
                }
                if(item.get("unit") != null) {
                    importInformation.setUnit(item.get("unit").toString());
                }
                Long quntity = getLongValue(item.get("quantity"));
                if(quntity != null) {
                    importInformation.setQuantity(quntity);
                }
                Double value = getDoubleValue(item.get("value"));
                if(value != null) {
                    importInformation.setValue(value);
                }
                importCertAndDeliVerifn.addImportInformations(importInformation);
            }
        }
        int random = new Random(1000).nextInt();
        importCertAndDeliVerifn.setExternalId("external" + random);
        importCertAndDeliVerifn.setStatus(Status.OPEN);
        return importCertAndDeliVerifn;
    }

    private static Long getLongValue(Object value) {
        Long longValue = null;
        if(value != null) {
            String valueString = value.toString();
            try {
                longValue = Long.valueOf(valueString);
            }catch(Exception e) {
                longValue = null;
            }
        }
        return longValue;
    }

    private static Double getDoubleValue(Object value) {
        Double doubleValue = null;
        if(value != null) {
            String valueString = value.toString();
            try {
                doubleValue = Double.valueOf(valueString);
            }catch(Exception e) {
                doubleValue = null;
            }
        }
        return doubleValue;
    }
}
