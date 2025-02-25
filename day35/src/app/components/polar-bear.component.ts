import { Component, inject, OnInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-polar-bear',
  standalone: false,
  templateUrl: './polar-bear.component.html',
  styleUrl: './polar-bear.component.css'
})
export class PolarBearComponent implements OnInit {

  private  title = inject(Title)
  private metadata = inject(Meta)

  ngOnInit(): void {
      this.title.setTitle('Home')
  }

}
