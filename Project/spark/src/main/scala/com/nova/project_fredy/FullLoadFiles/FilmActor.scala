package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object FilmActor extends File {
  val name = "film_actor"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("actor_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | actor_id INTEGER,
       | film_id INTEGER,
       | last_update STRING
       |""".stripMargin
}
