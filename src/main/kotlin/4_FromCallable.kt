import io.reactivex.rxjava3.core.Observable

class FromCallable() {
    val observable1: Observable<Int> = Observable.just(1, 2, 3, 4, 5, 6)
    val observable2: Observable<String> = Observable.fromCallable { "hello" }
//    observable1.
}

fun main() {

}