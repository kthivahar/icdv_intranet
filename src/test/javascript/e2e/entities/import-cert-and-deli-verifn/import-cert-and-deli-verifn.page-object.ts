import { element, by, ElementFinder } from 'protractor';

export class ImportCertAndDeliVerifnComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-import-cert-and-deli-verifn div table .btn-danger'));
  title = element.all(by.css('jhi-import-cert-and-deli-verifn div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ImportCertAndDeliVerifnUpdatePage {
  pageTitle = element(by.id('jhi-import-cert-and-deli-verifn-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  externalIdInput = element(by.id('field_externalId'));
  companyNameInput = element(by.id('field_companyName'));
  uniqueEntityNumberUenInput = element(by.id('field_uniqueEntityNumberUen'));
  addressInput = element(by.id('field_address'));
  nameOfContactPersonInput = element(by.id('field_nameOfContactPerson'));
  designationInput = element(by.id('field_designation'));
  contactNoInput = element(by.id('field_contactNo'));
  emailInput = element(by.id('field_email'));
  exporterCompanyNameInput = element(by.id('field_exporterCompanyName'));
  exporterAddressInput = element(by.id('field_exporterAddress'));
  euCompanyNameInput = element(by.id('field_euCompanyName'));
  countryInput = element(by.id('field_country'));
  radio1Input = element(by.id('field_radio1'));
  statusSelect = element(by.id('field_status'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setExternalIdInput(externalId: string): Promise<void> {
    await this.externalIdInput.sendKeys(externalId);
  }

  async getExternalIdInput(): Promise<string> {
    return await this.externalIdInput.getAttribute('value');
  }

  async setCompanyNameInput(companyName: string): Promise<void> {
    await this.companyNameInput.sendKeys(companyName);
  }

  async getCompanyNameInput(): Promise<string> {
    return await this.companyNameInput.getAttribute('value');
  }

  async setUniqueEntityNumberUenInput(uniqueEntityNumberUen: string): Promise<void> {
    await this.uniqueEntityNumberUenInput.sendKeys(uniqueEntityNumberUen);
  }

  async getUniqueEntityNumberUenInput(): Promise<string> {
    return await this.uniqueEntityNumberUenInput.getAttribute('value');
  }

  async setAddressInput(address: string): Promise<void> {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput(): Promise<string> {
    return await this.addressInput.getAttribute('value');
  }

  async setNameOfContactPersonInput(nameOfContactPerson: string): Promise<void> {
    await this.nameOfContactPersonInput.sendKeys(nameOfContactPerson);
  }

  async getNameOfContactPersonInput(): Promise<string> {
    return await this.nameOfContactPersonInput.getAttribute('value');
  }

  async setDesignationInput(designation: string): Promise<void> {
    await this.designationInput.sendKeys(designation);
  }

  async getDesignationInput(): Promise<string> {
    return await this.designationInput.getAttribute('value');
  }

  async setContactNoInput(contactNo: string): Promise<void> {
    await this.contactNoInput.sendKeys(contactNo);
  }

  async getContactNoInput(): Promise<string> {
    return await this.contactNoInput.getAttribute('value');
  }

  async setEmailInput(email: string): Promise<void> {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput(): Promise<string> {
    return await this.emailInput.getAttribute('value');
  }

  async setExporterCompanyNameInput(exporterCompanyName: string): Promise<void> {
    await this.exporterCompanyNameInput.sendKeys(exporterCompanyName);
  }

  async getExporterCompanyNameInput(): Promise<string> {
    return await this.exporterCompanyNameInput.getAttribute('value');
  }

  async setExporterAddressInput(exporterAddress: string): Promise<void> {
    await this.exporterAddressInput.sendKeys(exporterAddress);
  }

  async getExporterAddressInput(): Promise<string> {
    return await this.exporterAddressInput.getAttribute('value');
  }

  async setEuCompanyNameInput(euCompanyName: string): Promise<void> {
    await this.euCompanyNameInput.sendKeys(euCompanyName);
  }

  async getEuCompanyNameInput(): Promise<string> {
    return await this.euCompanyNameInput.getAttribute('value');
  }

  async setCountryInput(country: string): Promise<void> {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput(): Promise<string> {
    return await this.countryInput.getAttribute('value');
  }

  async setRadio1Input(radio1: string): Promise<void> {
    await this.radio1Input.sendKeys(radio1);
  }

  async getRadio1Input(): Promise<string> {
    return await this.radio1Input.getAttribute('value');
  }

  async setStatusSelect(status: string): Promise<void> {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect(): Promise<string> {
    return await this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption(): Promise<void> {
    await this.statusSelect.all(by.tagName('option')).last().click();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ImportCertAndDeliVerifnDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-importCertAndDeliVerifn-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-importCertAndDeliVerifn'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
