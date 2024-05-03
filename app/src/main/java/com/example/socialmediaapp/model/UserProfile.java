package com.example.socialmediaapp.model;

public class UserProfile {
    private String userName;
    private String emailMobileSocialId;
    private String password;
    private String profileName;
    private String GUID;
    private String loginType;

    private String mobileNo;

    public UserProfile() {
    }

    public UserProfile(String emailMobileSocialId, String password, String profileName, String GUID) {
        this.emailMobileSocialId = emailMobileSocialId;
        this.password = password;
        this.profileName = profileName;
        this.GUID = GUID;
    }

    public String getEmailMobileSocialId() {
        return emailMobileSocialId;
    }

    public void setEmailMobileSocialId(String emailMobileSocialId) {
        this.emailMobileSocialId = emailMobileSocialId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getMobileNo() {
        return mobileNo;
    }
}
