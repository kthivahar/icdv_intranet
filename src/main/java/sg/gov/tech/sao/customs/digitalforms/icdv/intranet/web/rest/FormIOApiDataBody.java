package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web.rest;

import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.*;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.enumeration.Status;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class FormIOApiDataBody {

    public static ImportCertAndDeliVerifn getImportCertAndDeliveryCerAndVerif(LinkedHashMap formIOApiDataBody) {
        LinkedHashMap request = (LinkedHashMap) formIOApiDataBody.get("request");
        if(request == null || (request != null && request.get("data") == null)) {
            return null;
        }

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
                Double value = getDoubleValue(item.get("valueS"));
                if(value != null) {
                    importInformation.setValue(value);
                }
                importCertAndDeliVerifn.addImportInformations(importInformation);
            }
        }

        List supportingsDoc = (List) data.get("supportingDocs");

        if (supportingsDoc != null && supportingsDoc.size() > 0) {
            for (Object itemObject : supportingsDoc) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                Content content = new Content();
                if(item.get("name") != null) {
                    content.setName(item.get("name").toString());
                }
                if(item.get("url") != null) {
                    content.setUrl(item.get("url").toString());
                }
                if(item.get("originalName") != null) {
                    content.setOrgName(item.get("originalName").toString());
                }
                if(item.get("type") != null) {
                    content.setBucketName(item.get("type").toString());
                }
                if(item.get("key") != null) {
                    content.setObjectKey(item.get("key").toString());
                }
                importCertAndDeliVerifn.addContents(content);
            }
        }

        int random = new Random(1000).nextInt();
        importCertAndDeliVerifn.setExternalId("external" + random);
        importCertAndDeliVerifn.setCreatedOn(ZonedDateTime.now());
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

    public static ManufCostStmt getManufactuingCostStmt(LinkedHashMap formIOApiDataBody) {
        LinkedHashMap request = (LinkedHashMap) formIOApiDataBody.get("request");
        if(request == null || (request != null && request.get("data") == null)) {
            return null;
        }

        LinkedHashMap data = (LinkedHashMap) request.get("data");

        ManufCostStmt manufCostStmt = new ManufCostStmt();
        if(data.get("nameOfManufacturer") != null) {
            manufCostStmt.setNameOfManufacturer(data.get("nameOfManufacturer").toString());
        }
        if(data.get("uniqueEntityNumber") != null) {
            manufCostStmt.setUniqueEntityNumber(data.get("uniqueEntityNumber").toString());
        }
        if(data.get("ftaScheme") != null) {
            manufCostStmt.setFtaScheme(data.get("ftaScheme").toString());
        }
        if(data.get("isQRVC") != null) {
            manufCostStmt.setIsQRVC(Boolean.valueOf(data.get("isQRVC").toString()));
        }
        if(data.get("isCTC") != null) {
            manufCostStmt.setIsCTC(Boolean.valueOf(data.get("isCTC").toString()));
        }
        if(data.get("isMP") != null) {
            manufCostStmt.setIsCTC(Boolean.valueOf(data.get("isMP").toString()));
        }
        if(data.get("descriptionOfGoods") != null) {
            manufCostStmt.setDescriptionOfGoods(data.get("descriptionOfGoods").toString());
        }
        if(data.get("model") != null) {
            manufCostStmt.setModel(data.get("model").toString());
        }
        if(data.get("hsCodeOfGoods") != null) {
            manufCostStmt.setHsCodeOfGoods(data.get("hsCodeOfGoods").toString());
        }
        if(data.get("incotermOfFta") != null) {
            manufCostStmt.setIncotermOfFta(data.get("incotermOfFta").toString());
        }
        if(data.get("fobValueOfGoods") != null) {
            manufCostStmt.setFobValueOfGoods(getDoubleValue(data.get("fobValueOfGoods")));
        }
        if(data.get("noOfMCSUnits") != null) {
            manufCostStmt.setFobValueOfGoods(getDoubleValue(data.get("noOfMCSUnits")));
        }

        List dataGrid = (List) data.get("materials");

        if (dataGrid != null && dataGrid.size() > 0) {
            for (Object itemObject : dataGrid) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                Material material = new Material();
                if(item.get("descriptionOfMaterials") != null) {
                    material.setDescription(item.get("descriptionOfMaterials").toString());
                }
                if(item.get("hsCode") != null) {
                    material.setHscode(item.get("hsCode").toString());
                }
                if(item.get("countryOfOrigin") != null) {
                    material.setCountryOfOrigin(item.get("countryOfOrigin").toString());
                }
                if(item.get("nameOfManufacturer") != null) {
                    material.setNameOfManufacturer(item.get("nameOfManufacturer").toString());
                }
                Double valueNonOrg = getDoubleValue(item.get("valueOfMaterialsNonOriginating"));
                if(valueNonOrg != null) {
                    material.setValueOfMaterialsNonOriginating(valueNonOrg);
                }
                Double valueOrg = getDoubleValue(item.get("valueOfMaterialOriginating"));
                if(valueOrg != null) {
                    material.setValueOfMaterialsNonOriginating(valueOrg);
                }
                manufCostStmt.addMaterial(material);
            }
        }

        List supportingsDoc = (List) data.get("supportingDocuments");

        if (supportingsDoc != null && supportingsDoc.size() > 0) {
            for (Object itemObject : supportingsDoc) {
                LinkedHashMap item = (LinkedHashMap) itemObject;
                Content content = new Content();
                if(item.get("name") != null) {
                    content.setName(item.get("name").toString());
                }
                if(item.get("url") != null) {
                    content.setUrl(item.get("url").toString());
                }
                if(item.get("originalName") != null) {
                    content.setOrgName(item.get("originalName").toString());
                }
                if(item.get("type") != null) {
                    content.setBucketName(item.get("type").toString());
                }
                if(item.get("key") != null) {
                    content.setObjectKey(item.get("key").toString());
                }
                manufCostStmt.addContent(content);
            }
        }
        return manufCostStmt;
    }
}
