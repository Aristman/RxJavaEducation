import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlin.random.Random

fun main() {
    val o1 = Observable
        .fromIterable((0..20).map { Random.nextInt(100) })
        .doOnSubscribe { println("Start") }
        .doOnNext { print("$it ") }
        .doOnComplete {
            println()
            println("complete")
        }

    o1
        .doOnSubscribe { println("first element") }
        .first(-1)
        .subscribe(Consumer {
            println(it)
        })
    o1
        .doOnSubscribe { println("last element") }
        .last(-1)
        .subscribe(Consumer {
            println(it)
        })
}