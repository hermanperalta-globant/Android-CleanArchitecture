package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.models.Image;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;


    public ImagesPresenter(@NonNull ImagesView view, @NonNull GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    public void onCountButtonPressed(String text) {
        view.showText(text);//todo: aca va el string que me devuelva el execute del usecase
    }

    private void onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(new DisposableObserver<List<Image>>() {
            @Override
            public void onNext(@NonNull List<Image> imageList) {

                if (imageList != null) {
                    onCountButtonPressed(formatImageListToString(imageList));
                }

                loadFromPreferences();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {

            }
        }, null);
        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    private String formatImageListToString(@NonNull List<Image> imageList) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\"images\": [");
        if (imageList != null) {

            for (Image img : imageList) {
                sb.append(String.format("\"id\"=%d, \"url\"=\"%s\", \"largeUrl\"=\"%s\"", img.getId(), img.getUrl(), img.getLargeUrl()));
            }
        }
        sb.append("]}");

        return sb.toString();
    }

    private void loadFromPreferences() {
        // view.showText("EL TEXTO QUE ME TRAGIA DE LAS PREFERENCES");// todo: traerme el texto de las preferences
    }

    public void register() {
        Activity activity = view.getActivity();
        if (activity != null) {
            RxBus.subscribe(activity, new CallServiceButtonObserver() {
                @Override
                public void onEvent(CallServiceButtonPressed event) {
                    onCallServiceButtonPressed();
                }
            });
        }
    }

    public void unregister() {
        Activity activity = view.getActivity();
        if (activity != null) {
            RxBus.clear(activity);
        }
    }
}
