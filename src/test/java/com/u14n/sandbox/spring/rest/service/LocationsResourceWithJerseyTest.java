package com.u14n.sandbox.spring.rest.service;

import sun.misc.BASE64Encoder;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LocationsResourceWithJerseyTest extends JerseyTest {

	/**
	 * @return
	 */
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

//	/**
//	 * @throws JSONException
//	 * @throws URISyntaxException
//	 */
//	@Test
//	public void testLocationsGetSuccess() throws JSONException, URISyntaxException {
//		WebResource webResource = client().resource("http://localhost:8080/");
//		JSONObject json = webResource.path("/restapi/locations.json").get(JSONObject.class);
//	}

	// /**
	// * @throws JSONException
	// * @throws URISyntaxException
	// */
	// @Test
	// public void testLocations9223372035280100646GetSuccess()
	// throws JSONException, URISyntaxException {
	// JSONObject json = client()
	// .resource("http://localhost:8080/")
	// .path("/restapi/locations/9223372035280100646")
	// .get(JSONObject.class);
	// assertEquals("USA" , json.get("countryCode"));
	// assertEquals("NC" , json.get("regionCode" ));
	// assertEquals("27601" , json.get("postalCode" ));
	// assertEquals("Raleigh" , json.get("city" ));
	// assertEquals("500 S McDowell St" , json.get("street" ));
	// assertEquals("-1" , json.get("revision" ));
	// assertEquals("9223372035280100646", json.get("id" ));
	// }

	// @Test(expected = UniformInterfaceException.class)
	// public void testLocationsNotFound() {
	// WebResource webResource = client().resource("http://localhost:8080/");
	// JSONObject json = webResource.path("/restapi/locations/id/666")
	// .get(JSONObject.class);
	// }

}