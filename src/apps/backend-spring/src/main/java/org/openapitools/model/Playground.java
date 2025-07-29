package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Playground
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Playground {

  private Integer playgroundID;

  private String name;

  private String location;

  public Playground() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Playground(Integer playgroundID, String name, String location) {
    this.playgroundID = playgroundID;
    this.name = name;
    this.location = location;
  }

  public Playground playgroundID(Integer playgroundID) {
    this.playgroundID = playgroundID;
    return this;
  }

  /**
   * Get playgroundID
   * @return playgroundID
   */
  @NotNull 
  @Schema(name = "PlaygroundID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("PlaygroundID")
  public Integer getPlaygroundID() {
    return playgroundID;
  }

  public void setPlaygroundID(Integer playgroundID) {
    this.playgroundID = playgroundID;
  }

  public Playground name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Playground location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @NotNull 
  @Schema(name = "Location", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Playground playground = (Playground) o;
    return Objects.equals(this.playgroundID, playground.playgroundID) &&
        Objects.equals(this.name, playground.name) &&
        Objects.equals(this.location, playground.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playgroundID, name, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Playground {\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

