package com.u14n.sandbox.spring.rest.service;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationsResourceWithRestAssuredTest {
	private static final int MAX_BUFFER_SIZE = 256; //Maximal size of the chars

	private static Server server;

	/**
	 * @throws Exception
	 * @see <a href="http://stackoverflow.com/questions/5267423/using-a-jetty-server-with-junit-tests">Using a Jetty Server with JUnit tests</a>
	 */
	@BeforeClass
	public static void setUpClass() throws Exception {
		server = new Server(8082);
		server.setStopAtShutdown(true);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setClassLoader(
				LocationsResourceWithRestAssuredTest.class.getClassLoader());
		server.addHandler(webAppContext);
		server.start();
	}

	@Test
	public void shouldGetHttpResponse() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request =
				new HttpGet("http://localhost:8082/restapi/locations");
		HttpResponse response = client.execute(request);
		Reader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		StringBuilder builder = new StringBuilder();
		char[] chars = new char[MAX_BUFFER_SIZE];
		int read;
		while ((read = reader.read(chars, 0, MAX_BUFFER_SIZE)) > 0) {
			builder.append(chars, 0, read);
		}
		String string = builder.toString();
		assertEquals("[{\"countryCode\":\"USA\",\"regionCode\":\"NC\",\"postalCode\":\"27601\",\"city\":\"Raleigh\",\"street\":\"S McDowell St\",\"number\":\"500\",\"revision\":-1,\"id\":9223372035280100646},{\"countryCode\":\"USA\",\"regionCode\":\"NC\",\"postalCode\":\"27601\",\"city\":\"Raleigh\",\"street\":\"E Davie Street\",\"number\":\"100\",\"revision\":-1,\"id\":1818238023}]", string);
																				System.out.println("LocationsResourceWithRestAssuredTest.shouldGetHttpResponse() string=" + string);
		reader.close();
	}

//	@Ignore @Test
//	public void testLocationNotFound() {
//		expect()
//			.body(nullValue())
//		.when()
//			.get("/restapi/locations/666");
//	}

	@Ignore @Test
	public void shouldGetLocations() {
		Response response =
			expect().
				statusCode(200).
			when().
				get("/restapi/locations");
																				System.out.println("LocationsResourceWithRestAssuredTest.shouldGetLocations() response.body().asString()=" + response.body().asString());
	}

	@Ignore @Test
	public void shouldGetLocationsHtml() {
		Response response =
			expect().
				statusCode(200).
			when().
				get("/restapi/locations.html");
																				System.out.println("LocationsResourceWithRestAssuredTest.shouldGetLocationsHtml() response.body().asString()=" + response.body().asString());
	}

	@Ignore @Test
	public void shouldGetLocations9223372035280100646() {
		Response response =
//			expect()
//				.statusCode(200)
////				.body("countryCode", equalTo("USA"))
////				.body("regionCode",  equalTo("NC"))
////				.body("postalCode",  equalTo("27601"))
////				.body("city",        equalTo("Raleigh"))
////				.body("street",      equalTo("500 S McDowell St"))
////				.body("revision",    equalTo("-1"))
////				.body("id",          equalTo("9223372035280100646"))
//			.when()
				get("/restapi/locations/9223372035280100646");
																				System.out.println("LocationsResourceWithRestAssuredTest.shouldGetLocations9223372035280100646() response.body().asString()=" + response.body().asString());
	}
}