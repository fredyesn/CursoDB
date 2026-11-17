# CursoDB

Project to load data into the database used in the course. It is a Scala/Spark batch ingestion project that reads pipe-delimited CSV files (a movie-rental / Sakila-style schema) and writes them into a Hive/Parquet data warehouse, using Databricks / Azure Data Lake (`abfss://`) as the storage layer.

## Repository layout

```
CursoDB/
├── Project/
│   ├── pom.xml                  # Maven parent POM (aggregates the spark module)
│   ├── spark/                   # Spark application module
│   │   ├── pom.xml              # Scala 2.12, Spark 3.5.1, PostgreSQL JDBC driver
│   │   └── src/main/
│   │       ├── resources/conf.properties   # Configured load directories
│   │       └── scala/com/nova/project_fredy/
│   │           ├── Main.scala              # Entry point
│   │           ├── Variables.scala         # Ordered list of load objects
│   │           ├── Utils.scala             # I/O helpers, logging, config parsing
│   │           ├── TableDefinition.scala   # (placeholder) table creation helper
│   │           ├── FullLoad.scala          # Full-load orchestration
│   │           ├── DailyLoad.scala         # Daily load (currently read/inspect only)
│   │           ├── MonthlyLoad.scala       # Monthly load (currently read/inspect only)
│   │           ├── FullLoadFiles/          # One object per table (schema + names)
│   │           ├── Install/PreInstall.scala# Creates land_proj / bu_proj databases
│   │           └── functions/              # Common + FullLoad business logic
│   └── scripts/run.sh           # Example spark-submit launcher
└── Notebooks/                   # Databricks notebooks used in the course exercises
```

## Technology stack

- **Scala 2.12.18**, **Spark 3.5.1** (`spark-core`, `spark-sql`, `spark-hive`, scope `provided`)
- **Maven** build (`maven-scala-plugin`), Java 1.8
- **PostgreSQL JDBC driver** (`org.postgresql:postgresql:42.2.4.jre7`)
- Hadoop `FileSystem`/`Path` APIs for remote configs; data landing on **ADLS Gen2** via `abfss://`

## Data model

`FullLoadFiles/` contains one `File` trait implementation per source table: `actor`, `address`, `category`, `city`, `country`, `customer`, `film`, `film_actor`, `film_category`, `inventory`, `language` (`Languaje`), `payment`, `rental`, `staff`, and `store`. Each object defines:

- `file` – expected CSV filename (lowercase, `|`-delimited, header row)
- `schema` – the `StructType` used when reading the CSV
- `land_table` – target table in the `land_proj` database (landing layer)
- `business_table` – target table in the `bu_proj` database (business layer)

`Variables.full_load_obj_lst` lists the objects in load order; `NullFile` is a sentinel for unknown files.

## Execution modes

`Main` accepts three arguments:

```
Main <conf_file> <execution_type> <preinstall_flag>
```

| Argument | Values |
|---|---|
| `conf_file` | Path (local or HDFS/ADLS) to a `key=value` properties file |
| `execution_type` | `FullLoad`, `DailyLoad`, or `MonthlyLoad` |
| `preinstall_flag` | `1` runs `PreInstall` first (creates the `land_proj` and `bu_proj` Hive databases) |

The properties file maps keys to directories, e.g. `src/main/resources/conf.properties`:

```properties
full_load_dir=/Volumes/volumes/ext-landing/full_load/
daily_load_dir=abfss://ext-landing@saexternalfred.dfs.core.windows.net/daily_load/
monthly_load_dir=abfss://ext-landing@saexternalfred.dfs.core.windows.net/monthly_load/
```

Note: in `Main.scala`, `DailyLoad` and `MonthlyLoad` currently also dispatch to `FullLoad.run` — the dedicated implementations in `DailyLoad.scala`/`MonthlyLoad.scala` only read and preview the CSVs and are not yet wired in.

### FullLoad flow

For every `*.csv` found in `full_load_dir`:

1. Match the filename to a load object (`Utils.getFullLoadObj`); unknown files map to `NullFile` and are skipped.
2. Read the CSV with the declared schema (`header=true`, `delimiter=|`).
3. Create the `land_proj.<table>` Parquet table if it does not exist (`functions.Common.create_parquet_table`) and overwrite-write the DataFrame into it.
4. Run `unitary_test` (currently a pass-through; validations are TODO) and, if the result is non-empty, do the same for `bu_proj.<table>`.

## Building

```bash
cd Project
mvn package
```

This produces `spark/target/spark.jar` and, via the assembly plugin, a `jar-with-dependencies` artifact.

## Running

`Project/scripts/run.sh` shows the intended `spark-submit` invocation (class `com.nova.project_fredy.Main`, 4G driver/executor memory, 5 executors × 5 cores). The concrete command looks like:

```bash
spark-submit \
  --class "com.nova.project_fredy.Main" \
  --driver-memory 4G \
  --executor-memory 4G \
  --executor-cores 5 \
  --num-executors 5 \
  spark.jar \
  <path/to/conf.properties> FullLoad 1
```

For local experimentation, a simpler form is:

```bash
spark-submit --class com.nova.project_fredy.Main \
  spark.jar src/main/resources/conf.properties FullLoad 1
```

## Notebooks

`Notebooks/` contains Databricks notebook exports (`.scala` files) used for course exercises — markdown formatting practice, catalog inspection, and ADLS (`abfss`) connectivity tests. They are standalone and not part of the Maven build.

## Notes / current limitations

- `unitary_test` in `functions/FullLoad.scala` is a TODO pass-through; business-layer validation rules are not implemented.
- `DailyLoad`/`MonthlyLoad` read CSVs and print schemas/samples but do not yet persist data.
- `TableDefinition.create_table` is an empty stub.
- `run.sh` contains a template `spark-submit` snippet inside a heredoc for reference only.
- The PostgreSQL JDBC dependency is declared but not referenced in the current code.
- CSV paths are built with a Windows-style `\\` separator (`s"$dir\\$file"`) — correct only when the Spark driver runs on Windows or against object storage that tolerates it.
- Some module POM properties (`scope=rorac`, `sandbox`, `ufm.version`, `cdh-distribution`) appear to be inherited from a bank-internal Maven archetype and are unused by this project.
- `derby.log` in `Project/` is a leftover from an embedded Derby run.

## Course context

This is a learning project (`CursoDB`, package `com.nova.project_fredy`) implementing a classic medallion-style ETL: **landing** (`land_proj`) → **business** (`bu_proj`) over a film-rental dataset, with incremental daily/monthly loads planned on top of the full load.
EOF
<|close|>argument<|sep|><|open|>argument key="file_path" type="string"<|sep|>/Users/fredysosa/IdeaProjects/CursoDB/README.md
<|close|>call<|sep|><|close|>tools<|sep|><|close|>message<|sep|><|end_of_msg|><|end_message|><|message_model|><|content_text|>I analyzed the project — a Scala/Spark ETL that loads pipe-delimited CSVs (a Sakila-style film-rental schema) into Hive/Parquet tables (`land_proj`, `bu_proj`), with ADLS Gen2 (`abfss://`) storage — and wrote a full description to `README.md` covering the layout, stack, execution modes, build/run commands, and current limitations (TODO validations, unwired Daily/Monthly loads, Windows-style path separators, etc.).<|end_message|><|message_model|>exec<|content_invoke_tool_json|>{