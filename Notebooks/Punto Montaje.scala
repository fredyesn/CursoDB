// Databricks notebook source
// MAGIC %md
// MAGIC ## Punto Montaje
// MAGIC
// MAGIC
// MAGIC

// COMMAND ----------

val containerName = "container"
val storageAccountName = "sapuntomontajefred"
val sas = "sv=2022-11-02&ss=bfqt&srt=sco&sp=rwdlacupyx&se=2024-04-15T23:35:58Z&st=2024-04-15T16:35:58Z&spr=https,http&sig=Yf6TvRaYql2ATCVL%2BELX5uKlcQNY59sELkOr7PkDtbM%3D"
val config = "fs.azure.sas." + containerName+ "." + storageAccountName + ".blob.core.windows.net"

// COMMAND ----------

/*Montaje de la ruta csv*/

dbutils.fs.mount(
  source = s"wasbs://$containerName@$storageAccountName.blob.core.windows.net",
  mountPoint = "/mnt/myfile",
  extraConfigs = Map(config -> sas))

// COMMAND ----------

display(dbutils.fs.ls("/mnt/myfile"))

// COMMAND ----------

spark.read
.option("delimiter", "|")
.option("header", true)
.csv("/mnt/myfile/customer.csv")
.show()

// COMMAND ----------

dbutils.fs.cp("/mnt/myfile/customer.csv", "/mnt/myfile/customer2.csv")
display(dbutils.fs.ls("/mnt/myfile"))

// COMMAND ----------

dbutils.fs.unmount("/mnt/myfile")
