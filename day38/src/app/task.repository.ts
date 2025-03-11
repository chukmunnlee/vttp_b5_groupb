import { Injectable } from "@angular/core";
import Dexie, { Table } from "dexie";
import { Task } from "./models";

@Injectable()
export class TaskRepository extends Dexie {

  taskTable!: Table<Task, string>;

  constructor() {
    // Database name
    super('taskdb')

    // create collections
    this.version(1).stores({
      tasks: 'id'
    })

    this.taskTable = this.table('tasks')
  }

  removeTask(id: string) {
    return this.taskTable.delete(id)
        .then(() => id)
  }

  saveTask(task: Task): Promise<Task> {
    return this.taskTable.add(task)
        .then(() => task)
  }

  getAllTasks(): Promise<Task[]> {
    return this.taskTable.toArray()
  }

}
