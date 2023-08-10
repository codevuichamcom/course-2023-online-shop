import React from "react";
import { Pagination, PaginationItem, PaginationLink } from "reactstrap";

export const PaginationComponent = ({ totalPage, pageIndex, onPageChange }) => {
  const listPageItems = [];
  for (let i = 1; i <= totalPage; i++) {
    listPageItems.push(
      <PaginationItem active={i === pageIndex}>
        <PaginationLink
          onClick={() => {
            onPageChange(i);
          }}
        >
          {i}
        </PaginationLink>
      </PaginationItem>
    );
  }
  if (totalPage <= 1) {
    return null;
  } else {
    return (
      <Pagination>
        <PaginationItem>
          <PaginationLink
            first
            onClick={() => {
              onPageChange(1);
            }}
          />
        </PaginationItem>
        <PaginationItem>
          <PaginationLink
            onClick={() => {
              if (pageIndex > 1) {
                onPageChange(pageIndex - 1);
              }
            }}
            previous
          />
        </PaginationItem>
        {listPageItems}
        <PaginationItem>
          <PaginationLink
            onClick={() => {
              if (pageIndex < totalPage) {
                onPageChange(pageIndex + 1);
              }
            }}
            next
          />
        </PaginationItem>
        <PaginationItem>
          <PaginationLink
            onClick={() => {
              onPageChange(totalPage);
            }}
            last
          />
        </PaginationItem>
      </Pagination>
    );
  }
};
