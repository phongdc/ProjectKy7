package com.example.phongdc.peaapp.service;

public interface CallBackData<T> {
    void onSuccess(T t);

    void onFail(String message);
}
