package com.nova.project_fredy

import com.nova.project_fredy.FullLoadFiles._
import org.apache.spark.sql.types.StructType
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.sql.SparkSession

import java.nio.file.{FileSystems, Files}

// deprecated in scala 2.13
// use import scala.jdk.CollectionConverters._
import scala.collection.JavaConverters._
//import scala.jdk.CollectionConverters._

object Utils {

  def initializeLOG(LogLevel: Level): Logger = {
    // LOGS
    LogManager.getRootLogger.setLevel(Level.FATAL)
    // Spark log
    val LOG = LogManager.getLogger(getClass.getName)
    LOG.setLevel(LogLevel)

    LOG
  }

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

  def read_prop_file(prop_file: String)(implicit spark: SparkSession): Map[String,String] = {
    val path = new Path(prop_file)
    println(s"prop_file: $prop_file")
    val sc=spark.sparkContext
    val exists_prop = FileSystem
      .get(sc.hadoopConfiguration)
      .exists(path)

    if (!exists_prop){
      println("Properties file does not exists\n")
      sys.exit(1)
    }

    // reading and filtering the properties file
    val prop_lst = sc.textFile(path.toString).collect().toSeq
      .filter(!_.endsWith("="))

    if (prop_lst.isEmpty) {
      println("Properties file have no admisible values or is empty\n")
      sys.exit(1)
    }

    val map: Map[String,String] = prop_lst.map(
      x => {
        x.split("=")(0).trim() -> x.substring(x.split("=")(0).length + 1).trim()
      }
    ).toMap

    map
  }
}
