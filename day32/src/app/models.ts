export interface Activity {
  activityName: string
}

export interface Task {
  taskName: string
  dueDate: string
  priority: string
  activities: Activity[]
}
