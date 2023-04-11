package com.example.practicaexamen.viewmodel;

public class StateDataList<T> {
    public enum DataState{
        CREATED,
        LOADING,
        NODATA,
        SUCCESS,
        ORDER_BY_TYPE,
        COMPLETE
    }

    private DataState state;
    private T data;

    public void StateDataList() {
        this.state = DataState.CREATED;
        this.data = null;
    }

    public StateDataList<T> loading() {
        this.state = DataState.LOADING;
        this.data = null;
        return this;
    }

    public StateDataList<T> noData() {
        this.state = DataState.NODATA;
        this.data = null;
        return this;
    }

    public StateDataList<T> success(T data) {
        this.state = DataState.SUCCESS;
        this.data = data;
        return this;
    }

    public StateDataList<T> orderByType(T data) {
        this.state = DataState.ORDER_BY_TYPE;
        this.data = data;
        return this;
    }

    public StateDataList<T> complete() {
        this.state = DataState.COMPLETE;
        return this;
    }

    public DataState getState() {
        return state;
    }

    public T getData() {
        return data;
    }
}
