package vttp.batch5.ssf.day13.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import vttp.batch5.ssf.day13.models.Task;

import static vttp.batch5.ssf.day13.Constants.*;

@Controller
@RequestMapping("/task")
public class TodoController {

	// Get the logger. The logger's name is the class name
	private final Logger logger = Logger.getLogger(TodoController.class.getName());

	@PostMapping
	public String postTask(Model model, HttpSession sess
			, @Valid @ModelAttribute Task task, BindingResult bindings) {

		if (bindings.hasErrors())
			return "todo";

		List<Task> taskList = getTaskList(sess);

		// Fake business logic
		if (taskList.size() > 3) {
			logger.warning("Free tier user is trying to create more than 3 tasks");
			ObjectError err = new ObjectError("globalError"
					, "You are on free tier. You cannot have more than 2 tasks");
			bindings.addError(err);
			return "todo";
		}

		logger.info("Task %s".formatted(task));

		taskList.add(task);

		model.addAttribute(ATTR_TASK, new Task());
		model.addAttribute(ATTR_TASK_LIST, taskList);

		return "todo";
	}
}
