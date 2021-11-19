import io.reactivex.Observable

fun <T> repeatWithLog(input: T): T {
    println("Вызов функции с параметом: $input")
    return input
}

fun main() {
    println("Без подписки")
    Observable.fromIterable((0..7).map { repeatWithLog(it) })
    Observable.fromCallable { listOf("trgs","gh","hghd","d","a").map { repeatWithLog(it) } }
    println("С подпиской")
    Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6).map { repeatWithLog(it) }).subscribe()
    Observable.fromCallable { listOf("trgs","gh","hghd","d","a").map { repeatWithLog(it) } }.subscribe()
}