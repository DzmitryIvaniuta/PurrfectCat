package com.example.purrfectcat.utils.viewBinding.activityViewBinding

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingProperty<T : ViewBinding>(
    private val inflater: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T> {

    private var viewBinding: T? = null

    @MainThread
    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (viewBinding == null) {
            viewBinding = inflater(thisRef.layoutInflater)
            thisRef.setContentView(viewBinding?.root)
        }

        return viewBinding ?: throw IllegalStateException("ViewBinding not initialized")
    }
}

inline fun <reified T : ViewBinding> viewBinding(
    noinline inflate: (LayoutInflater) -> T
): ReadOnlyProperty<AppCompatActivity, T> {
    return ActivityViewBindingProperty(inflate)
}