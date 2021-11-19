import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import kotlin.random.Random

fun main() {

    val observer = object : CompletableObserver{
        override fun onError(e: Throwable) {
            println("Ошибка ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {}

        override fun onComplete() {
            println("Operation Complete")
        }
    }

    val completable1 = Completable.create { emitter ->
        if (Random.nextBoolean()) {
            emitter.onComplete()
        } else {
            emitter.onError(Throwable("My error"))
        }
    }

    (0..100).map {
        completable1.subscribe(observer)
    }

    println("------------------------")
    Completable
        .fromAction { error("myError") }
        .subscribe(observer)
}