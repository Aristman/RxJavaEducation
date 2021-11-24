import io.reactivex.Observable

fun main() {
    Observable.just("a1", "a2", "a3")
        .mergeWith(Observable.just("b1", "b2"))
        .subscribe(System.out::println)
}