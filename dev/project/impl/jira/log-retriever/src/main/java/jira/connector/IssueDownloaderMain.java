package jira.connector;

import database.manager.DataBaseType;

public class IssueDownloaderMain
{

	public static void main(String[] args) throws Exception
	{
		IssueDownloader id = new IssueDownloader(DataBaseType.TEST);
		//id.retriveAllIssues();
		id.getIssueDbContext().initDbm();
		id.addIssuesFromSpringProject();
		id.getIssueDbContext().closeDbm();
	}

}
