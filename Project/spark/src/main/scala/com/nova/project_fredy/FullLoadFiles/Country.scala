package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Country {
  val file = "country.csv"

  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("country_id",IntegerType,nullable = true),
    StructField("country",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select =
    s"""
       | city_id INTEGER,
       | city STRING,
       | country_id INTEGER,
       | last_update STRING
       |""".stripMargin
}
