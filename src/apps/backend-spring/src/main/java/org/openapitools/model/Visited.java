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
 * Visited
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Visited {

  private Integer visitedID;

  private Integer userID;

  private Integer playgroundID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate visitedAt;

  public Visited() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Visited(Integer visitedID, Integer userID, Integer playgroundID, LocalDate visitedAt) {
    this.visitedID = visitedID;
    this.userID = userID;
    this.playgroundID = playgroundID;
    this.visitedAt = visitedAt;
  }

  public Visited visitedID(Integer visitedID) {
    this.visitedID = visitedID;
    return this;
  }

  /**
   * Get visitedID
   * @return visitedID
   */
  @NotNull 
  @Schema(name = "VisitedID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("VisitedID")
  public Integer getVisitedID() {
    return visitedID;
  }

  public void setVisitedID(Integer visitedID) {
    this.visitedID = visitedID;
  }

  public Visited userID(Integer userID) {
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

  public Visited playgroundID(Integer playgroundID) {
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

  public Visited visitedAt(LocalDate visitedAt) {
    this.visitedAt = visitedAt;
    return this;
  }

  /**
   * Get visitedAt
   * @return visitedAt
   */
  @NotNull @Valid 
  @Schema(name = "VisitedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("VisitedAt")
  public LocalDate getVisitedAt() {
    return visitedAt;
  }

  public void setVisitedAt(LocalDate visitedAt) {
    this.visitedAt = visitedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Visited visited = (Visited) o;
    return Objects.equals(this.visitedID, visited.visitedID) &&
        Objects.equals(this.userID, visited.userID) &&
        Objects.equals(this.playgroundID, visited.playgroundID) &&
        Objects.equals(this.visitedAt, visited.visitedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visitedID, userID, playgroundID, visitedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Visited {\n");
    sb.append("    visitedID: ").append(toIndentedString(visitedID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    visitedAt: ").append(toIndentedString(visitedAt)).append("\n");
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

