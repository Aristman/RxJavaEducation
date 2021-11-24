import io.reactivex.rxjava3.core.Observable

fun main() {
    Observable.range(1, 5)
        .filter { it % 2 == 0 }
        .subscribe(System.out::println)
}