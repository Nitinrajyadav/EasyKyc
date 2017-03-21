package com.nitin.ekyc.repository.pojo;

import java.util.HashSet;

/**
 * Created by Nitin on 3/16/2017.
 */

public class Document {

    private String docId;
    private String name;
    private String departmentId;
    private HashSet<String> supportDocuments;
    private String docVerificationId;
    private int docStatusFlag;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public HashSet<String> getSupportDocuments() {
        return supportDocuments;
    }

    public void addSupportDocument(String supportDocumentId) {
        if (this.supportDocuments == null) {
            this.supportDocuments = new HashSet<>();
        }
        this.supportDocuments.add(supportDocumentId);
    }

    public String getDocVerificationId() {
        return docVerificationId;
    }

    public void setDocVerificationId(String docVerificationId) {
        this.docVerificationId = docVerificationId;
    }

    public int getDocStatusFlag() {
        return docStatusFlag;
    }

    public void setDocStatusFlag(int docStatusFlag) {
        this.docStatusFlag = docStatusFlag;
    }
}


