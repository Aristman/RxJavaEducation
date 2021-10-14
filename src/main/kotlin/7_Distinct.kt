import io.reactivex.rxjava3.core.Observable


fun main() {
    Observable.fromArray(1, 2, 3, 4, 4, 5, 5, 6, 77, 6, 5, 4, 7, 3, 2, 1)
        .distinct()
        .map { " $it" }
        .subscribe(System.out::print)
}