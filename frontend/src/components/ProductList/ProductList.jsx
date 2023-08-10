import { Col, Row } from "reactstrap";
import { ProductCard } from "../ProductCard/ProductCard";

export const ProductList = ({ products = [], ...args }) => {
  return (
    <Row md="2" lg="3" xl="4" {...args}>
      {products.map((product) => (
        <Col key={product.id}>
          <ProductCard product={product} />
        </Col>
      ))}
    </Row>
  );
};
