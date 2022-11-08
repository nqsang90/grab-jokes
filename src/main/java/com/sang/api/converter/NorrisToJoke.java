package com.sang.api.converter;

import java.util.Arrays;

import com.sang.api.Joke;
import com.sang.api.JokeSearchResponse;
import com.sang.client.chucknorris.NorrisJoke;
import com.sang.client.chucknorris.SearchResponse;

public class NorrisToJoke {
	public static Joke convertJoke(NorrisJoke norris) {
		Joke joke = new Joke();
		joke.id = norris.id;
		joke.value = norris.value;
		joke.iconUrl = norris.iconUrl;
		return joke;
	}
	
	public static JokeSearchResponse convertResponse(SearchResponse norris) {
		JokeSearchResponse response = new JokeSearchResponse();
		response.total = norris.total;
		response.result = Arrays.stream(norris.result).map(NorrisToJoke::convertJoke).toArray(Joke[]::new);
		return response;
	}
}
