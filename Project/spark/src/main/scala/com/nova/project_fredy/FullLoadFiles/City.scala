package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object City extends File {
  val name = "city"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("city_id",IntegerType,nullable = true),
    StructField("city",StringType,nullable = true),
    StructField("country_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | city_id INTEGER,
       | city STRING,
       | country_id INTEGER,
       | last_update STRING
       |""".stripMargin
}
