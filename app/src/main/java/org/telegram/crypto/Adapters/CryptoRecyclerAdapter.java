package org.telegram.crypto.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.telegram.crypto.Models.Data;
import org.telegram.messenger.R;
import org.xmlpull.v1.XmlPullParser;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.CryptoViewHolder> {

    List<Data> data;
    Context context;

    public CryptoRecyclerAdapter(List<Data> data, Context context) {
        this.data = data;
        data.add(0, new Data("#", "Name", "Price", "24h %", "7d %", "Market Cap", "Volume (24h)"));
        this.context = context;
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LinearLayout itemContainer = new LinearLayout(parent.getContext());
        itemContainer.setOrientation(LinearLayout.VERTICAL);
        itemContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 180));

        LinearLayout listItem = new LinearLayout(parent.getContext());
        listItem.setId(R.id.list_item_container);
        listItem.setVerticalGravity(Gravity.CENTER_VERTICAL);
        listItem.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        listItem.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        listItem.setOrientation(LinearLayout.HORIZONTAL);
        listItem.setWeightSum(7);

        itemContainer.addView(listItem);

        TextView tvId = new TextView(context);
        tvId.setPadding(30, 0, 10, 0);
        tvId.setWidth(150);
        tvId.setId(R.id.tv_id);
        listItem.addView(tvId);

        TextView tvName = new TextView(context);
        tvName.setPadding(10, 0, 10, 0);
        tvName.setId(R.id.tv_name);
        tvName.setWidth(350);
        listItem.addView(tvName);


        TextView tvPrice = new TextView(context);
        tvPrice.setPadding(10, 0, 10, 0);
        tvPrice.setId(R.id.tv_price);
        tvPrice.setWidth(350);
        listItem.addView(tvPrice);

        TextView tv24H = new TextView(context);
        tv24H.setPadding(10, 0, 10, 0);
        tv24H.setId(R.id.tv_24h);
        tv24H.setWidth(300);
        listItem.addView(tv24H);


        TextView tv7D = new TextView(context);
        tv7D.setPadding(10, 0, 10, 0);
        tv7D.setId(R.id.tv_7d);
        tv7D.setWidth(300);
        listItem.addView(tv7D);

        TextView tvMarketCap = new TextView(context);
        tvMarketCap.setPadding(10, 0, 10, 0);
        tvMarketCap.setId(R.id.tv_market_cap);
        tvMarketCap.setWidth(450);
        listItem.addView(tvMarketCap);

        TextView tvVolume = new TextView(context);
        tvVolume.setPadding(10, 0, 10, 0);
        tvVolume.setId(R.id.tv_volume);
        tvVolume.setWidth(450);
        listItem.addView(tvVolume);

        CryptoViewHolder cryptoViewHolder = new CryptoViewHolder(itemContainer);
        return cryptoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, int position) {
        final Data singleData = data.get(position);

        if (position != 0) {
            holder.tvID.setText((position) + "");
            holder.tvName.setText(singleData.name + " \n" + singleData.symbol);
            holder.tvName.setTypeface(null, Typeface.BOLD);

            String amountPrice = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(data.get(position).quote.usd.price);
            holder.tvPrice.setText(amountPrice);

            holder.tv24H.setText(String.format("%.2f", singleData.quote.usd.percentageChange24h)+"%");
            if ((data.get(position).quote.usd.percentageChange24h > 0)) {
                holder.tv24H.setTextColor(context.getResources().getColor(R.color.colorGreen));
            } else {
                holder.tv24H.setTextColor(context.getResources().getColor(R.color.colorRed));
            }
            holder.tv7D.setText(String.format("%.2f",singleData.quote.usd.percentageChange7d)+"%");
            if ((data.get(position).quote.usd.percentageChange7d > 0)) {
                holder.tv7D.setTextColor(context.getResources().getColor(R.color.colorGreen));
            } else {
                holder.tv7D.setTextColor(context.getResources().getColor(R.color.colorRed));
            }

            String amountMarketcap = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(data.get(position).quote.usd.marketCap);
            amountMarketcap = amountMarketcap.substring(0, amountMarketcap.length()-3);
            holder.tvMarketcap.setText(amountMarketcap);

            String amountVolume = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(data.get(position).quote.usd.volume24h);
            amountVolume = amountVolume.substring(0, amountVolume.length()-3);
            holder.tvVolume.setText(amountVolume);

        }else{

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                holder.itemContainerLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundDark));
            }

            holder.tvID.setText(data.get(position).getTitleId());
            holder.tvID.setTypeface(null, Typeface.BOLD);
            holder.tvID.setTextSize(16);
            holder.tvName.setText(data.get(position).getTitleName());
            holder.tvName.setTypeface(null, Typeface.BOLD);
            holder.tvName.setTextSize(16);
            holder.tvPrice.setText(data.get(position).getTitlePrice());
            holder.tvPrice.setTypeface(null, Typeface.BOLD);
            holder.tvPrice.setTextSize(16);
            holder.tv24H.setText(data.get(position).getTitlePercentage24H());
            holder.tv24H.setTypeface(null, Typeface.BOLD);
            holder.tv24H.setTextSize(16);
            holder.tv7D.setText(data.get(position).getTitlePercentage7D());
            holder.tv7D.setTypeface(null, Typeface.BOLD);
            holder.tv7D.setTextSize(16);
            holder.tvMarketcap.setText(data.get(position).getTitleMarketCap());
            holder.tvMarketcap.setTypeface(null, Typeface.BOLD);
            holder.tvMarketcap.setTextSize(16);
            holder.tvVolume.setText(data.get(position).getTitleVolume24H());
            holder.tvVolume.setTypeface(null, Typeface.BOLD);
            holder.tvVolume.setTextSize(16);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class CryptoViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvName, tvPrice, tv24H, tv7D, tvMarketcap, tvVolume;
        LinearLayout itemContainerLayout;

        public CryptoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemContainerLayout = itemView.findViewById(R.id.list_item_container);
            this.tvID = itemView.findViewById(R.id.tv_id);
            this.tvName = itemView.findViewById(R.id.tv_name);
            this.tvPrice = itemView.findViewById(R.id.tv_price);
            this.tv24H = itemView.findViewById(R.id.tv_24h);
            this.tv7D = itemView.findViewById(R.id.tv_7d);
            this.tvMarketcap = itemView.findViewById(R.id.tv_market_cap);
            this.tvVolume = itemView.findViewById(R.id.tv_volume);
        }

    }
}


