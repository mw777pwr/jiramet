package printer;

import java.util.List;

import database.entity.JiraIssue;
import jira.AssigneeTimeResolve;
import jira.prediction.PredictionPrintable;
import utils.properties.PropertiesReader;

public class PredictionTextComposer extends PredictionPrintable
{
	public PredictionTextComposer(PropertiesReader propertiesReader)
	{
		super(propertiesReader);
	}

	private static final String NEW_LINE = "\n";
	private static final int DEFAULT_CAPACITY_STRINGBUILDER_ISSUE_INFORMATION = 500;
	private static final int DEFAULT_CAPACITY_STRINGBUILDER_PER_ASSIGNEE = 200;

	@Override
	public String getPrediction(JiraIssue newIssue, List<AssigneeTimeResolve> assigneeIssueTimes)
	{
		String informationAboutIssue = getInformationAboutIssue(newIssue);
		String informationAboutAssigneeAndTheirTime = getInformationAboutAssigneeAndTheirTime(assigneeIssueTimes);
		return new String(informationAboutIssue + informationAboutAssigneeAndTheirTime);
	}

	private String getInformationAboutIssue(JiraIssue newIssue)
	{
		return new StringBuilder(DEFAULT_CAPACITY_STRINGBUILDER_ISSUE_INFORMATION).append("Project name: ")
				.append(newIssue.getJiraProject().getProjectName()).append(NEW_LINE).append("Issue description: ")
				.append(newIssue.getDescription()).append(NEW_LINE).toString();
	}

	private String getInformationAboutAssigneeAndTheirTime(List<AssigneeTimeResolve> assigneeIssueTimes)
	{
		StringBuilder sb = new StringBuilder(DEFAULT_CAPACITY_STRINGBUILDER_PER_ASSIGNEE * assigneeIssueTimes.size());
		for (AssigneeTimeResolve assigneeIssueTime : assigneeIssueTimes)
		{
			sb.append("Assignee: ").append(assigneeIssueTime.getAssignee()).append(", time: ")
					.append(assigneeIssueTime.getPredictedTime()).append(NEW_LINE);
		}
		return sb.toString();
	}

}
