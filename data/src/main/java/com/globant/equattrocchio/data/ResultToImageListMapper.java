package com.globant.equattrocchio.data;

import android.support.annotation.NonNull;

import com.globant.equattrocchio.data.response.ImageResponse;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.models.Image;

import java.util.ArrayList;
import java.util.List;

public class ResultToImageListMapper {
    @NonNull
    public static List<Image> map(@NonNull Result result) {
        List<Image> imageList = new ArrayList<>();

        if (result.getImages() != null) {
            for (ImageResponse img : result.getImages()) {
                Image dImg = new Image();
                dImg.setId(img.getId());
                dImg.setLargeUrl(img.getLargeUrl());
                dImg.setUrl(img.getUrl());

                imageList.add(dImg);
            }
        }

        return imageList;
    }
}
