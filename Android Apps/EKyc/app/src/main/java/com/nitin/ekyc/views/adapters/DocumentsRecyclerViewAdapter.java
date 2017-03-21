package com.nitin.ekyc.views.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitin.ekyc.R;
import com.nitin.ekyc.repository.local.LocalCache;
import com.nitin.ekyc.repository.pojo.Document;
import com.nitin.ekyc.repository.remote.KEYS;
import com.nitin.ekyc.views.custom.tagView.Chip;
import com.nitin.ekyc.views.custom.tagView.ChipsContainer;
import com.nitin.ekyc.views.fragments.DashBoardFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DocumentsRecyclerViewAdapter extends RecyclerView.Adapter<DocumentsRecyclerViewAdapter.ViewHolder> {

    private List<Document> mValues;
    private OnListFragmentInteractionListener mListener;
    private LocalCache localCacheInstance;
    private Context context;

    public DocumentsRecyclerViewAdapter(Context context, List<Document> items, OnListFragmentInteractionListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
        localCacheInstance = LocalCache.getInstance();
    }

    public DocumentsRecyclerViewAdapter(Context context, List<Document> items) {
        this.context = context;
        mValues = items;
        localCacheInstance = LocalCache.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        boolean canAddDoc = true;
        holder.fabAddDoc.setOnClickListener(null);
        holder.chipsContainer.removeAll();
        holder.chipsContainer.setVisibility(View.GONE);
        holder.chipsContainer.setVisibility(View.VISIBLE);

        holder.mItem = mValues.get(position);
        holder.tvDocName.setText(holder.mItem.getName());
        if (holder.mItem.getDepartmentId() != null) {
            String authName = localCacheInstance.getDepartmentssArrayMap().get(holder.mItem.getDepartmentId());
            holder.tvDepartmentName.setText(authName);
        }

        holder.tvDocId.setText(holder.mItem.getDocId());

        if (holder.mItem.getDocVerificationId() != null && !TextUtils.isEmpty(holder.mItem.getDocVerificationId())) {
            ((View) holder.tvMyDoctId.getParent()).setVisibility(View.VISIBLE);
            holder.tvMyDoctId.setText(holder.mItem.getDocVerificationId());
        } else {
            ((View) holder.tvMyDoctId.getParent()).setVisibility(View.GONE);
        }
        HashSet<String> supportDocuments = holder.mItem.getSupportDocuments();
        if (supportDocuments != null && supportDocuments.size() > 0) {
            ((View) holder.chipsContainer.getParent()).setVisibility(View.VISIBLE);
            String[] arr = supportDocuments.toArray(new String[supportDocuments.size()]);
            ArrayList<Chip> chips = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                Document doc = localCacheInstance.getDocumentsArrayMap().get(arr[i]);
                if (doc != null) {
                    Chip chip = new Chip(doc.getName());
                    if (doc.getDocVerificationId() != null && !TextUtils.isEmpty(doc.getDocVerificationId())) {
                        chip.layoutColor = ContextCompat.getColor(context, R.color.white);
                        chip.tagTextColor = ContextCompat.getColor(context, R.color.colorAccent);
                    } else {
                        chip.layoutColor = ContextCompat.getColor(context, R.color.white);
                        chip.tagTextColor = ContextCompat.getColor(context, R.color.colorDanger);
                        canAddDoc = false;
                    }
                    chips.add(chip);
                }
            }
            holder.chipsContainer.addChips(chips);
        } else {
            ((View) holder.chipsContainer.getParent()).setVisibility(View.GONE);
        }

        ((View) holder.fabAddDoc.getParent()).setVisibility(View.VISIBLE);
        if (holder.mItem.getDocStatusFlag() > 0) {
            switch (holder.mItem.getDocStatusFlag()) {
                case KEYS.DOC_STATUS_PENDING: {
                    holder.fabAddDoc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_stat_done));
                    holder.tvAction.setText("Go To Center For Verification");
                }
                break;
                case KEYS.DOC_STATUS_APPROVED: {
                    holder.fabAddDoc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_stat_done_all));
                    holder.tvAction.setText("Verified");
                }
                break;
                case KEYS.DOC_STATUS_CANCELED: {
                    holder.fabAddDoc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_not_interested));
                    holder.tvAction.setText("Not Verified");
                }
                break;
            }
        } else if (canAddDoc && mListener != null) {
            holder.fabAddDoc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add));
            holder.tvAction.setText("Request");
            holder.fabAddDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onAddDocumentRequest(holder.mItem);
                }
            });
        } else {
            ((View) holder.fabAddDoc.getParent()).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDocName;
        public final TextView tvDepartmentName;
        public final TextView tvDocId;
        public final TextView tvMyDoctId;
        public ChipsContainer chipsContainer;
        public FloatingActionButton fabAddDoc;
        public TextView tvAction;

        public Document mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDocName = (TextView) view.findViewById(R.id.tv_doc_name);
            tvDepartmentName = (TextView) view.findViewById(R.id.tv_department_name);
            tvDocId = (TextView) view.findViewById(R.id.tv_doc_id);
            tvMyDoctId = (TextView) view.findViewById(R.id.tv_doc_verification_id);
            chipsContainer = (ChipsContainer) view.findViewById(R.id.chips_container);
            fabAddDoc = (FloatingActionButton) view.findViewById(R.id.fav_add_document);
            tvAction = (TextView) view.findViewById(R.id.tv_action);
        }
    }
}
