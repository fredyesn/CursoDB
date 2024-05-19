package com.nova.project_fredy.functions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object Common {
  def create_parquet_table(table: String, schema: StructType)(implicit spark: SparkSession): Unit = {
    val catalog = spark.catalog
    val options: Map[String,String] = Seq(
      "purge" -> "true"
    ).toMap

    if (catalog.tableExists(table)){
      println(s"Table $table already exists")
      return
    }

    println(s"Creating table $table")
    catalog.createTable(table, "parquet", schema, options)
  }
}
