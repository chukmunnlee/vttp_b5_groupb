import { AfterContentInit, AfterViewInit, Component, ContentChild, ElementRef, inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-num-input',
  standalone: false,
  templateUrl: './num-input.component.html',
  styleUrl: './num-input.component.css'
})
export class NumInputComponent implements OnInit, AfterViewInit, AfterContentInit {

  @ContentChild('btn')
  button!: HTMLButtonElement

  private _instanceName = "abc"
  @Input()
  set instanceName(name: string) {
    this._instanceName = name;
  }
  get instanceName(): string {
    return this._instanceName;
  }

  get value(): number {
    return this.form.value.num
  }
  set value(val: number) {
    this.form.get('num')?.setValue(val)
  }

  // readonly
  get createdOn(): string {
    return this._createOn.toISOString()
  }

  @Output()
  private onNewNumber = new Subject<number>()

  private fb = inject(FormBuilder)

  protected form!: FormGroup
  private _createOn = new Date()

  ngOnInit(): void {
    this.form = this.fb.group({
      num: this.fb.control<number>(0)
    })
  }

  ngAfterViewInit(): void {
      console.info('>>> button: ', this.button)
  }
  ngAfterContentInit(): void {
      console.info('>>> after content init button: ', this.button)
  }

  process() {
    console.info('>>>> form.value: ', this.form.value)
    //const value = parseInt(this.form.get('num')?.value)
    const value = this.form.value.num
    this.onNewNumber.next(value)
    this.form.reset()
  }
}
