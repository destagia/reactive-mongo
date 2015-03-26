package jp.kamasu.util

import akka.actor.{Actor, ActorLogging, Props}

class MessageActor extends Actor with ActorLogging {
    import MessageActor._

    def receive = {
        case Insert(id, age) =>
            log.info("Try Insert id : {}, age : {}", id, age)
        case Update(id, age) =>
            log.info("Try Update id : {}, age : {}", id, age)
    }

}

object MessageActor {
    val props = Props[MessageActor]
    case class Insert(id:String, age:Int)
    case class Update(id:String, age:Int)
}