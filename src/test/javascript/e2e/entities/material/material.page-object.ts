import { element, by, ElementFinder } from 'protractor';

export class MaterialComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-material div table .btn-danger'));
  title = element.all(by.css('jhi-material div h2#page-heading span')).first();
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

export class MaterialUpdatePage {
  pageTitle = element(by.id('jhi-material-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  descriptionInput = element(by.id('field_description'));
  hscodeInput = element(by.id('field_hscode'));
  countryOfOriginInput = element(by.id('field_countryOfOrigin'));
  nameOfManufacturerInput = element(by.id('field_nameOfManufacturer'));
  valueOfMaterialsNonOriginatingInput = element(by.id('field_valueOfMaterialsNonOriginating'));
  valueOfMaterialOriginatingInput = element(by.id('field_valueOfMaterialOriginating'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setHscodeInput(hscode: string): Promise<void> {
    await this.hscodeInput.sendKeys(hscode);
  }

  async getHscodeInput(): Promise<string> {
    return await this.hscodeInput.getAttribute('value');
  }

  async setCountryOfOriginInput(countryOfOrigin: string): Promise<void> {
    await this.countryOfOriginInput.sendKeys(countryOfOrigin);
  }

  async getCountryOfOriginInput(): Promise<string> {
    return await this.countryOfOriginInput.getAttribute('value');
  }

  async setNameOfManufacturerInput(nameOfManufacturer: string): Promise<void> {
    await this.nameOfManufacturerInput.sendKeys(nameOfManufacturer);
  }

  async getNameOfManufacturerInput(): Promise<string> {
    return await this.nameOfManufacturerInput.getAttribute('value');
  }

  async setValueOfMaterialsNonOriginatingInput(valueOfMaterialsNonOriginating: string): Promise<void> {
    await this.valueOfMaterialsNonOriginatingInput.sendKeys(valueOfMaterialsNonOriginating);
  }

  async getValueOfMaterialsNonOriginatingInput(): Promise<string> {
    return await this.valueOfMaterialsNonOriginatingInput.getAttribute('value');
  }

  async setValueOfMaterialOriginatingInput(valueOfMaterialOriginating: string): Promise<void> {
    await this.valueOfMaterialOriginatingInput.sendKeys(valueOfMaterialOriginating);
  }

  async getValueOfMaterialOriginatingInput(): Promise<string> {
    return await this.valueOfMaterialOriginatingInput.getAttribute('value');
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

export class MaterialDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-material-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-material'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
