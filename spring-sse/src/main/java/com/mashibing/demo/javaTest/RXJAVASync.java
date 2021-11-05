package com.mashibing.demo.javaTest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Deacription 同步，单线程 哪个线程产生就在哪个线程消费（观察者和被观察者在同一个线程）
 * @Author chenpengwei
 * @Date 2021/11/5 1:22 下午
 * @Version 1.0
 **/
public class RXJAVASync {

    public static void main(String[] args) {

        //被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            //emitter 发射体
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {

                // onNext可以 无限次调用
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onNext("4");
                emitter.onNext("5");

                emitter.onError(new Throwable("发生错误"));

                emitter.onComplete();

            }

        });

        //观察者
        Observer<String> observer = new Observer<String>(){
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe被执行");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext被执行");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError被执行");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete被执行");
            }
        };

        //被观察者订阅在观察者上
        observable.subscribe(observer);

    }

/*

    //方式二
    public static void main(String[] args) throws InterruptedException {

        //被观察者
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onNext("4");
                emitter.onNext("5");
            }
        })
                // 哪个线程是观察者
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe被执行" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("onNext被执行" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError被执行" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete被执行" + Thread.currentThread().getName());
                    }
                });

        Thread.sleep(1000);
    }
*/

}
