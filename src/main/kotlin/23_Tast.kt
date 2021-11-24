import io.reactivex.Observable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import model.CharactersNW
import okhttp3.OkHttpClient
import okhttp3.Request

private const val REQUEST_URL = "https://rickandmortyapi.com/api/character"

private val json = Json { ignoreUnknownKeys = true }
private fun request(url: String) = Request.Builder().url(url).build()

fun getData(): Observable<String> =
    Observable
        .create { emitter ->
            val okHttpClient = OkHttpClient.Builder().build()
            json.decodeFromString<CharactersNW>(
                okHttpClient.newCall(request(REQUEST_URL)).execute().body?.string().orEmpty()
            ).results.map { character ->
                val url = character.location.url
                emitter.onNext(
                    OkHttpClient
                        .Builder()
                        .build()
                        .newCall(request(url))
                        .execute().body?.string().orEmpty()
                )
            }
        }

fun main() {
    getData()
        .subscribe(
            { println("Successful -> $it") },
            { println("ERROR -> ${it.message}") } // Если не обрабатывать ошибку то при ошибке крашится по OnErrorNotImplementedException
        )
}