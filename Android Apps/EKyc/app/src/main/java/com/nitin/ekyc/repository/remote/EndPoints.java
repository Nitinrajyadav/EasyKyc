package com.nitin.ekyc.repository.remote;

/**
 * Created by Nitin on 3/15/2017.
 */
public class EndPoints {

    /**
     * Base server url.
     */

//    public static final String BASE_URL = "http://192.168.0.101:8080/easykyc/api/";
//    public static final String BASE_URL = "http://test.barspan.com/easyKYC/api/";
    public static final String BASE_URL = "http://172.16.45.165:80/easyKYC/api/";


    public static final String SIGN_IN_URL = BASE_URL.concat("signup.php");
    public static final String GET_ALL_DOCUMENTS_URL = BASE_URL.concat("getDocument.php");
    public static final String ADD_DOCUMENT_REQ_URL = BASE_URL.concat("userRequest.php");
    public static final String GET_ALL_DEPARTMENTS_URL = BASE_URL.concat("getAuthList.php");


//    api userRequest.php    json -> KYCid + doc_id
//
//    http://localhost:8080/easykyc/api/getAuthList.php
//
//    {"authorities":[{"id":"1","name":"Transport Office Admin","status":"1"},{"id":"2","name":"Panchayat","status":"1"},{"id":"3","name":"ICICI Bank","status":"1"}]}


}
