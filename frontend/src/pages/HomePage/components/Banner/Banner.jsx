import { Col, Container, Row } from "reactstrap";
import "./Banner.css";

export const Banner = () => {
  return (
    <div className="banner">
      <Container>
        <Row className="banner_row align-items-center pt-60px gx-0">
          <Col xs="5" className="d-none d-sm-block">
            <div className="Banner__img img-fluid">
              <img
                className="img-fluid"
                src="img/home/hero-banner.png"
                alt=""
              />
            </div>
          </Col>
          <Col sm="7" lg="6" className="offset-lg-1 ps-4 ps-md-5 ps-lg-0">
            <div className="banner__content">
              <h4>Shop is fun</h4>
              <h1>Browse Our Premium Product</h1>
              <p>
                Us which over of signs divide dominion deep fill bring they're
                meat beho upon own earth without morning over third. Their male
                dry. They are great appear whose land fly grass.
              </p>
              <a className="banner__browse" href="#">
                Browse Now
              </a>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};
