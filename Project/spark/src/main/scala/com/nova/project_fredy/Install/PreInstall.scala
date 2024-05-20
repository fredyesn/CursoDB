package com.nova.project_fredy.Install

import org.apache.spark.sql.SparkSession

object PreInstall {
  def main(args: Array[String]): Unit = {
    implicit val goodSparkSession: SparkSession = SparkSession
      .builder
      .appName(s"Pre-Install")
      .getOrCreate()

    // Creating necessary databases in hive warehouse
    run
  }

  def run()(implicit spark: SparkSession): Unit = {
    // Creating necessary databases in hive warehouse
    spark.sql("CREATE DATABASE IF NOT EXISTS land_proj")
    spark.sql("CREATE DATABASE IF NOT EXISTS bu_proj")
  }
}
