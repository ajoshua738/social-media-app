package com.example.socialmediaapp.helper;

public interface Constant {

    String ANDROID = "android";
    String HUAWEI = "Huawei";
    String login_type_normal = "0";
    String login_type_third_party = "1";
    String trx_type_point = "1";
    String trx_type_cash = "2";
    String redeem_type_all = "0";
    String redeem_type_point = "1";
    String redeem_type_amount = "2";
    String promo_all = "00";
    String promo_f_and_b = "01";
    String promo_fashion = "02";
    String promo_health = "03";
    String promo_service = "04";
    String promo_electronic = "05";
    String promo_retail = "06";
    String promo_travel = "07";
    String promo_perfume = "08";
    String promo_wellness = "09";
    String promo_others = "99";

    String PREPAID_TRX = "0";
    String POINT_TRX = "1";
    String OTHERS_TRX = "2";
    String POINT_CASH_TAB = "POINT_CASH_TAB";
    String TRX_TAB_ONE = "TRX_TAB_ONE";
    String TRX_TAB_TWO = "TRX_TAB_TWO";

    String default_latitude = "3.155771";
    String default_longitude = "101.714516";
    String default_radius = "0";
    String CARD_BASE_POINT_GIFT_CARD = "0";
    String CARD_BASE_POINT_CARD = "1";
    String CARD_BASE_GIFT_CARD = "2";
    String MOBILE_PIN = "123";
    String MOBILE_PIN_2 = "781729";
    String LANGUAGE_ENGLISH = "English";
    String LANGUAGE_MALAY = "Bahasa Malaysia";
    String LANGUAGE_CHINESE = "Chinese";
    String TRX_TYPE_REWARD_TOP_UP = "1";
    String TRX_TYPE_POINT_CASH_REDEMPTION = "2";
    String TRX_TYPE_VOUCHER = "3";
    String TRX_TYPE_RECEIPT = "4";
    String REQ_TYPE_PENDING = "1";
    String REQ_TYPE_REJECTED = "2";
    String REQ_TYPE_APPROVED = "3";
    String default_device_date = "2000-01-01 00:00:00";
    String noti_type_promotion = "Promotion";
    String noti_type_message = "Message";
    String noti_type_voucher = "Voucher";
    String noti_type_card_request = "CardRequest";
    String pointz_matter_url = "https://haiico.com/";
    String faq_url = "https://haiico.com/faqs/";
    String terms_url = "https://haiico.com/terms-and-conditions/?user";
    String privacy_policy = "https://haiico.com/privacy-policy/";
    String app_download = "https://download.haiico.com/memberapp/";
    String apple_manually_revoke_token = "https://support.apple.com/en-us/HT210426";
    String OWNER_TRUE = "1";
    String OWNER_FALSE = "0";
    String ADD_CARD = "1";
    String DELETE_CARD = "2";
    String REFRESH_CARD = "3";
    String RECOVER_CARD = "4";
    String PHOTO_SOURCE_CAMERA = "Camera";
    String PHOTO_SOURCE_GALLERY = "Gallery";
    String status_pending = "P";
    String status_rejected = "R";
    String status_approved = "A";
    //    String status_appeal = "L";
    //String status_returned = "B"; //for future reupload uses purposes
    String status_review = "V"; // reviewing status ori verifying
    String status_reward_failed = "F"; // ai no problem reward got problem

    String KEY_CARD_WIDTH = "card width";
    String KEY_CARD_HEIGHT = "card height";
    int GOOGLE_LOGIN_REQUEST_CODE = 123; //random, as long as not clashing with activity request codes
    int NORMAL_LOGIN = 1;
    int GOOGLE_LOGIN = 2;
    int APPLE_LOGIN = 3;
    int FACEBOOK_LOGIN = 4;
    String NO_APPROVAL = "0";
    String NEED_APPROVAL = "1";
    String CARD_RECOVERY = "2";
    int login_id_type = 5;
    String mode_sign_up = "Sign Up";
    String mode_login = "Login";
    String merchant_name = "merchantName";

    String IN_STORE = "1";
    String ONLINE = "2";
    String LOGIN_TYPE_MOBILE = "1";
    String LOGIN_TYPE_EMAIL = "2";

    //Apple Sign In output parameters
    String APP_CLIENT_SECRET = "AppClientSecret";
    String APPLE_AUTHORIZATION_CODE = "AppleAuthCode";
}
