package com.globant.equattrocchio.data.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("images")
    @Expose
    private List<ImageResponse> mImageResponses = null;

    @Nullable
    public List<ImageResponse> getImages() {
        return mImageResponses;
    }

    public void setImages(@Nullable List<ImageResponse> imageResponses) {
        this.mImageResponses = imageResponses;
    }

}