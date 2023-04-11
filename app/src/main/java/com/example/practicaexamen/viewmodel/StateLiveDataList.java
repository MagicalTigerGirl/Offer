package com.example.practicaexamen.viewmodel;

import androidx.lifecycle.MutableLiveData;

public class StateLiveDataList<T> extends MutableLiveData<StateDataList<T>> {

    public void setLoading() {
        setValue(new StateDataList<T>().loading());
    }

    public void setNoData() {
        setValue(new StateDataList<T>().noData());
    }

    public void setSuccess(T data) {
        setValue(new StateDataList<T>().success(data));
    }
    public void setOrderByType(T data) {
        setValue(new StateDataList<T>().orderByType(data));
    }

    public void setComplete() {
        setValue(new StateDataList<T>().complete());
    }
}
