package database.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ISSUE_COMMENT")
public class IssueComment {
	@Id
	@Column(name = "ISSUE_COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int issueCommentId;

	@ManyToOne
	@JoinColumn(name = "JIRA_ISSUE_ID")
	private JiraIssue jiraIssueNew;

	@Column(name = "CONTENT")
	private String content;
	@Column(name = "ADDED_AT")
	private Date addedAt;
	@Column(name = "ADDED_BY")
	private String addedBy;

	public int getIssueCommentId() {
		return issueCommentId;
	}

	public void setIssueCommentId(int issueCommentId) {
		this.issueCommentId = issueCommentId;
	}

	// public JiraIssue getJiraIssue() {
	// return jiraIssue;
	// }
	// public void setJiraIssue(JiraIssue jiraIssue) {
	// this.jiraIssue = jiraIssue;
	// }
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

}
