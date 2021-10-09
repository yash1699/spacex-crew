package com.yash.spacexcrew;

public class CrewMemberModel {

    private String mImageUrl;
    private String mName;
    private String mAgency;
    private String mStatus;
    private String mWikipediaUrl;

    public CrewMemberModel(String imageUrl, String name, String agency, String status, String wikipediaUrl) {
        mImageUrl = imageUrl;
        mName = name;
        mAgency = agency;
        mStatus = status;
        mWikipediaUrl = wikipediaUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getAgency() {
        return mAgency;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getWikipediaUrl() {
        return mWikipediaUrl;
    }

}
