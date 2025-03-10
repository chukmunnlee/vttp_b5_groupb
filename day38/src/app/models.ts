export interface  Task {
  id: string
  name: string
  priority: string
}

export interface TaskSlice {
  tasks: Task[]
  audit: string[]
  priorityFilter: string
}
