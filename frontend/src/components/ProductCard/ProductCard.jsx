import { Card, CardBody } from "reactstrap";

import "./ProductCard.css";
import { useNavigate } from "react-router-dom";
import { addToCart } from "src/app/feature/cart/CartSlice";
import { useDispatch } from "react-redux";

export const ProductCard = ({ product }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const gotoDetailPage = () => {
    navigate(`/product-detail/${product.id}`);
  };

  const handleAddToCart = (e) => {
    e.stopPropagation();
    dispatch(addToCart({ product, quantity: 1 }));
  };
  return (
    <Card className="product-card text-center" onClick={gotoDetailPage}>
      <div className="product-card__container">
        <img alt="Sample" src={product.imageUrl} />
        <div className="product-card__overlay" onClick={handleAddToCart}>
          Add to cart
        </div>
      </div>

      <CardBody>
        <p>{product.brand}</p>
        <h4 className="card-product__title">
          <a href="single-product.html">{product.name}</a>
        </h4>
        <p className="card-product__price">${product.price}</p>
      </CardBody>
    </Card>
  );
};
