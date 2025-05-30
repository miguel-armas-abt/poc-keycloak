package com.demo.poc.commons.properties.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputFile {

  private String absolutePath;
  private String filePath;

  @Override
  public String toString() {
    return this.absolutePath + this.filePath;
  }
}
