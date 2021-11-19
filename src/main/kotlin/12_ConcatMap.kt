import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import kotlin.random.Random

data class SomeObject(
    val id: Int
)

fun dbRequest(): Single<SomeObject> =
    Single.just(SomeObject(1))

fun networkResponse(id: Int): Completable =
    if (Random.nextBoolean()) Completable.complete() else Completable.error(Throwable())


fun main() {
    (1..20).map {step ->
        dbRequest().flatMapCompletable {
            networkResponse(it.id)
        }
            .doOnSubscribe { print("$step -> ") }
            .subscribe(
            { println("успех") },
            { println("неудача") }
        )
    }

    //Сравнение concatMap и flatMap
    println("concatMap")
    Observable.range(1, 15)
        .concatMap { item ->
            Observable.just(item, item).delay(10, TimeUnit.MILLISECONDS)
        }
        .subscribe { print("$it ") }
    Thread.sleep(1000L)
    println()
    println("flatMap")
    Observable.range(1, 15)
        .flatMap { item ->
            Observable.just(item, item).delay(10, TimeUnit.MILLISECONDS)
        }
        .subscribe { print("$it ") }
    Thread.sleep(1000L)
}