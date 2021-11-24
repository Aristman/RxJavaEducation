import io.reactivex.rxjava3.core.Observable

fun <T> repeatWithLog(input: T): T {
    println("Вызов функции с параметом: $input")
    return input
}

fun main() {
    println("Без подписки")
    Observable.just(repeatWithLog(listOf(1, 2, 3, 4, 5, 6)))
    Observable.fromCallable { repeatWithLog(listOf("trgs","gh","hghd","d","a")) }
    println("С подпиской")
    Observable.just(repeatWithLog(listOf(1, 2, 3, 4, 5, 6))).subscribe()
    Observable.fromCallable { repeatWithLog(listOf("trgs","gh","hghd","d","a")) }.subscribe()
}