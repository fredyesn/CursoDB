package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

trait File {
  val name: String
  val file: String = s"$name.csv"
  val land_table: String = s"land_proj.$name"
  val business_table: String = s"bu_proj.$name"
  val flg_part: Boolean
  val schema: StructType
  val cmp_select: String
}
