import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun main() {
    val subject = PublishSubject.create<String>()
    val observable =
        subject
            .map {
                OkHttpClient.Builder()
                    .build()
                    .newCall(Request.Builder().url(it).build()).execute()
            }
            .subscribeOn(Schedulers.io())
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