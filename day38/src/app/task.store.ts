import { Injectable } from "@angular/core";

import { v4 as uuidv4 } from 'uuid'

import { Task, TaskSlice } from './models'
import { ComponentStore } from "@ngrx/component-store";

const INIT: TaskSlice = {
  tasks: [],
  audit: [],
  priorityFilter: 'all'
}

@Injectable()
export class TaskStore extends ComponentStore<TaskSlice> {

  constructor() {
    // Initialize the store, initally the store is empty
    super(INIT)
  }

  // mutators - update methods
  // addTask(newTask)
  readonly addTask = this.updater<Task>(
    (slice: TaskSlice, newTask: Task) => {
      const toSave: Task = {
        ...newTask,
        id: uuidv4().substring(0, 8)
      }
      console.info('>>> toSave: ', toSave)
      return {
        tasks: [ ...slice.tasks, toSave ],
        audit: [ ...slice.audit, `Added new task on ${new Date()}`],
        priorityFilter: slice.priorityFilter
      } as TaskSlice
    }
  )

  // addTasks([ task1, task2, task3 ])
  // audit - 'Added <n> task on <date>'
  readonly addTasks = this.updater<Task[]>(
    (slice: TaskSlice, tasks: Task[]) => {
      const toSave = tasks.map(t => {
        return {
          ...t,
          id: uuidv4().substring(0, 8)
        }
      })
      return {
        tasks: [ ...slice.tasks, ...toSave ],
        audit: [ ...slice.audit, `Added ${tasks.length} tasks on ${new Date()}`],
        priorityFilter: slice.priorityFilter
      } as TaskSlice
    }
  )

  readonly deleteTask = this.updater<string>(
    (slice: TaskSlice, taskId: string) => {
      return {
        tasks: slice.tasks.filter(t => t.id != taskId),
        audit: [ ...slice.audit, `Deleted task id ${taskId} on ${new Date()}`],
        priorityFilter: slice.priorityFilter
      } as TaskSlice
    }
  )

  readonly updatePriorityView = this.updater<string>(
    (slice: TaskSlice, priority: string) => {
      return {
        tasks: [ ...slice.tasks ],
        audit: [ ...slice.audit ],
        priorityFilter: priority
      }
    }
  )

  // Selector - query
  readonly getTasks = this.select<Task[]>(
    (slice: TaskSlice) => slice.tasks
        .filter(
          t => (slice.priorityFilter == 'all') || (slice.priorityFilter == t.priority)
    )
  )

  readonly getTaskCount$ = this.select<number>(
    (slice: TaskSlice) => slice.tasks
        .filter(
          t => (slice.priorityFilter == 'all') || (slice.priorityFilter == t.priority)
        ).length
  )

}
