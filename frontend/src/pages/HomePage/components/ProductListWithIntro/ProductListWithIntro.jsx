import { Container } from "reactstrap";
import { ProductList } from "src/components";
import "./ProductListWithIntro.css";

export const ProductListWithIntro = ({
  description,
  title,
  name,
  products,
  ...args
}) => {
  return (
    <div className="product-list">
      <Container>
        <div className="product-list__intro">
          <p>{description}</p>
          <h2>
            {title} <span className="product-list__intro__style">{name}</span>
          </h2>
        </div>
        <ProductList products={products} {...args} />
      </Container>
    </div>
  );
};
