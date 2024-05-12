package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Languaje {
  val file = "language.csv"

  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("language_id",IntegerType,nullable = true),
    StructField("name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select =
    s"""
       | language_id INTEGER,
       | name STRING,
       | last_update STRING
       |""".stripMargin
}
