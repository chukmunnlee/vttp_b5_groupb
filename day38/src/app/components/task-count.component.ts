import { Component, inject } from '@angular/core';
import { TaskStore } from '../task.store.hof';

@Component({
  selector: 'app-task-count',
  standalone: false,
  templateUrl: './task-count.component.html',
  styleUrl: './task-count.component.css'
})
export class TaskCountComponent {

  protected taskStore = inject(TaskStore)

}
