package com.outerspace.rxchaneque;

import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.intervalRange;

public class BasicRxObservableObserver {
    TextView text;
    StringBuilder sb;

    public BasicRxObservableObserver(TextView text) {
        this.text = text;
        sb = new StringBuilder();
    }

    public void justDoIt() {
        Observable<Long> observable = observableIntegerInstance();
        Observer<Long> observer = observerIntegerInstance();

        // the observable and te observer get to work once the subscriptioin is done!
        observable.subscribe(observer);
    }

    public void justCountIt() {
        Observable<Long> observable = Observable.intervalRange(0, 10, 0, 200, TimeUnit.MILLISECONDS);
        Observer<Long> observer = observerIntegerInstance();

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(observer);
    }

    private Observable<Long> observableIntegerInstance() {
        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {
                for(long i = 1; i<=10; i++)
                    e.onNext(i);

                e.onComplete();
            }
        });
    }

    private Observer<Long> observerIntegerInstance() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                sb.append("onSubscribe").append('\n');
                text.setText(sb.toString());
            }

            @Override
            public void onNext(@NonNull Long value) {
                sb.append("onNext - " + value).append('\n');
                text.setText(sb.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                sb.append("onError").append('\n');
                text.setText(sb.toString());
            }

            @Override
            public void onComplete() {
                sb.append("onComplete").append('\n');
                text.setText(sb.toString());
            }
        };
    }


}
