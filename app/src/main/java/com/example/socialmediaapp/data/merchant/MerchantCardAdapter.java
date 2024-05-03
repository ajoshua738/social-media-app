package com.example.socialmediaapp.data.merchant;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.card.Card;
import com.example.socialmediaapp.helper.ApplicationClass;
import com.example.socialmediaapp.helper.CommonUtil;
import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.services.CallWebServices;
import com.example.socialmediaapp.services.DataResult;
import com.example.socialmediaapp.services.QueryService;
import com.example.socialmediaapp.services.ServiceConstant;

import java.util.ArrayList;

public class MerchantCardAdapter extends RecyclerView.Adapter<MerchantCardAdapter.MerchantCardViewHolder> {

    static ArrayList<Merchant> merchantList;
    static ArrayList<Card> cardList = new ArrayList<Card>();

    String url = "";

    Activity activity;

    private String client = "";


    public MerchantCardAdapter(ArrayList<Card> cardList, Activity activity) {
        this.cardList = cardList;
        this.activity = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MerchantCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MerchantCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.merchant_card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantCardViewHolder holder, int position) {

//        url = merchantList.get(position).getLogoUrl();
//        Log.d("Card Adapter",""+url);
//
//        Log.d("Client ID",""+ merchantList.get(position).getClient());
//        client = merchantList.get(position).getClient();
//
//
//        holder.tvMerchantName.setText(merchantList.get(position).getCompanyName());
//



        //holder.tvBenefitDesc.setText(cardList.get(position).getBenefitsDesc());

        url = cardList.get(position).getImageUrl();

        Glide.with(holder.itemView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .dontAnimate()//to prevent image follows placeholder size
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(holder.ivMerchantCardImage);


        holder.tvMerchantName.setText(cardList.get(position).getCardName());
        holder.tvBenefitDesc.setText(cardList.get(position).getBenefitsDesc());



    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class MerchantCardViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMerchantCardImage;
        TextView tvMerchantName;

        TextView tvBenefitDesc;
        public MerchantCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMerchantCardImage = itemView.findViewById(R.id.ivMerchantCard);
            tvMerchantName = itemView.findViewById(R.id.tvMerchantName);
            tvBenefitDesc = itemView.findViewById(R.id.tvBenefitDesc);
        }
    }





}
