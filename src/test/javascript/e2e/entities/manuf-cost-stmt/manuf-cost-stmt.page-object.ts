import { element, by, ElementFinder } from 'protractor';

export class ManufCostStmtComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-manuf-cost-stmt div table .btn-danger'));
  title = element.all(by.css('jhi-manuf-cost-stmt div h2#page-heading span')).first();
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

export class ManufCostStmtUpdatePage {
  pageTitle = element(by.id('jhi-manuf-cost-stmt-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameOfManufacturerInput = element(by.id('field_nameOfManufacturer'));
  uniqueEntityNumberInput = element(by.id('field_uniqueEntityNumber'));
  ftaSchemeInput = element(by.id('field_ftaScheme'));
  isQRVCInput = element(by.id('field_isQRVC'));
  isCTCInput = element(by.id('field_isCTC'));
  isMPInput = element(by.id('field_isMP'));
  descriptionOfGoodsInput = element(by.id('field_descriptionOfGoods'));
  modelInput = element(by.id('field_model'));
  hsCodeOfGoodsInput = element(by.id('field_hsCodeOfGoods'));
  statementDateInput = element(by.id('field_statementDate'));
  incotermOfFtaInput = element(by.id('field_incotermOfFta'));
  fobValueOfGoodsInput = element(by.id('field_fobValueOfGoods'));
  noOfMCSUnitsInput = element(by.id('field_noOfMCSUnits'));
  directLabourCostInput = element(by.id('field_directLabourCost'));
  directOverheadCostInput = element(by.id('field_directOverheadCost'));
  profitInput = element(by.id('field_profit'));
  otherCostInput = element(by.id('field_otherCost'));
  isDeclared1Input = element(by.id('field_isDeclared1'));
  declaringName1Input = element(by.id('field_declaringName1'));
  declarePosition1Input = element(by.id('field_declarePosition1'));
  declareOn1Input = element(by.id('field_declareOn1'));
  isDeclared2Input = element(by.id('field_isDeclared2'));
  declaringName2Input = element(by.id('field_declaringName2'));
  declarePosition2Input = element(by.id('field_declarePosition2'));
  declareOn2Input = element(by.id('field_declareOn2'));
  isDeclared3Input = element(by.id('field_isDeclared3'));
  declaringName3Input = element(by.id('field_declaringName3'));
  declarePosition3Input = element(by.id('field_declarePosition3'));
  declareOn3Input = element(by.id('field_declareOn3'));
  totalNonOrigMatInput = element(by.id('field_totalNonOrigMat'));
  totalOrigMatInput = element(by.id('field_totalOrigMat'));
  qvcRvcInput = element(by.id('field_qvcRvc'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameOfManufacturerInput(nameOfManufacturer: string): Promise<void> {
    await this.nameOfManufacturerInput.sendKeys(nameOfManufacturer);
  }

  async getNameOfManufacturerInput(): Promise<string> {
    return await this.nameOfManufacturerInput.getAttribute('value');
  }

  async setUniqueEntityNumberInput(uniqueEntityNumber: string): Promise<void> {
    await this.uniqueEntityNumberInput.sendKeys(uniqueEntityNumber);
  }

  async getUniqueEntityNumberInput(): Promise<string> {
    return await this.uniqueEntityNumberInput.getAttribute('value');
  }

  async setFtaSchemeInput(ftaScheme: string): Promise<void> {
    await this.ftaSchemeInput.sendKeys(ftaScheme);
  }

  async getFtaSchemeInput(): Promise<string> {
    return await this.ftaSchemeInput.getAttribute('value');
  }

  getIsQRVCInput(): ElementFinder {
    return this.isQRVCInput;
  }

  getIsCTCInput(): ElementFinder {
    return this.isCTCInput;
  }

  getIsMPInput(): ElementFinder {
    return this.isMPInput;
  }

  async setDescriptionOfGoodsInput(descriptionOfGoods: string): Promise<void> {
    await this.descriptionOfGoodsInput.sendKeys(descriptionOfGoods);
  }

  async getDescriptionOfGoodsInput(): Promise<string> {
    return await this.descriptionOfGoodsInput.getAttribute('value');
  }

  async setModelInput(model: string): Promise<void> {
    await this.modelInput.sendKeys(model);
  }

  async getModelInput(): Promise<string> {
    return await this.modelInput.getAttribute('value');
  }

  async setHsCodeOfGoodsInput(hsCodeOfGoods: string): Promise<void> {
    await this.hsCodeOfGoodsInput.sendKeys(hsCodeOfGoods);
  }

  async getHsCodeOfGoodsInput(): Promise<string> {
    return await this.hsCodeOfGoodsInput.getAttribute('value');
  }

  async setStatementDateInput(statementDate: string): Promise<void> {
    await this.statementDateInput.sendKeys(statementDate);
  }

  async getStatementDateInput(): Promise<string> {
    return await this.statementDateInput.getAttribute('value');
  }

  async setIncotermOfFtaInput(incotermOfFta: string): Promise<void> {
    await this.incotermOfFtaInput.sendKeys(incotermOfFta);
  }

  async getIncotermOfFtaInput(): Promise<string> {
    return await this.incotermOfFtaInput.getAttribute('value');
  }

  async setFobValueOfGoodsInput(fobValueOfGoods: string): Promise<void> {
    await this.fobValueOfGoodsInput.sendKeys(fobValueOfGoods);
  }

  async getFobValueOfGoodsInput(): Promise<string> {
    return await this.fobValueOfGoodsInput.getAttribute('value');
  }

  async setNoOfMCSUnitsInput(noOfMCSUnits: string): Promise<void> {
    await this.noOfMCSUnitsInput.sendKeys(noOfMCSUnits);
  }

  async getNoOfMCSUnitsInput(): Promise<string> {
    return await this.noOfMCSUnitsInput.getAttribute('value');
  }

  async setDirectLabourCostInput(directLabourCost: string): Promise<void> {
    await this.directLabourCostInput.sendKeys(directLabourCost);
  }

  async getDirectLabourCostInput(): Promise<string> {
    return await this.directLabourCostInput.getAttribute('value');
  }

  async setDirectOverheadCostInput(directOverheadCost: string): Promise<void> {
    await this.directOverheadCostInput.sendKeys(directOverheadCost);
  }

  async getDirectOverheadCostInput(): Promise<string> {
    return await this.directOverheadCostInput.getAttribute('value');
  }

  async setProfitInput(profit: string): Promise<void> {
    await this.profitInput.sendKeys(profit);
  }

  async getProfitInput(): Promise<string> {
    return await this.profitInput.getAttribute('value');
  }

  async setOtherCostInput(otherCost: string): Promise<void> {
    await this.otherCostInput.sendKeys(otherCost);
  }

  async getOtherCostInput(): Promise<string> {
    return await this.otherCostInput.getAttribute('value');
  }

  getIsDeclared1Input(): ElementFinder {
    return this.isDeclared1Input;
  }

  async setDeclaringName1Input(declaringName1: string): Promise<void> {
    await this.declaringName1Input.sendKeys(declaringName1);
  }

  async getDeclaringName1Input(): Promise<string> {
    return await this.declaringName1Input.getAttribute('value');
  }

  async setDeclarePosition1Input(declarePosition1: string): Promise<void> {
    await this.declarePosition1Input.sendKeys(declarePosition1);
  }

  async getDeclarePosition1Input(): Promise<string> {
    return await this.declarePosition1Input.getAttribute('value');
  }

  async setDeclareOn1Input(declareOn1: string): Promise<void> {
    await this.declareOn1Input.sendKeys(declareOn1);
  }

  async getDeclareOn1Input(): Promise<string> {
    return await this.declareOn1Input.getAttribute('value');
  }

  getIsDeclared2Input(): ElementFinder {
    return this.isDeclared2Input;
  }

  async setDeclaringName2Input(declaringName2: string): Promise<void> {
    await this.declaringName2Input.sendKeys(declaringName2);
  }

  async getDeclaringName2Input(): Promise<string> {
    return await this.declaringName2Input.getAttribute('value');
  }

  async setDeclarePosition2Input(declarePosition2: string): Promise<void> {
    await this.declarePosition2Input.sendKeys(declarePosition2);
  }

  async getDeclarePosition2Input(): Promise<string> {
    return await this.declarePosition2Input.getAttribute('value');
  }

  async setDeclareOn2Input(declareOn2: string): Promise<void> {
    await this.declareOn2Input.sendKeys(declareOn2);
  }

  async getDeclareOn2Input(): Promise<string> {
    return await this.declareOn2Input.getAttribute('value');
  }

  getIsDeclared3Input(): ElementFinder {
    return this.isDeclared3Input;
  }

  async setDeclaringName3Input(declaringName3: string): Promise<void> {
    await this.declaringName3Input.sendKeys(declaringName3);
  }

  async getDeclaringName3Input(): Promise<string> {
    return await this.declaringName3Input.getAttribute('value');
  }

  async setDeclarePosition3Input(declarePosition3: string): Promise<void> {
    await this.declarePosition3Input.sendKeys(declarePosition3);
  }

  async getDeclarePosition3Input(): Promise<string> {
    return await this.declarePosition3Input.getAttribute('value');
  }

  async setDeclareOn3Input(declareOn3: string): Promise<void> {
    await this.declareOn3Input.sendKeys(declareOn3);
  }

  async getDeclareOn3Input(): Promise<string> {
    return await this.declareOn3Input.getAttribute('value');
  }

  async setTotalNonOrigMatInput(totalNonOrigMat: string): Promise<void> {
    await this.totalNonOrigMatInput.sendKeys(totalNonOrigMat);
  }

  async getTotalNonOrigMatInput(): Promise<string> {
    return await this.totalNonOrigMatInput.getAttribute('value');
  }

  async setTotalOrigMatInput(totalOrigMat: string): Promise<void> {
    await this.totalOrigMatInput.sendKeys(totalOrigMat);
  }

  async getTotalOrigMatInput(): Promise<string> {
    return await this.totalOrigMatInput.getAttribute('value');
  }

  async setQvcRvcInput(qvcRvc: string): Promise<void> {
    await this.qvcRvcInput.sendKeys(qvcRvc);
  }

  async getQvcRvcInput(): Promise<string> {
    return await this.qvcRvcInput.getAttribute('value');
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

export class ManufCostStmtDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-manufCostStmt-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-manufCostStmt'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
