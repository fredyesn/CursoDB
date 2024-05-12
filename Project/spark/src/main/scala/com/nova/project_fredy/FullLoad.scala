package com.nova.project_fredy

import com.nova.project_fredy.Utils.{IsEmptyDF, read_csv}
import org.apache.spark.sql.SparkSession

import scala.util.control.Breaks.{break, breakable}

object FullLoad {
  def run(cfg: Map[String,String])(implicit spark: SparkSession): Unit = {
    println("\n**********************************")
    println("**                              **")
    println("**  Executing FullLoad process  **")
    println("**                              **")
    println("**********************************\n")

    val dir = cfg("full_load_dir")
    val files_lst = Utils.list_files(dir)

    files_lst.foreach(
      file => {
        println(s"Reading file: $file")

        val schema = Utils.GetSchema(file)

        if (schema.head.name != ""){
          val df = read_csv(dir, file, schema)

          df.printSchema()

          breakable {
            if (IsEmptyDF(df)) break

            df.show(100)
          }



        } else{
          println(s"File not included at FullLoad data ingestion: $file")
        }
      }
    )
  }
}
