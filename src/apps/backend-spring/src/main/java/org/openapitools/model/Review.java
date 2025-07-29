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
 * Review
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Review {

  private Integer reviewID;

  private Integer rating;

  private Integer userID;

  private Integer playgroundID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate reviewedAt;

  public Review() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Review(Integer reviewID, Integer rating, Integer userID, Integer playgroundID, LocalDate reviewedAt) {
    this.reviewID = reviewID;
    this.rating = rating;
    this.userID = userID;
    this.playgroundID = playgroundID;
    this.reviewedAt = reviewedAt;
  }

  public Review reviewID(Integer reviewID) {
    this.reviewID = reviewID;
    return this;
  }

  /**
   * Get reviewID
   * @return reviewID
   */
  @NotNull 
  @Schema(name = "ReviewID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ReviewID")
  public Integer getReviewID() {
    return reviewID;
  }

  public void setReviewID(Integer reviewID) {
    this.reviewID = reviewID;
  }

  public Review rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
   */
  @NotNull 
  @Schema(name = "Rating", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Rating")
  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public Review userID(Integer userID) {
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

  public Review playgroundID(Integer playgroundID) {
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

  public Review reviewedAt(LocalDate reviewedAt) {
    this.reviewedAt = reviewedAt;
    return this;
  }

  /**
   * Get reviewedAt
   * @return reviewedAt
   */
  @NotNull @Valid 
  @Schema(name = "ReviewedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ReviewedAt")
  public LocalDate getReviewedAt() {
    return reviewedAt;
  }

  public void setReviewedAt(LocalDate reviewedAt) {
    this.reviewedAt = reviewedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Review review = (Review) o;
    return Objects.equals(this.reviewID, review.reviewID) &&
        Objects.equals(this.rating, review.rating) &&
        Objects.equals(this.userID, review.userID) &&
        Objects.equals(this.playgroundID, review.playgroundID) &&
        Objects.equals(this.reviewedAt, review.reviewedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reviewID, rating, userID, playgroundID, reviewedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Review {\n");
    sb.append("    reviewID: ").append(toIndentedString(reviewID)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    reviewedAt: ").append(toIndentedString(reviewedAt)).append("\n");
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

