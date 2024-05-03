package com.example.socialmediaapp.rest;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestDataService {
    public static final String QUERY_FOR_USER_NAME = "https://jsonplaceholder.typicode.com/users";
    public static final String QUERY_FOR_USERS = "https://jsonplaceholder.typicode.com/users";
    String name = "";


    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String name);
    }


    public interface VRLGetUsers {
        void onError(String message);

        void onResponse(List<RestUser> users);
    }

    public void getName(int index, Context context, VolleyResponseListener volleyResponseListener){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, QUERY_FOR_USER_NAME, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject userName = response.getJSONObject(index);
                            name = userName.getString("name");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        Toast.makeText(context, "Name = "+name, Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(name);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT).show();
                Log.e("VolleyError", error.getMessage());
                volleyResponseListener.onError("Error");

            }
        });

        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);


        //return name;

    }


    public void getUsers(Context context, VRLGetUsers vrlGetUsers) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, QUERY_FOR_USERS, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<RestUser> users = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject userObject = response.getJSONObject(i);
                                int id = userObject.getInt("id");
                                String name = userObject.getString("name");
                                String username = userObject.getString("username");
                                String email = userObject.getString("email");
                                String phone = userObject.getString("phone");
                                String website = userObject.getString("website");

                                JSONObject addressObject = userObject.getJSONObject("address");
                                String street = addressObject.getString("street");
                                String suite = addressObject.getString("suite");
                                String city = addressObject.getString("city");
                                String zipcode = addressObject.getString("zipcode");

                                JSONObject geoObject = addressObject.getJSONObject("geo");
                                String lat = geoObject.getString("lat");
                                String lng = geoObject.getString("lng");

                                RestUser.Geo geo = new RestUser.Geo(lat, lng);
                                RestUser.Address address = new RestUser.Address(street, suite, city, zipcode, geo);

                                JSONObject companyObject = userObject.getJSONObject("company");
                                String companyName = companyObject.getString("name");
                                String catchPhrase = companyObject.getString("catchPhrase");
                                String bs = companyObject.getString("bs");

                                RestUser.Company company = new RestUser.Company(companyName, catchPhrase, bs);

                                RestUser restUser = new RestUser(id, name, username, email, address, phone, website, company);
                                users.add(restUser);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        vrlGetUsers.onResponse(users);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vrlGetUsers.onError("Error");
            }
        });

        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }


}
