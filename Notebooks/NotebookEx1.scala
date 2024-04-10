// Databricks notebook source
// MAGIC %md
// MAGIC # Header 1
// MAGIC ## Header 2
// MAGIC **bold text**
// MAGIC *italics text*
// MAGIC ~~strikethrough text~~
// MAGIC `monospace text`
// MAGIC ---
// MAGIC > Block quote
// MAGIC Ordered list:
// MAGIC 1. Item 1
// MAGIC 1. Item 2
// MAGIC 1. Item 3
// MAGIC Unordered list:
// MAGIC - Item a
// MAGIC - Item b
// MAGIC - Item c

// COMMAND ----------


// Fibonacci function code
def fibonacci(n: Int): Int = {
  if (n <= 1)
    return n
  else
    return fibonacci(n - 1) + fibonacci(n - 2)
}

println(fibonacci(10))

// COMMAND ----------



// COMMAND ----------

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

val spark = SparkSession.builder()
  .appName("Generate DataFrame")
  .getOrCreate()

val data = Seq(
  ("John", 25, "Emp1", 3000),
  ("Jane", 30, "Emp2", 4000),
  ("David", 35, "Emp3", 5000),
  ("Sarah", 28, "Emp4", 2000),
  ("Michael", 33, "Emp5", 6000),
  ("Emily", 27, "Emp6", 3500),
  ("Daniel", 31, "Emp7", 4500),
  ("Olivia", 29, "Emp8", 3200),
  ("James", 34, "Emp9", 4700),
  ("Lily", 26, "Emp10", 2500),
  ("Mark", 32, "Emp11", 4200),
  ("Emma", 28, "Emp12", 3800),
  ("William", 36, "Emp13", 5500),
  ("Sophia", 31, "Emp14", 2900),
  ("Joshua", 27, "Emp15", 3300),
  ("Ava", 35, "Emp16", 4800),
  ("Daniel", 29, "Emp17", 3100),
  ("Chloe", 33, "Emp18", 4300),
  ("Joseph", 26, "Emp19", 2700),
  ("Madison", 30, "Emp20", 4000)
)

val df = data.toDF("name", "age", "s1emp", "salary")
df.show()
