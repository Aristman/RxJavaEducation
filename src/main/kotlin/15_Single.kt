import io.reactivex.Observable

fun main() {
    Observable
        .just("one")
        .single("non")
        .subscribe(
            { println(it) },
            {}
        )

    Observable
        .create<String> { emitter ->
            emitter.onNext("one")
            emitter.onError(Throwable("My error"))
            emitter.onNext("two")
            emitter.onComplete()
        }
        .singleElement()
        .subscribe(
            { println(it)},
            { println(it.message)}
        )
}