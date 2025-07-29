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
 * Comment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T23:35:34.193120+02:00[Europe/Berlin]", comments = "Generator version: 7.14.0")
public class Comment {

  private Integer commentID;

  private Integer userID;

  private Integer playgroundID;

  private String content;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate commentedAt;

  public Comment() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Comment(Integer commentID, Integer userID, Integer playgroundID, String content, LocalDate commentedAt) {
    this.commentID = commentID;
    this.userID = userID;
    this.playgroundID = playgroundID;
    this.content = content;
    this.commentedAt = commentedAt;
  }

  public Comment commentID(Integer commentID) {
    this.commentID = commentID;
    return this;
  }

  /**
   * Get commentID
   * @return commentID
   */
  @NotNull 
  @Schema(name = "CommentID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("CommentID")
  public Integer getCommentID() {
    return commentID;
  }

  public void setCommentID(Integer commentID) {
    this.commentID = commentID;
  }

  public Comment userID(Integer userID) {
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

  public Comment playgroundID(Integer playgroundID) {
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

  public Comment content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
   */
  @NotNull 
  @Schema(name = "Content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Comment commentedAt(LocalDate commentedAt) {
    this.commentedAt = commentedAt;
    return this;
  }

  /**
   * Get commentedAt
   * @return commentedAt
   */
  @NotNull @Valid 
  @Schema(name = "CommentedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("CommentedAt")
  public LocalDate getCommentedAt() {
    return commentedAt;
  }

  public void setCommentedAt(LocalDate commentedAt) {
    this.commentedAt = commentedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.commentID, comment.commentID) &&
        Objects.equals(this.userID, comment.userID) &&
        Objects.equals(this.playgroundID, comment.playgroundID) &&
        Objects.equals(this.content, comment.content) &&
        Objects.equals(this.commentedAt, comment.commentedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentID, userID, playgroundID, content, commentedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    sb.append("    commentID: ").append(toIndentedString(commentID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    playgroundID: ").append(toIndentedString(playgroundID)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    commentedAt: ").append(toIndentedString(commentedAt)).append("\n");
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

