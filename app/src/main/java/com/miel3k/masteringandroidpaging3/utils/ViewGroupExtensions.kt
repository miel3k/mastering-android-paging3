package com.miel3k.masteringandroidpaging3.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by jmielczarek on 20/09/2022
 */
fun ViewGroup.inflate(
    @LayoutRes viewId: Int,
    root: ViewGroup = this,
    attachRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(viewId, root, attachRoot)