import { element, by, ElementFinder } from 'protractor';

export class ImportInformationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-import-information div table .btn-danger'));
  title = element.all(by.css('jhi-import-information div h2#page-heading span')).first();
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

export class ImportInformationUpdatePage {
  pageTitle = element(by.id('jhi-import-information-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  externalIdInput = element(by.id('field_externalId'));
  descriptionOfGoodsInput = element(by.id('field_descriptionOfGoods'));
  hsCodeInput = element(by.id('field_hsCode'));
  unitInput = element(by.id('field_unit'));
  quantityInput = element(by.id('field_quantity'));
  valueInput = element(by.id('field_value'));

  importCertAndDeliVerifnSelect = element(by.id('field_importCertAndDeliVerifn'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setExternalIdInput(externalId: string): Promise<void> {
    await this.externalIdInput.sendKeys(externalId);
  }

  async getExternalIdInput(): Promise<string> {
    return await this.externalIdInput.getAttribute('value');
  }

  async setDescriptionOfGoodsInput(descriptionOfGoods: string): Promise<void> {
    await this.descriptionOfGoodsInput.sendKeys(descriptionOfGoods);
  }

  async getDescriptionOfGoodsInput(): Promise<string> {
    return await this.descriptionOfGoodsInput.getAttribute('value');
  }

  async setHsCodeInput(hsCode: string): Promise<void> {
    await this.hsCodeInput.sendKeys(hsCode);
  }

  async getHsCodeInput(): Promise<string> {
    return await this.hsCodeInput.getAttribute('value');
  }

  async setUnitInput(unit: string): Promise<void> {
    await this.unitInput.sendKeys(unit);
  }

  async getUnitInput(): Promise<string> {
    return await this.unitInput.getAttribute('value');
  }

  async setQuantityInput(quantity: string): Promise<void> {
    await this.quantityInput.sendKeys(quantity);
  }

  async getQuantityInput(): Promise<string> {
    return await this.quantityInput.getAttribute('value');
  }

  async setValueInput(value: string): Promise<void> {
    await this.valueInput.sendKeys(value);
  }

  async getValueInput(): Promise<string> {
    return await this.valueInput.getAttribute('value');
  }

  async importCertAndDeliVerifnSelectLastOption(): Promise<void> {
    await this.importCertAndDeliVerifnSelect.all(by.tagName('option')).last().click();
  }

  async importCertAndDeliVerifnSelectOption(option: string): Promise<void> {
    await this.importCertAndDeliVerifnSelect.sendKeys(option);
  }

  getImportCertAndDeliVerifnSelect(): ElementFinder {
    return this.importCertAndDeliVerifnSelect;
  }

  async getImportCertAndDeliVerifnSelectedOption(): Promise<string> {
    return await this.importCertAndDeliVerifnSelect.element(by.css('option:checked')).getText();
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

export class ImportInformationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-importInformation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-importInformation'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
