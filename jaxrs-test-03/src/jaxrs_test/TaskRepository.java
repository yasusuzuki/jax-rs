package jaxrs_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * GASではGoolge Spreadsheetに保存されるもの。
 *　本来ならば、データベースなどに永続化されるべきものだが、
 * スタブ目的なので、そこまでおおがかりにする必要はない。
 * なので、ApplicationScopedでデータの永続化を表現している。
 */
@ApplicationScoped
public class TaskRepository {
	private Task task;
	

	public TaskRepository(){
		task = new Task();
		task.setTitle("これはたいとる");
		task.setSummary("これはサマリ");
		List<String> comments = new ArrayList<String>(){
			{
				add("こめんと１");
				add("こめんと２");
			}
		};
		task.setComments(comments);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
