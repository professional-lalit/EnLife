package com.enlife.app.screens.main.fragments.stoicism;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.enlife.app.R;
import com.enlife.app.database.models.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {
    private List<Quote> quotes;
    List<Integer> bgs = new ArrayList<>();

    public QuotesAdapter(List<Quote> quotes) {
        this.quotes = quotes;
        bgs.add(R.drawable.epictetus1);
        bgs.add(R.drawable.epictetus2);
        bgs.add(R.drawable.epictetus3);
        bgs.add(R.drawable.marcus);
        bgs.add(R.drawable.marcus1);
        bgs.add(R.drawable.marcus2);
        bgs.add(R.drawable.marcus3);
        bgs.add(R.drawable.cleanthes);
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.item_stoic_quote_layout,
                null
        );
        return new QuotesViewHolder(view, bgs);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        holder.onBind(quotes.get(position));
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    static class QuotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtQuote;
        private final TextView txtAuthor;
        private final ImageView imgAuthor;
        private final List<Integer> bgs;

        public QuotesViewHolder(@NonNull View itemView, List<Integer> bgs) {
            super(itemView);
            this.bgs = bgs;
            txtQuote = itemView.findViewById(R.id.txt_quote);
            txtAuthor = itemView.findViewById(R.id.txt_author);
            imgAuthor = itemView.findViewById(R.id.img_author);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(Quote quote) {
            txtQuote.setText(quote.getQuote());
            txtAuthor.setText("-" + quote.getAuthor());
            imgAuthor.setImageResource(bgs.get(getAdapterPosition() % bgs.size()));
        }
    }
}
