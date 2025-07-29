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
 * Favorite
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Favorite {

  private Integer favoriteID;

  private Integer userID;

  private Integer playgroundID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate favoritedAt;

  public Favorite() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Favorite(Integer favoriteID, Integer userID, Integer playgroundID, LocalDate favoritedAt) {
    this.favoriteID = favoriteID;
    this.userID = userID;
    this.playgroundID = playgroundID;
    this.favoritedAt = favoritedAt;
  }

  public Favorite favoriteID(Integer favoriteID) {
    this.favoriteID = favoriteID;
    return this;
  }

  /**
   * Get favoriteID
   * @return favoriteID
   */
  @NotNull 
  @Schema(name = "FavoriteID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("FavoriteID")
  public Integer getFavoriteID() {
    return favoriteID;
  }

  public void setFavoriteID(Integer favoriteID) {
    this.favoriteID = favoriteID;
  }

  public Favorite userID(Integer userID) {
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

  public Favorite playgroundID(Integer playgroundID) {
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

  public Favorite favoritedAt(LocalDate favoritedAt) {
    this.favoritedAt = favoritedAt;
    return this;
  }

  /**
   * Get favoritedAt
   * @return favoritedAt
   */
  @NotNull @Valid 
  @Schema(name = "FavoritedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("FavoritedAt")
  public LocalDate getFavoritedAt() {
    return favoritedAt;
  }

  public void setFavoritedAt(LocalDate favoritedAt) {
    this.favoritedAt = favoritedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Favorite favorite = (Favorite) o;
    return Objects.equals(this.favoriteID, favorite.favoriteID) &&
        Objects.equals(this.userID, favorite.userID) &&
        Objects.equals(this.playgroundID, favorite.playgroundID) &&
        Objects.equals(this.favoritedAt, favorite.favoritedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(favoriteID, userID, playgroundID, favoritedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Favorite {\n");
    sb.append("    favoriteID: ").append(toIndentedString(favoriteID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    favoritedAt: ").append(toIndentedString(favoritedAt)).append("\n");
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

