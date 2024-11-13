package vttp.batch5.ssf.day13.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Task {

	@NotNull(message="Task name cannot be empty")
	@NotEmpty(message="Task name cannot be empty")
	@Size(min=5, message="Task name must be at least 5 characters long")
	private String name;

	private String priority = "medium";

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@FutureOrPresent(message="Must be current or a future date")
	private Date dueDate;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setPriority(String priority) { this.priority = priority; }
	public String getPriority() { return this.priority; }

	public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
	public Date getDueDate() { return this.dueDate; }

	@Override
	public String toString() {
		return "Task{name=%s, priority=%s, dueDate=%s}".formatted(
				this.name, this.priority, this.dueDate);
	}

}
