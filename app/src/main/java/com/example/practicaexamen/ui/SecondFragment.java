package com.example.practicaexamen.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaexamen.R;
import com.example.practicaexamen.data.adapter.OfferAdapter;
import com.example.practicaexamen.data.model.Offer;
import com.example.practicaexamen.databinding.FragmentSecondBinding;
import com.example.practicaexamen.viewmodel.OfferViewModel;
import com.example.practicaexamen.viewmodel.StateDataList;

import java.util.ArrayList;

public class SecondFragment extends Fragment implements OfferAdapter.OnManageOfferListener {

    private FragmentSecondBinding binding;
    private OfferAdapter adapter;
    private OfferViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_orderbytype:
                viewModel.orderByType();
                return true;
        }
        return false;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initViewModel();

        binding.fabAdd.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_offerAddFragment));
    }

    private void initRecyclerView() {
        adapter = new OfferAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        binding.rvOffer.setAdapter(adapter);
        binding.rvOffer.setLayoutManager(layoutManager);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(OfferViewModel.class);

        viewModel.getLiveDataList().observe(getViewLifecycleOwner(), new Observer<StateDataList<ArrayList<Offer>>>() {
            @Override
            public void onChanged(StateDataList<ArrayList<Offer>> arrayListStateDataList) {
                switch (arrayListStateDataList.getState()){
                    case SUCCESS:
                        adapter.update(arrayListStateDataList.getData());
                        break;
                    case ORDER_BY_TYPE:
                        adapter.orderBy(arrayListStateDataList.getData());
                        break;
                }
            }
        });

        viewModel.getDataList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onEditOffer(Offer offer) {
        Toast.makeText(getContext(), "Has seleccionado "+ offer.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteOffer(Offer offer) {

    }
}