package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object City {
  val file = "city.csv"

  val schema: StructType = StructType( Array(
    StructField("city_id",IntegerType,nullable = true),
    StructField("city",StringType,nullable = true),
    StructField("country_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
