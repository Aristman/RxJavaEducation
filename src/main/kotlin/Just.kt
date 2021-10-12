import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Just : Observable<Int>() {
    override fun subscribeActual(observer: Observer<in Int>) {
        (1..3).map { observer.onNext(it) }
        observer.onComplete()
    }
}

fun main() {
    val justItems = Just()
    // Вариант 1
    justItems.subscribe(System.out::println)
    println()
    // Вариант 2
    Just().subscribeWith(object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {}

        override fun onNext(t: Int) {
            println(t)
            Thread.sleep(1000L)
        }

        override fun onError(e: Throwable) {}

        override fun onComplete() {}
    })


}