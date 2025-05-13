package com.demo.poc.commons.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {
  LOGIN("Inicio de sesión"),
  REALM_CREATION("Creación de Realm"),
  USER_CREATION("Creación de usuario"),
  ROLE_CREATION("Creación de rol"),
  ROLE_ASSIGNMENT("Asignación de rol al usuario"),
  CLIENT_CREATION("Creación de cliente"),;

  private final String description;

  public static OperationType parse(String operation) {
    return Arrays.stream(OperationType.values())
        .filter(type -> type.name().equalsIgnoreCase(operation))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("no such operation type: " + operation));
  }
}
