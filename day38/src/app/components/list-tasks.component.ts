import { Component, inject, OnInit } from '@angular/core';
import { TaskStore } from '../task.store.hof';
import { Observable, tap } from 'rxjs';
import { Task } from '../models';

@Component({
  selector: 'app-list-tasks',
  standalone: false,
  templateUrl: './list-tasks.component.html',
  styleUrl: './list-tasks.component.css'
})
export class ListTasksComponent implements OnInit {

  private taskStore = inject(TaskStore)

  protected tasks$!: Observable<Task[]>
  protected allTasks: Task[] = []

  ngOnInit(): void {
    this.tasks$ = this.taskStore.getTasks('all')
    // this.taskStore.getTasks.pipe(
    //   tap(tasks => {
    //     console.info('@@@@@@ tasks: ', tasks)
    //     this.allTasks = tasks
    //   } )
    // ).subscribe()
  }

  filterByPriority($event: any) {
    const priority = $event.target.value
    console.info('>>> priority: ', priority)
    //this.taskStore.updatePriorityView(priority)
    this.tasks$ = this.taskStore.getTasks(priority)
  }

  deleteTask(taskId: string) {
    this.taskStore.deleteTask(taskId)
  }

}
