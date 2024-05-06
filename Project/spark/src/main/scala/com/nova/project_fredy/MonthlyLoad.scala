package com.nova.project_fredy

import org.apache.spark.sql.SparkSession

object MonthlyLoad {
  def run(cfg: Map[String,String])(implicit spark: SparkSession): Unit = {
    println("\n*************************************")
    println("**                                 **")
    println("**  Executing MonthlyLoad process  **")
    println("**                                 **")
    println("*************************************\n")

    val dir = cfg("monthly_load_dir")
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


  }
}
