import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main() {
    val psOne = PublishSubject.create<Int>()
    val psTwo = PublishSubject.create<Int>()

    val observable = Observable.combineLatest(psOne, psTwo) { one, two ->
        "sum $one+$two = ${one + two}"
    }
        .subscribe { println(it) }

    thread {
        (1..5).map {
            psOne.onNext(it)
            sleep(1000L)
        }
    }

    thread {
        for (i in (10..50) step 10) {
            psTwo.onNext(i)
            sleep(500L)
        }
    }
}