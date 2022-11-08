package com.sang.client.chucknorris;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NorrisClient {
	private Client client;
	private String norrisBaseUrl;
	
	public NorrisClient(Client client, String norrisBaseUrl) {
		this.client = client;
		this.norrisBaseUrl = norrisBaseUrl;
	}
	
	public SearchResponse jokesSearch(String keyword) throws UnsupportedEncodingException {
		String uri = this.norrisBaseUrl + "/jokes/search?query=" + URLEncoder.encode(keyword, "UTF-8");
		WebTarget webTarget = this.client.target(uri);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		SearchResponse r = response.readEntity(SearchResponse.class);
		return r;
	}
}
