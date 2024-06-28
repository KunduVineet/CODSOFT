package com.teeniv.quoteapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewQuote;
    private QuoteApi quoteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuote = findViewById(R.id.textview_quote);
        Button buttonRefresh = findViewById(R.id.button_refresh);

        quoteApi = RetrofitClient.getClient().create(QuoteApi.class);

        // Initial fetch of quote
        fetchQuote();

        buttonRefresh.setOnClickListener(v -> fetchQuote());
    }

    private void fetchQuote() {
        Call<List<Quote>> call = quoteApi.getRandomQuote("author", "Albert Einstein", "json", "your_api_key_here");
        call.enqueue(new Callback<List<Quote>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Quote>> call, @NonNull Response<List<Quote>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String quoteText = response.body().get(0).getQuoteText();
                    textViewQuote.setText(quoteText);
                } else {
                    textViewQuote.setText("Failed to fetch quote");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<List<Quote>> call, @NonNull Throwable t) {
                textViewQuote.setText("Error: " + t.getMessage());
            }
        });
    }
}



