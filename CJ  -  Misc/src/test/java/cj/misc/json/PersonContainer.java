package cj.misc.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PersonContainer
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String info;

	@XmlElement
	private List<Person> persons = new ArrayList<>();

	public String getInfo()
	{
		return this.info;
	}

	public List<Person> getPersons()
	{
		return Collections.unmodifiableList(this.persons);
	}

}
