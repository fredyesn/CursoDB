package com.nova.project_fredy.Files

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object FilmCategory {
  val file = "film_category.csv"

  val schema: StructType = StructType( Array(
    StructField("film_id",IntegerType,nullable = true),
    StructField("category_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
