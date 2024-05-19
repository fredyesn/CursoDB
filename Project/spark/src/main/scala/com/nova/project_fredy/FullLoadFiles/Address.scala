package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType, TimestampType}

object Address extends File {
  val name = "address"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("address_id",IntegerType,nullable = true),
    StructField("address",StringType,nullable = true),
    StructField("address2",StringType,nullable = true),
    StructField("district",StringType,nullable = true),
    StructField("city_id",IntegerType,nullable = true),
    StructField("postal_code",IntegerType,nullable = true),
    StructField("phone",LongType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | address_id INTEGER,
       | address STRING,
       | address2 STRING,
       | district STRING,
       | city_id INTEGER,
       | postal_code STRING,
       | phone BIGINT,
       | last_update STRING
       |""".stripMargin
}
