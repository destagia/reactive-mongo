package jp.kamasu.util

// import play.api.libs.iteratee.Iteratee
import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.core.commands._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object MongoDB {
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost"))
    val db = connection.db("reactivemongo")
    val collection = db.collection("user")

    def findOne (id:String):Future[Option[BSONDocument]] = {
        val bson = BSONDocument("id" -> id)
        collection.find(bson).cursor[BSONDocument].collect[List]().map {
            _ match {
                case Nil => None
                case xs => Some(xs.head)
            }
        }
    }

    def insert (id:String, age:Int):Future[InsertStatus] = {
        findOne(id) flatMap ( _ match {
            case Some(bson) =>
                Future(Duplecated())
            case None =>
                val bson = BSONDocument("id" -> id, "age" -> age)
                collection.insert(bson) map { lastError =>
                    if (lastError.ok) {
                        Inserted()
                    } else {
                        Error(lastError.toString())
                    }
                }

        })
    }

    def update (id:String, age:Int) = {

    }

    def remove (id:String) = {

    }

}