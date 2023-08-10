import {
  Button,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import "./LoginPage.css";
import { useState } from "react";
import { axiosClient } from "src/axios/AxiosClient";
import { useNavigate } from "react-router-dom";
import { BannerPath } from "src/components";
import { useDispatch } from "react-redux";
import { setLoggedInAccount } from "src/app/feature/account/AccountSlice";
import { toast } from "react-toastify";

export const LoginPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    isRemember: "",
  });
  const handleDataChange = (key, value) => {
    setFormData({ ...formData, [key]: value });
  };

  const handleLogin = async () => {
    const { data, err } = await axiosClient.post("/login", formData);
    if (err) {
      toast.error("Login fail!");
      return;
    }
    const { accessToken, account } = data;
    localStorage.setItem("accessToken", accessToken);
    dispatch(setLoggedInAccount(account));
    navigate("/home");
    toast.success("Login success!");
  };
  return (
    <>
      <main className="login-page">
        <BannerPath title="Login / Register" path="Home - Login / Register" />
        <Container className="login-box">
          <Row className="login-box__wrapper">
            <Col lg="6" className="login-page__left">
              <div className="login-page__create-account">
                <h3>New to our website?</h3>
                <div>
                  There are advances being made in science and technology
                  everyday, and a good example of this is the Create an Account
                </div>
                <Button
                  outline
                  color="#fff"
                  className="login-page__btn-create"
                  onClick={() => {
                    navigate("/register");
                  }}
                >
                  Create an Account
                </Button>
              </div>
            </Col>
            <Col lg="6" className="login-page__right">
              <h3>LOG IN TO ENTER</h3>
              <Form onSubmit={handleLogin} className="login-page__form">
                <FormGroup>
                  <Input
                    id="username"
                    placeholder="Username"
                    onChange={(e) => {
                      handleDataChange("username", e.target.value);
                    }}
                  />
                </FormGroup>
                <FormGroup>
                  <Input
                    type="password"
                    id="password"
                    placeholder="Password"
                    onChange={(e) => {
                      handleDataChange("password", e.target.value);
                    }}
                  />
                </FormGroup>
                <FormGroup check className="login-page__remember">
                  <Label check>
                    <Input
                      type="checkbox"
                      onChange={(e) => {
                        handleDataChange("isRemember", e.target.checked);
                      }}
                    />{" "}
                    Keep me logged in
                  </Label>
                </FormGroup>
                <Button
                  outline
                  color="primary"
                  className="login-page__btn-login"
                  onClick={handleLogin}
                >
                  Login
                </Button>
              </Form>
            </Col>
          </Row>
        </Container>
      </main>
    </>
  );
};
