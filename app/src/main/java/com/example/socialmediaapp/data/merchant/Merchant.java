package com.example.socialmediaapp.data.merchant;

import com.orm.SugarRecord;

public class Merchant extends SugarRecord {
    private String companyName;
    private String client;
    private String logoUrl;

    public Merchant() {
    }

    public Merchant(String companyName, String client, String logoUrl) {
        this.companyName = companyName;
        this.client = client;
        this.logoUrl = logoUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
