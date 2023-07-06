package com.example.ONDC.sellerApp.ONDCSellerApp.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Optional;

public class OffsetBasedPageRequest implements Pageable, Serializable {

  private static final long serialVersionUID = -25822477129613575L;
  private final Sort sort;
  private int limit;
  private long offset;

  /**
   * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
   *
   * @param offset zero-based offset.
   * @param limit the size of the elements to be returned.
   * @param sort can be {@literal null}.
   */
  public OffsetBasedPageRequest(long offset, int limit, Sort sort) {

    if (offset < 0) {
      throw new IllegalArgumentException("Offset index must not be less than zero!");
    }

    if (limit < 1) {
      throw new IllegalArgumentException("Limit must not be less than one!");
    }
    this.limit = limit;
    this.offset = offset;
    this.sort = sort;
  }

  /**
   * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
   *
   * @param offset zero-based offset.
   * @param limit the size of the elements to be returned.
   * @param sort the direction of the {@link Sort} to be specified, can be {@literal null}.
   * @param properties the properties to sort by, must not be {@literal null} or empty.
   */
  public OffsetBasedPageRequest(
      int offset, int limit, Sort sort, String... properties) {
    this(offset, limit, sort);
  }

  /**
   * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
   *
   * @param offset zero-based offset.
   * @param limit the size of the elements to be returned.
   */
  public OffsetBasedPageRequest(int offset, int limit) {
    this(offset, limit,Sort.by(new Sort.Order(Sort.Direction.ASC, "id")));
  }

  @Override
  public boolean isPaged() {
    return Pageable.super.isPaged();
  }

  @Override
  public boolean isUnpaged() {
    return Pageable.super.isUnpaged();
  }

  @Override
  public int getPageNumber() {
    return 0;
  }

  @Override
  public int getPageSize() {
    return limit;
  }

  @Override
  public long getOffset() {
    return offset;
  }

  @Override
  public Sort getSort() {
    return sort;
  }

  @Override
  public Sort getSortOr(Sort sort) {
    return Pageable.super.getSortOr(sort);
  }

  @Override
  public Pageable next() {
    return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
  }

  public OffsetBasedPageRequest previous() {
    return hasPrevious()
        ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort())
        : this;
  }

  @Override
  public Pageable previousOrFirst() {
    return hasPrevious() ? previous() : first();
  }

  @Override
  public Pageable first() {
    return new OffsetBasedPageRequest(0, getPageSize(), getSort());
  }

  @Override
  public Pageable withPage(int pageNumber) {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return offset > limit;
  }

  @Override
  public Optional<Pageable> toOptional() {
    return Pageable.super.toOptional();
  }
}
