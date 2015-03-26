package jp.kamasu.util

case class Account(id:String, age:Int)

import reactivemongo.bson.{BSONDocument}

trait InsertStatus
case class Inserted() extends InsertStatus
case class Duplecated() extends InsertStatus
case class Error(status:String) extends InsertStatus


