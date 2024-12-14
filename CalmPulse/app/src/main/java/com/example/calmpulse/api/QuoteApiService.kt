package com.example.calmpulse.api



import com.example.calmpulse.model.Quote
import retrofit2.http.GET

interface QuoteApiService {
    @GET("random") // Endpoint for a random quote
    suspend fun getRandomQuote(): List<Quote> // ZenQuotes returns a list
}
