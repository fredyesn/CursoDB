package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object FilmCategory extends File {
  val name = "Film_Category"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("film_id",IntegerType,nullable = true),
    StructField("category_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
