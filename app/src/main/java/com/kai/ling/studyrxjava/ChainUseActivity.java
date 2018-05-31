package com.kai.ling.studyrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by kai.wang
 * on 2018/5/31 0031.
 */

public class ChainUseActivity extends AppCompatActivity {

    private static String TAG = ChainUseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);
        chainCall1();
        chainCall2();
    }

private void chainCall1() {
    // RxJava的链式操作
    // 1. 创建被观察者 & 生产事件
    Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }
    }).subscribe(new Observer<Integer>() {
        // 2. 通过通过订阅（subscribe）连接观察者和被观察者
        // 3. 创建观察者 & 定义响应事件的行为
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "开始采用subscribe连接");
        }

        @Override
        public void onNext(Integer integer) {
            Log.d(TAG, "对Next事件" + integer + "作出响应");
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
    //注：整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
}

    private void chainCall2() {
        Observable.just("hello").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

}
