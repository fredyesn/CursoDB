package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Actor {
  val file = "actor.csv"

  val schema: StructType = StructType( Array(
    StructField("actor_id",IntegerType,nullable = true),
    StructField("first_name",StringType,nullable = true),
    StructField("last_name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
