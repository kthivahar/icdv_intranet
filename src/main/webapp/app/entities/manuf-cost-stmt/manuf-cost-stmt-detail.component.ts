import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';

@Component({
  selector: 'jhi-manuf-cost-stmt-detail',
  templateUrl: './manuf-cost-stmt-detail.component.html',
})
export class ManufCostStmtDetailComponent implements OnInit {
  manufCostStmt: IManufCostStmt | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufCostStmt }) => (this.manufCostStmt = manufCostStmt));
  }

  previousState(): void {
    window.history.back();
  }
}
