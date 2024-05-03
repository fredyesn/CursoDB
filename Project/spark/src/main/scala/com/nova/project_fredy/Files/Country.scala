package com.nova.project_fredy.Files

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Country {
  val file = "country.csv"

  val schema: StructType = StructType( Array(
    StructField("country_id",IntegerType,nullable = true),
    StructField("country",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
