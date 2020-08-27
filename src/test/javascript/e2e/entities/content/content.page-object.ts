import { element, by, ElementFinder } from 'protractor';

export class ContentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-content div table .btn-danger'));
  title = element.all(by.css('jhi-content div h2#page-heading span')).first();
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

export class ContentUpdatePage {
  pageTitle = element(by.id('jhi-content-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  urlInput = element(by.id('field_url'));
  orgNameInput = element(by.id('field_orgName'));
  bucketNameInput = element(by.id('field_bucketName'));
  objectKeyInput = element(by.id('field_objectKey'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setUrlInput(url: string): Promise<void> {
    await this.urlInput.sendKeys(url);
  }

  async getUrlInput(): Promise<string> {
    return await this.urlInput.getAttribute('value');
  }

  async setOrgNameInput(orgName: string): Promise<void> {
    await this.orgNameInput.sendKeys(orgName);
  }

  async getOrgNameInput(): Promise<string> {
    return await this.orgNameInput.getAttribute('value');
  }

  async setBucketNameInput(bucketName: string): Promise<void> {
    await this.bucketNameInput.sendKeys(bucketName);
  }

  async getBucketNameInput(): Promise<string> {
    return await this.bucketNameInput.getAttribute('value');
  }

  async setObjectKeyInput(objectKey: string): Promise<void> {
    await this.objectKeyInput.sendKeys(objectKey);
  }

  async getObjectKeyInput(): Promise<string> {
    return await this.objectKeyInput.getAttribute('value');
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

export class ContentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-content-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-content'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
