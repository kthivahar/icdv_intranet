import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ManufCostStmtComponentsPage, ManufCostStmtDeleteDialog, ManufCostStmtUpdatePage } from './manuf-cost-stmt.page-object';

const expect = chai.expect;

describe('ManufCostStmt e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let manufCostStmtComponentsPage: ManufCostStmtComponentsPage;
  let manufCostStmtUpdatePage: ManufCostStmtUpdatePage;
  let manufCostStmtDeleteDialog: ManufCostStmtDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ManufCostStmts', async () => {
    await navBarPage.goToEntity('manuf-cost-stmt');
    manufCostStmtComponentsPage = new ManufCostStmtComponentsPage();
    await browser.wait(ec.visibilityOf(manufCostStmtComponentsPage.title), 5000);
    expect(await manufCostStmtComponentsPage.getTitle()).to.eq('icdvIntranetApp.manufCostStmt.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(manufCostStmtComponentsPage.entities), ec.visibilityOf(manufCostStmtComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ManufCostStmt page', async () => {
    await manufCostStmtComponentsPage.clickOnCreateButton();
    manufCostStmtUpdatePage = new ManufCostStmtUpdatePage();
    expect(await manufCostStmtUpdatePage.getPageTitle()).to.eq('icdvIntranetApp.manufCostStmt.home.createOrEditLabel');
    await manufCostStmtUpdatePage.cancel();
  });

  it('should create and save ManufCostStmts', async () => {
    const nbButtonsBeforeCreate = await manufCostStmtComponentsPage.countDeleteButtons();

    await manufCostStmtComponentsPage.clickOnCreateButton();

    await promise.all([
      manufCostStmtUpdatePage.setNameOfManufacturerInput('nameOfManufacturer'),
      manufCostStmtUpdatePage.setUniqueEntityNumberInput('uniqueEntityNumber'),
      manufCostStmtUpdatePage.setFtaSchemeInput('ftaScheme'),
      manufCostStmtUpdatePage.setDescriptionOfGoodsInput('descriptionOfGoods'),
      manufCostStmtUpdatePage.setModelInput('model'),
      manufCostStmtUpdatePage.setHsCodeOfGoodsInput('hsCodeOfGoods'),
      manufCostStmtUpdatePage.setStatementDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      manufCostStmtUpdatePage.setIncotermOfFtaInput('incotermOfFta'),
      manufCostStmtUpdatePage.setFobValueOfGoodsInput('5'),
      manufCostStmtUpdatePage.setNoOfMCSUnitsInput('5'),
      manufCostStmtUpdatePage.setDirectLabourCostInput('5'),
      manufCostStmtUpdatePage.setDirectOverheadCostInput('5'),
      manufCostStmtUpdatePage.setProfitInput('5'),
      manufCostStmtUpdatePage.setOtherCostInput('5'),
      manufCostStmtUpdatePage.setDeclaringName1Input('declaringName1'),
      manufCostStmtUpdatePage.setDeclarePosition1Input('declarePosition1'),
      manufCostStmtUpdatePage.setDeclareOn1Input('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      manufCostStmtUpdatePage.setDeclaringName2Input('declaringName2'),
      manufCostStmtUpdatePage.setDeclarePosition2Input('declarePosition2'),
      manufCostStmtUpdatePage.setDeclareOn2Input('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      manufCostStmtUpdatePage.setDeclaringName3Input('declaringName3'),
      manufCostStmtUpdatePage.setDeclarePosition3Input('declarePosition3'),
      manufCostStmtUpdatePage.setDeclareOn3Input('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      manufCostStmtUpdatePage.setTotalNonOrigMatInput('5'),
      manufCostStmtUpdatePage.setTotalOrigMatInput('5'),
      manufCostStmtUpdatePage.setQvcRvcInput('5'),
      manufCostStmtUpdatePage.statusSelectLastOption(),
      manufCostStmtUpdatePage.setCreatedOnInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await manufCostStmtUpdatePage.getNameOfManufacturerInput()).to.eq(
      'nameOfManufacturer',
      'Expected NameOfManufacturer value to be equals to nameOfManufacturer'
    );
    expect(await manufCostStmtUpdatePage.getUniqueEntityNumberInput()).to.eq(
      'uniqueEntityNumber',
      'Expected UniqueEntityNumber value to be equals to uniqueEntityNumber'
    );
    expect(await manufCostStmtUpdatePage.getFtaSchemeInput()).to.eq('ftaScheme', 'Expected FtaScheme value to be equals to ftaScheme');
    const selectedIsQRVC = manufCostStmtUpdatePage.getIsQRVCInput();
    if (await selectedIsQRVC.isSelected()) {
      await manufCostStmtUpdatePage.getIsQRVCInput().click();
      expect(await manufCostStmtUpdatePage.getIsQRVCInput().isSelected(), 'Expected isQRVC not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsQRVCInput().click();
      expect(await manufCostStmtUpdatePage.getIsQRVCInput().isSelected(), 'Expected isQRVC to be selected').to.be.true;
    }
    const selectedIsCTC = manufCostStmtUpdatePage.getIsCTCInput();
    if (await selectedIsCTC.isSelected()) {
      await manufCostStmtUpdatePage.getIsCTCInput().click();
      expect(await manufCostStmtUpdatePage.getIsCTCInput().isSelected(), 'Expected isCTC not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsCTCInput().click();
      expect(await manufCostStmtUpdatePage.getIsCTCInput().isSelected(), 'Expected isCTC to be selected').to.be.true;
    }
    const selectedIsMP = manufCostStmtUpdatePage.getIsMPInput();
    if (await selectedIsMP.isSelected()) {
      await manufCostStmtUpdatePage.getIsMPInput().click();
      expect(await manufCostStmtUpdatePage.getIsMPInput().isSelected(), 'Expected isMP not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsMPInput().click();
      expect(await manufCostStmtUpdatePage.getIsMPInput().isSelected(), 'Expected isMP to be selected').to.be.true;
    }
    expect(await manufCostStmtUpdatePage.getDescriptionOfGoodsInput()).to.eq(
      'descriptionOfGoods',
      'Expected DescriptionOfGoods value to be equals to descriptionOfGoods'
    );
    expect(await manufCostStmtUpdatePage.getModelInput()).to.eq('model', 'Expected Model value to be equals to model');
    expect(await manufCostStmtUpdatePage.getHsCodeOfGoodsInput()).to.eq(
      'hsCodeOfGoods',
      'Expected HsCodeOfGoods value to be equals to hsCodeOfGoods'
    );
    expect(await manufCostStmtUpdatePage.getStatementDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected statementDate value to be equals to 2000-12-31'
    );
    expect(await manufCostStmtUpdatePage.getIncotermOfFtaInput()).to.eq(
      'incotermOfFta',
      'Expected IncotermOfFta value to be equals to incotermOfFta'
    );
    expect(await manufCostStmtUpdatePage.getFobValueOfGoodsInput()).to.eq('5', 'Expected fobValueOfGoods value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getNoOfMCSUnitsInput()).to.eq('5', 'Expected noOfMCSUnits value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getDirectLabourCostInput()).to.eq('5', 'Expected directLabourCost value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getDirectOverheadCostInput()).to.eq('5', 'Expected directOverheadCost value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getProfitInput()).to.eq('5', 'Expected profit value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getOtherCostInput()).to.eq('5', 'Expected otherCost value to be equals to 5');
    const selectedIsDeclared1 = manufCostStmtUpdatePage.getIsDeclared1Input();
    if (await selectedIsDeclared1.isSelected()) {
      await manufCostStmtUpdatePage.getIsDeclared1Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared1Input().isSelected(), 'Expected isDeclared1 not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsDeclared1Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared1Input().isSelected(), 'Expected isDeclared1 to be selected').to.be.true;
    }
    expect(await manufCostStmtUpdatePage.getDeclaringName1Input()).to.eq(
      'declaringName1',
      'Expected DeclaringName1 value to be equals to declaringName1'
    );
    expect(await manufCostStmtUpdatePage.getDeclarePosition1Input()).to.eq(
      'declarePosition1',
      'Expected DeclarePosition1 value to be equals to declarePosition1'
    );
    expect(await manufCostStmtUpdatePage.getDeclareOn1Input()).to.contain(
      '2001-01-01T02:30',
      'Expected declareOn1 value to be equals to 2000-12-31'
    );
    const selectedIsDeclared2 = manufCostStmtUpdatePage.getIsDeclared2Input();
    if (await selectedIsDeclared2.isSelected()) {
      await manufCostStmtUpdatePage.getIsDeclared2Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared2Input().isSelected(), 'Expected isDeclared2 not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsDeclared2Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared2Input().isSelected(), 'Expected isDeclared2 to be selected').to.be.true;
    }
    expect(await manufCostStmtUpdatePage.getDeclaringName2Input()).to.eq(
      'declaringName2',
      'Expected DeclaringName2 value to be equals to declaringName2'
    );
    expect(await manufCostStmtUpdatePage.getDeclarePosition2Input()).to.eq(
      'declarePosition2',
      'Expected DeclarePosition2 value to be equals to declarePosition2'
    );
    expect(await manufCostStmtUpdatePage.getDeclareOn2Input()).to.contain(
      '2001-01-01T02:30',
      'Expected declareOn2 value to be equals to 2000-12-31'
    );
    const selectedIsDeclared3 = manufCostStmtUpdatePage.getIsDeclared3Input();
    if (await selectedIsDeclared3.isSelected()) {
      await manufCostStmtUpdatePage.getIsDeclared3Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared3Input().isSelected(), 'Expected isDeclared3 not to be selected').to.be.false;
    } else {
      await manufCostStmtUpdatePage.getIsDeclared3Input().click();
      expect(await manufCostStmtUpdatePage.getIsDeclared3Input().isSelected(), 'Expected isDeclared3 to be selected').to.be.true;
    }
    expect(await manufCostStmtUpdatePage.getDeclaringName3Input()).to.eq(
      'declaringName3',
      'Expected DeclaringName3 value to be equals to declaringName3'
    );
    expect(await manufCostStmtUpdatePage.getDeclarePosition3Input()).to.eq(
      'declarePosition3',
      'Expected DeclarePosition3 value to be equals to declarePosition3'
    );
    expect(await manufCostStmtUpdatePage.getDeclareOn3Input()).to.contain(
      '2001-01-01T02:30',
      'Expected declareOn3 value to be equals to 2000-12-31'
    );
    expect(await manufCostStmtUpdatePage.getTotalNonOrigMatInput()).to.eq('5', 'Expected totalNonOrigMat value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getTotalOrigMatInput()).to.eq('5', 'Expected totalOrigMat value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getQvcRvcInput()).to.eq('5', 'Expected qvcRvc value to be equals to 5');
    expect(await manufCostStmtUpdatePage.getCreatedOnInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdOn value to be equals to 2000-12-31'
    );

    await manufCostStmtUpdatePage.save();
    expect(await manufCostStmtUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await manufCostStmtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ManufCostStmt', async () => {
    const nbButtonsBeforeDelete = await manufCostStmtComponentsPage.countDeleteButtons();
    await manufCostStmtComponentsPage.clickOnLastDeleteButton();

    manufCostStmtDeleteDialog = new ManufCostStmtDeleteDialog();
    expect(await manufCostStmtDeleteDialog.getDialogTitle()).to.eq('icdvIntranetApp.manufCostStmt.delete.question');
    await manufCostStmtDeleteDialog.clickOnConfirmButton();

    expect(await manufCostStmtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
