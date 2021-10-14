import io.reactivex.rxjava3.core.Observable

fun main() {
    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map { it * 2 }
        .map { "value=$it" }
        .subscribe(System.out::println)
}