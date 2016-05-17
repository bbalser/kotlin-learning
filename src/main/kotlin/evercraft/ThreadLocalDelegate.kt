package evercraft

import kotlin.reflect.KProperty

class ThreadLocalDelegate<T>(private val threadLocal: ThreadLocal<T>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = threadLocal.get()

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        threadLocal.set(value)
    }

}

fun <T,R> ThreadLocal<T>.with(value: T, block: () -> R): R {
    this.set(value)
    val result = block()
    this.remove()
    return result
}
