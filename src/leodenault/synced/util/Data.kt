package leodenault.synced.util

import kotlin.reflect.KProperty

interface Data<T> : () -> T {
  val value: T

  override fun invoke(): T = value

  operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value
}

interface MutableData<T> : Data<T> {
  override var value: T

  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.value = value
  }
}

fun <T> mutableDataOf(initialValue: T): MutableData<T> = object : MutableData<T> {
  override var value = initialValue


}
