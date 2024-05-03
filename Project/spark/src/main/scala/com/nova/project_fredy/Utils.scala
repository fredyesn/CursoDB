package com.nova.project_fredy

import org.apache.spark.sql.types.StructType
import com.nova.project_fredy.Files._

import java.nio.file.{FileSystems, Files}
import scala.jdk.CollectionConverters.IteratorHasAsScala

object Utils {
  def list_files(dir: String): Seq[String] = {
    val dir_ok = FileSystems.getDefault.getPath(dir)
    val dir_list = Files.list(dir_ok).iterator().asScala.map(
        x => x.getFileName.toString
      )
      .filter(_.endsWith(".csv")).toSeq

    //dir_list.foreach(println)

    dir_list
  }

  def GetSchema(file: String): StructType = {
    file match {
      case Actor.file => Actor.schema
      case Address.file => Address.schema
      case Category.file => Category.schema
      case City.file => City.schema
      case Country.file => Country.schema
      case Customer.file => Customer.schema
      case Film.file => Film.schema
      case FilmActor.file => FilmActor.schema
      case FilmCategory.file => FilmCategory.schema
      case Inventory.file => Inventory.schema
      case Languaje.file => Languaje.schema
      case Payment.file => Payment.schema
      case Rental.file => Rental.schema
      case Staff.file => Staff.schema
      case Store.file => Store.schema
    }
  }
}
