package vttp.batch5.ssf.day13;

import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.day13.models.Task;

public class Constants {

	public static final String ATTR_TASK = "task";
	public static final String ATTR_TASK_LIST = "taskList";

	public static List<Task> getTaskList(HttpSession sess) {

		List<Task> taskList = (List<Task>)sess.getAttribute(ATTR_TASK_LIST);

		// Initialize the session if it is a new session
		if (null == taskList) {
			taskList = new LinkedList<>();
			sess.setAttribute(ATTR_TASK_LIST, taskList);
		}

		return taskList;
	}
}
