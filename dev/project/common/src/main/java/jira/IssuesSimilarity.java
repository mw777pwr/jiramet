package jira;

import java.util.Date;

import database.entity.JiraIssue;

/**
 * Interface for retrieving issues similarity level with specified jira issues
 * for assigneed issue
 *
 */
public interface IssuesSimilarity
{
	/**
	 * Evaluating jira issues similarity with specified jira issue
	 * @param assigneeIssues assignee and his jira issues
	 * @param jiraIssueToCompare jira issue which is going to be compared with assignee issues
	 * @param startedAt issues will be taken from that date
	 * @return assignee with jira similarities levels 
	 */
	AssigneeIssueSimilarity getAssigneesWithIssueSimilarities(AssigneeIssues assigneeIssues,
			JiraIssue jiraIssueToCompare, Date startedAt, Date endedAt);
}
