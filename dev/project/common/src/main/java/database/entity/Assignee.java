package database.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ASSIGNEE")
public class Assignee {

	@Id
	@Column(name = "ASSIGNEE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assigneeId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "assignee")
	private Set<AssignedIssue> assignedIssue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAssigneeId() {
		return assigneeId;
	}
	
	public List<JiraIssue> getAssigneedJiraIssues() {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		for(AssignedIssue assignedIssue : assignedIssue)
		{
			jiraIssues.add(assignedIssue.getJiraIssue());
		}
		return jiraIssues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignee other = (Assignee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
