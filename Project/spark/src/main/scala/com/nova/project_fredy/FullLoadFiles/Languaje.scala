package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Languaje extends File {
  val name = "Language"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("language_id",IntegerType,nullable = true),
    StructField("name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
