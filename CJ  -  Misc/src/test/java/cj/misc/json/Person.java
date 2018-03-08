package cj.misc.json;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Person
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "first-name")
	private String firstName;

	@XmlElement(name = "last-name")
	private String lastName;

	public Person()
	{
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String pFirstName)
	{
		this.firstName = pFirstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String pLastName)
	{
		this.lastName = pLastName;
	}

}
