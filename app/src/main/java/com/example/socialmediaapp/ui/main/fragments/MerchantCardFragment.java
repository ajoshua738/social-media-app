package com.example.socialmediaapp.ui.main.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.card.Card;
import com.example.socialmediaapp.data.merchant.Merchant;
import com.example.socialmediaapp.data.merchant.MerchantCardAdapter;
import com.example.socialmediaapp.databinding.FragmentMerchantCardBinding;
import com.example.socialmediaapp.helper.ApplicationClass;
import com.example.socialmediaapp.helper.BaseActivity;
import com.example.socialmediaapp.helper.CommonUtil;
import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.services.CallWebServices;
import com.example.socialmediaapp.services.DataResult;
import com.example.socialmediaapp.services.QueryService;
import com.example.socialmediaapp.services.ServiceConstant;

import java.util.ArrayList;


public class MerchantCardFragment extends Fragment {

    FragmentMerchantCardBinding binding;
    private static Activity activity;

    private static MerchantCardAdapter merchantCardAdapter;
    private static RecyclerView allRecyclerView;


    private static ArrayList<String> newList;
    private static ArrayList<String> popularList;
    private static ArrayList<String> allList;



    private static ArrayList<Merchant> newCardList = new ArrayList<>();
    private static ArrayList<Merchant> popularCardList = new ArrayList<>();
    private static ArrayList<Merchant> allCardList = new ArrayList<>();


    private static ArrayList<Merchant> merchantList = new ArrayList<>();

    private static ArrayList<Card> cardList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMerchantCardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        allRecyclerView = binding.rvMerchantCard;
        merchantCardAdapter = new MerchantCardAdapter(cardList, activity);
        allRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        allRecyclerView.setAdapter(merchantCardAdapter);



        reqGetCategorizeMerchantHQ();
        return view;
    }


    public static void reqGetCategorizeMerchantHQ() {
//        Log.e("xxx", "Calling GetCategorizeMerchantHQ");
        UserProfile userProfile = ((ApplicationClass) activity.getApplicationContext()).getUserProfile();

//        mProgressBar.setVisibility(View.VISIBLE);
//        mMerchantList.setVisibility(View.GONE);
        //String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + userProfile.getGUID();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + userProfile.getGUID()
                + "|" + BaseActivity.getPhoneManufacturer();
        Log.d("EN DATA MerchantFragment",""+strEnData);
        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ);
        new CallWebServices(QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ, activity, false).execute(b);
//        mSwipeContainer.setRefreshing(false);
    }


    public static void processWSData(Bundle resultBundle) {
        int command = resultBundle.getInt(QueryService.COMMAND);

        DataResult dataResult = resultBundle.getParcelable("dataResult");


        if (command == QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ) {

            if (dataResult.groupValues != null) {

//                if (dataResult.groupValues.get(0) != null) {
//                    newList = CommonUtil.tokenize(dataResult.groupValues.get(0), QueryService.DELIMITER_LIST);
//                }
//                if (dataResult.groupValues.size() > 1) {
//                    popularList = CommonUtil.tokenize(dataResult.groupValues.get(1), QueryService.DELIMITER_LIST);
//                }
                if (dataResult.groupValues.size() > 2) {
                    allList = CommonUtil.tokenize(dataResult.groupValues.get(2), QueryService.DELIMITER_LIST);
                }

//                newCardList.clear();
//                popularCardList.clear();
                allCardList.clear();

//                if (newList != null) {
//                    newList.remove(newList.size() - 1);//remove empty element
//                    merchantList.addAll(getMerchantList(newList));
//                    for (int i = 0; i < merchantList.size(); i++) {
//                        newCardList.add(merchantList.get(i));
//                    }
//                }
//
//                if (popularList != null) {
//                    popularList.remove(popularList.size() - 1);//remove empty element
//                    merchantList.clear();
//                    merchantList.addAll(getMerchantList(popularList));
//                    for (int i = 0; i < merchantList.size(); i++) {
//                        popularCardList.add(merchantList.get(i));
//                    }
//                }

                if (allList != null) {
                    allList.remove(allList.size() - 1);//remove empty element
                    merchantList.clear();
                    merchantList.addAll(getMerchantList(allList));
                    for (int i = 0; i < merchantList.size(); i++) {
                        allCardList.add(merchantList.get(i));
                    }

                }
                merchantList.clear();



                for(Merchant merchant: allCardList){

                    reqCardGetList(merchant.getClient());
                }

            }

//            if (allCardList.size() == 0) {//no card
//                mNoCardLayout.setVisibility(View.VISIBLE);
//                mSearch.setVisibility(View.GONE);
////                mScanCard.setVisibility(View.GONE);
//            }
//            else {
//                mOfflineLayout.setVisibility(View.GONE);
//                mNoCardLayout.setVisibility(View.GONE);
//                mSearch.setVisibility(View.VISIBLE);
////                mScanCard.setVisibility(View.VISIBLE);
//                imgRequest.setVisibility(View.VISIBLE);
//                manageAdapter(tempCardList);
//
//            }




        }
//        else if (command == QueryService.QUERY_GETCARDTYPE_BY_CARDNO) {
//
//            if (dataResult.values != null) {
//                ArrayList<String> strInfoList = CommonUtil.tokenize(
//                        dataResult.values.get(0), QueryService.DELIMITER_FIELD);
//
//                /*
//                  1. companyName
//                  2. cardType
//                  3. cardName
//                  4. client
//                  5. imageUrl
//                  6. benefitsDesc
//                  7. benefitsImage
//                  8. ReferralIndicator
//                  9. RequiredIC
//                  10. RequiredAddress
//                  11. RequiredState
//                  12. RequiredCountry
//                  13. RequiredGender
//                  14. RequiredRace
//                  15. RequiredDesignation
//                  16. RequiredMaritalStatus
//
//                  */
////                if (strInfoList.size() >= 13) {
////                    card = new Card();
////                    card.setCompanyName(strInfoList.get(0));
////                    card.setCardTypeId(strInfoList.get(1));
////                    card.setCardName(strInfoList.get(2));
////                    card.setClientCode(strInfoList.get(3));
////                    card.setImageUrl(strInfoList.get(4));
////                    card.setBenefitsDesc(strInfoList.get(5));
////                    card.setBenefitsImg(strInfoList.get(6));
////                    card.setReferral(strInfoList.get(7));
////
////                    addFields.clear();
////                    addFields.add(strInfoList.get(8));
////                    addFields.add(strInfoList.get(9));
////                    addFields.add(strInfoList.get(10));
////                    addFields.add(strInfoList.get(11));
////                    addFields.add(strInfoList.get(12));
////                    addFields.add(strInfoList.get(13));
////                    addFields.add(strInfoList.get(14));
////                    addFields.add(strInfoList.get(15));
////
////                }
//            }
//
//            MainActivity.showAddCardDialog(card, cardNumber ,true);
//
//        }
//        else if (command == QueryService.QUERY_USERCARD_ADD_P) {
//            MainActivity.dismissAddCardDialog();
//            HomeFragment.reqProfileCardGetList(cardNumber, Constant.ADD_CARD); //refresh wallet
//            MainActivity.changePage(0);
//        }
//        else if (command == QueryService.QUERY_GET_DISPLAY_NAME_BY_CLIENT) { // instant join
//            ArrayList<String> clientName = null;
//            clientName = CommonUtil.tokenize(dataResult.values.get(0), QueryService.DELIMITER_FIELD);
//            Intent intent = new Intent(activity, AddCardActivity.class);
//            intent.putExtra(activity.getString(R.string.card_indicator), "false");
//            intent.putExtra(activity.getString(R.string.client), clientID);
//            intent.putExtra(activity.getString(R.string.merchant_name), clientName.get(0));
//            activity.startActivity(intent);
//        }
    }

    private static void reqCardGetList(String client) {

        ApplicationClass applicationClass = (ApplicationClass) activity.getApplicationContext();
        UserProfile userProfile = applicationClass.getUserProfile();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + client + "|" + userProfile.getGUID();

        Log.d("Client ID",""+client);
        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_CARD_GETLIST);
        new CallWebServices(QueryService.QUERY_CARD_GETLIST, activity, false).execute(b);
    }


    public static void processWSDataCard(Bundle resultBundle) {
        DataResult dataResult = resultBundle.getParcelable("dataResult");

        if (dataResult.values != null) {
            merchantList = new ArrayList<>();
            for (String strCard : dataResult.values) {
                ArrayList<String> cardDataList = CommonUtil.tokenize(strCard, QueryService.DELIMITER_FIELD);

             	/*	1. clientCode
                    2. cardTypeId
                    3. cardName
                    4. backgroundUrl
                    5. logoUrl
                    6. imageUrl
                    7. cardBase
                    8. benefits
                    9. benefit image
                    10. terms n condition
                    11. ownedCard
                    12. authorize
                    13. referral
                    14. availability
                    15. RequiredIC
                    16. RequiredAddress
                    17. RequiredState
                    18. RequiredCountry
                    19. RequiredGender
                    20. RequiredRace
                    21. RequiredDesignation
                    22. RequiredMaritalStatus
                */
                if (cardDataList.size() == 22) {

                    Card card = new Card(cardDataList.get(0), cardDataList.get(1),
                            cardDataList.get(2), cardDataList.get(3), cardDataList.get(4),
                            cardDataList.get(5), cardDataList.get(6), cardDataList.get(7)
                            , cardDataList.get(8), cardDataList.get(9), cardDataList.get(10), cardDataList.get(11), cardDataList.get(12),
                            cardDataList.get(13), cardDataList.get(14), cardDataList.get(15), cardDataList.get(16), cardDataList.get(17),
                            cardDataList.get(18), cardDataList.get(19), cardDataList.get(20), cardDataList.get(21));
                    cardList.add(card);
                    merchantCardAdapter.notifyDataSetChanged();
                }
            }


        }
//        if (cardList.size() == 0) {//no card
//            mNoCardLayout.setVisibility(View.VISIBLE);
//            mCardItem.setVisibility(View.GONE);
//            //swipeRefresh.setRefreshing(false);
//            cancelProgress();
//
//        }
//        else {
//            mNoCardLayout.setVisibility(View.GONE);
//            manageAdapter(cardList);
//            if(physical.equalsIgnoreCase("true")){
//                int position = mItemScrollView.getCurrentItem();
//                Intent intent = new Intent(AddCardActivity.this, BenefitsActivity.class);
//                intent.putExtra(getString(R.string.position), position);
//                intent.putExtra(getString(R.string.card_indicator), "true");
//                intent.putExtra(getString(R.string.CardName), cardList.get(position).getCardName());
//                intent.putExtra(getString(R.string.BenefitsImage), cardList.get(position).getBenefitsImg());
//                intent.putExtra(getString(R.string.benefits), cardList.get(position).getBenefitsDesc());
//                startActivity(intent);
//                finish();
//            }
//        }

    }

    private static ArrayList<Merchant> getMerchantList(ArrayList<String> list) {
        ArrayList<Merchant> merchantList = new ArrayList<>();

        for (String strCard : list) {
            ArrayList<String> cardDataList = CommonUtil.tokenize(strCard, QueryService.DELIMITER_FIELD);

                        /*	1. companyName
                            2. client
                            3. logoUrl
                        */
            if (cardDataList.size() >= 3) {

                Merchant merchant = new Merchant(cardDataList.get(0), cardDataList.get(1),
                        cardDataList.get(2));

                merchantList.add(merchant);
            }
        }
        return merchantList;
    }


}