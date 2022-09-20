package com.miel3k.masteringandroidpaging3.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by jmielczarek on 20/09/2022
 */
fun <T> Fragment.lifecycleBinding(
    finish: ((binding: T) -> Unit)? = null,
    initialise: () -> T
): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        private var binding: T? = null
        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            this@lifecycleBinding
                .viewLifecycleOwnerLiveData
                .observe(this@lifecycleBinding) { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)
                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            binding?.let { finish?.invoke(it) }
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
            binding ?: initialise().also {
                binding = it
            }
    }