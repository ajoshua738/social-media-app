package com.example.socialmediaapp.services;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

public class QueryService {
    public static final String DELIMITER_FIELD4 = "\\^";
    public static final String DELIMITER_FIELD3 = "#";
    public static final String DELIMITER_FIELD2 = "%";
    public static final String DELIMITER_FIELD = "`";
    public static final String DELIMITER_LIST = "\\|";
    public static final String DELIMITER_RESPONSE = ";";
    public static final String DELIMITER_GROUP = "*";

    public static final String COMMAND = "command";
    public static final String RESULT = "result";
    public static final String MESSAGE = "message";

    private static final String WSKEY_VALUE = "mobileApp";
    private static final String WSKEY_VALUE_N = "HAiico91xs2Z0jAILQzPNEmobile";
    private static final String WSKEY_VALUE_I = "cardsys";
    private static final int TIMEOUT = 30000;

    /*private static final String URLO = "https://www.pointzmatter.com/IVOVI_PRO_API/api/ReceiptUpload"; //HAi!CO live OCR receipt
    private static final String URL = "https://api.haiico.com/PMMemberWS/Service.asmx"; //HAi!CO live
    private static final String URL2 = "https://api.haiico.com/PMMemberWS/Service.asmx"; //HAi!CO live
    static final String URLI = "https://www.pointzmatter.com/PointzMatter_Ivovi/api/PM3/";  //HAi!CO liveRelease*/

    private static final String URLO = "https://apistaging.haiico.com/ReceiptUpload/api/ReceiptUpload"; //HAi!CO staging // ocr
    private static final String URLI = "https://apistaging.haiico.com/PointzMatter_Ivovi/api/PM3/"; //HAi!CO staging
    private static final String URL = "https://apistaging.haiico.com/PMMemberWSTestGift/Service.asmx"; //HAi!CO staging
    private static final String URL2 = "https://apistaging.haiico.com/PMMemberWSTestGift/Service.asmx"; //HAi!CO staging*/

    /*private static final String URLI = "http://175.143.103.241/PointzMatter_Ivovi/api/PM3/"; //HAi!CO staging
    private static final String URL = "http://175.143.103.241/PMMemberWSTestGift/Service.asmx";//HAi!CO staging
    private static final String URL2 = "http://175.143.103.241/PMMemberWSTestGift/Service.asmx";//HAi!CO staging*/

   /* private static final String URL = "https://www.pointzmatter.com/PMMEMBERWS/Service.asmx"; //live
    private static final String URL2 = "http://www.pointzmatter.com/PMMEMBERWS/Service.asmx"; //live
    static final String URLI = "https://www.pointzmatter.com/PointzMatter_Ivovi/api/PM3/";  //live*/

/*    private static final String URL = "http://10.1.20.107/PMMEMBERWS/Service.asmx";
	private static final String URL2 = "http://10.1.20.107/PMMEMBERWS/Service.asmx";*/

    /*private static final String URLI = "http://175.143.103.241/PointzMatter_Ivovi/api/PM3/"; //NEW Revamped Test 2021 July
    private static final String URL = "http://175.143.103.241/PMMEMBERWSTEST/Service.asmx";
    private static final String URL2 = "http://175.143.103.241/PMMEMBERWSTEST/Service.asmx";*/

    /*private static final String URLI = "https://apistaging.haiico.com/PointzMatter_Ivovi/api/PM3/"; //NEW Gift Test 2021 July
    private static final String URL = "https://apistaging.haiico.com/PMMemberWSTestGift/Service.asmx";
    private static final String URL2 = "https://apistaging.haiico.com/PMMemberWSTestGift/Service.asmx";*/


    /*static final String URLI = "https://www.pointzmatter.com/PointzMatter_Ivovi/api/PM3/"; //Live
    private static final String URL = "http://www.pointzmatter.com/PMMemberBetaNew/Service.asmx"; //LiveBeta
    private static final String URL2 = "http://www.pointzmatter.com/PMMemberBetaNew/Service.asmx"; //LiveBeta*/


    /*static final String URLI = "http://175.143.103.241/PointzMatter_Ivovi/api/PM3/";// test
    private static final String URL = "http://175.143.103.241/PMMEMBERWS/service.asmx";
    private static final String URL2 = "http://175.143.103.241/PMMEMBERWS/service.asmx";*/


    private static final String NAMESPACE = "http://tempuri.org/";

    public static final int QUERY_PROFILE_LOGIN = 1;
    public static final int QUERY_REGISTER_MOBILEID = 2;
    //    public static final int QUERY_PROFILE_REGISTER = 3; //not calling anymore (20-02-2023)
    public static final int QUERY_PROFILE_FORGOT_PWD = 4;
    public static final int QUERY_USERCARD_GETLIST = 5; //display user's card
    public static final int QUERY_CARD_GETLIST = 6; //display to add card
    //    public static final int QUERY_USERCARD_ADD = 7;
    public static final int QUERY_OUTLET_LIST = 8;
    public static final int QUERY_OUTLET_DETAILS = 9;
    public static final int QUERY_USERCARD_POINTINQUIRY = 10;
    public static final int QUERY_REDEMPTION = 11;
    //    public static final int QUERY_PROMOTION_BY_CATEGORY_NEW = 12;//not calling anymore (20-02-2023)
//    public static final int QUERY_PROMOTION_BY_LOCATION = 13;
    public static final int QUERY_GET_SINGLEPROMO = 14;
    public static final int QUERY_CARD_TRANSACTION_GET = 15;
    public static final int QUERY_CARD_REDEMPTION_GET = 16;
    public static final int QUERY_CHANGE_PIN = 17;
    //    public static final int QUERY_USERCARD_MEMBER_DETAILSGET = 18;
//    public static final int QUERY_USERCARD_MEMBER_DETAILSUPDATE = 19;
    public static final int QUERY_PROFILE_FEEDBACK = 20;
    public static final int QUERY_GETCARDTYPE_BY_CARDNO = 21;
    //    public static final int QUERY_USERCARD_DELETE = 22;//not calling anymore (20-02-2023)
    public static final int QUERY_GET_NOTIFICATION_SENT = 23;
    public static final int QUERY_CHANGE_PASSWORD = 24;
    public static final int QUERY_PROMO_LIST_GET = 25;
    public static final int QUERY_GET_VOUCHER_LIST_BY_CARD = 26;
    public static final int QUERY_GET_SINGLE_VOUCHER = 27;
    public static final int QUERY_GENERATE_VOUCHER_OTP = 28;
    public static final int QUERY_GET_VOUCHER_LOG = 29;
    //    public static final int QUERY_REWARD = 30;
//    public static final int QUERY_GET_TRANSACTION_LOG = 31;
    public static final int QUERY_SEND_REG_OTP = 32;
    public static final int QUERY_REG_OTP_CONFIRMATION = 33;
    public static final int QUERY_PLATFORM_UPDATE = 34;
    public static final int QUERY_GET_CATEGORIZE_MERCHANT_HQ = 35;
    public static final int QUERY_SEND_UPDATE_OTP = 36;
    public static final int QUERY_UPDATE_OTP_CONFIRMATION = 37;
    public static final int QUERY_USERCARD_ADD_P = 38;
    public static final int QUERY_USERCARD_ADD_V = 39;
    public static final int QUERY_PROFILE_GET_DETAILS = 40;
    public static final int QUERY_PROFILE_FORGET_PIN = 41;
    public static final int QUERY_GET_CARD_REQUEST_LIST = 42;
    public static final int QUERY_GET_MERCHANT_HQ_CONTACT = 43;
    public static final int QUERY_PROFILE_IS_NEW = 44;
    public static final int QUERY_GET_ADDITIONAL_INFO_BY_CARD = 45;
    public static final int QUERY_UPDATE_ADDITIONAL_INFO_BY_CARD = 46;
    public static final int QUERY_GET_RACE_LIST = 47;
    public static final int QUERY_GET_OCCUPATION_LIST = 48;
    public static final int QUERY_GET_STATE_LIST = 49;
    public static final int QUERY_GET_COUNTRY_LIST = 50;
    public static final int QUERY_GET_GIFT_LIST_BY_CARD_NO = 51;
    public static final int QUERY_REDEEM_GIFT = 52;
    public static final int QUERY_SWIPE_REDEEM_VOUCHER = 53;
    public static final int QUERY_END_SWIPE_VOUCHER = 54;
    public static final int QUERY_GET_REFERRAL_BY_CARD_TYPE = 55;
    public static final int QUERY_DELETE_ACCOUNT = 56;
    public static final int QUERY_REDEMPTION_PIN_CHECK = 57;
    public static final int QUERY_UPLOAD_NEW_RECEIPT = 58;
    public static final int QUERY_GET_RECEIPT_RESULT = 59;
    public static final int QUERY_GET_DISPLAY_NAME_BY_CLIENT = 60;
    public static final int QUERY_PROFILE_LOGIN_GET_OTP = 61;
    public static final int QUERY_PROFILE_LOGIN_BY_OTP = 62;
    public static final int QUERY_UPDATE_OTP_CONFIRMATION_2 = 63; // differentiate create pin page and update profile page fragment

    private static final String CLIENT_CODE_VALUE = "1";

    private static Context context;
    private static int module;
    private static ProgressDialog progressDialog;
    private static boolean displayProgress;
    private static boolean isRefresh;

    public static Bundle processRequest(Bundle b, Context currentContext){
        context = currentContext;
        int command = b.getInt(COMMAND, -1);

        Log.i(TAG, "" + command);

        try{
            switch (command){
                case QUERY_PROFILE_LOGIN:
                //profileLogin(b);
                requestWebService(b,ServiceConstant.ACTION_PROFILE_LOGIN);
                break;

                case QUERY_GET_CATEGORIZE_MERCHANT_HQ:
                    //getCategorizeMerchantHQ(b);
                     requestWebService(b,ServiceConstant.ACTION_GET_CATEGORIZE_MERCHANT_HQ);
                    break;
                case QUERY_PROFILE_IS_NEW:
                    //profileIsNew(b);
                    requestWebService(b,ServiceConstant.ACTION_PROFILE_IS_NEW);
                    break;
                case QUERY_REGISTER_MOBILEID:
                    //registerMobileID(b);
                    requestWebService(b,ServiceConstant.ACTION_REGISTER_MOBILE_ID);
                    break;
                case QUERY_CARD_GETLIST:
                    //cardGetList(b);
                    requestWebService(b,ServiceConstant.ACTION_CARD_GET_LIST);
                    break;
                case QUERY_PROFILE_GET_DETAILS:
                    //profileGetDetails(b);
                    requestWebService(b,ServiceConstant.ACTION_PROFILE_GET_DETAILS);
                    break;
                case QUERY_SEND_UPDATE_OTP:
                    //sendUpdateOTP(b);
                    requestWebService(b,ServiceConstant.ACTION_SEND_UPDATE_OTP);
                    break;
            }
        }catch (InterruptedIOException e) {
            //Log.e(TAG, "InterruptedIOException: " + e.getMessage());
            b.putString(QueryService.RESULT, "104");
            b.putString(QueryService.MESSAGE, "Connection timeout");
        } catch (IOException e) {
            //Log.e(TAG, "IOException: " + e.getMessage());
            b.putString(QueryService.RESULT, "101");
            b.putString(QueryService.MESSAGE, "Failed to Connect");
        } catch (XmlPullParserException e) {
            //Log.e(TAG, "XmlException: " + e.getMessage());
            b.putString(QueryService.RESULT, "102");
            b.putString(QueryService.MESSAGE, "Received incomplete data");
        } catch (Exception e) {
            //Log.e(TAG, "Exception: " + e.getMessage());
            b.putString(QueryService.MESSAGE, e.getMessage());
            b.putString(QueryService.RESULT, "120");
        }

        return b;
    }





    public static PropertyInfo createPropertyInfo(String name, Object value) {
        PropertyInfo pi = new PropertyInfo();
        pi.setName(name);
        pi.setValue(value);
        return pi;
    }

    public static String performSoap(String method,
                                     List<PropertyInfo> properties) throws IOException,
            XmlPullParserException {
        SoapObject request = new SoapObject(NAMESPACE, method);

        for (PropertyInfo prop : properties) {
            request.addProperty(prop);
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        SoapPrimitive result = null;
        try {
            HttpTransportSE androidHttpTransport;

            androidHttpTransport = new HttpTransportSE(URL, TIMEOUT);
            androidHttpTransport.call(NAMESPACE + method, envelope);
            result = (SoapPrimitive) envelope.getResponse();
        } catch (IOException e) {
            try {
                HttpTransportSE androidHttpTransport;

                androidHttpTransport = new HttpTransportSE(URL2, TIMEOUT);
                androidHttpTransport.call(NAMESPACE + method, envelope);
                result = (SoapPrimitive) envelope.getResponse();
            } catch (IOException a) {
                throw a;
            }

        }

        return result.toString();
    }

    public static Bundle requestWebService(Bundle b, String methodName) throws IOException,
            XmlPullParserException {

        ArrayList<PropertyInfo> pis = new ArrayList<PropertyInfo>();

        pis.add(createPropertyInfo(ServiceConstant.WSKEY, QueryService.WSKEY_VALUE_N));

        try {
            pis.add(createPropertyInfo(ServiceConstant.EN_DATA, MobileAppEncryption.EncryptWS(b.getString(ServiceConstant.EN_DATA))));
        } catch (Exception e) {

        }

        //    Log.e(TAG, methodName + pis);
        String result = performSoap(methodName, pis);

        //    Log.e(TAG, methodName + result);
        b.putString(QueryService.RESULT, result);

        //throw new InterruptedIOException();
        return b;
    }

}
