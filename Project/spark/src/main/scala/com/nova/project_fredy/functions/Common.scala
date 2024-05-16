package com.nova.project_fredy.functions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object Common {
  def create_parquet_table(table: String, path: String, schema: StructType)(implicit spark: SparkSession): Unit = {
    val catalog = spark.catalog
    val options: Map[String,String] = Seq(
      "" -> ""
    ).toMap

    catalog.createTable(table, "parquet", schema, options)
  }
}
