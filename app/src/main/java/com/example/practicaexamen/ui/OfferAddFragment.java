package com.example.practicaexamen.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.practicaexamen.R;
import com.example.practicaexamen.data.adapter.TypeOfferAdapter;
import com.example.practicaexamen.data.model.Offer;
import com.example.practicaexamen.data.model.OfferResult;
import com.example.practicaexamen.data.model.TypeOffer;
import com.example.practicaexamen.data.repository.OfferRepository;
import com.example.practicaexamen.data.repository.TypeOfferRepository;
import com.example.practicaexamen.databinding.FragmentOfferAddBinding;
import com.example.practicaexamen.viewmodel.OfferViewModel;


public class OfferAddFragment extends Fragment {

    private FragmentOfferAddBinding binding;
    private TypeOfferAdapter adapter;
    private TypeOffer typeOfferSelected = TypeOfferRepository.getInstance().getList().get(0);
    private OfferViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOfferAddBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSpinner();
        initViewModel();

        binding.btnAddOffer.setOnClickListener(v -> viewModel.add(new Offer(typeOfferSelected.getType(), binding.tilAddOfferName.getEditText().getText().toString())));
    }

    private void initSpinner() {
        adapter = new TypeOfferAdapter(getContext(), R.layout.item_offer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spTypeOffer.setAdapter(adapter);
        binding.spTypeOffer.setSelection(0, false);
        binding.spTypeOffer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeOfferSelected = (TypeOffer) parent.getSelectedItem();
                Toast.makeText(getContext(), "Has seleccionado "+typeOfferSelected.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<OfferResult>() {
            @Override
            public void onChanged(OfferResult offerResult) {
                switch (offerResult) {
                    case SUCCESS:
                        NavHostFragment.findNavController(getParentFragment()).navigateUp();
                        break;
                }
            }
        });
    }
}