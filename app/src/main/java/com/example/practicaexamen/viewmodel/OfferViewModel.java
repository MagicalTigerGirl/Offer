package com.example.practicaexamen.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practicaexamen.data.adapter.OfferAdapter;
import com.example.practicaexamen.data.model.Offer;
import com.example.practicaexamen.data.model.OfferComparatorType;
import com.example.practicaexamen.data.model.OfferResult;
import com.example.practicaexamen.data.repository.OfferRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class OfferViewModel extends ViewModel {
    //SECONDFRAGMENT
    private StateLiveDataList<ArrayList<Offer>> liveDataList = new StateLiveDataList<>();

    public StateLiveDataList<ArrayList<Offer>> getLiveDataList() {
        return liveDataList;
    }


    public void getDataList(){
        liveDataList.setLoading();
        ArrayList<Offer> list = OfferRepository.getInstance().getListFiltred();
        list.clear();

        if (Boolean.TRUE.equals(swHome.getValue()))
            list.addAll(OfferRepository.getInstance().home());
        if (Boolean.TRUE.equals(swElectronic.getValue()))
            list.addAll(OfferRepository.getInstance().electronic());
        if (Boolean.TRUE.equals(swGames.getValue()))
            list.addAll(OfferRepository.getInstance().games());

        if (list.isEmpty())
            liveDataList.setNoData();
        else
            liveDataList.setSuccess(list);
    }

    public void orderByType() {
        Collections.sort(liveDataList.getValue().getData(), new OfferComparatorType());
        liveDataList.setOrderByType(liveDataList.getValue().getData());
    }

    //FIRSTFRAGMNET
    public MutableLiveData<Boolean> swHome = new MutableLiveData<>();
    public MutableLiveData<Boolean> swElectronic = new MutableLiveData<>();
    public MutableLiveData<Boolean> swGames = new MutableLiveData<>();

    // OFFERADDFRAGMENT

    private MutableLiveData<OfferResult> result;

    public MutableLiveData<OfferResult> getResult() {
        if (result == null)
            result = new MutableLiveData<>();
        return result;
    }

    public void add(Offer offer) {
        if (validateName(offer)) {
            if (OfferRepository.getInstance().check(offer)) {
                if (OfferRepository.getInstance().add(offer))
                    result.setValue(OfferResult.SUCCESS);
                else
                    result.setValue(OfferResult.FAILURE);
            } else
                result.setValue(OfferResult.NAMEDUPLICATE);
        } else
            result.setValue(OfferResult.NAMEEMPTY);
    }

    private boolean validateName(Offer offer) {
        if (TextUtils.isEmpty(offer.getName()))
            return false;
        return true;
    }
}
