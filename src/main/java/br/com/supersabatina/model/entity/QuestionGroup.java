package br.com.supersabatina.model.entity;

public class QuestionGroup {

	private long questionGroupId;
	private User user;
	private String title;
	private String description;

	public long getQuestionGroupId() {
		return questionGroupId;
	}

	public void setQuestionGroupId(long questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
