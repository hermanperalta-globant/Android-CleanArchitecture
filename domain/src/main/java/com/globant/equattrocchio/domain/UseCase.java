package com.globant.equattrocchio.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T, Params> {

    private final CompositeDisposable disposables;

    UseCase() {
        this.disposables = new CompositeDisposable();
    }

    abstract void buildUseCaseObservable(@Nullable DisposableObserver<T> observer, @Nullable Params params);

    public void execute(@NonNull DisposableObserver<T> observer, @Nullable Params params) {
        Preconditions.checkNotNull(observer);
        this.buildUseCaseObservable(observer, params);
        addDisposable(observer);
    }

    public void execute(@Nullable Params params) {
        this.buildUseCaseObservable(null, params);
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(@NonNull Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
