package com.nova.project_fredy

import com.nova.project_fredy.FullLoadFiles._
import com.nova.project_fredy.Main.LOG
import com.nova.project_fredy.Variables.full_load_obj_lst
import org.apache.spark.sql.types.{StringType, StructField, StructType, TimestampType}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SaveMode, SparkSession}

import java.nio.file.{FileSystems, Files}
import java.text.SimpleDateFormat

// deprecated in scala 2.13
// use import scala.jdk.CollectionConverters._
import scala.collection.JavaConverters._

object Utils {
  def findTimeStamp(): String = new SimpleDateFormat("yyyyMMddHHmmss").toString

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

    println(s"Files read from directory $dir: \n")
    dir_list.foreach(println)

    dir_list
  }

  def getFullLoadObj(file: String): File = {
    var obj: File = NullFile

    for (elem <- full_load_obj_lst)
      if (elem.file == file )
        obj = elem

    obj
  }

  def writeParquetTable(df: DataFrame, table: String): Unit = {
    println(s"Writing df into parquet table $table")
    df.coalesce(10)
      .write
      .format("parquet")
      .mode(SaveMode.Overwrite)
      .insertInto(table)
  }

  def GetSchema(file: String): StructType = {
    file match {
      case Actor.file => Actor.fl_schema
      case Address.file => Address.fl_schema
      case Category.file => Category.fl_schema
      case City.file => City.fl_schema
      case Country.file => Country.fl_schema
      case Customer.file => Customer.fl_schema
      case Film.file => Film.fl_schema
      case FilmActor.file => FilmActor.fl_schema
      case FilmCategory.file => FilmCategory.fl_schema
      case Inventory.file => Inventory.fl_schema
      case Languaje.file => Languaje.fl_schema
      case Payment.file => Payment.fl_schema
      case Rental.file => Rental.fl_schema
      case Staff.file => Staff.fl_schema
      case Store.file => Store.fl_schema
      case _ => StructType(Array(StructField("",StringType)))
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
      .filter(findVar(_).nonEmpty)
      .filter(findValue(_).nonEmpty)

    if (prop_lst.isEmpty) {
      println("Properties file have no admissible values or is empty\n")
      sys.exit(1)
    }

    val map: Map[String,String] = prop_lst.map(
      x => {
        findVar(x) -> findValue(x)
      }
    ).toMap

    map
  }

  private def findVar(str: String): String = str.split("=").head.trim()

  private def findValue(str: String): String = str.split("=").tail.mkString("=").trim()

  def read_csv(dir: String, file: String, schema: StructType)(implicit spark: SparkSession): DataFrame = {
    try{
      spark.read
        .option("header","true")
        .option("delimiter","|")
        .schema(schema)
        .csv(s"$dir\\$file")
    } catch {
      case e: Exception =>
        LOG.error(s"ERROR: INGEST FAILED: $file !\n")
        LOG.error(s"ERROR: ${e.getMessage}")

        EmptyDF(schema)
    } finally {
      LOG.info("SparkSession GENERATED !\n")
    }
  }

  def EmptyDF(schema: StructType)(implicit spark:SparkSession): DataFrame = {
    val rowsSeq: Seq[Row] = Seq()
    val rowsRDD: RDD[Row] = spark.sparkContext.parallelize(rowsSeq)

    spark.createDataFrame(rowsRDD, schema)
  }

  def IsEmptyDF(df: DataFrame): Boolean = {
    df.rdd.isEmpty()
  }

  def lanzador(executionType: String, cfg: Map[String, String])(implicit spark: SparkSession): Unit = {
    try{
      executionType match {
        case "FullLoad" => ExecuteFullLoad.run(cfg)
        case "DailyLoad" => ExecuteDailyLoad.run(cfg)
        case "MonthlyLoad" => ExecuteMonthlyLoad.run(cfg)
        case _ => println("Process not implemented yet.")
      }
    } catch {
      case e: Exception =>
        LOG.error(s"ERROR: EXECUTION $executionType FAILED !\n")
        LOG.error(s"ERROR: ${e.getMessage}")

        throw e
    }
  }

  def lanzador0(executionType: String, cfg: Map[String, String])(implicit spark: SparkSession): Unit = {
    val executionMap: Map[String, Any] = Map(
      "FullLoad" -> ExecuteFullLoad.run(cfg),
      "DailyLoad" -> ExecuteDailyLoad.run(cfg),
      "MonthlyLoad" -> ExecuteMonthlyLoad.run(cfg)
    )

    if (executionMap.contains(executionType)) {
      try {
        executionMap(executionType)
      } catch {
        case e: Exception =>
          LOG.error(s"ERROR: EXECUTION $executionType FAILED !\n")
          LOG.error(s"ERROR: ${e.getMessage}")

          throw e
      }
    } else {
      println("Process not implemented yet.")
    }
  }
}
