package com.example.codehero.utils;

import android.view.View;

/**
 * Created by minimac2 on 06/06/17.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
