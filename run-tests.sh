#!/bin/bash

# Script para executar os testes
# Uso: ./run-tests.sh [browser]
# browser pode ser: chrome (padrão), firefox, edge

BROWSER=${1:-chrome}

echo "Executando testes com navegador: $BROWSER"
mvn clean test -Dbrowser=$BROWSER
