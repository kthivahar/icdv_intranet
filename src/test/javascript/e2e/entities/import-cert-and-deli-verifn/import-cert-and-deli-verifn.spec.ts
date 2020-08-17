import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ImportCertAndDeliVerifnComponentsPage,
  ImportCertAndDeliVerifnDeleteDialog,
  ImportCertAndDeliVerifnUpdatePage,
} from './import-cert-and-deli-verifn.page-object';

const expect = chai.expect;

describe('ImportCertAndDeliVerifn e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let importCertAndDeliVerifnComponentsPage: ImportCertAndDeliVerifnComponentsPage;
  let importCertAndDeliVerifnUpdatePage: ImportCertAndDeliVerifnUpdatePage;
  let importCertAndDeliVerifnDeleteDialog: ImportCertAndDeliVerifnDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ImportCertAndDeliVerifns', async () => {
    await navBarPage.goToEntity('import-cert-and-deli-verifn');
    importCertAndDeliVerifnComponentsPage = new ImportCertAndDeliVerifnComponentsPage();
    await browser.wait(ec.visibilityOf(importCertAndDeliVerifnComponentsPage.title), 5000);
    expect(await importCertAndDeliVerifnComponentsPage.getTitle()).to.eq('icdvIntranetApp.importCertAndDeliVerifn.home.title');
    await browser.wait(
      ec.or(
        ec.visibilityOf(importCertAndDeliVerifnComponentsPage.entities),
        ec.visibilityOf(importCertAndDeliVerifnComponentsPage.noResult)
      ),
      1000
    );
  });

  it('should load create ImportCertAndDeliVerifn page', async () => {
    await importCertAndDeliVerifnComponentsPage.clickOnCreateButton();
    importCertAndDeliVerifnUpdatePage = new ImportCertAndDeliVerifnUpdatePage();
    expect(await importCertAndDeliVerifnUpdatePage.getPageTitle()).to.eq('icdvIntranetApp.importCertAndDeliVerifn.home.createOrEditLabel');
    await importCertAndDeliVerifnUpdatePage.cancel();
  });

  it('should create and save ImportCertAndDeliVerifns', async () => {
    const nbButtonsBeforeCreate = await importCertAndDeliVerifnComponentsPage.countDeleteButtons();

    await importCertAndDeliVerifnComponentsPage.clickOnCreateButton();

    await promise.all([
      importCertAndDeliVerifnUpdatePage.setExternalIdInput('externalId'),
      importCertAndDeliVerifnUpdatePage.setCompanyNameInput('companyName'),
      importCertAndDeliVerifnUpdatePage.setUniqueEntityNumberUenInput('uniqueEntityNumberUen'),
      importCertAndDeliVerifnUpdatePage.setAddressInput('address'),
      importCertAndDeliVerifnUpdatePage.setNameOfContactPersonInput('nameOfContactPerson'),
      importCertAndDeliVerifnUpdatePage.setDesignationInput('designation'),
      importCertAndDeliVerifnUpdatePage.setContactNoInput('contactNo'),
      importCertAndDeliVerifnUpdatePage.setEmailInput('email'),
      importCertAndDeliVerifnUpdatePage.setExporterCompanyNameInput('exporterCompanyName'),
      importCertAndDeliVerifnUpdatePage.setExporterAddressInput('exporterAddress'),
      importCertAndDeliVerifnUpdatePage.setEuCompanyNameInput('euCompanyName'),
      importCertAndDeliVerifnUpdatePage.setCountryInput('country'),
      importCertAndDeliVerifnUpdatePage.setRadio1Input('radio1'),
      importCertAndDeliVerifnUpdatePage.statusSelectLastOption(),
    ]);

    expect(await importCertAndDeliVerifnUpdatePage.getExternalIdInput()).to.eq(
      'externalId',
      'Expected ExternalId value to be equals to externalId'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getCompanyNameInput()).to.eq(
      'companyName',
      'Expected CompanyName value to be equals to companyName'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getUniqueEntityNumberUenInput()).to.eq(
      'uniqueEntityNumberUen',
      'Expected UniqueEntityNumberUen value to be equals to uniqueEntityNumberUen'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await importCertAndDeliVerifnUpdatePage.getNameOfContactPersonInput()).to.eq(
      'nameOfContactPerson',
      'Expected NameOfContactPerson value to be equals to nameOfContactPerson'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getDesignationInput()).to.eq(
      'designation',
      'Expected Designation value to be equals to designation'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getContactNoInput()).to.eq(
      'contactNo',
      'Expected ContactNo value to be equals to contactNo'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await importCertAndDeliVerifnUpdatePage.getExporterCompanyNameInput()).to.eq(
      'exporterCompanyName',
      'Expected ExporterCompanyName value to be equals to exporterCompanyName'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getExporterAddressInput()).to.eq(
      'exporterAddress',
      'Expected ExporterAddress value to be equals to exporterAddress'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getEuCompanyNameInput()).to.eq(
      'euCompanyName',
      'Expected EuCompanyName value to be equals to euCompanyName'
    );
    expect(await importCertAndDeliVerifnUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await importCertAndDeliVerifnUpdatePage.getRadio1Input()).to.eq('radio1', 'Expected Radio1 value to be equals to radio1');

    await importCertAndDeliVerifnUpdatePage.save();
    expect(await importCertAndDeliVerifnUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await importCertAndDeliVerifnComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ImportCertAndDeliVerifn', async () => {
    const nbButtonsBeforeDelete = await importCertAndDeliVerifnComponentsPage.countDeleteButtons();
    await importCertAndDeliVerifnComponentsPage.clickOnLastDeleteButton();

    importCertAndDeliVerifnDeleteDialog = new ImportCertAndDeliVerifnDeleteDialog();
    expect(await importCertAndDeliVerifnDeleteDialog.getDialogTitle()).to.eq('icdvIntranetApp.importCertAndDeliVerifn.delete.question');
    await importCertAndDeliVerifnDeleteDialog.clickOnConfirmButton();

    expect(await importCertAndDeliVerifnComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
