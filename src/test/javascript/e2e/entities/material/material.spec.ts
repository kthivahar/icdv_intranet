import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MaterialComponentsPage, MaterialDeleteDialog, MaterialUpdatePage } from './material.page-object';

const expect = chai.expect;

describe('Material e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let materialComponentsPage: MaterialComponentsPage;
  let materialUpdatePage: MaterialUpdatePage;
  let materialDeleteDialog: MaterialDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Materials', async () => {
    await navBarPage.goToEntity('material');
    materialComponentsPage = new MaterialComponentsPage();
    await browser.wait(ec.visibilityOf(materialComponentsPage.title), 5000);
    expect(await materialComponentsPage.getTitle()).to.eq('icdvIntranetApp.material.home.title');
    await browser.wait(ec.or(ec.visibilityOf(materialComponentsPage.entities), ec.visibilityOf(materialComponentsPage.noResult)), 1000);
  });

  it('should load create Material page', async () => {
    await materialComponentsPage.clickOnCreateButton();
    materialUpdatePage = new MaterialUpdatePage();
    expect(await materialUpdatePage.getPageTitle()).to.eq('icdvIntranetApp.material.home.createOrEditLabel');
    await materialUpdatePage.cancel();
  });

  it('should create and save Materials', async () => {
    const nbButtonsBeforeCreate = await materialComponentsPage.countDeleteButtons();

    await materialComponentsPage.clickOnCreateButton();

    await promise.all([
      materialUpdatePage.setDescriptionInput('description'),
      materialUpdatePage.setHscodeInput('hscode'),
      materialUpdatePage.setCountryOfOriginInput('countryOfOrigin'),
      materialUpdatePage.setNameOfManufacturerInput('nameOfManufacturer'),
      materialUpdatePage.setValueOfMaterialsNonOriginatingInput('5'),
      materialUpdatePage.setValueOfMaterialOriginatingInput('5'),
    ]);

    expect(await materialUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await materialUpdatePage.getHscodeInput()).to.eq('hscode', 'Expected Hscode value to be equals to hscode');
    expect(await materialUpdatePage.getCountryOfOriginInput()).to.eq(
      'countryOfOrigin',
      'Expected CountryOfOrigin value to be equals to countryOfOrigin'
    );
    expect(await materialUpdatePage.getNameOfManufacturerInput()).to.eq(
      'nameOfManufacturer',
      'Expected NameOfManufacturer value to be equals to nameOfManufacturer'
    );
    expect(await materialUpdatePage.getValueOfMaterialsNonOriginatingInput()).to.eq(
      '5',
      'Expected valueOfMaterialsNonOriginating value to be equals to 5'
    );
    expect(await materialUpdatePage.getValueOfMaterialOriginatingInput()).to.eq(
      '5',
      'Expected valueOfMaterialOriginating value to be equals to 5'
    );

    await materialUpdatePage.save();
    expect(await materialUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await materialComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Material', async () => {
    const nbButtonsBeforeDelete = await materialComponentsPage.countDeleteButtons();
    await materialComponentsPage.clickOnLastDeleteButton();

    materialDeleteDialog = new MaterialDeleteDialog();
    expect(await materialDeleteDialog.getDialogTitle()).to.eq('icdvIntranetApp.material.delete.question');
    await materialDeleteDialog.clickOnConfirmButton();

    expect(await materialComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
