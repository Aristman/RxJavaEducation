import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main() {
    val o1 = Observable.range(1, 7)
    val o2 = o1
        .startWith(111)
        .delay(500, TimeUnit.MILLISECONDS)
    o2.subscribe { println(it) }
    Thread.sleep(1000L)
}