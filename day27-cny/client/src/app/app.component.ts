import { Component, inject, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PurchaseOrder} from './models';
import {PurchaseOrderService} from './purchaseorder.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  private poSvc = inject(PurchaseOrderService)

  poForm!: FormGroup
  lineItemsArray!: FormArray

  ngOnInit(): void {
    this.poForm = this.createForm()
  }

  protected processForm() {
    const po: PurchaseOrder = this.poForm.value
    console.info('>>> po: ', po)
    this.poSvc.createPO(po)
      .then(resp => {
        alert(`Your order id is ${resp.poId}`)
        this.poForm = this.createForm()
      })
      .catch(err => alert(`ERROR:\n${JSON.stringify(err)}`))
  }

  protected addLineItem() {
    this.lineItemsArray.push(this.createLineItem())
  }

  protected removeLineItem(idx: number) {
    this.lineItemsArray.removeAt(idx)
  }

  protected invalid(): boolean {
    return this.poForm.invalid || (this.lineItemsArray.length <= 0)
  }

  private createLineItem(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      quantity: this.fb.control<number>(1, [Validators.required, Validators.min(1)]),
      unitPrice: this.fb.control<number>(.1, [Validators.required, Validators.min(.1)]),
    })
  }

  private createForm(): FormGroup {
    this.lineItemsArray = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>("", [Validators.required]),
      address: this.fb.control<string>("", [Validators.required ]),
      deliveryDate: this.fb.control<string>("", [Validators.required ]),
      lineItems: this.lineItemsArray
    })
  }
}
