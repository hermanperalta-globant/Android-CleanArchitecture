package com.globant.equattrocchio.domain.service;

import android.support.annotation.NonNull;

import com.globant.equattrocchio.domain.models.Image;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(@NonNull Observer<List<Image>> observer);
}
