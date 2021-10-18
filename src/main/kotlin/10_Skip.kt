import io.reactivex.Observable

fun main() {
    Observable.just(9, 8, 7, 6, 5)
        .skip(1)
        .subscribe { println(it) }
}