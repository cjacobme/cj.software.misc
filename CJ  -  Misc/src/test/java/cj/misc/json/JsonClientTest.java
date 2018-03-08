package cj.misc.json;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

public class JsonClientTest
{
	@ClassRule
	public static WireMockClassRule wireMockClassRule = new WireMockClassRule(
			WireMockConfiguration.options().dynamicPort());

	@Test
	public void loadPerson() throws IOException, URISyntaxException
	{
		byte[] lAllBytes = Files.readAllBytes(
				Paths.get(this.getClass().getResource("/Person.json").toURI()));
		String lHost = String.format("http://localhost:%d", wireMockClassRule.port());
		stubFor(
				get(urlMatching(".*/person")).willReturn(
						aResponse()
								.withStatus(200)
								.withHeader("Content-Type", MediaType.APPLICATION_JSON)
								.withBody(lAllBytes)));

		Client lClient = ClientBuilder.newClient();
		try
		{
			Response lResponse = lClient
					.target(lHost)
					.path("person")
					.request(MediaType.APPLICATION_JSON)
					.get();
			assertThat(lResponse).as("response").isNotNull();
			assertThat(lResponse.getStatus()).as("status").isEqualTo(200);
			Person lPerson = lResponse.readEntity(Person.class);
			assertThat(lPerson).as("read entity").isNotNull();
			assertThat(lPerson.getFirstName()).as("first name").isEqualTo("Christian");
		}
		finally
		{
			lClient.close();
		}
	}

	@Test
	public void loadContainer() throws IOException, URISyntaxException
	{
		byte[] lAllBytes = Files.readAllBytes(
				Paths.get(this.getClass().getResource("/PersonList.json").toURI()));
		String lHost = String.format("http://localhost:%d", wireMockClassRule.port());
		stubFor(
				get(urlMatching(".*/persons")).willReturn(
						aResponse()
								.withStatus(200)
								.withHeader("Content-Type", MediaType.APPLICATION_JSON)
								.withBody(lAllBytes)));
		Client lClient = ClientBuilder.newClient();
		try
		{
			Response lResponse = lClient
					.target(lHost)
					.path("persons")
					.request(MediaType.APPLICATION_JSON)
					.get();
			assertThat(lResponse).as("response").isNotNull();
			assertThat(lResponse.getStatus()).as("status").isEqualTo(200);
			PersonContainer lPersonContainer = lResponse.readEntity(PersonContainer.class);
			assertThat(lPersonContainer).as("read entity").isNotNull();
			assertThat(lPersonContainer.getInfo()).as("info").isEqualTo("ich hab was gefunden");
			List<Person> lPersons = lPersonContainer.getPersons();
			assertThat(lPersons).as("list of persons").isNotNull().hasSize(2);
			this.assertPerson(lPersons, 0, "Christian", "Jacob");
			this.assertPerson(lPersons, 1, "Barbara", "Jacob");
		}
		finally
		{
			lClient.close();
		}
	}

	private void assertPerson(
			List<Person> pPersons,
			int pIndex,
			String pExpFirstName,
			String pExpLastName)
	{
		Person lPerson = pPersons.get(pIndex);
		assertThat(lPerson).as("Person #%d", pIndex).isNotNull();
		assertThat(lPerson.getFirstName()).as("first name #%d", pIndex).isEqualTo(pExpFirstName);
		assertThat(lPerson.getLastName()).as("last name #%d", pIndex).isEqualTo(pExpLastName);
	}
}
