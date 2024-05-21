package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Country extends File {
  val name = "Country"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("country_id",IntegerType,nullable = true),
    StructField("country",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
