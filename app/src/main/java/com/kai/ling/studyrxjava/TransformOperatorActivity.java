package com.kai.ling.studyrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 变换操作符
 */

public class TransformOperatorActivity extends AppCompatActivity {

    private static String TAG = "RxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);

//        map();
//        flatMap();
//        concatMap();
        buffer();
    }

private void buffer() {
    Observable.just(1, 2, 3, 4, 5)
            .buffer(3, 2)
            // 设置缓存区大小 & 步长
            // 缓存区大小 = 每次从被观察者中获取的事件数量
            // 步长 = 每隔步长个事件，去一次
            .subscribe(new Observer<List<Integer>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<Integer> integers) {
                    Log.d(TAG, " 缓存区里的事件数量 = " + integers.size());
                    for (Integer value : integers) {
                        Log.d(TAG, " 事件 = " + value);
                    }
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

    private void concatMap() {
        // 采用RxJava基于事件流的链式操作
        Observable.just(1, 2, 3)
                // 采用concatMap（）变换操作符
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < 2; i++) {
                            list.add("我是事件:" + integer + "--下的子事件:" + i);
                            // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送2个String事件
                            // 最终合并，再发送给被观察者
                        }
                        return Observable.fromIterable(list);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }

    private void flatMap() {
        // 采用RxJava基于事件流的链式操作
        Observable.just(1, 2, 3)
                // 采用flatMap（）变换操作符
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < 2; i++) {
                            list.add("我是事件:" + integer + "--下的子事件:" + i);
                            // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送2个String事件
                            // 最终合并，再发送给被观察者
                        }
                        return Observable.fromIterable(list);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }

    private void map() {
        // 采用RxJava基于事件流的链式操作
        // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
        Observable.just(1, 2, 3)
                // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "字符串类型" + String.valueOf(integer);
                    }
                })
                .subscribe(new Consumer<String>() {
                    // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }
}
