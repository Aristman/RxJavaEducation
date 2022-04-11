import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main() {
    val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
    val dispose = getAllNews()
        .subscribe(
            {
                println(json.decodeFromString<NewsNW>(it))
            },
            { println("Error request > ${it.message}") }
        )
    println("the end")
}