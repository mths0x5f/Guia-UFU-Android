/*
 *  Created on base of this tutorial:
 *  http://developer.android.com/guide/topics/resources/runtime-changes.html#RetainingAnObject
 *  Licensed under Apache License 2.0, as the Official Android Documentation
 *
 */

package io.github.mths0x5f.guiaufu.util;

import android.app.Fragment;
import android.os.Bundle;

/**
 * This class makes preserving object instances on configuration change easy
 * @param <T> Generic type of the object
 */
public class DataFragment<T> extends Fragment {

    private T data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(T data) { this.data = data; }

    public T getData() { return data; }

}
