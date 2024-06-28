package com.teeniv.quoteapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApi {

    @GET("quotes.php")
    Call<List<Quote>> getRandomQuote(@Query("searchtype") String searchType,
                                     @Query("query") String query,
                                     @Query("format") String format,
                                     @Query("api_key") String apiKey);
}
