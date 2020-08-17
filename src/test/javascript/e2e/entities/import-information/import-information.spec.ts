import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ImportInformationComponentsPage,
  ImportInformationDeleteDialog,
  ImportInformationUpdatePage,
} from './import-information.page-object';

const expect = chai.expect;

describe('ImportInformation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let importInformationComponentsPage: ImportInformationComponentsPage;
  let importInformationUpdatePage: ImportInformationUpdatePage;
  let importInformationDeleteDialog: ImportInformationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ImportInformations', async () => {
    await navBarPage.goToEntity('import-information');
    importInformationComponentsPage = new ImportInformationComponentsPage();
    await browser.wait(ec.visibilityOf(importInformationComponentsPage.title), 5000);
    expect(await importInformationComponentsPage.getTitle()).to.eq('icdvIntranetApp.importInformation.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(importInformationComponentsPage.entities), ec.visibilityOf(importInformationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ImportInformation page', async () => {
    await importInformationComponentsPage.clickOnCreateButton();
    importInformationUpdatePage = new ImportInformationUpdatePage();
    expect(await importInformationUpdatePage.getPageTitle()).to.eq('icdvIntranetApp.importInformation.home.createOrEditLabel');
    await importInformationUpdatePage.cancel();
  });

  it('should create and save ImportInformations', async () => {
    const nbButtonsBeforeCreate = await importInformationComponentsPage.countDeleteButtons();

    await importInformationComponentsPage.clickOnCreateButton();

    await promise.all([
      importInformationUpdatePage.setExternalIdInput('externalId'),
      importInformationUpdatePage.setDescriptionOfGoodsInput('descriptionOfGoods'),
      importInformationUpdatePage.setHsCodeInput('hsCode'),
      importInformationUpdatePage.setUnitInput('unit'),
      importInformationUpdatePage.setQuantityInput('5'),
      importInformationUpdatePage.setValueInput('5'),
      importInformationUpdatePage.importCertAndDeliVerifnSelectLastOption(),
    ]);

    expect(await importInformationUpdatePage.getExternalIdInput()).to.eq(
      'externalId',
      'Expected ExternalId value to be equals to externalId'
    );
    expect(await importInformationUpdatePage.getDescriptionOfGoodsInput()).to.eq(
      'descriptionOfGoods',
      'Expected DescriptionOfGoods value to be equals to descriptionOfGoods'
    );
    expect(await importInformationUpdatePage.getHsCodeInput()).to.eq('hsCode', 'Expected HsCode value to be equals to hsCode');
    expect(await importInformationUpdatePage.getUnitInput()).to.eq('unit', 'Expected Unit value to be equals to unit');
    expect(await importInformationUpdatePage.getQuantityInput()).to.eq('5', 'Expected quantity value to be equals to 5');
    expect(await importInformationUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');

    await importInformationUpdatePage.save();
    expect(await importInformationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await importInformationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ImportInformation', async () => {
    const nbButtonsBeforeDelete = await importInformationComponentsPage.countDeleteButtons();
    await importInformationComponentsPage.clickOnLastDeleteButton();

    importInformationDeleteDialog = new ImportInformationDeleteDialog();
    expect(await importInformationDeleteDialog.getDialogTitle()).to.eq('icdvIntranetApp.importInformation.delete.question');
    await importInformationDeleteDialog.clickOnConfirmButton();

    expect(await importInformationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
