package com.nitin.ekyc.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nitin.ekyc.KycApp;
import com.nitin.ekyc.R;
import com.nitin.ekyc.repository.local.CurrentUserHolder;
import com.nitin.ekyc.repository.local.LocalCache;
import com.nitin.ekyc.repository.pojo.Document;
import com.nitin.ekyc.repository.remote.EndPoints;
import com.nitin.ekyc.repository.remote.JSONRequest;
import com.nitin.ekyc.repository.remote.KEYS;
import com.nitin.ekyc.repository.remote.RequestTags;
import com.nitin.ekyc.utils.CommonUtils;
import com.nitin.ekyc.views.adapters.DocumentsRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin on 3/16/2017.
 */

public class DashBoardFragment extends Fragment {

    private static final String TAG = UserProfileFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private LocalCache localCacheInstance;

    private TabHost mTabHost;
    private RecyclerView mRvMyDocs;
    private RecyclerView mRvAllDocs;
    private SwipeRefreshLayout swrfAllDoc;
    private SwipeRefreshLayout swrfMyDoc;


    private View rootView;

    public static DashBoardFragment getInstance() {
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "llallala");
        dashBoardFragment.setArguments(bundle);
        return dashBoardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (localCacheInstance == null) {
            localCacheInstance = LocalCache.getInstance();
        }
        if (progressDialog == null) {
            progressDialog = CommonUtils.getProgressDialog(getContext());
        }
    }

    @Override
    public void onResume() {
        if (localCacheInstance == null) {
            localCacheInstance = LocalCache.getInstance();
        }
        if (progressDialog == null) {
            progressDialog = CommonUtils.getProgressDialog(getContext());
        }
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmeny_dash_board, container, false);


        mTabHost = (TabHost) rootView.findViewById(R.id.tab_host_dashboard);
        mTabHost.setup();

        mRvMyDocs = (RecyclerView) rootView.findViewById(R.id.rv_my_doc);
        mRvAllDocs = (RecyclerView) rootView.findViewById(R.id.rv_all_doc);

        swrfAllDoc = (SwipeRefreshLayout) rootView.findViewById(R.id.swrf_all_doc);
        swrfAllDoc.setOnRefreshListener(onRefreshListener);
        swrfMyDoc = (SwipeRefreshLayout) rootView.findViewById(R.id.swrf_my_doc);
        swrfMyDoc.setOnRefreshListener(onRefreshListener);

        setupAllDocsRecycler();
        setupMyDocsRecycler();
        setupTabHost();


        return rootView;
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            try {
                JSONObject payload = new JSONObject();
                payload.put(KEYS.USER_KYC_ID, CurrentUserHolder.getInstance().getKYC_ID());
                makeGetAllDocumentsRequest(payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void setupTabHost() {
        //Tab 1
        String tabOne = getResources().getString(R.string.title_my_documents);
        TabHost.TabSpec spec = mTabHost.newTabSpec(tabOne);
        spec.setContent(R.id.tab1);
        spec.setIndicator(tabOne);
        mTabHost.addTab(spec);

        //Tab 2
        String tabTwo = getResources().getString(R.string.title_all_doc);
        spec = mTabHost.newTabSpec(tabTwo);
        spec.setContent(R.id.tab2);
        spec.setIndicator(tabTwo);
        mTabHost.addTab(spec);
    }

    private void setupMyDocsRecycler() {
        mRvMyDocs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvMyDocs.setHasFixedSize(false);
        final List<Document> documents = localCacheInstance.getDocumentsList();
        List<Document> myDocs = new ArrayList<>();

        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);
            if (doc.getDocVerificationId() != null) {
                myDocs.add(doc);
            }
        }
        if (myDocs.size() > 0) {
            mRvMyDocs.setAdapter(new DocumentsRecyclerViewAdapter(getContext(), myDocs));
            mRvMyDocs.getAdapter().notifyDataSetChanged();
        }
        swrfMyDoc.setRefreshing(false);

    }

    private void setupAllDocsRecycler() {
        mRvAllDocs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvAllDocs.setHasFixedSize(false);
        List<Document> documents = localCacheInstance.getDocumentsList();

        if (documents.size() > 0) {
            mRvAllDocs.setAdapter(new DocumentsRecyclerViewAdapter(getContext(), documents, new OnListFragmentInteractionListener() {

                @Override
                public void onAddDocumentRequest(Document document) {
                    Log.e(TAG, "onAddDocumentRequest: " + document);
                    makeAddDocumentRequest(document);
                }

                @Override
                public void onListFragmentInteraction(Document document) {
                    Log.d(TAG, "onListFragmentInteraction: all docs rv--" + document);
                }
            }));
            mRvAllDocs.getAdapter().notifyDataSetChanged();

        }
        swrfAllDoc.setRefreshing(false);
    }

    private void makeAddDocumentRequest(Document document) {
        progressDialog.show();
        JSONObject payload = new JSONObject();
        try {
            payload.put(KEYS.USER_KYC_ID, CurrentUserHolder.getInstance().getKYC_ID());
            payload.put(KEYS.JSON_DOC_ID, document.getDocId());
        } catch (JSONException e) {
            e.printStackTrace();
            progressDialog.hide();

        }

        JSONRequest addDocRequest = new JSONRequest(
                Request.Method.POST,
                EndPoints.ADD_DOCUMENT_REQ_URL,
                payload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "addDocRequest  onResponse: " + response);

                        try {
                            JSONObject payload = new JSONObject();
                            payload.put(KEYS.USER_KYC_ID, CurrentUserHolder.getInstance().getKYC_ID());
                            makeGetAllDocumentsRequest(payload);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.hide();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error);
                        progressDialog.hide();
                    }
                },
                getFragmentManager(),
                null
        );
        addDocRequest.setRetryPolicy(KycApp.getDefaultRetryPolice());
        KycApp.getInstance().addToRequestQueue(addDocRequest, RequestTags.DASHBOARD_REQUESTS_TAG);
    }

    private void makeGetAllDocumentsRequest(final JSONObject payload) {
        progressDialog.show();
        progressDialog.setMessage("Retrieving Documents");
        JSONRequest getAllDocumentsRequest = new JSONRequest(
                Request.Method.POST,
                EndPoints.GET_ALL_DOCUMENTS_URL,
                payload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(TAG, "onResponse: getAllDocumentsRequest -->" + response);
                        try {
                            JSONArray jsonArray = response.getJSONArray(KEYS.GET_ALL_DOCUMENTS);
                            localCacheInstance.saveDocumentsFromJsonArray(jsonArray);
                            setupMyDocsRecycler();
                            setupAllDocsRecycler();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Log.d(TAG, "onErrorResponse:  getAllDocumentsRequest-->" + error);
                    }
                }
        );

        getAllDocumentsRequest.setTag(RequestTags.DASHBOARD_REQUESTS_TAG);
        getAllDocumentsRequest.setRetryPolicy(KycApp.getDefaultRetryPolice());
        KycApp.getInstance().getRequestQueue().add(getAllDocumentsRequest);

    }

    @Override
    public void onStop() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        KycApp.getInstance().cancelPendingRequests(RequestTags.DASHBOARD_REQUESTS_TAG);
        super.onStop();
    }

    public interface OnListFragmentInteractionListener {
        void onAddDocumentRequest(Document document);

        void onListFragmentInteraction(Document document);
    }


}
