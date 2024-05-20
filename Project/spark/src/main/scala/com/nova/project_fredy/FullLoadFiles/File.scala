package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

trait File {
  val name: String
  val file: String
  val land_table: String
  val business_table: String
  val flg_part: Boolean
  val schema: StructType
}
