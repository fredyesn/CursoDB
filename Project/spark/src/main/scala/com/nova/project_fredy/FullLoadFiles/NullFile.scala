package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types._

object NullFile extends File {
  val name = "null_file"
  val flg_part: Boolean = false
  val schema: StructType = StructType(Array(StructField("",IntegerType,nullable = true)))
  val cmp_select: String =""
}
