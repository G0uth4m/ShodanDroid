package com.g0uth4m.shodanapp;

public class ListItem {
    private String mISP;
    private String mPort;
    private String mCity;
    private String mCountry;
    private String mIP;
    private String mOrg;
    private String mData;

    public ListItem(String isp, String port, String city, String country, String ip, String org, String data){
        mISP = isp;
        mPort = port;
        mCity = city;
        mCountry = country;
        mIP = ip;
        mOrg = org;
        mData = data;
    }

    public String getISP(){
        return mISP;
    }

    public String getPort(){
        return mPort;
    }

    public String getCity(){
        return mCity;
    }

    public String getCountry(){
        return mCountry;
    }

    public String getIP(){
        return mIP;
    }

    public String getOrg(){
        return mOrg;
    }

    public String getData(){
        return mData;
    }
}
