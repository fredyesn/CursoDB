package com.nova.project_fredy

import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder
      .appName("Scala Spark SQL basic example")
      .getOrCreate()

    val df = spark.read
      .option("header","true")
      .csv("C:\\Users\\fredy.sosa\\Documents\\Cursos\\AzureDB\\Practica\\modeloRelacional\\actor.csv")

    df.show(20)



    spark.close()
  }
}