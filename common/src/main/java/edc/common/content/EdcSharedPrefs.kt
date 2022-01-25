package edc.common.content

import android.content.SharedPreferences
import androidx.core.content.edit
import java.lang.IllegalArgumentException

class EdcSharedPrefs(val sharedPreferences: SharedPreferences) {

    inline fun <reified T> set(key: String, value: T) = sharedPreferences.setValueInline(key, value)

    inline fun <reified T> get(key: String) = sharedPreferences.getValueInline<T>(key)

    fun deleteAll() {
        sharedPreferences.edit().clear().commit()
    }

    inline fun <reified V> SharedPreferences.getValueInline(key: String): V? {
        return when (V::class) {
            String::class -> getValueCrossline(key) {
                getString(key, null)
            }
            Boolean::class -> getValueCrossline(key) {
                getBoolean(key, false)
            }
            Double::class -> getValueCrossline(key) {
                getFloat(key, 0f)
            }
            Float::class -> getValueCrossline(key) {
                getFloat(key, 0f)
            }
            Long::class -> getValueCrossline(key) {
                getLong(key, 0L)
            }
            else -> throw IllegalArgumentException("not supported type :${V::class.simpleName}")
        } as V?
    }

    inline fun <reified V> SharedPreferences.getValueCrossline(
        key: String,
        crossinline block: (SharedPreferences) -> V
    ): V? {
        return if (contains(key).not()) null
        else block(this)
    }

    inline fun <reified V> SharedPreferences.setValueInline(key: String, value: V) {
        edit {
            when (V::class) {
                String::class -> {
                    putString(key, value as String)
                }
                Boolean::class -> {
                    putBoolean(key, value as Boolean)
                }
                Double::class -> {
                    putFloat(key, value as Float)
                }
                Long::class -> {
                    putLong(key, value as Long)
                }
                else -> throw IllegalArgumentException("not supported type :${V::class.simpleName}")
            }
            commit()
        }
    }
}