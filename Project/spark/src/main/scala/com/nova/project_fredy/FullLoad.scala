package com.nova.project_fredy

import com.nova.project_fredy.FullLoadFiles.File
import com.nova.project_fredy.Utils.{IsEmptyDF, getFullLoadObj, writeParquetTable}
import com.nova.project_fredy.functions.Common.create_parquet_table
import com.nova.project_fredy.functions.FullLoad.{read_file, unitary_test}

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
        val load_obj: File = getFullLoadObj(file)
        val df_landing = read_file(load_obj, dir)

        df_landing.show(10)

        // this block doesn't execute in case of empty dataframe
        breakable {
          if (IsEmptyDF(df_landing)) break

          // Creating landing table
          create_parquet_table(load_obj.land_table, load_obj.schema)
          // Writing data into landing table
          writeParquetTable(df_landing, load_obj.land_table)

          val df_business = unitary_test(df_landing)

          if (!IsEmptyDF(df_business)){
            // Creating business table
            create_parquet_table(load_obj.business_table, load_obj.schema)
            // Writing data into business table
            writeParquetTable(df_landing, load_obj.business_table)
          } else{
            println(s"There is no data to insert after validations")
          }
        }
      }
    )
  }
}
