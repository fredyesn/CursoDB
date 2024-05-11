package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object FilmActor {
  val file = "film_actor.csv"

  val schema: StructType = StructType( Array(
    StructField("actor_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
