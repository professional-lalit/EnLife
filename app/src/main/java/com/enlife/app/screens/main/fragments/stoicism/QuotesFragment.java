package com.enlife.app.screens.main.fragments.stoicism;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.common.QuotesManager;
import com.enlife.app.database.models.Quote;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.enlife.app.utils.DividerItemDecoration;
import com.enlife.app.utils.VerticalSpaceDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


public class QuotesFragment extends Fragment {


    public static QuotesFragment getInstance() {
        return new QuotesFragment();
    }

    @Inject
    QuotesManager quotesManager;

    private CustomToolbar customToolbar;
    private RecyclerView recyclerQuotes;

    private List<Quote> quotes = new ArrayList<>();
    private long lastRowId = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .quotesComponentBuilder()
                .create(this)
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quotes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolbar();
        setUpQuoteList();
        loadQuotes();
    }

    private void initViews() {
        customToolbar = requireView().findViewById(R.id.toolbar);
        recyclerQuotes = requireView().findViewById(R.id.recycler_quotes);
    }

    private void initToolbar() {
        customToolbar.titleColor(R.color.black)
                .title("Stoic Quotes")
                .homeIcon(R.drawable.ic_back)
                .backGroundColor(R.color.white);
    }

    private void setUpQuoteList() {
        recyclerQuotes.addItemDecoration(new VerticalSpaceDecorator((int) getResources().getDimension(R.dimen.dimen_size_20)));
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerQuotes.setAdapter(new QuotesAdapter(quotes));
        recyclerQuotes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = Objects.requireNonNull(layoutManager).getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    loadQuotes();
                }
            }
        });
    }

    private void loadQuotes() {
        new Handler(Looper.getMainLooper()).post(() -> {
            quotes.addAll(quotesManager.getQuotes(lastRowId));
            Objects.requireNonNull(recyclerQuotes.getAdapter()).notifyDataSetChanged();
            lastRowId = quotes.get(quotes.size() - 1).getQuoteId();
        });
    }
}