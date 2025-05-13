#!/bin/bash

source ./commons.sh

container_name=$1

print_title() {
  echo -e "\n########## ${CYAN} Ejecute una acción ${NC}##########\n"
}

script_caller() {
  $1
  print_title
}

print_title

options=(
  "Iniciar Keycloak (up)"
  "Configurar Keycloak mediante scraping"
  "Detener Keycloak (stop)"
  "Eliminar Keycloak (delete)"
  "Recrear contenedor (recreate)"
  "Salir"
)

while true; do
  select option in "${options[@]}"; do
      case $REPLY in
        1) script_caller "./docker-commands.sh up-compose"; break ;;
        2) script_caller "./scraping-config-keycloak-service.sh"; break ;;
        3) script_caller "./docker-commands.sh stop-compose"; break ;;
        4) script_caller "./docker-commands.sh delete-compose"; break ;;
        5) script_caller "./docker-commands.sh recreate-container $container_name"; break ;;
        6) exit; ;;
        *) echo -e "${RED}Opción inválida${NC}" >&2
      esac
  done
done