import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContent, Content } from 'app/shared/model/content.model';
import { ContentService } from './content.service';

@Component({
  selector: 'jhi-content-update',
  templateUrl: './content-update.component.html',
})
export class ContentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    url: [],
    orgName: [],
    bucketName: [],
    objectKey: [],
  });

  constructor(protected contentService: ContentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ content }) => {
      this.updateForm(content);
    });
  }

  updateForm(content: IContent): void {
    this.editForm.patchValue({
      id: content.id,
      name: content.name,
      url: content.url,
      orgName: content.orgName,
      bucketName: content.bucketName,
      objectKey: content.objectKey,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const content = this.createFromForm();
    if (content.id !== undefined) {
      this.subscribeToSaveResponse(this.contentService.update(content));
    } else {
      this.subscribeToSaveResponse(this.contentService.create(content));
    }
  }

  private createFromForm(): IContent {
    return {
      ...new Content(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      url: this.editForm.get(['url'])!.value,
      orgName: this.editForm.get(['orgName'])!.value,
      bucketName: this.editForm.get(['bucketName'])!.value,
      objectKey: this.editForm.get(['objectKey'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
