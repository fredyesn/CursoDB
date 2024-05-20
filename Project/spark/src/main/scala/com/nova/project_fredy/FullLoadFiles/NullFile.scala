package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types._

object NullFile extends File {
  val name = "null_file"
  val file: String = s"$name.csv"
  val land_table: String = s"land_proj.$name"
  val business_table: String = s"bu_proj.$name"
  val flg_part: Boolean = false
  val schema: StructType = StructType(Array(StructField("",IntegerType,nullable = true)))
}
