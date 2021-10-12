import io.reactivex.rxjava3.core.Observable

fun main() {
    val a = listOf(1, 2, 3)
    val b = listOf(5, 6, 7)
    Observable.just(a)
        .zipWith(Observable.just(b)) { t1, t2 ->
            t1.zip(t2).map { it.first+it.second }
        }
        .subscribe(System.out::println)
}