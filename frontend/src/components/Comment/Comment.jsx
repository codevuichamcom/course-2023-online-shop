import { Button, Col, Row } from "reactstrap";
import { StarList } from "../StarList/StarList";
import "./Comment.css";

export const Comment = () => {
  return (
    <div className="comment">
      <Row className="comment__total-rate">
        <Col
          xs={{ offset: 1, size: 4 }}
          className="comment__total-rate__overall"
        >
          <h5>Overall</h5>
          <h4>4.0</h4>
          <h6>(03 Reviews)</h6>
        </Col>
        <Col
          xs={{ offset: 1, size: 5 }}
          className="comment__total-rate__rating-list"
        >
          <h3>Based on 3 Reviews</h3>
          <ul class="list">
            <li>
              <span>5 Star </span>
              <StarList numOfHighLight={5} numOfStar={5} />
              <span> 01</span>
            </li>
            <li>
              <span>4 Star </span>
              <StarList numOfHighLight={4} numOfStar={5} />
              <span> 01</span>
            </li>
            <li>
              <span>3 Star </span>
              <StarList numOfHighLight={3} numOfStar={5} />
              <span> 01</span>
            </li>
            <li>
              <span>2 Star </span>
              <StarList numOfHighLight={2} numOfStar={5} />
              <span> 01</span>
            </li>
            <li>
              <span>1 Star </span>
              <StarList numOfHighLight={1} numOfStar={5} />
              <span> 01</span>
            </li>
          </ul>
        </Col>
      </Row>
      <ul className="comment__message">
        <li className="comment__message__items">
          <div className="comment__message__review">
            <div className="comment__message__review__header me-3">
              <img src="img/product/review-1.png" alt="" />
            </div>
            <div className="comment__message__review__body">
              <h4>Blake Ruiz</h4>
              <StarList
                className="ms-0 me-0"
                numOfHighLight={3}
                numOfStar={5}
              />
              <h5>12th Feb, 2018 at 05:56 pm</h5>
            </div>
            <div className="comment__message__review__action">
              <Button color="primary" outline>
                Reply
              </Button>
            </div>
          </div>
          <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio vero
            beatae, cum corrupti atque, porro quaerat, aliquam mollitia eos
            distinctio aperiam non velit assumenda id voluptates quasi expedita
            adipisci culpa.
          </p>
        </li>
        <li className="comment__message__items comment__message__items--reply">
          <div className="comment__message__review">
            <div className="comment__message__review__header me-3">
              <img src="img/product/review-1.png" alt="" />
            </div>
            <div className="comment__message__review__body">
              <h4>Blake Ruiz</h4>
              <h5>12th Feb, 2018 at 05:56 pm</h5>
            </div>
          </div>
          <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio vero
            beatae, cum corrupti atque, porro quaerat, aliquam mollitia eos
            distinctio aperiam non velit assumenda id voluptates quasi expedita
            adipisci culpa.
          </p>
        </li>
        <li className="comment__message__items comment__message__items--reply">
          <div className="comment__message__review">
            <div className="comment__message__review__header me-3">
              <img src="img/product/review-1.png" alt="" />
            </div>
            <div className="comment__message__review__body">
              <h4>Blake Ruiz</h4>
              <h5>12th Feb, 2018 at 05:56 pm</h5>
            </div>
          </div>
          <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio vero
            beatae, cum corrupti atque, porro quaerat, aliquam mollitia eos
            distinctio aperiam non velit assumenda id voluptates quasi expedita
            adipisci culpa.
          </p>
        </li>
        <li className="comment__message__items comment__message__items--reply">
          <div className="comment__message__review">
            <div className="comment__message__review__header me-3">
              <img src="img/product/review-1.png" alt="" />
            </div>
            <div className="comment__message__review__body">
              <h4>Blake Ruiz</h4>
              <h5>12th Feb, 2018 at 05:56 pm</h5>
            </div>
          </div>
          <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio vero
            beatae, cum corrupti atque, porro quaerat, aliquam mollitia eos
            distinctio aperiam non velit assumenda id voluptates quasi expedita
            adipisci culpa.
          </p>
        </li>
      </ul>
    </div>
  );
};
