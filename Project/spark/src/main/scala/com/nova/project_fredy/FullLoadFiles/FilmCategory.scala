package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object FilmCategory extends File {
  val name = "film_category"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("film_id",IntegerType,nullable = true),
    StructField("category_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | actor_id INTEGER,
       | film_id INTEGER,
       | last_update STRING
       |""".stripMargin
}
