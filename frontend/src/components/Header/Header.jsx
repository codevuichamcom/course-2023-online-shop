import { useState } from "react";
import {
  Button,
  Collapse,
  Container,
  Nav,
  NavItem,
  Navbar,
  NavbarBrand,
  NavbarToggler,
} from "reactstrap";

import { faSearch, faShoppingCart } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useSelector } from "react-redux";
import { Avatar } from "../Avatar/Avatar";
import "./Header.css";
import { NavLink, useNavigate } from "react-router-dom";

export const Header = () => {
  const navigate = useNavigate();
  const accountLoggedIn = useSelector((state) => state.account.loggedIn);
  const carts = useSelector((state) => state.cart.carts);
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const gotoCartPage = () => {
    navigate("/cart");
  };
  return (
    <div className="header">
      <Container>
        <Navbar expand="xl" light>
          <NavbarBrand href="/">
            <img src="/img/logo.png" />
          </NavbarBrand>
          <NavbarToggler onClick={toggle} />
          <Collapse isOpen={isOpen} navbar>
            <Nav className="ms-auto me-auto" navbar>
              <NavItem>
                <NavLink to="/home">Home</NavLink>
              </NavItem>
              <NavItem>
                <NavLink to="/shop-category">Shop</NavLink>
              </NavItem>
              <NavItem>
                <NavLink to="/blog">Blog</NavLink>
              </NavItem>
              <NavItem>
                <NavLink to="/contact">Contact</NavLink>
              </NavItem>
            </Nav>
            <Nav className="d-flex align-items-center Header__actions">
              <NavItem className="header__actions__search">
                <FontAwesomeIcon icon={faSearch} />
              </NavItem>
              <NavItem className="header__actions__cart" onClick={gotoCartPage}>
                <FontAwesomeIcon icon={faShoppingCart} />{" "}
                {Object.keys(carts).length}
              </NavItem>
              <NavItem className="header__actions__buy">
                <Button color="primary" outline>
                  Buy Now
                </Button>
              </NavItem>
              {accountLoggedIn.username ? (
                <Avatar
                  imageURL="img/user.png"
                  name={accountLoggedIn.username}
                />
              ) : (
                <Button
                  color="primary"
                  outline
                  onClick={() => {
                    navigate("/login");
                  }}
                >
                  Login
                </Button>
              )}
            </Nav>
          </Collapse>
        </Navbar>
      </Container>
    </div>
  );
};
