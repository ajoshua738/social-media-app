package com.example.socialmediaapp.services;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.socialmediaapp.helper.CommonUtil;

import java.util.ArrayList;

public class DataResult implements Parcelable {
    public static final Parcelable.Creator<DataResult> CREATOR = new Parcelable.Creator<DataResult>() {
        @Override
        public DataResult createFromParcel(Parcel in) {
            return new DataResult(in);
        }

        @Override
        public DataResult[] newArray(int size) {
            return new DataResult[size];
        }
    };

    public static DataResult createRaw(String result) {
        DataResult dataResult = new DataResult();

        Log.d("Who called tokenize","createRaw");
        ArrayList<String> strResponseList = CommonUtil.tokenize(result,
                QueryService.DELIMITER_RESPONSE);

        dataResult = new DataResult();
        try {
            dataResult.resultCode = Integer.parseInt(strResponseList.get(0));
        } catch (NumberFormatException ex) {

        }


        if (strResponseList.size() > 1) {
            int i = 1;
            if (strResponseList.size() == 3) {
                try {
                    dataResult.totalItems = Integer
                            .parseInt(strResponseList.get(i));
                } catch (NumberFormatException ex) {

                }

                i++;
            }

            ArrayList<String> strDataList = new ArrayList<>();
            String[] grpDataList = null;

            if (CallWebServices.module == QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ) {
                grpDataList = strResponseList.get(i).split("\\*");
                if (grpDataList.length > 1) { //for getting merchant cards
                    for (int x = 0; x < grpDataList.length; x++) {
                        dataResult.groupValues.add(grpDataList[x]);
                    }
                }
            } else {
                strDataList = CommonUtil.tokenize(
                        strResponseList.get(i), QueryService.DELIMITER_LIST);
                dataResult.values = strDataList;
            }

        }

        return dataResult;
    }

    public static DataResult create(String result) {
        DataResult dataResult = new DataResult();
        //00;encrypted_data or 00

        Log.d("Who called tokenize","create line 77");
        ArrayList<String> strResponseList = CommonUtil.tokenize(result,
                QueryService.DELIMITER_RESPONSE);

        dataResult = new DataResult();
        try {
            dataResult.resultCode = Integer.parseInt(strResponseList.get(0));
        } catch (NumberFormatException ex) {

        }

        String decryptedString = "";
        if (strResponseList.size() > 1) {

            try {
                decryptedString = MobileAppEncryption.DecryptWS(strResponseList.get(1));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        String response_decrypted_data = strResponseList.get(0) + ";" + decryptedString;

        Log.d("Who called tokenize","create line 101");
        strResponseList = CommonUtil.tokenize(response_decrypted_data,
                QueryService.DELIMITER_RESPONSE);


        if (strResponseList.size() > 1) {
            int i = 1;
            if (strResponseList.size() == 3) {
                try {
                    dataResult.totalItems = Integer
                            .parseInt(strResponseList.get(i));
                } catch (NumberFormatException ex) {

                }

                i++;
            }

            ArrayList<String> strDataList = new ArrayList<>();
            String[] grpDataList = null;

            if (CallWebServices.module == QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ) {
                grpDataList = strResponseList.get(i).split("\\*");
                if (grpDataList.length > 1) { //for getting merchant cards
                    for (int x = 0; x < grpDataList.length; x++) {
                        dataResult.groupValues.add(grpDataList[x]);
                    }
                }
            } else {
                Log.d("Who called tokenize","create line 130");
                strDataList = CommonUtil.tokenize(
                        strResponseList.get(i), QueryService.DELIMITER_LIST);
                dataResult.values = strDataList;
            }

        }

        return dataResult;
    }

    public int resultCode = -1;

    public int totalItems = -1;

    public ArrayList<String> values = null;
    public ArrayList<String> groupValues = new ArrayList<>();

    public DataResult() {

    }

    private DataResult(Parcel in) {
        resultCode = in.readInt();
        totalItems = in.readInt();
        in.readStringList(values);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resultCode);
        dest.writeInt(totalItems);
        dest.writeStringList(values);
    }
}

