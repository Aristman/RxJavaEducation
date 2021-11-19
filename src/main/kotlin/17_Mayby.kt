import io.reactivex.Maybe
import kotlin.random.Random

fun main() {


    val maybeObservable = Maybe.create<String> { emitter ->
        when (Random.nextInt(3)) {
            0 -> {
                emitter.onSuccess("Value")
            }
            1 -> {
                emitter.onError(Throwable("my ERROR"))
            }
            2 -> {
                emitter.onComplete()
            }
        }
    }

    (0..10).map {
        maybeObservable.subscribe(
            { println("Successful -> $it")},
            { println("Error ->${it.message}")},
            { println("Complete")}
        )
    }
}