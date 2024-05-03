package com.example.socialmediaapp.data.card;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class Card extends SugarRecord implements Parcelable {

    private String cardNo;
    private String cardStatus;//added on DB VERSION 12
    private String memberName;
    private String owner;
    private String clientCode;
    private String companyName;
    private String cardTypeId;
    private String cardName;
    private String remark;
    private String backgroundUrl;
    private String logoUrl;
    private String imageUrl;
    private String cardBase;
    private String balPoint;
    private String balAmount;
    private String benefitsDesc;
    private String benefitsImg;
    private String termsCondition;
    private String enableReceipt;
    private String ownedCard; //true = own the card, false = dont have the card
    private String authorize; //true = need to wait for merchant approval, false = directly add
    private String referral;
    private String availability;
    private String requiredIC;
    private String requiredAddress;
    private String requiredState;
    private String requiredCountry;
    private String requiredGender;
    private String requiredRace;
    private String requiredDesignation;
    private String requiredMaritalStatus;
    private String additionalInfoIndicator;
    private String redeemType;//added on DB VERSION 11

    public Card(String cardNo, String memberName, String owner, String clientCode, String companyName, String cardTypeId, String cardName, String remark, String backgroundUrl, String logoUrl, String imageUrl, String cardBase, String balPoint, String balAmount, String enableReceipt) {
        this.cardNo = cardNo;
        this.memberName = memberName;
        this.owner = owner;
        this.clientCode = clientCode;
        this.companyName = companyName;
        this.cardTypeId = cardTypeId;
        this.cardName = cardName;
        this.remark = remark;
        this.backgroundUrl = backgroundUrl;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.cardBase = cardBase;
        this.balPoint = balPoint;
        this.balAmount = balAmount;
        this.enableReceipt = enableReceipt;
    }

    public Card(String cardNo, String cardStatus, String memberName, String owner, String clientCode, String companyName, String cardTypeId, String cardName, String remark,
                String backgroundUrl, String logoUrl, String imageUrl, String cardBase, String balPoint, String balAmount, String enableReceipt,
                String referral, String additionalInfoIndicator, String redeemType) {
        this.cardNo = cardNo;
        this.cardStatus = cardStatus;
        this.memberName = memberName;
        this.owner = owner;
        this.clientCode = clientCode;
        this.companyName = companyName;
        this.cardTypeId = cardTypeId;
        this.cardName = cardName;
        this.remark = remark;
        this.backgroundUrl = backgroundUrl;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.cardBase = cardBase;
        this.balPoint = balPoint;
        this.balAmount = balAmount;
        this.enableReceipt = enableReceipt;
        this.referral = referral;
        this.additionalInfoIndicator = additionalInfoIndicator;
        this.redeemType = redeemType;
    }

    //for add card latest
    public Card(String clientCode, String cardTypeId, String cardName, String backgroundUrl, String logoUrl, String imageUrl, String cardBase, String benefitsDesc,
                String benefitsImg, String termsCondition, String ownedCard, String authorize, String referral, String availability, String requiredIC, String requiredAddress,
                String requiredState, String requiredCountry, String requiredGender, String requiredRace, String requiredDesignation, String requiredMaritalStatus) {
        this.clientCode = clientCode;
        this.cardTypeId = cardTypeId;
        this.cardName = cardName;
        this.backgroundUrl = backgroundUrl;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.cardBase = cardBase;
        this.benefitsDesc = benefitsDesc;
        this.benefitsImg = benefitsImg;
        this.termsCondition = termsCondition;
        this.ownedCard = ownedCard;
        this.authorize = authorize;
        this.referral = referral;
        this.availability = availability;
        this.requiredIC = requiredIC;
        this.requiredAddress = requiredAddress;
        this.requiredState = requiredState;
        this.requiredCountry = requiredCountry;
        this.requiredGender = requiredGender;
        this.requiredRace = requiredRace;
        this.requiredDesignation = requiredDesignation;
        this.requiredMaritalStatus = requiredMaritalStatus;
    }

    public Card() {
    }


    protected Card(Parcel in) {
        cardNo = in.readString();
        cardStatus = in.readString();
        memberName = in.readString();
        owner = in.readString();
        clientCode = in.readString();
        companyName = in.readString();
        cardTypeId = in.readString();
        cardName = in.readString();
        remark = in.readString();
        backgroundUrl = in.readString();
        logoUrl = in.readString();
        imageUrl = in.readString();
        cardBase = in.readString();
        balPoint = in.readString();
        balAmount = in.readString();
        benefitsDesc = in.readString();
        benefitsImg = in.readString();
        termsCondition = in.readString();
        enableReceipt = in.readString();
        ownedCard = in.readString();
        authorize = in.readString();
        referral = in.readString();
        availability = in.readString();
        requiredIC = in.readString();
        requiredAddress = in.readString();
        requiredState = in.readString();
        requiredCountry = in.readString();
        requiredGender = in.readString();
        requiredRace = in.readString();
        requiredDesignation = in.readString();
        requiredMaritalStatus = in.readString();
        additionalInfoIndicator = in.readString();
        redeemType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardNo);
        dest.writeString(cardStatus);
        dest.writeString(memberName);
        dest.writeString(owner);
        dest.writeString(clientCode);
        dest.writeString(companyName);
        dest.writeString(cardTypeId);
        dest.writeString(cardName);
        dest.writeString(remark);
        dest.writeString(backgroundUrl);
        dest.writeString(logoUrl);
        dest.writeString(imageUrl);
        dest.writeString(cardBase);
        dest.writeString(balPoint);
        dest.writeString(balAmount);
        dest.writeString(benefitsDesc);
        dest.writeString(benefitsImg);
        dest.writeString(termsCondition);
        dest.writeString(enableReceipt);
        dest.writeString(ownedCard);
        dest.writeString(authorize);
        dest.writeString(referral);
        dest.writeString(availability);
        dest.writeString(requiredIC);
        dest.writeString(requiredAddress);
        dest.writeString(requiredState);
        dest.writeString(requiredCountry);
        dest.writeString(requiredGender);
        dest.writeString(requiredRace);
        dest.writeString(requiredDesignation);
        dest.writeString(requiredMaritalStatus);
        dest.writeString(additionalInfoIndicator);
        dest.writeString(redeemType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardBase() {
        return cardBase;
    }

    public void setCardBase(String cardBase) {
        this.cardBase = cardBase;
    }

    public String getBalPoint() {
        return balPoint;
    }

    public void setBalPoint(String balPoint) {
        this.balPoint = balPoint;
    }

    public String getBalAmount() {
        return balAmount;
    }

    public void setBalAmount(String balAmount) {
        this.balAmount = balAmount;
    }

    public String getBenefitsDesc() {
        return benefitsDesc;
    }

    public void setBenefitsDesc(String benefitsDesc) {
        this.benefitsDesc = benefitsDesc;
    }

    public String getBenefitsImg() {
        return benefitsImg;
    }

    public void setBenefitsImg(String benefitsImg) {
        this.benefitsImg = benefitsImg;
    }

    public String getTermsCondition() {
        return termsCondition;
    }

    public void setTermsCondition(String termsCondition) {
        this.termsCondition = termsCondition;
    }

    public String getEnableReceipt() {
        return enableReceipt;
    }

    public void setEnableReceipt(String enableReceipt) {
        this.enableReceipt = enableReceipt;
    }

    public String getOwnedCard() {
        return ownedCard;
    }

    public void setOwnedCard(String ownedCard) {
        this.ownedCard = ownedCard;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRequiredIC() {
        return requiredIC;
    }

    public void setRequiredIC(String requiredIC) {
        this.requiredIC = requiredIC;
    }

    public String getRequiredAddress() {
        return requiredAddress;
    }

    public void setRequiredAddress(String requiredAddress) {
        this.requiredAddress = requiredAddress;
    }

    public String getRequiredState() {
        return requiredState;
    }

    public void setRequiredState(String requiredState) {
        this.requiredState = requiredState;
    }

    public String getRequiredCountry() {
        return requiredCountry;
    }

    public void setRequiredCountry(String requiredCountry) {
        this.requiredCountry = requiredCountry;
    }

    public String getRequiredGender() {
        return requiredGender;
    }

    public void setRequiredGender(String requiredGender) {
        this.requiredGender = requiredGender;
    }

    public String getRequiredRace() {
        return requiredRace;
    }

    public void setRequiredRace(String requiredRace) {
        this.requiredRace = requiredRace;
    }

    public String getRequiredDesignation() {
        return requiredDesignation;
    }

    public void setRequiredDesignation(String requiredDesignation) {
        this.requiredDesignation = requiredDesignation;
    }

    public String getRequiredMaritalStatus() {
        return requiredMaritalStatus;
    }

    public void setRequiredMaritalStatus(String requiredMaritalStatus) {
        this.requiredMaritalStatus = requiredMaritalStatus;
    }

    public String getAdditionalInfoIndicator() {
        return additionalInfoIndicator;
    }

    public void setAdditionalInfoIndicator(String additionalInfoIndicator) {
        this.additionalInfoIndicator = additionalInfoIndicator;
    }

    public String getRedeemType() {
        return redeemType;
    }

    public void setRedeemType(String redeemType) {
        this.redeemType = redeemType;
    }
}
