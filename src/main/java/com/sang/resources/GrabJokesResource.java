package com.sang.resources;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.validator.constraints.Length;

import com.sang.api.JokeSearchResponse;
import com.sang.api.converter.NorrisToJoke;
import com.sang.client.chucknorris.NorrisClient;
import com.sang.client.chucknorris.SearchResponse;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;


@Path("/grab-jokes")
@Produces(MediaType.APPLICATION_JSON)
public class GrabJokesResource {
	private NorrisClient norrisClient;
	private Bandwidth limit;
	private Map<String, Bucket> buckets;
	
	public GrabJokesResource(NorrisClient norrisClient) {
		// TODO Auto-generated constructor stub
		this.norrisClient = norrisClient;
		this.limit = Bandwidth.simple(5, Duration.ofMinutes(1));
		this.buckets = new HashMap<>();
	}

	@GET
	public JokeSearchResponse searchJokes(@QueryParam("keyword") @Length(min=3, max=120) String keyword) {
		if (!this.buckets.containsKey(keyword)) {
			this.buckets.put(keyword, Bucket.builder().addLimit(limit).build());
		}
		Bucket bucket = this.buckets.get(keyword);
		if (bucket.tryConsume(1)) {
			SearchResponse response;
			try {
				response = this.norrisClient.jokesSearch(keyword);
			} catch (Throwable e) {
				throw new WebApplicationException("We encounter problem handling your request", e, Status.INTERNAL_SERVER_ERROR);
			}
			return NorrisToJoke.convertResponse(response);
		}
		else {
			throw new WebApplicationException(Status.TOO_MANY_REQUESTS);
		}
    }
}
