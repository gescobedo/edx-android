package com.galileo.gustavo.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.galileo.gustavo.tipcalc.R;
import com.galileo.gustavo.tipcalc.activities.TipDetailActivity;
import com.galileo.gustavo.tipcalc.adapters.OnItemClickListener;
import com.galileo.gustavo.tipcalc.adapters.TipAdapter;
import com.galileo.gustavo.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener,OnItemClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private TipAdapter adapter;
    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return  view;

    }
    public void initAdapter(){
    if(adapter==null){
        adapter=new TipAdapter(getActivity().getApplicationContext(),this);
    }
    }
    public void initRecyclerView(){
recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void addToList(TipRecord record) {
    adapter.add(record);
    }

    @Override
    public void clearList() {
    adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord record) {
        //Toast.makeText(getActivity(),record.getFormattedDate(),Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY,record.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY,record.getFormattedDate());
        intent.putExtra(TipDetailActivity.TIP_KEY,record.getTip());
        startActivity(intent);
    }
}
