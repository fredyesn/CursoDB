package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Category {
  val file = "category.csv"

  val schema: StructType = StructType( Array(
    StructField("category_id",IntegerType,nullable = true),
    StructField("name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
