import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Container, Input, Table } from "reactstrap";
import { BannerPath } from "src/components";
import "./CartPage.css";
import { updateQuatity } from "src/app/feature/cart/CartSlice";
import { useNavigate } from "react-router-dom";

export const CartPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const carts = useSelector((state) => state.cart.carts);

  const renderCartRow = () => {
    const data = [];
    for (const productId in carts) {
      const { product, quantity } = carts[productId];
      data.push(
        <tr>
          <th scope="row">{product.id}</th>
          <td className="cart-area__image">
            <img width={150} height={100} src={product.imageUrl} />
          </td>
          <td>{product.name}</td>
          <td>${product.price}</td>
          <td className="cart-area__quantity">
            <Input
              type="number"
              value={quantity}
              onChange={(e) => {
                dispatch(
                  updateQuatity({
                    productId: product.id,
                    quantity: e.target.value,
                  })
                );
              }}
            />
          </td>
          <td>${product.price * quantity}</td>
        </tr>
      );
    }
    return data;
  };
  return (
    <>
      <BannerPath title="Shopping Cart" path="Home - Shopping Cart" />
      <Container className="cart-area">
        <Table>
          <thead>
            <tr>
              <th>#</th>
              <th colSpan={2}>Product</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>{renderCartRow()}</tbody>
        </Table>
        <Button
          color="primary"
          outline
          className="cart-area__btn-checkout"
          onClick={() => {
            navigate("/checkout");
          }}
        >
          Checkout
        </Button>
      </Container>
    </>
  );
};
