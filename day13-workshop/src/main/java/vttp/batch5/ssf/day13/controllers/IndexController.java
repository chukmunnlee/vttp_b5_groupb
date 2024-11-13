package vttp.batch5.ssf.day13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.day13.models.Task;

import static vttp.batch5.ssf.day13.Constants.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(path={"/", "/index.html"})
public class IndexController {

	private final Logger logger = Logger.getLogger(IndexController.class.getName());

	@GetMapping
	public String getIndex(Model model, HttpSession sess) {

		logger.info("New session started");

		model.addAttribute(ATTR_TASK, new Task());

		List<Task> taskList = getTaskList(sess);

		model.addAttribute(ATTR_TASK, new Task());
		model.addAttribute(ATTR_TASK_LIST, taskList);

		return "todo";
	}

	@PostMapping
	public String postIndex(Model model, HttpSession sess) {

		sess.invalidate();

		model.addAttribute(ATTR_TASK, new Task());
		model.addAttribute(ATTR_TASK_LIST, new LinkedList<Task>());

		return "todo";

	}

}
