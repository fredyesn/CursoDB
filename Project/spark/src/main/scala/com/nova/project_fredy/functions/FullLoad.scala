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
      read_csv(dir, load_obj.file, load_obj.fl_schema)
    else
      EmptyDF(load_obj.fl_schema)
  }

  def unitary_test(df: DataFrame): DataFrame = {
    // TODO: Implementate full_load validations
    df
  }

  def move_file(load_obj: File, dir_input: String, dir_output: String)(implicit spark: SparkSession): Unit = {
    import org.apache.hadoop.fs.{FileSystem, Path}
    val conf = spark.sparkContext.hadoopConfiguration
    val fs = FileSystem.get(conf)
    val file_source = new Path(s"$dir_input${load_obj.file}")
    val file_target = new Path(s"$dir_output${load_obj.file}")

    fs.mkdirs(file_target)
    fs.rename(file_source, file_target)
  }
}
