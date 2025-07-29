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
 * Tag
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Tag {

  private Integer tagID;

  private String name;

  public Tag() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Tag(Integer tagID, String name) {
    this.tagID = tagID;
    this.name = name;
  }

  public Tag tagID(Integer tagID) {
    this.tagID = tagID;
    return this;
  }

  /**
   * Get tagID
   * @return tagID
   */
  @NotNull 
  @Schema(name = "TagID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("TagID")
  public Integer getTagID() {
    return tagID;
  }

  public void setTagID(Integer tagID) {
    this.tagID = tagID;
  }

  public Tag name(String name) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(this.tagID, tag.tagID) &&
        Objects.equals(this.name, tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagID, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");
    sb.append("    tagID: ").append(toIndentedString(tagID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

