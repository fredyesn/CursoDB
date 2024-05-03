package com.nova.project_fredy.Files

import org.apache.spark.sql.types.{DecimalType, DoubleType, IntegerType, StringType, StructField, StructType, TimestampType}

object Film {
  val file = "film.csv"

  val schema: StructType = StructType( Array(
    StructField("film_id",IntegerType,nullable = true),
    StructField("title",StringType,nullable = true),
    StructField("description",StringType,nullable = true),
    StructField("release_year",IntegerType,nullable = true),
    StructField("language_id",IntegerType,nullable = true),
    StructField("rental_duration",IntegerType,nullable = true),
    StructField("rental_rate",DecimalType(7,2),nullable = true),
    StructField("length",IntegerType,nullable = true),
    StructField("replacement_cost",DecimalType(10,2),nullable = true),
    StructField("rating",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true),
    StructField("special_features",StringType,nullable = true),
    StructField("fulltext",StringType,nullable = true)
  ))
}
