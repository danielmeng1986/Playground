package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.Playground;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PlaygroundsGet200Response
 */

@JsonTypeName("_playgrounds_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class PlaygroundsGet200Response {

  @Valid
  private List<@Valid Playground> playgrounds = new ArrayList<>();

  private Integer total;

  private Integer skip;

  private Integer limit;

  public PlaygroundsGet200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PlaygroundsGet200Response(List<@Valid Playground> playgrounds, Integer total, Integer skip, Integer limit) {
    this.playgrounds = playgrounds;
    this.total = total;
    this.skip = skip;
    this.limit = limit;
  }

  public PlaygroundsGet200Response playgrounds(List<@Valid Playground> playgrounds) {
    this.playgrounds = playgrounds;
    return this;
  }

  public PlaygroundsGet200Response addPlaygroundsItem(Playground playgroundsItem) {
    if (this.playgrounds == null) {
      this.playgrounds = new ArrayList<>();
    }
    this.playgrounds.add(playgroundsItem);
    return this;
  }

  /**
   * Get playgrounds
   * @return playgrounds
   */
  @NotNull @Valid 
  @Schema(name = "playgrounds", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("playgrounds")
  public List<@Valid Playground> getPlaygrounds() {
    return playgrounds;
  }

  public void setPlaygrounds(List<@Valid Playground> playgrounds) {
    this.playgrounds = playgrounds;
  }

  public PlaygroundsGet200Response total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
   */
  @NotNull 
  @Schema(name = "total", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public PlaygroundsGet200Response skip(Integer skip) {
    this.skip = skip;
    return this;
  }

  /**
   * Get skip
   * @return skip
   */
  @NotNull 
  @Schema(name = "skip", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("skip")
  public Integer getSkip() {
    return skip;
  }

  public void setSkip(Integer skip) {
    this.skip = skip;
  }

  public PlaygroundsGet200Response limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Get limit
   * @return limit
   */
  @NotNull 
  @Schema(name = "limit", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("limit")
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaygroundsGet200Response playgroundsGet200Response = (PlaygroundsGet200Response) o;
    return Objects.equals(this.playgrounds, playgroundsGet200Response.playgrounds) &&
        Objects.equals(this.total, playgroundsGet200Response.total) &&
        Objects.equals(this.skip, playgroundsGet200Response.skip) &&
        Objects.equals(this.limit, playgroundsGet200Response.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playgrounds, total, skip, limit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlaygroundsGet200Response {\n");
    sb.append("    playgrounds: ").append(toIndentedString(playgrounds)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    skip: ").append(toIndentedString(skip)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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

