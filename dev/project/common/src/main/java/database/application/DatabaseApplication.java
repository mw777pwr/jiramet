package database.application;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import database.entity.Assignee;
import database.entity.IssueComment;
import database.entity.JiraIssue;
import database.entity.JiraProject;
import database.exception.DatabaseAccessException;
import database.exception.IssueNotFoundException;
import database.manager.DatabaseManager;
import utils.properties.PropertiesReader;
import utils.properties.hibernate.HibernateProductionConfiguration;

public class DatabaseApplication
{
	private Session session;
	private Criteria criteria;
	@SuppressWarnings("rawtypes")
	private List issues;
	@SuppressWarnings("rawtypes")
	private List projects;
	private Logger logger = Logger.getLogger(DatabaseApplication.class.getName());

	public DatabaseApplication(PropertiesReader propertiesReader)
	{
		DatabaseManager dbm = new DatabaseManager(new HibernateProductionConfiguration(propertiesReader));
		dbm.init();
		session = dbm.getSession();
	}

	public DatabaseApplication(SessionFactory sessionFactory)
	{
		session = sessionFactory.getCurrentSession();
	}

	public JiraIssue getJiraIssue(int issueID)
	{
		criteria = session.createCriteria(JiraIssue.class);
		issues = criteria.add(Restrictions.eq("id", issueID)).list();
		if (!issues.isEmpty())
		{
			return (JiraIssue) issues.get(0);
		} else
		{
			throw new IssueNotFoundException(issueID);
		}
	}

	public JiraProject getJiraProject(int projectID)
	{
		try
		{
			criteria = session.createCriteria(JiraProject.class);
			projects = criteria.add(Restrictions.eq("id", projectID)).list();
			if (projects.size() >= 1)
				return (JiraProject) projects.get(0);
			else
				throw new DatabaseAccessException();
		} catch (DatabaseAccessException ex)
		{
			logger.error(ex);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getJiraProjects()
	{
		try
		{
			criteria = session.createCriteria(JiraProject.class);
			projects = criteria.list();
			if (projects.size() >= 1)
				return projects;
			else
				throw new DatabaseAccessException();
		} catch (DatabaseAccessException ex)
		{
			logger.error(ex);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getJiraIssues(JiraProject projectID)
	{
		try
		{
			criteria = session.createCriteria(JiraIssue.class);
			criteria.add(Restrictions.eq("jiraProject", projectID));
			issues = criteria.list();
			if (issues.size() >= 1)
				return issues;
			else
				throw new DatabaseAccessException();
		} catch (DatabaseAccessException ex)
		{
			logger.error(ex);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getJiraAssignees(JiraProject jiraProject)
	{
		try
		{
			criteria = session.createCriteria(Assignee.class);
			criteria.add(Restrictions.ne("name", "Unassigned"));
			List assignees = criteria.list();
			if (assignees.size() >= 1)
				return assignees;
			else
				throw new DatabaseAccessException();
		} catch (DatabaseAccessException ex)
		{
			logger.error(ex);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getAssigneesComments(Assignee assignee)
	{
		try
		{
			criteria = session.createCriteria(IssueComment.class);
			criteria.add(Restrictions.eq("addedBy", assignee.getName()));
			List comments = criteria.list();
			if (comments.size() >= 1)
				return comments;
			else
				throw new DatabaseAccessException();
		} catch (DatabaseAccessException ex)
		{
			logger.error(ex);
		}
		return null;
	}

	public void closeSession()
	{
		session.close();
	}

}
