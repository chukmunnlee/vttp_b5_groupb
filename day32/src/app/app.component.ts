import { Component, inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Task } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  //private fb!: FormBuilder

  protected form!: FormGroup
  protected activites!: FormArray

  protected taskControlName = 'taskName'

  // constructor injection
  constructor() { console.info('in constructor: ') }

  ngOnInit(): void {
    console.info('>>> in ngOnInit')
    this.form = this.createForm()
  }

  protected addActivity() {
    this.activites.push(this.createActivity())
  }

  protected deleteActivity(idx: number) {
    console.info('>>>> idx: ', idx)
    this.activites.removeAt(idx)
  }

  protected invalid(): boolean {
    return this.form.invalid || this.activites.controls.length <= 0
  }

  protected isCtrlInvalid(ctrlName: string): boolean {
    return !!this.form.get(ctrlName)?.invalid
  }

  protected processForm(): void {
    // const values: Task = {
    //   taskName: this.form.controls['taskName'].value,
    //   dueDate: this.form.controls['dueDate'].value,
    //   priority: this.form.controls['priority'].value,
    //   activities: this.form.controls['activities'].value
    // }

    const values: Task = this.form.value
    console.info('>>> values: ', values)

    //this.form.reset()
  }

  private createActivity(): FormGroup {
    return this.fb.group({
      activityName: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ])
    })
  }

  private createForm(): FormGroup {
    this.activites = this.fb.array([], [ Validators.minLength(1) ])
    return this.fb.group({
      taskName: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      dueDate: this.fb.control<string>('', [ Validators.required ]),
      priority: this.fb.control<string>('med'),
      activities: this.activites
    })
  }
}
