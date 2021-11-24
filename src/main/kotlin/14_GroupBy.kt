import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function

fun main() {
    val list = listOf(
        "создать",
        "Observable",
        "из",
        "нескольких",
        "элементов",
        "помощью",
        "Добавлю",
        "не",
        "да",
        "возвращает"
    )
    val observable = Observable.fromIterable(list)
    observable
        .groupBy { it.length }
        .flatMapSingle { it.toList() }
        .subscribe{ println(it)}
}