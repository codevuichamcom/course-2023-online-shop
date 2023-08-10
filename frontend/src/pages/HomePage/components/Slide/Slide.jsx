import React from "react";
import { Col, Container, Row } from "reactstrap";
import "./Slide.css";
export const Slide = () => {
  return (
    <Container fluid className="slide">
      <Row className="flex-nowrap">
        <Col sm="4">
          <img className="img-fluid" src="/img/home/hero-slide1.png" alt="" />
        </Col>
        <Col sm="4" className="mt-2 mt-sm-0">
          <img className="img-fluid" src="/img/home/hero-slide2.png" alt="" />
        </Col>
        <Col sm="4" className="mt-2 mt-sm-0">
          <img className="img-fluid" src="/img/home/hero-slide3.png" alt="" />
        </Col>
        <Col sm="4">
          <img className="img-fluid" src="/img/home/hero-slide1.png" alt="" />
        </Col>
        <Col sm="4" className="mt-2 mt-sm-0">
          <img className="img-fluid" src="/img/home/hero-slide2.png" alt="" />
        </Col>
        <Col sm="4" className="mt-2 mt-sm-0">
          <img className="img-fluid" src="/img/home/hero-slide3.png" alt="" />
        </Col>
      </Row>
    </Container>
  );
};
