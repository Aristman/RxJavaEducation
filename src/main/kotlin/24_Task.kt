import io.reactivex.subjects.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

val asyncSubject = AsyncSubject.create<Int>()
val behaviorSubject = BehaviorSubject.createDefault(-1)
val publishSubject = PublishSubject.create<Int>()
val replaySubject = ReplaySubject.create<Int>()

fun subscriber(name: String, subject: Subject<Int>, delay: Long? = null) {
    thread {
        delay?.let { sleep(it) }
        subject.subscribe { println("$name subscriber --> $it") }
    }
}

fun main() {
//    jobAsyncSubject()
//    jobBehaviorSubject()
//    jobPublishSubject()
    jobReplaySubject()
}

fun jobReplaySubject() {
    thread {
        (100..1000 step (87)).map {
            replaySubject.onNext(it)
            sleep(it.toLong())
        }
    }
    subscriber("replay first", replaySubject)
    subscriber("replay second", replaySubject, delay = 1300L)
}

fun jobPublishSubject() {
    thread {
        (100..1000 step (87)).map {
            publishSubject.onNext(it)
            sleep(it.toLong())
        }
    }
    subscriber("publish first", publishSubject)
    subscriber("publish second", publishSubject, delay = 1000L)
}

fun jobBehaviorSubject() {
    thread {
        sleep(500L)
        (20..70 step (10)).map {
            behaviorSubject.onNext(it)
            sleep(Random.nextLong(300L))
        }
        behaviorSubject.onComplete()
    }
    subscriber("behavior first", behaviorSubject)
    subscriber("behavior second", behaviorSubject, delay = 1000L)
}

private fun jobAsyncSubject() {
    thread {
        (0..10).map {
            asyncSubject.onNext(it)
            sleep(Random.nextLong(500L))
        }
        asyncSubject.onComplete()
    }
    subscriber("async first", asyncSubject)
    subscriber("async second", asyncSubject, delay = 1000L)
    subscriber("async third", asyncSubject, delay = 3000L)
}