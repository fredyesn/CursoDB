package com.nova.project_fredy.functions

import com.nova.project_fredy.Utils.{EmptyDF, IsEmptyDF, read_csv}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types.StructType

import scala.util.control.Breaks.{break, breakable}

object FullLoad {

  def read_file(file: String, dir: String, schema: StructType)(implicit spark: SparkSession): DataFrame = {
    var df: DataFrame = EmptyDF(schema)

    if (schema.head.name != "") {
      df = read_csv(dir, file, schema)
    } else{
      println(s"File not included at FullLoad data ingestion: $file")
    }

    df
  }
}
