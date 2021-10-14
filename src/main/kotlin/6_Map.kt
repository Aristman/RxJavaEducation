import io.reactivex.rxjava3.core.Observable

fun main() {
    Observable.just((1..12).map { it })
        .map { items ->
            items.map { it * 2 }
        }
        .map { items ->
            items.map { "value=$it" }
        }
        .subscribe(System.out::println)
}