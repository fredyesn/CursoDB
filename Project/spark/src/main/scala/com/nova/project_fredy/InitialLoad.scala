package com.nova.project_fredy

import org.apache.spark.sql.SparkSession

object InitialLoad {
  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder
      .appName("Scala Spark SQL basic example")
      .getOrCreate()

    val dir = "C:\\Users\\fredy\\OneDrive\\Documenten\\Fredy\\cursodatabricks0424\\Practica\\modeloRelacional"
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
