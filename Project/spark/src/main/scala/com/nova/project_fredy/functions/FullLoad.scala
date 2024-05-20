package com.nova.project_fredy.functions

import com.nova.project_fredy.FullLoadFiles.{File, NullFile}
import com.nova.project_fredy.Utils.{EmptyDF, IsEmptyDF, read_csv}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types.StructType

import scala.util.control.Breaks.{break, breakable}

object FullLoad {
  def read_file(load_obj: File, dir: String)(implicit spark: SparkSession): DataFrame = {
    println(s"Reading file: ${load_obj.file}")
    if (load_obj.name != NullFile.name)
      read_csv(dir, load_obj.file, load_obj.schema)
    else
      EmptyDF(load_obj.schema)
  }

  def unitary_test(df: DataFrame): DataFrame = {
    // TODO: Implementate full_load validations
    df
  }

}
