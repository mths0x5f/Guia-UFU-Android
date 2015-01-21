package io.github.mths0x5f.guiaufu.api;

import retrofit.RestAdapter;


public class UFUInfoAPIClient {

    private static UFUInfoAPIInterface api;
    private static String baseUrl = "http://192.168.0.123/guiaufu"; // TODO Change on final build

    public static UFUInfoAPIInterface get() {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(baseUrl).build();
        api = restAdapter.create(UFUInfoAPIInterface.class);
        return api;

    }

}
