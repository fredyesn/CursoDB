package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Category extends File {
  val name = "category"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("category_id",IntegerType,nullable = true),
    StructField("name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | category_id INTEGER,
       | name STRING,
       | last_update STRING
       |""".stripMargin
}
