package jaxrs_test;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;


public class Task {
	private String title;
	private String summary;
	private List<String> comments = new ArrayList<String>();
 	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	} 
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public void addComment(String comment) {
		this.comments.add(comment);
	}

}
