package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object City extends File {
  val name = "City"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("city_id",IntegerType,nullable = true),
    StructField("city",StringType,nullable = true),
    StructField("country_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
