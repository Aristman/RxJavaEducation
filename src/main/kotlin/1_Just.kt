import io.reactivex.Observable

fun main() {
    Observable.just(1,2,3).subscribe(System.out::println)
}