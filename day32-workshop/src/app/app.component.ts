import { Component, inject, OnInit } from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PurchaseOrder} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)

  pOrder!: FormGroup
  lineItems!: FormArray

  ngOnInit(): void {
    this.pOrder = this.createPurchaseOrder()
  }

  protected processForm() {
    const order: PurchaseOrder = this.pOrder.value
    console.info('>>> ', order)
    this.pOrder = this.createPurchaseOrder()
  }

  protected addLineItem() {
    this.lineItems.push(this.createLineItem())
  }
  protected deleteLineItem(idx: number) {
    this.lineItems.removeAt(idx)
  }

  protected formInvalid(): boolean {
    return !this.lineItems.controls.length || this.pOrder.invalid
  }

  protected invalid(ctrlName: string): boolean {
    return this.invalidCtrl(this.pOrder, ctrlName)
  }
  protected valid(ctrlName: string): boolean {
    return this.validCtrl(this.pOrder, ctrlName)
  }
  protected invalidCtrl(grp: FormGroup | AbstractControl, ctrlName: string) {
    const ctrl = grp.get(ctrlName)
    return !ctrl?.pristine && !!ctrl?.invalid
  }
  protected validCtrl(grp: FormGroup | AbstractControl, ctrlName: string): boolean {
    const ctrl = grp.get(ctrlName)
    return !ctrl?.pristine && !!ctrl?.valid
  }

  private createLineItem(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>('', [ Validators.required, Validators.minLength(3)]),
      unitPrice: this.fb.control<number>(.1, [ Validators.required, Validators.required, Validators.min(.1)]),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.required, Validators.min(1)])
    })
  }
  private createPurchaseOrder(): FormGroup {
    this.lineItems = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      address: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      deliveryDate: this.fb.control<string>('', [ Validators.required ]),
      rush: this.fb.control<boolean>(false),
      lineItems: this.lineItems
    })
  }
}
