#!/usr/bin/env bash
set -e


export VARIANT="v1"
export SCRIPT_PATH=/docker-entrypoint-initdb.d/
export PGPASSWORD=dadinos
psql -f "$SCRIPT_PATH/scripts/db-$VARIANT.sql"