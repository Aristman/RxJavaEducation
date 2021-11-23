import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request

private const val BASE_URL = "http://inshortsapi.vercel.app/news?category=all"

fun getAllNews(): Single<String> =
    Single.fromCallable {
        val okhttpClient = OkHttpClient().newBuilder()
            .build()
        val request = Request.Builder()
            .url(BASE_URL)
            .build()
        okhttpClient.newCall(request).execute().body?.string().orEmpty()
    }

fun main() {
    val dispose = getAllNews()
        .subscribe(
            { println("Successful -> $it") },
            { println("Error request > ${it.message}") }
        )
    println("the end")
}
