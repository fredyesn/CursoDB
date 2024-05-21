package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DecimalType, DoubleType, IntegerType, StringType, StructField, StructType, TimestampType}

object Film extends File {
  val name = "Film"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

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
