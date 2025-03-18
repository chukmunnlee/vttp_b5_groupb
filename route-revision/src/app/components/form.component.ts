import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-form',
  standalone: false,
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)

  protected form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  get prestine() {
    return this.form.pristine
  }

  submit() {
    console.info('>>>> form: ', this.form.value)
    this.form = this.createForm()
    this.router.navigate(['/'])
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control<string>('')
    })
  }


}
