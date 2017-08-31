package com.globant.equattrocchio.domain;

import android.support.annotation.NonNull;

import com.globant.equattrocchio.domain.models.Image;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<List<Image>, Void> {

    private ImagesServices imagesServices;

    public GetLatestImagesUseCase(@NonNull ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(@NonNull DisposableObserver<List<Image>> observer, Void aVoid) {
        imagesServices.getLatestImages(observer);
    }
}
