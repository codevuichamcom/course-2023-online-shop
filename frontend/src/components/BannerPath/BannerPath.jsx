import React from "react";
import { Container } from "reactstrap";
import "./BannerPath.css";
export const BannerPath = ({ title, path }) => {
  return (
    <div className="banner-path">
      <Container className="text-center">
        <h1>{title}</h1>
        <span>{path}</span>
      </Container>
    </div>
  );
};
