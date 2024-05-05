package com.nova.project_fredy

import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val prop_file = args(0)
    val execution_type = args(1)

    // Creating Spark Session
    implicit val spark: SparkSession = SparkSession
      .builder
      .appName(s"Scala Spark Proj: $execution_type")
      .getOrCreate()

    // Reading configuration file


    val dir = "C:\\Users\\fredy\\OneDrive\\Documenten\\Fredy\\cursodatabricks0424\\cursodatabricks0424\\Practica\\modeloRelacional"
    val files_lst = Utils.list_files(dir)

    files_lst.foreach(
      file => {
        println(s"Reading file: $file")

        val schema = Utils.GetSchema(file)
        val df = spark.read
          .option("header","true")
          .option("delimiter","|")
          .schema(schema)
          .csv(s"$dir\\$file")

        df.printSchema()
        df.show(100)
      }
    )



    spark.close()
  }
}