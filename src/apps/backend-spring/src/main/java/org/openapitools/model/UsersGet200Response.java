package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.User;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UsersGet200Response
 */

@JsonTypeName("_users_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class UsersGet200Response {

  @Valid
  private List<@Valid User> users = new ArrayList<>();

  private Integer total;

  private Integer skip;

  private Integer limit;

  public UsersGet200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UsersGet200Response(List<@Valid User> users, Integer total, Integer skip, Integer limit) {
    this.users = users;
    this.total = total;
    this.skip = skip;
    this.limit = limit;
  }

  public UsersGet200Response users(List<@Valid User> users) {
    this.users = users;
    return this;
  }

  public UsersGet200Response addUsersItem(User usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
   */
  @NotNull @Valid 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("users")
  public List<@Valid User> getUsers() {
    return users;
  }

  public void setUsers(List<@Valid User> users) {
    this.users = users;
  }

  public UsersGet200Response total(Integer total) {
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

  public UsersGet200Response skip(Integer skip) {
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

  public UsersGet200Response limit(Integer limit) {
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
    UsersGet200Response usersGet200Response = (UsersGet200Response) o;
    return Objects.equals(this.users, usersGet200Response.users) &&
        Objects.equals(this.total, usersGet200Response.total) &&
        Objects.equals(this.skip, usersGet200Response.skip) &&
        Objects.equals(this.limit, usersGet200Response.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users, total, skip, limit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersGet200Response {\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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

