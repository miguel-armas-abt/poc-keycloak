#!/bin/bash
set -e

source ./commons.sh
source ./variables.env

SCRIPT_NAME="${BASH_SOURCE[0]}"
print_log "$SCRIPT_NAME started"

install_keycloak_settings_scrapping() {
  local original_dir
  original_dir="$(pwd)"

  command="mvn clean install -Dmaven.home=\"$MAVEN_HOME\" -Dmaven.repo.local=\"$MAVEN_REPOSITORY\""
  print_log "$command"
  cd "$KEYCLOAK_SETTINGS_SCRAPING_PATH"
  eval "$command"

  cd "$original_dir"
}

echo -e "${PURPLE}You need to have Chrome updated to its latest version${NC}"

./chromedriver-downloader.sh
install_keycloak_settings_scrapping
run_target_jar "$KEYCLOAK_SETTINGS_SCRAPING_NAME" "$KEYCLOAK_SETTINGS_SCRAPING_PATH"
