package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Wished
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Wished {

  private Integer wishedID;

  private Integer userID;

  private Integer playgroundID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate wishedAt;

  public Wished() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Wished(Integer wishedID, Integer userID, Integer playgroundID, LocalDate wishedAt) {
    this.wishedID = wishedID;
    this.userID = userID;
    this.playgroundID = playgroundID;
    this.wishedAt = wishedAt;
  }

  public Wished wishedID(Integer wishedID) {
    this.wishedID = wishedID;
    return this;
  }

  /**
   * Get wishedID
   * @return wishedID
   */
  @NotNull 
  @Schema(name = "WishedID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("WishedID")
  public Integer getWishedID() {
    return wishedID;
  }

  public void setWishedID(Integer wishedID) {
    this.wishedID = wishedID;
  }

  public Wished userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * Get userID
   * @return userID
   */
  @NotNull 
  @Schema(name = "UserID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("UserID")
  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Wished playgroundID(Integer playgroundID) {
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

  public Wished wishedAt(LocalDate wishedAt) {
    this.wishedAt = wishedAt;
    return this;
  }

  /**
   * Get wishedAt
   * @return wishedAt
   */
  @NotNull @Valid 
  @Schema(name = "WishedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("WishedAt")
  public LocalDate getWishedAt() {
    return wishedAt;
  }

  public void setWishedAt(LocalDate wishedAt) {
    this.wishedAt = wishedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wished wished = (Wished) o;
    return Objects.equals(this.wishedID, wished.wishedID) &&
        Objects.equals(this.userID, wished.userID) &&
        Objects.equals(this.playgroundID, wished.playgroundID) &&
        Objects.equals(this.wishedAt, wished.wishedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wishedID, userID, playgroundID, wishedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Wished {\n");
    sb.append("    wishedID: ").append(toIndentedString(wishedID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    wishedAt: ").append(toIndentedString(wishedAt)).append("\n");
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

