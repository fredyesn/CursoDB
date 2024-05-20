package com.nova.project_fredy

import com.nova.project_fredy.Install.PreInstall
import com.nova.project_fredy.Utils.{initializeLOG, read_prop_file}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Main {
  val LOG: Logger = initializeLOG(Level.ALL)

  def main(args: Array[String]): Unit = {
    val prop_file = args(0)
    val execution_type = args(1)
    val flag_preinstall = args(2)

    // Creating Spark Session
    implicit val goodSparkSession: SparkSession = SparkSession
      .builder
      .appName(s"Scala Spark Proj: $execution_type")
      .getOrCreate()

    if (flag_preinstall == "1") PreInstall.run()

    // Reading configuration file
    val cfg = read_prop_file(prop_file)

    try{
      execution_type match {
        case "FullLoad" => FullLoad.run(cfg)
        case "DailyLoad" => FullLoad.run(cfg)
        case "MonthlyLoad" => FullLoad.run(cfg)
      }
    } catch {
      case e: Exception =>
        LOG.error(s"ERROR: EXECUTION $execution_type FAILED !\n")
        LOG.error(s"ERROR: ${e.getMessage}")

        throw e
    }
  }
}