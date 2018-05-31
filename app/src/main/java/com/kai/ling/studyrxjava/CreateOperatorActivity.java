package com.kai.ling.studyrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by kai.wang
 * on 2018/5/31 0031.
 */

public class CreateOperatorActivity extends AppCompatActivity {

    private static String TAG = "RxJava";

    private List<String> dataList;
    private Integer i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);

//        initDara();
//        traverse();
        defer();
//        timer();

    }

    // 该例子 = 延迟2s后，发送一个long类型数值
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

    }

    private void defer() {
        //1. 第1次对i赋值
        i = 10;

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {

            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        //2. 第2次对i赋值
        i = 15;

        //3. 观察者开始订阅
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        defer.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到的整数是" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });

    }

    private void initDara() {
        dataList = new ArrayList<>();
        dataList.add("123");
        dataList.add("456");
        dataList.add("789");
        dataList.add("101112");
    }

    private void traverse() {
        Observable.fromIterable(dataList).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "集合遍历");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "集合中的数据元素 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "遍历结束");
            }
        });
    }
}
