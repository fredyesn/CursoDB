package com.nova.project_fredy

import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder
      .appName("Scala Spark SQL basic example")
      .getOrCreate()

    val dir = "C:\\Users\\fredy.sosa\\Documents\\Cursos\\AzureDB\\Practica\\modeloRelacional"
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
        df.show()
      }
    )





    spark.close()
  }
}