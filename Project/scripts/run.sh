#!/bin/bash


##########################################  CONTROL DE VERSIONES ####################################################
# SCRIPT NAME: run.sh
#--------------------------------------------------------------------------------------------------------------------
# Ejemplo COMANDOS EJECUCION:
# ./run.sh PRE 20210731 MEX 12
#
#####################################################################################################################


conf_file="/${enviroment,,}/commondata/bdr/supra_cva/${enviroment^^}/cva_cfg.yml"

echo "Fichero de configuración:  $conf_file"

cd /initiatives_share/bdr/supra_cva/wfm/bdr_extractor

cat << EOF
  spark-submit \ --class "com.nova.project_fredy.Main" \
                 --driver-memory 4G \
                 --executor-memory 4G \
                 --executor-cores 5 \
                 --num-executors 5 core-jar-with-dependencies.jar
EOF

spark-submit --class "com.santander.cib.supra_cva.core.Main" core-jar-with-dependencies.jar

RESULT=$?

if [ ${RESULT} -ne 0 ]; then
    echo "Error en la llamada al script de spark: $RESULT"
    exit $RESULT
fi