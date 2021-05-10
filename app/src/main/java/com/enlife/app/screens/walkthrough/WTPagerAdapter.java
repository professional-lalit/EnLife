package com.enlife.app.screens.walkthrough;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;

import java.util.List;

public class WTPagerAdapter extends RecyclerView.Adapter<WTPagerAdapter.WTPagerViewHolder> {

    private final List<PageData> pageDataList;

    public WTPagerAdapter(List<PageData> pageDataList) {
        this.pageDataList = pageDataList;
    }

    @NonNull
    @Override
    public WTPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wt_pager_layout, parent, false);
        return new WTPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WTPagerViewHolder holder, int position) {
        holder.onBind(pageDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return pageDataList.size();
    }

    static class WTPagerViewHolder extends RecyclerView.ViewHolder {

        private PageData pageData;
        private final ImageView imgPagerData;
        private final TextView txtPagerData;

        public WTPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPagerData = itemView.findViewById(R.id.img_pager_data);
            txtPagerData = itemView.findViewById(R.id.txt_pager_data);
        }

        void onBind(PageData pageData) {
            this.pageData = pageData;
            imgPagerData.setImageDrawable(pageData.getPageImage());
            txtPagerData.setText(pageData.getPageText());
        }
    }
}
