import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun httpRequest(url: String): Response =
    OkHttpClient.Builder()
        .build()
        .newCall(
            Request.Builder()
                .url(url = url)
                .build()
        )
        .execute()


fun main() {
    val subject = BehaviorSubject.create<String>()
    val observable =
        subject.flatMapSingle { url ->
            Single.fromCallable {
                httpRequest(url)
            }
        }

    observable
        .subscribe(
            { println(it.body?.string().orEmpty()) },
            { println("ERROR! -> ${it.message}") }
        )
    thread {
        subject.onNext("https://rickandmortyapi.com/api/episode/2")
        sleep(500L)
        subject.onNext("https://rickandmortyapi.com/api/episode/16")
        sleep(1000L)
        subject.onNext("https://rickandmortyapi.com/api/episode/8")
        subject.onNext("https://rickandmortyapi.com/api/episode/9")
        sleep(500L)
        subject.onNext("h://rickaapi.com/api/episode/2")
    }
}