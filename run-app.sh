#!/bin/bash

PROFILE=${1:-dev}
JAR_NAME="target/service-manager.jar"
LOG_FILE="app.log"

echo "🔧 Using profile: $PROFILE"

if [ ! -f "$JAR_NAME" ]; then
  echo "❌ JAR file not found. Please build the project first using 'mvn clean install'.."
  exit 1
fi

echo " Starting Spring Boot application..."
nohup java -Dspring.profiles.active=$PROFILE -jar "$JAR_NAME" > "$LOG_FILE" 2>&1 &

echo "✅ Application started. Logs: $LOG_FILE"
