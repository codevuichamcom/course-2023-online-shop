import React, { useEffect, useState } from "react";
import "./ProductDetailPage.css";
import { BannerPath, Comment, Footer, Header } from "src/components";
import { Button, Col, Container, Input, Row, Spinner } from "reactstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { useParams } from "react-router-dom";
import { axiosClient } from "src/axios/AxiosClient";
import { useDispatch } from "react-redux";
import { addToCart } from "src/app/feature/cart/CartSlice";

const renderTab = (tab) => {
  switch (tab) {
    case 2:
      return <div>Specifications</div>;
    case 3:
      return <Comment />;
    default:
      return (
        <p>
          Beryl Cook is one of Britain’s most talented and amusing artists
          .Beryl’s pictures feature women of all shapes and sizes enjoying
          themselves .Born between the two world wars, Beryl Cook eventually
          left Kendrick School in Reading at the age of 15, where she went to
          secretarial school and then into an insurance office. After moving to
          London and then Hampton, she eventually married her next door
          neighbour from Reading, John Cook. He was an officer in the Merchant
          Navy and after he left the sea in 1956, they bought a pub for a year
          before John took a job in Southern Rhodesia with a motor company.
          Beryl bought their young son a box of watercolours, and when showing
          him how to use it, she decided that she herself quite enjoyed
          painting. John subsequently bought her a child’s painting set for her
          birthday and it was with this that she produced her first significant
          work, a half-length portrait of a dark-skinned lady with a vacant
          expression and large drooping breasts. It was aptly named ‘Hangover’
          by Beryl’s husband and It is often frustrating to attempt to plan
          meals that are designed for one. Despite this fact, we are seeing more
          and more recipe books and Internet websites that are dedicated to the
          act of cooking for one. Divorce and the death of spouses or grown
          children leaving for college are all reasons that someone accustomed
          to cooking for more than one would suddenly need to learn how to
          adjust all the cooking practices utilized before into a streamlined
          plan of cooking that is more efficient for one person creating less
        </p>
      );
  }
};
export const ProductDetailPage = () => {
  const [activeTab, setActiveTab] = useState(1);
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const dispatch = useDispatch();
  useEffect(() => {
    const fetchData = async () => {
      const { data } = await axiosClient.get(`/products/${productId}`);
      setProduct(data);
    };
    fetchData();
  }, []);

  const handleAddToCart = () => {
    dispatch(addToCart({ product, quantity }));
  };

  if (!product)
    return (
      <h1
        style={{
          marginTop: "200px",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <Spinner
          color="primary"
          style={{
            height: "3rem",
            width: "3rem",
          }}
        >
          Loading...
        </Spinner>
      </h1>
    );
  else
    return (
      <div className="product-detail-page">
        <BannerPath title="Product Detail" path="Home - Product detail" />
        <Container className="product-detail-page__product-area">
          <Row>
            <Col className="product-detail-page__product-area__image" lg="6">
              <img className="img-fluid" src={"/" + product.imageUrl} alt="" />
            </Col>
            <Col className="product-detail-page__product-area__infor" lg="6">
              <h3>{product.name}</h3>
              <h2>${product.price}</h2>
              <ul className="product-detail-page__product-area__infor__list">
                <li>
                  <span>Category</span> : {product.category.categoryName}
                </li>
                <li>
                  <span>Availibility</span> : {product.availability}
                </li>
              </ul>
              <p>{product.summary}</p>
              <div className="product-detail-page__product-area__infor__quantity">
                <label htmlFor="quantity">Quantity:</label>
                <Input
                  id="quantity"
                  type="number"
                  size="2"
                  value={quantity}
                  onChange={(e) => {
                    setQuantity(e.target.value);
                  }}
                />
                <Button color="primary" onClick={handleAddToCart}>
                  Add to Cart
                </Button>
              </div>
              <div className="product-detail-page__product-area__infor__favorite">
                <FontAwesomeIcon
                  beat
                  size="2x"
                  icon={faHeart}
                  // mask={faHeart}
                  color="red"
                  className="product-detail-page__product-area__infor__favorite__icon"
                />
                <div>100k+</div>
              </div>
            </Col>
          </Row>
        </Container>
        <Container className="product-detail-page__description-area mb-5">
          <ul className="product-detail-page__description-area__tabs mt-5">
            <li onClick={() => setActiveTab(1)}>
              <Button outline color="primary" active={activeTab === 1}>
                Description
              </Button>
            </li>
            <li onClick={() => setActiveTab(2)}>
              <Button outline color="primary" active={activeTab === 2}>
                Specification
              </Button>
            </li>
            <li onClick={() => setActiveTab(3)}>
              <Button outline color="primary" active={activeTab === 3}>
                Comments & Review
              </Button>
            </li>
          </ul>
          <div className="product-detail-page__description-area__contents">
            {renderTab(activeTab)}
          </div>
        </Container>
      </div>
    );
};
