package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object City extends File {
  val name = "City"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("city_id",IntegerType,nullable = true),
    StructField("city",StringType,nullable = true),
    StructField("country_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
