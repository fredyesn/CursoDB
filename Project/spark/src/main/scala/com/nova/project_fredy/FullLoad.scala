package com.nova.project_fredy

import com.nova.project_fredy.Utils.{IsEmptyDF, read_csv}
import com.nova.project_fredy.functions.FullLoad.read_file
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

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

        val df = read_file(file: String, dir: String, schema: StructType)

        df.show(100)

        // this block doesn't execute in case of empty dataframe
        breakable {
          if (IsEmptyDF(df)) break





        }
      }
    )
  }
}
