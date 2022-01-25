package edc.common.bundle

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty

inline fun <reified T> Fragment.argumentDelegate(): ReadOnlyProperty<Fragment, T?> =
    argumentDelegate { arguments!! }

inline fun <F, reified T> argumentDelegate(
    crossinline provideArguments: (F) -> Bundle
): ReadOnlyProperty<F, T> =
    ReadOnlyProperty { thisRef, property ->
        val bundle = provideArguments(thisRef)
        bundle.get(property.name) as T
    }

inline fun <reified T> Activity.argumentDelegate(): ReadOnlyProperty<Activity, T> =
    argumentDelegate { it.intent?.extras!! }

inline fun <reified T> Fragment.arg(): ReadOnlyProperty<Fragment, T> =
    argumentDelegate { it.requireArguments() }
